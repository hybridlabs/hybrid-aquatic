package dev.hybridlabs.aquatic.entity.fish.ray

import dev.hybridlabs.aquatic.entity.cephalopod.HybridAquaticCephalopodEntity
import dev.hybridlabs.aquatic.entity.fish.HybridAquaticFishEntity
import dev.hybridlabs.aquatic.entity.fish.ray.HybridAquaticRayEntity.VariantCollisionRules.ExclusionStatus.EXCLUSIVE
import dev.hybridlabs.aquatic.entity.fish.ray.HybridAquaticRayEntity.VariantCollisionRules.ExclusionStatus.INCLUSIVE
import dev.hybridlabs.aquatic.entity.shark.HybridAquaticSharkEntity
import net.minecraft.block.Blocks
import net.minecraft.entity.*
import net.minecraft.entity.ai.control.AquaticMoveControl
import net.minecraft.entity.ai.control.YawAdjustingLookControl
import net.minecraft.entity.ai.goal.LookAroundGoal
import net.minecraft.entity.ai.goal.LookAtEntityGoal
import net.minecraft.entity.ai.goal.SwimAroundGoal
import net.minecraft.entity.ai.pathing.SwimNavigation
import net.minecraft.entity.damage.DamageSource
import net.minecraft.entity.data.DataTracker
import net.minecraft.entity.data.TrackedData
import net.minecraft.entity.data.TrackedDataHandlerRegistry
import net.minecraft.entity.mob.WaterCreatureEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.nbt.NbtCompound
import net.minecraft.particle.ParticleTypes
import net.minecraft.registry.tag.FluidTags
import net.minecraft.registry.tag.TagKey
import net.minecraft.util.Identifier
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Vec3d
import net.minecraft.util.math.random.Random
import net.minecraft.world.LocalDifficulty
import net.minecraft.world.ServerWorldAccess
import net.minecraft.world.World
import net.minecraft.world.WorldAccess
import net.minecraft.world.biome.Biome
import software.bernie.geckolib.animatable.GeoAnimatable
import software.bernie.geckolib.animatable.GeoEntity
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache
import software.bernie.geckolib.animation.*
import software.bernie.geckolib.animation.AnimationState
import software.bernie.geckolib.util.GeckoLibUtil

@Suppress("LeakingThis")
open class HybridAquaticRayEntity(
    type: EntityType<out HybridAquaticRayEntity>,
    world: World,
    private val variants: Map<String, RayVariant> = hashMapOf(),
    open val assumeDefault: Boolean = false,
    open val collisionRules: List<VariantCollisionRules> = listOf()
) : WaterCreatureEntity(type, world), GeoEntity {

    private val factory = GeckoLibUtil.createInstanceCache(this)

    init {
        moveControl = AquaticMoveControl(this, 85, 10, 0.1F, 0.1F, true)
        lookControl = YawAdjustingLookControl(this, 20)
        navigation = SwimNavigation(this, world)
    }

    override fun initGoals() {
        super.initGoals()
        goalSelector.add(1, SwimToRandomPlaceGoal(this))
        goalSelector.add(4, SwimAroundGoal(this, 1.0, 2))
        goalSelector.add(4, LookAroundGoal(this))
        goalSelector.add(5, LookAtEntityGoal(this, PlayerEntity::class.java, 6.0f))
    }

    override fun initDataTracker() {
        super.initDataTracker()
        dataTracker.set(MOISTNESS, getMaxMoistness())
        dataTracker.set(RAY_SIZE, 0)
        dataTracker.set(ATTEMPT_ATTACK, false)
        dataTracker.set(HUNGER, MAX_HUNGER)
        dataTracker.set(VARIANT, "")
        dataTracker.set(VARIANT_DATA, NbtCompound())
    }

    override fun initialize(
        world: ServerWorldAccess,
        difficulty: LocalDifficulty?,
        spawnReason: SpawnReason,
        entityData: EntityData?
    ): EntityData? {
        this.air = getMaxMoistness()
        pitch = 0.0f
        this.size = this.random.nextBetween(getMinSize(),getMaxSize())

        if (variants.isNotEmpty()) {
            if (spawnReason == SpawnReason.SPAWN_EGG) {
                variantKey = variants.keys.elementAt(random.nextBetween(0, variants.size - 1))
            } else {
                val validKeys = variants.filter { it.value.spawnCondition(world, spawnReason, blockPos, random) }.map { it.key }

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
        return super.initialize(world, difficulty, spawnReason, entityData)
    }
    override fun tick() {
        super.tick()
        if (isAiDisabled) {
            return
        }

        if (isWet) {
            moistness = getMaxMoistness()
        } else {
            moistness -= 1
            if (moistness <= -20) {
                moistness = 0
                damage(this.damageSources.dryOut(), 1.0f)
            }
        }
        if (world.isClient && isTouchingWater && isAttacking) {
            val rotationVec = getRotationVec(0.0f)
            val offsetY = 0.0f - random.nextFloat()

            for (i in 0..1) {
                val particleX = x - rotationVec.x * 0.1
                val particleY = y - rotationVec.y * -0.1
                val particleZ = z - rotationVec.z * offsetY

                world.addParticle(
                    ParticleTypes.DOLPHIN,
                    particleX,
                    particleY,
                    particleZ,
                    0.0,
                    0.0,
                    0.0
                )
            }
        }
    }

    override fun dropLoot(source: DamageSource, causedByPlayer: Boolean) {
        val attacker = source.attacker
        if (attacker !is HybridAquaticFishEntity && attacker !is HybridAquaticSharkEntity && attacker !is HybridAquaticRayEntity && attacker !is HybridAquaticCephalopodEntity) {
            super.dropLoot(source, causedByPlayer)
        }
    }

    override fun getLootTableId(): Identifier {
        return if (variant != null) {
            super.getLootTableId().withPath { path -> "${path}_${variant!!.variantName}" }
        } else {
            super.getLootTableId()
        }
    }

    override fun tickWaterBreathingAir(air: Int) {}

    private fun getMaxMoistness(): Int {
        return 600
    }

    override fun writeCustomDataToNbt(nbt: NbtCompound) {
        super.writeCustomDataToNbt(nbt)
        nbt.putInt(MOISTNESS_KEY, moistness)
        nbt.putString(VARIANT_KEY, variantKey)
        nbt.put(VARIANT_DATA_KEY, variantData)
        nbt.putInt(FISH_SIZE_KEY, size)
        nbt.putInt(HUNGER_KEY, hunger)
        nbt.putBoolean("FromFishingNet", fromFishingNet)
    }

    private var fromFishingNet = false

    override fun readCustomDataFromNbt(nbt: NbtCompound) {
        super.readCustomDataFromNbt(nbt)
        moistness = nbt.getInt(MOISTNESS_KEY)
        variantKey = nbt.getString(VARIANT_KEY)
        variantData = nbt.getCompound(VARIANT_DATA_KEY)
        size = nbt.getInt(FISH_SIZE_KEY)
        hunger = nbt.getInt(HUNGER_KEY)
        fromFishingNet = nbt.getBoolean("FromFishingNet")
    }

    open fun <E : GeoAnimatable> predicate(event: AnimationState<E>): PlayState {
        if (isSubmergedInWater && event.isMoving) {
            event.controller.setAnimation(SWIM_ANIMATION)
            return PlayState.CONTINUE

        }

        if (!isSubmergedInWater && this.moistness < getMaxMoistness()) {
            event.controller.setAnimation(FLOP_ANIMATION)
            return PlayState.CONTINUE
        }

        if (isSubmergedInWater && !event.isMoving) {
            event.controller.setAnimation(IDLE_ANIMATION)
            return PlayState.CONTINUE
        }
        return PlayState.CONTINUE
    }

    override fun canImmediatelyDespawn(distanceSquared: Double): Boolean {
        return !hasCustomName() && !fromFishingNet
    }

    override fun getLimitPerChunk(): Int {
        return 4
    }

    // region Properties

    private var moistness: Int
        get() = dataTracker.get(MOISTNESS)
        set(moistness) {
            dataTracker.set(MOISTNESS, moistness)
        }

    var size: Int
        get() = dataTracker.get(RAY_SIZE)
        set(size) {
            dataTracker.set(RAY_SIZE, size)
        }

    private var hunger: Int
        get() = dataTracker.get(HUNGER)
        set(hunger) {
            dataTracker.set(HUNGER, hunger)
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
    var variant: RayVariant?
        get() = variants[variantKey]
        private set(value) {}

    // endregion

    override fun getNextAirOnLand(air: Int): Int {
        return this.maxAir
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

    override fun shouldSwimInFluids(): Boolean {
        return true
    }

    override fun travel(movementInput: Vec3d?) {
        super.travel(movementInput)
    }

    companion object {
        val MOISTNESS: TrackedData<Int> = DataTracker.registerData(HybridAquaticRayEntity::class.java, TrackedDataHandlerRegistry.INTEGER)
        val RAY_SIZE: TrackedData<Int> = DataTracker.registerData(HybridAquaticRayEntity::class.java, TrackedDataHandlerRegistry.INTEGER)
        val HUNGER: TrackedData<Int> = DataTracker.registerData(HybridAquaticRayEntity::class.java, TrackedDataHandlerRegistry.INTEGER)
        val ATTEMPT_ATTACK: TrackedData<Boolean> = DataTracker.registerData(HybridAquaticRayEntity::class.java, TrackedDataHandlerRegistry.BOOLEAN)
        val VARIANT: TrackedData<String> = DataTracker.registerData(HybridAquaticRayEntity::class.java, TrackedDataHandlerRegistry.STRING)
        var VARIANT_DATA: TrackedData<NbtCompound> = DataTracker.registerData(HybridAquaticRayEntity::class.java, TrackedDataHandlerRegistry.NBT_COMPOUND)

        const val MAX_HUNGER = 1200
        const val HUNGER_KEY = "Hunger"
        const val MOISTNESS_KEY = "Moistness"
        const val VARIANT_KEY = "Variant"
        const val VARIANT_DATA_KEY = "VariantData"
        const val FISH_SIZE_KEY = "FishSize"

        val IDLE_ANIMATION: RawAnimation = RawAnimation.begin().then("idle", Animation.LoopType.LOOP)
        val SWIM_ANIMATION: RawAnimation = RawAnimation.begin().then("swim", Animation.LoopType.LOOP)
        val FLOP_ANIMATION: RawAnimation = RawAnimation.begin().then("flop", Animation.LoopType.LOOP)

        @Suppress("UNUSED_PARAMETER", "DEPRECATION")
        fun canSpawn(
            type: EntityType<out WaterCreatureEntity>,
            world: WorldAccess,
            reason: SpawnReason?,
            pos: BlockPos,
            random: Random?
        ): Boolean {
            val topY = world.seaLevel
            val bottomY = world.seaLevel - 24

            return pos.y in bottomY..topY &&
                    world.getFluidState(pos.down()).isIn(FluidTags.WATER) &&
                    world.getBlockState(pos.up()).isOf(Blocks.WATER)
        }

        fun getScaleAdjustment(ray: HybridAquaticRayEntity, adjustment: Float): Float {
            return 1.0f + (ray.size * adjustment)
        }
    }

    override fun getMaxHeadRotation(): Int {
        return 1
    }

    override fun getMaxLookPitchChange(): Int {
        return 1
    }

    internal class SwimToRandomPlaceGoal(private val ray: HybridAquaticRayEntity) : SwimAroundGoal(ray, 1.0, 40) {
        override fun canStart(): Boolean {
            return ray.hasSelfControl() && super.canStart()
        }
    }

    @Suppress("UNUSED")
    data class RayVariant(
        val variantName : String,
        val spawnCondition: (WorldAccess, SpawnReason, BlockPos, Random) -> Boolean,
        val ignore: List<Ignore> = emptyList(),
        val priority: Int = 0,
        var providedVariant: (World, BlockPos, Random, HybridAquaticRayEntity) -> String = { _, _, _, _ ->
            variantName
        }
    ) {

        fun getProvidedVariant(ray: HybridAquaticRayEntity) : String {
            return providedVariant(ray.world, ray.blockPos, ray.random, ray)
        }

        companion object {
            fun biomeVariant(variantName: String, biomes : TagKey<Biome>, ignore : List<Ignore> = emptyList()): RayVariant {
                return RayVariant(variantName, { world, _, pos, _ ->
                    world.getBiome(pos).isIn(biomes)
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
    data class VariantCollisionRules(val variants : Set<String>, val collisionHandler: (Set<String>, Random, ServerWorldAccess) -> String, val exclusionStatus: ExclusionStatus = INCLUSIVE) {

        enum class ExclusionStatus {
            INCLUSIVE,
            EXCLUSIVE
        }
        fun equalDistribution(variants: Set<String>, status : ExclusionStatus = INCLUSIVE) : VariantCollisionRules {
            return VariantCollisionRules(variants, { possibleVariants, _, _ ->
                possibleVariants.random()
            }, status)
        }

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