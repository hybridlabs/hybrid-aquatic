package dev.hybridlabs.aquatic.entity.miniboss

import net.minecraft.enchantment.EnchantmentHelper
import net.minecraft.enchantment.Enchantments
import net.minecraft.entity.EntityGroup
import net.minecraft.entity.EntityType
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.ai.control.MoveControl
import net.minecraft.entity.ai.goal.*
import net.minecraft.entity.ai.pathing.EntityNavigation
import net.minecraft.entity.ai.pathing.MobNavigation
import net.minecraft.entity.ai.pathing.PathNodeType
import net.minecraft.entity.attribute.DefaultAttributeContainer
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.entity.boss.BossBar
import net.minecraft.entity.boss.ServerBossBar
import net.minecraft.entity.damage.DamageSource
import net.minecraft.entity.damage.DamageType
import net.minecraft.entity.damage.DamageTypes
import net.minecraft.entity.data.DataTracker
import net.minecraft.entity.data.TrackedData
import net.minecraft.entity.data.TrackedDataHandlerRegistry
import net.minecraft.entity.passive.IronGolemEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.nbt.NbtCompound
import net.minecraft.predicate.entity.EntityPredicates
import net.minecraft.server.network.ServerPlayerEntity
import net.minecraft.sound.SoundEvent
import net.minecraft.sound.SoundEvents
import net.minecraft.text.Text
import net.minecraft.util.Hand
import net.minecraft.world.Difficulty
import net.minecraft.world.World
import software.bernie.geckolib3.constant.DefaultAnimations
import software.bernie.geckolib3.core.animation.AnimationController
import software.bernie.geckolib3.core.animation.RawAnimation
import software.bernie.geckolib3.core.`object`.PlayState


class KarkinosEntity(entityType: EntityType<out HybridAquaticMinibossEntity>, world: World) :
    HybridAquaticMinibossEntity(entityType, world) {

    private var landNavigation: EntityNavigation = createNavigation(world)

    override fun createNavigation(world: World): EntityNavigation {
        return MobNavigation(this, world)
    }

    override fun shouldSwimInFluids(): Boolean {
        return !isOnGround
    }

    override fun isPushedByFluids(): Boolean {
        return false
    }

    init {
        setPathfindingPenalty(PathNodeType.WATER, 0.0f)
        moveControl = MoveControl(this)
        navigation = this.landNavigation
        stepHeight = 1.5F
    }

    private var flipTimer: Int = 0
    private val flipDuration: Int = 60
    private var bossBar: ServerBossBar = ServerBossBar(displayName, BossBar.Color.RED, BossBar.Style.NOTCHED_20)

    private var isFlipped: Boolean
        get() = dataTracker.get(FLIPPED)
        set(bool) = dataTracker.set(FLIPPED, bool)

    override fun initDataTracker() {
        super.initDataTracker()
        dataTracker.startTracking(FLIPPED, false)
    }

    override fun initGoals() {
        goalSelector.add(1, KarkinosAttackGoal(this))
        goalSelector.add(7, PounceAtTargetGoal(this, 0.3F))
        goalSelector.add(4, WanderAroundFarGoal(this, 0.3))
        goalSelector.add(5, LookAroundGoal(this))
        goalSelector.add(8, LookAtEntityGoal(this, PlayerEntity::class.java, 16.0f))
        targetSelector.add(1, RevengeGoal(this))
        targetSelector.add(2, ActiveTargetGoal(this, PlayerEntity::class.java, 10, true, true, null))
        targetSelector.add(2, ActiveTargetGoal(this, IronGolemEntity::class.java, 10, true, true, null))
    }

    private fun beFlipped() {
        isFlipped = true
        flipTimer = flipDuration
    }

    override fun hasNoDrag(): Boolean {
        return false
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

    override fun mobTick() {

        if (isFlipped) {
            flipTimer--

            if (flipTimer <= 0) {
                isFlipped = false
                attributes.getCustomInstance(EntityAttributes.GENERIC_MOVEMENT_SPEED)?.baseValue = 0.5
                attributes.getCustomInstance(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE)?.baseValue = 1.0
            } else {
                attributes.getCustomInstance(EntityAttributes.GENERIC_MOVEMENT_SPEED)?.baseValue = 0.0
                attributes.getCustomInstance(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE)?.baseValue = 0.0
            }
        }

        bossBar.percent = health / maxHealth

        super.mobTick()
    }

    override fun onStartedTrackingBy(player: ServerPlayerEntity) {
        super.onStartedTrackingBy(player)
        bossBar.addPlayer(player)
    }

    override fun onStoppedTrackingBy(player: ServerPlayerEntity) {
        super.onStoppedTrackingBy(player)
        bossBar.removePlayer(player)
    }

    override fun checkDespawn() {
        if (world.difficulty == Difficulty.PEACEFUL && this.isDisallowedInPeaceful) {
            discard()
        } else {
            despawnCounter = 0
        }
    }

    override fun isPushable(): Boolean =
        this.isFlipped

    override fun getMovementSpeed(): Float {
        return if (isFlipped) 0.0f else super.getMovementSpeed()
    }

    override fun damage(source: DamageSource, amount: Float): Boolean {
        val dmgSourcesRegistry = damageSources.registry
        var adjustedAmount = amount

        if (source.type == dmgSourcesRegistry.entryOf(DamageTypes.ARROW).value() as DamageType)
            adjustedAmount *= 0.5f
        else if (source.type == dmgSourcesRegistry.entryOf(DamageTypes.IN_WALL).value() as DamageType)
            adjustedAmount *= 0.5f

        val damaged = super.damage(source, amount)

        if (source.source is PlayerEntity && !isFlipped) {
            val player = source.source as PlayerEntity
            val heldItem = player.mainHandStack

            if (EnchantmentHelper.getLevel(Enchantments.BANE_OF_ARTHROPODS, heldItem) > 2 ||
                EnchantmentHelper.getLevel(Enchantments.RIPTIDE, heldItem) > 0
            ) {
                beFlipped()
            }
        }

        return damaged
    }

    override fun getGroup(): EntityGroup {
        return EntityGroup.ARTHROPOD
    }

    override fun readCustomDataFromNbt(nbt: NbtCompound) {
        if (hasCustomName()) {
            bossBar.name = this.displayName
        }
        super.readCustomDataFromNbt(nbt)
    }

    override fun setCustomName(name: Text?) {
        super.setCustomName(name)
        bossBar.name = this.displayName
    }

    override fun registerControllers(controllers: AnimationData) {
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
        return SoundEvents.ENTITY_TURTLE_EGG_CRACK
    }

    override fun getDeathSound(): SoundEvent {
        return SoundEvents.ENTITY_TURTLE_EGG_BREAK
    }

    companion object {

        val FLIP: RawAnimation = RawAnimation.begin().thenPlay("misc.flip")

        fun createMobAttributes(): DefaultAttributeContainer.Builder {
            return createLivingAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 300.0)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.35)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 10.0)
                .add(EntityAttributes.GENERIC_ATTACK_KNOCKBACK, 0.0)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 32.0)
                .add(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, 1.0)
        }

        val FLIPPED: TrackedData<Boolean> =
            DataTracker.registerData(KarkinosEntity::class.java, TrackedDataHandlerRegistry.BOOLEAN)
    }

    internal open class KarkinosAttackGoal(private val karkinos: KarkinosEntity) :
        MeleeAttackGoal(karkinos, 0.6, false) {
        override fun attack(target: LivingEntity, squaredDistance: Double) {
            val d = getSquaredMaxAttackDistance(target)
            if (squaredDistance <= d && this.cooldown <= 0) {
                resetCooldown()
                karkinos.swingHand(Hand.MAIN_HAND)
                karkinos.tryAttack(target)
            }
        }

        override fun getSquaredMaxAttackDistance(entity: LivingEntity): Double {
            return (karkinos.width * 2.0 + entity.width)
        }

        override fun start() {
            super.start()
            karkinos.isSprinting = true
            karkinos.handSwinging = false
            karkinos.handSwingTicks = 0
        }

        override fun stop() {
            val livingEntity = karkinos.target
            if (!EntityPredicates.EXCEPT_CREATIVE_OR_SPECTATOR.test(livingEntity)) {
                karkinos.target = null
            }

            karkinos.isSprinting = false
            karkinos.isAttacking = false
            karkinos.navigation.stop()
        }

        override fun shouldRunEveryTick(): Boolean {
            return true
        }

        override fun shouldContinue(): Boolean {
            return !karkinos.isFlipped && super.shouldContinue()
        }
    }
}
