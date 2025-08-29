package dev.hybridlabs.aquatic.entity.crustacean

import dev.hybridlabs.aquatic.entity.cephalopod.HybridAquaticCephalopodEntity
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
import net.minecraft.world.DifficultyInstance
import net.minecraft.world.damagesource.DamageSource
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.MobSpawnType
import net.minecraft.world.entity.MobType
import net.minecraft.world.entity.SpawnGroupData
import net.minecraft.world.entity.ai.attributes.Attributes
import net.minecraft.world.entity.ai.control.MoveControl
import net.minecraft.world.entity.ai.goal.PanicGoal
import net.minecraft.world.entity.ai.goal.RandomStrollGoal
import net.minecraft.world.entity.ai.navigation.GroundPathNavigation
import net.minecraft.world.entity.ai.navigation.PathNavigation
import net.minecraft.world.entity.animal.WaterAnimal
import net.minecraft.world.entity.monster.Monster.isDarkEnoughToSpawn
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
import software.bernie.geckolib.core.animation.RawAnimation
import software.bernie.geckolib.core.`object`.PlayState
import software.bernie.geckolib.util.GeckoLibUtil


@Suppress("DEPRECATION", "LeakingThis", "UNUSED_PARAMETER")
open class HybridAquaticCrustaceanEntity(
    type: EntityType<out HybridAquaticCrustaceanEntity>,
    world: Level,
    open val canDance: Boolean,
    private val variants: Map<String, CrustaceanVariant> = mutableMapOf(),
    open val assumeDefault: Boolean = false,
    open val collisionRules: List<HybridAquaticFishEntity.VariantCollisionRules> = listOf(),
) : WaterAnimal(type, world), GeoEntity {
    private val factory = GeckoLibUtil.createInstanceCache(this)
    private var fromFishingNet = false
    private var songPlaying = false
    private var songSource: BlockPos? = null

    private var isHiding: Boolean = false

    private var hidingTimer: Int = 0
    private var lastDamageTime: Long = 0

    var size: Int
        get() = entityData.get(CRUSTACEAN_SIZE)
        set(size) {
            entityData.set(CRUSTACEAN_SIZE, size)
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
    var variant: CrustaceanVariant?
        get() = variants[variantKey]
        private set(value) {}

    override fun defineSynchedData() {
        super.defineSynchedData()
        entityData.define(CRUSTACEAN_SIZE, 0)
        entityData.define(ATTEMPT_ATTACK, false)
        entityData.define(VARIANT, "")
        entityData.define(VARIANT_DATA, CompoundTag())
    }

    override fun registerGoals() {
        super.registerGoals()
        goalSelector.addGoal(1, PanicGoal(this, 1.0))
        goalSelector.addGoal(3, RandomStrollGoal(this, 0.4))
    }

    override fun finalizeSpawn(
        world: ServerLevelAccessor,
        difficulty: DifficultyInstance,
        spawnReason: MobSpawnType,
        spawnData: SpawnGroupData?,
        entityNbt: CompoundTag?
    ): SpawnGroupData? {
        this.size = this.random.nextIntBetweenInclusive(getMinSize(), getMaxSize())

        if (variants.isNotEmpty()) {
            if (spawnReason == MobSpawnType.SPAWN_EGG) {
                variantKey = variants.keys.elementAt(random.nextIntBetweenInclusive(0, variants.size - 1))
            } else {
                // Handle collisions
                val validKeys =
                    variants.filter { it.value.spawnCondition(world, spawnReason, this.blockPosition(), random) }
                        .map { it.key }

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

        this.size = this.random.nextIntBetweenInclusive(getMinSize(), getMaxSize())
        return super.finalizeSpawn(world, difficulty, spawnReason, spawnData, entityNbt)
    }

    // region movement

    init {
        setPathfindingMalus(BlockPathTypes.WATER, 0.0f)
        setPathfindingMalus(BlockPathTypes.WALKABLE, 0.0f)
        moveControl = MoveControl(this)
        navigation = GroundPathNavigation(this, world)
    }

    override fun aiStep() {
        if (this.songSource == null || !songSource!!.closerToCenterThan(
                this.position(),
                3.5
            ) || !level().getBlockState(this.songSource!!).`is`(Blocks.JUKEBOX)
        ) {
            this.songPlaying = false
            this.songSource = null
        }

        super.aiStep()
    }

    override fun setRecordPlayingNearby(songPosition: BlockPos, playing: Boolean) {
        this.songSource = songPosition
        this.songPlaying = playing
    }

    private fun isSongPlaying(): Boolean {
        return this.songPlaying
    }

    override fun isAffectedByFluids(): Boolean {
        return !onGround()
    }

    override fun isPushedByFluid(): Boolean {
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

            if (hidingTimer <= 0 && (level().gameTime - lastDamageTime) >= 200) {
                isHiding = false
                attributes.getInstance(Attributes.MOVEMENT_SPEED)?.baseValue = 0.3
                attributes.getInstance(Attributes.ARMOR)?.baseValue = 5.0
            } else {
                attributes.getInstance(Attributes.MOVEMENT_SPEED)?.baseValue = 0.0
                attributes.getInstance(Attributes.ARMOR)?.baseValue = 50.0
            }
        }
    }

    override fun hurt(source: DamageSource, amount: Float): Boolean {
        if (this is HermitCrabEntity || this is GiantIsopodEntity && !isHiding) {
            startHiding()
        }

        lastDamageTime = level().gameTime

        return super.hurt(source, amount)
    }

    // end region

    override fun getMobType(): MobType? {
        return MobType.WATER
    }

    protected open fun getMinSize(): Int {
        return 0
    }

    protected open fun getMaxSize(): Int {
        return 0
    }

    override fun addAdditionalSaveData(nbt: CompoundTag) {
        super.addAdditionalSaveData(nbt)
        nbt.putString(VARIANT_KEY, variantKey)
        nbt.put(VARIANT_DATA_KEY, variantData)
        nbt.putInt(CRUSTACEAN_SIZE_KEY, size)
        nbt.putBoolean("FromFishingNet", fromFishingNet)
    }

    override fun readAdditionalSaveData(nbt: CompoundTag) {
        super.readAdditionalSaveData(nbt)
        variantKey = nbt.getString(VARIANT_KEY)
        variantData = nbt.getCompound(VARIANT_DATA_KEY)
        size = nbt.getInt(CRUSTACEAN_SIZE_KEY)
        fromFishingNet = nbt.getBoolean("FromFishingNet")
    }

    //#region SFX

    override fun nextStep(): Float {
        return this.moveDist + 0.25f
    }

    override fun getHurtSound(source: DamageSource): SoundEvent {
        return SoundEvents.TURTLE_EGG_CRACK
    }

    override fun getDeathSound(): SoundEvent {
        return SoundEvents.TURTLE_EGG_BREAK
    }

    //#endregion

    override fun createNavigation(world: Level): PathNavigation {
        return GroundPathNavigation(this, world)
    }

    // region water breathing

    override fun canBreatheUnderwater(): Boolean {
        return true
    }

    override fun handleAirSupply(air: Int) {
    }

    // endregion

    override fun dropFromLootTable(source: DamageSource, causedByPlayer: Boolean) {
        val attacker = source.directEntity
        if (attacker !is HybridAquaticFishEntity && attacker !is HybridAquaticCephalopodEntity) {
            super.dropFromLootTable(source, causedByPlayer)
        }
    }

    override fun getMaxSpawnClusterSize(): Int {
        return 4
    }

    override fun removeWhenFarAway(distanceSquared: Double): Boolean {
        return !fromFishingNet && !hasCustomName()
    }

    //#region Animations
    override fun registerControllers(controllerRegistrar: AnimatableManager.ControllerRegistrar) {
        controllerRegistrar.add(
            DefaultAnimations.genericWalkIdleController(this)
        )
        controllerRegistrar.add(
            AnimationController(
                this, "Hide", 5,
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
            AnimationController(
                this, "Dance", 5,
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
        val CRUSTACEAN_SIZE: EntityDataAccessor<Int> =
            SynchedEntityData.defineId(
                HybridAquaticCrustaceanEntity::class.java,
                EntityDataSerializers.INT
            )
        val ATTEMPT_ATTACK: EntityDataAccessor<Boolean> =
            SynchedEntityData.defineId(
                HybridAquaticCrustaceanEntity::class.java,
                EntityDataSerializers.BOOLEAN
            )
        val VARIANT: EntityDataAccessor<String> =
            SynchedEntityData.defineId(
                HybridAquaticCrustaceanEntity::class.java,
                EntityDataSerializers.STRING
            )
        var VARIANT_DATA: EntityDataAccessor<CompoundTag> =
            SynchedEntityData.defineId(
                HybridAquaticCrustaceanEntity::class.java,
                EntityDataSerializers.COMPOUND_TAG
            )

        val DANCE: RawAnimation = RawAnimation.begin().thenPlay("misc.dance")
        val HIDE: RawAnimation = RawAnimation.begin().thenPlay("misc.hide")

        fun canSpawn(
            type: EntityType<out WaterAnimal>,
            world: ServerLevelAccessor,
            reason: MobSpawnType,
            pos: BlockPos,
            random: RandomSource
        ): Boolean {
            val topY = world.seaLevel + 5
            val bottomY = world.seaLevel - 24

            return pos.y in bottomY..topY &&
                    world.getBlockState(pos.below()).isSolid &&
                    (world.isWaterAt(pos) || world.isEmptyBlock(pos)) &&
                    !isDarkEnoughToSpawn(world, pos, random)
        }

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
                    world.getBlockState(pos.below()).isSolid &&
                    world.getBlockState(pos).`is`(Blocks.WATER) &&
                    isDarkEnoughToSpawn(world, pos, random)
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
        val spawnCondition: (LevelAccessor, MobSpawnType, BlockPos, RandomSource) -> Boolean,
        val ignore: List<Ignore> = emptyList(),
        val priority: Int = 0,
        var providedVariant: (Level, BlockPos, RandomSource, HybridAquaticCrustaceanEntity) -> String = { _, _, _, _ ->
            variantName
        }
    ) {

        fun getProvidedVariant(crustacean: HybridAquaticCrustaceanEntity): String {
            return providedVariant(crustacean.level(), crustacean.blockPosition(), crustacean.random, crustacean)
        }

        companion object {
            fun biomeVariant(
                variantName: String,
                biomes: TagKey<Biome>,
                ignore: List<Ignore> = emptyList()
            ): CrustaceanVariant {
                return CrustaceanVariant(variantName, { world, _, pos, _ ->
                    world.getBiome(pos).`is`(biomes)
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