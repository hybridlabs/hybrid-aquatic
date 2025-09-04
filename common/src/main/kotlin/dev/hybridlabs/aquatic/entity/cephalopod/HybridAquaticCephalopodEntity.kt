package dev.hybridlabs.aquatic.entity.cephalopod

import dev.hybridlabs.aquatic.entity.fish.HybridAquaticFishEntity
import dev.hybridlabs.aquatic.entity.shark.HybridAquaticSharkEntity
import net.minecraft.entity.*
import net.minecraft.entity.ai.control.SmoothSwimmingMoveControl
import net.minecraft.entity.ai.control.SmoothSwimmingLookControl
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal
import net.minecraft.entity.ai.goal.PanicGoal
import net.minecraft.entity.ai.goal.MeleeAttackGoal
import net.minecraft.entity.ai.goal.RandomSwimmingGoal
import net.minecraft.entity.ai.pathing.EntityNavigation
import net.minecraft.entity.ai.pathing.BlockPathTypes
import net.minecraft.entity.ai.pathing.WaterBoundPathNavigation
import net.minecraft.entity.damage.DamageSource
import net.minecraft.entity.data.SynchedEntityData
import net.minecraft.entity.data.EntityDataAccessor
import net.minecraft.entity.data.EntityDataSerializers
import net.minecraft.entity.effect.MobEffectInstance
import net.minecraft.entity.effect.MobEffects
import net.minecraft.entity.mob.Monster.isDarkEnoughToSpawn
import net.minecraft.entity.mob.WaterAnimal
import net.minecraft.nbt.CompoundTag
import net.minecraft.particle.ParticleEffect
import net.minecraft.particle.ParticleTypes
import net.minecraft.registry.tag.TagKey
import net.minecraft.server.world.ServerLevel
import net.minecraft.sound.SoundEvent
import net.minecraft.sound.SoundEvents
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Box
import net.minecraft.util.math.Vec3d
import net.minecraft.util.math.random.Random
import net.minecraft.world.DifficultyInstance
import net.minecraft.world.ServerLevelAccess
import net.minecraft.world.World
import software.bernie.geckolib.animatable.GeoEntity
import software.bernie.geckolib.constant.DefaultAnimations
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache
import software.bernie.geckolib.core.animation.AnimatableManager
import software.bernie.geckolib.core.animation.AnimationController
import software.bernie.geckolib.core.animation.AnimationState
import software.bernie.geckolib.core.animation.EasingType
import software.bernie.geckolib.util.GeckoLibUtil

@Suppress("LeakingThis", "UNUSED_PARAMETER")
open class HybridAquaticCephalopodEntity(
    type: EntityType<out HybridAquaticCephalopodEntity>,
    world: World,
    open val prey: TagKey<EntityType<*>>,
    open val predator: TagKey<EntityType<*>>,
    open var hasInk: Boolean,
    open var hasGlowInk: Boolean
) : WaterAnimal(type, world), GeoEntity {
    private val factory = GeckoLibUtil.createInstanceCache(this)

    override fun registerGoals() {
        goalSelector.addGoal(0, PanicGoal(this, 1.25))
        goalSelector.addGoal(3, RandomSwimmingGoal(this, 1.0, 10))
        goalSelector.addGoal(2, CephalopodAttackGoal(this))
        targetSelector.addGoal(
            1,
            NearestAttackableTargetGoal(this, LivingEntity::class.java, 10, true, true) { hunger <= 1200 && it.type.`is`(prey) })
    }

    override fun initSynchedEntityData() {
        super.initSynchedEntityData()
        entityData.define(MOISTNESS, getMaxMoistness())
        entityData.define(CEPHALOPOD_SIZE, 0)
        entityData.define(ATTEMPT_ATTACK, false)
        entityData.define(HUNGER, MAX_HUNGER)
    }

    override fun finalizeSpawn(
        world: ServerLevelAccessor,
        difficulty: DifficultyInstance,
        spawnReason: MobSpawnType,
        entityData: SpawnGroupData?,
        entityNbt: CompoundTag?
    ): SpawnGroupData? {
        this.airSupply= getMaxMoistness()
        this.size = this.random.nextIntBetweenInclusive(getMinSize(), getMaxSize())
        this.pitch = 0.0f
        return super.finalizeSpawn(world, difficulty, spawnReason, entityData, entityNbt)
    }

    override fun tick() {
        super.tick()
        if .isNoAi) {
            return
        }

        if (isInWaterRainOrBubble) {
            moistness = getMaxMoistness()
        } else {
            moistness -= 1
            if (moistness <= -20) {
                moistness = 0
                damage(this.damageSources.dryOut(), 1.0f)
            }

            if (!this.isUnderWater) {
                this.pitch = 0.0f
                this.yaw = this.prevYaw
                this.headYaw = this.prevHeadYaw
            }
        }

        isSprinting = isAttacking

        if (hunger > 0) hunger -= 1
    }

    override fun handleAirSupply(air: Int) {}

    private fun getMaxMoistness(): Int {
        return 600
    }

    override fun damage(source: DamageSource, amount: Float): Boolean {
        if (super.damage(source, amount) && this.attacker != null) {
            if (!world.isClient) {
                if (this.isUnderWater && this.hasInk || this.hasGlowInk) {
                    this.squirt()
                }

                val attackerPos = this.attacker?.pos
                if (attackerPos != null) {
                    val directionAway = this.pos.subtract(attackerPos).normalize().multiply(10.0)
                    val targetPos = this.pos.add(directionAway.x, 0.0, directionAway.z)

                    this.navigation.startMovingTo(targetPos.x, targetPos.y, targetPos.z, 1.5)
                }
            }
            return true
        }
        return false
    }

    private fun squirt() {
        this.playSound(this.getSquirtSound(), this.soundVolume, this.soundPitch)

        val entityPosition = Vec3d(this.x, this.y, this.z)
        val radius = 3.0

        val affectedEntities = world.getEntitiesByClass(
            LivingEntity::class.java,
            Box(
                entityPosition.x - radius, entityPosition.y - radius, entityPosition.z - radius,
                entityPosition.x + radius, entityPosition.y + radius, entityPosition.z + radius
            )
        ) { it != this && it.isAlive }

        for (entity in affectedEntities) {
            entity.addMobEffect(MobEffectInstance(MobEffects.BLINDNESS, 100, 0))
            entity.addMobEffect(MobEffectInstance(MobEffects.DARKNESS, 100, 0))
        }

        for (i in 0..199) {
            val offsetX = (random.nextDouble() - 0.5) * 2.0
            val offsetY = (random.nextDouble() - 0.5) * 2.0
            val offsetZ = (random.nextDouble() - 0.5) * 2.0

            val randomMultiplier = 0.5 + random.nextDouble() * 1.5
            val velocity = Vec3d(offsetX, offsetY, offsetZ).normalize().multiply(randomMultiplier)

            (world as ServerLevel).spawnParticles(
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

    protected open fun getInkParticle(): ParticleEffect {
        return if (this.hasGlowInk) {
            ParticleTypes.GLOW_SQUID_INK
        } else {
            ParticleTypes.SQUID_INK
        }
    }

    override fun addAdditionalSaveData(nbt: CompoundTag) {
        super.addAdditionalSaveData(nbt)
        nbt.putInt(MOISTNESS_KEY, moistness)
        nbt.putInt(CEPHALOPOD_SIZE_KEY, size)
        nbt.putInt(HUNGER_KEY, hunger)
        nbt.putBoolean("FromFishingNet", fromFishingNet)
    }

    override fun readAdditionalSaveData(nbt: CompoundTag) {
        super.readAdditionalSaveData(nbt)
        moistness = nbt.getInt(MOISTNESS_KEY)
        size = nbt.getInt(CEPHALOPOD_SIZE_KEY)
        hunger = nbt.getInt(HUNGER_KEY)
        fromFishingNet = nbt.getBoolean("FromFishingNet")
    }

    override fun getStandingEyeHeight(pose: EntityPose?, dimensions: EntityDimensions): Float {
        return dimensions.height * 0.5f
    }

    override fun removeWhenFarAway(distanceSquared: Double): Boolean {
        return !fromFishingNet && !hasCustomName()
    }

    init {
        setPathfindingMalus(BlockPathTypes.WATER, 0.0f)
        setPathfindingMalus(BlockPathTypes.WATER_BORDER, -1.0f)
        setPathfindingMalus(BlockPathTypes.WALKABLE, -1.0f)
        moveControl = SmoothSwimmingMoveControl(this, 85, 10, 0.05F, 0.1F, true)
        lookControl = SmoothSwimmingLookControl(this, 10)
        navigation = WaterBoundPathNavigation(this, world)
    }

    override fun dropLoot(source: DamageSource, causedByPlayer: Boolean) {
        val attacker = source.attacker
        if (attacker !is HybridAquaticFishEntity && attacker !is HybridAquaticSharkEntity && attacker !is HybridAquaticCephalopodEntity) {
            super.dropLoot(source, causedByPlayer)
        }
    }

    override fun getSpawnClusterSize(): Int {
        return 2
    }

    override fun getAmbientSound(): SoundEvent {
        return SoundEvents._SQUID_AMBIENT
    }

    override fun getHurtSound(source: DamageSource?): SoundEvent {
        return SoundEvents._SQUID_HURT
    }

    override fun getDeathSound(): SoundEvent {
        return SoundEvents._SQUID_DEATH
    }

    private fun getSquirtSound(): SoundEvent {
        return SoundEvents._SQUID_SQUIRT
    }

    override fun createNavigation(world: Level): EntityNavigation {
        return WaterBoundPathNavigation(this, world)
    }

    //region properties

    private var moistness: Int
        get() = entityData.get(MOISTNESS)
        set(moistness) {
            entityData.set(MOISTNESS, moistness)
        }

    var size: Int
        get() = entityData.get(CEPHALOPOD_SIZE)
        set(size) {
            entityData.set(CEPHALOPOD_SIZE, size)
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

    // endregion

    override fun getMaxAir(): Int {
        return 600
    }

    public override fun getNextAirOnLand(air: Int): Int {
        return this.maxAir
    }

    override fun registerControllers(controllerRegistrar: AnimatableManager.ControllerRegistrar) {
        controllerRegistrar.add(
            AnimationController(
                this,
                "Swim/Run",
                20
            ) { state: AnimationState<HybridAquaticCephalopodEntity> ->
                if (!this.isUnderWater && isOnGround) {
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

    protected open fun getMinSize(): Int {
        return 0
    }

    protected open fun getMaxSize(): Int {
        return 0
    }

    private var fromFishingNet = false

    internal class CephalopodAttackGoal(private val cephalopod: HybridAquaticCephalopodEntity) :
        MeleeAttackGoal(cephalopod, 1.0, true) {
        override fun canUse(): Boolean {
            return !cephalopod.fromFishingNet && super.canUse()
        }

        override fun attack(target: LivingEntity, squaredDistance: Double) {
            val d = getSquaredMaxAttackDistance(target)
            if (squaredDistance <= d && this.isCooledDown) {
                resetCooldown()
                mob.doHurtTarget(target)
                cephalopod.isSprinting = true
                cephalopod.attemptAttack = true

                if (target.health <= 0)
                    cephalopod.hunger = HybridAquaticSharkEntity.MAX_HUNGER
                cephalopod.health = cephalopod.maxHealth
            }
        }

        override fun getSquaredMaxAttackDistance(entity: LivingEntity): Double {
            return (1.25f + entity.width).toDouble()
        }

        override fun start() {
            super.start()
            cephalopod.attemptAttack = false
        }

        override fun stop() {
            super.stop()
            cephalopod.attemptAttack = false
        }
    }

    companion object {
        val MOISTNESS: EntityDataAccessor<Int> =
            SynchedEntityData.defineId(HybridAquaticCephalopodEntity::class.java, EntityDataSerializers.INTEGER)
        val CEPHALOPOD_SIZE: EntityDataAccessor<Int> =
            SynchedEntityData.defineId(HybridAquaticCephalopodEntity::class.java, EntityDataSerializers.INTEGER)
        val HUNGER: EntityDataAccessor<Int> =
            SynchedEntityData.defineId(HybridAquaticFishEntity::class.java, EntityDataSerializers.INTEGER)
        val ATTEMPT_ATTACK: EntityDataAccessor<Boolean> =
            SynchedEntityData.defineId(HybridAquaticCephalopodEntity::class.java, EntityDataSerializers.BOOLEAN)

        const val MAX_HUNGER = 2400
        const val HUNGER_KEY = "Hunger"
        const val MOISTNESS_KEY = "Moistness"
        const val CEPHALOPOD_SIZE_KEY = "CephalopodSize"

        @Suppress("UNUSED_PARAMETER", "DEPRECATION")
        fun canSpawn(
            type: EntityType<out WaterAnimal>,
            world: ServerLevelAccessor,
            reason: MobSpawnType,
            pos: BlockPos,
            random: Random
        ): Boolean {
            val topY = world.seaLevel - 4
            val bottomY = world.seaLevel - 24

            return pos.y in bottomY..topY &&
                    world.isWaterAt(pos) &&
                    world.canSeeSkyFromBelowWater(pos) &&
                    !isDarkEnoughToSpawn(world, pos, random)
        }

        @Suppress("UNUSED_PARAMETER", "DEPRECATION")
        fun canNightSpawn(
            type: EntityType<out WaterAnimal>,
            world: ServerLevelAccessor,
            reason: MobSpawnType,
            pos: BlockPos,
            random: Random
        ): Boolean {
            val topY = world.seaLevel - 4
            val bottomY = world.seaLevel - 24

            return !world.toServerLevel().isDay &&
                    pos.y in bottomY..topY &&
                    world.isWaterAt(pos) &&
                    world.canSeeSkyFromBelowWater(pos) &&
                    !isDarkEnoughToSpawn(world, pos, random)
        }

        @Suppress("UNUSED_PARAMETER", "DEPRECATION")
        fun canDeepSpawn(
            type: EntityType<out WaterAnimal>,
            world: ServerLevelAccessor,
            reason: MobSpawnType,
            pos: BlockPos,
            random: Random
        ): Boolean {
            val topY = world.seaLevel - 24
            val bottomY = world.seaLevel - 128

            return pos.y in bottomY..topY &&
                    world.isWaterAt(pos) &&
                    isDarkEnoughToSpawn(world, pos, random)
        }

        fun getScaleAdjustment(cephalopod: HybridAquaticCephalopodEntity, adjustment: Float): Float {
            return 1.0f + (cephalopod.size * adjustment)
        }
    }
}