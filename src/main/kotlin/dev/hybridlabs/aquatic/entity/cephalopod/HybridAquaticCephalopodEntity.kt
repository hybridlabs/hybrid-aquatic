package dev.hybridlabs.aquatic.entity.cephalopod

import dev.hybridlabs.aquatic.effect.HybridAquaticStatusEffects
import dev.hybridlabs.aquatic.entity.fish.HybridAquaticFishEntity
import dev.hybridlabs.aquatic.entity.shark.HybridAquaticSharkEntity
import net.minecraft.block.Blocks
import net.minecraft.entity.EntityData
import net.minecraft.entity.EntityType
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.SpawnReason
import net.minecraft.entity.ai.control.AquaticMoveControl
import net.minecraft.entity.ai.control.YawAdjustingLookControl
import net.minecraft.entity.ai.goal.ActiveTargetGoal
import net.minecraft.entity.ai.goal.EscapeDangerGoal
import net.minecraft.entity.ai.goal.MeleeAttackGoal
import net.minecraft.entity.ai.goal.SwimAroundGoal
import net.minecraft.entity.ai.pathing.EntityNavigation
import net.minecraft.entity.ai.pathing.PathNodeType
import net.minecraft.entity.ai.pathing.SwimNavigation
import net.minecraft.entity.damage.DamageSource
import net.minecraft.entity.data.DataTracker
import net.minecraft.entity.data.TrackedData
import net.minecraft.entity.data.TrackedDataHandlerRegistry
import net.minecraft.entity.effect.StatusEffectInstance
import net.minecraft.entity.effect.StatusEffects
import net.minecraft.entity.mob.HostileEntity.isSpawnDark
import net.minecraft.entity.mob.WaterCreatureEntity
import net.minecraft.nbt.NbtCompound
import net.minecraft.particle.ParticleEffect
import net.minecraft.particle.ParticleTypes
import net.minecraft.registry.tag.FluidTags
import net.minecraft.registry.tag.TagKey
import net.minecraft.server.world.ServerWorld
import net.minecraft.sound.SoundEvent
import net.minecraft.sound.SoundEvents
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Box
import net.minecraft.util.math.Vec3d
import net.minecraft.util.math.random.Random
import net.minecraft.world.LocalDifficulty
import net.minecraft.world.ServerWorldAccess
import net.minecraft.world.World
import net.minecraft.world.WorldAccess
import net.minecraft.world.biome.Biome
import software.bernie.geckolib.animatable.GeoEntity
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache
import software.bernie.geckolib.animation.AnimatableManager
import software.bernie.geckolib.animation.AnimationController
import software.bernie.geckolib.animation.AnimationState
import software.bernie.geckolib.animation.EasingType
import software.bernie.geckolib.constant.DefaultAnimations
import software.bernie.geckolib.util.GeckoLibUtil

open class HybridAquaticCephalopodEntity(
    type: EntityType<out HybridAquaticCephalopodEntity>,
    world: World,
    private val variants: Map<String, CephalopodVariant> = hashMapOf(),
    open val prey: TagKey<EntityType<*>>,
    open val predator: TagKey<EntityType<*>>,
    open var hasInk: Boolean,
    open var hasGlowInk: Boolean
) : WaterCreatureEntity(type, world), GeoEntity {
    private val factory = GeckoLibUtil.createInstanceCache(this)

    override fun initGoals() {
        goalSelector.add(0, EscapeDangerGoal(this, 1.25))
        goalSelector.add(3, SwimAroundGoal(this, 1.0, 10))
        goalSelector.add(2, CephalopodAttackGoal(this))
        targetSelector.add(
            1,
            ActiveTargetGoal(this, LivingEntity::class.java, 10, true, true) { hunger <= 1200 && it.type.isIn(prey) })
    }

    override fun initDataTracker(builder: DataTracker.Builder) {
        super.initDataTracker(builder)
        builder.add(MOISTNESS, getMaxMoistness())
        builder.add(CEPHALOPOD_SIZE, 0)
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

        for (pair in variants) if (pair.value.spawnCondition(world, spawnReason, blockPos, random)) {
            variantKey = pair.key
        }

        this.size = this.random.nextBetween(getMinSize(), getMaxSize())
        this.pitch = 0.0f
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

            if (!this.isSubmergedInWater) {
                this.pitch = -90.0f
                this.yaw = this.prevYaw
                this.headYaw = this.prevHeadYaw
            }
        }

        isSprinting = isAttacking

        if (hunger > 0) hunger -= 1
    }

    override fun tickWaterBreathingAir(air: Int) {}

    private fun getMaxMoistness(): Int {
        return 600
    }

    override fun damage(source: DamageSource, amount: Float): Boolean {
        if (super.damage(source, amount) && this.attacker != null) {
            if (!world.isClient) {
                if (this.hasInk || this.hasGlowInk) {
                    this.squirt()
                }

                val attackerPos = this.attacker?.pos
                if (attackerPos != null) {
                    val directionAway = this.pos.subtract(attackerPos).normalize().multiply(10.0)
                    val targetPos = this.pos.add(directionAway.x, 0.0, directionAway.z)

                    this.navigation.startMovingTo(targetPos.x, targetPos.y, targetPos.z, 1.5)
                }
            }
            return true
        }
        return false
    }

    private fun squirt() {
        this.playSound(this.getSquirtSound(), this.soundVolume, this.soundPitch)

        val entityPosition = Vec3d(this.x, this.y, this.z)
        val radius = 3.0

        val affectedEntities = world.getEntitiesByClass(
            LivingEntity::class.java,
            Box(
                entityPosition.x - radius, entityPosition.y - radius, entityPosition.z - radius,
                entityPosition.x + radius, entityPosition.y + radius, entityPosition.z + radius
            )
        ) { it != this && it.isAlive }

        for (entity in affectedEntities) {
            entity.addStatusEffect(StatusEffectInstance(StatusEffects.BLINDNESS, 100, 0))
            entity.addStatusEffect(StatusEffectInstance(StatusEffects.DARKNESS, 100, 0))
        }

        for (i in 0..199) {
            val offsetX = (random.nextDouble() - 0.5) * 2.0
            val offsetY = (random.nextDouble() - 0.5) * 2.0
            val offsetZ = (random.nextDouble() - 0.5) * 2.0

            val randomMultiplier = 0.5 + random.nextDouble() * 1.5
            val velocity = Vec3d(offsetX, offsetY, offsetZ).normalize().multiply(randomMultiplier)

            (world as ServerWorld).spawnParticles(
                this.getInkParticle(),
                entityPosition.x,
                entityPosition.y,
                entityPosition.z,
                1,
                velocity.x * 0.25,
                velocity.y * 0.25,
                velocity.z * 0.25,
                0.1
            )
        }
    }

    protected open fun getInkParticle(): ParticleEffect {
        return if (this.hasGlowInk) {
            ParticleTypes.GLOW_SQUID_INK
        } else {
            ParticleTypes.SQUID_INK
        }
    }

    override fun writeCustomDataToNbt(nbt: NbtCompound) {
        super.writeCustomDataToNbt(nbt)
        nbt.putInt(MOISTNESS_KEY, moistness)
        nbt.putString(VARIANT_KEY, variantKey)
        nbt.put(VARIANT_DATA_KEY, variantData)
        nbt.putInt(CEPHALOPOD_SIZE_KEY, size)
        nbt.putInt(HUNGER_KEY, hunger)
        nbt.putBoolean("FromFishingNet", fromFishingNet)
    }

    override fun readCustomDataFromNbt(nbt: NbtCompound) {
        super.readCustomDataFromNbt(nbt)
        moistness = nbt.getInt(MOISTNESS_KEY)
        variantKey = nbt.getString(VARIANT_KEY)
        variantData = nbt.getCompound(VARIANT_DATA_KEY)
        size = nbt.getInt(CEPHALOPOD_SIZE_KEY)
        hunger = nbt.getInt(HUNGER_KEY)
        fromFishingNet = nbt.getBoolean("FromFishingNet")
    }

    override fun canImmediatelyDespawn(distanceSquared: Double): Boolean {
        return !fromFishingNet && !hasCustomName()
    }

    init {
        setPathfindingPenalty(PathNodeType.WATER, 0.0f)
        setPathfindingPenalty(PathNodeType.WALKABLE, 10.0f)
        moveControl = AquaticMoveControl(this, 85, 10, 0.05F, 0.1F, true)
        lookControl = YawAdjustingLookControl(this, 10)
        navigation = SwimNavigation(this, world)
    }

    override fun dropLoot(source: DamageSource, causedByPlayer: Boolean) {
        val attacker = source.attacker
        if (attacker !is HybridAquaticFishEntity && attacker !is HybridAquaticSharkEntity && attacker !is HybridAquaticCephalopodEntity) {
            super.dropLoot(source, causedByPlayer)
        }
    }

    override fun getLimitPerChunk(): Int {
        return 2
    }

    override fun getAmbientSound(): SoundEvent {
        return SoundEvents.ENTITY_SQUID_AMBIENT
    }

    override fun getHurtSound(source: DamageSource?): SoundEvent {
        return SoundEvents.ENTITY_SQUID_HURT
    }

    override fun getDeathSound(): SoundEvent {
        return SoundEvents.ENTITY_SQUID_DEATH
    }

    private fun getSquirtSound(): SoundEvent {
        return SoundEvents.ENTITY_SQUID_SQUIRT
    }

    override fun createNavigation(world: World): EntityNavigation {
        return SwimNavigation(this, world)
    }


    private var moistness: Int
        get() = dataTracker.get(MOISTNESS)
        set(moistness) {
            dataTracker.set(MOISTNESS, moistness)
        }

    var size: Int
        get() = dataTracker.get(CEPHALOPOD_SIZE)
        set(size) {
            dataTracker.set(CEPHALOPOD_SIZE, size)
        }

    var hunger: Int
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
        get() = dataTracker.get(VARIANT)
        private set(value) {
            dataTracker.set(VARIANT, value)
            dataTracker.set(VARIANT, value)
        }

    var variant: CephalopodVariant?
        get() = variants[variantKey]
        private set(value) {
            variants
        }

    override fun getMaxAir(): Int {
        return 600
    }

    public override fun getNextAirOnLand(air: Int): Int {
        return this.maxAir
    }

    override fun registerControllers(controllerRegistrar: AnimatableManager.ControllerRegistrar) {
        controllerRegistrar.add(
            AnimationController(
                this,
                "Swim/Run",
                20
            ) { state: AnimationState<HybridAquaticCephalopodEntity> ->
                if (!this.isSubmergedInWater && isOnGround) {
                    state.setAndContinue(DefaultAnimations.SIT)
                } else {
                    if (state.isMoving) {
                        state.setAndContinue(if (this.isSprinting) DefaultAnimations.RUN else DefaultAnimations.SWIM)
                    } else {
                        state.setAndContinue(DefaultAnimations.IDLE)
                    }
                }
            }.setOverrideEasingType(EasingType.EASE_IN_OUT_SINE)
        )
    }

    override fun getAnimatableInstanceCache(): AnimatableInstanceCache {
        return factory
    }

    protected open fun getMinSize(): Int {
        return 0
    }

    protected open fun getMaxSize(): Int {
        return 0
    }

    private var fromFishingNet = false

    internal class CephalopodAttackGoal(private val cephalopod: HybridAquaticCephalopodEntity) : MeleeAttackGoal(cephalopod, 1.0, true) {
        override fun canStart(): Boolean {
            return !cephalopod.fromFishingNet && super.canStart()
        }

        override fun attack(target: LivingEntity) {
            if (canAttack(target)) {
                resetCooldown()
                mob.tryAttack(target)
                cephalopod.isSprinting = true
                cephalopod.attemptAttack = true

                if (target.health <= 0)
                    cephalopod.hunger = HybridAquaticSharkEntity.MAX_HUNGER
                cephalopod.health = cephalopod.maxHealth
            }
        }

        override fun start() {
            super.start()
            cephalopod.attemptAttack = false
        }

        override fun stop() {
            super.stop()
            cephalopod.attemptAttack = false
        }
    }

    companion object {
        val MOISTNESS: TrackedData<Int> =
            DataTracker.registerData(HybridAquaticCephalopodEntity::class.java, TrackedDataHandlerRegistry.INTEGER)
        val CEPHALOPOD_SIZE: TrackedData<Int> =
            DataTracker.registerData(HybridAquaticCephalopodEntity::class.java, TrackedDataHandlerRegistry.INTEGER)
        val HUNGER: TrackedData<Int> =
            DataTracker.registerData(HybridAquaticFishEntity::class.java, TrackedDataHandlerRegistry.INTEGER)
        val ATTEMPT_ATTACK: TrackedData<Boolean> =
            DataTracker.registerData(HybridAquaticCephalopodEntity::class.java, TrackedDataHandlerRegistry.BOOLEAN)
        val VARIANT: TrackedData<String> =
            DataTracker.registerData(HybridAquaticCephalopodEntity::class.java, TrackedDataHandlerRegistry.STRING)
        var VARIANT_DATA: TrackedData<NbtCompound> =
            DataTracker.registerData(HybridAquaticCephalopodEntity::class.java, TrackedDataHandlerRegistry.NBT_COMPOUND)

        const val MAX_HUNGER = 2400
        const val HUNGER_KEY = "Hunger"
        const val MOISTNESS_KEY = "Moistness"
        const val VARIANT_KEY = "Variant"
        const val VARIANT_DATA_KEY = "VariantData"
        const val CEPHALOPOD_SIZE_KEY = "CephalopodSize"

                fun canSpawn(
            type: EntityType<out WaterCreatureEntity>,
            world: ServerWorldAccess,
            reason: SpawnReason,
            pos: BlockPos,
            random: Random
        ): Boolean {
            val topY = world.seaLevel - 4
            val bottomY = world.seaLevel - 24

            return pos.y in bottomY..topY &&
                    world.getFluidState(pos).isIn(FluidTags.WATER) &&
                    world.getFluidState(pos.down()).isIn(FluidTags.WATER) &&
                    world.getBlockState(pos.up()).isOf(Blocks.WATER) &&
                    world.isSkyVisibleAllowingSea(pos) &&
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
                    world.getFluidState(pos).isIn(FluidTags.WATER) &&
                    world.getFluidState(pos.down()).isIn(FluidTags.WATER) &&
                    world.getBlockState(pos.up()).isOf(Blocks.WATER) &&
                    isSpawnDark(world, pos, random)
        }

        fun getScaleAdjustment(cephalopod: HybridAquaticCephalopodEntity, adjustment: Float): Float {
            return 1.0f + (cephalopod.size * adjustment)
        }
    }

        data class CephalopodVariant(
        var variantName: String,
        val spawnCondition: (WorldAccess, SpawnReason, BlockPos, Random) -> Boolean,
        var ignore: List<Ignore> = emptyList()
    ) {
        companion object {
            /**
             * Creates a biome variant of a cephalopod
             */
            fun biomeVariant(
                variantName: String,
                biomes: TagKey<Biome>,
                ignore: List<Ignore> = emptyList()
            ): CephalopodVariant {
                return CephalopodVariant(variantName, { world, _, pos, _ ->
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

}
