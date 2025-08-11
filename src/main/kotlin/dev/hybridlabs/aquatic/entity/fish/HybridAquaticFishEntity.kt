package dev.hybridlabs.aquatic.entity.fish

import dev.hybridlabs.aquatic.entity.cephalopod.HybridAquaticCephalopodEntity
import dev.hybridlabs.aquatic.entity.shark.HybridAquaticSharkEntity
import net.minecraft.entity.EntityData
import net.minecraft.entity.EntityType
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.SpawnReason
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
import net.minecraft.entity.mob.WaterCreatureEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.nbt.NbtCompound
import net.minecraft.registry.tag.TagKey
import net.minecraft.sound.SoundEvent
import net.minecraft.sound.SoundEvents
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.random.Random
import net.minecraft.world.LocalDifficulty
import net.minecraft.world.ServerWorldAccess
import net.minecraft.world.World
import software.bernie.geckolib.animatable.GeoEntity
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache
import software.bernie.geckolib.animation.AnimatableManager
import software.bernie.geckolib.animation.AnimationController
import software.bernie.geckolib.animation.AnimationState
import software.bernie.geckolib.animation.RawAnimation
import software.bernie.geckolib.constant.DefaultAnimations
import software.bernie.geckolib.util.GeckoLibUtil

@Suppress("LeakingThis", "UNUSED_PARAMETER", "DEPRECATION")
open class HybridAquaticFishEntity(
    type: EntityType<out HybridAquaticFishEntity>,
    world: World,
    open val prey: List<TagKey<EntityType<*>>>,
    open val predator: List<TagKey<EntityType<*>>>,
) : WaterCreatureEntity(type, world), GeoEntity {

    private val factory = GeckoLibUtil.createInstanceCache(this)

    override fun initGoals() {
        super.initGoals()
        goalSelector.add(0, MoveIntoWaterGoal(this))
        goalSelector.add(0, EscapeDangerGoal(this, 1.25))
        goalSelector.add(1, SwimAroundGoal(this, 1.0, 10))
        goalSelector.add(1, LookAroundGoal(this))
        goalSelector.add(2, LookAtEntityGoal(this, PlayerEntity::class.java, 6.0f))
        goalSelector.add(4, FishAttackGoal(this))
        targetSelector.add(1, ActiveTargetGoal(this, LivingEntity::class.java, 10, true, true) { entity: LivingEntity -> prey.any { preyType -> entity.type.isIn(preyType) } && hunger < MAX_HUNGER / 4 })
    }

    override fun initDataTracker(builder: DataTracker.Builder) {
        super.initDataTracker(builder)
        builder.add(MOISTNESS, getMaxMoistness())
        builder.add(FISH_SIZE, 0)
        builder.add(ATTEMPT_ATTACK, false)
        builder.add(HUNGER, MAX_HUNGER)
    }

    override fun initialize(
        world: ServerWorldAccess,
        difficulty: LocalDifficulty,
        spawnReason: SpawnReason,
        entityData: EntityData?
    ): EntityData? {
        this.air = getMaxMoistness()
        pitch = 0.0f
        yaw = 0.0f
        this.size = this.random.nextBetween(getMinSize(), getMaxSize())
        return super.initialize(world, difficulty, spawnReason, entityData)
    }

    override fun createNavigation(world: World): EntityNavigation {
        return SwimNavigation(this, world)
    }

    override fun tick() {
        super.tick()
        if (isAiDisabled) {
            return
        }

        if (isSubmergedInWater) {
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

    override fun dropLoot(source: DamageSource, causedByPlayer: Boolean) {
        val attacker = source.attacker
        if (attacker !is HybridAquaticFishEntity && attacker !is HybridAquaticSharkEntity && attacker !is HybridAquaticCephalopodEntity) {
            super.dropLoot(source, causedByPlayer)
        }
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
        nbt.putInt(FISH_SIZE_KEY, size)
        nbt.putInt(HUNGER_KEY, hunger)
        nbt.putBoolean("FromFishingNet", fromFishingNet)
    }

    private var fromFishingNet = false

    override fun readCustomDataFromNbt(nbt: NbtCompound) {
        super.readCustomDataFromNbt(nbt)
        moistness = nbt.getInt(MOISTNESS_KEY)
        size = nbt.getInt(FISH_SIZE_KEY)
        hunger = nbt.getInt(HUNGER_KEY)
        fromFishingNet = nbt.getBoolean("FromFishingNet")
    }

    override fun canImmediatelyDespawn(distanceSquared: Double): Boolean {
        return !this.fromFishingNet && !this.hasCustomName()
    }

    override fun getLimitPerChunk(): Int {
        return 8
    }

    //#region SFX
    open val flopSound: SoundEvent = SoundEvents.ENTITY_PUFFER_FISH_FLOP

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

    //#region end

    //#region Properties

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

    //#region Animations
    override fun registerControllers(controllerRegistrar: AnimatableManager.ControllerRegistrar) {
        controllerRegistrar.add(
            AnimationController(this, "Swim/Idle/Flop", 4
            ) { state: AnimationState<HybridAquaticFishEntity> ->
                when {
                    state.isMoving && isSubmergedInWater -> state.setAndContinue(DefaultAnimations.SWIM)
                    !this.isSubmergedInWater && !this.isSwimming && shouldFlopOnLand() && this.moistness < 580 -> state.setAndContinue(FLOP)
                    else -> state.setAndContinue(DefaultAnimations.IDLE)
                }
            }
        )
    }

    override fun getAnimatableInstanceCache(): AnimatableInstanceCache {
        return factory
    }

    // endregion

    init {
        setPathfindingPenalty(PathNodeType.WATER, 0.0f)
        setPathfindingPenalty(PathNodeType.WATER_BORDER, -1.0f)
        setPathfindingPenalty(PathNodeType.WALKABLE, -1.0f)
        moveControl = AquaticMoveControl(this, 85, 5, movementSpeed, 0.1f, true)
        lookControl = YawAdjustingLookControl(this, 10)
        navigation = SwimNavigation(this, world)
    }

    internal class FishAttackGoal(private val fish: HybridAquaticFishEntity) : MeleeAttackGoal(fish, 1.0, true) {
        override fun canStart(): Boolean {
            return !fish.fromFishingNet && super.canStart()
        }

        override fun attack(target: LivingEntity) {
            if (this.canAttack(target)) {
                this.resetCooldown()
                mob.tryAttack(target)
                fish.isSprinting = true
                fish.attemptAttack = true

                if (target.health <= 0)
                    fish.hunger = MAX_HUNGER
                fish.health = fish.maxHealth
            }
        }
    }

    companion object {
        val MOISTNESS: TrackedData<Int> =
            DataTracker.registerData(HybridAquaticFishEntity::class.java, TrackedDataHandlerRegistry.INTEGER)
        val FISH_SIZE: TrackedData<Int> =
            DataTracker.registerData(HybridAquaticFishEntity::class.java, TrackedDataHandlerRegistry.INTEGER)
        val HUNGER: TrackedData<Int> =
            DataTracker.registerData(HybridAquaticFishEntity::class.java, TrackedDataHandlerRegistry.INTEGER)
        val ATTEMPT_ATTACK: TrackedData<Boolean> =
            DataTracker.registerData(HybridAquaticFishEntity::class.java, TrackedDataHandlerRegistry.BOOLEAN)

        val FLOP: RawAnimation = RawAnimation.begin().thenPlay("misc.flop")

        const val MAX_HUNGER = 2400
        const val HUNGER_KEY = "Hunger"
        const val MOISTNESS_KEY = "Moistness"
        const val FISH_SIZE_KEY = "FishSize"

        fun canShallowSpawn(
            type: EntityType<out WaterCreatureEntity>,
            world: ServerWorldAccess,
            reason: SpawnReason,
            pos: BlockPos,
            random: Random
        ): Boolean {
            val topY = world.seaLevel - 1
            val bottomY = world.seaLevel - 8

            return pos.y in bottomY..topY &&
                    world.isWater(pos) &&
                    world.isSkyVisibleAllowingSea(pos)
        }

        fun canSpawn(
            type: EntityType<out WaterCreatureEntity>,
            world: ServerWorldAccess,
            reason: SpawnReason,
            pos: BlockPos,
            random: Random
        ): Boolean {
            val topY = world.seaLevel - 12
            val bottomY = world.seaLevel - 24

            return pos.y in bottomY..topY &&
                    world.isWater(pos) &&
                    world.isSkyVisibleAllowingSea(pos)
        }

        fun canNightSpawn(
            type: EntityType<out WaterCreatureEntity>,
            world: ServerWorldAccess,
            reason: SpawnReason,
            pos: BlockPos,
            random: Random
        ): Boolean {
            val topY = world.seaLevel - 12
            val bottomY = world.seaLevel - 24

            return !world.toServerWorld().isDay &&
                    pos.y in bottomY..topY &&
                    world.isWater(pos) &&
                    world.isSkyVisibleAllowingSea(pos)
        }

        fun canDeepSpawn(
            type: EntityType<out WaterCreatureEntity>,
            world: ServerWorldAccess,
            reason: SpawnReason,
            pos: BlockPos,
            random: Random
        ): Boolean {
            val topY = world.seaLevel - 28
            val bottomY = world.seaLevel - 128

            return pos.y in bottomY..topY &&
                    world.isWater(pos)
        }

        fun getScaleAdjustment(fish: HybridAquaticFishEntity, adjustment: Float): Float {
            return 1.0f + (fish.size * adjustment)
        }
    }

    override fun getMaxLookPitchChange(): Int {
        return 5
    }

    override fun getMaxLookYawChange(): Int {
        return 1
    }
}