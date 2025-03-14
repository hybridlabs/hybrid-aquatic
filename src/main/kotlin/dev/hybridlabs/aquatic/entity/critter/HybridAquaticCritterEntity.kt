package dev.hybridlabs.aquatic.entity.critter

import dev.hybridlabs.aquatic.entity.critter.HybridAquaticCritterEntity.VariantCollisionRules.ExclusionStatus.EXCLUSIVE
import dev.hybridlabs.aquatic.entity.critter.HybridAquaticCritterEntity.VariantCollisionRules.ExclusionStatus.INCLUSIVE
import net.minecraft.entity.EntityData
import net.minecraft.entity.EntityType
import net.minecraft.entity.SpawnReason
import net.minecraft.entity.ai.control.MoveControl
import net.minecraft.entity.ai.goal.EscapeDangerGoal
import net.minecraft.entity.ai.goal.MoveIntoWaterGoal
import net.minecraft.entity.ai.goal.WanderAroundGoal
import net.minecraft.entity.ai.pathing.MobNavigation
import net.minecraft.entity.ai.pathing.PathNodeType
import net.minecraft.entity.damage.DamageSource
import net.minecraft.entity.data.DataTracker
import net.minecraft.entity.data.TrackedData
import net.minecraft.entity.data.TrackedDataHandlerRegistry
import net.minecraft.entity.mob.WaterCreatureEntity
import net.minecraft.nbt.NbtCompound
import net.minecraft.registry.tag.FluidTags
import net.minecraft.registry.tag.TagKey
import net.minecraft.sound.SoundEvent
import net.minecraft.sound.SoundEvents
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.random.Random
import net.minecraft.world.LocalDifficulty
import net.minecraft.world.ServerWorldAccess
import net.minecraft.world.World
import net.minecraft.world.WorldAccess
import net.minecraft.world.biome.Biome
import software.bernie.geckolib.animatable.GeoEntity
import software.bernie.geckolib.core.animatable.GeoAnimatable
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache
import software.bernie.geckolib.core.animation.*
import software.bernie.geckolib.core.`object`.PlayState
import software.bernie.geckolib.util.GeckoLibUtil


@Suppress("LeakingThis", "DEPRECATION", "UNUSED_PARAMETER")
open class HybridAquaticCritterEntity(
    type: EntityType<out HybridAquaticCritterEntity>,
    world: World,
    private val variants: Map<String, CritterVariant> = mutableMapOf(),
    open val assumeDefault: Boolean = false,
    open val collisionRules: List<VariantCollisionRules> = listOf()
) : WaterCreatureEntity(type, world), GeoEntity {
    private val factory = GeckoLibUtil.createInstanceCache(this)
    private var fromFishingNet = false

    init {
        setPathfindingPenalty(PathNodeType.WATER, 0.0f)
        setPathfindingPenalty(PathNodeType.WALKABLE, 10.0f)
        moveControl = MoveControl(this)
        navigation = MobNavigation(this, world)
    }

    override fun hasNoDrag(): Boolean {
        return this.isClimbing
    }

    override fun tick() {
        super.tick()

        if (!isWet) {
            this.speed = 0.01F
        }
    }

    override fun getStepHeight(): Float {
        return 1.0F
    }

    override fun initDataTracker() {
        super.initDataTracker()
        dataTracker.startTracking(VARIANT, "")
        dataTracker.startTracking(VARIANT_DATA, NbtCompound())
        dataTracker.startTracking(CRITTER_SIZE, 0)
        dataTracker.startTracking(CRITTER_FLAGS, 0.toByte())
        dataTracker.startTracking(IS_CLIMBING_WALL, false)
    }

    override fun initGoals() {
        super.initGoals()
        goalSelector.add(2, MoveIntoWaterGoal(this))
        goalSelector.add(3, EscapeDangerGoal(this, 0.35))
        goalSelector.add(5, WanderAroundGoal(this, 0.35, 10))
    }

    override fun initialize(
        world: ServerWorldAccess,
        difficulty: LocalDifficulty,
        spawnReason: SpawnReason,
        entityData: EntityData?,
        entityNbt: NbtCompound?
    ): EntityData? {
        this.air = this.maxAir

        if (variants.isNotEmpty()) {
            if (spawnReason == SpawnReason.SPAWN_EGG) {
                variantKey = variants.keys.elementAt(random.nextBetween(0, variants.size - 1))
            } else {
                // Handle collisions
                val validKeys =
                    variants.filter { it.value.spawnCondition(world, spawnReason, blockPos, random) }.map { it.key }

                if (validKeys.isEmpty()) {
                    variantKey = variants.keys.random()
                } else if (collisionRules.isNotEmpty()) {
                    for (rule in collisionRules) {
                        val variantSet = rule.variants.toSet()
                        if ((rule.exclusionStatus == VariantCollisionRules.ExclusionStatus.EXCLUSIVE && validKeys.toSet() == variantSet) ||
                            (rule.exclusionStatus == VariantCollisionRules.ExclusionStatus.INCLUSIVE && validKeys.containsAll(
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

        this.size = this.random.nextBetween(getMinSize(), getMaxSize())
        return super.initialize(world, difficulty, spawnReason, entityData, entityNbt)
    }

    override fun shouldSwimInFluids(): Boolean {
        return !isOnGround || !isClimbing
    }

    override fun isPushedByFluids(): Boolean {
        return false
    }

    override fun writeCustomDataToNbt(nbt: NbtCompound) {
        super.writeCustomDataToNbt(nbt)
        nbt.putString(VARIANT_KEY, variantKey)
        nbt.put(VARIANT_DATA_KEY, variantData)
        nbt.putInt(CRITTER_SIZE_KEY, size)
        nbt.putBoolean("FromFishingNet", fromFishingNet)
    }

    override fun readCustomDataFromNbt(nbt: NbtCompound) {
        super.readCustomDataFromNbt(nbt)
        variantKey = nbt.getString(VARIANT_KEY)
        variantData = nbt.getCompound(VARIANT_DATA_KEY)
        size = nbt.getInt(CRITTER_SIZE_KEY)
        fromFishingNet = nbt.getBoolean("FromFishingNet")
    }

    override fun tickWaterBreathingAir(air: Int) {}

    open fun <E : GeoAnimatable> predicate(event: AnimationState<E>): PlayState {
        if (isSubmergedInWater && event.isMoving) {
            event.controller.setAnimation(WALK_ANIMATION)
            return PlayState.CONTINUE

        }

        if (!isSubmergedInWater) {
            event.controller.setAnimation(FLOP_ANIMATION)
            return PlayState.CONTINUE

        }

        if (isSubmergedInWater && !event.isMoving) {
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

    override fun canImmediatelyDespawn(distanceSquared: Double): Boolean {
        return !fromFishingNet && !hasCustomName()
    }

    override fun getLimitPerChunk(): Int {
        return 4
    }

    override fun getHurtSound(source: DamageSource): SoundEvent {
        return SoundEvents.ENTITY_SLIME_HURT
    }

    override fun getDeathSound(): SoundEvent {
        return SoundEvents.ENTITY_SLIME_DEATH_SMALL
    }

    override fun getAmbientSound(): SoundEvent {
        return SoundEvents.ENTITY_TROPICAL_FISH_AMBIENT
    }

    override fun getSwimSound(): SoundEvent {
        return SoundEvents.ENTITY_SLIME_JUMP_SMALL
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

    override fun canBreatheInWater(): Boolean {
        return true
    }

    private var variantData: NbtCompound
        get() = dataTracker.get(VARIANT_DATA)
        set(value) {
            dataTracker.set(VARIANT_DATA, value)
        }

    private var variantKey: String
        get() = dataTracker.get(VARIANT).ifBlank {
            if (!assumeDefault && variants.isNotEmpty()) {
                variants.isNotEmpty()
            }
            dataTracker.get(VARIANT)
        }
        private set(value) {
            dataTracker.set(VARIANT, value)
        }

    @Suppress("UNUSED_PARAMETER")
    var variant: CritterVariant?
        get() = variants[variantKey]
        private set(value) {}

    var size: Int
        get() = dataTracker.get(CRITTER_SIZE)
        set(size) {
            dataTracker.set(CRITTER_SIZE, size)
        }

    companion object {
        val VARIANT: TrackedData<String> =
            DataTracker.registerData(HybridAquaticCritterEntity::class.java, TrackedDataHandlerRegistry.STRING)
        var VARIANT_DATA: TrackedData<NbtCompound> =
            DataTracker.registerData(HybridAquaticCritterEntity::class.java, TrackedDataHandlerRegistry.NBT_COMPOUND)
        val CRITTER_SIZE: TrackedData<Int> =
            DataTracker.registerData(HybridAquaticCritterEntity::class.java, TrackedDataHandlerRegistry.INTEGER)
        val CRITTER_FLAGS: TrackedData<Byte> =
            DataTracker.registerData(HybridAquaticCritterEntity::class.java, TrackedDataHandlerRegistry.BYTE)
        val IS_CLIMBING_WALL: TrackedData<Boolean> =
            DataTracker.registerData(HybridAquaticCritterEntity::class.java, TrackedDataHandlerRegistry.BOOLEAN)

        fun canSpawn(
            type: EntityType<out WaterCreatureEntity>,
            world: ServerWorldAccess,
            reason: SpawnReason,
            pos: BlockPos,
            random: Random
        ): Boolean {
            val topY = world.seaLevel
            val bottomY = world.seaLevel - 128

            return pos.y in bottomY..topY &&
                    world.getBlockState(pos.down()).isSolid &&
                    world.getFluidState(pos).isIn(FluidTags.WATER)
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
        val spawnCondition: (WorldAccess, SpawnReason, BlockPos, Random) -> Boolean,
        val ignore: List<Ignore> = emptyList(),
        val priority: Int = 0,
        var providedVariant: (World, BlockPos, Random, HybridAquaticCritterEntity) -> String = { _, _, _, _ ->
            variantName
        }
    ) {

        fun getProvidedVariant(critter: HybridAquaticCritterEntity): String {
            return providedVariant(critter.world, critter.blockPos, critter.random, critter)
        }

        companion object {

            fun biomeVariant(
                variantName: String,
                biomes: List<TagKey<Biome>>,
                ignore: List<Ignore> = emptyList()
            ): CritterVariant {
                return CritterVariant(variantName, { world, _, pos, _ ->
                    val biome = world.getBiome(pos)
                    biomes.any { biome.isIn(it) }
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
        val collisionHandler: (Set<String>, Random, ServerWorldAccess) -> String,
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
