package dev.hybridlabs.aquatic.entity.cephalopod

import dev.hybridlabs.aquatic.entity.ai.goal.StayInWaterGoal
import dev.hybridlabs.aquatic.entity.fish.HybridAquaticFishEntity
import dev.hybridlabs.aquatic.entity.fish.ray.HybridAquaticRayEntity
import dev.hybridlabs.aquatic.entity.shark.HybridAquaticSharkEntity
import dev.hybridlabs.aquatic.tag.HybridAquaticEntityTags
import net.minecraft.block.BlockState
import net.minecraft.block.Blocks
import net.minecraft.entity.*
import net.minecraft.entity.ai.control.AquaticMoveControl
import net.minecraft.entity.ai.control.YawAdjustingLookControl
import net.minecraft.entity.ai.goal.*
import net.minecraft.entity.ai.pathing.EntityNavigation
import net.minecraft.entity.ai.pathing.PathNodeType
import net.minecraft.entity.ai.pathing.SwimNavigation
import net.minecraft.entity.damage.DamageSource
import net.minecraft.entity.data.DataTracker
import net.minecraft.entity.data.TrackedData
import net.minecraft.entity.data.TrackedDataHandlerRegistry
import net.minecraft.entity.effect.StatusEffects
import net.minecraft.entity.mob.WaterCreatureEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.fluid.FluidState
import net.minecraft.nbt.NbtCompound
import net.minecraft.particle.ParticleEffect
import net.minecraft.particle.ParticleTypes
import net.minecraft.registry.tag.FluidTags
import net.minecraft.registry.tag.TagKey
import net.minecraft.server.world.ServerWorld
import net.minecraft.sound.SoundEvent
import net.minecraft.sound.SoundEvents
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.MathHelper
import net.minecraft.util.math.Vec3d
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
import software.bernie.geckolib.core.animation.AnimationState
import software.bernie.geckolib.core.`object`.PlayState
import software.bernie.geckolib.util.GeckoLibUtil

@Suppress("LeakingThis", "UNUSED_PARAMETER")
open class HybridAquaticCephalopodEntity(
    type: EntityType<out HybridAquaticCephalopodEntity>,
    world: World,
    private val variants: Map<String, CephalopodVariant> = hashMapOf(),
    open val prey: TagKey<EntityType<*>>,
    open val predator: TagKey<EntityType<*>>,
    open val hasInk: Boolean
) : WaterCreatureEntity(type, world), GeoEntity {
    private val factory = GeckoLibUtil.createInstanceCache(this)
    private var tiltAngle: Float = 0f
    private var prevTiltAngle: Float = 0f
    private var rollAngle: Float = 0f
    private var prevRollAngle: Float = 0f
    private var thrustTimer: Float = 0f
    private var prevThrustTimer: Float = 0f
    private var swimVelocityScale = 0f
    private var thrustTimerSpeed = 0f
    private var tentacleAngle: Float = 0f
    private var prevTentacleAngle: Float = 0f
    private var turningSpeed = 0f
    private var swimX = 0f
    private var swimY = 0f
    private var swimZ = 0f

    override fun initGoals() {
        goalSelector.add(2, SwimGoal(this))
        goalSelector.add(2, SwimToRandomPlaceGoal(this, 0.50, 6))
        goalSelector.add(0, StayInWaterGoal(this))
        goalSelector.add(1, EscapeAttackerGoal(this))
        goalSelector.add(1, FleeEntityGoal(this, LivingEntity::class.java, 8.0f, 1.2, 1.0) { !fromFishingNet && it.type.isIn(predator) })
        goalSelector.add(1, FleeEntityGoal(this, PlayerEntity::class.java, 5.0f, 1.0, 1.0) { !fromFishingNet })
        targetSelector.add(1, ActiveTargetGoal(this, LivingEntity::class.java, 10, true, true) { hunger <= 1200 && it.type.isIn(prey) })
    }

    override fun initDataTracker() {
        super.initDataTracker()
        dataTracker.startTracking(MOISTNESS, getMaxMoistness())
        dataTracker.startTracking(CEPHALOPOD_SIZE, 0)
        dataTracker.startTracking(ATTEMPT_ATTACK, false)
        dataTracker.startTracking(HUNGER, MAX_HUNGER)
        dataTracker.startTracking(VARIANT, "")
        dataTracker.startTracking(VARIANT_DATA, NbtCompound())
    }

    override fun initialize(
        world: ServerWorldAccess,
        difficulty: LocalDifficulty,
        spawnReason: SpawnReason,
        entityData: EntityData?,
        entityNbt: NbtCompound?
    ): EntityData? {
        this.air = getMaxMoistness()

        for (pair in variants) if (pair.value.spawnCondition(world, spawnReason, blockPos, random)) {
            variantKey = pair.key
        }

        this.size = this.random.nextBetween(getMinSize(), getMaxSize())
        this.pitch = 0.0f
        return super.initialize(world, difficulty, spawnReason, entityData, entityNbt)
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

            if (world.isClient && isTouchingWater && isAttacking) {
                val rotationVec = getRotationVec(0.0f)
                val offsetY = 0.0f - random.nextFloat()

                for (i in 0..1) {
                    val particleX = x - rotationVec.x * offsetY
                    val particleY = y - rotationVec.y
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

    open fun <E : GeoAnimatable> predicate(event: AnimationState<E>): PlayState {
        if (moistness > 575) {
            event.controller.setAnimation(SWIM_ANIMATION)
            return PlayState.CONTINUE
        }
        if (moistness < 575) {
            event.controller.setAnimation(FLOP_ANIMATION)
            return PlayState.CONTINUE
        }
        return PlayState.STOP
    }

    override fun getActiveEyeHeight(pose: EntityPose?, dimensions: EntityDimensions): Float {
        return dimensions.height * 0.5f
    }

    override fun canImmediatelyDespawn(distanceSquared: Double): Boolean {
        return !hasCustomName() && !fromFishingNet
    }

    init {
        random.setSeed(id.toLong())
        this.thrustTimerSpeed = 1.0f / (random.nextFloat() + 1.0f) * 0.2f
        setPathfindingPenalty(PathNodeType.WATER, 0.0f)
        moveControl = AquaticMoveControl(this, 85, 10, 0.05F, 0.1F, true)
        lookControl = YawAdjustingLookControl(this, 10)
        navigation = SwimNavigation(this, world)
    }

    override fun dropLoot(source: DamageSource, causedByPlayer: Boolean) {
        val attacker = source.attacker
        if (attacker !is HybridAquaticFishEntity && attacker !is HybridAquaticSharkEntity && attacker !is HybridAquaticRayEntity && attacker !is HybridAquaticCephalopodEntity) {
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
        return SoundEvents.ENTITY_SLIME_HURT
    }

    override fun getDeathSound(): SoundEvent {
        return SoundEvents.ENTITY_SLIME_DEATH
    }

    protected open fun getSquirtSound(): SoundEvent? {
        return SoundEvents.ENTITY_SQUID_SQUIRT
    }

    override fun getSoundVolume(): Float {
        return 0.4f
    }

    override fun getMoveEffect(): MoveEffect {
        return MoveEffect.EVENTS
    }

    override fun createNavigation(world: World): EntityNavigation {
        return SwimNavigation(this, world)
    }

    override fun tickMovement() {
        super.tickMovement()
        this.prevTiltAngle = this.tiltAngle
        this.prevRollAngle = this.rollAngle
        this.prevThrustTimer = this.thrustTimer
        this.prevTentacleAngle = this.tentacleAngle
        this.thrustTimer += this.thrustTimerSpeed
        if (thrustTimer.toDouble() > 6.283185307179586) {
            if (world.isClient) {
                this.thrustTimer = 6.2831855f
            } else {
                this.thrustTimer -= 6.2831855f
                if (random.nextInt(10) == 0) {
                    this.thrustTimerSpeed = 1.0f / (random.nextFloat() + 1.0f) * 0.2f
                }

                world.sendEntityStatus(this, 19.toByte())
            }
        }

        if (this.isInsideWaterOrBubbleColumn) {
            if (this.thrustTimer < 3.1415927f) {
                val f = this.thrustTimer / 3.1415927f
                this.tentacleAngle = MathHelper.sin(f * f * 3.1415927f) * 3.1415927f * 0.25f
                if (f.toDouble() > 0.75) {
                    this.swimVelocityScale = 1.0f
                    this.turningSpeed = 1.0f
                } else {
                    this.turningSpeed *= 0.8f
                }
            } else {
                this.tentacleAngle = 0.0f
                this.swimVelocityScale *= 0.9f
                this.turningSpeed *= 0.99f
            }

            if (!world.isClient) {
                this.setVelocity(
                    (this.swimX * this.swimVelocityScale).toDouble(),
                    (this.swimY * this.swimVelocityScale).toDouble(),
                    (this.swimZ * this.swimVelocityScale).toDouble()
                )
            }

            val vec3d = this.velocity
            val d = vec3d.horizontalLength()
            this.bodyYaw += (-(MathHelper.atan2(vec3d.x, vec3d.z).toFloat()) * 57.295776f - this.bodyYaw) * 0.1f
            this.yaw = this.bodyYaw
            this.rollAngle += 3.1415927f * this.turningSpeed * 1.5f
            this.tiltAngle += (-(MathHelper.atan2(d, vec3d.y).toFloat()) * 57.295776f - this.tiltAngle) * 0.1f
        } else {
            this.tentacleAngle = MathHelper.abs(MathHelper.sin(this.thrustTimer)) * 3.1415927f * 0.25f
            if (!world.isClient) {
                var e = velocity.y
                if (this.hasStatusEffect(StatusEffects.LEVITATION)) {
                    e = 0.05 * (getStatusEffect(StatusEffects.LEVITATION)!!.amplifier + 1).toDouble()
                } else if (!this.hasNoGravity()) {
                    e -= 0.08
                }

                this.setVelocity(0.0, e * 0.9800000190734863, 0.0)
            }

            this.tiltAngle += (-90.0f - this.tiltAngle) * 0.02f
        }
    }

    override fun damage(source: DamageSource?, amount: Float): Boolean {
        if (super.damage(source, amount) && this.attacker != null) {
            if (!world.isClient) {
                this.squirt()
            }
            return true
        } else {
            return false
        }
    }

    private fun applyBodyRotations(shootVector: Vec3d): Vec3d {
        var vec3d = shootVector.rotateX(this.prevTiltAngle * 0.017453292f)
        vec3d = vec3d.rotateY(-this.prevBodyYaw * 0.017453292f)
        return vec3d
    }

    private fun squirt() {
        if (hasInk) {
            this.playSound(this.getSquirtSound(), this.soundVolume, this.soundPitch)
            val vec3d = applyBodyRotations(Vec3d(0.0, -1.0, 0.0)).add(this.x, this.y, this.z)

            for (i in 0..29) {
                val vec3d2 = this.applyBodyRotations(
                    Vec3d(
                        random.nextFloat().toDouble() * 0.6 - 0.3, -1.0,
                        random.nextFloat().toDouble() * 0.6 - 0.3
                    )
                )
                val vec3d3 = vec3d2.multiply(0.3 + (random.nextFloat() * 2.0f).toDouble())
                (world as ServerWorld).spawnParticles(
                    this.getInkParticle(),
                    vec3d.x,
                    vec3d.y + 0.5,
                    vec3d.z,
                    0,
                    vec3d3.x,
                    vec3d3.y,
                    vec3d3.z,
                    0.10000000149011612
                )
            }
        }
    }

    protected open fun getInkParticle(): ParticleEffect {
        return ParticleTypes.SQUID_INK
    }

    override fun travel(movementInput: Vec3d?) {
        this.move(MovementType.SELF, this.velocity)
    }

    override fun handleStatus(status: Byte) {
        if (status.toInt() == 19) {
            this.thrustTimer = 0.0f
        } else {
            super.handleStatus(status)
        }
    }

    fun setSwimmingVector(x: Float, y: Float, z: Float) {
        this.swimX = x
        this.swimY = y
        this.swimZ = z
    }

    fun hasSwimmingVector(): Boolean {
        return this.swimX != 0.0f || (this.swimY != 0.0f) || (this.swimZ != 0.0f)
    }

    internal class SwimGoal(private val cephalopod: HybridAquaticCephalopodEntity) : Goal() {
        override fun canStart(): Boolean {
            return true
        }

        override fun tick() {
            val i = cephalopod.despawnCounter
            if (i > 100) {
                cephalopod.setSwimmingVector(0.0f, 0.0f, 0.0f)
            } else if (cephalopod.random.nextInt(toGoalTicks(50)) == 0 || !cephalopod.touchingWater || !cephalopod.hasSwimmingVector()) {
                val f = cephalopod.random.nextFloat() * 6.2831855f
                val g = MathHelper.cos(f) * 0.2f
                val h = -0.1f + cephalopod.random.nextFloat() * 0.2f
                val j = MathHelper.sin(f) * 0.2f
                cephalopod.setSwimmingVector(g, h, j)
            }
        }
    }

    //region properties

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

    // endregion

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
                "controller",
                5,
                ::predicate
            )
        )
    }

    override fun getAnimatableInstanceCache(): AnimatableInstanceCache {
        return factory
    }

    protected open fun hasSelfControl(): Boolean {
        return true
    }

    protected open fun getMinSize() : Int {
        return 0
    }

    protected open fun getMaxSize() : Int {
        return 0
    }

    private var fromFishingNet = false

    internal class SwimToRandomPlaceGoal(private val cephalopod: HybridAquaticCephalopodEntity, d: Double, i: Int) :
        SwimAroundGoal(cephalopod, 1.0, 40) {
        override fun canStart(): Boolean {
            return cephalopod.hasSelfControl() && super.canStart()
        }
    }

    internal class EscapeAttackerGoal(private val cephalopod: HybridAquaticCephalopodEntity) : Goal() {
        private var timer = 0

        override fun canStart(): Boolean {
            val attacker: LivingEntity? = cephalopod.attacker
            return cephalopod.isTouchingWater && attacker != null && cephalopod.squaredDistanceTo(attacker) < 100.0
        }

        override fun start() {
            timer = 0
        }

        override fun shouldRunEveryTick(): Boolean {
            return true
        }

        override fun tick() {
            timer++
            val attacker: LivingEntity? = cephalopod.attacker
            if (attacker != null) {
                var vec3d = Vec3d(
                    cephalopod.x - attacker.x,
                    cephalopod.y - attacker.y,
                    cephalopod.z - attacker.z
                )
                val blockState: BlockState = cephalopod.world.getBlockState(
                    BlockPos.ofFloored(
                        cephalopod.x + vec3d.x,
                        cephalopod.y + vec3d.y,
                        cephalopod.z + vec3d.z
                    )
                )
                val fluidState: FluidState = cephalopod.world.getFluidState(
                    BlockPos.ofFloored(
                        cephalopod.x + vec3d.x,
                        cephalopod.y + vec3d.y,
                        cephalopod.z + vec3d.z
                    )
                )
                if (fluidState.isIn(FluidTags.WATER) || blockState.isAir) {
                    val d = vec3d.length()
                    if (d > 0.0) {
                        vec3d = vec3d.normalize().multiply(3.0)
                    }

                    if (blockState.isAir) {
                        vec3d = vec3d.subtract(0.0, vec3d.y, 0.0)
                    }

                    cephalopod.setSwimmingVector(
                        vec3d.x.toFloat() / 20.0f,
                        vec3d.y.toFloat() / 20.0f,
                        vec3d.z.toFloat() / 20.0f
                    )
                }

                if (timer % 10 == 5) {
                    cephalopod.world.addParticle(
                        ParticleTypes.BUBBLE,
                        cephalopod.x,
                        cephalopod.y,
                        cephalopod.z,
                        0.0, 0.0, 0.0
                    )
                }
            }
        }
    }

    internal class AttackGoal(private val cephalopod: HybridAquaticCephalopodEntity) : MeleeAttackGoal(cephalopod, 1.0,true) {
        override fun canStart(): Boolean {
            return !cephalopod.fromFishingNet && super.canStart()
        }

        override fun attack(target: LivingEntity) {
            val squaredDistance = target.squaredDistanceTo(mob)
            val d = getSquaredMaxAttackDistance(target)
            if (squaredDistance <= d && this.isCooledDown) {
                resetCooldown()
                mob.tryAttack(target)
                cephalopod.isSprinting = false
                cephalopod.attemptAttack = true

                if (target.health <= 0)
                    cephalopod.eatFish(target.type)
            }
        }

        private fun getSquaredMaxAttackDistance(entity: LivingEntity): Double {
            return (1.0f + entity.width).toDouble()
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

    override fun tryAttack(target: Entity?): Boolean {
        if (super.tryAttack(target)) {

            playSound(SoundEvents.ENTITY_FOX_EAT,1.0F,0.0F)

            return true
        } else {
            return false
        }
    }

    companion object {
        val MOISTNESS: TrackedData<Int> = DataTracker.registerData(HybridAquaticCephalopodEntity::class.java, TrackedDataHandlerRegistry.INTEGER)
        val CEPHALOPOD_SIZE: TrackedData<Int> = DataTracker.registerData(HybridAquaticCephalopodEntity::class.java, TrackedDataHandlerRegistry.INTEGER)
        val HUNGER: TrackedData<Int> = DataTracker.registerData(HybridAquaticFishEntity::class.java, TrackedDataHandlerRegistry.INTEGER)
        val ATTEMPT_ATTACK: TrackedData<Boolean> = DataTracker.registerData(HybridAquaticCephalopodEntity::class.java, TrackedDataHandlerRegistry.BOOLEAN)
        val VARIANT: TrackedData<String> = DataTracker.registerData(HybridAquaticCephalopodEntity::class.java, TrackedDataHandlerRegistry.STRING)
        var VARIANT_DATA: TrackedData<NbtCompound> = DataTracker.registerData(HybridAquaticCephalopodEntity::class.java, TrackedDataHandlerRegistry.NBT_COMPOUND)

        const val MAX_HUNGER = 1200
        const val HUNGER_KEY = "Hunger"
        const val MOISTNESS_KEY = "Moistness"
        const val VARIANT_KEY = "Variant"
        const val VARIANT_DATA_KEY = "VariantData"
        const val CEPHALOPOD_SIZE_KEY = "CephalopodSize"

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

        fun getScaleAdjustment(jellyfish : HybridAquaticCephalopodEntity, adjustment : Float): Float {
            return 1.0f + (jellyfish.size * adjustment)
        }
    }

    @Suppress("UNUSED")
    data class CephalopodVariant(
        var variantName : String,
        val spawnCondition: (WorldAccess, SpawnReason, BlockPos, Random ) -> Boolean,
        var ignore: List<Ignore> = emptyList()
    ) {
        companion object {
            /**
             * Creates a biome variant of a cephalopod
             */
            fun biomeVariant(variantName: String, biomes : TagKey<Biome>, ignore : List<Ignore> = emptyList()): CephalopodVariant {
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