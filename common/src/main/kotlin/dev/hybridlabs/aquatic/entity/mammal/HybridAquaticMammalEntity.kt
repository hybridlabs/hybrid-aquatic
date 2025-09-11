package dev.hybridlabs.aquatic.entity.mammal

import net.minecraft.core.BlockPos
import net.minecraft.nbt.CompoundTag
import net.minecraft.network.syncher.EntityDataAccessor
import net.minecraft.network.syncher.EntityDataSerializers
import net.minecraft.network.syncher.SynchedEntityData
import net.minecraft.sounds.SoundEvent
import net.minecraft.sounds.SoundEvents
import net.minecraft.tags.TagKey
import net.minecraft.util.RandomSource
import net.minecraft.world.DifficultyInstance
import net.minecraft.world.entity.*
import net.minecraft.world.entity.ai.control.LookControl
import net.minecraft.world.entity.ai.control.MoveControl
import net.minecraft.world.entity.ai.goal.*
import net.minecraft.world.entity.ai.navigation.AmphibiousPathNavigation
import net.minecraft.world.entity.animal.WaterAnimal
import net.minecraft.world.entity.player.Player
import net.minecraft.world.level.Level
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

@Suppress("LeakingThis", "DEPRECATION", "UNUSED_PARAMETER", "unused")
open class HybridAquaticMammalEntity(
    type: EntityType<out HybridAquaticMammalEntity>,
    world: Level,
    open val prey: List<TagKey<EntityType<*>>>,
    open val predator: List<TagKey<EntityType<*>>>,
) : WaterAnimal(type, world), GeoEntity {

    private val factory = GeckoLibUtil.createInstanceCache(this)

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

    override fun canBreatheUnderwater(): Boolean {
        return false
    }

    override fun handleAirSupply(air: Int) {
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
        moveControl = MoveControl(this)
        lookControl = LookControl(this)
        navigation = AmphibiousPathNavigation(this, world)
    }

    override fun registerGoals() {
        goalSelector.addGoal(3, RandomStrollGoal(this, 0.5, 2))
        goalSelector.addGoal(3, RandomSwimmingGoal(this, 0.5, 2))
        goalSelector.addGoal(4, RandomLookAroundGoal(this))
        goalSelector.addGoal(4, TryFindWaterGoal(this))
        goalSelector.addGoal(4, LookAtPlayerGoal(this, Player::class.java, 6.0f))
        goalSelector.addGoal(6, MeleeAttackGoal(this, 1.2000000476837158, true))
    }

    override fun getStandingEyeHeight(pose: Pose, dimensions: EntityDimensions): Float {
        return 0.3f
    }

    override fun getMaxHeadYRot(): Int {
        return 1
    }

    override fun getMaxHeadXRot(): Int {
        return 1
    }

    override fun getAmbientSound(): SoundEvent? {
        return SoundEvents.FOX_AMBIENT
    }

    override fun getSwimSplashSound(): SoundEvent {
        return SoundEvents.DOLPHIN_SPLASH
    }

    override fun getSwimSound(): SoundEvent {
        return SoundEvents.DOLPHIN_SWIM
    }

    companion object {
        val MAMMAL_SIZE: EntityDataAccessor<Int> =
            SynchedEntityData.defineId(HybridAquaticMammalEntity::class.java, EntityDataSerializers.INT)

        val WATER_IDLE: RawAnimation = RawAnimation.begin().thenPlay("misc.water_idle")

        fun getScaleAdjustment(fish: HybridAquaticMammalEntity, adjustment: Float): Float {
            return 1.0f + (fish.size * adjustment)
        }

        fun canSpawn(
            type: EntityType<out WaterAnimal>,
            world: ServerLevelAccessor,
            reason: MobSpawnType,
            pos: BlockPos,
            random: RandomSource
        ): Boolean {
            val topY = world.seaLevel + 4
            val bottomY = world.seaLevel

            return pos.y in bottomY..topY &&
                    world.isWaterAt(pos)
        }
    }
}