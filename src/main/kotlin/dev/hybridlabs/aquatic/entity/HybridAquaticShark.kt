package dev.hybridlabs.aquatic.entity

import net.minecraft.block.Blocks
import net.minecraft.entity.*
import net.minecraft.entity.ai.TargetPredicate
import net.minecraft.entity.ai.control.AquaticMoveControl
import net.minecraft.entity.ai.control.YawAdjustingLookControl
import net.minecraft.entity.ai.goal.*
import net.minecraft.entity.ai.pathing.EntityNavigation
import net.minecraft.entity.ai.pathing.SwimNavigation
import net.minecraft.entity.damage.DamageSource
import net.minecraft.entity.data.DataTracker
import net.minecraft.entity.data.TrackedData
import net.minecraft.entity.data.TrackedDataHandlerRegistry
import net.minecraft.entity.mob.Angerable
import net.minecraft.entity.mob.WaterCreatureEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.nbt.NbtCompound
import net.minecraft.sound.SoundEvent
import net.minecraft.sound.SoundEvents
import net.minecraft.util.TimeHelper
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.intprovider.UniformIntProvider
import net.minecraft.util.math.random.Random
import net.minecraft.world.LocalDifficulty
import net.minecraft.world.ServerWorldAccess
import net.minecraft.world.World
import net.minecraft.world.WorldAccess
import software.bernie.geckolib.animatable.GeoEntity
import software.bernie.geckolib.core.animatable.GeoAnimatable
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache
import software.bernie.geckolib.core.animation.*
import software.bernie.geckolib.core.animation.AnimationState
import software.bernie.geckolib.core.`object`.PlayState
import software.bernie.geckolib.util.GeckoLibUtil
import java.util.*

@Suppress("LeakingThis")
open class HybridAquaticShark(entityType: EntityType<out HybridAquaticFish>, world: World)
    : WaterCreatureEntity(entityType, world), Angerable, GeoEntity {
    private val factory = GeckoLibUtil.createInstanceCache(this)
    private var angerTime = 0
    private var angryAt: UUID? = null


    override fun initGoals() {
        super.initGoals()
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
            }
        }
    }

    override fun writeCustomDataToNbt(nbt: NbtCompound) {
        super.writeCustomDataToNbt(nbt)
        this.writeAngerToNbt(nbt)
        nbt.putInt(MOISTNESS_KEY, moistness)
    }

    override fun readCustomDataFromNbt(nbt: NbtCompound) {
        super.readCustomDataFromNbt(nbt)
        this.readAngerFromNbt(this.world, nbt)
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

    //#region SFX
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
    //#endregion
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
        moveControl = AquaticMoveControl(this, 85, 10, 0.02f, 0.1f, true)
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

    internal class SwimToRandomPlaceGoal(private val fish: HybridAquaticShark, d: Double, i: Int) :
            SwimAroundGoal(fish, 1.0, 40) {
        override fun canStart(): Boolean {
            return fish.hasSelfControl() && super.canStart()
        }
    }


    override fun getAngerTime(): Int {
        return angerTime
    }

    override fun setAngerTime(angerTime: Int) {
        this.angerTime = angerTime
    }

    override fun getAngryAt(): UUID? {
        return angryAt
    }

    override fun setAngryAt(angryAt: UUID?) {
        this.angryAt = angryAt
    }

    override fun chooseRandomAngerTime() {
        setAngerTime(ANGER_TIME_RANGE.get(random))
    }

    companion object {
        val MOISTNESS: TrackedData<Int> = DataTracker.registerData(HybridAquaticFish::class.java, TrackedDataHandlerRegistry.INTEGER)
        val CLOSE_PLAYER_PREDICATE: TargetPredicate = TargetPredicate.createNonAttackable().setBaseMaxDistance(10.0).ignoreVisibility()
        val ANGER_TIME_RANGE: UniformIntProvider = TimeHelper.betweenSeconds(19, 40)

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

        const val MOISTNESS_KEY = "Moistness"
    }

}
