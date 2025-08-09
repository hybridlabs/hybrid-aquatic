package dev.hybridlabs.aquatic.entity.miniboss

import net.minecraft.enchantment.EnchantmentHelper
import net.minecraft.enchantment.Enchantments
import net.minecraft.entity.EntityType
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.ai.control.MoveControl
import net.minecraft.entity.ai.goal.ActiveTargetGoal
import net.minecraft.entity.ai.goal.LookAroundGoal
import net.minecraft.entity.ai.goal.LookAtEntityGoal
import net.minecraft.entity.ai.goal.MeleeAttackGoal
import net.minecraft.entity.ai.goal.RevengeGoal
import net.minecraft.entity.ai.goal.WanderAroundGoal
import net.minecraft.entity.ai.pathing.EntityNavigation
import net.minecraft.entity.ai.pathing.PathNodeType
import net.minecraft.entity.attribute.DefaultAttributeContainer
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.entity.boss.BossBar
import net.minecraft.entity.boss.ServerBossBar
import net.minecraft.entity.damage.DamageSource
import net.minecraft.entity.damage.DamageTypes
import net.minecraft.entity.data.DataTracker
import net.minecraft.entity.data.TrackedData
import net.minecraft.entity.data.TrackedDataHandlerRegistry
import net.minecraft.entity.mob.WaterCreatureEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.nbt.NbtCompound
import net.minecraft.registry.RegistryKeys
import net.minecraft.server.network.ServerPlayerEntity
import net.minecraft.server.world.ServerWorld
import net.minecraft.text.Text
import net.minecraft.world.Difficulty
import net.minecraft.world.World
import software.bernie.geckolib.core.animatable.GeoAnimatable
import software.bernie.geckolib.core.animation.Animation
import software.bernie.geckolib.core.animation.AnimationState
import software.bernie.geckolib.core.animation.RawAnimation
import software.bernie.geckolib.core.`object`.PlayState

class KarkinosEntity(entityType: EntityType<out HybridAquaticMinibossEntity>, world: World) :
    HybridAquaticMinibossEntity(entityType, world) {

    private var landNavigation: EntityNavigation = createNavigation(world)

    init {
        setPathfindingPenalty(PathNodeType.WATER, 0.0f)
        moveControl = MoveControl(this)
        navigation = this.landNavigation
    }

    private var flipTimer: Int = 0
    private val flipDuration: Int = 60
    private var bossBar: ServerBossBar = ServerBossBar(displayName, BossBar.Color.RED, BossBar.Style.NOTCHED_20)
    private var attemptAttack: Boolean
        get() = dataTracker.get(ATTEMPT_ATTACK)
        set(attemptAttack) {
            dataTracker.set(ATTEMPT_ATTACK, attemptAttack)
        }
    var isFlipped: Boolean
        get() = dataTracker.get(FLIPPED)
        set(bool) = dataTracker.set(FLIPPED, bool)

    override fun initDataTracker(builder: DataTracker.Builder) {
        super.initDataTracker(builder)
        builder.add(FLIPPED, false)
    }

    override fun initGoals() {
        goalSelector.add(1, AttackGoal(this))
        goalSelector.add(1, LookAtEntityGoal(this, PlayerEntity::class.java, 12.0f))
        goalSelector.add(4, KarkinosWanderAroundGoal(this, 0.3))
        goalSelector.add(4, LookAroundGoal(this))
        targetSelector.add(1, RevengeGoal(this))
        targetSelector.add(2, ActiveTargetGoal(this, PlayerEntity::class.java, 10, true, true, null))
    }

    private fun beFlipped() {
        isFlipped = true
        flipTimer = flipDuration
    }

    override fun mobTick(world: ServerWorld) {

        if (isFlipped) {
            flipTimer--

            if (flipTimer <= 0) {
                isFlipped = false
                attributes.getCustomInstance(EntityAttributes.GENERIC_MOVEMENT_SPEED)?.baseValue = 0.75
                attributes.getCustomInstance(EntityAttributes.GENERIC_ARMOR_TOUGHNESS)?.baseValue = 5.0
                attributes.getCustomInstance(EntityAttributes.GENERIC_ARMOR)?.baseValue = 8.0
            } else {
                attributes.getCustomInstance(EntityAttributes.GENERIC_MOVEMENT_SPEED)?.baseValue = 0.0
                attributes.getCustomInstance(EntityAttributes.GENERIC_ARMOR_TOUGHNESS)?.baseValue = 0.0
                attributes.getCustomInstance(EntityAttributes.GENERIC_ARMOR)?.baseValue = 0.0
            }
        }

        bossBar.percent = health / maxHealth

        super.mobTick(world)
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

    override fun <E : GeoAnimatable> predicate(event: AnimationState<E>): PlayState {
        if (isFlipped) {
            event.controller.setAnimation(FLIPPED_ANIMATION)
            return PlayState.CONTINUE
        }
        if (isAttacking) {
            event.controller.setAnimation(ATTACK_ANIMATION)
            return PlayState.CONTINUE
        }

        return super.predicate(event)
    }

    override fun getMovementSpeed(): Float {
        return if (isFlipped) 0.0f else super.getMovementSpeed()
    }

    override fun damage(source: DamageSource, amount: Float): Boolean {
        val registryManager = world.registryManager
        val dmgSourcesRegistry = damageSources.registry

        if (source.type == dmgSourcesRegistry[DamageTypes.ARROW]) return false
        else if (source.type == dmgSourcesRegistry[DamageTypes.IN_WALL]) return false

        val damaged = super.damage(source, amount)

        if (damaged && source.source is PlayerEntity && !isFlipped) {
            val player = source.source as PlayerEntity
            val heldItem = player.mainHandStack

            val enchantmentsRegistry = registryManager.getOrThrow(RegistryKeys.ENCHANTMENT)
            if (EnchantmentHelper.getLevel(enchantmentsRegistry.getOrThrow(Enchantments.BANE_OF_ARTHROPODS), heldItem) > 2 ||
                (EnchantmentHelper.getLevel(enchantmentsRegistry.getOrThrow(Enchantments.RIPTIDE), heldItem) > 0)
            ) {
                beFlipped()
            }
        }

        return damaged
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

    companion object {
        fun createMobAttributes(): DefaultAttributeContainer.Builder {
            return WaterCreatureEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 300.0)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.6)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 10.0)
                .add(EntityAttributes.GENERIC_ATTACK_SPEED, 8.0)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 32.0)
                .add(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, 50.0)
                .add(EntityAttributes.GENERIC_ARMOR_TOUGHNESS, 5.0)
                .add(EntityAttributes.GENERIC_ARMOR, 8.0)
        }

        val FLIPPED_ANIMATION: RawAnimation = RawAnimation.begin().then("flipped", Animation.LoopType.LOOP)
        val ATTACK_ANIMATION: RawAnimation = RawAnimation.begin().then("attack", Animation.LoopType.LOOP)

        val FLIPPED: TrackedData<Boolean> = DataTracker.registerData(KarkinosEntity::class.java, TrackedDataHandlerRegistry.BOOLEAN)
    }

    internal open class AttackGoal(private val karkinos: KarkinosEntity) : MeleeAttackGoal(karkinos, 0.7, false) {
        override fun attack(target: LivingEntity, squaredDistance: Double) {
            val d = getSquaredMaxAttackDistance(target)
            if (squaredDistance <= d && this.isCooledDown) {
                resetCooldown()
                mob.tryAttack(target)
                karkinos.attemptAttack = true
            }
            if (!this.isCooledDown)
                karkinos.handSwinging
        }

        override fun getSquaredMaxAttackDistance(entity: LivingEntity): Double {
            return (4.0f + entity.width).toDouble()
        }

        override fun start() {
            super.start()
            karkinos.attemptAttack = false
        }

        override fun stop() {
            super.stop()
            karkinos.attemptAttack = false
        }

        override fun shouldContinue(): Boolean {
            return !karkinos.isFlipped && super.shouldContinue()
        }
    }

    class KarkinosWanderAroundGoal(private val karkinos: KarkinosEntity, speed: Double) : WanderAroundGoal(karkinos, speed) {
        override fun shouldContinue(): Boolean {
            return !karkinos.isFlipped && super.shouldContinue()
        }
    }
}
