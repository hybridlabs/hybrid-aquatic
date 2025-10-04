package dev.hybridlabs.aquatic.entity.cephalopod

import dev.hybridlabs.aquatic.entity.fish.HybridAquaticFishEntity
import dev.hybridlabs.aquatic.entity.shark.HybridAquaticSharkEntity
import net.minecraft.core.BlockPos
import net.minecraft.core.particles.ParticleTypes
import net.minecraft.core.particles.SimpleParticleType
import net.minecraft.nbt.CompoundTag
import net.minecraft.network.syncher.EntityDataAccessor
import net.minecraft.network.syncher.EntityDataSerializers
import net.minecraft.network.syncher.SynchedEntityData
import net.minecraft.server.level.ServerLevel
import net.minecraft.sounds.SoundEvent
import net.minecraft.sounds.SoundEvents
import net.minecraft.tags.TagKey
import net.minecraft.util.RandomSource
import net.minecraft.world.DifficultyInstance
import net.minecraft.world.damagesource.DamageSource
import net.minecraft.world.effect.MobEffectInstance
import net.minecraft.world.effect.MobEffects
import net.minecraft.world.entity.*
import net.minecraft.world.entity.ai.control.SmoothSwimmingLookControl
import net.minecraft.world.entity.ai.control.SmoothSwimmingMoveControl
import net.minecraft.world.entity.ai.goal.*
import net.minecraft.world.entity.ai.navigation.WaterBoundPathNavigation
import net.minecraft.world.entity.animal.WaterAnimal
import net.minecraft.world.entity.player.Player
import net.minecraft.world.level.Level
import net.minecraft.world.level.ServerLevelAccessor
import net.minecraft.world.level.pathfinder.BlockPathTypes
import net.minecraft.world.phys.AABB
import net.minecraft.world.phys.Vec3
import software.bernie.geckolib.animatable.GeoEntity
import software.bernie.geckolib.constant.DefaultAnimations
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache
import software.bernie.geckolib.core.animation.AnimatableManager
import software.bernie.geckolib.core.animation.AnimationController
import software.bernie.geckolib.core.animation.AnimationState
import software.bernie.geckolib.core.animation.EasingType
import software.bernie.geckolib.util.GeckoLibUtil


@Suppress("LeakingThis", "unused")
open class HybridAquaticOctopusEntity(
    type: EntityType<out HybridAquaticOctopusEntity>,
    world: Level,
    open val prey: TagKey<EntityType<*>>,
    open val predator: TagKey<EntityType<*>>,
    open var hasInk: Boolean,
) : WaterAnimal(type, world), GeoEntity {
    private val factory = GeckoLibUtil.createInstanceCache(this)
    private var sittingTimer: Int = 0

    init {
        setPathfindingMalus(BlockPathTypes.WATER, 0.0f)
        moveControl = OctopusMoveControl(this, 85, 10, 1.0F, 0.1F, true)
        lookControl = SmoothSwimmingLookControl(this, 10)
        navigation = WaterBoundPathNavigation(this, world)
    }

    override fun aiStep() {
        super.aiStep()
        if (!level().isClientSide() && this.isEffectiveAi) {
            if (this.isInWater) {
                if (this.isSitting()) {
                    if (--this.sittingTimer <= 0) {
                        this.setSitting(false)
                    } else {
                        this.deltaMovement = deltaMovement.subtract(0.0, 0.01, 0.0)
                        this.yHeadRot = 0F
                    }
                } else if (random.nextFloat() <= 0.001f) {
                    this.sittingTimer = random.nextInt(200, 650)
                    this.setSitting(true)
                }
            } else {
                this.setSitting(false)
            }
        }
    }

    fun isSitting(): Boolean {
        return entityData.get(SITTING)
    }

    private fun setSitting(sitting: Boolean) {
        entityData.set(SITTING, sitting)
    }

    override fun isVisuallySwimming(): Boolean {
        return this.isSwimming
    }

    override fun registerGoals() {
        goalSelector.addGoal(0, PanicGoal(this, 1.25))
        goalSelector.addGoal(3, RandomStrollGoal(this, 0.5, 2))
        goalSelector.addGoal(3, OctopusSwimmingGoal(this, 1.0, 10))
        goalSelector.addGoal(4, RandomLookAroundGoal(this))
        goalSelector.addGoal(4, LookAtPlayerGoal(this, Player::class.java, 6.0f))
    }

    override fun defineSynchedData() {
        super.defineSynchedData()
        entityData.define(MOISTNESS, getMaxMoistness())
        entityData.define(OCTOPUS_SIZE, 0)
        entityData.define(HUNGER, MAX_HUNGER)
        entityData.define(ATTEMPT_ATTACK, false)
        entityData.define(SITTING, true)
        entityData.define(TARGET_COLOR, 12799593)
        entityData.define(CURRENT_COLOR, 12799593)
    }

    override fun finalizeSpawn(
        world: ServerLevelAccessor,
        difficulty: DifficultyInstance,
        spawnReason: MobSpawnType,
        entityData: SpawnGroupData?,
        entityNbt: CompoundTag?,
    ): SpawnGroupData? {
        this.size = this.random.nextIntBetweenInclusive(getMinSize(), getMaxSize())
        return super.finalizeSpawn(world, difficulty, spawnReason, entityData, entityNbt)
    }

    override fun getMobType(): MobType {
        return MobType.WATER
    }

    override fun canBreatheUnderwater(): Boolean {
        return true
    }

    override fun isPushedByFluid(): Boolean {
        return false
    }

    override fun tick() {
        super.tick()

        this.determineTargetColor()

        if (isInWaterRainOrBubble) {
            moistness = getMaxMoistness()
        } else {
            moistness -= 1
            if (moistness <= -20) {
                moistness = 0
                hurt(this.damageSources().dryOut(), 1.0f)
            }

            if (!this.isUnderWater) {
                this.xRot = 0.0f
                this.yRot = this.yRotO
                this.yHeadRot = this.yHeadRotO
            }
        }

        if (hunger > 0) hunger -= 1
    }

    fun getTargetColor(): Int {
        return entityData.get(TARGET_COLOR)
    }

    private fun setTargetColor(targetColor: Int) {
        entityData.set(TARGET_COLOR, targetColor)
    }

    fun getCurrentColor(): Int {
        return entityData.get(CURRENT_COLOR)
    }

    fun setCurrentColor(currentColor: Int) {
        entityData.set(CURRENT_COLOR, currentColor)
    }

    private fun determineTargetColor() {
        val currentPos = this.blockPosition()
        val floorPos = this.onPos
        val sharedBlock = level().getBlockState(currentPos)
        val floor = level().getBlockState(floorPos)

        if (!sharedBlock.isAir && !sharedBlock.fluidState.`is`(net.minecraft.tags.FluidTags.WATER)) {
            val sharedColor = sharedBlock.getMapColor(this.level(), currentPos).col
            if (this.getTargetColor() != sharedColor && sharedColor != 0) {
                this.setTargetColor(sharedColor)
            }
        }
        else if (!floor.fluidState.`is`(net.minecraft.tags.FluidTags.WATER)) {
            val floorColor = floor.getMapColor(this.level(), floorPos).col
            if (this.getTargetColor() != floorColor && floorColor != 0) {
                this.setTargetColor(floorColor)
            }
        }
    }

    override fun hurt(source: DamageSource, amount: Float): Boolean {
        if (super.hurt(source, amount) && this.lastHurtByMob != null) {
            if (!level().isClientSide) {
                if (this.isSitting()) {
                    this.setSitting(false)
                }

                if (this.isUnderWater && this.hasInk) {
                    this.squirt()
                }

                val attackerPos = this.lastHurtByMob?.position()
                if (attackerPos != null) {
                    val directionAway = this.position().subtract(attackerPos).normalize().scale(10.0)
                    val targetPos = this.position().add(directionAway.x, 0.0, directionAway.z)

                    this.navigation.moveTo(targetPos.x, targetPos.y, targetPos.z, 1.5)
                }
            }
            return true
        }
        return false
    }

    private fun squirt() {
        this.playSound(this.getSquirtSound(), this.soundVolume, this.voicePitch)

        val entityPosition = Vec3(this.x, this.y, this.z)
        val radius = 3.0

        val affectedEntities = level().getEntitiesOfClass(
            LivingEntity::class.java,
            AABB(
                entityPosition.x - radius, entityPosition.y - radius, entityPosition.z - radius,
                entityPosition.x + radius, entityPosition.y + radius, entityPosition.z + radius
            )
        ) { it != this && it.isAlive }

        for (entity in affectedEntities) {
            entity.addEffect(MobEffectInstance(MobEffects.BLINDNESS, 100, 0))
            entity.addEffect(MobEffectInstance(MobEffects.DARKNESS, 100, 0))
        }

        for (i in 0..199) {
            val offsetX = (random.nextDouble() - 0.5) * 2.0
            val offsetY = (random.nextDouble() - 0.5) * 2.0
            val offsetZ = (random.nextDouble() - 0.5) * 2.0

            val randomMultiplier = 0.5 + random.nextDouble() * 1.5
            val velocity = Vec3(offsetX, offsetY, offsetZ).normalize().scale(randomMultiplier)

            (level() as ServerLevel).sendParticles(
                this.getInkParticle(),
                entityPosition.x,
                entityPosition.y,
                entityPosition.z,
                1,
                velocity.x * 0.25,
                velocity.y * 0.25,
                velocity.z * 0.25,
                0.1
            )
        }
    }

    protected open fun getInkParticle(): SimpleParticleType {
        return ParticleTypes.SQUID_INK
    }

    override fun addAdditionalSaveData(nbt: CompoundTag) {
        super.addAdditionalSaveData(nbt)
        nbt.putInt(MOISTNESS_KEY, moistness)
        nbt.putInt(OCTOPUS_SIZE_KEY, size)
        nbt.putInt(HUNGER_KEY, hunger)
        nbt.putBoolean("FromFishingNet", fromFishingNet)
        nbt.putBoolean("Sitting", isSitting())
        nbt.putInt("targetColor", getTargetColor())
        nbt.putInt("currentColor", getCurrentColor())
    }

    override fun readAdditionalSaveData(nbt: CompoundTag) {
        super.readAdditionalSaveData(nbt)
        moistness = nbt.getInt(MOISTNESS_KEY)
        size = nbt.getInt(OCTOPUS_SIZE_KEY)
        hunger = nbt.getInt(HUNGER_KEY)
        fromFishingNet = nbt.getBoolean("FromFishingNet")
        this.setTargetColor(nbt.getInt("targetColor"))
        this.setCurrentColor(nbt.getInt("currentColor"))
        this.setSitting(nbt.getBoolean("Sitting"))
    }

    override fun getStandingEyeHeight(pose: Pose, dimensions: EntityDimensions): Float {
        return dimensions.height * 0.5f
    }

    override fun removeWhenFarAway(distanceSquared: Double): Boolean {
        return !fromFishingNet && !hasCustomName()
    }

    private fun getMaxMoistness(): Int {
        return 1200
    }

    override fun dropFromLootTable(source: DamageSource, causedByPlayer: Boolean) {
        val attacker = source.directEntity
        if (attacker !is HybridAquaticFishEntity && attacker !is HybridAquaticSharkEntity) {
            super.dropFromLootTable(source, causedByPlayer)
        }
    }

    override fun getMaxSpawnClusterSize(): Int {
        return 1
    }

    override fun getAmbientSound(): SoundEvent {
        return SoundEvents.SQUID_AMBIENT
    }

    override fun getHurtSound(source: DamageSource): SoundEvent {
        return SoundEvents.SQUID_HURT
    }

    override fun getDeathSound(): SoundEvent {
        return SoundEvents.SQUID_DEATH
    }

    private fun getSquirtSound(): SoundEvent {
        return SoundEvents.SQUID_SQUIRT
    }

    //region properties

    private var moistness: Int
        get() = entityData.get(MOISTNESS)
        set(moistness) {
            entityData.set(MOISTNESS, moistness)
        }

    var size: Int
        get() = entityData.get(OCTOPUS_SIZE)
        set(size) {
            entityData.set(OCTOPUS_SIZE, size)
        }

    var hunger: Int
        get() = entityData.get(HUNGER)
        set(hunger) {
            entityData.set(HUNGER, hunger)
        }

    private var attemptAttack: Boolean
        get() = entityData.get(ATTEMPT_ATTACK)
        set(attemptAttack) {
            entityData.set(ATTEMPT_ATTACK, attemptAttack)
        }

    protected open fun getMinSize(): Int {
        return 0
    }

    protected open fun getMaxSize(): Int {
        return 3
    }

    private var fromFishingNet = false

    // endregion

    override fun registerControllers(controllerRegistrar: AnimatableManager.ControllerRegistrar) {
        controllerRegistrar.add(
            AnimationController(
                this,
                "Sit/Swim/Idle",
                20
            ) { state: AnimationState<HybridAquaticOctopusEntity> ->
                if (isSitting() || this.onGround()) {
                    state.setAndContinue(DefaultAnimations.SIT)
                } else {
                    if (state.isMoving) {
                        state.setAndContinue(DefaultAnimations.SWIM)
                    } else {
                        state.setAndContinue(DefaultAnimations.IDLE)
                    }
                }
            }.setOverrideEasingType(EasingType.EASE_IN_OUT_SINE)
        )
    }

    override fun getAnimatableInstanceCache(): AnimatableInstanceCache {
        return factory
    }

    companion object {
        val SITTING: EntityDataAccessor<Boolean> =
            SynchedEntityData.defineId(HybridAquaticOctopusEntity::class.java, EntityDataSerializers.BOOLEAN)
        val MOISTNESS: EntityDataAccessor<Int> =
            SynchedEntityData.defineId(HybridAquaticOctopusEntity::class.java, EntityDataSerializers.INT)
        val OCTOPUS_SIZE: EntityDataAccessor<Int> =
            SynchedEntityData.defineId(HybridAquaticOctopusEntity::class.java, EntityDataSerializers.INT)
        val HUNGER: EntityDataAccessor<Int> =
            SynchedEntityData.defineId(HybridAquaticOctopusEntity::class.java, EntityDataSerializers.INT)
        val ATTEMPT_ATTACK: EntityDataAccessor<Boolean> =
            SynchedEntityData.defineId(HybridAquaticOctopusEntity::class.java, EntityDataSerializers.BOOLEAN)
        private val CURRENT_COLOR: EntityDataAccessor<Int> = SynchedEntityData.defineId(HybridAquaticOctopusEntity::class.java, EntityDataSerializers.INT)
        private val TARGET_COLOR: EntityDataAccessor<Int> = SynchedEntityData.defineId(HybridAquaticOctopusEntity::class.java, EntityDataSerializers.INT)

        const val MAX_HUNGER = 2400
        const val HUNGER_KEY = "Hunger"
        const val MOISTNESS_KEY = "Moistness"
        const val OCTOPUS_SIZE_KEY = "OctopusSize"

        @Suppress("UNUSED_PARAMETER", "DEPRECATION")
        fun canSpawn(
            type: EntityType<out WaterAnimal>,
            world: ServerLevelAccessor,
            reason: MobSpawnType,
            pos: BlockPos,
            random: RandomSource,
        ): Boolean {
            return pos.y >= world.seaLevel - 64 &&
                    world.getBlockState(pos.below()).isSolid
        }

        fun getScaleAdjustment(octopus: HybridAquaticOctopusEntity, adjustment: Float): Float {
            return 1.0f + (octopus.size * adjustment)
        }
    }

    internal class OctopusSwimmingGoal(private val octopus: HybridAquaticOctopusEntity, speedModifier: Double, interval: Int) :
        RandomSwimmingGoal(octopus, speedModifier, interval) {

        override fun canUse(): Boolean {
            return !octopus.isSitting() && super.canUse()
        }
    }

    internal class OctopusMoveControl(
        private val octopus: HybridAquaticOctopusEntity,
        maxTurnX: Int,
        maxTurnY: Int,
        inWaterSpeedModifier: Float,
        outsideWaterSpeedModifier: Float,
        applyGravity: Boolean,
    ) : SmoothSwimmingMoveControl(
        octopus,
        maxTurnX,
        maxTurnY,
        inWaterSpeedModifier,
        outsideWaterSpeedModifier,
        applyGravity) {

        override fun tick() {
            if (!octopus.isSitting()) {
                super.tick()
            }
        }
    }
}