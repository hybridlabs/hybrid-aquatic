package dev.hybridlabs.aquatic.entity.jellyfish

import dev.hybridlabs.aquatic.entity.ai.goal.boids.StayInWaterGoal
import net.minecraft.core.BlockPos
import net.minecraft.nbt.CompoundTag
import net.minecraft.network.syncher.EntityDataAccessor
import net.minecraft.network.syncher.EntityDataSerializers
import net.minecraft.network.syncher.SynchedEntityData
import net.minecraft.server.level.ServerPlayer
import net.minecraft.sounds.SoundEvent
import net.minecraft.sounds.SoundEvents
import net.minecraft.util.Mth
import net.minecraft.util.RandomSource
import net.minecraft.world.damagesource.DamageSource
import net.minecraft.world.effect.MobEffectInstance
import net.minecraft.world.effect.MobEffects
import net.minecraft.world.entity.EntityDimensions
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.MobSpawnType
import net.minecraft.world.entity.MobType
import net.minecraft.world.entity.MoverType
import net.minecraft.world.entity.Pose
import net.minecraft.world.entity.ai.control.SmoothSwimmingLookControl
import net.minecraft.world.entity.ai.control.SmoothSwimmingMoveControl
import net.minecraft.world.entity.ai.goal.Goal
import net.minecraft.world.entity.ai.navigation.WaterBoundPathNavigation
import net.minecraft.world.entity.animal.WaterAnimal
import net.minecraft.world.entity.player.Player
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

@Suppress("LeakingThis", "DEPRECATION", "UNUSED_PARAMETER")
open class HybridAquaticJellyfishEntity(
    type: EntityType<out HybridAquaticJellyfishEntity>,
    world: Level,
    private val isVenomous: Boolean,
    private val venomLevel: Int

) : WaterAnimal(type, world), GeoEntity {
    private val factory = GeckoLibUtil.createInstanceCache(this)
    var tiltAngle: Float = 0f
    var prevTiltAngle: Float = 0f
    var rollAngle: Float = 0f
    var prevRollAngle: Float = 0f
    private var thrustTimer: Float = 0f
    private var prevThrustTimer: Float = 0f
    private var swimVelocityScale = 0f
    private var thrustTimerSpeed = 0f
    private var tentacleAngle: Float = 0f
    private var prevTentacleAngle: Float = 0f
    private var turningSpeed = 0f
    private var swimX = 0f
    private var swimY = 0f
    private var swimZ = 0f

    init {
        random.setSeed(id.toLong())
        this.thrustTimerSpeed = 1.0f / (random.nextFloat() + 1.0f) * 0.2f
        setPathfindingMalus(BlockPathTypes.WATER, 0.0f)
        moveControl = SmoothSwimmingMoveControl(this, 85, 10, 0.05F, 0.1F, true)
        lookControl = SmoothSwimmingLookControl(this, 10)
        navigation = WaterBoundPathNavigation(this, world)
    }

    override fun registerGoals() {
        goalSelector.addGoal(0, SwimGoal(this))
        goalSelector.addGoal(0, StayInWaterGoal(this))
    }

    override fun defineSynchedData() {
        super.defineSynchedData()
        entityData.define(MOISTNESS, getMaxMoistness())
        entityData.define(JELLYFISH_SIZE, 0)
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

    override fun getMaxSpawnClusterSize(): Int {
        return 2
    }

    override fun getStandingEyeHeight(pose: Pose, dimensions: EntityDimensions): Float {
        return dimensions.height * 0.5f
    }

    override fun getAmbientSound(): SoundEvent {
        return SoundEvents.SQUID_AMBIENT
    }

    override fun getHurtSound(source: DamageSource): SoundEvent {
        return SoundEvents.SLIME_HURT
    }

    override fun getDeathSound(): SoundEvent {
        return SoundEvents.SLIME_DEATH
    }

    override fun getSoundVolume(): Float {
        return 0.4f
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
        }
    }


    override fun removeWhenFarAway(distanceSquared: Double): Boolean {
        return !fromFishingNet && !hasCustomName()
    }

    override fun aiStep() {
        super.aiStep()
        this.prevTiltAngle = this.tiltAngle
        this.prevRollAngle = this.rollAngle
        this.prevThrustTimer = this.thrustTimer
        this.prevTentacleAngle = this.tentacleAngle
        this.thrustTimer += this.thrustTimerSpeed
        if (thrustTimer.toDouble() > 6.283185307179586) {
            if (level().isClientSide) {
                this.thrustTimer = 6.2831855f
            } else {
                this.thrustTimer -= 6.2831855f
                if (random.nextInt(10) == 0) {
                    this.thrustTimerSpeed = 1.0f / (random.nextFloat() + 1.0f) * 0.2f
                }

                level().broadcastEntityEvent(this, 19.toByte())
            }
        }

        if (this.isInWaterOrBubble) {
            if (this.thrustTimer < 3.1415927f) {
                val f = this.thrustTimer / 3.1415927f
                this.tentacleAngle = Mth.sin(f * f * 3.1415927f) * 3.1415927f * 0.25f
                if (f.toDouble() > 0.75) {
                    this.swimVelocityScale = 0.5f
                    this.turningSpeed = 0.5f
                } else {
                    this.turningSpeed *= 0.8f
                }
            } else {
                this.tentacleAngle = 0.0f
                this.swimVelocityScale *= 0.9f
                this.turningSpeed *= 0.99f
            }

            if (!level().isClientSide) {
                this.setDeltaMovement(
                    (this.swimX * this.swimVelocityScale).toDouble(),
                    (this.swimY * this.swimVelocityScale).toDouble(),
                    (this.swimZ * this.swimVelocityScale).toDouble()
                )
            }

            val vec3d = this.deltaMovement
            val d = vec3d.horizontalDistance()
            val targetYaw = -(Mth.atan2(vec3d.x, vec3d.z).toFloat()) * (180f / Math.PI.toFloat())
            val deltaYaw = Mth.wrapDegrees(targetYaw - this.yBodyRot)
            this.yBodyRot += deltaYaw * 0.1f
            this.yHeadRot = this.yBodyRot
            this.yRot = this.yBodyRot
            this.rollAngle += 3.1415927f * this.turningSpeed * 1.5f
            this.tiltAngle += (-(Mth.atan2(d, vec3d.y).toFloat()) * 57.295776f - this.tiltAngle) * 0.1f
        } else {
            this.tentacleAngle = Mth.abs(Mth.sin(this.thrustTimer)) * 3.1415927f * 0.25f
            if (!level().isClientSide) {
                var e = deltaMovement.y
                if (this.hasEffect(MobEffects.LEVITATION)) {
                    e = 0.05 * (getEffect(MobEffects.LEVITATION)!!.amplifier + 1).toDouble()
                } else if (!this.isNoGravity) {
                    e -= 0.08
                }

                this.setDeltaMovement(0.0, e * 0.9800000190734863, 0.0)


            }

            this.tiltAngle += (-90.0f - this.tiltAngle) * 0.02f
        }


    }

    override fun hurt(source: DamageSource, amount: Float): Boolean {
        if (super.hurt(source, amount)) {

            val attacker = source.directEntity
            if (attacker is Player && isVenomous && attacker.mainHandItem.isEmpty) {
                attacker.addEffect(MobEffectInstance(MobEffects.POISON, 200, venomLevel))
                playSound(SoundEvents.PUFFER_FISH_STING, 0.5F, 0.5F)
            }
            return true
        }
        return false
    }

    override fun playerTouch(player: Player) {
        super.playerTouch(player)

        if (player is ServerPlayer && isVenomous && !player.isPassenger) {
            player.hurt(this.damageSources().mobAttack(this), 1.0f)
            player.addEffect(MobEffectInstance(MobEffects.POISON, 100, venomLevel), this)
        }
    }

    override fun travel(movementInput: Vec3) {
        this.move(MoverType.SELF, this.deltaMovement)
    }

    override fun handleEntityEvent(status: Byte) {
        if (status.toInt() == 19) {
            this.thrustTimer = 0.0f
        } else {
            super.handleEntityEvent(status)
        }
    }

    fun setSwimmingVector(x: Float, y: Float, z: Float) {
        this.swimX = x
        this.swimY = y
        this.swimZ = z
    }

    fun hasSwimmingVector(): Boolean {
        return this.swimX != 0.0f || (this.swimY != 0.0f) || (this.swimZ != 0.0f)
    }

    internal class SwimGoal(private val jellyfish: HybridAquaticJellyfishEntity) : Goal() {
        override fun canUse(): Boolean {
            return true
        }

        override fun tick() {
            val i = jellyfish.noActionTime//
            if (i > 100) {
                jellyfish.setSwimmingVector(0.0f, 0.0f, 0.0f)
            } else if (jellyfish.random.nextInt(reducedTickDelay(50)) == 0 || !jellyfish.wasTouchingWater || !jellyfish.hasSwimmingVector()) {
                val f = jellyfish.random.nextFloat() * 6.2831855f
                val g = Mth.cos(f) * 0.2f
                val h = -0.1f + jellyfish.random.nextFloat() * 0.2f
                val j = Mth.sin(f) * 0.2f
                jellyfish.setSwimmingVector(g, h, j)
            }
        }
    }

    private var moistness: Int
        get() = entityData.get(MOISTNESS)
        set(moistness) {
            entityData.set(MOISTNESS, moistness)
        }

    var size: Int
        get() = entityData.get(JELLYFISH_SIZE)
        set(size) {
            entityData.set(JELLYFISH_SIZE, size)
        }

    override fun registerControllers(controllerRegistrar: AnimatableManager.ControllerRegistrar) {
        controllerRegistrar.add(
            AnimationController(
                this,
                "Swim/Idle",
                20
            ) { state: AnimationState<HybridAquaticJellyfishEntity> ->
                if (state.isMoving) {
                    state.setAndContinue(DefaultAnimations.SWIM)
                } else {
                    state.setAndContinue(DefaultAnimations.SWIM)
                }
            }.setOverrideEasingType(EasingType.EASE_IN_OUT_SINE)
        )
    }

    override fun getAnimatableInstanceCache(): AnimatableInstanceCache {
        return factory
    }

    override fun handleAirSupply(air: Int) {}

    private fun getMaxMoistness(): Int {
        return 300
    }

    protected open fun getMinSize(): Int {
        return -3
    }

    protected open fun getMaxSize(): Int {
        return 3
    }

    override fun addAdditionalSaveData(nbt: CompoundTag) {
        super.addAdditionalSaveData(nbt)
        nbt.putInt(MOISTNESS_KEY, moistness)
        nbt.putInt(JELLYFISH_SIZE_KEY, size)
        nbt.putBoolean("FromFishingNet", fromFishingNet)
    }

    private var fromFishingNet = false

    override fun readAdditionalSaveData(nbt: CompoundTag) {
        super.readAdditionalSaveData(nbt)
        moistness = nbt.getInt(MOISTNESS_KEY)
        size = nbt.getInt(JELLYFISH_SIZE_KEY)
        fromFishingNet = nbt.getBoolean("FromFishingNet")
    }

    companion object {
        val MOISTNESS: EntityDataAccessor<Int> =
            SynchedEntityData.defineId(HybridAquaticJellyfishEntity::class.java, EntityDataSerializers.INT)
        val JELLYFISH_SIZE: EntityDataAccessor<Int> =
            SynchedEntityData.defineId(HybridAquaticJellyfishEntity::class.java, EntityDataSerializers.INT)

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
                    world.canSeeSkyFromBelowWater(pos)
        }

        fun canDeepSpawn(
            type: EntityType<out WaterAnimal>,
            world: ServerLevelAccessor,
            reason: MobSpawnType,
            pos: BlockPos,
            random: RandomSource
        ): Boolean {
            val topY = world.seaLevel - 48
            val bottomY = world.seaLevel - 256

            return pos.y in bottomY..topY &&
                    world.isWaterAt(pos)
        }

        fun getScaleAdjustment(jellyfish: HybridAquaticJellyfishEntity, adjustment: Float): Float {
            return 1.0f + (jellyfish.size * adjustment)
        }

        const val MOISTNESS_KEY = "Moistness"
        const val JELLYFISH_SIZE_KEY = "JellyfishSize"
    }
}
