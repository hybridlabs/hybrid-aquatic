package dev.hybridlabs.aquatic.entity.crustacean

import dev.hybridlabs.aquatic.tag.HybridAquaticBlockTags
import net.minecraft.block.Blocks
import net.minecraft.entity.*
import net.minecraft.entity.ai.control.MoveControl
import net.minecraft.entity.ai.goal.*
import net.minecraft.entity.ai.pathing.EntityNavigation
import net.minecraft.entity.damage.DamageSource
import net.minecraft.entity.data.DataTracker
import net.minecraft.entity.data.TrackedData
import net.minecraft.entity.data.TrackedDataHandlerRegistry
import net.minecraft.entity.mob.WaterCreatureEntity
import net.minecraft.nbt.NbtCompound
import net.minecraft.sound.SoundEvent
import net.minecraft.sound.SoundEvents
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.random.Random
import net.minecraft.world.LocalDifficulty
import net.minecraft.world.ServerWorldAccess
import net.minecraft.world.World
import net.minecraft.world.WorldAccess
import software.bernie.geckolib.animatable.GeoEntity
import software.bernie.geckolib.core.animatable.GeoAnimatable
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache
import software.bernie.geckolib.core.animation.*
import software.bernie.geckolib.core.animation.AnimationState
import software.bernie.geckolib.core.`object`.PlayState
import software.bernie.geckolib.util.GeckoLibUtil

@Suppress("DEPRECATION", "LeakingThis")
open class HybridAquaticCrustaceanEntity(
    type: EntityType<out HybridAquaticCrustaceanEntity>,
    world: World,
    private val variantCount: Int = 1,
    open val canDig: Boolean,
    open val canDance: Boolean,
    open val canWalkOnLand: Boolean,
) : WaterCreatureEntity(type, world), GeoEntity {
    private val factory = GeckoLibUtil.createInstanceCache(this)
    private var landNavigation: EntityNavigation = createNavigation(world)
    private var songSource: BlockPos? = null
    private var songPlaying: Boolean = false
    private var attemptAttack: Boolean
        get() = dataTracker.get(ATTEMPT_ATTACK)
        set(attemptAttack) {
            dataTracker.set(ATTEMPT_ATTACK, attemptAttack)
        }

    var diggingCooldown: Int = 0
    var isDigging: Boolean
        get() = dataTracker.get(IS_DIGGING)
        set(bool) {
            dataTracker.set(IS_DIGGING, bool)
        }

    private var moistness: Int
        get() = dataTracker.get(MOISTNESS)
        set(moistness) {
            dataTracker.set(MOISTNESS, moistness)
        }

    var variant: Int
        get() = dataTracker.get(VARIANT).coerceAtLeast(0).coerceAtMost(variantCount-1)
        set(int) {
            dataTracker.set(VARIANT, int)
        }

    var size: Int
        get() = dataTracker.get(CRUSTACEAN_SIZE)
        set(size) {
            dataTracker.set(CRUSTACEAN_SIZE, size)
        }

    init {
        stepHeight = 1.0F
        moveControl = MoveControl(this)
        navigation = this.landNavigation
    }

    override fun initDataTracker() {
        dataTracker.startTracking(MOISTNESS, getMaxMoistness())
        dataTracker.startTracking(IS_DIGGING, false)
        dataTracker.startTracking(ATTEMPT_ATTACK, false)
        dataTracker.startTracking(VARIANT, 0)
        dataTracker.startTracking(CRUSTACEAN_SIZE, 0)
        super.initDataTracker()
    }

    override fun initGoals() {
        super.initGoals()
        goalSelector.add(1, EscapeDangerGoal(this, 0.35))
        goalSelector.add(1, MoveIntoWaterGoal(this))
        goalSelector.add(2, WanderAroundGoal(this, 0.35, 10))
        goalSelector.add(2, LookAroundGoal(this))
    }

    override fun initialize(
        world: ServerWorldAccess,
        difficulty: LocalDifficulty,
        spawnReason: SpawnReason,
        entityData: EntityData?,
        entityNbt: NbtCompound?
    ): EntityData? {
        this.air = this.maxAir
        this.variant = this.random.nextInt(variantCount)
        this.size = this.random.nextBetween(getMinSize(),getMaxSize())
        return super.initialize(world, difficulty, spawnReason, entityData, entityNbt)
    }
    override fun hasNoDrag(): Boolean {
        return false
    }

    override fun shouldSwimInFluids(): Boolean {
        return false
    }

    override fun setNearbySongPlaying(songPosition: BlockPos?, playing: Boolean) {
        songSource = songPosition
        songPlaying = playing
        super.setNearbySongPlaying(songPosition, playing)
    }

    override fun mobTick() {
        if (diggingCooldown > 0) {
            diggingCooldown--
            if (isDigging) {
                this.movementSpeed = 0.0F
            }
        }

        super.mobTick()
    }

    override fun getGroup(): EntityGroup? {
        return EntityGroup.ARTHROPOD
    }

    protected open fun getMinSize(): Int {
        return 0
    }

    protected open fun getMaxSize(): Int {
        return 0
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
    }

    override fun tickMovement() {
        val songSourceCopy = songSource
        if (songSourceCopy == null || !songSourceCopy.isWithinDistance(pos, 3.46) || !world.getBlockState(songSourceCopy).isOf(Blocks.JUKEBOX)) {
            songPlaying = false
            songSource = null
        }
        super.tickMovement()
    }

    override fun isSneaking(): Boolean {
        return true
    }

    override fun tickWaterBreathingAir(air: Int) {}

    private fun getMaxMoistness(): Int {
        return if (canWalkOnLand)
            -1
        else
            1200
    }

    override fun writeCustomDataToNbt(nbt: NbtCompound) {
        super.writeCustomDataToNbt(nbt)
        nbt.putInt(DIGGING_COOLDOWN_KEY, diggingCooldown)
        nbt.putInt(MOISTNESS_KEY, moistness)
        nbt.putInt(VARIANT_KEY, variant)
        nbt.putInt(CRUSTACEAN_SIZE_KEY, size)
    }

    override fun readCustomDataFromNbt(nbt: NbtCompound) {
        super.readCustomDataFromNbt(nbt)
        diggingCooldown = nbt.getInt(DIGGING_COOLDOWN_KEY)
        moistness = nbt.getInt(MOISTNESS_KEY)
        variant = nbt.getInt(VARIANT_KEY).coerceAtLeast(0).coerceAtMost(variantCount-1)
        size = nbt.getInt(CRUSTACEAN_SIZE_KEY)
    }

    override fun getHurtSound(source: DamageSource): SoundEvent {
        return SoundEvents.ENTITY_TURTLE_EGG_CRACK
    }

    override fun getDeathSound(): SoundEvent {
        return SoundEvents.ENTITY_TURTLE_EGG_BREAK
    }

    override fun getAmbientSound(): SoundEvent {
        return SoundEvents.ENTITY_COD_AMBIENT
    }

    override fun registerControllers(controllerRegistrar: AnimatableManager.ControllerRegistrar) {
        controllerRegistrar.add(
            AnimationController(
                this,
                "controller",
                0,
                ::predicate
            )
        )
    }

    override fun getAnimatableInstanceCache(): AnimatableInstanceCache {
        return factory
    }

    open fun <E : GeoAnimatable> predicate(event: AnimationState<E>): PlayState {
        if (event.isMoving) {
            event.controller.setAnimation(WALK_ANIMATION)
        } else {
            event.controller.setAnimation(IDLE_ANIMATION)
        }
        if (canDig && isDigging) {
            event.controller.setAnimation(DIG_ANIMATION)
        }
        if (canDance && songPlaying)
            event.controller.setAnimation(DANCE_ANIMATION)
        return PlayState.CONTINUE
    }

    internal open class AttackGoal(private val crab: HybridAquaticCrustaceanEntity) : MeleeAttackGoal(crab, 0.4,true) {
        override fun attack(target: LivingEntity, squaredDistance: Double) {
            val d = getSquaredMaxAttackDistance(target)
            if (squaredDistance <= d && this.isCooledDown) {
                resetCooldown()
                mob.tryAttack(target)
                crab.attemptAttack = true
            }
        }

        override fun getSquaredMaxAttackDistance(entity: LivingEntity): Double {
            return (0.25f + entity.width).toDouble()
        }

        override fun start() {
            super.start()
            crab.attemptAttack = false
        }

        override fun stop() {
            super.stop()
            crab.attemptAttack = false
        }
    }

    companion object {
        val MOISTNESS: TrackedData<Int> = DataTracker.registerData(HybridAquaticCrustaceanEntity::class.java, TrackedDataHandlerRegistry.INTEGER)
        val VARIANT: TrackedData<Int> = DataTracker.registerData(HybridAquaticCrustaceanEntity::class.java, TrackedDataHandlerRegistry.INTEGER)
        val CRUSTACEAN_SIZE: TrackedData<Int> = DataTracker.registerData(HybridAquaticCrustaceanEntity::class.java, TrackedDataHandlerRegistry.INTEGER)
        val ATTEMPT_ATTACK: TrackedData<Boolean> = DataTracker.registerData(HybridAquaticCrustaceanEntity::class.java, TrackedDataHandlerRegistry.BOOLEAN)
        val DANCE_ANIMATION: RawAnimation = RawAnimation.begin().then("dance", Animation.LoopType.LOOP)
        val DIG_ANIMATION: RawAnimation = RawAnimation.begin().then("dig", Animation.LoopType.LOOP)
        val WALK_ANIMATION: RawAnimation = RawAnimation.begin().then("walk", Animation.LoopType.LOOP)
        val IDLE_ANIMATION: RawAnimation = RawAnimation.begin().then("idle", Animation.LoopType.LOOP)
        val HIDING_ANIMATION: RawAnimation = RawAnimation.begin().then("hide", Animation.LoopType.LOOP)
        val FLIPPED_ANIMATION: RawAnimation = RawAnimation.begin().then("flipped", Animation.LoopType.LOOP)

        fun canSpawn(
            type: EntityType<out WaterCreatureEntity?>?,
            world: WorldAccess,
            reason: SpawnReason?,
            pos: BlockPos,
            random: Random?
        ): Boolean {
            val topY = world.seaLevel + 6
            val bottomY = world.seaLevel - 48

            return pos.y in bottomY..topY &&
                    world.getBlockState(pos.down()).isIn(HybridAquaticBlockTags.CRABS_SPAWN_ON) &&
                    (world.isWater(pos) || world.isAir(pos))
        }

        fun canUndergroundSpawn(
            type: EntityType<out WaterCreatureEntity?>?,
            world: WorldAccess,
            reason: SpawnReason?,
            pos: BlockPos,
            random: Random?
        ): Boolean {
            val topY = world.seaLevel + 6
            val bottomY = world.seaLevel - 48

            return pos.y in bottomY..topY &&
                    world.getBaseLightLevel(pos, 0) == 0 &&
                    world.getBlockState(pos).isOf(Blocks.WATER) &&
                    world.getBlockState(pos.down()).isIn(HybridAquaticBlockTags.CRABS_SPAWN_ON)
        }

        fun getScaleAdjustment(critter : HybridAquaticCrustaceanEntity, adjustment : Float): Float {
            return 1.0f + (critter.size * adjustment)
        }

        const val MOISTNESS_KEY = "Moistness"
        const val VARIANT_KEY = "Variant"
        const val CRUSTACEAN_SIZE_KEY = "FishSize"

        val IS_DIGGING: TrackedData<Boolean> =
            DataTracker.registerData(HybridAquaticCrustaceanEntity::class.java, TrackedDataHandlerRegistry.BOOLEAN)
        const val DIGGING_COOLDOWN_KEY = "DiggingCooldown"
    }
}
