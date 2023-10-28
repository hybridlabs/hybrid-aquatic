package dev.hybridlabs.aquatic.entity.critter

import dev.hybridlabs.aquatic.entity.fish.HybridAquaticFishEntity
import net.minecraft.block.Blocks
import net.minecraft.entity.*
import net.minecraft.entity.ai.control.MoveControl
import net.minecraft.entity.ai.goal.*
import net.minecraft.entity.ai.pathing.EntityNavigation
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
import net.minecraft.world.WorldAccess
import software.bernie.geckolib.animatable.GeoEntity
import software.bernie.geckolib.core.animatable.GeoAnimatable
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache
import software.bernie.geckolib.core.animation.*
import software.bernie.geckolib.core.animation.AnimationState
import software.bernie.geckolib.core.`object`.PlayState
import software.bernie.geckolib.util.GeckoLibUtil

@Suppress("LeakingThis")
open class HybridAquaticCritterEntity(type: EntityType<out HybridAquaticCritterEntity>, world: World, private val variantCount: Int = 1) : WaterCreatureEntity(type, world), GeoEntity {

    private val factory = GeckoLibUtil.createInstanceCache(this)
    private val buoyant = false
    private var landNavigation: EntityNavigation = createNavigation(world)

    init {
        stepHeight = 1.0F
        moveControl = MoveControl(this)
        setPathfindingPenalty(PathNodeType.WATER, 0.0F)
        navigation = this.landNavigation
    }

    override fun initDataTracker() {
        super.initDataTracker()
        dataTracker.startTracking(VARIANT, 0)
        dataTracker.startTracking(CRITTER_SIZE, 0)
    }

    override fun initGoals() {
        super.initGoals()
        goalSelector.add(2, MoveIntoWaterGoal(this))
        goalSelector.add(3, EscapeDangerGoal(this, 0.35))
        goalSelector.add(5, WanderAroundGoal(this, 0.35, 10))
        //goalSelector.add(5, LookAtEntityGoal(this, PlayerEntity::class.java, 12.0f))
        goalSelector.add(5, LookAroundGoal(this))
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
        this.variant = this.random.nextInt(variantCount)
        this.size = this.random.nextBetween(getMinSize(),getMaxSize())
        this.pitch = 0.0f
        return super.initialize(world, difficulty, spawnReason, entityData, entityNbt)
    }

    override fun updateSwimming() {
        if (!world.isClient) {
            this.isSwimming = canMoveVoluntarily() && this.isTouchingWater
        }
    }

    override fun tick() {
        super.tick()
        if (!buoyant && this.isTouchingWater && !isOnGround) {
            this.velocity = this.velocity.add(0.0, -0.05, 0.0)
        }
    }

    override fun hasNoDrag(): Boolean {
        return false
    }

    override fun shouldSwimInFluids(): Boolean {
        return false
    }

    override fun getBaseMovementSpeedMultiplier(): Float {
        return 1.0F
    }

    override fun writeCustomDataToNbt(nbt: NbtCompound) {
        super.writeCustomDataToNbt(nbt)
        nbt.putInt(HybridAquaticFishEntity.VARIANT_KEY, variant)
        nbt.putInt(CRITTER_SIZE_KEY, size)
    }

    override fun readCustomDataFromNbt(nbt: NbtCompound) {
        super.readCustomDataFromNbt(nbt)
        variant = nbt.getInt(HybridAquaticFishEntity.VARIANT_KEY)
        size = nbt.getInt(CRITTER_SIZE_KEY)
    }

    override fun tickWaterBreathingAir(air: Int) {}

    open fun <E : GeoAnimatable> predicate(event: AnimationState<E>): PlayState {
        if (event.isMoving) {
            event.controller.setAnimation(RawAnimation.begin().then("walk", Animation.LoopType.LOOP))
            return PlayState.CONTINUE
        }

        if (isOnGround) {
            event.controller.setAnimation(RawAnimation.begin().then("sit", Animation.LoopType.LOOP))
            return PlayState.CONTINUE
        }
        return PlayState.STOP
    }

    protected open fun getMinSize() : Int {
        return 0
    }

    protected open fun getMaxSize() : Int {
        return 0
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

    override fun getHurtSound(source: DamageSource): SoundEvent {
        return SoundEvents.ENTITY_TURTLE_EGG_CRACK
    }

    override fun getDeathSound(): SoundEvent {
        return SoundEvents.ENTITY_TURTLE_EGG_BREAK
    }

    override fun getAmbientSound(): SoundEvent {
        return SoundEvents.ENTITY_COD_AMBIENT
    }

    override fun getSplashSound(): SoundEvent {
        return SoundEvents.ENTITY_DOLPHIN_SPLASH
    }

    override fun getSwimSound(): SoundEvent {
        return SoundEvents.ENTITY_SPIDER_STEP
    }

    override fun createNavigation(world: World): EntityNavigation {
        return MobNavigation(this, world)
    }

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

    override fun canBreatheInWater(): Boolean {
        return true
    }

    var variant: Int
        get() = dataTracker.get(VARIANT)
        set(int) {
            dataTracker.set(VARIANT, int)
        }

    var size: Int
        get() = dataTracker.get(CRITTER_SIZE)
        set(size) {
            dataTracker.set(CRITTER_SIZE, size)
        }

    companion object {
        val VARIANT: TrackedData<Int> = DataTracker.registerData(HybridAquaticCritterEntity::class.java, TrackedDataHandlerRegistry.INTEGER)
        val CRITTER_SIZE: TrackedData<Int> = DataTracker.registerData(HybridAquaticCritterEntity::class.java, TrackedDataHandlerRegistry.INTEGER)

        fun canSpawn(
            type: EntityType<out WaterCreatureEntity?>?,
            world: WorldAccess,
            reason: SpawnReason?,
            pos: BlockPos,
            random: Random?
        ): Boolean {
            val topY = world.seaLevel - 6
            val bottomY = world.seaLevel - 40

            return pos.y in bottomY..topY &&
                    world.getBlockState(pos.down()).isSolidBlock(world, pos.down()) &&
                    world.getBlockState(pos.up()).isOf(Blocks.WATER)
        }
        fun getScaleAdjustment(critter : HybridAquaticCritterEntity, adjustment : Float): Float {
            return 1.0f + (critter.size * adjustment)
        }
        const val VARIANT_KEY = "Variant"
        const val CRITTER_SIZE_KEY = "CritterSize"
    }
}
