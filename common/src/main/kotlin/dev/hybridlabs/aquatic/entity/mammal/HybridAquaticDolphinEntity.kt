package dev.hybridlabs.aquatic.entity.mammal

import dev.hybridlabs.aquatic.entity.ai.goal.HybridAquaticJumpGoal
import dev.hybridlabs.aquatic.entity.ai.goal.WaterAnimalBreedGoal
import dev.hybridlabs.aquatic.entity.ai.goal.WaterAnimalFollowParentGoal
import dev.hybridlabs.aquatic.entity.base.HybridAquaticWaterAnimal
import net.minecraft.core.BlockPos
import net.minecraft.core.particles.ParticleTypes
import net.minecraft.nbt.CompoundTag
import net.minecraft.network.syncher.EntityDataAccessor
import net.minecraft.network.syncher.EntityDataSerializers
import net.minecraft.network.syncher.SynchedEntityData
import net.minecraft.server.level.ServerLevel
import net.minecraft.tags.FluidTags
import net.minecraft.util.Mth
import net.minecraft.util.RandomSource
import net.minecraft.world.DifficultyInstance
import net.minecraft.world.entity.*
import net.minecraft.world.entity.ai.control.SmoothSwimmingLookControl
import net.minecraft.world.entity.ai.control.SmoothSwimmingMoveControl
import net.minecraft.world.entity.ai.goal.BreathAirGoal
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal
import net.minecraft.world.entity.ai.goal.RandomSwimmingGoal
import net.minecraft.world.entity.ai.goal.TemptGoal
import net.minecraft.world.entity.ai.navigation.WaterBoundPathNavigation
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.Items
import net.minecraft.world.item.crafting.Ingredient
import net.minecraft.world.level.Level
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
open class HybridAquaticDolphinEntity(type: EntityType<out HybridAquaticDolphinEntity>, world: Level) :
    HybridAquaticWaterAnimal(type, world), GeoEntity {
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
        goalSelector.addGoal(0, BreathAirGoal(this))
        goalSelector.addGoal(1, WaterAnimalBreedGoal(this, 1.1))
        goalSelector.addGoal(2, HybridAquaticJumpGoal(this, 20))
        goalSelector.addGoal(2, TemptGoal(this, 1.1, BREEDING_INGREDIENT, false))
        goalSelector.addGoal(3, RandomSwimmingGoal(this, 1.0, 2))
        goalSelector.addGoal(5, WaterAnimalFollowParentGoal(this, 1.1))
        goalSelector.addGoal(6, MeleeAttackGoal(this, 1.2, true))
    }

    override fun tick() {
        super.tick()

        prevRoll = currentRoll

        if (this.isNoAi) {
            this.airSupply = this.maxAirSupply
        } else {
            if (this.isInWaterRainOrBubble) {
                moistness = getMaxMoistness()
            } else {
                moistness -= 1
                if (moistness <= 0) {
                    this.hurt(this.damageSources().dryOut(), 2.0f)
                }

                if (this.onGround()) {
                    this.deltaMovement = this.deltaMovement.add(
                        ((this.random.nextFloat() * 2.0f - 1.0f) * 0.2f).toDouble(),
                        0.5,
                        ((this.random.nextFloat() * 2.0f - 1.0f) * 0.2f).toDouble()
                    )
                    this.yRot = this.random.nextFloat() * 360.0f
                    this.setOnGround(false)
                    this.hasImpulse = true
                }
            }

            if (this.level().isClientSide && this.isInWater && this.deltaMovement.lengthSqr() > 0.03) {
                val vec3 = this.getViewVector(0.0f)
                val f = Mth.cos(this.yRot * (Math.PI.toFloat() / 180f)) * 0.3f
                val f1 = Mth.sin(this.yRot * (Math.PI.toFloat() / 180f)) * 0.3f
                val f2 = 1.2f - this.random.nextFloat() * 0.7f

                for (i in 0..1) {
                    this.level().addParticle(
                        ParticleTypes.DOLPHIN,
                        this.x - vec3.x * f2.toDouble() + f.toDouble(),
                        this.y - vec3.y,
                        this.z - vec3.z * f2.toDouble() + f1.toDouble(),
                        0.0,
                        0.0,
                        0.0
                    )
                    this.level().addParticle(
                        ParticleTypes.DOLPHIN,
                        this.x - vec3.x * f2.toDouble() - f.toDouble(),
                        this.y - vec3.y,
                        this.z - vec3.z * f2.toDouble() - f1.toDouble(),
                        0.0,
                        0.0,
                        0.0
                    )
                }
            }
        }
    }

    //#region Data
    override fun defineSynchedData() {
        super.defineSynchedData()
        entityData.define(DOLPHIN_SIZE, 0)
        entityData.define(MOISTNESS, getMaxMoistness())
    }

    override fun addAdditionalSaveData(compound: CompoundTag) {
        super.addAdditionalSaveData(compound)
        compound.putInt(MOISTNESS_KEY, moistness)
    }

    override fun readAdditionalSaveData(compound: CompoundTag) {
        super.readAdditionalSaveData(compound)
        moistness = compound.getInt(MOISTNESS_KEY)
    }
    //#endregion

    var size: Int
        get() = entityData.get(DOLPHIN_SIZE)
        set(size) {
            entityData.set(DOLPHIN_SIZE, size)
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

    override fun handleAirSupply(airSupply: Int) {
    }

    private fun getMaxMoistness(): Int {
        return 2400
    }

    var moistness: Int
        get() = entityData.get(MOISTNESS)
        set(moistness) {
            entityData.set(MOISTNESS, moistness)
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
            ) { state: AnimationState<HybridAquaticDolphinEntity> ->
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
        val MOISTNESS: EntityDataAccessor<Int> =
            SynchedEntityData.defineId(HybridAquaticDolphinEntity::class.java, EntityDataSerializers.INT)
        val DOLPHIN_SIZE: EntityDataAccessor<Int> =
            SynchedEntityData.defineId(HybridAquaticDolphinEntity::class.java, EntityDataSerializers.INT)

        val WATER_IDLE: RawAnimation = RawAnimation.begin().thenPlay("misc.water_idle")

        val BREEDING_INGREDIENT: Ingredient = Ingredient.of(
            Items.SEAGRASS,
        )

        const val HUNGER_KEY = "Hunger"
        const val MOISTNESS_KEY = "Moistness"

        fun getScaleAdjustment(dolphin: HybridAquaticDolphinEntity, adjustment: Float): Float {
            return 1.0f + (dolphin.size * adjustment)
        }

        fun canSpawn(
            type: EntityType<out HybridAquaticDolphinEntity>,
            world: ServerLevelAccessor,
            reason: MobSpawnType,
            pos: BlockPos,
            random: RandomSource,
        ): Boolean {
            val topY = world.seaLevel - 4
            val bottomY = world.seaLevel - 32

            return pos.y in bottomY..topY &&
                    world.isWaterAt(pos)
        }
    }
}
