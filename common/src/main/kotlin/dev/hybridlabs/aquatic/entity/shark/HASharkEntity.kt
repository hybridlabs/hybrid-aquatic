package dev.hybridlabs.aquatic.entity.shark

import dev.hybridlabs.aquatic.effect.HAMobEffects
import dev.hybridlabs.aquatic.entity.ai.goal.SharkAttackGoal
import dev.hybridlabs.aquatic.entity.ai.goal.boids.StayInWaterGoal
import dev.hybridlabs.aquatic.entity.base.HAWaterAnimal
import dev.hybridlabs.aquatic.tag.HAItemTags
import dev.hybridlabs.aquatic.world.WorldHelper
import net.minecraft.core.BlockPos
import net.minecraft.nbt.CompoundTag
import net.minecraft.server.level.ServerLevel
import net.minecraft.sounds.SoundEvent
import net.minecraft.sounds.SoundEvents
import net.minecraft.tags.ItemTags
import net.minecraft.util.RandomSource
import net.minecraft.util.TimeUtil
import net.minecraft.util.valueproviders.UniformInt
import net.minecraft.world.damagesource.DamageSource
import net.minecraft.world.entity.*
import net.minecraft.world.entity.ai.control.SmoothSwimmingLookControl
import net.minecraft.world.entity.ai.control.SmoothSwimmingMoveControl
import net.minecraft.world.entity.ai.goal.RandomSwimmingGoal
import net.minecraft.world.entity.ai.goal.TemptGoal
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal
import net.minecraft.world.entity.ai.navigation.PathNavigation
import net.minecraft.world.entity.ai.navigation.WaterBoundPathNavigation
import net.minecraft.world.entity.monster.Monster.isDarkEnoughToSpawn
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.crafting.Ingredient
import net.minecraft.world.level.Level
import net.minecraft.world.level.ServerLevelAccessor
import net.minecraft.world.level.pathfinder.BlockPathTypes
import net.minecraft.world.phys.Vec3
import software.bernie.geckolib.constant.DefaultAnimations
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache
import software.bernie.geckolib.core.animation.AnimatableManager
import software.bernie.geckolib.core.animation.AnimationController
import software.bernie.geckolib.core.animation.AnimationController.AnimationStateHandler
import software.bernie.geckolib.core.animation.AnimationState
import software.bernie.geckolib.core.animation.RawAnimation
import software.bernie.geckolib.core.`object`.PlayState
import software.bernie.geckolib.util.GeckoLibUtil
import java.util.*

@Suppress("LeakingThis", "DEPRECATION", "UNUSED_PARAMETER")
open class HASharkEntity(
    entityType: EntityType<out HASharkEntity>,
    world: Level,
) : HAWaterAnimal(entityType, world), NeutralMob {
    open val isPassive: Boolean = true
    open val closePlayerAttack: Boolean = false
    var prevRoll: Float = 0f
    var currentRoll: Float = 0.0f
    private val factory = GeckoLibUtil.createInstanceCache(this)
    private var angerTime = 0
    private var angryAt: UUID? = null

    //#region Initialization
    override fun createNavigation(level: Level): PathNavigation {
        setPathfindingMalus(BlockPathTypes.WATER, 0.0f)
        setPathfindingMalus(BlockPathTypes.DANGER_FIRE, 16.0f)
        setPathfindingMalus(BlockPathTypes.DAMAGE_FIRE, -1.0f)

        moveControl = SmoothSwimmingMoveControl(this, 60, 6, 0.02F, 0.1F, false)
        lookControl = SmoothSwimmingLookControl(this, 15)

        return WaterBoundPathNavigation(this, level)
    }

    override fun registerGoals() {
        super.registerGoals()
        goalSelector.addGoal(0, SharkAttackGoal(this, 1.1, true))
        goalSelector.addGoal(0, StayInWaterGoal(this))
        goalSelector.addGoal(1, TemptGoal(this, 1.1, BREEDING_INGREDIENT, false))
        goalSelector.addGoal(2, RandomSwimmingGoal(this, 1.0, 2))
        targetSelector.addGoal(
            1,
            NearestAttackableTargetGoal(this, Player::class.java, 10, true, true) {
                entity: LivingEntity -> isAngryAt(entity) || shouldProximityAttack(entity as Player) && !isPassive
            }
        )
        targetSelector.addGoal(
            1,
            NearestAttackableTargetGoal(this, LivingEntity::class.java, 10, true, true) {
                it.hasEffect(HAMobEffects.BLEEDING.get()) && it !is HASharkEntity && !isPassive
            }
        )
        getTargetConfig()?.addAttackTarget(targetSelector, MAX_HUNGER / 4, this, HAWaterAnimal::hunger)
        getTargetConfig()?.addAvoidanceGoal(goalSelector, this)
    }

    override fun getBreedOffspring(p0: ServerLevel, p1: AgeableMob): AgeableMob? {
        return null
    }

    override fun canBreatheUnderwater(): Boolean {
        return true
    }

    override fun isPushedByFluid(): Boolean {
        return false
    }

    override fun tick() {
        super.tick()
        prevRoll = currentRoll

        if (this.isUnderWater) {
            moistness = getMaxMoistness()
        } else {
            moistness -= 1
            if (moistness <= -20) {
                moistness = 0
                hurt(this.damageSources().dryOut(), 2.0f)
                this.xRot = 0.0f
                this.yRot = 0.0f
            }
        }
    }
    //#endregion

    override fun getMaxSpawnClusterSize(): Int {
        return 1
    }

    //#region Data
    override fun addAdditionalSaveData(compound: CompoundTag) {
        super.addAdditionalSaveData(compound)
        this.addPersistentAngerSaveData(compound)
    }

    override fun readAdditionalSaveData(compound: CompoundTag) {
        super.readAdditionalSaveData(compound)
        this.readPersistentAngerSaveData(this.level(), compound)
    }
    //#endregion

    //#region Movement
    override fun travel(travelVector: Vec3) {
        if (this.isEffectiveAi && this.isInWater) {
            this.moveRelative(this.speed, travelVector)
            this.move(MoverType.SELF, this.deltaMovement)
            this.deltaMovement = deltaMovement.scale(0.9)
        } else {
            super.travel(travelVector)
        }
    }

    override fun aiStep() {
        this.updateSwingTime()

        prevRoll = currentRoll
        var targetRoll = ((this.yRot - this.yRotO) * 0.1f).coerceIn(-0.45f, 0.45f)
        targetRoll = -targetRoll
        currentRoll += (targetRoll - currentRoll) * 0.05f
        super.aiStep()
    }
    //#endregion

    //#region Properties
    override fun getMaxHeadXRot(): Int {
        return 1
    }

    override fun getMaxHeadYRot(): Int {
        return 1
    }

    override fun getStandingEyeHeight(pose: Pose, dimensions: EntityDimensions): Float {
        return dimensions.height * 0.65f
    }

    override fun getMaxMoistness(): Int {
        return 1200
    }

    override fun isFood(stack: ItemStack): Boolean {
        return stack.`is`(ItemTags.FISHES)
    }
    //#endregion

    //#region Animations
    override fun registerControllers(controllers: AnimatableManager.ControllerRegistrar) {
        controllers.add(
            AnimationController(this, "Run/Swim/Idle", 4) {
                    state: AnimationState<HASharkEntity> ->
                if (this.isInWaterOrBubble && state.isMoving) state.setAndContinue(
                    if (this.isSprinting) DefaultAnimations.RUN else DefaultAnimations.SWIM)
                else state.setAndContinue(DefaultAnimations.SWIM)
            }
        )

        controllers.add(
            AnimationController(
                this, "Sit",
                AnimationStateHandler { state: AnimationState<HASharkEntity> ->
                    if (this.isSitting())
                        return@AnimationStateHandler state.setAndContinue(DefaultAnimations.SIT)
                    PlayState.STOP
                }
            )
        )

        controllers.add(
            AnimationController(this, "Flop", 4) { state ->
                if (!this.isInWaterOrBubble && this.moistness < 1190) {
                    return@AnimationController state.setAndContinue(FLOP_ANIMATION)
                }

                PlayState.STOP
            }
        )

        controllers.add(
            DefaultAnimations.genericAttackAnimation(this, DefaultAnimations.ATTACK_BITE)
        )
    }

    override fun getAnimatableInstanceCache(): AnimatableInstanceCache {
        return factory
    }
    //#endregion

    //#region SFX
    override fun getAmbientSound(): SoundEvent? {
        return SoundEvents.COD_AMBIENT
    }

    override fun getHurtSound(source: DamageSource): SoundEvent {
        return SoundEvents.COD_HURT
    }

    override fun getDeathSound(): SoundEvent {
        return SoundEvents.COD_DEATH
    }
    //#endregion

    //#region Angerable Implementation Details
    override fun getRemainingPersistentAngerTime(): Int {
        return angerTime
    }

    override fun setRemainingPersistentAngerTime(angerTime: Int) {
        this.angerTime = angerTime
    }

    override fun getPersistentAngerTarget(): UUID? {
        return this.angryAt
    }

    override fun setPersistentAngerTarget(angryAt: UUID?) {
        this.angryAt = angryAt
    }

    override fun startPersistentAngerTimer() {
        remainingPersistentAngerTime = ANGER_TIME_RANGE.sample(random)
    }

    private fun shouldProximityAttack(player: Player): Boolean {
        if (customName?.string == "friend") return false

        return closePlayerAttack && player.distanceToSqr(this) <= 8 && !player.isCreative
    }
    //#endregion

    override fun doHurtTarget(target: Entity): Boolean {
        if (super.doHurtTarget(target)) {
            playSound(SoundEvents.FOX_BITE, 1.0F, 0.0F)
            return true
        } else {
            return false
        }
    }

    companion object {
        val ANGER_TIME_RANGE: UniformInt = TimeUtil.rangeOfSeconds(10, 30)
        val FLOP_ANIMATION: RawAnimation = RawAnimation.begin().thenPlay("misc.flop")

        val BREEDING_INGREDIENT: Ingredient = Ingredient.of(
            HAItemTags.RAW_FISH,
        )

        //#region Spawning
        fun canShallowSpawn(
            type: EntityType<out HAWaterAnimal>,
            world: ServerLevelAccessor,
            reason: MobSpawnType,
            pos: BlockPos,
            random: RandomSource,
        ): Boolean {
            val seaLevel = world.level.chunkSource.generator.seaLevel
            val topY = seaLevel - 2
            val bottomY = seaLevel - 6

            return pos.y in bottomY..topY &&
                    world.isWaterAt(pos) &&
                    WorldHelper.canSeeSkyFromBelowWater(world, pos)
        }

        fun canSpawn(
            type: EntityType<out HAWaterAnimal>,
            world: ServerLevelAccessor,
            reason: MobSpawnType,
            pos: BlockPos,
            random: RandomSource,
        ): Boolean {
            val seaLevel = world.level.chunkSource.generator.seaLevel
            val topY = seaLevel - 8
            val bottomY = seaLevel - 24

            return pos.y in bottomY..topY &&
                    world.isWaterAt(pos)
        }

        @Suppress("UNUSED_PARAMETER", "DEPRECATION")
        fun canDeepSpawn(
            type: EntityType<out HAWaterAnimal>,
            world: ServerLevelAccessor,
            reason: MobSpawnType,
            pos: BlockPos,
            random: RandomSource,
        ): Boolean {
            val seaLevel = world.level.chunkSource.generator.seaLevel
            val topY = seaLevel - 48
            val bottomY = seaLevel - 256

            return pos.y in bottomY..topY &&
                    world.isWaterAt(pos) &&
                    isDarkEnoughToSpawn(world, pos, random)
        }
        //#endregion
    }
}
