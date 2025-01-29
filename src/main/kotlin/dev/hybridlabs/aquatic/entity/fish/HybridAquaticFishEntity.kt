package dev.hybridlabs.aquatic.entity.fish

import dev.hybridlabs.aquatic.entity.cephalopod.HybridAquaticCephalopodEntity
import dev.hybridlabs.aquatic.entity.fish.HybridAquaticFishEntity.VariantCollisionRules.ExclusionStatus.EXCLUSIVE
import dev.hybridlabs.aquatic.entity.fish.HybridAquaticFishEntity.VariantCollisionRules.ExclusionStatus.INCLUSIVE
import dev.hybridlabs.aquatic.entity.fish.ray.HybridAquaticRayEntity
import dev.hybridlabs.aquatic.entity.shark.HybridAquaticSharkEntity
import dev.hybridlabs.aquatic.tag.HybridAquaticEntityTags
import net.minecraft.block.Blocks
import net.minecraft.entity.Entity
import net.minecraft.entity.EntityData
import net.minecraft.entity.EntityType
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.MovementType
import net.minecraft.entity.SpawnReason
import net.minecraft.entity.ai.control.AquaticMoveControl
import net.minecraft.entity.ai.control.YawAdjustingLookControl
import net.minecraft.entity.ai.goal.ActiveTargetGoal
import net.minecraft.entity.ai.goal.EscapeDangerGoal
import net.minecraft.entity.ai.goal.MeleeAttackGoal
import net.minecraft.entity.ai.goal.SwimAroundGoal
import net.minecraft.entity.ai.pathing.EntityNavigation
import net.minecraft.entity.ai.pathing.SwimNavigation
import net.minecraft.entity.damage.DamageSource
import net.minecraft.entity.data.DataTracker
import net.minecraft.entity.data.TrackedData
import net.minecraft.entity.data.TrackedDataHandlerRegistry
import net.minecraft.entity.mob.WaterCreatureEntity
import net.minecraft.loot.LootTable
import net.minecraft.nbt.NbtCompound
import net.minecraft.particle.ParticleTypes
import net.minecraft.registry.RegistryKey
import net.minecraft.registry.RegistryKeys
import net.minecraft.registry.tag.FluidTags
import net.minecraft.registry.tag.TagKey
import net.minecraft.server.world.ServerWorld
import net.minecraft.sound.SoundEvent
import net.minecraft.sound.SoundEvents
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
import software.bernie.geckolib.animation.AnimatableManager
import software.bernie.geckolib.animation.Animation
import software.bernie.geckolib.animation.AnimationController
import software.bernie.geckolib.animation.AnimationState
import software.bernie.geckolib.animation.PlayState
import software.bernie.geckolib.animation.RawAnimation
import software.bernie.geckolib.util.GeckoLibUtil
import java.util.Optional

@Suppress("LeakingThis", "UNUSED_PARAMETER")
open class HybridAquaticFishEntity(
    type: EntityType<out HybridAquaticFishEntity>,
    world: World,
    private val variants: Map<String, FishVariant> = mutableMapOf(),
    open val prey: TagKey<EntityType<*>>,
    open val predator: TagKey<EntityType<*>>,
    open val assumeDefault: Boolean = false,
    open val collisionRules: List<VariantCollisionRules> = listOf()
) : WaterCreatureEntity(type, world), GeoEntity {

    private val factory = GeckoLibUtil.createInstanceCache(this)

    override fun initGoals() {
        super.initGoals()
        goalSelector.add(0, EscapeDangerGoal(this, 1.25))
        goalSelector.add(4, SwimAroundGoal(this, 1.0, 10))
        goalSelector.add(1, AttackGoal(this))
        targetSelector.add(1, ActiveTargetGoal(this, LivingEntity::class.java, 10, true, true) { entity, _ -> hunger <= 1200 && entity.type.isIn(prey) })
    }

    override fun initDataTracker(builder: DataTracker.Builder) {
        super.initDataTracker(builder)
        builder.add(MOISTNESS, getMaxMoistness())
        builder.add(FISH_SIZE, 0)
        builder.add(ATTEMPT_ATTACK, false)
        builder.add(HUNGER, MAX_HUNGER)
        builder.add(VARIANT, "")
        builder.add(VARIANT_DATA, NbtCompound())
    }

    override fun initialize(
        world: ServerWorldAccess,
        difficulty: LocalDifficulty,
        spawnReason: SpawnReason,
        entityData: EntityData?
    ): EntityData? {
        this.air = getMaxMoistness()
        pitch = 0.0f
        this.size = this.random.nextBetween(getMinSize(),getMaxSize())

        if (variants.isNotEmpty()) {
            if (spawnReason == SpawnReason.SPAWN_ITEM_USE) {
                variantKey = variants.keys.elementAt(random.nextBetween(0, variants.size - 1))
            } else {
                // Handle collisions
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
                damage(world, this.damageSources.dryOut(), 1.0f)
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

    override fun dropLoot(world: ServerWorld, source: DamageSource, causedByPlayer: Boolean) {
        val attacker = source.attacker
        if (attacker !is HybridAquaticFishEntity && attacker !is HybridAquaticSharkEntity && attacker !is HybridAquaticRayEntity && attacker !is HybridAquaticCephalopodEntity) {
            super.dropLoot(world, source, causedByPlayer)
        }
    }

    override fun getLootTableKey(): Optional<RegistryKey<LootTable>> {
        return if (lootTable.isPresent) super.getLootTableKey() else Optional.ofNullable(getLootTableKeyOverride())
    }

    open fun getLootTableKeyOverride(): RegistryKey<LootTable>? {
        return variant.createLootTableKey(type)
    }

    private fun getHungerValue(entityType: EntityType<*>): Int {
        if (entityType.isIn(HybridAquaticEntityTags.CRAB))
            return 150
        if (entityType.isIn(HybridAquaticEntityTags.JELLYFISH))
            return 150
        if (entityType.isIn(HybridAquaticEntityTags.SMALL_PREY))
            return 300
        else if (entityType.isIn(HybridAquaticEntityTags.MEDIUM_PREY))
            return 600
        else if (entityType.isIn(HybridAquaticEntityTags.LARGE_PREY))
            return 1200

        return 0
    }

    open fun eatFish(entityType: EntityType<*>) {
        hunger += getHungerValue(entityType)
    }

    override fun tickWaterBreathingAir(air: Int) {}

    private fun getMaxMoistness(): Int {
        return 600
    }

    open fun shouldFlopOnLand(): Boolean {
        return true
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
        return !this.fromFishingNet && !this.hasCustomName()
    }

    override fun getLimitPerChunk(): Int {
        return 10
    }

    open val flopSound: SoundEvent = SoundEvents.ENTITY_PUFFER_FISH_FLOP

    override fun getSwimSound(): SoundEvent {
        return SoundEvents.ENTITY_FISH_SWIM
    }

    override fun getHurtSound(source: DamageSource): SoundEvent {
        return SoundEvents.ENTITY_COD_HURT
    }

    override fun getDeathSound(): SoundEvent {
        return SoundEvents.ENTITY_COD_DEATH
    }

    override fun getAmbientSound(): SoundEvent {
        return SoundEvents.ENTITY_COD_AMBIENT
    }

    override fun getSplashSound(): SoundEvent {
        return SoundEvents.ENTITY_DOLPHIN_SPLASH
    }

    override fun createNavigation(world: World): EntityNavigation {
        return SwimNavigation(this, world)
    }

    // region Properties

    private var moistness: Int
        get() = dataTracker.get(MOISTNESS)
        set(moistness) {
            dataTracker.set(MOISTNESS, moistness)
        }

    var size: Int
        get() = dataTracker.get(FISH_SIZE)
        set(size) {
            dataTracker.set(FISH_SIZE, size)
        }

    private var hunger: Int
        get() = dataTracker.get(HUNGER)
        set(hunger) {
            dataTracker.set(HUNGER, hunger)
        }

    private var attemptAttack: Boolean
        get() = dataTracker.get(ATTEMPT_ATTACK)
        set(attemptAttack) {
            dataTracker.set(ATTEMPT_ATTACK, attemptAttack)
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

    val variant: FishVariant get() = variants[variantKey] ?: throw IllegalArgumentException("Invalid variant key: $variantKey")

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

    init {
        moveControl = AquaticMoveControl(this, 75, 5, movementSpeed, 0.1f, true)
        lookControl = YawAdjustingLookControl(this, 10)
        navigation = SwimNavigation(this, world)
    }

    override fun travel(movementInput: Vec3d?) {
        if (this.canMoveVoluntarily() && this.isTouchingWater) {
            this.updateVelocity(0.01f, movementInput)
            this.move(MovementType.SELF, this.velocity)
            this.velocity = velocity.multiply(0.9)
            if (this.target == null) {
                this.velocity = velocity.add(0.0, -0.005, 0.0)
            }
        } else {
            super.travel(movementInput)
        }
    }

    override fun tickMovement() {
        if (!this.isTouchingWater && this.isOnGround && this.verticalCollision) {
            this.velocity = velocity.add(
                ((random.nextFloat() * 2.0f - 1.0f) * 0.05f).toDouble(), 0.4000000059604645,
                ((random.nextFloat() * 2.0f - 1.0f) * 0.05f).toDouble()
            )
            this.isOnGround = false
            this.velocityDirty = true
            this.playSound(this.flopSound, this.soundVolume, this.soundPitch)
        }

        super.tickMovement()
    }

    open fun speedModifier(): Double {
        return 0.0
    }

    internal class SwimToRandomPlaceGoal(private val fish: HybridAquaticFishEntity, d: Double, i: Int) : SwimAroundGoal(fish, 1.0, 40) {
        override fun canStart(): Boolean {
            return fish.hasSelfControl() && super.canStart()
        }
    }

    internal class AttackGoal(private val fish: HybridAquaticFishEntity) : MeleeAttackGoal(fish, 1.0,true) {
        override fun canStart(): Boolean {
            return !fish.fromFishingNet && super.canStart()
        }

        override fun attack(target: LivingEntity, squaredDistance: Double) {
            val d = getSquaredMaxAttackDistance(target)
            if (squaredDistance <= d && this.isCooledDown) {
                resetCooldown()
                mob.tryAttack(target)
                fish.isSprinting = true
                fish.attemptAttack = true

                if (target.health <= 0)
                    fish.eatFish(target.type)
            }
        }

        override fun getSquaredMaxAttackDistance(entity: LivingEntity): Double {
            return (1.25f + entity.width).toDouble()
        }

        override fun start() {
            super.start()
            fish.attemptAttack = false
        }

        override fun stop() {
            super.stop()
            fish.attemptAttack = false
        }
    }

    override fun tryAttack(target: Entity?): Boolean {
        if (super.tryAttack(target)) {

            playSound(SoundEvents.ENTITY_FOX_EAT,1.0F,0.0F)

            return true
        } else {
            return false
        }
    }

    companion object {
        val MOISTNESS: TrackedData<Int> = DataTracker.registerData(HybridAquaticFishEntity::class.java, TrackedDataHandlerRegistry.INTEGER)
        val FISH_SIZE: TrackedData<Int> = DataTracker.registerData(HybridAquaticFishEntity::class.java, TrackedDataHandlerRegistry.INTEGER)
        val HUNGER: TrackedData<Int> = DataTracker.registerData(HybridAquaticFishEntity::class.java, TrackedDataHandlerRegistry.INTEGER)
        val ATTEMPT_ATTACK: TrackedData<Boolean> = DataTracker.registerData(HybridAquaticFishEntity::class.java, TrackedDataHandlerRegistry.BOOLEAN)
        val VARIANT: TrackedData<String> = DataTracker.registerData(HybridAquaticFishEntity::class.java, TrackedDataHandlerRegistry.STRING)
        var VARIANT_DATA: TrackedData<NbtCompound> = DataTracker.registerData(HybridAquaticFishEntity::class.java, TrackedDataHandlerRegistry.NBT_COMPOUND)

        const val MAX_HUNGER = 1200
        const val HUNGER_KEY = "Hunger"
        const val MOISTNESS_KEY = "Moistness"
        const val VARIANT_KEY = "Variant"
        const val VARIANT_DATA_KEY = "VariantData"
        const val FISH_SIZE_KEY = "FishSize"

        val ATTACK_ANIMATION: RawAnimation  = RawAnimation.begin().then("attack", Animation.LoopType.LOOP)
        val IDLE_ANIMATION: RawAnimation  = RawAnimation.begin().then("idle", Animation.LoopType.LOOP)
        val SWIM_ANIMATION: RawAnimation  = RawAnimation.begin().then("swim", Animation.LoopType.LOOP)
        val FLOP_ANIMATION: RawAnimation  = RawAnimation.begin().then("flop", Animation.LoopType.LOOP)

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

        @Suppress("UNUSED_PARAMETER", "DEPRECATION")
        fun canUndergroundSpawn(
            type: EntityType<out WaterCreatureEntity?>?,
            world: WorldAccess,
            reason: SpawnReason?,
            pos: BlockPos,
            random: Random?
        ): Boolean {
            return pos.y <= world.seaLevel - 32 &&
                    world.getBaseLightLevel(pos, 0) == 0 &&
                    world.getBlockState(pos).isOf(Blocks.WATER)
        }

        fun getScaleAdjustment(fish: HybridAquaticFishEntity, adjustment: Float): Float {
            return 1.0f + (fish.size * adjustment)
        }
    }

    override fun getMaxHeadRotation(): Int {
        return 1
    }

    override fun getMaxLookPitchChange(): Int {
        return 1
    }

    @Suppress("UNUSED")
    data class FishVariant(
        val variantName : String,
        val spawnCondition: (WorldAccess, SpawnReason, BlockPos, Random ) -> Boolean,
        val ignore: List<Ignore>,
        val priority: Int = 0,
        var providedVariant: (World, BlockPos, Random, HybridAquaticFishEntity) -> String = {_,_,_,_ ->
            variantName
        },
    ) {
        fun getProvidedVariant(fish: HybridAquaticFishEntity) : String {
            return providedVariant(fish.world, fish.blockPos, fish.random, fish)
        }

        fun createLootTableKey(type: EntityType<*>): RegistryKey<LootTable>? {
            return type.lootTableKey.map { lootTable ->
                val lootTableId = lootTable.value
                RegistryKey.of(RegistryKeys.LOOT_TABLE, lootTableId.withSuffixedPath("_$variantName"))
            }.orElse(null)
        }

        companion object {
            /**
             * Creates a biome variant of a fish
             */
            fun biomeVariant(variantName: String, biomes : TagKey<Biome>, ignore : List<Ignore> = emptyList()): FishVariant {
                return FishVariant(variantName, { world, _, pos, _ ->
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
