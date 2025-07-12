package dev.hybridlabs.aquatic.entity.critter

import net.minecraft.entity.EntityData
import net.minecraft.entity.EntityType
import net.minecraft.entity.SpawnReason
import net.minecraft.entity.ai.control.MoveControl
import net.minecraft.entity.ai.goal.EscapeDangerGoal
import net.minecraft.entity.ai.goal.MoveIntoWaterGoal
import net.minecraft.entity.ai.goal.WanderAroundGoal
import net.minecraft.entity.ai.pathing.MobNavigation
import net.minecraft.entity.ai.pathing.PathNodeType
import net.minecraft.entity.damage.DamageSource
import net.minecraft.entity.data.DataTracker
import net.minecraft.entity.data.TrackedData
import net.minecraft.entity.data.TrackedDataHandlerRegistry
import net.minecraft.entity.mob.WaterCreatureEntity
import net.minecraft.nbt.NbtCompound
import net.minecraft.sound.SoundEvent
import net.minecraft.sound.SoundEvents
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.random.Random
import net.minecraft.world.LocalDifficulty
import net.minecraft.world.ServerWorldAccess
import net.minecraft.world.World
import software.bernie.geckolib.animatable.GeoEntity
import software.bernie.geckolib.constant.DefaultAnimations
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache
import software.bernie.geckolib.core.animation.AnimatableManager
import software.bernie.geckolib.core.animation.AnimationController
import software.bernie.geckolib.core.animation.AnimationState
import software.bernie.geckolib.util.GeckoLibUtil


@Suppress("LeakingThis", "DEPRECATION", "UNUSED_PARAMETER")
open class HybridAquaticCritterEntity(
    type: EntityType<out HybridAquaticCritterEntity>,
    world: World,
) : WaterCreatureEntity(type, world), GeoEntity {
    private val factory = GeckoLibUtil.createInstanceCache(this)
    private var fromFishingNet = false

    init {
        setPathfindingPenalty(PathNodeType.WATER, 0.0f)
        setPathfindingPenalty(PathNodeType.WALKABLE, 10.0f)
        moveControl = MoveControl(this)
        navigation = MobNavigation(this, world)
    }

    override fun hasNoDrag(): Boolean {
        return this.isClimbing
    }

    override fun tick() {
        super.tick()

        if (!isWet) {
            this.speed = 0.01F
        }
    }

    override fun getStepHeight(): Float {
        return 1.0F
    }

    override fun initDataTracker() {
        super.initDataTracker()
        dataTracker.startTracking(CRITTER_SIZE, 0)
        dataTracker.startTracking(CRITTER_FLAGS, 0.toByte())
    }

    override fun initGoals() {
        super.initGoals()
        goalSelector.add(2, MoveIntoWaterGoal(this))
        goalSelector.add(3, EscapeDangerGoal(this, 0.35))
        goalSelector.add(5, WanderAroundGoal(this, 0.35, 10))
    }

    override fun initialize(
        world: ServerWorldAccess,
        difficulty: LocalDifficulty,
        spawnReason: SpawnReason,
        entityData: EntityData?,
        entityNbt: NbtCompound?
    ): EntityData? {
        this.air = this.maxAir
        this.size = this.random.nextBetween(getMinSize(), getMaxSize())
        return super.initialize(world, difficulty, spawnReason, entityData, entityNbt)
    }

    override fun shouldSwimInFluids(): Boolean {
        return !isOnGround || !isClimbing
    }

    override fun isPushedByFluids(): Boolean {
        return false
    }

    override fun writeCustomDataToNbt(nbt: NbtCompound) {
        super.writeCustomDataToNbt(nbt)
        nbt.putInt(CRITTER_SIZE_KEY, size)
        nbt.putBoolean("FromFishingNet", fromFishingNet)
    }

    override fun readCustomDataFromNbt(nbt: NbtCompound) {
        super.readCustomDataFromNbt(nbt)
        size = nbt.getInt(CRITTER_SIZE_KEY)
        fromFishingNet = nbt.getBoolean("FromFishingNet")
    }

    override fun tickWaterBreathingAir(air: Int) {}

    override fun registerControllers(controllerRegistrar: AnimatableManager.ControllerRegistrar) {
        controllerRegistrar.add(
            AnimationController(this, "Swim/Idle", 5,
                AnimationController.AnimationStateHandler { state: AnimationState<HybridAquaticCritterEntity> ->
                    if (state.isMoving) {
                        return@AnimationStateHandler state.setAndContinue(DefaultAnimations.WALK)
                    } else {
                        return@AnimationStateHandler state.setAndContinue(DefaultAnimations.IDLE)
                    }
                })
        )
    }

    protected open fun getMinSize(): Int {
        return 0
    }

    protected open fun getMaxSize(): Int {
        return 0
    }

    override fun canImmediatelyDespawn(distanceSquared: Double): Boolean {
        return !fromFishingNet && !hasCustomName()
    }

    override fun getLimitPerChunk(): Int {
        return 4
    }

    override fun getHurtSound(source: DamageSource): SoundEvent {
        return SoundEvents.ENTITY_SLIME_HURT
    }

    override fun getDeathSound(): SoundEvent {
        return SoundEvents.ENTITY_SLIME_DEATH_SMALL
    }

    override fun getAmbientSound(): SoundEvent {
        return SoundEvents.ENTITY_TROPICAL_FISH_AMBIENT
    }

    override fun getSwimSound(): SoundEvent {
        return SoundEvents.ENTITY_SLIME_JUMP_SMALL
    }

    override fun getAnimatableInstanceCache(): AnimatableInstanceCache {
        return factory
    }

    override fun canBreatheInWater(): Boolean {
        return true
    }

    var size: Int
        get() = dataTracker.get(CRITTER_SIZE)
        set(size) {
            dataTracker.set(CRITTER_SIZE, size)
        }

    companion object {
        val CRITTER_SIZE: TrackedData<Int> =
            DataTracker.registerData(HybridAquaticCritterEntity::class.java, TrackedDataHandlerRegistry.INTEGER)
        val CRITTER_FLAGS: TrackedData<Byte> =
            DataTracker.registerData(HybridAquaticCritterEntity::class.java, TrackedDataHandlerRegistry.BYTE)

        fun canSpawn(
            type: EntityType<out WaterCreatureEntity>,
            world: ServerWorldAccess,
            reason: SpawnReason,
            pos: BlockPos,
            random: Random
        ): Boolean {
            val topY = world.seaLevel
            val bottomY = world.seaLevel - 128

            return pos.y in bottomY..topY &&
                    world.getBlockState(pos.down()).isSolid &&
                    world.isWater(pos)
        }

        fun getScaleAdjustment(critter: HybridAquaticCritterEntity, adjustment: Float): Float {
            return 1.0f + (critter.size * adjustment)
        }

        const val CRITTER_SIZE_KEY = "CritterSize"
    }
}
