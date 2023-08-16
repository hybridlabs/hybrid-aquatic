package dev.hybridlabs.aquatic.entity.fish

import net.minecraft.block.Blocks
import net.minecraft.entity.EntityData
import net.minecraft.entity.EntityDimensions
import net.minecraft.entity.EntityPose
import net.minecraft.entity.EntityType
import net.minecraft.entity.SpawnReason
import net.minecraft.entity.ai.TargetPredicate
import net.minecraft.entity.ai.control.MoveControl
import net.minecraft.entity.ai.control.YawAdjustingLookControl
import net.minecraft.entity.ai.goal.EscapeDangerGoal
import net.minecraft.entity.ai.goal.LookAroundGoal
import net.minecraft.entity.ai.goal.LookAtEntityGoal
import net.minecraft.entity.ai.goal.MoveIntoWaterGoal
import net.minecraft.entity.ai.goal.SwimAroundGoal
import net.minecraft.entity.ai.pathing.EntityNavigation
import net.minecraft.entity.ai.pathing.SwimNavigation
import net.minecraft.entity.attribute.DefaultAttributeContainer
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.entity.damage.DamageSource
import net.minecraft.entity.data.DataTracker
import net.minecraft.entity.data.TrackedData
import net.minecraft.entity.data.TrackedDataHandlerRegistry
import net.minecraft.entity.mob.WaterCreatureEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.nbt.NbtCompound
import net.minecraft.particle.ParticleTypes
import net.minecraft.registry.tag.FluidTags
import net.minecraft.sound.SoundEvent
import net.minecraft.sound.SoundEvents
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.MathHelper
import net.minecraft.util.math.random.Random
import net.minecraft.world.LocalDifficulty
import net.minecraft.world.ServerWorldAccess
import net.minecraft.world.World
import net.minecraft.world.WorldAccess
import software.bernie.geckolib.animatable.GeoEntity
import software.bernie.geckolib.core.animatable.GeoAnimatable
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache
import software.bernie.geckolib.core.animation.AnimatableManager
import software.bernie.geckolib.core.animation.Animation
import software.bernie.geckolib.core.animation.AnimationController
import software.bernie.geckolib.core.animation.AnimationState
import software.bernie.geckolib.core.animation.RawAnimation
import software.bernie.geckolib.core.`object`.PlayState
import software.bernie.geckolib.util.GeckoLibUtil
import kotlin.math.sqrt

@Suppress("LeakingThis")
open class HybridAquaticFishEntity(type: EntityType<out HybridAquaticFishEntity>, world: World) : WaterCreatureEntity(type, world), GeoEntity {
    private val factory = GeckoLibUtil.createInstanceCache(this)

    override fun initGoals() {
        super.initGoals()
        goalSelector.add(8, EscapeDangerGoal(this, 2.1))
        goalSelector.add(0, MoveIntoWaterGoal(this))
        goalSelector.add(2, SwimToRandomPlaceGoal(this, 0.50, 6))
        goalSelector.add(5, LookAtEntityGoal(this, PlayerEntity::class.java, 12.0f))
        goalSelector.add(4, LookAroundGoal(this))
    }

    override fun initDataTracker() {
        super.initDataTracker()
        dataTracker.startTracking(MOISTNESS, 2400)
    }

    override fun initialize(
        world: ServerWorldAccess,
        difficulty: LocalDifficulty,
        spawnReason: SpawnReason,
        entityData: EntityData?,
        entityNbt: NbtCompound?
    ): EntityData? {
        this.air = this.maxAir
        pitch = 0.0f
        return super.initialize(world, difficulty, spawnReason, entityData, entityNbt)
    }

    override fun tick() {
        super.tick()
        if (isAiDisabled) {
            air = maxAir
        } else {
            if (isWet) {
                moistness = 2400
                air = 4800
            } else {
                moistness -= 1
                if (moistness <= 0) {
                    damage(this.damageSources.dryOut(), 1.0f)
                }
                if (isOnGround) {
                    val randomFloat = random.nextFloat()
                    velocity = velocity.add(
                        ((randomFloat * 2.0f - 1.0f) * 0.2f).toDouble(),
                        0.5,
                        ((random.nextFloat() * 2.0f - 1.0f) * 0.2f).toDouble()
                    )
                    yaw = randomFloat * 360.0f
                    velocityDirty = true
                }
            }
            if (world.isClient && isTouchingWater && isAttacking) {
                val rotationVec = getRotationVec(0.0f)
                val cosYaw = MathHelper.cos(yaw * MathHelper.RADIANS_PER_DEGREE) * 0.6f
                val sinYaw = MathHelper.sin(yaw * MathHelper.RADIANS_PER_DEGREE) * 0.6f
                val offsetY = 0.0f - random.nextFloat() * 0.7f
                for (i in 0..1) {
                    world.addParticle(
                        ParticleTypes.BUBBLE,
                        x - rotationVec.x * offsetY + cosYaw,
                        y - rotationVec.y,
                        z - rotationVec.z * offsetY + sinYaw,
                        0.0,
                        0.0,
                        0.0
                    )
                    world.addParticle(
                        ParticleTypes.BUBBLE,
                        x - rotationVec.x * offsetY - cosYaw,
                        y - rotationVec.y,
                        z - rotationVec.z * offsetY - sinYaw,
                        0.0,
                        0.0,
                        0.0
                    )
                }
            }
        }
    }

    override fun writeCustomDataToNbt(nbt: NbtCompound) {
        super.writeCustomDataToNbt(nbt)
        nbt.putInt(MOISTNESS_KEY, moistness)
    }

    override fun readCustomDataFromNbt(nbt: NbtCompound) {
        super.readCustomDataFromNbt(nbt)
        moistness = nbt.getInt(MOISTNESS_KEY)
    }

    open fun <E : GeoAnimatable> predicate(event: AnimationState<E>): PlayState {
        if (event.isMoving) {
            event.controller.setAnimation(RawAnimation.begin().then("swim", Animation.LoopType.LOOP))
            return PlayState.CONTINUE
        }
        return PlayState.STOP
    }

    override fun getActiveEyeHeight(pose: EntityPose, dimensions: EntityDimensions): Float {
        return dimensions.height * 0.65f
    }

    override fun canImmediatelyDespawn(distanceSquared: Double): Boolean {
        return !hasCustomName()
    }

    override fun getLimitPerChunk(): Int {
        return 8
    }

    open val flopSound: SoundEvent = SoundEvents.ENTITY_PUFFER_FISH_FLOP

    override fun getHurtSound(source: DamageSource): SoundEvent {
        return SoundEvents.ENTITY_COD_HURT
    }

    override fun getDeathSound(): SoundEvent {
        return SoundEvents.ENTITY_COD_DEATH
    }

    override fun getAmbientSound(): SoundEvent {
        return SoundEvents.ENTITY_SALMON_AMBIENT
    }

    override fun getSplashSound(): SoundEvent {
        return SoundEvents.ENTITY_DOLPHIN_SPLASH
    }

    override fun getSwimSound(): SoundEvent {
        return SoundEvents.ENTITY_DOLPHIN_SWIM
    }

    override fun createNavigation(world: World): EntityNavigation {
        return SwimNavigation(this, world)
    }

    var moistness: Int
        get() = dataTracker.get(MOISTNESS)
        set(moistness) {
            dataTracker.set(MOISTNESS, moistness)
        }

    override fun getMaxAir(): Int {
        return 4800
    }

    public override fun getNextAirOnLand(air: Int): Int {
        return this.maxAir
    }

    open val lookPitchSpeed: Int = 1
    open val bodyYawSpeed: Int = 1

    protected fun hasSelfControl(): Boolean {
        return true
    }

    override fun registerControllers(controllerRegistrar: AnimatableManager.ControllerRegistrar) {
        controllerRegistrar.add(
            AnimationController(
                this,
                "controller",
                0,
                ::predicate
            )
        )
    }

    override fun getAnimatableInstanceCache(): AnimatableInstanceCache {
        return factory
    }

    init {
        moveControl = FishMoveControl(this)
        lookControl = YawAdjustingLookControl(this, 10)
    }

    override fun tickMovement() {
        if (!this.isTouchingWater && this.isOnGround && verticalCollision) {
            velocity = velocity.add(
                ((random.nextFloat() * 2.0f - 1.0f) * 0.05f).toDouble(),
                0.4000000059604645,
                ((random.nextFloat() * 2.0f - 1.0f) * 0.05f).toDouble()
            )
            velocityDirty = true
            playSound(flopSound, this.soundVolume, this.soundPitch)
        }
        super.tickMovement()
    }

    internal class FishMoveControl(private val fish: HybridAquaticFishEntity) : MoveControl(fish) {
        override fun tick() {
            if (fish.isSubmergedIn(FluidTags.WATER)) {
                fish.velocity = fish.velocity.add(0.0, 0.005, 0.0)
            }
            if (state == State.MOVE_TO && !fish.getNavigation().isIdle) {
                val f = (speed * fish.getAttributeValue(EntityAttributes.GENERIC_MOVEMENT_SPEED)).toFloat()
                fish.movementSpeed = MathHelper.lerp(0.125f, fish.movementSpeed, f)
                val d = targetX - fish.x
                val e = targetY - fish.y
                val g = targetZ - fish.z
                if (e != 0.0) {
                    val h = sqrt(d * d + e * e + g * g)
                    fish.velocity = fish.velocity.add(0.0, fish.movementSpeed.toDouble() * (e / h) * 0.1, 0.0)
                }
                if (d != 0.0 || g != 0.0) {
                    val i = (MathHelper.atan2(g, d) * 57.2957763671875).toFloat() - 90.0f
                    fish.yaw = wrapDegrees(fish.yaw, i, 90.0f)
                    fish.bodyYaw = fish.yaw
                }
            } else {
                fish.movementSpeed = 0.0f
            }
        }
    }

    internal class SwimToRandomPlaceGoal(private val fish: HybridAquaticFishEntity, d: Double, i: Int) :
        SwimAroundGoal(fish, 1.0, 40) {
        override fun canStart(): Boolean {
            return fish.hasSelfControl() && super.canStart()
        }
    }

    companion object {
        val MOISTNESS: TrackedData<Int> = DataTracker.registerData(HybridAquaticFishEntity::class.java, TrackedDataHandlerRegistry.INTEGER)
        val CLOSE_PLAYER_PREDICATE: TargetPredicate = TargetPredicate.createNonAttackable().setBaseMaxDistance(10.0).ignoreVisibility()

        fun canSpawnDeep(
            type: EntityType<out WaterCreatureEntity?>?,
            world: WorldAccess,
            reason: SpawnReason?,
            pos: BlockPos,
            random: Random?
        ): Boolean {
            return pos.y <= world.seaLevel - 25 && world.getBlockState(pos).isOf(Blocks.WATER) && canSpawn(
                type,
                world,
                reason,
                pos,
                random
            )
        }

        fun createGenericAttributes(): DefaultAttributeContainer.Builder {
            return createMobAttributes()
                    .add(EntityAttributes.GENERIC_MAX_HEALTH, 5.0)
                    .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 1.8)
        }

        const val MOISTNESS_KEY = "Moistness"
    }
}