package dev.hybridlabs.aquatic.entity.shark

import dev.hybridlabs.aquatic.effect.HybridAquaticStatusEffects
import dev.hybridlabs.aquatic.item.HybridAquaticItems
import dev.hybridlabs.aquatic.tag.HybridAquaticEntityTags
import net.minecraft.block.Blocks
import net.minecraft.entity.EntityData
import net.minecraft.entity.EntityDimensions
import net.minecraft.entity.EntityPose
import net.minecraft.entity.EntityType
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.SpawnReason
import net.minecraft.entity.ai.control.AquaticMoveControl
import net.minecraft.entity.ai.control.YawAdjustingLookControl
import net.minecraft.entity.ai.goal.ActiveTargetGoal
import net.minecraft.entity.ai.goal.ChaseBoatGoal
import net.minecraft.entity.ai.goal.LookAroundGoal
import net.minecraft.entity.ai.goal.LookAtEntityGoal
import net.minecraft.entity.ai.goal.MeleeAttackGoal
import net.minecraft.entity.ai.goal.RevengeGoal
import net.minecraft.entity.ai.goal.SwimAroundGoal
import net.minecraft.entity.ai.goal.UniversalAngerGoal
import net.minecraft.entity.ai.pathing.PathNodeType
import net.minecraft.entity.ai.pathing.SwimNavigation
import net.minecraft.entity.damage.DamageSource
import net.minecraft.entity.data.DataTracker
import net.minecraft.entity.data.TrackedData
import net.minecraft.entity.data.TrackedDataHandlerRegistry
import net.minecraft.entity.effect.StatusEffectInstance
import net.minecraft.entity.mob.Angerable
import net.minecraft.entity.mob.WaterCreatureEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.nbt.NbtCompound
import net.minecraft.particle.ParticleTypes
import net.minecraft.registry.tag.FluidTags
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
import software.bernie.geckolib.core.animation.AnimatableManager
import software.bernie.geckolib.core.animation.Animation
import software.bernie.geckolib.core.animation.AnimationController
import software.bernie.geckolib.core.animation.AnimationState
import software.bernie.geckolib.core.animation.RawAnimation
import software.bernie.geckolib.core.`object`.PlayState
import software.bernie.geckolib.util.GeckoLibUtil
import java.util.UUID


@Suppress("LeakingThis", "DEPRECATION")
open class HybridAquaticSharkEntity(
    entityType: EntityType<out HybridAquaticSharkEntity>,
    world: World,
    private val prey: TagKey<EntityType<*>>,
    private val isPassive: Boolean,
    private val closePlayerAttack: Boolean
) : WaterCreatureEntity(entityType, world), Angerable, GeoEntity {
    private val factory = GeckoLibUtil.createInstanceCache(this)
    private var angerTime = 0
    private var angryAt: UUID? = null
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
    private var attemptAttack: Boolean
        get() = dataTracker.get(ATTEMPT_ATTACK)
        set(attemptAttack) {
            dataTracker.set(ATTEMPT_ATTACK, attemptAttack)
        }
    var size: Int
        get() = dataTracker.get(SHARK_SIZE)
        set(size) {
            dataTracker.set(SHARK_SIZE, size)
        }

    init {
        setPathfindingPenalty(PathNodeType.WATER, 0.0f)
        moveControl = AquaticMoveControl(this, 85, 10, 0.1F, 0.1F, true)
        lookControl = YawAdjustingLookControl(this, 10)
        navigation = SwimNavigation(this, world)
    }

    companion object {
        const val MOISTNESS_KEY = "Moistness"
        const val SHARK_SIZE_KEY = "SharkSize"
        val SHARK_SIZE: TrackedData<Int> = DataTracker.registerData(HybridAquaticSharkEntity::class.java, TrackedDataHandlerRegistry.INTEGER)
        val MOISTNESS: TrackedData<Int> =
            DataTracker.registerData(HybridAquaticSharkEntity::class.java, TrackedDataHandlerRegistry.INTEGER)

        const val MAX_HUNGER = 1200
        const val HUNGER_KEY = "Hunger"

        val HUNGER: TrackedData<Int> =
            DataTracker.registerData(HybridAquaticSharkEntity::class.java, TrackedDataHandlerRegistry.INTEGER)

        val ATTEMPT_ATTACK: TrackedData<Boolean> =
            DataTracker.registerData(HybridAquaticSharkEntity::class.java, TrackedDataHandlerRegistry.BOOLEAN)

        val ANGER_TIME_RANGE: UniformIntProvider = TimeHelper.betweenSeconds(15, 60)

        val FLOP_ANIMATION: RawAnimation  = RawAnimation.begin().then("flop", Animation.LoopType.LOOP)
        val SWIM_ANIMATION: RawAnimation  = RawAnimation.begin().then("swim", Animation.LoopType.LOOP)
        val RUSH_ANIMATION: RawAnimation  = RawAnimation.begin().then("rush", Animation.LoopType.LOOP)

        fun canSpawn(
            type: EntityType<out WaterCreatureEntity>,
            world: WorldAccess,
            reason: SpawnReason?,
            pos: BlockPos,
            random: Random?
        ): Boolean {
            val topY = world.seaLevel - 8
            val bottomY = world.seaLevel - 24

            return pos.y in bottomY..topY &&
                    world.getFluidState(pos.down()).isIn(FluidTags.WATER) &&
                    world.getBlockState(pos.up()).isOf(Blocks.WATER)
        }
        fun getScaleAdjustment(shark : HybridAquaticSharkEntity, adjustment : Float): Float {
            return 1.0f + (shark.size * adjustment)
        }
    }

    override fun initGoals() {
        super.initGoals()
        goalSelector.add(1, AttackGoal(this))
        goalSelector.add(4, SwimAroundGoal(this, 1.0, 2))
        goalSelector.add(4, LookAroundGoal(this))
        goalSelector.add(5, LookAtEntityGoal(this, PlayerEntity::class.java, 6.0f))
        if (!isPassive) {
            targetSelector.add(1, RevengeGoal(this))
            targetSelector.add(2, ActiveTargetGoal(this, PlayerEntity::class.java, 10, true, true) { entity: LivingEntity -> shouldAngerAt(entity) || shouldProximityAttack(entity as PlayerEntity) })
            targetSelector.add(3, ActiveTargetGoal(this, LivingEntity::class.java, 10, true, true) { hunger <= 100 && it.type.isIn(prey) })
            targetSelector.add(1, ActiveTargetGoal(this, LivingEntity::class.java, 10, true, true) { it.hasStatusEffect(HybridAquaticStatusEffects.BLEEDING) && it !is HybridAquaticSharkEntity})
            targetSelector.add(3, UniversalAngerGoal(this, false))
        }
    }

    override fun initDataTracker() {
        super.initDataTracker()
        dataTracker.startTracking(MOISTNESS, getMaxMoistness())
        dataTracker.startTracking(HUNGER, MAX_HUNGER)
        dataTracker.startTracking(ATTEMPT_ATTACK, false)
        dataTracker.startTracking(SHARK_SIZE, 0)
    }

    override fun initialize(
        world: ServerWorldAccess,
        difficulty: LocalDifficulty,
        spawnReason: SpawnReason,
        entityData: EntityData?,
        entityNbt: NbtCompound?
    ): EntityData? {
        this.air = getMaxMoistness()
        pitch = 0.0f
        this.size = this.random.nextBetween(getMinSize(),getMaxSize())
        return super.initialize(world, difficulty, spawnReason, entityData, entityNbt)
    }

    override fun tick() {
        super.tick()
        if (isAiDisabled) {
            return
        }
        if (this.isWet) {
            moistness = getMaxMoistness()
        } else {
            moistness -= 1
            if (moistness <= -20) {
                moistness = 0
                damage(this.damageSources.dryOut(), 1.0f)
            }
        }
        if (isSprinting) {
            attributes.getCustomInstance(EntityAttributes.GENERIC_MOVEMENT_SPEED)?.baseValue = 2.0
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
        if (hunger > 0) hunger -= 1
    }

    override fun tickWaterBreathingAir(air: Int) {}

    private fun getMaxMoistness(): Int {
        return 1200
    }

    override fun travel(movementInput: Vec3d?) {
        super.travel(movementInput)
    }

    override fun writeCustomDataToNbt(nbt: NbtCompound) {
        super.writeCustomDataToNbt(nbt)
        this.writeAngerToNbt(nbt)
        nbt.putInt(MOISTNESS_KEY, moistness)
        nbt.putInt(HUNGER_KEY, hunger)
        nbt.putInt(SHARK_SIZE_KEY, size)
        nbt.putBoolean("FromFishingNet", fromFishingNet)
    }

    private var fromFishingNet = false
    override fun readCustomDataFromNbt(nbt: NbtCompound) {
        super.readCustomDataFromNbt(nbt)
        this.readAngerFromNbt(this.world, nbt)
        moistness = nbt.getInt(MOISTNESS_KEY)
        hunger = nbt.getInt(HUNGER_KEY)
        size = nbt.getInt(SHARK_SIZE_KEY)
        fromFishingNet = nbt.getBoolean("FromFishingNet")
    }

    open fun <E : GeoAnimatable> predicate(event: AnimationState<E>): PlayState {
        if (!this.isWet) {
            event.controller.setAnimation(FLOP_ANIMATION)

        } else if (isAttacking) {
            event.controller.setAnimation(RUSH_ANIMATION)

        } else if (isSubmergedInWater) {
                event.controller.setAnimation(SWIM_ANIMATION)
        }
        return PlayState.CONTINUE
    }
    protected open fun getMinSize() : Int {
        return 0
    }

    protected open fun getMaxSize() : Int {
        return 0
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
        return SoundEvents.ENTITY_HOSTILE_HURT
    }

    override fun getDeathSound(): SoundEvent {
        return SoundEvents.ENTITY_HOSTILE_DEATH
    }

    override fun getAmbientSound(): SoundEvent {
        return SoundEvents.ENTITY_COD_AMBIENT
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

        return closePlayerAttack && player.squaredDistanceTo(this) <= 12 && !player.isCreative
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
    //#endregioni

    private fun getHungerValue(entityType: EntityType<*>): Int {
        if (entityType.isIn(HybridAquaticEntityTags.CRAB))
            return 400
        if (entityType.isIn(HybridAquaticEntityTags.SMALL_PREY))
            return 400
        else if (entityType.isIn(HybridAquaticEntityTags.MEDIUM_PREY))
            return 800
        else if (entityType.isIn(HybridAquaticEntityTags.LARGE_PREY))
            return 1200

        return 0
    }

    open fun eatFish(entityType: EntityType<*>) {
        hunger += getHungerValue(entityType)
    }

    internal class AttackGoal(private val shark: HybridAquaticSharkEntity) : MeleeAttackGoal(shark, 1.5,true) {
        override fun canStart(): Boolean {
            return !shark.fromFishingNet && super.canStart()
        }

        override fun attack(target: LivingEntity, squaredDistance: Double) {
    internal class AttackGoal(private val shark: HybridAquaticSharkEntity) : MeleeAttackGoal(shark,
        ORIGINAL_SPEED, true) {
        override fun attack(target: LivingEntity) {
            val squaredDistance = target.squaredDistanceTo(mob)
            val d = getSquaredMaxAttackDistance(target)
            if (squaredDistance <= d && this.isCooledDown) {
                resetCooldown()
                mob.tryAttack(target)
                shark.isSprinting = true
                shark.attemptAttack = true

                if (target.health <= 0)
                    shark.eatFish(target.type)
            }
        }

        private fun getSquaredMaxAttackDistance(entity: LivingEntity): Double {
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
    }
}
