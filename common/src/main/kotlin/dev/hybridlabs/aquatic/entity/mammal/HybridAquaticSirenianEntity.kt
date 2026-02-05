    package dev.hybridlabs.aquatic.entity.mammal

    import dev.hybridlabs.aquatic.entity.ai.goal.boids.StayInWaterGoal
    import net.minecraft.core.BlockPos
    import net.minecraft.nbt.CompoundTag
    import net.minecraft.network.syncher.EntityDataAccessor
    import net.minecraft.network.syncher.EntityDataSerializers
    import net.minecraft.network.syncher.SynchedEntityData
    import net.minecraft.tags.FluidTags
    import net.minecraft.util.RandomSource
    import net.minecraft.world.DifficultyInstance
    import net.minecraft.world.entity.*
    import net.minecraft.world.entity.ai.control.SmoothSwimmingLookControl
    import net.minecraft.world.entity.ai.control.SmoothSwimmingMoveControl
    import net.minecraft.world.entity.ai.goal.BreathAirGoal
    import net.minecraft.world.entity.ai.goal.RandomSwimmingGoal
    import net.minecraft.world.entity.ai.navigation.WaterBoundPathNavigation
    import net.minecraft.world.entity.animal.WaterAnimal
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

    @Suppress("LeakingThis", "UNUSED_PARAMETER", "unused", "DEPRECATION")
    open class HybridAquaticSirenianEntity(type: EntityType<out HybridAquaticSirenianEntity>, world: Level) : WaterAnimal(type, world), GeoEntity {
        private val factory = GeckoLibUtil.createInstanceCache(this)

        init {
            setPathfindingMalus(BlockPathTypes.WATER, 0.0f)
            setPathfindingMalus(BlockPathTypes.DANGER_FIRE, 16.0f)
            setPathfindingMalus(BlockPathTypes.DAMAGE_FIRE, -1.0f)
            moveControl = SmoothSwimmingMoveControl(this, 45, 3, 0.02F, 0.1F, false)
            lookControl = SmoothSwimmingLookControl(this, 15)
            navigation = WaterBoundPathNavigation(this, world)
        }

        override fun defineSynchedData() {
            super.defineSynchedData()
            entityData.define(MAMMAL_SIZE, 0)
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

        protected open fun getMinSize(): Int {
            return 0
        }

        protected open fun getMaxSize(): Int {
            return 0
        }

        //#region Animations
        override fun registerControllers(controllers: AnimatableManager.ControllerRegistrar) {
            controllers.add(
                AnimationController(
                    this, "Walk/Swim/Idle", 4
                ) { state: AnimationState<HybridAquaticSirenianEntity> ->
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

        override fun registerGoals() {
            super.registerGoals()
            goalSelector.addGoal(0, StayInWaterGoal(this))
            goalSelector.addGoal(1, RandomSwimmingGoal(this, 1.0, 2))
            goalSelector.addGoal(5, BreathAirGoal(this))
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
                SynchedEntityData.defineId(HybridAquaticSirenianEntity::class.java, EntityDataSerializers.INT)

            val WATER_IDLE: RawAnimation = RawAnimation.begin().thenPlay("misc.water_idle")

            fun getScaleAdjustment(mammal: HybridAquaticSirenianEntity, adjustment: Float): Float {
                return 1.0f + (mammal.size * adjustment)
            }

            fun canSpawn(
                type: EntityType<out WaterAnimal>,
                world: ServerLevelAccessor,
                reason: MobSpawnType,
                pos: BlockPos,
                random: RandomSource,
            ): Boolean {
                val topY = world.seaLevel - 1
                val bottomY = world.seaLevel - 32

                return pos.y in bottomY..topY &&
                        world.isWaterAt(pos) &&
                        world.canSeeSkyFromBelowWater(pos)
            }
        }
    }
