package dev.hybridlabs.aquatic.entity.jellyfish

import net.minecraft.block.Blocks
import net.minecraft.entity.*
import net.minecraft.entity.ai.TargetPredicate
import net.minecraft.entity.ai.goal.LookAroundGoal
import net.minecraft.entity.ai.pathing.EntityNavigation
import net.minecraft.entity.ai.pathing.SwimNavigation
import net.minecraft.entity.damage.DamageSource
import net.minecraft.entity.data.DataTracker
import net.minecraft.entity.data.TrackedData
import net.minecraft.entity.data.TrackedDataHandlerRegistry
import net.minecraft.entity.mob.WaterCreatureEntity
import net.minecraft.nbt.NbtCompound
import net.minecraft.registry.tag.FluidTags
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
import kotlin.math.abs

@Suppress("LeakingThis")
open class HybridAquaticJellyfishEntity(type: EntityType<out HybridAquaticJellyfishEntity>, world: World, private val variantCount: Int = 1) : WaterCreatureEntity(type, world), GeoEntity {
    private val factory = GeckoLibUtil.createInstanceCache(this)

    override fun initGoals() {
        super.initGoals()
        goalSelector.add(4, LookAroundGoal(this))
    }

    override fun initDataTracker() {
        super.initDataTracker()
        dataTracker.startTracking(MOISTNESS, getMaxMoistness())
        dataTracker.startTracking(VARIANT, 0)
        dataTracker.startTracking(SPAWNED_ON_Y, 0)
        dataTracker.startTracking(JELLYFISH_SIZE, 0)
    }

    override fun initialize(
        world: ServerWorldAccess,
        difficulty: LocalDifficulty,
        spawnReason: SpawnReason,
        entityData: EntityData?,
        entityNbt: NbtCompound?
    ): EntityData? {
        this.air = getMaxMoistness()
        this.variant = this.random.nextInt(variantCount)
        this.size = this.random.nextBetween(getMinSize(),getMaxSize())
        this.spawnedY = this.y.toInt()
        this.pitch = 0.0f
        return super.initialize(world, difficulty, spawnReason, entityData, entityNbt)
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
                damage(this.damageSources.dryOut(), 1.0f)
            }
        }

        if (this.submergedInWater) {
            if (this.y <= spawnedY) {
                if (!world.getFluidState(this.blockPos.up(2)).isIn(FluidTags.WATER) &&
                    !world.getFluidState(this.blockPos.up(4)).isIn(FluidTags.WATER) &&
                    !world.getFluidState(this.blockPos.up(6)).isIn(FluidTags.WATER)) {
                    spawnedY = this.y.toInt() - 6
                    return
                }

                val randomOffset = this.random.nextDouble() * 0.25
                this.setVelocity(0.0, 0.6 + randomOffset, 0.0)
            }

            if (abs(this.velocity.length()) <= 0.01 && !world.getFluidState(this.blockPos.down()).isIn(FluidTags.WATER)) spawnedY = this.y.toInt()
        }
    }

    override fun tickWaterBreathingAir(air: Int) {}

    fun getMaxMoistness(): Int {
        return 600
    }

    override fun writeCustomDataToNbt(nbt: NbtCompound) {
        super.writeCustomDataToNbt(nbt)
        nbt.putInt(MOISTNESS_KEY, moistness)
        nbt.putInt(VARIANT_KEY, variant)
        nbt.putInt(SPAWNED_ON_Y_KEY, spawnedY)
        nbt.putInt(JELLYFISH_SIZE_KEY, size)
    }

    override fun readCustomDataFromNbt(nbt: NbtCompound) {
        super.readCustomDataFromNbt(nbt)
        moistness = nbt.getInt(MOISTNESS_KEY)
        variant = nbt.getInt(VARIANT_KEY)
        size = nbt.getInt(JELLYFISH_SIZE_KEY)
        spawnedY = if (!nbt.contains(SPAWNED_ON_Y_KEY)) (this.y - 1).toInt()
        else nbt.getInt(SPAWNED_ON_Y_KEY)
    }

    open fun <E : GeoAnimatable> predicate(event: AnimationState<E>): PlayState {
        if (isSubmergedInWater) {
            event.controller.setAnimation(RawAnimation.begin().then("bob", Animation.LoopType.LOOP))
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
        return SoundEvents.ENTITY_SLIME_HURT
    }
    override fun getDeathSound(): SoundEvent {
        return SoundEvents.ENTITY_SLIME_DEATH
    }
    override fun getAmbientSound(): SoundEvent {
        return SoundEvents.ENTITY_SQUID_AMBIENT
    }
    override fun getSplashSound(): SoundEvent {
        return SoundEvents.ENTITY_DOLPHIN_SPLASH
    }
    override fun getSwimSound(): SoundEvent {
        return SoundEvents.ENTITY_SQUID_AMBIENT
    }
    override fun createNavigation(world: World): EntityNavigation {
        return SwimNavigation(this, world)
    }

    private var moistness: Int
        get() = dataTracker.get(MOISTNESS)
        set(moistness) {
            dataTracker.set(MOISTNESS, moistness)
        }

    var variant: Int
        get() = dataTracker.get(VARIANT)
        set(int) {
            dataTracker.set(VARIANT, int)
        }

    var size: Int
        get() = dataTracker.get(JELLYFISH_SIZE)
        set(size) {
            dataTracker.set(JELLYFISH_SIZE, size)
        }

    private var spawnedY: Int
        get() = dataTracker.get(SPAWNED_ON_Y)
        set(int) {
            dataTracker.set(SPAWNED_ON_Y, int)
        }

    override fun getMaxAir(): Int {
        return 1200
    }

    public override fun getNextAirOnLand(air: Int): Int {
        return this.maxAir
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

    companion object {
        val MOISTNESS: TrackedData<Int> = DataTracker.registerData(HybridAquaticJellyfishEntity::class.java, TrackedDataHandlerRegistry.INTEGER)
        val VARIANT: TrackedData<Int> = DataTracker.registerData(HybridAquaticJellyfishEntity::class.java, TrackedDataHandlerRegistry.INTEGER)
        val SPAWNED_ON_Y: TrackedData<Int> = DataTracker.registerData(HybridAquaticJellyfishEntity::class.java, TrackedDataHandlerRegistry.INTEGER)
        val JELLYFISH_SIZE: TrackedData<Int> = DataTracker.registerData(HybridAquaticJellyfishEntity::class.java, TrackedDataHandlerRegistry.INTEGER)

        val CLOSE_PLAYER_PREDICATE: TargetPredicate = TargetPredicate.createNonAttackable().setBaseMaxDistance(10.0).ignoreVisibility()

        fun canSpawn(
            type: EntityType<out WaterCreatureEntity>,
            world: WorldAccess,
            reason: SpawnReason?,
            pos: BlockPos,
            random: Random?
        ): Boolean {
            val topY = world.seaLevel - 6
            val bottomY = world.seaLevel - 24

            return pos.y in bottomY..topY &&
                    world.getFluidState(pos.down()).isIn(FluidTags.WATER) &&
                    world.getBlockState(pos.up()).isOf(Blocks.WATER)
        }
        fun getScaleAdjustment(jellyfish : HybridAquaticJellyfishEntity, adjustment : Float): Float {
            return 1.0f + (jellyfish.size * adjustment)
        }
        const val MOISTNESS_KEY = "Moistness"
        const val SPAWNED_ON_Y_KEY = "Spawned_on_Y"
        const val VARIANT_KEY = "Variant"
        const val JELLYFISH_SIZE_KEY = "JellyfishSize"

    }
}
