package dev.hybridlabs.aquatic.entity.critter

import dev.hybridlabs.aquatic.entity.critter.HybridAquaticCritterEntity.VariantCollisionRules.ExclusionStatus.EXCLUSIVE
import dev.hybridlabs.aquatic.entity.critter.HybridAquaticCritterEntity.VariantCollisionRules.ExclusionStatus.INCLUSIVE
import net.minecraft.core.BlockPos
import net.minecraft.nbt.CompoundTag
import net.minecraft.network.syncher.EntityDataAccessor
import net.minecraft.network.syncher.EntityDataSerializers
import net.minecraft.network.syncher.SynchedEntityData
import net.minecraft.sounds.SoundEvent
import net.minecraft.sounds.SoundEvents
import net.minecraft.tags.FluidTags
import net.minecraft.tags.TagKey
import net.minecraft.util.RandomSource
import net.minecraft.world.DifficultyInstance
import net.minecraft.world.damagesource.DamageSource
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.MobSpawnType
import net.minecraft.world.entity.SpawnGroupData
import net.minecraft.world.entity.ai.control.MoveControl
import net.minecraft.world.entity.ai.goal.PanicGoal
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal
import net.minecraft.world.entity.ai.goal.RandomStrollGoal
import net.minecraft.world.entity.ai.goal.TryFindWaterGoal
import net.minecraft.world.entity.ai.navigation.GroundPathNavigation
import net.minecraft.world.entity.animal.WaterAnimal
import net.minecraft.world.level.Level
import net.minecraft.world.level.LevelAccessor
import net.minecraft.world.level.ServerLevelAccessor
import net.minecraft.world.level.biome.Biome
import net.minecraft.world.level.pathfinder.BlockPathTypes
import software.bernie.geckolib.animatable.GeoEntity
import software.bernie.geckolib.core.animatable.GeoAnimatable
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache
import software.bernie.geckolib.core.animation.*
import software.bernie.geckolib.core.`object`.PlayState
import software.bernie.geckolib.util.GeckoLibUtil


@Suppress("LeakingThis", "DEPRECATION", "UNUSED_PARAMETER")
open class HybridAquaticCritterEntity(
    type: EntityType<out HybridAquaticCritterEntity>,
    world: Level,
    private val variants: Map<String, CritterVariant> = mutableMapOf(),
    open val assumeDefault: Boolean = false,
    open val collisionRules: List<VariantCollisionRules> = listOf()
) : WaterAnimal(type, world), GeoEntity {
    private val factory = GeckoLibUtil.createInstanceCache(this)
    private var fromFishingNet = false
    private var climbingTicks = 0

    init {
        setPathfindingMalus(BlockPathTypes.WATER, 0.0f)
        setPathfindingMalus(BlockPathTypes.WALKABLE, 10.0f)
        moveControl = MoveControl(this)
        navigation = GroundPathNavigation(this, world)
    }

    override fun shouldDiscardFriction(): Boolean {
        return this.onClimbable()
    }

    override fun tick() {
        super.tick()

        if (!isInWaterRainOrBubble) {
            this.speed = 0.01F
        }

        if (!this.level().isClientSide) {
            this.setClimbingWall(this.horizontalCollision)
        }

        if (this.isClimbingWall()) {
            this.climbingTicks++

            val blockStateAtPos = this.feetBlockState
            if (this.isMoving() && !blockStateAtPos.liquid() && this.climbingTicks % 6 == 0) {
                this.playStepSound(this.blockPosition(), blockStateAtPos)
            }
        } else {
            this.climbingTicks = 0
        }

        if (this.onClimbable()) {
            val velocity = this.deltaMovement
            this.setDeltaMovement(velocity.x, velocity.y * 0.33F, velocity.z)
        }
    }

    private fun isMoving(): Boolean {
        return (this.onGround() || this.onClimbable()) && deltaMovement.horizontalDistanceSqr() >= 0.0001
    }

    override fun onClimbable(): Boolean {
        return this.climbingTicks > 8 && this.isClimbingWall()
    }

    private fun isClimbingWall(): Boolean {
        return entityData.get(IS_CLIMBING_WALL)
    }

    private fun setClimbingWall(isClimbingWall: Boolean) {
        entityData.set(IS_CLIMBING_WALL, isClimbingWall)
    }

    override fun defineSynchedData() {
        super.defineSynchedData()
        entityData.define(VARIANT, "")
        entityData.define(VARIANT_DATA, CompoundTag())
        entityData.define(CRITTER_SIZE, 0)
        entityData.define(CRITTER_FLAGS, 0.toByte())
        entityData.define(IS_CLIMBING_WALL, false)
    }

    override fun registerGoals() {
        super.registerGoals()
        goalSelector.addGoal(2, TryFindWaterGoal(this))
        goalSelector.addGoal(3, PanicGoal(this, 0.35))
        goalSelector.addGoal(5, RandomStrollGoal(this, 0.35, 10))
        goalSelector.addGoal(5, RandomLookAroundGoal(this))
    }

    override fun finalizeSpawn(
        world: ServerLevelAccessor,
        difficulty: DifficultyInstance,
        spawnReason: MobSpawnType,
        entityData: SpawnGroupData?,
        entityNbt: CompoundTag?
    ): SpawnGroupData? {
        this.airSupply = this.maxAirSupply

        if (variants.isNotEmpty()) {
            if (spawnReason == MobSpawnType.SPAWN_EGG) {
                variantKey = variants.keys.elementAt(random.nextIntBetweenInclusive(0, variants.size - 1))
            } else {
                // Handle collisions
                val validKeys =
                    variants.filter { it.value.spawnCondition(world, spawnReason, blockPosition(), random) }
                        .map { it.key }

                if (validKeys.isEmpty()) {
                    variantKey = variants.keys.random()
                } else if (collisionRules.isNotEmpty()) {
                    for (rule in collisionRules) {
                        val variantSet = rule.variants.toSet()
                        if ((rule.exclusionStatus == EXCLUSIVE && validKeys.toSet() == variantSet) ||
                            (rule.exclusionStatus == INCLUSIVE && validKeys.containsAll(
                                variantSet
                            ))
                        ) {
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

    override fun isAffectedByFluids(): Boolean {
        return !onGround() || !onClimbable()
    }

    override fun isPushedByFluid(): Boolean {
        return false
    }

    override fun addAdditionalSaveData(nbt: CompoundTag) {
        super.addAdditionalSaveData(nbt)
        nbt.putString(VARIANT_KEY, variantKey)
        nbt.put(VARIANT_DATA_KEY, variantData)
        nbt.putInt(CRITTER_SIZE_KEY, size)
        nbt.putBoolean("FromFishingNet", fromFishingNet)
    }

    override fun readAdditionalSaveData(nbt: CompoundTag) {
        super.readAdditionalSaveData(nbt)
        variantKey = nbt.getString(VARIANT_KEY)
        variantData = nbt.getCompound(VARIANT_DATA_KEY)
        size = nbt.getInt(CRITTER_SIZE_KEY)
        fromFishingNet = nbt.getBoolean("FromFishingNet")
    }

    override fun handleAirSupply(air: Int) {}

    open fun <E : GeoAnimatable> predicate(event: AnimationState<E>): PlayState {
        if (isUnderWater && event.isMoving) {
            event.controller.setAnimation(WALK_ANIMATION)
            return PlayState.CONTINUE

        }

        if (!isUnderWater) {
            event.controller.setAnimation(FLOP_ANIMATION)
            return PlayState.CONTINUE

        }

        if (isUnderWater && !event.isMoving) {
            event.controller.setAnimation(IDLE_ANIMATION)
            return PlayState.CONTINUE
        }
        return PlayState.CONTINUE
    }

    protected open fun getMinSize(): Int {
        return 0
    }

    protected open fun getMaxSize(): Int {
        return 0
    }

    override fun removeWhenFarAway(distanceSquared: Double): Boolean {
        return !fromFishingNet && !hasCustomName()
    }

    override fun getMaxSpawnClusterSize(): Int {
        return 4
    }

    override fun getHurtSound(source: DamageSource): SoundEvent {
        return SoundEvents.SLIME_HURT
    }

    override fun getDeathSound(): SoundEvent {
        return SoundEvents.SLIME_DEATH_SMALL
    }

    override fun getAmbientSound(): SoundEvent {
        return SoundEvents.TROPICAL_FISH_AMBIENT
    }

    override fun getSwimSound(): SoundEvent {
        return SoundEvents.SLIME_JUMP_SMALL
    }

    override fun registerControllers(controllerRegistrar: AnimatableManager.ControllerRegistrar) {
        controllerRegistrar.add(
            AnimationController(
                this,
                "controller",
                5,
                ::predicate
            )
        )
    }

    override fun getAnimatableInstanceCache(): AnimatableInstanceCache {
        return factory
    }

    override fun canBreatheUnderwater(): Boolean {
        return true
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
    var variant: CritterVariant?
        get() = variants[variantKey]
        private set(value) {}

    var size: Int
        get() = entityData.get(CRITTER_SIZE)
        set(size) {
            entityData.set(CRITTER_SIZE, size)
        }

    companion object {
        val VARIANT: EntityDataAccessor<String> =
            SynchedEntityData.defineId(HybridAquaticCritterEntity::class.java, EntityDataSerializers.STRING)
        var VARIANT_DATA: EntityDataAccessor<CompoundTag> =
            SynchedEntityData.defineId(HybridAquaticCritterEntity::class.java, EntityDataSerializers.COMPOUND_TAG)
        val CRITTER_SIZE: EntityDataAccessor<Int> =
            SynchedEntityData.defineId(HybridAquaticCritterEntity::class.java, EntityDataSerializers.INT)
        val CRITTER_FLAGS: EntityDataAccessor<Byte> =
            SynchedEntityData.defineId(HybridAquaticCritterEntity::class.java, EntityDataSerializers.BYTE)
        val IS_CLIMBING_WALL: EntityDataAccessor<Boolean> =
            SynchedEntityData.defineId(HybridAquaticCritterEntity::class.java, EntityDataSerializers.BOOLEAN)

        fun canSpawn(
            type: EntityType<out WaterAnimal>,
            world: ServerLevelAccessor,
            reason: MobSpawnType,
            pos: BlockPos,
            random: RandomSource
        ): Boolean {
            val topY = world.seaLevel
            val bottomY = world.seaLevel - 128

            return pos.y in bottomY..topY &&
                    world.getBlockState(pos.below()).isSolid &&
                    world.getFluidState(pos).`is`(FluidTags.WATER)
        }

        fun getScaleAdjustment(critter: HybridAquaticCritterEntity, adjustment: Float): Float {
            return 1.0f + (critter.size * adjustment)
        }

        const val VARIANT_KEY = "Variant"
        const val VARIANT_DATA_KEY = "VariantData"
        const val CRITTER_SIZE_KEY = "CritterSize"

        val WALK_ANIMATION: RawAnimation = RawAnimation.begin().then("walk", Animation.LoopType.LOOP)
        val IDLE_ANIMATION: RawAnimation = RawAnimation.begin().then("idle", Animation.LoopType.LOOP)
        val FLOP_ANIMATION: RawAnimation = RawAnimation.begin().then("flop", Animation.LoopType.LOOP)
    }

    @Suppress("UNUSED")
    data class CritterVariant(
        val variantName: String,
        val spawnCondition: (LevelAccessor, MobSpawnType, BlockPos, RandomSource) -> Boolean,
        val ignore: List<Ignore> = emptyList(),
        val priority: Int = 0,
        var providedVariant: (Level, BlockPos, RandomSource, HybridAquaticCritterEntity) -> String = { _, _, _, _ ->
            variantName
        }
    ) {

        fun getProvidedVariant(critter: HybridAquaticCritterEntity): String {
            return providedVariant(critter.level(), critter.blockPosition(), critter.random, critter)
        }

        companion object {

            fun biomeVariant(
                variantName: String,
                biomes: List<TagKey<Biome>>,
                ignore: List<Ignore> = emptyList()
            ): CritterVariant {
                return CritterVariant(variantName, { world, _, pos, _ ->
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
    data class VariantCollisionRules(
        val variants: Set<String>,
        val collisionHandler: (Set<String>, RandomSource, ServerLevelAccessor) -> String,
        val exclusionStatus: ExclusionStatus = INCLUSIVE
    ) {
        enum class ExclusionStatus {
            INCLUSIVE,
            EXCLUSIVE
        }

        fun equalDistribution(variants: Set<String>, status: ExclusionStatus = INCLUSIVE): VariantCollisionRules {
            return VariantCollisionRules(variants, { possibleVariants, _, _ ->
                possibleVariants.random()
            }, status)
        }

        fun weightedDistribution(
            weights: Set<Pair<String, Double>>,
            status: ExclusionStatus = EXCLUSIVE
        ): VariantCollisionRules {
            return VariantCollisionRules(weights.map { pair -> pair.first }.toSet(), { _, random, _ ->
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
