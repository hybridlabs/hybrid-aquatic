package dev.hybridlabs.aquatic.entity.fish

import dev.hybridlabs.aquatic.entity.ai.MobTargetConfiguration
import dev.hybridlabs.aquatic.tag.HybridAquaticEntityTags
import net.minecraft.nbt.CompoundTag
import net.minecraft.network.syncher.EntityDataAccessor
import net.minecraft.network.syncher.EntityDataSerializers
import net.minecraft.network.syncher.SynchedEntityData
import net.minecraft.world.damagesource.DamageSource
import net.minecraft.world.entity.EntityDimensions
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.Pose
import net.minecraft.world.entity.ai.attributes.AttributeSupplier
import net.minecraft.world.entity.ai.attributes.Attributes
import net.minecraft.world.entity.ai.control.SmoothSwimmingLookControl
import net.minecraft.world.entity.ai.control.SmoothSwimmingMoveControl
import net.minecraft.world.entity.ai.goal.RandomSwimmingGoal
import net.minecraft.world.entity.ai.navigation.PathNavigation
import net.minecraft.world.entity.ai.navigation.WaterBoundPathNavigation
import net.minecraft.world.level.Level
import net.minecraft.world.level.pathfinder.BlockPathTypes
import software.bernie.geckolib.constant.DefaultAnimations
import software.bernie.geckolib.core.animation.AnimatableManager
import software.bernie.geckolib.core.animation.AnimationController

class TripodFishEntity(type: EntityType<out TripodFishEntity>, world: Level) :
    HybridAquaticFishEntity(type, world) {
    private var sittingTimer: Int = 0

    override fun getTargetConfig() = MobTargetConfiguration.ofPrey(
        HybridAquaticEntityTags.MEDIUM_CREATURES,
        HybridAquaticEntityTags.LARGE_CREATURES,
        HybridAquaticEntityTags.ALL_SHARKS
    )

    override fun createNavigation(level: Level): PathNavigation {
        setPathfindingMalus(BlockPathTypes.WATER, 0.0f)
        setPathfindingMalus(BlockPathTypes.DANGER_FIRE, 16.0f)
        setPathfindingMalus(BlockPathTypes.DAMAGE_FIRE, -1.0f)

        moveControl = TripodfishMoveControl(this, 85, 5, 0.02F, 0.1F, false)
        lookControl = SmoothSwimmingLookControl(this, 10)

        return WaterBoundPathNavigation(this, level)
    }

    override fun registerGoals() {
        goalSelector.addGoal(3, TripodfishSwimmingGoal(this, 1.0, 10))
        super.registerGoals()
    }
    override fun tick() {
        super.tick()

        if (this.isSitting()) {
            this.xRot = 0.0f
            this.yRot = 0.0f
            this.yHeadRot = 0.0f
        }
    }

    override fun getStandingEyeHeight(pose: Pose, dimensions: EntityDimensions): Float {
        return dimensions.height * 1.1f
    }

    //#region Data
    override fun defineSynchedData() {
        super.defineSynchedData()
        entityData.define(SITTING, true)
    }

    override fun addAdditionalSaveData(compound: CompoundTag) {
        super.addAdditionalSaveData(compound)
        compound.putBoolean("Sitting", isSitting())
    }

    override fun readAdditionalSaveData(compound: CompoundTag) {
        super.readAdditionalSaveData(compound)
        this.setSitting(compound.getBoolean("Sitting"))
    }
    //#endregion

    override fun getMaxSpawnClusterSize(): Int {
        return 1
    }

    fun isSitting(): Boolean {
        return entityData.get(SITTING)
    }

    private fun setSitting(sitting: Boolean) {
        entityData.set(SITTING, sitting)
    }

    override fun isVisuallySwimming(): Boolean {
        return this.isSwimming
    }

    //#region Animations
    override fun registerControllers(controllers: AnimatableManager.ControllerRegistrar) {
        controllers.add(
            AnimationController(this, "Tripod Fish Controller", 4) { state ->
                when {
                    isInWater && state.isMoving -> {
                        state.setAndContinue(DefaultAnimations.SWIM)
                    }

                    isInWater && isSprinting && state.isMoving -> {
                        state.setAndContinue(DefaultAnimations.RUN)
                    }

                    isInWater && !state.isMoving -> {
                        state.setAndContinue(DefaultAnimations.IDLE)
                    }

                    isInWater && isSitting() -> {
                        state.setAndContinue(DefaultAnimations.SIT)
                    }

                    this.moistness < 590 -> {
                        state.setAndContinue(FLOP_ANIMATION)
                    }

                    else -> {
                        state.setAndContinue(DefaultAnimations.IDLE)
                    }
                }
            }
        )
    }

    override fun aiStep() {
        super.aiStep()
        if (!level().isClientSide() && this.isEffectiveAi) {
            if (this.isInWater) {
                if (this.isSitting()) {
                    if (--this.sittingTimer <= 0) {
                        this.setSitting(false)
                    } else {
                        this.deltaMovement = deltaMovement.subtract(0.0, 0.01, 0.0)
                    }
                } else if (random.nextFloat() <= 0.001f) {
                    this.sittingTimer = random.nextInt(500, 1000)
                    this.setSitting(true)
                }
            } else {
                this.setSitting(false)
            }
        }
    }

    override fun hurt(source: DamageSource, amount: Float): Boolean {
        if (super.hurt(source, amount) && this.lastHurtByMob != null) {
            if (!level().isClientSide) {
                if (this.isSitting()) {
                    this.setSitting(false)
                }
            }
            return true
        }
        return false
    }

    companion object {
        fun createMobAttributes(): AttributeSupplier.Builder {
            return createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 5.0)
                .add(Attributes.MOVEMENT_SPEED, 0.5)
                .add(Attributes.ATTACK_DAMAGE, 1.0)
                .add(Attributes.ATTACK_KNOCKBACK, 0.0)
                .add(Attributes.FOLLOW_RANGE, 8.0)
        }

        val SITTING: EntityDataAccessor<Boolean> =
            SynchedEntityData.defineId(TripodFishEntity::class.java, EntityDataSerializers.BOOLEAN)
    }

    internal class TripodfishSwimmingGoal(
        private val tripodFish: TripodFishEntity,
        speedModifier: Double,
        interval: Int,
    ) :
        RandomSwimmingGoal(tripodFish, speedModifier, interval) {

        override fun canUse(): Boolean {
            return !tripodFish.isSitting() && super.canUse()
        }
    }

    internal class TripodfishMoveControl(
        private val tripodFish: TripodFishEntity,
        maxTurnX: Int,
        maxTurnY: Int,
        inWaterSpeedModifier: Float,
        outsideWaterSpeedModifier: Float,
        applyGravity: Boolean,
    ) : SmoothSwimmingMoveControl(
        tripodFish,
        maxTurnX,
        maxTurnY,
        inWaterSpeedModifier,
        outsideWaterSpeedModifier,
        applyGravity
    ) {

        override fun tick() {
            if (!tripodFish.isSitting()) {
                super.tick()
            }
        }
    }
}