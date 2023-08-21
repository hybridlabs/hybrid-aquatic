package dev.hybridlabs.aquatic.entity.shark

import dev.hybridlabs.aquatic.access.CustomPlayerEntityData
import dev.hybridlabs.aquatic.tag.HybridAquaticEntityTags
import net.minecraft.block.Blocks
import net.minecraft.entity.*
import net.minecraft.entity.ai.control.AquaticMoveControl
import net.minecraft.entity.ai.control.YawAdjustingLookControl
import net.minecraft.entity.ai.goal.*
import net.minecraft.entity.ai.pathing.PathNodeType
import net.minecraft.entity.ai.pathing.SwimNavigation
import net.minecraft.entity.damage.DamageSource
import net.minecraft.entity.data.DataTracker
import net.minecraft.entity.data.TrackedData
import net.minecraft.entity.data.TrackedDataHandlerRegistry
import net.minecraft.entity.mob.Angerable
import net.minecraft.entity.mob.GuardianEntity
import net.minecraft.entity.mob.WaterCreatureEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.nbt.NbtCompound
import net.minecraft.registry.tag.TagKey
import net.minecraft.server.world.ServerWorld
import net.minecraft.sound.SoundEvent
import net.minecraft.sound.SoundEvents
import net.minecraft.util.TimeHelper
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Vec3d
import net.minecraft.util.math.intprovider.UniformIntProvider
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
import java.util.*


@Suppress("LeakingThis")
open class HybridAquaticSharkEntity(
    entityType: EntityType<out HybridAquaticSharkEntity>,
    world: World,
    private val prey: TagKey<EntityType<*>>,
    private val isPassive: Boolean,
    private val isCannibalistic: Boolean,
    private val closePlayerAttack: Boolean,
    private val bleedingPlayerAttack: Boolean,
    private val revengeAttack: Boolean
) : WaterCreatureEntity(entityType, world), Angerable, GeoEntity {
    private val factory = GeckoLibUtil.createInstanceCache(this)
    private var angerTime = 0
    private var angryAt: UUID? = null
    private var rushTargetPosition: Vec3d? = null
    private var moistness: Int
        get() = dataTracker.get(MOISTNESS)
        set(moistness) {
            dataTracker.set(MOISTNESS, moistness)
        }
    private var hunger: Int
        get() = dataTracker.get(HUNGER)
        set(hunger) {
            dataTracker.set(HUNGER, hunger)
        }
    private var isRushing: Boolean
        get() = dataTracker.get(RUSHING)
        set(rushing) {
            dataTracker.set(RUSHING, rushing)
        }
    private var attemptAttack: Boolean
        get() = dataTracker.get(ATTEMPT_ATTACK)
        set(attemptAttack) {
            dataTracker.set(ATTEMPT_ATTACK, attemptAttack)
        }

    init {
        setPathfindingPenalty(PathNodeType.WATER, 0.0f)
        moveControl = AquaticMoveControl(this, 85, 10, 0.02F, 0.1F, true)
        lookControl = YawAdjustingLookControl(this, 10)
        navigation = SwimNavigation(this, world)
    }

    companion object {
        const val MOISTNESS_KEY = "Moistness"
        val MOISTNESS: TrackedData<Int> =
            DataTracker.registerData(HybridAquaticSharkEntity::class.java, TrackedDataHandlerRegistry.INTEGER)

        const val MAX_HUNGER = 9600
        const val HUNGER_KEY = "Hunger"
        val HUNGER: TrackedData<Int> =
            DataTracker.registerData(HybridAquaticSharkEntity::class.java, TrackedDataHandlerRegistry.INTEGER)

        val RUSHING: TrackedData<Boolean> =
            DataTracker.registerData(HybridAquaticSharkEntity::class.java, TrackedDataHandlerRegistry.BOOLEAN)

        val ATTEMPT_ATTACK: TrackedData<Boolean> =
            DataTracker.registerData(HybridAquaticSharkEntity::class.java, TrackedDataHandlerRegistry.BOOLEAN)


        val ANGER_TIME_RANGE: UniformIntProvider = TimeHelper.betweenSeconds(19, 40)

        fun canSpawnPredicate(
            type: EntityType<out WaterCreatureEntity?>?,
            world: WorldAccess,
            reason: SpawnReason?,
            pos: BlockPos,
            random: Random?
        ): Boolean {
            return pos.y <= world.seaLevel - 10 && world.getBlockState(pos).isOf(Blocks.WATER) && canSpawn(type, world, reason, pos, random)
        }

    }

    override fun initGoals() {
        super.initGoals()
        goalSelector.add(1, AttackGoal(this))
        goalSelector.add(4, SwimAroundGoal(this, 1.0, 2))
//        goalSelector.add(4, WanderAroundGoal(this, 1.0))
        goalSelector.add(4, LookAroundGoal(this))
        goalSelector.add(5, LookAtEntityGoal(this, PlayerEntity::class.java, 6.0f))
        if(isPassive) {
            if (revengeAttack) targetSelector.add(1, RevengeGoal(this, *arrayOfNulls(0)).setGroupRevenge(*arrayOfNulls(0)))
            targetSelector.add(2, ActiveTargetGoal(this, PlayerEntity::class.java, 10, true, true) { entity: LivingEntity ->
                shouldAngerAt(entity) || shouldProximityAttack(entity as PlayerEntity) || isPlayerBleeding(entity)
            })
            targetSelector.add(3, UniversalAngerGoal(this, false))
            targetSelector.add(4, ActiveTargetGoal(this, LivingEntity::class.java, 10, true, true) {
                hunger <= 1200 && it.type.isIn(prey) && (!isCannibalistic && !it.type.equals(this.type))
            })
            goalSelector.add(9, FleeEntityGoal(this, GuardianEntity::class.java, 10.0f, 1.0, 1.3))
        }
//        targetSelector.add(
//            4,
//            ActiveTargetGoal(this, BoatEntity::class.java, 10, true, false, { livingEntity: LivingEntity? ->
//                livingEntity.squaredDistanceTo(this) < 20
//            })
//        )
    }

    override fun initDataTracker() {
        super.initDataTracker()
        dataTracker.startTracking(MOISTNESS, 1200)
        dataTracker.startTracking(HUNGER, MAX_HUNGER)
        dataTracker.startTracking(RUSHING, false)
        dataTracker.startTracking(ATTEMPT_ATTACK, false)

    }

    override fun initialize(
        world: ServerWorldAccess,
        difficulty: LocalDifficulty,
        spawnReason: SpawnReason,
        entityData: EntityData?,
        entityNbt: NbtCompound?
    ): EntityData? {
        this.air = this.maxAir
        pitch = 0.0f
        return super.initialize(world, difficulty, spawnReason, entityData, entityNbt)
    }

    //
    override fun tick() {
        super.tick()

        if (!world.isClient) {
            this.tickAngerLogic(world as ServerWorld, false)

            rushTargetPosition?.let { targetPos ->
                val velocityMod = targetPos.subtract(pos).normalize().multiply(3.0)
                println(velocityMod)
                this.pos.add(velocityMod)
            }
        }

        if (this.isAiDisabled) {
            this.air = this.maxAir
        } else {
            if (this.isWet) {
                moistness = 2400
            } else {
                moistness -= 1
                if (moistness <= 0) {
                    damage(this.damageSources.dryOut(), 1.0f)
                }

            }

            if (hunger > 0)
                hunger -= 1
        }

    }

    override fun tickWaterBreathingAir(air: Int) {}

    override fun travel(movementInput: Vec3d?) {
        super.travel(movementInput)
    }

    override fun writeCustomDataToNbt(nbt: NbtCompound) {
        super.writeCustomDataToNbt(nbt)
        this.writeAngerToNbt(nbt)
        nbt.putInt(MOISTNESS_KEY, moistness)
        nbt.putInt(HUNGER_KEY, hunger)
    }

    override fun readCustomDataFromNbt(nbt: NbtCompound) {
        super.readCustomDataFromNbt(nbt)
        this.readAngerFromNbt(this.world, nbt)
        moistness = nbt.getInt(MOISTNESS_KEY)
        hunger = nbt.getInt(HUNGER_KEY)
    }

    open fun <E : GeoAnimatable> predicate(event: AnimationState<E>): PlayState {

        if (!this.isWet) {
            event.controller.setAnimation(RawAnimation.begin().then("flop", Animation.LoopType.LOOP))
        }
        else if (this.handSwinging) {
            event.controller.setAnimation(RawAnimation.begin().then("attack", Animation.LoopType.LOOP))
//            this.attemptAttack = false
        }
        else if (isSubmergedInWater) {
            if (!isRushing)
                event.controller.setAnimation(RawAnimation.begin().then("swim", Animation.LoopType.LOOP))
            else
                event.controller.setAnimation(RawAnimation.begin().then("rush", Animation.LoopType.LOOP))
        }
        return PlayState.CONTINUE
    }

    override fun getActiveEyeHeight(pose: EntityPose, dimensions: EntityDimensions): Float {
        return dimensions.height * 0.65f
    }

    override fun canImmediatelyDespawn(distanceSquared: Double): Boolean {
        return !hasCustomName()
    }

    override fun getLimitPerChunk(): Int {
        return 8
    }

    //#region SFX
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

    override fun getSwimSound(): SoundEvent {
        return SoundEvents.ENTITY_DOLPHIN_SWIM
    }

    //#endregion
    override fun getMaxAir(): Int {
        return 4800
    }

    public override fun getNextAirOnLand(air: Int): Int {
        return this.maxAir
    }

    //    open val lookPitchSpeed: Int = 1
//    open val bodyYawSpeed: Int = 1
//
    protected fun hasSelfControl(): Boolean {
        return true
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

    private fun shouldProximityAttack(player: PlayerEntity): Boolean {
        if (customName?.string == "friend")
            return false

        return closePlayerAttack && player.squaredDistanceTo(this) <= 5 && !player.isCreative
    }

    private fun isPlayerBleeding(player: PlayerEntity): Boolean {
        return bleedingPlayerAttack && (player as CustomPlayerEntityData).`hybrid_aquatic$getHurtTime`() > 0
    }

    //#region Angerable Implementation Details
    override fun getAngerTime(): Int {
        return angerTime
    }

    override fun setAngerTime(angerTime: Int) {
        this.angerTime = angerTime
    }

    override fun getAngryAt(): UUID? {
        return angryAt
    }

    override fun setAngryAt(angryAt: UUID?) {
        this.angryAt = angryAt
    }

    override fun chooseRandomAngerTime() {
        setAngerTime(ANGER_TIME_RANGE.get(random))
    }
    //#endregion

    private fun getHungerValue(entityType: EntityType<*>): Int {
        if (entityType.isIn(HybridAquaticEntityTags.SMALL_PREY))
            return 600
        else if (entityType.isIn(HybridAquaticEntityTags.MEDIUM_PREY))
            return 1200
        else if (entityType.isIn(HybridAquaticEntityTags.LARGE_PREY))
            return 1800

        return 0
    }

    open fun eatFish(entityType: EntityType<*>) {
        hunger += getHungerValue(entityType)
    }

    internal class AttackGoal(private val shark: HybridAquaticSharkEntity) : MeleeAttackGoal(shark, ORIGINAL_SPEED, true) {
        override fun attack(target: LivingEntity, squaredDistance: Double) {
            val d = getSquaredMaxAttackDistance(target)
            if (squaredDistance <= d && this.isCooledDown) {
                resetCooldown()
                mob.tryAttack(target)


                shark.isSprinting = false
                shark.isRushing = false
                shark.attemptAttack = true

                if (target.health <= 0)
                    shark.eatFish(target.type)
//                shark.setRushing(false)
            } else if (squaredDistance > d * 5 && !shark.isRushing) {
//                shark.rushTargetPosition = Vec3d(target.pos.x, target.pos.y, target.pos.z)
                shark.isSprinting = true
                shark.isRushing = true
            }
        }

        override fun getSquaredMaxAttackDistance(entity: LivingEntity): Double {
            return (7.0f + entity.width).toDouble()
        }

        override fun start() {
            super.start()
            shark.attemptAttack = false
        }

        override fun stop() {
            super.stop()
            shark.attemptAttack = false
        }

        companion object {
            private const val ORIGINAL_SPEED = 2.5
            private const val SPEED_MULTIPLIER = 2.0
        }
    }
}
