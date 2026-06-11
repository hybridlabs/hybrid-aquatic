package dev.hybridlabs.aquatic.entity.base

import dev.hybridlabs.aquatic.entity.ai.goal.WaterAnimalAttackGoal
import dev.hybridlabs.aquatic.entity.ai.goal.WaterAnimalBreedGoal
import dev.hybridlabs.aquatic.entity.ai.goal.WaterAnimalFollowParentGoal
import dev.hybridlabs.aquatic.entity.ai.goal.WaterAnimalJumpGoal
import net.minecraft.core.BlockPos
import net.minecraft.core.particles.ParticleTypes
import net.minecraft.nbt.CompoundTag
import net.minecraft.server.level.ServerLevel
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
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal
import net.minecraft.world.entity.ai.navigation.PathNavigation
import net.minecraft.world.entity.ai.navigation.WaterBoundPathNavigation
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.Items
import net.minecraft.world.item.crafting.Ingredient
import net.minecraft.world.level.Level
import net.minecraft.world.level.ServerLevelAccessor
import net.minecraft.world.level.pathfinder.PathType
import net.minecraft.world.phys.Vec3
import software.bernie.geckolib.constant.DefaultAnimations
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache
import software.bernie.geckolib.animation.AnimatableManager
import software.bernie.geckolib.animation.RawAnimation
import software.bernie.geckolib.util.GeckoLibUtil

@Suppress("LeakingThis", "UNUSED_PARAMETER", "unused", "DEPRECATION")
open class HADolphinEntity(type: EntityType<out HADolphinEntity>, world: Level) :
    HAWaterAnimal(type, world) {
    private val factory = GeckoLibUtil.createInstanceCache(this)

    override fun createNavigation(level: Level): PathNavigation {
        setPathfindingMalus(PathType.WATER, 0.0f)
        setPathfindingMalus(PathType.DANGER_FIRE, 16.0f)
        setPathfindingMalus(PathType.DAMAGE_FIRE, -1.0f)

        moveControl = SmoothSwimmingMoveControl(this, 60, 6, 0.02F, 0.1F, true)
        lookControl = SmoothSwimmingLookControl(this, 15)

        return WaterBoundPathNavigation(this, level)
    }

    override fun registerGoals() {
        super.registerGoals()
        goalSelector.addGoal(0, WaterAnimalAttackGoal(this, 1.1, true))
        goalSelector.addGoal(0, BreathAirGoal(this))
        goalSelector.addGoal(1, HurtByTargetGoal(this))
        goalSelector.addGoal(1, WaterAnimalBreedGoal(this, 1.1))
        goalSelector.addGoal(5, WaterAnimalJumpGoal(this, 10, 7.0))
        goalSelector.addGoal(2, TemptGoal(this, 1.1, BREEDING_INGREDIENT, false))
        goalSelector.addGoal(3, RandomSwimmingGoal(this, 1.0, 4))
        goalSelector.addGoal(5, WaterAnimalFollowParentGoal(this, 1.1))
        goalSelector.addGoal(6, MeleeAttackGoal(this, 1.2, true))
        getTargetConfig()?.addAttackTarget(targetSelector, MAX_HUNGER / 4, this, HAWaterAnimal::hunger)
    }

    override fun tick() {
        super.tick()

        if (this.isNoAi) {
            this.airSupply = this.maxAirSupply
        } else {
            if (this.isInWaterRainOrBubble) {
                moistness = getMaxMoistness()
            } else {
                moistness -= 1
                if (moistness <= 0) {
                    this.hurt(this.damageSources().dryOut(), 2.0f)
                    this.xRot = 0.0f
                    this.yRot = 0.0f
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

    override fun getMaxSpawnClusterSize(): Int {
        return 2
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
    ): SpawnGroupData? {
        this.airSupply = this.maxAirSupply
        this.yRot = 0.0f

        if (this.random.nextFloat() < 0.25f) {
            this.setAge(-6000)
        }

        return super.finalizeSpawn(world, difficulty, spawnReason, entityData)
    }

    override fun getBreedOffspring(p0: ServerLevel, p1: AgeableMob): AgeableMob? {
        return null
    }

    //#region Animations
    override fun registerControllers(controllers: AnimatableManager.ControllerRegistrar) {
        controllers.add(DefaultAnimations.genericSwimIdleController(this))
    }

    override fun getAnimatableInstanceCache(): AnimatableInstanceCache {
        return factory
    }

    override fun getStandingEyeHeight(pose: Pose, dimensions: EntityDimensions): Float {
        return 0.3f
    }

    companion object {
        val WATER_IDLE: RawAnimation = RawAnimation.begin().thenPlay("misc.water_idle")

        val BREEDING_INGREDIENT: Ingredient = Ingredient.of(
            Items.SEAGRASS,
        )

        fun canSpawn(
            type: EntityType<out HADolphinEntity>,
            world: ServerLevelAccessor,
            reason: MobSpawnType,
            pos: BlockPos,
            random: RandomSource,
        ): Boolean {
            val seaLevel = world.level.chunkSource.generator.seaLevel
            val topY = seaLevel - 4
            val bottomY = seaLevel - 32

            return pos.y in bottomY..topY &&
                    world.isWaterAt(pos)
        }
    }
}
