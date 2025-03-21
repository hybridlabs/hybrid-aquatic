package dev.hybridlabs.aquatic.entity.jellyfish

import dev.hybridlabs.aquatic.entity.ai.goal.StayInWaterGoal
import net.minecraft.block.Blocks
import net.minecraft.entity.*
import net.minecraft.entity.ai.control.AquaticMoveControl
import net.minecraft.entity.ai.control.YawAdjustingLookControl
import net.minecraft.entity.ai.goal.Goal
import net.minecraft.entity.ai.pathing.PathNodeType
import net.minecraft.entity.ai.pathing.SwimNavigation
import net.minecraft.entity.damage.DamageSource
import net.minecraft.entity.data.DataTracker
import net.minecraft.entity.data.TrackedData
import net.minecraft.entity.data.TrackedDataHandlerRegistry
import net.minecraft.entity.effect.StatusEffectInstance
import net.minecraft.entity.effect.StatusEffects
import net.minecraft.entity.mob.HostileEntity.isSpawnDark
import net.minecraft.entity.mob.WaterCreatureEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.nbt.NbtCompound
import net.minecraft.server.network.ServerPlayerEntity
import net.minecraft.sound.SoundEvent
import net.minecraft.sound.SoundEvents
import net.minecraft.tag.FluidTags
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.MathHelper
import net.minecraft.util.math.Vec3d
import net.minecraft.util.math.random.Random
import net.minecraft.world.ServerWorldAccess
import net.minecraft.world.World

@Suppress("LeakingThis", "DEPRECATION", "UNUSED_PARAMETER")
open class HybridAquaticJellyfishEntity(
    type: EntityType<out HybridAquaticJellyfishEntity>,
    world: World,
    private val isVenomous: Boolean,
    private val venomLevel: Int

) : WaterCreatureEntity(type, world), GeoEntity {
    private val factory = GeckoLibUtil.createFactory(this)
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
        setPathfindingPenalty(PathNodeType.WATER, 0.0f)
        setPathfindingPenalty(PathNodeType.WALKABLE, 10.0f)
        moveControl = AquaticMoveControl(this, 85, 10, 0.05F, 0.1F, true)
        lookControl = YawAdjustingLookControl(this, 10)
        navigation = SwimNavigation(this, world)
    }

    override fun initGoals() {
        goalSelector.add(0, SwimGoal(this))
        goalSelector.add(0, StayInWaterGoal(this))
    }

    override fun initDataTracker() {
        super.initDataTracker()
        dataTracker.startTracking(MOISTNESS, getMaxMoistness())
        dataTracker.startTracking(JELLYFISH_SIZE, 0)
    }

    override fun getLimitPerChunk(): Int {
        return 4
    }

    override fun getActiveEyeHeight(pose: EntityPose?, dimensions: EntityDimensions): Float {
        return dimensions.height * 0.5f
    }

    override fun getAmbientSound(): SoundEvent {
        return SoundEvents.ENTITY_SQUID_AMBIENT
    }

    override fun getHurtSound(source: DamageSource?): SoundEvent {
        return SoundEvents.ENTITY_SLIME_HURT
    }

    override fun getDeathSound(): SoundEvent {
        return SoundEvents.ENTITY_SLIME_DEATH
    }

    override fun getSoundVolume(): Float {
        return 0.4f
    }

    override fun getMoveEffect(): MoveEffect {
        return MoveEffect.EVENTS
    }

    override fun tick() {
        super.tick()
        if (isAiDisabled) {
            return
        }

        if (isWet) {
            moistness = getMaxMoistness()
        } else {
            moistness -= 1
            if (moistness <= -20) {
                moistness = 0
                damage(DamageSource.DRYOUT, 1.0f)
            }
        }
    }


    override fun canImmediatelyDespawn(distanceSquared: Double): Boolean {
        return !fromFishingNet && !hasCustomName()
    }

    override fun tickMovement() {
        super.tickMovement()
        this.prevTiltAngle = this.tiltAngle
        this.prevRollAngle = this.rollAngle
        this.prevThrustTimer = this.thrustTimer
        this.prevTentacleAngle = this.tentacleAngle
        this.thrustTimer += this.thrustTimerSpeed
        if (thrustTimer.toDouble() > 6.283185307179586) {
            if (world.isClient) {
                this.thrustTimer = 6.2831855f
            } else {
                this.thrustTimer -= 6.2831855f
                if (random.nextInt(10) == 0) {
                    this.thrustTimerSpeed = 1.0f / (random.nextFloat() + 1.0f) * 0.2f
                }

                world.sendEntityStatus(this, 19.toByte())
            }
        }

        if (this.isInsideWaterOrBubbleColumn) {
            if (this.thrustTimer < 3.1415927f) {
                val f = this.thrustTimer / 3.1415927f
                this.tentacleAngle = MathHelper.sin(f * f * 3.1415927f) * 3.1415927f * 0.25f
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

            if (!world.isClient) {
                this.setVelocity(
                    (this.swimX * this.swimVelocityScale).toDouble(),
                    (this.swimY * this.swimVelocityScale).toDouble(),
                    (this.swimZ * this.swimVelocityScale).toDouble()
                )
            }

            val vec3d = this.velocity
            val d = vec3d.horizontalLength()
            this.bodyYaw += (-(MathHelper.atan2(vec3d.x, vec3d.z).toFloat()) * 57.295776f - this.bodyYaw) * 0.1f
            this.yaw = this.bodyYaw
            this.rollAngle += 3.1415927f * this.turningSpeed * 1.5f
            this.tiltAngle += (-(MathHelper.atan2(d, vec3d.y).toFloat()) * 57.295776f - this.tiltAngle) * 0.1f
        } else {
            this.tentacleAngle = MathHelper.abs(MathHelper.sin(this.thrustTimer)) * 3.1415927f * 0.25f
            if (!world.isClient) {
                var e = velocity.y
                if (this.hasStatusEffect(StatusEffects.LEVITATION)) {
                    e = 0.05 * (getStatusEffect(StatusEffects.LEVITATION)!!.amplifier + 1).toDouble()
                } else if (!this.hasNoGravity()) {
                    e -= 0.08
                }

                this.setVelocity(0.0, e * 0.9800000190734863, 0.0)


            }

            this.tiltAngle += (-90.0f - this.tiltAngle) * 0.02f
        }


    }

    override fun damage(source: DamageSource?, amount: Float): Boolean {
        if (super.damage(source, amount)) {

            val attacker = source?.attacker
            if (attacker is PlayerEntity && isVenomous && attacker.mainHandStack.isEmpty) {
                attacker.addStatusEffect(StatusEffectInstance(StatusEffects.POISON, 200, venomLevel))
                playSound(SoundEvents.ENTITY_PUFFER_FISH_STING, 0.5F, 0.5F)
            }

            return true
        }

        return false
    }

    override fun onPlayerCollision(player: PlayerEntity) {
        super.onPlayerCollision(player)

        if (player is ServerPlayerEntity && isVenomous && !player.hasVehicle()) {
            player.damage(DamageSource.mob(this), 1.0f)
            player.addStatusEffect(StatusEffectInstance(StatusEffects.POISON, 100, venomLevel), this)
        }
    }

    override fun travel(movementInput: Vec3d?) {
        this.move(MovementType.SELF, this.velocity)
    }

    override fun handleStatus(status: Byte) {
        if (status.toInt() == 19) {
            this.thrustTimer = 0.0f
        } else {
            super.handleStatus(status)
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
        override fun canStart(): Boolean {
            return true
        }

        override fun tick() {
            val i = jellyfish.despawnCounter
            if (i > 100) {
                jellyfish.setSwimmingVector(0.0f, 0.0f, 0.0f)
            } else if (jellyfish.random.nextInt(toGoalTicks(50)) == 0 || !jellyfish.touchingWater || !jellyfish.hasSwimmingVector()) {
                val f = jellyfish.random.nextFloat() * 6.2831855f
                val g = MathHelper.cos(f) * 0.2f
                val h = -0.1f + jellyfish.random.nextFloat() * 0.2f
                val j = MathHelper.sin(f) * 0.2f
                jellyfish.setSwimmingVector(g, h, j)
            }
        }
    }

    private var moistness: Int
        get() = dataTracker.get(MOISTNESS)
        set(moistness) {
            dataTracker.set(MOISTNESS, moistness)
        }

    var size: Int
        get() = dataTracker.get(JELLYFISH_SIZE)
        set(size) {
            dataTracker.set(JELLYFISH_SIZE, size)
        }

    override fun registerControllers(data: AnimationData) {
        data.addAnimationController(
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

    override fun getFactory(): AnimationFactory {
        return factory
    }

    override fun tickWaterBreathingAir(air: Int) {}

    private fun getMaxMoistness(): Int {
        return 300
    }

    protected open fun getMinSize(): Int {
        return 0
    }

    protected open fun getMaxSize(): Int {
        return 0
    }

    override fun writeCustomDataToNbt(nbt: NbtCompound) {
        super.writeCustomDataToNbt(nbt)
        nbt.putInt(MOISTNESS_KEY, moistness)
        nbt.putInt(JELLYFISH_SIZE_KEY, size)
        nbt.putBoolean("FromFishingNet", fromFishingNet)
    }

    private var fromFishingNet = false

    override fun readCustomDataFromNbt(nbt: NbtCompound) {
        super.readCustomDataFromNbt(nbt)
        moistness = nbt.getInt(MOISTNESS_KEY)
        size = nbt.getInt(JELLYFISH_SIZE_KEY)
        fromFishingNet = nbt.getBoolean("FromFishingNet")
    }

    companion object {
        val MOISTNESS: TrackedData<Int> =
            DataTracker.registerData(HybridAquaticJellyfishEntity::class.java, TrackedDataHandlerRegistry.INTEGER)
        val JELLYFISH_SIZE: TrackedData<Int> =
            DataTracker.registerData(HybridAquaticJellyfishEntity::class.java, TrackedDataHandlerRegistry.INTEGER)

        fun canSpawn(
            type: EntityType<out WaterCreatureEntity>,
            world: ServerWorldAccess,
            reason: SpawnReason,
            pos: BlockPos,
            random: Random
        ): Boolean {
            val topY = world.seaLevel - 4
            val bottomY = world.seaLevel - 24

            return pos.y in bottomY..topY &&
                    world.getFluidState(pos).isIn(FluidTags.WATER) &&
                    world.getFluidState(pos.down()).isIn(FluidTags.WATER) &&
                    world.getBlockState(pos.up()).isOf(Blocks.WATER) &&
                    world.isSkyVisibleAllowingSea(pos) &&
                    !isSpawnDark(world, pos, random)
        }

        fun canUndergroundSpawn(
            type: EntityType<out WaterCreatureEntity>,
            world: ServerWorldAccess,
            reason: SpawnReason,
            pos: BlockPos,
            random: Random
        ): Boolean {
            val topY = world.seaLevel - 24
            val bottomY = world.seaLevel - 128

            return pos.y in bottomY..topY &&
                    world.getFluidState(pos).isIn(FluidTags.WATER) &&
                    world.getFluidState(pos.down()).isIn(FluidTags.WATER) &&
                    world.getBlockState(pos.up()).isOf(Blocks.WATER) &&
                    isSpawnDark(world, pos, random)
        }

        fun getScaleAdjustment(jellyfish: HybridAquaticJellyfishEntity, adjustment: Float): Float {
            return 1.0f + (jellyfish.size * adjustment)
        }

        const val MOISTNESS_KEY = "Moistness"
        const val JELLYFISH_SIZE_KEY = "JellyfishSize"
    }
}
