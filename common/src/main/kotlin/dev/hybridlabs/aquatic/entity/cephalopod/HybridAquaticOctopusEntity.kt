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
import net.minecraft.sounds.SoundEvent
import net.minecraft.sounds.SoundEvents
import net.minecraft.tags.TagKey
import net.minecraft.util.RandomSource
import net.minecraft.world.DifficultyInstance
import net.minecraft.world.damagesource.DamageSource
import net.minecraft.world.entity.*
import net.minecraft.world.entity.ai.control.MoveControl
import net.minecraft.world.entity.ai.goal.PanicGoal
import net.minecraft.world.entity.ai.goal.RandomSwimmingGoal
import net.minecraft.world.entity.ai.navigation.GroundPathNavigation
import net.minecraft.world.entity.ai.navigation.WaterBoundPathNavigation
import net.minecraft.world.entity.animal.WaterAnimal
import net.minecraft.world.entity.monster.Monster
import net.minecraft.world.level.Level
import net.minecraft.world.level.ServerLevelAccessor
import net.minecraft.world.level.pathfinder.BlockPathTypes
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
    open var canCamouflage: Boolean,
) : WaterAnimal(type, world), GeoEntity {
    private val factory = GeckoLibUtil.createInstanceCache(this)
    private var waterNavigation: WaterBoundPathNavigation
    private var groundNavigation: GroundPathNavigation

    init {
        setPathfindingMalus(BlockPathTypes.WATER, 0.0f)
        setPathfindingMalus(BlockPathTypes.WATER_BORDER, -1.0f)
        setPathfindingMalus(BlockPathTypes.WALKABLE, -1.0f)
        moveControl = MoveControl(this)
        groundNavigation = GroundPathNavigation(this, world)
        waterNavigation = WaterBoundPathNavigation(this, world)
    }

    private var swimTicksRemaining: Int = 0
    private var swimmingState: Boolean = false

    private fun wantsToSwim(): Boolean {
        if (swimTicksRemaining <= 0) {
            swimmingState = !swimmingState
            swimTicksRemaining = random.nextInt(200, 401)
        }

        swimTicksRemaining--
        return swimmingState
    }


    override fun travel(travelVector: Vec3) {
        if (this.isControlledByLocalInstance && this.isInWater && this.wantsToSwim()) {
            this.moveRelative(0.01f, travelVector)
            this.move(MoverType.SELF, this.deltaMovement)
            this.deltaMovement = deltaMovement.scale(0.9)
        } else {
            super.travel(travelVector)
        }
    }

    override fun updateSwimming() {
        if (!level().isClientSide) {
            if (this.isEffectiveAi && this.isInWater && this.wantsToSwim()) {
                this.navigation = this.waterNavigation
                this.isSwimming = true
            } else {
                this.navigation = this.groundNavigation
                this.isSwimming = false
            }
        }
    }

    override fun isVisuallySwimming(): Boolean {
        return this.isSwimming
    }

    override fun registerGoals() {
        goalSelector.addGoal(0, PanicGoal(this, 1.25))
        goalSelector.addGoal(3, RandomSwimmingGoal(this, 1.0, 10))
    }

    override fun defineSynchedData() {
        super.defineSynchedData()
        entityData.define(MOISTNESS, getMaxMoistness())
        entityData.define(OCTOPUS_SIZE, 0)
        entityData.define(ATTEMPT_ATTACK, false)
        entityData.define(HUNGER, MAX_HUNGER)
    }

    override fun finalizeSpawn(
        world: ServerLevelAccessor,
        difficulty: DifficultyInstance,
        spawnReason: MobSpawnType,
        entityData: SpawnGroupData?,
        entityNbt: CompoundTag?,
    ): SpawnGroupData? {
        this.airSupply = getMaxMoistness()
        this.size = this.random.nextIntBetweenInclusive(getMinSize(), getMaxSize())
        this.xRot = 0.0f
        return super.finalizeSpawn(world, difficulty, spawnReason, entityData, entityNbt)
    }

    override fun getMobType(): MobType {
        return MobType.WATER
    }

    override fun isPushedByFluid(): Boolean {
        return false
    }

    override fun tick() {
        super.tick()

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

        isSprinting = isAggressive

        if (hunger > 0) hunger -= 1
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
    }

    override fun readAdditionalSaveData(nbt: CompoundTag) {
        super.readAdditionalSaveData(nbt)
        moistness = nbt.getInt(MOISTNESS_KEY)
        size = nbt.getInt(OCTOPUS_SIZE_KEY)
        hunger = nbt.getInt(HUNGER_KEY)
        fromFishingNet = nbt.getBoolean("FromFishingNet")
    }

    override fun getStandingEyeHeight(pose: Pose, dimensions: EntityDimensions): Float {
        return dimensions.height * 0.5f
    }

    override fun removeWhenFarAway(distanceSquared: Double): Boolean {
        return !fromFishingNet && !hasCustomName()
    }

    override fun handleAirSupply(air: Int) {}

    private fun getMaxMoistness(): Int {
        return 600
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
                "Swim/Run",
                20
            ) { state: AnimationState<HybridAquaticOctopusEntity> ->
                if (!this.isUnderWater && onGround()) {
                    state.setAndContinue(DefaultAnimations.SIT)
                } else {
                    if (state.isMoving) {
                        state.setAndContinue(if (this.isSprinting) DefaultAnimations.RUN else DefaultAnimations.SWIM)
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
        val MOISTNESS: EntityDataAccessor<Int> =
            SynchedEntityData.defineId(HybridAquaticOctopusEntity::class.java, EntityDataSerializers.INT)
        val OCTOPUS_SIZE: EntityDataAccessor<Int> =
            SynchedEntityData.defineId(HybridAquaticOctopusEntity::class.java, EntityDataSerializers.INT)
        val HUNGER: EntityDataAccessor<Int> =
            SynchedEntityData.defineId(HybridAquaticOctopusEntity::class.java, EntityDataSerializers.INT)
        val ATTEMPT_ATTACK: EntityDataAccessor<Boolean> =
            SynchedEntityData.defineId(HybridAquaticOctopusEntity::class.java, EntityDataSerializers.BOOLEAN)

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
            random: RandomSource
        ): Boolean {
            val topY = world.seaLevel - 4
            val bottomY = world.seaLevel - 24

            return pos.y in bottomY..topY &&
                    world.isWaterAt(pos) &&
                    world.canSeeSkyFromBelowWater(pos) &&
                    !Monster.isDarkEnoughToSpawn(world, pos, random)
        }

        fun getScaleAdjustment(octopus: HybridAquaticOctopusEntity, adjustment: Float): Float {
            return 1.0f + (octopus.size * adjustment)
        }
    }
}