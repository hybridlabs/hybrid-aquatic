package dev.hybridlabs.aquatic.entity.jellyfish

import dev.hybridlabs.aquatic.entity.ai.goal.boids.StayInWaterGoal
import dev.hybridlabs.aquatic.entity.base.HAWaterAnimal
import dev.hybridlabs.aquatic.world.WorldHelper
import net.minecraft.core.BlockPos
import net.minecraft.server.level.ServerLevel
import net.minecraft.server.level.ServerPlayer
import net.minecraft.sounds.SoundEvent
import net.minecraft.sounds.SoundEvents
import net.minecraft.util.Mth
import net.minecraft.util.RandomSource
import net.minecraft.world.damagesource.DamageSource
import net.minecraft.world.effect.MobEffectInstance
import net.minecraft.world.effect.MobEffects
import net.minecraft.world.entity.*
import net.minecraft.world.entity.ai.control.SmoothSwimmingLookControl
import net.minecraft.world.entity.ai.control.SmoothSwimmingMoveControl
import net.minecraft.world.entity.ai.goal.Goal
import net.minecraft.world.entity.ai.navigation.PathNavigation
import net.minecraft.world.entity.ai.navigation.WaterBoundPathNavigation
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

@Suppress("LeakingThis", "DEPRECATION")
open class HAJellyfishEntity(
    type: EntityType<out HAJellyfishEntity>,
    world: Level,
    private val isVenomous: Boolean,
    private val venomLevel: Int

) : HAWaterAnimal(type, world), GeoEntity {
    private val factory = GeckoLibUtil.createInstanceCache(this)
    var tiltAngle: Float = 0f
    var prevTiltAngle: Float = 0f
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
    }

    override fun createNavigation(level: Level): PathNavigation {
        setPathfindingMalus(BlockPathTypes.WATER, 0.0f)
        setPathfindingMalus(BlockPathTypes.DANGER_FIRE, 16.0f)
        setPathfindingMalus(BlockPathTypes.DAMAGE_FIRE, -1.0f)

        moveControl = SmoothSwimmingMoveControl(this, 85, 10, 0.05F, 0.1F, true)
        lookControl = SmoothSwimmingLookControl(this, 10)

        return WaterBoundPathNavigation(this, level)
    }

    override fun registerGoals() {
        goalSelector.addGoal(0, SwimGoal(this))
        goalSelector.addGoal(0, StayInWaterGoal(this))
    }

    override fun getBreedOffspring(
        p0: ServerLevel,
        p1: AgeableMob,
    ): AgeableMob? {
        return null
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

    override fun aiStep() {
        super.aiStep()
        this.prevTiltAngle = this.tiltAngle
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
            if (attacker is Player && isVenomous && attacker.mainHandItem.isEmpty && !attacker.isPassenger) {
                attacker.addEffect(MobEffectInstance(MobEffects.POISON, 200, venomLevel))
                playSound(SoundEvents.PUFFER_FISH_STING, 0.5F, 0.5F)
            }
            return true
        }
        return false
    }

    override fun playerTouch(player: Player) {
        super.playerTouch(player)

        if (player is ServerPlayer && isVenomous && !player.isPassenger && !this.fromFishingNet) {
            player.hurt(this.damageSources().mobAttack(this), 1.0f)
            player.addEffect(MobEffectInstance(MobEffects.POISON, 100, venomLevel), this)
        }
    }

    override fun travel(movementInput: Vec3) {
        this.move(MoverType.SELF, this.deltaMovement)
    }

    override fun handleEntityEvent(id: Byte) {
        if (id.toInt() == 19) {
            this.thrustTimer = 0.0f
        } else {
            super.handleEntityEvent(id)
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

    internal class SwimGoal(private val jellyfish: HAJellyfishEntity) : Goal() {
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

    override fun registerControllers(controllerRegistrar: AnimatableManager.ControllerRegistrar) {
        controllerRegistrar.add(
            AnimationController(
                this,
                "Swim/Idle",
                20
            ) { state: AnimationState<HAJellyfishEntity> ->
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

    override fun handleAirSupply(air: Int) {
        if (isInWaterOrBubble) {
            airSupply = maxAirSupply
        }
    }

    override fun getMaxMoistness(): Int {
        return 300
    }

    companion object {

        fun canSpawn(
            type: EntityType<out HAWaterAnimal>,
            world: ServerLevelAccessor,
            reason: MobSpawnType,
            pos: BlockPos,
            random: RandomSource,
        ): Boolean {
            val seaLevel = world.level.chunkSource.generator.seaLevel
            val topY = seaLevel - 4
            val bottomY = seaLevel - 24

            return pos.y in bottomY..topY &&
                    world.isWaterAt(pos) &&
                    WorldHelper.canSeeSkyFromBelowWater(world, pos)
        }

        fun canDeepSpawn(
            type: EntityType<out HAWaterAnimal>,
            world: ServerLevelAccessor,
            reason: MobSpawnType,
            pos: BlockPos,
            random: RandomSource,
        ): Boolean {
            val seaLevel = world.level.chunkSource.generator.seaLevel
            val topY = seaLevel - 48
            val bottomY = seaLevel - 256

            return pos.y in bottomY..topY &&
                    world.isWaterAt(pos)
        }
    }
}
