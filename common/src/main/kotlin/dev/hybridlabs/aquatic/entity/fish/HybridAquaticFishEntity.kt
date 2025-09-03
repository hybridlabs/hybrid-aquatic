package dev.hybridlabs.aquatic.entity.fish

import dev.hybridlabs.aquatic.entity.cephalopod.HybridAquaticCephalopodEntity
import dev.hybridlabs.aquatic.entity.fish.HybridAquaticFishEntity.VariantCollisionRules.ExclusionStatus.EXCLUSIVE
import dev.hybridlabs.aquatic.entity.fish.HybridAquaticFishEntity.VariantCollisionRules.ExclusionStatus.INCLUSIVE
import dev.hybridlabs.aquatic.entity.shark.HybridAquaticSharkEntity
import net.minecraft.core.BlockPos
import net.minecraft.nbt.CompoundTag
import net.minecraft.network.syncher.EntityDataAccessor
import net.minecraft.network.syncher.EntityDataSerializers
import net.minecraft.network.syncher.SynchedEntityData
import net.minecraft.resources.ResourceLocation
import net.minecraft.sounds.SoundEvent
import net.minecraft.sounds.SoundEvents
import net.minecraft.tags.FluidTags
import net.minecraft.tags.TagKey
import net.minecraft.util.RandomSource
import net.minecraft.world.DifficultyInstance
import net.minecraft.world.damagesource.DamageSource
import net.minecraft.world.entity.*
import net.minecraft.world.entity.ai.control.SmoothSwimmingLookControl
import net.minecraft.world.entity.ai.control.SmoothSwimmingMoveControl
import net.minecraft.world.entity.ai.goal.*
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal
import net.minecraft.world.entity.ai.navigation.PathNavigation
import net.minecraft.world.entity.ai.navigation.WaterBoundPathNavigation
import net.minecraft.world.entity.animal.WaterAnimal
import net.minecraft.world.entity.monster.Guardian
import net.minecraft.world.entity.monster.Monster.isDarkEnoughToSpawn
import net.minecraft.world.entity.player.Player
import net.minecraft.world.level.Level
import net.minecraft.world.level.LevelAccessor
import net.minecraft.world.level.ServerLevelAccessor
import net.minecraft.world.level.biome.Biome
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.pathfinder.BlockPathTypes
import software.bernie.geckolib.animatable.GeoEntity
import software.bernie.geckolib.constant.DefaultAnimations
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache
import software.bernie.geckolib.core.animation.AnimatableManager
import software.bernie.geckolib.core.animation.AnimationController
import software.bernie.geckolib.core.animation.AnimationState
import software.bernie.geckolib.util.GeckoLibUtil

@Suppress("LeakingThis", "UNUSED_PARAMETER")
open class HybridAquaticFishEntity(
    type: EntityType<out HybridAquaticFishEntity>,
    world: Level,
    private val variants: Map<String, FishVariant> = mutableMapOf(),
    open val prey: List<TagKey<EntityType<*>>>,
    open val predator: List<TagKey<EntityType<*>>>,
    open val assumeDefault: Boolean = false,
    open val collisionRules: List<VariantCollisionRules> = listOf()
) : WaterAnimal(type, world), GeoEntity {

    private val factory = GeckoLibUtil.createInstanceCache(this)

    override fun registerGoals() {
        super.registerGoals()
        goalSelector.addGoal(0, TryFindWaterGoal(this))
        goalSelector.addGoal(1, RandomSwimmingGoal(this, 1.0, 10))
        goalSelector.addGoal(1, RandomLookAroundGoal(this))
        goalSelector.addGoal(2, LookAtPlayerGoal(this, Player::class.java, 6.0f))
        goalSelector.addGoal(3, AvoidEntityGoal(this, Guardian::class.java, 8.0f, 1.0, 1.0))
        goalSelector.addGoal(3, AvoidEntityGoal(this, Player::class.java, 8.0f, 1.0, 1.0))
        goalSelector.addGoal(4, FishAttackGoal(this))
        targetSelector.addGoal(1, NearestAttackableTargetGoal(this, LivingEntity::class.java, 10, true, true) { entity: LivingEntity -> prey.any { preyType -> entity.type.`is`(preyType) } && hunger < MAX_HUNGER / 4 })
    }

    override fun defineSynchedData() {
        super.defineSynchedData()
        entityData.define(MOISTNESS, getMaxMoistness())
        entityData.define(FISH_SIZE, 0)
        entityData.define(ATTEMPT_ATTACK, false)
        entityData.define(HUNGER, MAX_HUNGER)
        entityData.define(VARIANT, "")
        entityData.define(VARIANT_DATA, CompoundTag())
    }

    override fun finalizeSpawn(
        world: ServerLevelAccessor,
        difficulty: DifficultyInstance,
        spawnReason: MobSpawnType,
        entityData: SpawnGroupData?,
        entityNbt: CompoundTag?
    ): SpawnGroupData? {
        this.airSupply = getMaxMoistness()
        xRot = 0.0f
        yRot = 0.0f
        this.size = this.random.nextIntBetweenInclusive(getMinSize(),getMaxSize())

        if (variants.isNotEmpty()) {
            if (spawnReason == MobSpawnType.SPAWN_EGG) {
                variantKey = variants.keys.elementAt(random.nextIntBetweenInclusive(0, variants.size - 1))
            } else {
                // Handle collisions
                val validKeys = variants.filter { it.value.spawnCondition(world, spawnReason, blockPosition(), random) }.map { it.key }

                if (validKeys.isEmpty()) {
                    variantKey = variants.keys.random()
                } else if (collisionRules.isNotEmpty()) {
                    for (rule in collisionRules) {
                        val variantSet = rule.variants.toSet()
                        if ((rule.exclusionStatus == EXCLUSIVE && validKeys.toSet() == variantSet) ||
                            (rule.exclusionStatus == INCLUSIVE && validKeys.containsAll(variantSet))) {
                            variantKey = rule.collisionHandler(validKeys.toSet(), random, world)
                            break
                        }
                    }
                } else {
                    // Default to a priority based system
                    val validityFilter = variants.filter { validKeys.contains(it.key) }
                    variantKey = if (validityFilter.isNotEmpty()) {
                        val maxPriority = validityFilter.values.maxOf { it.priority }
                        val filteredMap = validityFilter.filter { it.value.priority == maxPriority }
                        if (filteredMap.isNotEmpty()) {
                            filteredMap.keys.random()
                        } else {
                            validKeys.random()
                        }
                    } else {
                        validKeys.random()
                    }
                }
            }
        }

        this.size = this.random.nextIntBetweenInclusive(getMinSize(), getMaxSize())
        return super.finalizeSpawn(world, difficulty, spawnReason, entityData, entityNbt)
    }

    override fun createNavigation(world: Level): PathNavigation{
        return WaterBoundPathNavigation(this, world)
    }

    override fun tick() {
        super.tick()
        if (isNoAi) {
            return
        }

        if (isUnderWater) {
            moistness = getMaxMoistness()
        } else {
            moistness = moistness.minus(1)
            if (moistness <= -20) {
                moistness = 0
                hurt(this.damageSources().dryOut(), 1.0f)
            }
        }
    }

    override fun aiStep() {
        if (!this.isInWater && this.onGround() && this.verticalCollision) {
            this.deltaMovement = deltaMovement.add(
                ((random.nextFloat() * 2.0f - 1.0f) * 0.05f).toDouble(), 0.4000000059604645,
                ((random.nextFloat() * 2.0f - 1.0f) * 0.05f).toDouble()
            )
            this.setOnGround(false)
            this.hasImpulse = true
            this.playSound(this.flopSound, this.soundVolume, this.voicePitch)
        }

        super.aiStep()
    }

    override fun dropFromLootTable(source: DamageSource, causedByPlayer: Boolean) {
        val attacker = source.directEntity
        if (attacker !is HybridAquaticFishEntity && attacker !is HybridAquaticSharkEntity && attacker !is HybridAquaticCephalopodEntity) {
            super.dropFromLootTable(source, causedByPlayer)
        }
    }

    override fun getDefaultLootTable(): ResourceLocation {
        return if (variant != null) {
            super.getDefaultLootTable().withPath { path -> "${path}_${variant!!.variantName}" }
        } else {
            super.getDefaultLootTable()
        }
    }

    override fun handleAirSupply(air: Int) {}

    private fun getMaxMoistness(): Int {
        return 600
    }

    open fun shouldFlopOnLand(): Boolean {
        return true
    }

    override fun addAdditionalSaveData(nbt: CompoundTag) {
        super.addAdditionalSaveData(nbt)
        nbt.putInt(MOISTNESS_KEY, moistness)
        nbt.putString(VARIANT_KEY, variantKey)
        nbt.put(VARIANT_DATA_KEY, variantData)
        nbt.putInt(FISH_SIZE_KEY, size)
        nbt.putInt(HUNGER_KEY, hunger)
        nbt.putBoolean("FromFishingNet", fromFishingNet)
    }

    private var fromFishingNet = false

    override fun readAdditionalSaveData(nbt: CompoundTag) {
        super.readAdditionalSaveData(nbt)
        moistness = nbt.getInt(MOISTNESS_KEY)
        variantKey = nbt.getString(VARIANT_KEY)
        variantData = nbt.getCompound(VARIANT_DATA_KEY)
        size = nbt.getInt(FISH_SIZE_KEY)
        hunger = nbt.getInt(HUNGER_KEY)
        fromFishingNet = nbt.getBoolean("FromFishingNet")
    }

    override fun getStandingEyeHeight(pose: Pose, dimensions: EntityDimensions): Float {
        return dimensions.height * 0.65f
    }

    override fun removeWhenFarAway(distanceSquared: Double): Boolean {
        return !this.fromFishingNet && !this.hasCustomName()
    }

    override fun getMaxSpawnClusterSize(): Int {
        return 8
    }

    //#region SFX
    open val flopSound: SoundEvent = SoundEvents.PUFFER_FISH_FLOP

    override fun getSwimSound(): SoundEvent {
        return SoundEvents.DOLPHIN_SWIM
    }

    override fun getHurtSound(source: DamageSource): SoundEvent {
        return SoundEvents.COD_HURT
    }

    override fun getDeathSound(): SoundEvent {
        return SoundEvents.COD_DEATH
    }

    override fun getAmbientSound(): SoundEvent {
        return SoundEvents.TROPICAL_FISH_AMBIENT
    }

    override fun getSwimSplashSound(): SoundEvent {
        return SoundEvents.DOLPHIN_SPLASH
    }

    //#region end

    //#region Properties

    private var moistness: Int
        get() = entityData.get(MOISTNESS)!!
        set(moistness) {
            if (MOISTNESS != null) {
                entityData.set(MOISTNESS, moistness)
            }
        }

    var size: Int
        get() = entityData.get(FISH_SIZE)
        set(size) {
            entityData.set(FISH_SIZE, size)
        }

    private var hunger: Int
        get() = entityData.get(HUNGER)
        set(hunger) {
            entityData.set(HUNGER, hunger)
        }

    private var attemptAttack: Boolean
        get() = entityData.get(ATTEMPT_ATTACK)
        set(attemptAttack) {
            entityData.set(ATTEMPT_ATTACK, attemptAttack)
        }

    private var variantData: CompoundTag
        get() = entityData.get(VARIANT_DATA)
        set(value) {
            entityData.set(VARIANT_DATA, value)
        }

    private var variantKey: String
        get() = entityData.get(VARIANT).ifBlank {
            if (!assumeDefault && variants.isNotEmpty()) {
                variants.isNotEmpty()
            }
            entityData.get(VARIANT)
        }
        set(value) {
            entityData.set(VARIANT, value)
        }

    @Suppress("UNUSED_PARAMETER")
    var variant: FishVariant?
        get() = variants[variantKey]
        private set(value) {}

    // endregion

    override fun increaseAirSupply(air: Int): Int {
        return this.maxAirSupply
    }

    protected open fun hasSelfControl(): Boolean {
        return true
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
            AnimationController(this, "Swim/Idle", 5,
                AnimationController.AnimationStateHandler { state: AnimationState<HybridAquaticFishEntity> ->
                    if (state.isMoving) {
                        return@AnimationStateHandler state.setAndContinue(DefaultAnimations.SWIM)
                    } else {
                        return@AnimationStateHandler state.setAndContinue(DefaultAnimations.IDLE)
                    }
                })
        )
    }

    override fun getAnimatableInstanceCache(): AnimatableInstanceCache {
        return factory
    }

    // endregion

    init {
        setPathfindingMalus(BlockPathTypes.WATER, 0.0f)
        setPathfindingMalus(BlockPathTypes.WALKABLE, 10.0f)
        moveControl = SmoothSwimmingMoveControl(this, 75, 5, speed, 0.1f, true)
        lookControl = SmoothSwimmingLookControl(this, 10)
        navigation = WaterBoundPathNavigation(this, world)
    }

    open fun speedModifier(): Double {
        return 0.0
    }

    internal class FishAttackGoal(private val fish: HybridAquaticFishEntity) : MeleeAttackGoal(fish, 1.0,true) {
        override fun canUse(): Boolean {
            return !fish.fromFishingNet && super.canUse()
        }

        override fun checkAndPerformAttack(target: LivingEntity, squaredDistance: Double) {
            val d = getAttackReachSqr(target)
            if (squaredDistance <= d && this.isTimeToAttack) {
                resetAttackCooldown()
                mob.doHurtTarget(target)
                fish.isSprinting = true
                fish.attemptAttack = true

                if (target.health <= 0)
                    fish.hunger = MAX_HUNGER
                fish.health = fish.maxHealth
            }
        }
    }

    companion object {
        val MOISTNESS: EntityDataAccessor<Int?>? = SynchedEntityData.defineId(HybridAquaticFishEntity::class.java, EntityDataSerializers.INT)
        val FISH_SIZE: EntityDataAccessor<Int> = SynchedEntityData.defineId(HybridAquaticFishEntity::class.java, EntityDataSerializers.INT)
        val HUNGER: EntityDataAccessor<Int> = SynchedEntityData.defineId(HybridAquaticFishEntity::class.java, EntityDataSerializers.INT)
        val ATTEMPT_ATTACK: EntityDataAccessor<Boolean> = SynchedEntityData.defineId(HybridAquaticFishEntity::class.java, EntityDataSerializers.BOOLEAN)
        val VARIANT: EntityDataAccessor<String> = SynchedEntityData.defineId(HybridAquaticFishEntity::class.java, EntityDataSerializers.STRING)
        var VARIANT_DATA: EntityDataAccessor<CompoundTag> = SynchedEntityData.defineId(HybridAquaticFishEntity::class.java, EntityDataSerializers.COMPOUND_TAG)

        const val MAX_HUNGER = 2400
        const val HUNGER_KEY = "Hunger"
        const val MOISTNESS_KEY = "Moistness"
        const val VARIANT_KEY = "Variant"
        const val VARIANT_DATA_KEY = "VariantData"
        const val FISH_SIZE_KEY = "FishSize"

        @Suppress("UNUSED_PARAMETER", "DEPRECATION")
        fun canSpawn(
            type: EntityType<out WaterAnimal>,
            world: ServerLevelAccessor,
            reason: MobSpawnType,
            pos: BlockPos,
            random: RandomSource
        ): Boolean {
            val topY = world.seaLevel - 4
            val bottomY = world.seaLevel - 24

            return pos.y in bottomY..topY &&
                    world.getFluidState(pos).`is`(FluidTags.WATER) &&
                    world.getFluidState(pos.below()).`is`(FluidTags.WATER) &&
                    world.getBlockState(pos.above()).`is`(Blocks.WATER) &&
                    world.canSeeSkyFromBelowWater(pos) &&
                    !isDarkEnoughToSpawn(world, pos, random)
        }

        @Suppress("UNUSED_PARAMETER", "DEPRECATION")
        fun canUndergroundSpawn(
            type: EntityType<out WaterAnimal>,
            world: ServerLevelAccessor,
            reason: MobSpawnType,
            pos: BlockPos,
            random: RandomSource
        ): Boolean {
            val topY = world.seaLevel - 24
            val bottomY = world.seaLevel - 128

            return pos.y in bottomY..topY &&
                    world.getFluidState(pos).`is`(FluidTags.WATER) &&
                    world.getFluidState(pos.below()).`is`(FluidTags.WATER) &&
                    world.getBlockState(pos.above()).`is`(Blocks.WATER) &&
                    isDarkEnoughToSpawn(world, pos, random)
        }

        fun getScaleAdjustment(fish: HybridAquaticFishEntity, adjustment: Float): Float {
            return 1.0f + (fish.size * adjustment)
        }
    }

    override fun getMaxHeadXRot(): Int {
        return 1
    }

    override fun getMaxHeadYRot(): Int {
        return 1
    }

    @Suppress("UNUSED")
    data class FishVariant(
        val variantName : String,
        val spawnCondition: (LevelAccessor, MobSpawnType, BlockPos, RandomSource ) -> Boolean,
        val ignore: List<Ignore> = emptyList(),
        val priority: Int = 0,
        var providedVariant: (Level, BlockPos, RandomSource, HybridAquaticFishEntity) -> String = {_,_,_,_ ->
            variantName
        }
    ) {

        fun getProvidedVariant(fish: HybridAquaticFishEntity) : String {
            return providedVariant(fish.level(), fish.blockPosition(), fish.random, fish)
        }

        companion object {
            /**
             * Creates a biome variant of a fish
             */
            fun biomeVariant(variantName: String, biomes : List<TagKey<Biome>>, ignore : List<Ignore> = emptyList()): FishVariant {
                return FishVariant(variantName, { world, _, pos, _ ->
                    val biome = world.getBiome(pos)
                    biomes.any { biome.`is`(it) }
                }, ignore)
            }
        }

        enum class Ignore {
            TEXTURE,
            MODEL,
            ANIMATION
        }
    }

    @Suppress("UNUSED")
    data class VariantCollisionRules(val variants : Set<String>, val collisionHandler: (Set<String>, RandomSource, ServerLevelAccessor) -> String, val exclusionStatus: ExclusionStatus = INCLUSIVE) {

        /**
         * INCLUSIVE - all other variants can exist within this selection swath
         * <pre> </pre>
         * EXCLUSIVE - all other variants are excluded from this selection swath
         */
        enum class ExclusionStatus {
            INCLUSIVE,
            EXCLUSIVE
        }

        /**
         * <pre></pre>
         * Example:
         * ```kotlin
         * // returns a bluefin or a yellowfin tuna variant
         * equalDistribution(setOf("bluefin", "yellowfin"))
         * ```
         * @return a random variant within the set
         */
        fun equalDistribution(variants: Set<String>, status : ExclusionStatus = INCLUSIVE) : VariantCollisionRules {
            return VariantCollisionRules(variants, { possibleVariants, _, _ ->
                possibleVariants.random()
            }, status)
        }

        /**
         * Example
         * ```
         * weightedDistribution(setOf(
         *  Pair("bluefin", 0.80),
         *  Pair("yellowfin", 0.20)
         * ))
         * ```
         * @return a premade variant collision rule which allows weighted distribution of variants.
         */
        fun weightedDistribution(weights: Set<Pair<String, Double>>, status: ExclusionStatus = EXCLUSIVE) : VariantCollisionRules {
            return VariantCollisionRules(weights.map { pair -> pair.first }.toSet(), { _, random, _ ->
                // sum up weights
                val weightTotal = weights.sumOf { pair -> pair.second }
                val randomVal = random.nextFloat() * weightTotal
                var accumulatedWeight = 0.0
                var result = ""

                for (pair in weights) {
                    accumulatedWeight += pair.second
                    if (randomVal < accumulatedWeight) {
                        result = pair.first
                        break
                    }
                }

                result
            }, status)
        }
    }
}