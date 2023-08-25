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
import software.bernie.geckolib.core.animation.*
import software.bernie.geckolib.core.animation.AnimationState
import software.bernie.geckolib.core.`object`.PlayState
import software.bernie.geckolib.util.GeckoLibUtil

@Suppress("LeakingThis")
open class HybridAquaticJellyfishEntity(type: EntityType<out HybridAquaticJellyfishEntity>, world: World, private val variantCount: Int = 1) : WaterCreatureEntity(type, world), GeoEntity {
    private val factory = GeckoLibUtil.createInstanceCache(this)

    override fun initGoals() {
        super.initGoals()
        goalSelector.add(4, LookAroundGoal(this))
    }

    override fun initDataTracker() {
        super.initDataTracker()
        dataTracker.startTracking(MOISTNESS, 600)
        dataTracker.startTracking(VARIANT, 0)
    }

    override fun initialize(
        world: ServerWorldAccess,
        difficulty: LocalDifficulty,
        spawnReason: SpawnReason,
        entityData: EntityData?,
        entityNbt: NbtCompound?
    ): EntityData? {
        this.air = this.maxAir
        this.variant = this.random.nextInt(variantCount)
        this.pitch = 0.0f
        this.bobTicks = this.random.nextDouble() * TAU
        return super.initialize(world, difficulty, spawnReason, entityData, entityNbt)
    }

    private var bobTicks = this.random.nextDouble() * TAU
    override fun tick() {
        super.tick()
        setNoGravity(submergedInWater)
        bobTicks += 0.09817477042468103
        if(bobTicks > TAU) bobTicks -= TAU
        val bobOffset = MathHelper.sin(bobTicks.toFloat()) * 0.1f
        this.setPos(this.x, this.y + bobOffset.toDouble(), this.z)
    }

    override fun tickWaterBreathingAir(air: Int) {}

    override fun writeCustomDataToNbt(nbt: NbtCompound) {
        super.writeCustomDataToNbt(nbt)
        nbt.putInt(MOISTNESS_KEY, moistness)
        nbt.putInt(VARIANT_KEY, variant)
    }

    override fun readCustomDataFromNbt(nbt: NbtCompound) {
        super.readCustomDataFromNbt(nbt)
        moistness = nbt.getInt(MOISTNESS_KEY)
        variant = nbt.getInt(VARIANT_KEY)
    }

    open fun <E : GeoAnimatable> predicate(event: AnimationState<E>): PlayState {
        if (isSubmergedInWater) {
            event.controller.setAnimation(RawAnimation.begin().then("bob", Animation.LoopType.LOOP))
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
        val CLOSE_PLAYER_PREDICATE: TargetPredicate = TargetPredicate.createNonAttackable().setBaseMaxDistance(10.0).ignoreVisibility()

        fun canSpawnPredicate(
            type: EntityType<out WaterCreatureEntity?>?,
            world: WorldAccess,
            reason: SpawnReason?,
            pos: BlockPos,
            random: Random?
        ): Boolean {
            return pos.y <= world.seaLevel && pos.y >= world.seaLevel - 9 && world.getBlockState(pos).isOf(Blocks.WATER) && canSpawn(type, world, reason, pos, random)
        }
        const val MOISTNESS_KEY = "Moistness"
        const val VARIANT_KEY = "Variant"
        const val TAU = Math.PI * 2
    }
}
