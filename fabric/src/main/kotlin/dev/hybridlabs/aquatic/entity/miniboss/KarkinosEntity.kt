package dev.hybridlabs.aquatic.entity.miniboss

import net.minecraft.nbt.CompoundTag
import net.minecraft.network.chat.Component
import net.minecraft.network.syncher.EntityDataAccessor
import net.minecraft.network.syncher.EntityDataSerializers
import net.minecraft.network.syncher.SynchedEntityData
import net.minecraft.server.level.ServerBossEvent
import net.minecraft.server.level.ServerPlayer
import net.minecraft.sounds.SoundEvent
import net.minecraft.sounds.SoundEvents
import net.minecraft.world.BossEvent
import net.minecraft.world.Difficulty
import net.minecraft.world.InteractionHand
import net.minecraft.world.damagesource.DamageSource
import net.minecraft.world.damagesource.DamageType
import net.minecraft.world.damagesource.DamageTypes
import net.minecraft.world.entity.EntitySelector
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.MobType
import net.minecraft.world.entity.ai.attributes.AttributeSupplier
import net.minecraft.world.entity.ai.attributes.Attributes
import net.minecraft.world.entity.ai.control.MoveControl
import net.minecraft.world.entity.ai.goal.*
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal
import net.minecraft.world.entity.ai.navigation.GroundPathNavigation
import net.minecraft.world.entity.ai.navigation.PathNavigation
import net.minecraft.world.entity.animal.IronGolem
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.enchantment.EnchantmentHelper
import net.minecraft.world.item.enchantment.Enchantments
import net.minecraft.world.level.Level
import net.minecraft.world.level.pathfinder.BlockPathTypes
import software.bernie.geckolib.constant.DefaultAnimations
import software.bernie.geckolib.core.animation.AnimatableManager
import software.bernie.geckolib.core.animation.AnimationController
import software.bernie.geckolib.core.animation.RawAnimation
import software.bernie.geckolib.core.`object`.PlayState


class KarkinosEntity(entityType: EntityType<out HybridAquaticMinibossEntity>, world: Level) :
    HybridAquaticMinibossEntity(entityType, world) {

    private var landNavigation: PathNavigation = createNavigation(world)

    override fun createNavigation(world: Level): PathNavigation {
        return GroundPathNavigation(this, world)
    }

    override fun isAffectedByFluids(): Boolean {
        return !onGround()
    }

    override fun isPushedByFluid(): Boolean {
        return false
    }

    init {
        setPathfindingMalus(BlockPathTypes.WATER, 0.0f)
        moveControl = MoveControl(this)
        navigation = this.landNavigation
        setMaxUpStep(1.5F)
    }

    private var flipTimer: Int = 0
    private val flipDuration: Int = 60
    private var bossBar: ServerBossEvent =
        ServerBossEvent(displayName, BossEvent.BossBarColor.RED, BossEvent.BossBarOverlay.NOTCHED_20)

    private var isFlipped: Boolean
        get() = entityData.get(FLIPPED)
        set(bool) = entityData.set(FLIPPED, bool)

    override fun defineSynchedData() {
        super.defineSynchedData()
        entityData.define(FLIPPED, false)
    }

    override fun registerGoals() {
        goalSelector.addGoal(1, KarkinosAttackGoal(this))
        goalSelector.addGoal(7, LeapAtTargetGoal(this, 0.3F))
        goalSelector.addGoal(4, WaterAvoidingRandomStrollGoal(this, 0.3))
        goalSelector.addGoal(5, RandomLookAroundGoal(this))
        goalSelector.addGoal(8, LookAtPlayerGoal(this, Player::class.java, 16.0f))
        targetSelector.addGoal(1, HurtByTargetGoal(this))
        targetSelector.addGoal(2, NearestAttackableTargetGoal(this, Player::class.java, 10, true, true, null))
        targetSelector.addGoal(2, NearestAttackableTargetGoal(this, IronGolem::class.java, 10, true, true, null))
    }

    private fun beFlipped() {
        isFlipped = true
        flipTimer = flipDuration
    }


    override fun shouldDiscardFriction(): Boolean {
        return false
    }

    private fun getHandSwingDuration(): Int {
        return 40
    }

    override fun updateSwingTime() {
        val i = this.getHandSwingDuration()
        if (this.swinging) {
            ++this.swingTime
            if (this.swingTime >= i) {
                this.swingTime = 0
                this.swinging = false
            }
        } else {
            this.swingTime = 0
        }

        this.attackAnim = swingTime.toFloat() / i.toFloat()
    }

    override fun getAttackAnim(tickDelta: Float): Float {
        var f = this.attackAnim - this.oAttackAnim
        if (f < 0.0f) {
            ++f
        }

        return this.oAttackAnim + f * tickDelta
    }

    override fun customServerAiStep() {

        if (isFlipped) {
            flipTimer--

            if (flipTimer <= 0) {
                isFlipped = false
                attributes.getInstance(Attributes.MOVEMENT_SPEED)?.baseValue = 0.5
                attributes.getInstance(Attributes.KNOCKBACK_RESISTANCE)?.baseValue = 1.0
            } else {
                attributes.getInstance(Attributes.MOVEMENT_SPEED)?.baseValue = 0.0
                attributes.getInstance(Attributes.KNOCKBACK_RESISTANCE)?.baseValue = 0.0
            }
        }

        bossBar.progress = health / maxHealth

        super.customServerAiStep()
    }

    override fun startSeenByPlayer(player: ServerPlayer) {
        super.startSeenByPlayer(player)
        bossBar.addPlayer(player)
    }

    override fun stopSeenByPlayer(player: ServerPlayer) {
        super.stopSeenByPlayer(player)
        bossBar.removePlayer(player)
    }

    override fun checkDespawn() {
        if (level().difficulty == Difficulty.PEACEFUL && this.shouldDespawnInPeaceful()) {
            discard()
        } else {
            noActionTime = 0
        }
    }

    override fun isPushable(): Boolean =
        this.isFlipped

    override fun getSpeed(): Float {
        return if (isFlipped) 0.0f else super.getSpeed()
    }

    override fun hurt(source: DamageSource, amount: Float): Boolean {
        val dmgSourcesRegistry = damageSources().damageTypes
        var adjustedAmount = amount

        if (source.type() == dmgSourcesRegistry.getHolderOrThrow(DamageTypes.ARROW).value() as DamageType)
            adjustedAmount *= 0.5f
        else if (source.type() == dmgSourcesRegistry.getHolderOrThrow(DamageTypes.IN_WALL).value() as DamageType)
            adjustedAmount *= 0.5f

        val damaged = super.hurt(source, amount)

        if (source.directEntity is Player && !isFlipped) {
            val player = source.directEntity as Player

            if (EnchantmentHelper.getEnchantmentLevel(Enchantments.BANE_OF_ARTHROPODS, player) > 2 ||
                EnchantmentHelper.getEnchantmentLevel(Enchantments.RIPTIDE, player) > 0
            ) {
                beFlipped()
            }
        }

        return damaged
    }

    override fun getMobType(): MobType {
        return MobType.ARTHROPOD
    }

    override fun readAdditionalSaveData(nbt: CompoundTag) {
        if (hasCustomName()) {
            bossBar.name = this.displayName
        }
        super.readAdditionalSaveData(nbt)
    }

    override fun setCustomName(name: Component?) {
        super.setCustomName(name)
        bossBar.name = this.displayName
    }

    override fun registerControllers(controllers: AnimatableManager.ControllerRegistrar) {
        controllers.add(DefaultAnimations.genericWalkRunIdleController(this))
        controllers.add(DefaultAnimations.genericAttackAnimation(this, DefaultAnimations.ATTACK_SWING))
        controllers.add(AnimationController(this, 5) { state ->
            if (isFlipped) {
                state.setAndContinue(FLIP)
                PlayState.CONTINUE
            } else {
                PlayState.STOP
            }
        }
        )
    }

    override fun getHurtSound(source: DamageSource): SoundEvent {
        return SoundEvents.TURTLE_EGG_CRACK
    }

    override fun getDeathSound(): SoundEvent {
        return SoundEvents.TURTLE_EGG_BREAK
    }

    companion object {

        val FLIP: RawAnimation = RawAnimation.begin().thenPlay("misc.flip")

        fun createMobAttributes(): AttributeSupplier.Builder {
            return createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 300.0)
                .add(Attributes.MOVEMENT_SPEED, 0.35)
                .add(Attributes.ATTACK_DAMAGE, 10.0)
                .add(Attributes.ATTACK_KNOCKBACK, 0.0)
                .add(Attributes.FOLLOW_RANGE, 32.0)
                .add(Attributes.KNOCKBACK_RESISTANCE, 1.0)
        }

        val FLIPPED: EntityDataAccessor<Boolean> =
            SynchedEntityData.defineId(KarkinosEntity::class.java, EntityDataSerializers.BOOLEAN)
    }

    internal open class KarkinosAttackGoal(private val karkinos: KarkinosEntity) :
        MeleeAttackGoal(karkinos, 0.6, false) {
        override fun checkAndPerformAttack(target: LivingEntity, squaredDistance: Double) {
            val d = getAttackReachSqr(target)
            if (squaredDistance <= d && this.attackInterval <= 0) {
                resetAttackCooldown()
                karkinos.swing(InteractionHand.MAIN_HAND)
                karkinos.doHurtTarget(target)
            }
        }

        override fun getAttackReachSqr(entity: LivingEntity): Double {
            return (karkinos.bbWidth * 2.0 + entity.bbWidth)
        }

        override fun start() {
            super.start()
            karkinos.isSprinting = true
            karkinos.swinging = false
            karkinos.swingTime = 0
        }

        override fun stop() {
            val livingEntity = karkinos.target
            if (!EntitySelector.NO_CREATIVE_OR_SPECTATOR.test(livingEntity)) {
                karkinos.target = null
            }

            karkinos.isSprinting = false
            karkinos.isAggressive = false
            karkinos.navigation.stop()
        }

        override fun requiresUpdateEveryTick(): Boolean {
            return true
        }

        override fun canContinueToUse(): Boolean {
            return !karkinos.isFlipped && super.canContinueToUse()
        }
    }
}