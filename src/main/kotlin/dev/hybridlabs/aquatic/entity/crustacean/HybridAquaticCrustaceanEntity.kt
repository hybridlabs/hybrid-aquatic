package dev.hybridlabs.aquatic.entity.crustacean

import dev.hybridlabs.aquatic.entity.cephalopod.HybridAquaticCephalopodEntity
import dev.hybridlabs.aquatic.entity.fish.HybridAquaticFishEntity
import dev.hybridlabs.aquatic.entity.shark.HybridAquaticSharkEntity
import net.minecraft.block.Blocks
import net.minecraft.entity.EntityData
import net.minecraft.entity.EntityGroup
import net.minecraft.entity.EntityType
import net.minecraft.entity.SpawnReason
import net.minecraft.entity.ai.control.MoveControl
import net.minecraft.entity.ai.goal.EscapeDangerGoal
import net.minecraft.entity.ai.goal.WanderAroundGoal
import net.minecraft.entity.ai.pathing.EntityNavigation
import net.minecraft.entity.ai.pathing.MobNavigation
import net.minecraft.entity.ai.pathing.PathNodeType
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.entity.damage.DamageSource
import net.minecraft.entity.data.DataTracker
import net.minecraft.entity.data.TrackedData
import net.minecraft.entity.data.TrackedDataHandlerRegistry
import net.minecraft.entity.mob.HostileEntity.isSpawnDark
import net.minecraft.entity.mob.WaterCreatureEntity
import net.minecraft.nbt.NbtCompound
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
import software.bernie.geckolib.constant.DefaultAnimations
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache
import software.bernie.geckolib.core.animation.AnimatableManager
import software.bernie.geckolib.core.animation.AnimationController
import software.bernie.geckolib.core.animation.AnimationState
import software.bernie.geckolib.core.animation.RawAnimation
import software.bernie.geckolib.core.`object`.PlayState
import software.bernie.geckolib.util.GeckoLibUtil


@Suppress("DEPRECATION", "LeakingThis", "UNUSED_PARAMETER")
open class HybridAquaticCrustaceanEntity(
    type: EntityType<out HybridAquaticCrustaceanEntity>,
    world: World,
    open val canDance: Boolean,
    private val variants: Map<String, CrustaceanVariant> = mutableMapOf(),
    open val assumeDefault: Boolean = false,
    open val collisionRules: List<HybridAquaticFishEntity.VariantCollisionRules> = listOf(),
) : WaterCreatureEntity(type, world), GeoEntity {
    private val factory = GeckoLibUtil.createInstanceCache(this)
    private var fromFishingNet = false
    private var songPlaying = false
    private var songSource: BlockPos? = null

    private var isHiding: Boolean = false

    private var hidingTimer: Int = 0
    private var lastDamageTime: Long = 0

    var size: Int
        get() = dataTracker.get(CRUSTACEAN_SIZE)
        set(size) {
            dataTracker.set(CRUSTACEAN_SIZE, size)
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
    var variant: CrustaceanVariant?
        get() = variants[variantKey]
        private set(value) {}

    override fun initDataTracker() {
        super.initDataTracker()
        dataTracker.startTracking(CRUSTACEAN_SIZE, 0)
        dataTracker.startTracking(ATTEMPT_ATTACK, false)
        dataTracker.startTracking(VARIANT, "")
        dataTracker.startTracking(VARIANT_DATA, NbtCompound())
    }

    override fun initGoals() {
        super.initGoals()
        goalSelector.add(1, EscapeDangerGoal(this, 1.0))
        goalSelector.add(3, WanderAroundGoal(this, 0.4))
    }

    override fun initialize(
        world: ServerWorldAccess,
        difficulty: LocalDifficulty,
        spawnReason: SpawnReason,
        entityData: EntityData?,
        entityNbt: NbtCompound?
    ): EntityData? {
        this.size = this.random.nextBetween(getMinSize(), getMaxSize())

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
                        if ((rule.exclusionStatus == HybridAquaticFishEntity.VariantCollisionRules.ExclusionStatus.EXCLUSIVE && validKeys.toSet() == variantSet) ||
                            (rule.exclusionStatus == HybridAquaticFishEntity.VariantCollisionRules.ExclusionStatus.INCLUSIVE && validKeys.containsAll(
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

    // region movement

    init {
        setPathfindingPenalty(PathNodeType.WATER, 0.0f)
        setPathfindingPenalty(PathNodeType.WALKABLE, 0.0f)
        moveControl = MoveControl(this)
        navigation = MobNavigation(this, world)
    }

    override fun tickMovement() {
        if (this.songSource == null || !songSource!!.isWithinDistance(
                this.pos,
                3.5
            ) || !world.getBlockState(this.songSource).isOf(Blocks.JUKEBOX)
        ) {
            this.songPlaying = false
            this.songSource = null
        }

        super.tickMovement()
    }

    override fun setNearbySongPlaying(songPosition: BlockPos, playing: Boolean) {
        this.songSource = songPosition
        this.songPlaying = playing
    }

    private fun isSongPlaying(): Boolean {
        return this.songPlaying
    }

    override fun getStepHeight(): Float {
        return 1.0F
    }

    override fun shouldSwimInFluids(): Boolean {
        return !isOnGround
    }

    override fun isPushedByFluids(): Boolean {
        return false
    }

    private fun startHiding() {
        isHiding = true
        hidingTimer = 200
    }

    override fun tick() {
        super.tick()

        if ((this is HermitCrabEntity || this is GiantIsopodEntity) && isHiding) {
            hidingTimer--

            if (hidingTimer <= 0 && (world.time - lastDamageTime) >= 200) {
                isHiding = false
                attributes.getCustomInstance(EntityAttributes.GENERIC_MOVEMENT_SPEED)?.baseValue = 0.3
                attributes.getCustomInstance(EntityAttributes.GENERIC_ARMOR)?.baseValue = 5.0
            } else {
                attributes.getCustomInstance(EntityAttributes.GENERIC_MOVEMENT_SPEED)?.baseValue = 0.0
                attributes.getCustomInstance(EntityAttributes.GENERIC_ARMOR)?.baseValue = 50.0
            }
        }
    }

    override fun damage(source: DamageSource, amount: Float): Boolean {
        if (this is HermitCrabEntity || this is GiantIsopodEntity && !isHiding) {
            startHiding()
        }

        lastDamageTime = world.time

        return super.damage(source, amount)
    }

    // end region

    override fun getGroup(): EntityGroup {
        return EntityGroup.AQUATIC
    }

    protected open fun getMinSize(): Int {
        return 0
    }

    protected open fun getMaxSize(): Int {
        return 0
    }

    override fun writeCustomDataToNbt(nbt: NbtCompound) {
        super.writeCustomDataToNbt(nbt)
        nbt.putString(VARIANT_KEY, variantKey)
        nbt.put(VARIANT_DATA_KEY, variantData)
        nbt.putInt(CRUSTACEAN_SIZE_KEY, size)
        nbt.putBoolean("FromFishingNet", fromFishingNet)
    }

    override fun readCustomDataFromNbt(nbt: NbtCompound) {
        super.readCustomDataFromNbt(nbt)
        variantKey = nbt.getString(VARIANT_KEY)
        variantData = nbt.getCompound(VARIANT_DATA_KEY)
        size = nbt.getInt(CRUSTACEAN_SIZE_KEY)
        fromFishingNet = nbt.getBoolean("FromFishingNet")
    }

    //#region SFX

    override fun calculateNextStepSoundDistance(): Float {
        return this.distanceTraveled + 0.25f
    }

    override fun getHurtSound(source: DamageSource): SoundEvent {
        return SoundEvents.ENTITY_TURTLE_EGG_CRACK
    }

    override fun getDeathSound(): SoundEvent {
        return SoundEvents.ENTITY_TURTLE_EGG_BREAK
    }

    //#endregion

    override fun createNavigation(world: World): EntityNavigation {
        return MobNavigation(this, world)
    }

    // region water breathing

    override fun canBreatheInWater(): Boolean {
        return true
    }

    override fun tickWaterBreathingAir(air: Int) {
    }

    // endregion

    override fun dropLoot(source: DamageSource, causedByPlayer: Boolean) {
        val attacker = source.attacker
        if (attacker !is HybridAquaticFishEntity && attacker !is HybridAquaticSharkEntity && attacker !is HybridAquaticCephalopodEntity) {
            super.dropLoot(source, causedByPlayer)
        }
    }

    override fun getLimitPerChunk(): Int {
        return 4
    }

    override fun canImmediatelyDespawn(distanceSquared: Double): Boolean {
        return !fromFishingNet && !hasCustomName()
    }

    //#region Animations
    override fun registerControllers(controllerRegistrar: AnimatableManager.ControllerRegistrar) {
        controllerRegistrar.add(
            DefaultAnimations.genericWalkIdleController(this)
        )
        controllerRegistrar.add(
            AnimationController(this, "Hide", 5,
                AnimationController.AnimationStateHandler { state: AnimationState<HybridAquaticCrustaceanEntity> ->
                    if (this.isHiding) {
                        return@AnimationStateHandler state.setAndContinue(HIDE)
                    } else {
                        PlayState.STOP
                    }
                }
            )
        )
        controllerRegistrar.add(
            AnimationController(this, "Dance", 5,
                AnimationController.AnimationStateHandler { state: AnimationState<HybridAquaticCrustaceanEntity> ->
                    if (this.canDance && isSongPlaying() && !state.isMoving) {
                        return@AnimationStateHandler state.setAndContinue(DANCE)
                    } else {
                        PlayState.STOP
                    }
                }
            )
        )
    }

    override fun getAnimatableInstanceCache(): AnimatableInstanceCache {
        return factory
    }

    //#endregion

    companion object {
        val CRUSTACEAN_SIZE: TrackedData<Int> =
            DataTracker.registerData(HybridAquaticCrustaceanEntity::class.java, TrackedDataHandlerRegistry.INTEGER)
        val ATTEMPT_ATTACK: TrackedData<Boolean> =
            DataTracker.registerData(HybridAquaticCrustaceanEntity::class.java, TrackedDataHandlerRegistry.BOOLEAN)
        val VARIANT: TrackedData<String> =
            DataTracker.registerData(HybridAquaticCrustaceanEntity::class.java, TrackedDataHandlerRegistry.STRING)
        var VARIANT_DATA: TrackedData<NbtCompound> =
            DataTracker.registerData(HybridAquaticCrustaceanEntity::class.java, TrackedDataHandlerRegistry.NBT_COMPOUND)

        val DANCE: RawAnimation = RawAnimation.begin().thenPlay("misc.dance")
        val HIDE: RawAnimation = RawAnimation.begin().thenPlay("misc.hide")

        fun canSpawn(
            type: EntityType<out WaterCreatureEntity>,
            world: ServerWorldAccess,
            reason: SpawnReason,
            pos: BlockPos,
            random: Random
        ): Boolean {
            val topY = world.seaLevel + 5
            val bottomY = world.seaLevel - 24

            return pos.y in bottomY..topY &&
                    world.getBlockState(pos.down()).isSolid &&
                    (world.isWater(pos) || world.isAir(pos)) &&
                    !isSpawnDark(world, pos, random)
        }

        fun canUndergroundSpawn(
            type: EntityType<out WaterCreatureEntity>,
            world: ServerWorldAccess,
            reason: SpawnReason,
            pos: BlockPos,
            random: Random
        ): Boolean {
            val topY = world.seaLevel - 24
            val bottomY = world.seaLevel - 128

            return pos.y in bottomY..topY &&
                    world.getBlockState(pos.down()).isSolid &&
                    world.getBlockState(pos).isOf(Blocks.WATER) &&
                    isSpawnDark(world, pos, random)
        }

        fun getScaleAdjustment(crustacean: HybridAquaticCrustaceanEntity, adjustment: Float): Float {
            return 1.0f + (crustacean.size * adjustment)
        }

        const val VARIANT_KEY = "Variant"
        const val VARIANT_DATA_KEY = "VariantData"
        const val CRUSTACEAN_SIZE_KEY = "CrustaceanSize"
    }

    @Suppress("UNUSED")
    data class CrustaceanVariant(
        val variantName: String,
        val spawnCondition: (WorldAccess, SpawnReason, BlockPos, Random) -> Boolean,
        val ignore: List<Ignore> = emptyList(),
        val priority: Int = 0,
        var providedVariant: (World, BlockPos, Random, HybridAquaticCrustaceanEntity) -> String = { _, _, _, _ ->
            variantName
        }
    ) {

        fun getProvidedVariant(crustacean: HybridAquaticCrustaceanEntity): String {
            return providedVariant(crustacean.world, crustacean.blockPos, crustacean.random, crustacean)
        }

        companion object {
            fun biomeVariant(
                variantName: String,
                biomes: TagKey<Biome>,
                ignore: List<Ignore> = emptyList()
            ): CrustaceanVariant {
                return CrustaceanVariant(variantName, { world, _, pos, _ ->
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
    data class VariantCollisionRules(
        val variants: Set<String>,
        val collisionHandler: (Set<String>, Random, ServerWorldAccess) -> String,
        val exclusionStatus: ExclusionStatus = ExclusionStatus.INCLUSIVE
    ) {

        enum class ExclusionStatus {
            INCLUSIVE,
            EXCLUSIVE
        }

        fun equalDistribution(
            variants: Set<String>,
            status: ExclusionStatus = ExclusionStatus.INCLUSIVE
        ): VariantCollisionRules {
            return VariantCollisionRules(variants, { possibleVariants, _, _ ->
                possibleVariants.random()
            }, status)
        }

        fun weightedDistribution(
            weights: Set<Pair<String, Double>>,
            status: ExclusionStatus = ExclusionStatus.EXCLUSIVE
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