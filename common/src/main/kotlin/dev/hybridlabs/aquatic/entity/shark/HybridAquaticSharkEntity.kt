package dev.hybridlabs.aquatic.entity.shark

import dev.hybridlabs.aquatic.effect.HybridAquaticMobEffects
import dev.hybridlabs.aquatic.entity.fish.HybridAquaticFishEntity
import dev.hybridlabs.aquatic.item.HybridAquaticItems
import net.minecraft.entity.*
import net.minecraft.entity.ai.control.SmoothSwimmingMoveControl
import net.minecraft.entity.ai.control.SmoothSwimmingLookControl
import net.minecraft.entity.ai.goal.*
import net.minecraft.entity.ai.pathing.BlockPathTypes
import net.minecraft.entity.ai.pathing.WaterBoundPathNavigation
import net.minecraft.entity.damage.DamageSource
import net.minecraft.entity.data.SynchedEntityData
import net.minecraft.entity.data.EntityDataAccessor
import net.minecraft.entity.data.EntityDataSerializers
import net.minecraft.entity.effect.MobEffectInstance
import net.minecraft.entity.mob.Angerable
import net.minecraft.entity.mob.Monster.isDarkEnoughToSpawn
import net.minecraft.entity.mob.WaterAnimal
import net.minecraft.entity.player.Player
import net.minecraft.item.ItemStack
import net.minecraft.item.Items
import net.minecraft.nbt.CompoundTag
import net.minecraft.predicate.entity.EntityPredicates
import net.minecraft.registry.tag.TagKey
import net.minecraft.sound.SoundEvent
import net.minecraft.sound.SoundEvents
import net.minecraft.util.Hand
import net.minecraft.util.TimeHelper
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Vec3d
import net.minecraft.util.math.intprovider.UniformIntProvider
import net.minecraft.util.math.random.Random
import net.minecraft.world.DifficultyInstance
import net.minecraft.world.ServerLevelAccess
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
) : WaterAnimal(entityType, world), Angerable, GeoEntity {
    private val factory = GeckoLibUtil.createInstanceCache(this)
    private var angerTime = 0
    private var angryAt: UUID? = null
    private var fromFishingNet = false

    var hunger: Int
        get() = entityData.get(HUNGER)
        set(hunger) {
            entityData.set(HUNGER, hunger)
        }

    private var moistness: Int
        get() = entityData.get(MOISTNESS)
        set(moistness) {
            entityData.set(MOISTNESS, moistness)
        }


    //#region Initialization
    init {
        setPathfindingMalus(BlockPathTypes.WATER, 0.0f)
        setPathfindingMalus(BlockPathTypes.WATER_BORDER, -1.0f)
        setPathfindingMalus(BlockPathTypes.WALKABLE, -1.0f)
        moveControl = SmoothSwimmingMoveControl(this, 85, 5, speed, 0.1F, true)
        lookControl = SmoothSwimmingLookControl(this, 15)
        navigation = WaterBoundPathNavigation(this, world)
    }

    override fun registerGoals() {
        super.registerGoals()
        goalSelector.addGoal(0, TryFindWaterGoal(this))
        goalSelector.addGoal(4, RandomSwimmingGoal(this, 1.0, 2))
        goalSelector.addGoal(4,RandomRandomLookAroundGoal(this))
        goalSelector.addGoal(5, LookAtPlayerGoal(this,Player::class.java, 6.0f))
        goalSelector.addGoal(1, SharkAttackGoal(this))
        targetSelector.addGoal(1, NearestAttackableTargetGoal(this,Player::class.java, 10, true, true) { entity: LivingEntity -> shouldAngerAt(entity) || shouldProximityAttack(entity asPlayer) && !isPassive })
        targetSelector.addGoal(1, NearestAttackableTargetGoal(this, LivingEntity::class.java, 10, true, true) { it.hasMobEffect(HybridAquaticMobEffects.BLEEDING) && it !is HybridAquaticSharkEntity && !isPassive })
        targetSelector.addGoal(1, NearestAttackableTargetGoal(this, LivingEntity::class.java, 10, true, true) { entity: LivingEntity -> prey.any { preyType -> entity.type.`is`(preyType) } && hunger < MAX_HUNGER / 4 })
    }

    override fun finalizeSpawn(
        world: ServerLevelAccessor,
        difficulty: DifficultyInstance,
        spawnReason: MobSpawnType,
        entityData: SpawnGroupData?,
        entityNbt: CompoundTag?
    ): SpawnGroupData? {
        this.airSupply= getMaxMoistness()
        xRot = 0.0f
        yRot = 0.0f
        this.size = this.random.nextIntBetweenInclusive(getMinSize(), getMaxSize())
        return super.finalizeSpawn(world, difficulty, spawnReason, entityData, entityNbt)
    }

    override fun getGroup(): EntityGroup {
        return EntityGroup.AQUATIC
    }

    override fun tick() {
        super.tick()

        if (this.isUnderWater) {
            moistness = getMaxMoistness()
        } else {
            moistness -= 1
            if (moistness <= -20) {
                moistness = 0
                damage(this.damageSources.dryOut(), 2.0f)
            }
        }

        if (!this.isUnderWater && this.isOnGround) {
            this.pitch = 0.0f
            this.yaw = 0.0f
        }

        if (hunger > 0) hunger -= 1
    }

    //#endregion

    override fun removeWhenFarAway(distanceSquared: Double): Boolean {
        return !this.fromFishingNet && !this.hasCustomName()
    }

    override fun getSpawnClusterSize(): Int {
        return 4
    }

    //#region NBT

    override fun addAdditionalSaveData(nbt: CompoundTag) {
        super.addAdditionalSaveData(nbt)
        this.writeAngerToNbt(nbt)
        nbt.putInt(MOISTNESS_KEY, moistness)
        nbt.putInt(HUNGER_KEY, hunger)
        nbt.putInt(SHARK_SIZE_KEY, size)
        nbt.putBoolean("FromFishingNet", fromFishingNet)
    }

    override fun readAdditionalSaveData(nbt: CompoundTag) {
        super.readAdditionalSaveData(nbt)
        this.readAngerFromNbt(this.world, nbt)
        moistness = nbt.getInt(MOISTNESS_KEY)
        hunger = nbt.getInt(HUNGER_KEY)
        size = nbt.getInt(SHARK_SIZE_KEY)
        fromFishingNet = nbt.getBoolean("FromFishingNet")
    }

    override fun initSynchedEntityData() {
        super.initSynchedEntityData()
        entityData.define(MOISTNESS, getMaxMoistness())
        entityData.define(SHARK_SIZE, 0)
        entityData.define(HUNGER, MAX_HUNGER)
        entityData.define(ATTEMPT_ATTACK, false)
    }

    //#endregion


    //#region Movement
    override fun aiStep() {
        this.updateSwingTime()
        super.aiStep()
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
        get() = entityData.get(SHARK_SIZE)
        set(size) {
            entityData.set(SHARK_SIZE, size)
        }

    protected open fun getMinSize(): Int {
        return 0
    }

    protected open fun getMaxSize(): Int {
        return 0
    }

    override fun getStandingEyeHeight(pose: Pose, dimensions: EntityDimensions): Float {
        return dimensions.height * 0.65f
    }

    //#endregion

    //#region Water Breathing

    override fun handleAirSupply(air: Int) {}

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
        controllerRegistrar.add(
            AnimationController(this, "Swim", 4,
                AnimationController.AnimationStateHandler { state: AnimationState<HybridAquaticSharkEntity> ->
                    if (this.isUnderWater) {
                        return@AnimationStateHandler state.setAndContinue(DefaultAnimations.SWIM)
                    } else {
                        PlayState.STOP
                    }
                }
            )
        )

        controllerRegistrar.add(
            AnimationController(this, "Charge", 4,
                AnimationController.AnimationStateHandler { state: AnimationState<HybridAquaticSharkEntity> ->
                    if (this.isUnderWater && this.isSprinting) {
                        return@AnimationStateHandler state.setAndContinue(DefaultAnimations.RUN)
                    } else {
                        PlayState.STOP
                    }
                }
            )
        )

        controllerRegistrar.add(
            AnimationController(this, "Beached", 4,
                AnimationController.AnimationStateHandler { state: AnimationState<HybridAquaticSharkEntity> ->
                    if (this.isOnGround && !this.isUnderWater) {
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
        return SoundEvents._COD_HURT
    }

    override fun getDeathSound(): SoundEvent {
        return SoundEvents._COD_DEATH
    }

    //#endregion

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

    private fun shouldProximityAttack(player:Player): Boolean {
        if (customName?.string == "friend") return false

        return closePlayerAttack && player.squaredDistanceTo(this) <= 5 && !player.isCreative
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

    override fun updateSwingTime() {
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
        override fun canUse(): Boolean {
            return !shark.fromFishingNet && super.canUse()
        }

        override fun attack(target: LivingEntity, squaredDistance: Double) {
            val d = getSquaredMaxAttackDistance(target)
            if (squaredDistance <= d && this.cooldown <= 0 && !target.isBlocking) {
                resetCooldown()
                shark.swingHand(Hand.MAIN_HAND)
                shark.doHurtTarget(target)
                shark.playSound(SoundEvents._FOX_BITE, 0.5F, 0.0F)
                target.addMobEffect(MobEffectInstance(HybridAquaticMobEffects.BLEEDING, 200, 0), shark)

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

    override fun doHurtTarget(target: Entity?): Boolean {
        if (super.doHurtTarget(target)) {
            playSound(SoundEvents._FOX_BITE, 1.0F, 0.0F)

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

        val SHARK_SIZE: EntityDataAccessor<Int> =
            SynchedEntityData.defineId(HybridAquaticSharkEntity::class.java, EntityDataSerializers.INTEGER)
        val MOISTNESS: EntityDataAccessor<Int> =
            SynchedEntityData.defineId(HybridAquaticSharkEntity::class.java, EntityDataSerializers.INTEGER)
        val HUNGER: EntityDataAccessor<Int> =
            SynchedEntityData.defineId(HybridAquaticSharkEntity::class.java, EntityDataSerializers.INTEGER)
        val ATTEMPT_ATTACK: EntityDataAccessor<Boolean> =
            SynchedEntityData.defineId(HybridAquaticSharkEntity::class.java, EntityDataSerializers.BOOLEAN)
        val ANGER_TIME_RANGE: UniformIntProvider = TimeHelper.betweenSeconds(10, 30)
        val BEACHED: RawAnimation = RawAnimation.begin().thenPlay("misc.beached")

        //#region Spawning
        fun canShallowSpawn(
            type: EntityType<out WaterAnimal>,
            world: ServerLevelAccessor,
            reason: MobSpawnType,
            pos: BlockPos,
            random: Random
        ): Boolean {
            val topY = world.seaLevel - 2
            val bottomY = world.seaLevel - 6

            return pos.y in bottomY..topY &&
                    world.isWaterAt(pos) &&
                    world.canSeeSkyFromBelowWater(pos)
        }

        fun canSpawn(
            type: EntityType<out WaterAnimal>,
            world: ServerLevelAccessor,
            reason: MobSpawnType,
            pos: BlockPos,
            random: Random
        ): Boolean {
            val topY = world.seaLevel - 8
            val bottomY = world.seaLevel - 24

            return pos.y in bottomY..topY &&
                    world.isWaterAt(pos)
        }

        @Suppress("UNUSED_PARAMETER", "DEPRECATION")
        fun canDeepSpawn(
            type: EntityType<out WaterAnimal>,
            world: ServerLevelAccessor,
            reason: MobSpawnType,
            pos: BlockPos,
            random: Random?
        ): Boolean {
            val topY = world.seaLevel - 28
            val bottomY = world.seaLevel - 128

            return pos.y in bottomY..topY &&
                    world.isWaterAt(pos) &&
                    isDarkEnoughToSpawn(world, pos, random)
        }

        fun getScaleAdjustment(shark: HybridAquaticSharkEntity, adjustment: Float): Float {
            return 1.0f + (shark.size * adjustment)
        }
        //#endregion
    }
}