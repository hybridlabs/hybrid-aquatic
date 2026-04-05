package dev.hybridlabs.aquatic.entity.fish

import dev.hybridlabs.aquatic.entity.ai.MobTargetConfiguration
import dev.hybridlabs.aquatic.entity.ai.goal.AvoidEntityInWaterGoal
import dev.hybridlabs.aquatic.entity.ai.goal.FishAttackGoal
import dev.hybridlabs.aquatic.entity.ai.goal.FollowGlowingEntityGoal
import dev.hybridlabs.aquatic.entity.base.HAWaterAnimal
import dev.hybridlabs.aquatic.entity.cephalopod.HACephalopodEntity
import dev.hybridlabs.aquatic.entity.mammal.HAMammalEntity
import dev.hybridlabs.aquatic.entity.shark.HASharkEntity
import dev.hybridlabs.aquatic.world.WorldHelper
import net.minecraft.core.BlockPos
import net.minecraft.nbt.CompoundTag
import net.minecraft.network.syncher.EntityDataAccessor
import net.minecraft.network.syncher.EntityDataSerializers
import net.minecraft.network.syncher.SynchedEntityData
import net.minecraft.server.level.ServerLevel
import net.minecraft.sounds.SoundEvent
import net.minecraft.sounds.SoundEvents
import net.minecraft.util.Mth
import net.minecraft.util.RandomSource
import net.minecraft.world.damagesource.DamageSource
import net.minecraft.world.entity.*
import net.minecraft.world.entity.ai.control.SmoothSwimmingLookControl
import net.minecraft.world.entity.ai.control.SmoothSwimmingMoveControl
import net.minecraft.world.entity.ai.goal.RandomSwimmingGoal
import net.minecraft.world.entity.ai.goal.TemptGoal
import net.minecraft.world.entity.ai.navigation.PathNavigation
import net.minecraft.world.entity.ai.navigation.WaterBoundPathNavigation
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.Items
import net.minecraft.world.item.crafting.Ingredient
import net.minecraft.world.level.Level
import net.minecraft.world.level.ServerLevelAccessor
import net.minecraft.world.level.pathfinder.BlockPathTypes
import net.minecraft.world.phys.Vec3
import software.bernie.geckolib.constant.DefaultAnimations
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache
import software.bernie.geckolib.core.animation.AnimatableManager
import software.bernie.geckolib.core.animation.AnimationController
import software.bernie.geckolib.core.animation.RawAnimation
import software.bernie.geckolib.util.GeckoLibUtil

abstract class HAFishEntity(type: EntityType<out HAFishEntity>, world: Level) :
    HAWaterAnimal(type, world) {
    var prevRoll: Float = 0f
    var currentRoll: Float = 0.0f
    private var sittingTimer: Int = 0
    private val factory = GeckoLibUtil.createInstanceCache(this)

    open fun getTargetConfig(): MobTargetConfiguration? = null

    override fun createNavigation(level: Level): PathNavigation {
        setPathfindingMalus(BlockPathTypes.WATER, 0.0f)
        setPathfindingMalus(BlockPathTypes.DANGER_FIRE, 16.0f)
        setPathfindingMalus(BlockPathTypes.DAMAGE_FIRE, -1.0f)

        moveControl = SmoothSwimmingMoveControl(this, 85, 5, 0.02F, 0.1f, false)
        lookControl = SmoothSwimmingLookControl(this, 10)

        return WaterBoundPathNavigation(this, level)
    }

    override fun registerGoals() {
        super.registerGoals()
        goalSelector.addGoal(0, FishAttackGoal(this, 1.1, true))
        goalSelector.addGoal(0, FollowGlowingEntityGoal(this, 1.1, 4.0F, 8.0F))
        goalSelector.addGoal(1, TemptGoal(this, 1.1, BREEDING_INGREDIENT, false))
        goalSelector.addGoal(2, RandomSwimmingGoal(this, 1.0, 10))
        goalSelector.addGoal(2, AvoidEntityInWaterGoal(this, Player::class.java, 16.0f, 1.5, 1.5))
        getTargetConfig()?.addAttackTarget(targetSelector, MAX_HUNGER / 4, this, HAWaterAnimal::hunger)
        getTargetConfig()?.addAvoidanceGoal(goalSelector, this)
    }

    //#region Data
    protected open fun canSit(): Boolean {
        return false
    }

    fun isSitting(): Boolean {
        return entityData.get(SITTING)
    }

    fun setSitting(sitting: Boolean) {
        entityData.set(SITTING, sitting)
    }

    override fun isVisuallySwimming(): Boolean {
        return this.isSwimming
    }

    override fun defineSynchedData() {
        super.defineSynchedData()
        entityData.define(ATTEMPT_ATTACK, false)
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

    override fun getBreedOffspring(p0: ServerLevel, p1: AgeableMob): AgeableMob? {
        return null
    }

    //#region Moistness & Air
    override fun handleAirSupply(air: Int) {
        if (isInWaterOrBubble) {
            airSupply = maxAirSupply
        }
    }

    open fun shouldFlopOnLand(): Boolean {
        return true
    }
    //#endregion

    //#region Animations
    override fun registerControllers(controllers: AnimatableManager.ControllerRegistrar) {
        controllers.add(
            AnimationController(this, "Fish Controller", 4) { state ->
                when {
                    isInWater && state.isMoving -> {
                        state.setAndContinue(DefaultAnimations.SWIM)
                    }

                    isUnderWater && isSprinting -> {
                        state.setAndContinue(DefaultAnimations.RUN)
                    }

                    isInWater && isSitting() && canSit() -> {
                        state.setAndContinue(DefaultAnimations.SIT)
                    }

                    onGround() && !state.isMoving && !isSitting() && canSit() -> {
                        state.setAndContinue(DefaultAnimations.IDLE)
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

        controllers.add(
            DefaultAnimations.genericAttackAnimation(this, DefaultAnimations.ATTACK_BITE)
        )
    }

    override fun getAnimatableInstanceCache(): AnimatableInstanceCache {
        return factory
    }
    //#endregion

    //#region SFX
    open val flopSound: SoundEvent = SoundEvents.PUFFER_FISH_FLOP

    override fun getHurtSound(source: DamageSource): SoundEvent {
        return SoundEvents.COD_HURT
    }

    override fun getDeathSound(): SoundEvent {
        return SoundEvents.COD_DEATH
    }
    //#endregion

    //#region Properties

    override fun getMaxHeadXRot(): Int {
        return 5
    }

    override fun getMaxHeadYRot(): Int {
        return 1
    }

    private fun getHandSwingDuration(): Int {
        return 40
    }

    override fun getStandingEyeHeight(pose: Pose, dimensions: EntityDimensions): Float {
        return dimensions.height * 0.5f
    }

    protected open fun hasSelfControl(): Boolean {
        return true
    }
    //#endregion

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
                currentRoll = Mth.lerp(0.2f, currentRoll, 0f)
            }
        }

        if (this.isSitting()) {
            this.xRot = 0.0f
            this.yRot = 0.0f
            this.yHeadRot = 0.0f
        }
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

    override fun aiStep() {
        if (this.shouldFlopOnLand() && !this.isInWater && this.onGround() && this.verticalCollision) {
            this.deltaMovement = deltaMovement.add(
                ((random.nextFloat() * 2.0f - 1.0f) * 0.05f).toDouble(), 0.4000000059604645,
                ((random.nextFloat() * 2.0f - 1.0f) * 0.05f).toDouble()
            )
            this.setOnGround(false)
            this.hasImpulse = true
            this.playSound(this.flopSound, this.soundVolume, this.voicePitch)
        }

        if (!level().isClientSide() && this.isEffectiveAi) {
            if (this.isInWater && canSit()) {
                if (this.isSitting()) {
                    if (--this.sittingTimer <= 0) {
                        this.setSitting(false)
                    } else {
                        this.deltaMovement = deltaMovement.subtract(0.0, 0.01, 0.0)
                    }
                } else if (random.nextFloat() <= 0.001f) {
                    this.sittingTimer = random.nextInt(200, 600)
                    this.setSitting(true)
                }
            } else {
                this.setSitting(false)
            }
        }

        prevRoll = currentRoll
        var targetRoll = ((this.yRot - this.yRotO) * 0.1f).coerceIn(-0.45f, 0.45f)
        targetRoll = -targetRoll
        currentRoll += (targetRoll - currentRoll) * 0.05f

        this.updateSwingTime()
        super.aiStep()
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

    override fun updateSwingTime() {
        val i = this.getHandSwingDuration()
        if (this.swinging) {
            ++this.swingTime
            if (this.swingTime >= i) {
                this.swingTime = 0
                this.swinging = false
            }
        } else {
            this.swingTime = 0
        }

        this.attackAnim = swingTime.toFloat() / i.toFloat()
    }

    override fun getAttackAnim(tickDelta: Float): Float {
        var f = this.attackAnim - this.oAttackAnim
        if (f < 0.0f) {
            ++f
        }

        return this.oAttackAnim + f * tickDelta
    }

    override fun doHurtTarget(target: Entity): Boolean {
        if (super.doHurtTarget(target)) {
            playSound(SoundEvents.FOX_BITE, 1.0F, 1.0F)
            return true
        } else {
            return false
        }
    }

    @Suppress("DEPRECATION", "unused")
    companion object {
        val ATTEMPT_ATTACK: EntityDataAccessor<Boolean> =
            SynchedEntityData.defineId(HAFishEntity::class.java, EntityDataSerializers.BOOLEAN)
        val SITTING: EntityDataAccessor<Boolean> =
            SynchedEntityData.defineId(HAFishEntity::class.java, EntityDataSerializers.BOOLEAN)

        val FLOP_ANIMATION: RawAnimation = RawAnimation.begin().thenPlay("misc.flop")

        val BREEDING_INGREDIENT: Ingredient = Ingredient.of(
            Items.BREAD,
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
            return pos.y in (seaLevel - 16)..<(seaLevel - 1) &&
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
            return pos.y in (seaLevel - 32)..(seaLevel - 8) &&
                    world.isWaterAt(pos) &&
                    WorldHelper.canSeeSkyFromBelowWater(world, pos)
        }

        fun canNightSpawn(
            type: EntityType<out HAWaterAnimal>,
            world: ServerLevelAccessor,
            reason: MobSpawnType,
            pos: BlockPos,
            random: RandomSource,
        ): Boolean {
            val seaLevel = world.level.chunkSource.generator.seaLevel
            return !world.level.isDay &&
                    return pos.y in (seaLevel - 32)..(seaLevel - 8) &&
                            world.isWaterAt(pos) &&
                            WorldHelper.canSeeSkyFromBelowWater(world, pos)
        }

        fun canDeepSpawn(
            type: EntityType<out HAWaterAnimal>,
            world: ServerLevelAccessor,
            reason: MobSpawnType,
            pos: BlockPos,
            random: RandomSource,
        ): Boolean {
            val seaLevel = world.level.chunkSource.generator.seaLevel
            return pos.y in (seaLevel - 256)..(seaLevel - 55) &&
                    world.isWaterAt(pos)
        }
        //#endregion
    }

    internal class BottomDwellerSwimmingGoal(
        private val bottomDweller: HAFishEntity,
        speedModifier: Double,
        interval: Int,
    ) :
        RandomSwimmingGoal(bottomDweller, speedModifier, interval) {

        override fun canUse(): Boolean {
            return !bottomDweller.isSitting() && super.canUse()
        }
    }

    internal class BottomDwellerMoveControl(
        private val bottomDweller: HAFishEntity,
        maxTurnX: Int,
        maxTurnY: Int,
        inWaterSpeedModifier: Float,
        outsideWaterSpeedModifier: Float,
        applyGravity: Boolean,
    ) : SmoothSwimmingMoveControl(
        bottomDweller,
        maxTurnX,
        maxTurnY,
        inWaterSpeedModifier,
        outsideWaterSpeedModifier,
        applyGravity
    ) {

        override fun tick() {
            if (!bottomDweller.isSitting()) {
                super.tick()
            }
        }
    }
}
