package dev.hybridlabs.aquatic.entity.mammal

import dev.hybridlabs.aquatic.block.HybridAquaticBlocks
import dev.hybridlabs.aquatic.entity.ai.goal.boids.StayInWaterGoal
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.nbt.CompoundTag
import net.minecraft.network.syncher.EntityDataAccessor
import net.minecraft.network.syncher.EntityDataSerializers
import net.minecraft.network.syncher.SynchedEntityData
import net.minecraft.server.level.ServerLevel
import net.minecraft.tags.FluidTags
import net.minecraft.util.RandomSource
import net.minecraft.world.DifficultyInstance
import net.minecraft.world.entity.*
import net.minecraft.world.entity.ai.control.SmoothSwimmingLookControl
import net.minecraft.world.entity.ai.control.SmoothSwimmingMoveControl
import net.minecraft.world.entity.ai.goal.*
import net.minecraft.world.entity.ai.navigation.WaterBoundPathNavigation
import net.minecraft.world.entity.animal.Animal
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.Items
import net.minecraft.world.item.crafting.Ingredient
import net.minecraft.world.level.Level
import net.minecraft.world.level.LevelAccessor
import net.minecraft.world.level.ServerLevelAccessor
import net.minecraft.world.level.pathfinder.BlockPathTypes
import net.minecraft.world.phys.Vec3
import software.bernie.geckolib.animatable.GeoEntity
import software.bernie.geckolib.constant.DefaultAnimations
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache
import software.bernie.geckolib.core.animation.AnimatableManager
import software.bernie.geckolib.core.animation.AnimationController
import software.bernie.geckolib.core.animation.AnimationState
import software.bernie.geckolib.core.animation.RawAnimation
import software.bernie.geckolib.util.GeckoLibUtil

@Suppress("LeakingThis", "UNUSED_PARAMETER", "unused", "DEPRECATION")
open class HybridAquaticSirenianEntity(type: EntityType<out HybridAquaticSirenianEntity>, world: Level) :
    Animal(type, world), GeoEntity {
    private val factory = GeckoLibUtil.createInstanceCache(this)
    var prevRoll: Float = 0f
    var currentRoll: Float = 0.0f

    init {
        setPathfindingMalus(BlockPathTypes.WATER, 0.0f)
        setPathfindingMalus(BlockPathTypes.DANGER_FIRE, 16.0f)
        setPathfindingMalus(BlockPathTypes.DAMAGE_FIRE, -1.0f)
        moveControl = SmoothSwimmingMoveControl(this, 45, 3, 0.02F, 0.1F, false)
        lookControl = SmoothSwimmingLookControl(this, 15)
        navigation = WaterBoundPathNavigation(this, world)
    }

    override fun registerGoals() {
        super.registerGoals()
        goalSelector.addGoal(0, StayInWaterGoal(this))
        goalSelector.addGoal(1, TemptGoal(this, 0.5, BREEDING_INGREDIENT, false))
        goalSelector.addGoal(2, BreedGoal(this, 0.5))
        goalSelector.addGoal(1, RandomSwimmingGoal(this, 1.0, 2))
        goalSelector.addGoal(5, FollowParentGoal(this, 1.1))
        goalSelector.addGoal(5, BreathAirGoal(this))
    }

    //#region Data

    override fun defineSynchedData() {
        super.defineSynchedData()
        entityData.define(SIRENIAN_SIZE, 0)
    }

    var size: Int
        get() = entityData.get(SIRENIAN_SIZE)
        set(size) {
            entityData.set(SIRENIAN_SIZE, size)
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

    override fun getMobType(): MobType {
        return MobType.WATER
    }

    override fun canBreatheUnderwater(): Boolean {
        return true
    }

    override fun isPushedByFluid(): Boolean {
        return false
    }

    override fun getMaxHeadXRot(): Int {
        return 1
    }

    override fun getMaxHeadYRot(): Int {
        return 1
    }

    override fun tick() {
        super.tick()
        prevRoll = currentRoll
    }

    override fun isFood(stack: ItemStack): Boolean {
        return BREEDING_INGREDIENT.test(stack)
    }

    override fun aiStep() {

        prevRoll = currentRoll
        var targetRoll = ((this.yRot - this.yRotO) * 0.1f).coerceIn(-0.45f, 0.45f)
        targetRoll = -targetRoll
        currentRoll += (targetRoll - currentRoll) * 0.05f

        val vec3d = this.deltaMovement
        if (!this.onGround() && this.isSwimming && vec3d.y < 0.0) {
            this.deltaMovement = vec3d.multiply(1.0, 0.6, 1.0)
        }

        super.aiStep()
    }

    override fun travel(travelVector: Vec3) {
        if (this.isEffectiveAi && this.isInWater) {
            this.moveRelative(this.speed, travelVector)
            this.move(MoverType.SELF, this.deltaMovement)
            this.deltaMovement = deltaMovement.scale(0.9)
        } else {
            super.travel(travelVector)
        }
    }

    override fun finalizeSpawn(
        world: ServerLevelAccessor,
        difficulty: DifficultyInstance,
        spawnReason: MobSpawnType,
        entityData: SpawnGroupData?,
        entityNbt: CompoundTag?,
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

    override fun removeWhenFarAway(distanceSquared: Double): Boolean {
        return !this.hasCustomName()
    }

    //#region Animations
    override fun registerControllers(controllers: AnimatableManager.ControllerRegistrar) {
        controllers.add(
            AnimationController(
                this, "Swim/Idle", 4
            ) { state: AnimationState<HybridAquaticSirenianEntity> ->
                when {
                    state.isMoving && isInWater -> state.setAndContinue(DefaultAnimations.SWIM)
                    !state.isMoving && isInWater -> state.setAndContinue(WATER_IDLE)
                    else -> state.setAndContinue(WATER_IDLE)
                }
            }
        )
    }

    override fun getAnimatableInstanceCache(): AnimatableInstanceCache {
        return factory
    }

    override fun getStandingEyeHeight(pose: Pose, dimensions: EntityDimensions): Float {
        return 0.3f
    }

    companion object {
        val SIRENIAN_SIZE: EntityDataAccessor<Int> =
            SynchedEntityData.defineId(HybridAquaticSirenianEntity::class.java, EntityDataSerializers.INT)

        val WATER_IDLE: RawAnimation = RawAnimation.begin().thenPlay("misc.water_idle")

        val BREEDING_INGREDIENT: Ingredient = Ingredient.of(
            Items.SEAGRASS,
            HybridAquaticBlocks.SEA_LETTUCE.get()
        )

        fun getScaleAdjustment(sirenian: HybridAquaticSirenianEntity, adjustment: Float): Float {
            return 1.0f + (sirenian.size * adjustment)
        }

        fun canSpawn(
            type: EntityType<out HybridAquaticSirenianEntity>,
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
