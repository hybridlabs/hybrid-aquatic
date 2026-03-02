package dev.hybridlabs.aquatic.entity.fish

import dev.hybridlabs.aquatic.entity.ai.MobTargetConfiguration
import dev.hybridlabs.aquatic.entity.ai.goal.FishAttackGoal
import dev.hybridlabs.aquatic.entity.base.HybridAquaticWaterAnimal
import dev.hybridlabs.aquatic.entity.cephalopod.HybridAquaticCephalopodEntity
import dev.hybridlabs.aquatic.entity.mammal.HybridAquaticMammalEntity
import dev.hybridlabs.aquatic.entity.shark.HybridAquaticSharkEntity
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
import net.minecraft.world.DifficultyInstance
import net.minecraft.world.damagesource.DamageSource
import net.minecraft.world.entity.*
import net.minecraft.world.entity.ai.control.SmoothSwimmingLookControl
import net.minecraft.world.entity.ai.control.SmoothSwimmingMoveControl
import net.minecraft.world.entity.ai.goal.RandomSwimmingGoal
import net.minecraft.world.entity.ai.goal.TemptGoal
import net.minecraft.world.entity.ai.navigation.WaterBoundPathNavigation
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
import software.bernie.geckolib.core.animation.RawAnimation
import software.bernie.geckolib.util.GeckoLibUtil

abstract class HybridAquaticFishEntity(type: EntityType<out HybridAquaticFishEntity>, world: Level) :
    HybridAquaticWaterAnimal(type, world), GeoEntity {
    var prevRoll: Float = 0f
    var currentRoll: Float = 0.0f
    private val factory = GeckoLibUtil.createInstanceCache(this)

    open fun getTargetConfig(): MobTargetConfiguration? = null

    init {
        setPathfindingMalus(BlockPathTypes.WATER, 0.0f)
        setPathfindingMalus(BlockPathTypes.DANGER_FIRE, 16.0f)
        setPathfindingMalus(BlockPathTypes.DAMAGE_FIRE, -1.0f)
        moveControl = SmoothSwimmingMoveControl(this, 85, 5, 0.02F, 0.1f, false)
        lookControl = SmoothSwimmingLookControl(this, 10)
        navigation = WaterBoundPathNavigation(this, world)
    }

    override fun registerGoals() {
        super.registerGoals()
        goalSelector.addGoal(0, FishAttackGoal(this, 1.1, true))
        goalSelector.addGoal(1, TemptGoal(this, 1.1, BREEDING_INGREDIENT, false))
        goalSelector.addGoal(2, RandomSwimmingGoal(this, 1.0, 10))
        getTargetConfig()?.addAttackTarget(targetSelector, MAX_HUNGER / 4, this, HybridAquaticFishEntity::hunger)
    }

    override fun defineSynchedData() {
        super.defineSynchedData()
        entityData.define(MOISTNESS, getMaxMoistness())
        entityData.define(FISH_SIZE, 0)
        entityData.define(ATTEMPT_ATTACK, false)
        entityData.define(HUNGER, MAX_HUNGER)
    }

    override fun finalizeSpawn(
        world: ServerLevelAccessor,
        difficulty: DifficultyInstance,
        spawnReason: MobSpawnType,
        entityData: SpawnGroupData?,
        entityNbt: CompoundTag?,
    ): SpawnGroupData? {
        this.size = this.random.nextIntBetweenInclusive(getMinSize(), getMaxSize())
        return super.finalizeSpawn(world, difficulty, spawnReason, entityData, entityNbt)
    }

    override fun getBreedOffspring(p0: ServerLevel, p1: AgeableMob): AgeableMob? {
        return null
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

        if (hunger > 0) hunger -= 1
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

        prevRoll = currentRoll
        var targetRoll = ((this.yRot - this.yRotO) * 0.1f).coerceIn(-0.45f, 0.45f)
        targetRoll = -targetRoll
        currentRoll += (targetRoll - currentRoll) * 0.05f

        this.updateSwingTime()
        super.aiStep()
    }

    override fun handleAirSupply(airSupply: Int) {}

    private fun getMaxMoistness(): Int {
        return 600
    }

    open fun shouldFlopOnLand(): Boolean {
        return true
    }

    override fun addAdditionalSaveData(compound: CompoundTag) {
        super.addAdditionalSaveData(compound)
        compound.putInt(MOISTNESS_KEY, moistness)
        compound.putInt(FISH_SIZE_KEY, size)
        compound.putInt(HUNGER_KEY, hunger)
        compound.putBoolean("FromFishingNet", fromFishingNet)
    }

    var fromFishingNet = false

    override fun readAdditionalSaveData(compound: CompoundTag) {
        super.readAdditionalSaveData(compound)
        moistness = compound.getInt(MOISTNESS_KEY)
        size = compound.getInt(FISH_SIZE_KEY)
        hunger = compound.getInt(HUNGER_KEY)
        fromFishingNet = compound.getBoolean("FromFishingNet")
    }

    override fun getStandingEyeHeight(pose: Pose, dimensions: EntityDimensions): Float {
        return dimensions.height * 0.5f
    }

    override fun removeWhenFarAway(distanceSquared: Double): Boolean {
        return !this.fromFishingNet && !this.hasCustomName()
    }

    //#region SFX
    open val flopSound: SoundEvent = SoundEvents.PUFFER_FISH_FLOP

    override fun getHurtSound(source: DamageSource): SoundEvent {
        return SoundEvents.COD_HURT
    }

    override fun getDeathSound(): SoundEvent {
        return SoundEvents.COD_DEATH
    }

    //#region end

    override fun dropFromLootTable(source: DamageSource, causedByPlayer: Boolean) {
        val attacker = source.directEntity
        if (attacker !is HybridAquaticFishEntity && attacker !is HybridAquaticSharkEntity && attacker !is HybridAquaticCephalopodEntity && attacker !is HybridAquaticMammalEntity) {
            super.dropFromLootTable(source, causedByPlayer)
        }
    }

    private fun getHandSwingDuration(): Int {
        return 40
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

    //#region Properties

    var moistness: Int
        get() = entityData.get(MOISTNESS)
        set(moistness) {
            entityData.set(MOISTNESS, moistness)
        }

    var size: Int
        get() = entityData.get(FISH_SIZE)
        set(size) {
            entityData.set(FISH_SIZE, size)
        }

    var hunger: Int
        get() = entityData.get(HUNGER)
        set(hunger) {
            entityData.set(HUNGER, hunger)
        }

    // endregion

    protected open fun hasSelfControl(): Boolean {
        return true
    }

    protected open fun getMinSize(): Int {
        return -5
    }

    protected open fun getMaxSize(): Int {
        return 5
    }

    //#region Animations
    override fun registerControllers(controllers: AnimatableManager.ControllerRegistrar) {
        controllers.add(
            AnimationController(this, "Fish Controller", 4) { state ->
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

    override fun getAnimatableInstanceCache(): AnimatableInstanceCache {
        return factory
    }

    // endregion

    @Suppress("DEPRECATION", "unused")
    companion object {
        val MOISTNESS: EntityDataAccessor<Int> =
            SynchedEntityData.defineId(HybridAquaticFishEntity::class.java, EntityDataSerializers.INT)
        val FISH_SIZE: EntityDataAccessor<Int> =
            SynchedEntityData.defineId(HybridAquaticFishEntity::class.java, EntityDataSerializers.INT)
        val HUNGER: EntityDataAccessor<Int> =
            SynchedEntityData.defineId(HybridAquaticFishEntity::class.java, EntityDataSerializers.INT)
        val ATTEMPT_ATTACK: EntityDataAccessor<Boolean> =
            SynchedEntityData.defineId(HybridAquaticFishEntity::class.java, EntityDataSerializers.BOOLEAN)

        val FLOP_ANIMATION: RawAnimation = RawAnimation.begin().thenPlay("misc.flop")

        val BREEDING_INGREDIENT: Ingredient = Ingredient.of(
            Items.BREAD,
        )

        const val MAX_HUNGER = 2400
        const val HUNGER_KEY = "Hunger"
        const val MOISTNESS_KEY = "Moistness"
        const val FISH_SIZE_KEY = "FishSize"

        fun canShallowSpawn(
            type: EntityType<out HybridAquaticWaterAnimal>,
            world: ServerLevelAccessor,
            reason: MobSpawnType,
            pos: BlockPos,
            random: RandomSource,
        ): Boolean {
            return pos.y in (world.seaLevel - 16)..<(world.seaLevel - 1) &&
                    world.isWaterAt(pos) &&
                    world.canSeeSkyFromBelowWater(pos)
        }

        fun canSpawn(
            type: EntityType<out HybridAquaticWaterAnimal>,
            world: ServerLevelAccessor,
            reason: MobSpawnType,
            pos: BlockPos,
            random: RandomSource,
        ): Boolean {
            return pos.y in (world.seaLevel - 32)..(world.seaLevel - 8) &&
                    world.isWaterAt(pos) &&
                    world.canSeeSkyFromBelowWater(pos)
        }

        fun canNightSpawn(
            type: EntityType<out HybridAquaticWaterAnimal>,
            world: ServerLevelAccessor,
            reason: MobSpawnType,
            pos: BlockPos,
            random: RandomSource,
        ): Boolean {
            return !world.level.isDay &&
                    return pos.y in (world.seaLevel - 32)..(world.seaLevel - 8) &&
                    world.isWaterAt(pos) &&
                    world.canSeeSkyFromBelowWater(pos)
        }

        fun canDeepSpawn(
            type: EntityType<out HybridAquaticWaterAnimal>,
            world: ServerLevelAccessor,
            reason: MobSpawnType,
            pos: BlockPos,
            random: RandomSource,
        ): Boolean {
            return pos.y in (world.seaLevel - 128)..(world.seaLevel - 48) &&
                    world.isWaterAt(pos)
        }

        fun getScaleAdjustment(fish: HybridAquaticFishEntity, adjustment: Float): Float {
            return 1.0f + (fish.size * adjustment)
        }
    }

    override fun getMaxHeadXRot(): Int {
        return 5
    }

    override fun getMaxHeadYRot(): Int {
        return 1
    }
}
