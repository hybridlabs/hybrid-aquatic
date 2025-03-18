package dev.hybridlabs.aquatic.entity.shark

import dev.hybridlabs.aquatic.effect.HybridAquaticStatusEffects
import dev.hybridlabs.aquatic.entity.fish.HybridAquaticFishEntity
import dev.hybridlabs.aquatic.item.HybridAquaticItems
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
import net.minecraft.entity.effect.StatusEffectInstance
import net.minecraft.entity.mob.Angerable
import net.minecraft.entity.mob.HostileEntity.isSpawnDark
import net.minecraft.entity.mob.WaterCreatureEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.ItemStack
import net.minecraft.item.Items
import net.minecraft.nbt.NbtCompound
import net.minecraft.predicate.entity.EntityPredicates
import net.minecraft.registry.tag.FluidTags
import net.minecraft.registry.tag.TagKey
import net.minecraft.sound.SoundEvent
import net.minecraft.sound.SoundEvents
import net.minecraft.util.Hand
import net.minecraft.util.TimeHelper
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Vec3d
import net.minecraft.util.math.intprovider.UniformIntProvider
import net.minecraft.util.math.random.Random
import net.minecraft.world.LocalDifficulty
import net.minecraft.world.ServerWorldAccess
import net.minecraft.world.World
import software.bernie.geckolib.animatable.GeoEntity
import software.bernie.geckolib.constant.DefaultAnimations
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache
import software.bernie.geckolib.core.animation.AnimatableManager
import software.bernie.geckolib.core.animation.AnimationController
import software.bernie.geckolib.core.animation.AnimationState
import software.bernie.geckolib.core.animation.RawAnimation
import software.bernie.geckolib.core.`object`.PlayState
import software.bernie.geckolib.util.GeckoLibUtil
import java.util.*

@Suppress("LeakingThis", "DEPRECATION", "UNUSED_PARAMETER")
open class HybridAquaticSharkEntity(
    entityType: EntityType<out HybridAquaticSharkEntity>,
    world: World,
    private val prey: List<TagKey<EntityType<*>>>,
    private val isPassive: Boolean,
    private val closePlayerAttack: Boolean
) : WaterCreatureEntity(entityType, world), Angerable, GeoEntity {
    private val factory = GeckoLibUtil.createInstanceCache(this)
    private var angerTime = 0
    private var angryAt: UUID? = null

    var hunger: Int
        get() = dataTracker.get(HUNGER)
        set(hunger) {
            dataTracker.set(HUNGER, hunger)
        }

    //#region Initialization
    init {
        setPathfindingPenalty(PathNodeType.WATER, 0.0f)
        setPathfindingPenalty(PathNodeType.WALKABLE, 10.0f)
        moveControl = AquaticMoveControl(this, 75, 5, movementSpeed, 0.1F, true)
        lookControl = YawAdjustingLookControl(this, 5)
        navigation = SwimNavigation(this, world)
    }

    override fun initGoals() {
        super.initGoals()
        goalSelector.add(4, SwimAroundGoal(this, 1.0, 2))
        goalSelector.add(4, LookAroundGoal(this))
        goalSelector.add(5, LookAtEntityGoal(this, PlayerEntity::class.java, 6.0f))
        goalSelector.add(1, SharkAttackGoal(this))
        targetSelector.add(2, ActiveTargetGoal(this, PlayerEntity::class.java, 10, true, true) { entity: LivingEntity ->
            shouldAngerAt(entity) || shouldProximityAttack(entity as PlayerEntity) && !isPassive
        })
        targetSelector.add(1, ActiveTargetGoal(this, LivingEntity::class.java, 10, true, true) {
            it.hasStatusEffect(HybridAquaticStatusEffects.BLEEDING) && it !is HybridAquaticSharkEntity && !isPassive
        })
        targetSelector.add(3, ActiveTargetGoal(
            this, LivingEntity::class.java, 10, true, true
        ) { entity: LivingEntity -> prey.any { preyType -> entity.type.isIn(preyType) } && hunger < MAX_HUNGER / 4 })
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
        yaw = 0.0f
        this.size = this.random.nextBetween(getMinSize(), getMaxSize())
        return super.initialize(world, difficulty, spawnReason, entityData, entityNbt)
    }

    override fun tick() {
        super.tick()
        if (isAiDisabled) {
            return
        }

        if (this.isSubmergedInWater) {
            moistness = getMaxMoistness()
        } else {
            moistness -= 1
            if (moistness <= -20) {
                moistness = 0
                damage(this.damageSources.dryOut(), 2.0f)
            }
        }

        if (!this.isSubmergedInWater && this.isOnGround) {
            this.pitch = 0.0f
        }

        isSprinting = isAttacking

        val originalSpeed = movementSpeed

        movementSpeed = if (isAttacking) {
            originalSpeed * 2
        } else {
            originalSpeed
        }

        if (hunger > 0) hunger -= 1
    }

    //#endregion

    override fun canImmediatelyDespawn(distanceSquared: Double): Boolean {
        return !this.fromFishingNet && !this.hasCustomName()
    }

    override fun getLimitPerChunk(): Int {
        return 4
    }

    //#region NBT & Data
    private var fromFishingNet = false

    override fun writeCustomDataToNbt(nbt: NbtCompound) {
        super.writeCustomDataToNbt(nbt)
        this.writeAngerToNbt(nbt)
        nbt.putInt(MOISTNESS_KEY, moistness)
        nbt.putInt(HUNGER_KEY, hunger)
        nbt.putInt(SHARK_SIZE_KEY, size)
        nbt.putBoolean("FromFishingNet", fromFishingNet)
    }

    override fun readCustomDataFromNbt(nbt: NbtCompound) {
        super.readCustomDataFromNbt(nbt)
        this.readAngerFromNbt(this.world, nbt)
        moistness = nbt.getInt(MOISTNESS_KEY)
        hunger = nbt.getInt(HUNGER_KEY)
        size = nbt.getInt(SHARK_SIZE_KEY)
        fromFishingNet = nbt.getBoolean("FromFishingNet")
    }

    override fun initDataTracker() {
        super.initDataTracker()
        dataTracker.startTracking(MOISTNESS, getMaxMoistness())
        dataTracker.startTracking(SHARK_SIZE, 0)
        dataTracker.startTracking(HUNGER, MAX_HUNGER)
        dataTracker.startTracking(ATTEMPT_ATTACK, false)
    }

    //#endregion


    //#region Movement
    override fun tickMovement() {
        this.tickHandSwing()
        super.tickMovement()
    }

    override fun travel(movementInput: Vec3d?) {
        super.travel(movementInput)
    }

    override fun getMaxHeadRotation(): Int {
        return 1
    }

    override fun getMaxLookPitchChange(): Int {
        return 1
    }

    //#endregion

    //#region Size & Dimensions
    var size: Int
        get() = dataTracker.get(SHARK_SIZE)
        set(size) {
            dataTracker.set(SHARK_SIZE, size)
        }

    protected open fun getMinSize(): Int {
        return 0
    }

    protected open fun getMaxSize(): Int {
        return 0
    }

    override fun getActiveEyeHeight(pose: EntityPose, dimensions: EntityDimensions): Float {
        return dimensions.height * 0.65f
    }

    //#endregion

    //#region Water Breathing
    private var moistness: Int
        get() = dataTracker.get(MOISTNESS)
        set(moistness) {
            dataTracker.set(MOISTNESS, moistness)
        }

    override fun tickWaterBreathingAir(air: Int) {}

    private fun getMaxMoistness(): Int {
        return 1200
    }

    override fun getMaxAir(): Int {
        return 4800
    }

    public override fun getNextAirOnLand(air: Int): Int {
        return this.maxAir
    }

    //#endregion

    //#region Animations
    override fun registerControllers(controllerRegistrar: AnimatableManager.ControllerRegistrar) {
        controllerRegistrar.add(AnimationController(this, "Swim/Charge/Idle", 4) { state ->
            val animation = when {
                state.isMoving -> if (this.isSprinting) DefaultAnimations.RUN else DefaultAnimations.SWIM
                else -> DefaultAnimations.SWIM
            }
            state.setAndContinue(animation)
        })
        controllerRegistrar.add(
            AnimationController(this, "Beached", 4,
                AnimationController.AnimationStateHandler { state: AnimationState<HybridAquaticSharkEntity> ->
                    if (this.isOnGround && !isSubmergedInWater) {
                        return@AnimationStateHandler state.setAndContinue(BEACHED)
                    } else {
                        PlayState.STOP
                    }
                }
            )
        )
        controllerRegistrar.add(
            DefaultAnimations.genericAttackAnimation(this, DefaultAnimations.ATTACK_BITE)
        )
    }


    override fun getAnimatableInstanceCache(): AnimatableInstanceCache {
        return factory
    }

    //#endregion

    //#region SFX
    override fun getHurtSound(source: DamageSource): SoundEvent {
        return SoundEvents.ENTITY_COD_HURT
    }

    override fun getDeathSound(): SoundEvent {
        return SoundEvents.ENTITY_COD_DEATH
    }

    override fun getAmbientSound(): SoundEvent {
        return SoundEvents.ENTITY_TROPICAL_FISH_AMBIENT
    }

    //#endregion

    private fun shouldProximityAttack(player: PlayerEntity): Boolean {
        if (customName?.string == "friend") return false

        return closePlayerAttack && player.squaredDistanceTo(this) <= 5 && !player.isCreative
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

    override fun dropLoot(source: DamageSource, causedByPlayer: Boolean) {
        val attacker = source.attacker
        if (attacker !is HybridAquaticFishEntity && attacker !is HybridAquaticSharkEntity) {
            super.dropLoot(source, causedByPlayer)
        }
    }

    private fun getHandSwingDuration(): Int {
        return 40
    }

    override fun tickHandSwing() {
        val i = this.getHandSwingDuration()
        if (this.handSwinging) {
            ++this.handSwingTicks
            if (this.handSwingTicks >= i) {
                this.handSwingTicks = 0
                this.handSwinging = false
            }
        } else {
            this.handSwingTicks = 0
        }

        this.handSwingProgress = handSwingTicks.toFloat() / i.toFloat()
    }

    override fun getHandSwingProgress(tickDelta: Float): Float {
        var f = this.handSwingProgress - this.lastHandSwingProgress
        if (f < 0.0f) {
            ++f
        }

        return this.lastHandSwingProgress + f * tickDelta
    }

    internal class SharkAttackGoal(private val shark: HybridAquaticSharkEntity) : MeleeAttackGoal(shark, 1.0, true) {
        override fun canStart(): Boolean {
            return !shark.fromFishingNet && super.canStart()
        }

        override fun attack(target: LivingEntity, squaredDistance: Double) {
            val d = getSquaredMaxAttackDistance(target)
            if (squaredDistance <= d && this.cooldown <= 0 && !target.isBlocking) {
                resetCooldown()
                shark.swingHand(Hand.MAIN_HAND)
                shark.tryAttack(target)
                shark.playSound(SoundEvents.ENTITY_FOX_BITE, 0.5F, 0.0F)
                target.addStatusEffect(StatusEffectInstance(HybridAquaticStatusEffects.BLEEDING, 200, 0), shark)

                if (target.health <= 0) shark.hunger = MAX_HUNGER
                shark.health = shark.maxHealth

                if (target.mainHandStack.isOf(Items.SHIELD) && target.isBlocking) {
                    shark.dropStack(ItemStack(HybridAquaticItems.SHARK_TOOTH))
                }
            }
        }

        override fun getSquaredMaxAttackDistance(entity: LivingEntity): Double {
            return (shark.width * 2.5 + entity.width)
        }

        override fun start() {
            super.start()
            shark.isSprinting = true
            shark.handSwinging = false
            shark.handSwingTicks = 0
        }

        override fun stop() {
            val livingEntity = shark.target
            if (!EntityPredicates.EXCEPT_CREATIVE_OR_SPECTATOR.test(livingEntity)) {
                shark.target = null
            }

            shark.isSprinting = false
            shark.isAttacking = false
            shark.navigation.stop()
        }

        override fun shouldRunEveryTick(): Boolean {
            return true
        }
    }

    override fun tryAttack(target: Entity?): Boolean {
        if (super.tryAttack(target)) {
            playSound(SoundEvents.ENTITY_FOX_BITE, 1.0F, 0.0F)

            return true
        } else {
            return false
        }
    }


    companion object {
        const val MOISTNESS_KEY = "Moistness"
        const val SHARK_SIZE_KEY = "SharkSize"
        const val MAX_HUNGER = 2400
        const val HUNGER_KEY = "Hunger"

        val SHARK_SIZE: TrackedData<Int> =
            DataTracker.registerData(HybridAquaticSharkEntity::class.java, TrackedDataHandlerRegistry.INTEGER)
        val MOISTNESS: TrackedData<Int> =
            DataTracker.registerData(HybridAquaticSharkEntity::class.java, TrackedDataHandlerRegistry.INTEGER)
        val HUNGER: TrackedData<Int> =
            DataTracker.registerData(HybridAquaticSharkEntity::class.java, TrackedDataHandlerRegistry.INTEGER)
        val ATTEMPT_ATTACK: TrackedData<Boolean> =
            DataTracker.registerData(HybridAquaticSharkEntity::class.java, TrackedDataHandlerRegistry.BOOLEAN)
        val ANGER_TIME_RANGE: UniformIntProvider = TimeHelper.betweenSeconds(10, 30)

        val BEACHED: RawAnimation = RawAnimation.begin().thenPlay("misc.beached")

        fun canSpawn(
            type: EntityType<out WaterCreatureEntity>,
            world: ServerWorldAccess,
            reason: SpawnReason,
            pos: BlockPos,
            random: Random
        ): Boolean {
            val topY = world.seaLevel - 4
            val bottomY = world.seaLevel - 24

            return pos.y in bottomY..topY && world.getFluidState(pos)
                .isIn(FluidTags.WATER) && world.getFluidState(pos.down()).isIn(FluidTags.WATER) && world.getBlockState(
                pos.up()
            ).isOf(Blocks.WATER) && world.isSkyVisibleAllowingSea(pos) && !isSpawnDark(world, pos, random)
        }

        @Suppress("UNUSED_PARAMETER", "DEPRECATION")
        fun canUndergroundSpawn(
            type: EntityType<out WaterCreatureEntity>,
            world: ServerWorldAccess,
            reason: SpawnReason,
            pos: BlockPos,
            random: Random?
        ): Boolean {
            val topY = world.seaLevel - 24
            val bottomY = world.seaLevel - 128

            return pos.y in bottomY..topY && world.getFluidState(pos)
                .isIn(FluidTags.WATER) && world.getFluidState(pos.down()).isIn(FluidTags.WATER) && world.getBlockState(
                pos.up()
            ).isOf(Blocks.WATER) && isSpawnDark(world, pos, random)
        }

        fun getScaleAdjustment(shark: HybridAquaticSharkEntity, adjustment: Float): Float {
            return 1.0f + (shark.size * adjustment)
        }
    }
}