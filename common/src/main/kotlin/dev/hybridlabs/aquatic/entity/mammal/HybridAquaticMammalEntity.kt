package dev.hybridlabs.aquatic.entity.mammal

import dev.hybridlabs.aquatic.entity.ai.control.FloatControl
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.nbt.CompoundTag
import net.minecraft.network.syncher.EntityDataAccessor
import net.minecraft.network.syncher.EntityDataSerializers
import net.minecraft.network.syncher.SynchedEntityData
import net.minecraft.server.level.ServerLevel
import net.minecraft.sounds.SoundEvent
import net.minecraft.sounds.SoundEvents
import net.minecraft.tags.FluidTags
import net.minecraft.tags.TagKey
import net.minecraft.util.RandomSource
import net.minecraft.world.DifficultyInstance
import net.minecraft.world.entity.*
import net.minecraft.world.entity.ai.control.LookControl
import net.minecraft.world.entity.ai.goal.*
import net.minecraft.world.entity.ai.navigation.AmphibiousPathNavigation
import net.minecraft.world.entity.animal.Animal
import net.minecraft.world.entity.player.Player
import net.minecraft.world.level.Level
import net.minecraft.world.level.LevelAccessor
import net.minecraft.world.level.ServerLevelAccessor
import net.minecraft.world.level.pathfinder.BlockPathTypes
import software.bernie.geckolib.animatable.GeoEntity
import software.bernie.geckolib.constant.DefaultAnimations
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache
import software.bernie.geckolib.core.animation.AnimatableManager
import software.bernie.geckolib.core.animation.AnimationController
import software.bernie.geckolib.core.animation.AnimationState
import software.bernie.geckolib.core.animation.RawAnimation
import software.bernie.geckolib.util.GeckoLibUtil

@Suppress("LeakingThis", "UNUSED_PARAMETER", "unused")
open class HybridAquaticMammalEntity(
    type: EntityType<out HybridAquaticMammalEntity>,
    world: Level,
    open val prey: List<TagKey<EntityType<*>>>,
    open val predator: List<TagKey<EntityType<*>>>,
) : Animal(type, world), GeoEntity {

    private val factory = GeckoLibUtil.createInstanceCache(this)

    init {
        moveControl = FloatControl(this)
        navigation = AmphibiousPathNavigation(this, world)
    }

    fun isBelowWaterline(): Boolean {
        return this.isUnderWater || this.getFluidHeight(FluidTags.WATER) > this.getWaterline()
    }

    open fun getWaterline(): Float {
        return 0.5f
    }

    override fun getMaxSpawnClusterSize(): Int {
        return 2
    }

    override fun finalizeSpawn(
        world: ServerLevelAccessor,
        difficulty: DifficultyInstance,
        spawnReason: MobSpawnType,
        entityData: SpawnGroupData?,
        entityNbt: CompoundTag?
    ): SpawnGroupData? {
        this.airSupply = this.maxAirSupply
        this.yRot = 0.0f
        this.size = this.random.nextIntBetweenInclusive(getMinSize(), getMaxSize())
        return super.finalizeSpawn(world, difficulty, spawnReason, entityData, entityNbt)
    }

    override fun getBreedOffspring(p0: ServerLevel, p1: AgeableMob): AgeableMob? {
        return null
    }

    protected open fun getMinSize(): Int {
        return 0
    }

    protected open fun getMaxSize(): Int {
        return 0
    }

    //#region Animations
    override fun registerControllers(controllerRegistrar: AnimatableManager.ControllerRegistrar) {
        controllerRegistrar.add(
            AnimationController(
                this, "Walk/Swim/Idle", 4
            ) { state: AnimationState<HybridAquaticMammalEntity> ->
                when {
                    state.isMoving && onGround() -> state.setAndContinue(DefaultAnimations.WALK)
                    state.isMoving && isInWater -> state.setAndContinue(DefaultAnimations.SWIM)
                    !state.isMoving && isInWater -> state.setAndContinue(WATER_IDLE)
                    else -> state.setAndContinue(DefaultAnimations.IDLE)
                }
            }
        )
    }

    override fun getAnimatableInstanceCache(): AnimatableInstanceCache {
        return factory
    }

    var size: Int
        get() = entityData.get(MAMMAL_SIZE)
        set(size) {
            entityData.set(MAMMAL_SIZE, size)
        }

    override fun defineSynchedData() {
        super.defineSynchedData()
        entityData.define(MAMMAL_SIZE, 0)
    }

    init {
        setPathfindingMalus(BlockPathTypes.WATER, 0.0f)
        setPathfindingMalus(BlockPathTypes.WATER_BORDER, 0.0f)
        setPathfindingMalus(BlockPathTypes.WALKABLE, 0.0f)
        moveControl = FloatControl(this)
        lookControl = LookControl(this)
        navigation = AmphibiousPathNavigation(this, world)
    }

    override fun registerGoals() {
        goalSelector.addGoal(3, RandomStrollGoal(this, 0.5, 2))
        goalSelector.addGoal(4, RandomLookAroundGoal(this))
        goalSelector.addGoal(4, LookAtPlayerGoal(this, Player::class.java, 6.0f))
        goalSelector.addGoal(5, TryFindWaterGoal(this))
        goalSelector.addGoal(6, MeleeAttackGoal(this, 1.2000000476837158, true))
    }

    override fun getStandingEyeHeight(pose: Pose, dimensions: EntityDimensions): Float {
        return 0.3f
    }

    override fun aiStep() {
        super.aiStep()
        val vec3d = this.deltaMovement
        if (!this.onGround() && this.isSwimming && vec3d.y < 0.0) {
            this.deltaMovement = vec3d.multiply(1.0, 0.6, 1.0)
        }
    }

    companion object {
        val MAMMAL_SIZE: EntityDataAccessor<Int> =
            SynchedEntityData.defineId(HybridAquaticMammalEntity::class.java, EntityDataSerializers.INT)

        val WATER_IDLE: RawAnimation = RawAnimation.begin().thenPlay("misc.water_idle")

        fun getScaleAdjustment(mammal: HybridAquaticMammalEntity, adjustment: Float): Float {
            return 1.0f + (mammal.size * adjustment)
        }

        fun canSpawn(
            type: EntityType<out HybridAquaticMammalEntity>,
            level: LevelAccessor,
            spawnReason: MobSpawnType,
            pos: BlockPos,
            random: RandomSource
        ): Boolean {
            val mutable = pos.mutable()
            do {
                mutable.move(Direction.UP)
            } while (level.getFluidState(mutable).`is`(FluidTags.WATER))
            return level.getBlockState(mutable).isAir
        }
    }
}