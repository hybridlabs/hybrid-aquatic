package dev.hybridlabs.aquatic.entity.shark

import dev.hybridlabs.aquatic.effect.HybridAquaticMobEffects
import dev.hybridlabs.aquatic.entity.ai.goal.SharkAttackGoal
import dev.hybridlabs.aquatic.entity.ai.goal.boids.StayInWaterGoal
import dev.hybridlabs.aquatic.entity.fish.HybridAquaticFishEntity
import net.minecraft.core.BlockPos
import net.minecraft.nbt.CompoundTag
import net.minecraft.network.syncher.EntityDataAccessor
import net.minecraft.network.syncher.EntityDataSerializers
import net.minecraft.network.syncher.SynchedEntityData
import net.minecraft.sounds.SoundEvent
import net.minecraft.sounds.SoundEvents
import net.minecraft.tags.TagKey
import net.minecraft.util.RandomSource
import net.minecraft.util.TimeUtil
import net.minecraft.util.valueproviders.UniformInt
import net.minecraft.world.DifficultyInstance
import net.minecraft.world.damagesource.DamageSource
import net.minecraft.world.entity.*
import net.minecraft.world.entity.ai.control.SmoothSwimmingLookControl
import net.minecraft.world.entity.ai.control.SmoothSwimmingMoveControl
import net.minecraft.world.entity.ai.goal.MoveTowardsTargetGoal
import net.minecraft.world.entity.ai.goal.RandomSwimmingGoal
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal
import net.minecraft.world.entity.ai.navigation.PathNavigation
import net.minecraft.world.entity.ai.navigation.WaterBoundPathNavigation
import net.minecraft.world.entity.animal.WaterAnimal
import net.minecraft.world.entity.monster.Monster.isDarkEnoughToSpawn
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
import software.bernie.geckolib.core.`object`.PlayState
import software.bernie.geckolib.util.GeckoLibUtil
import java.util.*

@Suppress("LeakingThis", "DEPRECATION", "UNUSED_PARAMETER")
open class HybridAquaticSharkEntity(
    entityType: EntityType<out HybridAquaticSharkEntity>,
    world: Level,
    private val prey: List<TagKey<EntityType<*>>>,
    private val isPassive: Boolean,
    private val closePlayerAttack: Boolean,
) : WaterAnimal(entityType, world), NeutralMob, GeoEntity {
    private val factory = GeckoLibUtil.createInstanceCache(this)
    private var angerTime = 0
    private var angryAt: UUID? = null
    var fromFishingNet = false

    var hunger: Int
        get() = entityData.get(HUNGER)
        set(hunger) {
            entityData.set(HUNGER, hunger)
        }

    private var moistness: Int
        get() = entityData.get(MOISTNESS)
        set(moistness) {
            entityData.set(MOISTNESS, moistness)
        }

    //#region Initialization
    init {
        setPathfindingMalus(BlockPathTypes.WATER, 0.0f)
        setPathfindingMalus(BlockPathTypes.WATER_BORDER, -1.0f)
        setPathfindingMalus(BlockPathTypes.WALKABLE, -1.0f)
        moveControl = SmoothSwimmingMoveControl(this, 85, 5, 1.0F, 0.1F, true)
        lookControl = SmoothSwimmingLookControl(this, 15)
        navigation = WaterBoundPathNavigation(this, world)
    }

    override fun registerGoals() {
        super.registerGoals()
        goalSelector.addGoal(0, StayInWaterGoal(this))
        goalSelector.addGoal(1, MoveTowardsTargetGoal(this, 1.5, 16.0F))
        goalSelector.addGoal(1, RandomSwimmingGoal(this, 1.0, 2))
        goalSelector.addGoal(0, SharkAttackGoal(this, 1.0, true))
        targetSelector.addGoal(1, NearestAttackableTargetGoal(this, Player::class.java, 10, true, true) { entity: LivingEntity -> isAngryAt(entity) || shouldProximityAttack(entity as Player) && !isPassive })
        targetSelector.addGoal(1, NearestAttackableTargetGoal(this, LivingEntity::class.java, 10, true, true) { it.hasEffect(HybridAquaticMobEffects.BLEEDING.get()) && it !is HybridAquaticSharkEntity && !isPassive })
        targetSelector.addGoal(1, NearestAttackableTargetGoal(this, LivingEntity::class.java, 10, true, true) { entity: LivingEntity -> prey.any { preyType -> entity.type.`is`(preyType) } && hunger < MAX_HUNGER / 4 })
    }

    override fun finalizeSpawn(
        world: ServerLevelAccessor,
        difficulty: DifficultyInstance,
        spawnReason: MobSpawnType,
        entityData: SpawnGroupData?,
        entityNbt: CompoundTag?,
    ): SpawnGroupData? {
        this.airSupply = getMaxMoistness()
        this.size = this.random.nextIntBetweenInclusive(getMinSize(), getMaxSize())
        return super.finalizeSpawn(world, difficulty, spawnReason, entityData, entityNbt)
    }

    override fun getMobType(): MobType {
        return MobType.WATER
    }

    override fun isPushedByFluid(): Boolean {
        return false
    }

    override fun createNavigation(world: Level): PathNavigation {
        return WaterBoundPathNavigation(this, world)
    }

    override fun tick() {
        super.tick()

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

        if (hunger > 0) hunger -= 1
    }

    //#endregion

    override fun removeWhenFarAway(distanceSquared: Double): Boolean {
        return !this.fromFishingNet && !this.hasCustomName()
    }

    override fun getMaxSpawnClusterSize(): Int {
        return 1
    }

    //#region NBT

    override fun addAdditionalSaveData(nbt: CompoundTag) {
        super.addAdditionalSaveData(nbt)
        this.addPersistentAngerSaveData(nbt)
        nbt.putInt(MOISTNESS_KEY, moistness)
        nbt.putInt(HUNGER_KEY, hunger)
        nbt.putInt(SHARK_SIZE_KEY, size)
        nbt.putBoolean("FromFishingNet", fromFishingNet)
    }

    override fun readAdditionalSaveData(nbt: CompoundTag) {
        super.readAdditionalSaveData(nbt)
        this.readPersistentAngerSaveData(this.level(), nbt)
        moistness = nbt.getInt(MOISTNESS_KEY)
        hunger = nbt.getInt(HUNGER_KEY)
        size = nbt.getInt(SHARK_SIZE_KEY)
        fromFishingNet = nbt.getBoolean("FromFishingNet")
    }

    override fun defineSynchedData() {
        super.defineSynchedData()
        entityData.define(MOISTNESS, getMaxMoistness())
        entityData.define(SHARK_SIZE, 0)
        entityData.define(HUNGER, MAX_HUNGER)
        entityData.define(ATTEMPT_ATTACK, false)
    }

    //#endregion


    //#region Movement

    override fun aiStep() {
        this.updateSwingTime()
        super.aiStep()
    }

    override fun getMaxHeadXRot(): Int {
        return 1
    }

    override fun getMaxHeadYRot(): Int {
        return 1
    }

    //#endregion

    //#region Size & Dimensions
    var size: Int
        get() = entityData.get(SHARK_SIZE)
        set(size) {
            entityData.set(SHARK_SIZE, size)
        }

    protected open fun getMinSize(): Int {
        return -3
    }

    protected open fun getMaxSize(): Int {
        return 0
    }

    override fun getStandingEyeHeight(pose: Pose, dimensions: EntityDimensions): Float {
        return dimensions.height * 0.65f
    }

    //#endregion

    //#region Water Breathing

    private fun getMaxMoistness(): Int {
        return 1200
    }

    //#endregion

    //#region Animations
    override fun registerControllers(controllerRegistrar: AnimatableManager.ControllerRegistrar) {
        controllerRegistrar.add(
            AnimationController(
                this, "Swim", 4,
                AnimationController.AnimationStateHandler { state: AnimationState<HybridAquaticSharkEntity> ->
                    if (this.isUnderWater) {
                        return@AnimationStateHandler state.setAndContinue(DefaultAnimations.SWIM)
                    } else {
                        PlayState.STOP
                    }
                }
            )
        )

        controllerRegistrar.add(
            AnimationController(
                this, "Charge", 8,
                AnimationController.AnimationStateHandler { state: AnimationState<HybridAquaticSharkEntity> ->
                    if (this.isUnderWater && this.isSprinting) {
                        return@AnimationStateHandler state.setAndContinue(DefaultAnimations.RUN)
                    } else {
                        PlayState.STOP
                    }
                }
            )
        )

        controllerRegistrar.add(
            AnimationController(
                this, "Beached", 4,
                AnimationController.AnimationStateHandler { state: AnimationState<HybridAquaticSharkEntity> ->
                    if (this.onGround() && !this.isUnderWater) {
                        return@AnimationStateHandler state.setAndContinue(BEACHED)
                    } else {
                        PlayState.STOP
                    }
                }
            )
        )

        controllerRegistrar.add(
            DefaultAnimations.genericAttackAnimation(this, DefaultAnimations.ATTACK_BITE)
        )
    }

    override fun getAnimatableInstanceCache(): AnimatableInstanceCache {
        return factory
    }

    //#endregion

    //#region SFX
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

    override fun dropFromLootTable(source: DamageSource, causedByPlayer: Boolean) {
        val attacker = source.directEntity
        if (attacker !is HybridAquaticFishEntity && attacker !is HybridAquaticSharkEntity) {
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
            playSound(SoundEvents.FOX_BITE, 1.0F, 0.0F)
            return true
        } else {
            return false
        }
    }

    companion object {
        const val MOISTNESS_KEY = "Moistness"
        const val SHARK_SIZE_KEY = "SharkSize"
        const val MAX_HUNGER = 2400
        const val HUNGER_KEY = "Hunger"

        val SHARK_SIZE: EntityDataAccessor<Int> =
            SynchedEntityData.defineId(HybridAquaticSharkEntity::class.java, EntityDataSerializers.INT)
        val MOISTNESS: EntityDataAccessor<Int> =
            SynchedEntityData.defineId(HybridAquaticSharkEntity::class.java, EntityDataSerializers.INT)
        val HUNGER: EntityDataAccessor<Int> =
            SynchedEntityData.defineId(HybridAquaticSharkEntity::class.java, EntityDataSerializers.INT)
        val ATTEMPT_ATTACK: EntityDataAccessor<Boolean> =
            SynchedEntityData.defineId(HybridAquaticSharkEntity::class.java, EntityDataSerializers.BOOLEAN)
        val ANGER_TIME_RANGE: UniformInt = TimeUtil.rangeOfSeconds(10, 30)
        val BEACHED: RawAnimation = RawAnimation.begin().thenPlay("misc.beached")

        //#region Spawning
        fun canShallowSpawn(
            type: EntityType<out WaterAnimal>,
            world: ServerLevelAccessor,
            reason: MobSpawnType,
            pos: BlockPos,
            random: RandomSource,
        ): Boolean {
            val topY = world.seaLevel - 2
            val bottomY = world.seaLevel - 6

            return pos.y in bottomY..topY &&
                    world.isWaterAt(pos) &&
                    world.canSeeSkyFromBelowWater(pos)
        }

        fun canSpawn(
            type: EntityType<out WaterAnimal>,
            world: ServerLevelAccessor,
            reason: MobSpawnType,
            pos: BlockPos,
            random: RandomSource,
        ): Boolean {
            val topY = world.seaLevel - 8
            val bottomY = world.seaLevel - 24

            return pos.y in bottomY..topY &&
                    world.isWaterAt(pos)
        }

        @Suppress("UNUSED_PARAMETER", "DEPRECATION")
        fun canDeepSpawn(
            type: EntityType<out WaterAnimal>,
            world: ServerLevelAccessor,
            reason: MobSpawnType,
            pos: BlockPos,
            random: RandomSource,
        ): Boolean {
            val topY = world.seaLevel - 28
            val bottomY = world.seaLevel - 128

            return pos.y in bottomY..topY &&
                    world.isWaterAt(pos) &&
                    isDarkEnoughToSpawn(world, pos, random)
        }

        fun getScaleAdjustment(shark: HybridAquaticSharkEntity, adjustment: Float): Float {
            return 1.0f + (shark.size * adjustment)
        }
        //#endregion
    }
}