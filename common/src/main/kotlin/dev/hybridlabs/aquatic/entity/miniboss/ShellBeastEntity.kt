package dev.hybridlabs.aquatic.entity.miniboss

import net.minecraft.world.InteractionHand
import net.minecraft.world.item.SwingAnimationType
import net.minecraft.world.item.component.SwingAnimation
import java.util.UUID
import net.minecraft.world.entity.ai.targeting.TargetingConditions

import net.minecraft.world.entity.EntitySpawnReason

import net.minecraft.world.level.storage.ValueInput
import net.minecraft.world.level.storage.ValueOutput

import dev.hybridlabs.aquatic.entity.HAEntityTypes
import dev.hybridlabs.aquatic.entity.ai.control.SmoothStrafeSwimmingMoveControl
import dev.hybridlabs.aquatic.entity.ai.goal.ShellBeastRangedAttackGoal
import dev.hybridlabs.aquatic.entity.ai.goal.ShellBeastSummonBeaklingsGoal
import dev.hybridlabs.hapi.entity.ai.goal.aquatic.boids.StayInWaterGoal
import dev.hybridlabs.aquatic.entity.base.HAMinibossEntity
import dev.hybridlabs.aquatic.sound.HASoundEvents
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
import net.minecraft.world.damagesource.DamageSource
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.Mob
import net.minecraft.world.entity.MoverType
import net.minecraft.world.entity.ai.attributes.AttributeSupplier
import net.minecraft.world.entity.ai.attributes.Attributes
import net.minecraft.world.entity.ai.control.SmoothSwimmingLookControl
import net.minecraft.world.entity.ai.goal.RandomSwimmingGoal
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal
import net.minecraft.world.entity.ai.navigation.PathNavigation
import net.minecraft.world.entity.ai.navigation.WaterBoundPathNavigation
import net.minecraft.world.entity.animal.golem.IronGolem
import net.minecraft.world.entity.player.Player
import net.minecraft.world.level.Level
import net.minecraft.world.level.pathfinder.PathType
import net.minecraft.world.phys.AABB
import net.minecraft.world.phys.Vec3
import com.geckolib.animatable.manager.AnimatableManager
import com.geckolib.animation.AnimationController
import com.geckolib.animation.AnimationController.AnimationStateHandler
import com.geckolib.animation.RawAnimation
import com.geckolib.animation.state.AnimationTest
import com.geckolib.animation.`object`.PlayState
import com.geckolib.animation.`object`.EasingType
import com.geckolib.constant.DefaultAnimations
import com.geckolib.util.ClientUtil
import java.lang.ref.WeakReference
import java.util.Collections.synchronizedList
import java.util.function.Predicate

@Suppress("unused")
class ShellBeastEntity(type: EntityType<out HAMinibossEntity>, world: Level) :
    HAMinibossEntity(type, world) {
    private var summonTimer: Int = 0
    var hypnautilusCooldown: Int = 0
    var beaklingCooldown: Int = 0
    private var summonType = SummonType.NONE
    private val minions = synchronizedList(mutableListOf<WeakReference<Mob>>())

    //#region SFX
    override fun getAmbientSound(): SoundEvent {
        return HASoundEvents.SHELL_BEAST_AMBIENT.get()
    }

    override fun getHurtSound(source: DamageSource): SoundEvent {
        return HASoundEvents.SHELL_BEAST_HURT.get()
    }

    override fun getDeathSound(): SoundEvent {
        return HASoundEvents.SHELL_BEAST_DIE.get()
    }
    //#endregion

    override fun createNavigation(level: Level): PathNavigation {
        setPathfindingMalus(PathType.WATER, 0.0f)
        setPathfindingMalus(PathType.FIRE_IN_NEIGHBOR, 16.0f)
        setPathfindingMalus(PathType.FIRE, -1.0f)

        moveControl = SmoothStrafeSwimmingMoveControl(
            this,
            85,
            5,
            0.03F,
            0.1F,
            false
        )

        lookControl = SmoothSwimmingLookControl(this, 10)

        return WaterBoundPathNavigation(this, level)
    }

    fun setCharging(charging: Boolean) {
        this.entityData.set(DATA_IS_CHARGING, charging)
    }

    private var bossBar: ServerBossEvent =
        ServerBossEvent(UUID.randomUUID(), displayName, BossEvent.BossBarColor.YELLOW, BossEvent.BossBarOverlay.NOTCHED_6)

    override fun registerGoals() {
        goalSelector.addGoal(1, ShellBeastSummonBeaklingsGoal(this))
        // goalSelector.addGoal(1, ShellBeastSummonHypnautilusGoal(this))
        goalSelector.addGoal(0, StayInWaterGoal(this))
        goalSelector.addGoal(3, RandomSwimmingGoal(this, 1.0, 2))
        goalSelector.addGoal(1, ShellBeastRangedAttackGoal(this))
        targetSelector.addGoal(1, HurtByTargetGoal(this))
        this.targetSelector.addGoal(
            1,
            ShellBeastNearestTargetGoal(
                this,
                Player::class.java,
                true
            ) { target, _ -> target is Player && !(target.isCreative || target.isSpectator) }
        )

        targetSelector.addGoal(
            1, ShellBeastNearestTargetGoal(
                this, IronGolem::class.java, true
            ) { _, _ -> true }
        )
    }

    override fun swingForAttack(hand: InteractionHand) {
        this.swing(hand, SwingAnimation(SwingAnimationType.WHACK, 20), false)
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
        if (level().difficulty == Difficulty.PEACEFUL) {
            discard()
        } else {
            noActionTime = 0
        }
    }

    //#region Data
    override fun defineSynchedData(builder: SynchedEntityData.Builder) {
        super.defineSynchedData(builder)
        builder.define(DATA_IS_CHARGING, false)
        builder.define(SUMMONING, false)
    }

    override fun addAdditionalSaveData(output: ValueOutput) {
        super.addAdditionalSaveData(output)
        output.putBoolean("Summoning", isSummoning())
        output.putInt("SummonTimer", summonTimer)
        output.putInt("BeaklingCooldown", beaklingCooldown)
        output.putInt("HypnautilusCooldown", hypnautilusCooldown)
    }

    override fun readAdditionalSaveData(input: ValueInput) {
        if (hasCustomName()) {
            bossBar.name = this.displayName
        }

        this.setSummoning(input.getBooleanOr("Summoning", isSummoning()))
        this.summonTimer = input.getIntOr("SummonTimer", summonTimer)
        this.beaklingCooldown = input.getIntOr("BeaklingCooldown", beaklingCooldown)
        this.hypnautilusCooldown = input.getIntOr("HypnautilusCooldown", hypnautilusCooldown)

        super.readAdditionalSaveData(input)
    }
    //#endregion

    override fun travel(travelVector: Vec3) {
        if (this.isEffectiveAi && this.isInWater) {
            this.moveRelative(this.speed, travelVector)
            this.move(MoverType.SELF, this.deltaMovement)
            this.deltaMovement = deltaMovement.scale(0.9)
        } else {
            super.travel(travelVector)
        }
    }

    override fun aiStep() {
        if (tickCount < 120) {
            navigation.stop()
            deltaMovement = Vec3.ZERO
        }

        if (beaklingCooldown > 0) beaklingCooldown--
        if (hypnautilusCooldown > 0) hypnautilusCooldown--

        if (isSummoning()) {
            summonTimer--

            if (summonTimer == 0) {
                if (this.isUnderWater) {
                    when (summonType) {
                        SummonType.BEAKLINGS -> summonBeaklings()
                        // SummonType.HYPNAUTILUS -> summonHypnautilus()
                        else -> {}
                    }
                }
                stopSummoning()
            }
        }
        cleanMinions()
        bossBar.progress = health / maxHealth
        super.aiStep()
    }

    override fun setCustomName(name: Component?) {
        super.setCustomName(name)
        bossBar.name = this.displayName
    }

    //#region Animations
    override fun registerControllers(controllers: AnimatableManager.ControllerRegistrar) {

        controllers.add(
            DefaultAnimations.genericSwimIdleController<ShellBeastEntity>()
                .setTransitionTicks(8)
        )

        controllers.add(
            AnimationController<ShellBeastEntity>("shoot_controller") { PlayState.STOP }
                .triggerableAnim("shoot", DefaultAnimations.ATTACK_SHOOT)
                .setSoundKeyframeHandler { event ->
                    val player = ClientUtil.getClientPlayer()

                    player?.playSound(HASoundEvents.SHELL_BEAST_SHOOT.get(), 1f, 1f)
                }
        )

        controllers.add(
            AnimationController<ShellBeastEntity>("summon_controller") { PlayState.STOP }
                .triggerableAnim("summon", SUMMON_ANIMATION)
                .setSoundKeyframeHandler { event ->
                    val player = ClientUtil.getClientPlayer()

                    player?.playSound(HASoundEvents.SHELL_BEAST_ROAR.get(), 1f, 1f)
                }
        )

        controllers.add(
            AnimationController<ShellBeastEntity>("spawn_controller",
                AnimationStateHandler { state: AnimationTest<ShellBeastEntity> ->
                    if (this.tickCount < 120)
                        return@AnimationStateHandler state.setAndContinue(DefaultAnimations.SPAWN)
                    PlayState.STOP
                }
            )
                .setSoundKeyframeHandler { event ->
                    val player = ClientUtil.getClientPlayer()

                    player?.playSound(HASoundEvents.SHELL_BEAST_ROAR.get(), 1f, 1f)
                }
        )
    }
    //#endregion

    companion object {
        fun createMobAttributes(): AttributeSupplier.Builder {
            return createLivingAttributes()
                .add(Attributes.TEMPT_RANGE, 10.0)
                .add(Attributes.MAX_HEALTH, 500.0)
                .add(Attributes.MOVEMENT_SPEED, 0.7)
                .add(Attributes.ATTACK_DAMAGE, 8.0)
                .add(Attributes.ATTACK_KNOCKBACK, 1.0)
                .add(Attributes.FOLLOW_RANGE, 64.0)
                .add(Attributes.KNOCKBACK_RESISTANCE, 1.0)
        }

        val SUMMON_ANIMATION: RawAnimation = RawAnimation.begin().thenPlay("misc.summon")

        private val DATA_IS_CHARGING: EntityDataAccessor<Boolean> =
            SynchedEntityData.defineId(ShellBeastEntity::class.java, EntityDataSerializers.BOOLEAN)
        private val SUMMONING: EntityDataAccessor<Boolean> =
            SynchedEntityData.defineId(ShellBeastEntity::class.java, EntityDataSerializers.BOOLEAN)
    }

    enum class SummonType {
        NONE,
        BEAKLINGS,
        HYPNAUTILUS
    }

    // Scan the entire cube of targetDistance, not just a slice like NearestAttackableTargetGoal
    class ShellBeastNearestTargetGoal<T : LivingEntity>(
        shellBeast: Mob,
        target: Class<T>,
        mustSee: Boolean,
        predicate: TargetingConditions.Selector,
    ) :
        NearestAttackableTargetGoal<T>(shellBeast, target, mustSee, predicate) {
        override fun getTargetSearchArea(targetDistance: Double): AABB {
            return this.mob.boundingBox.inflate(targetDistance)
        }
    }

    fun isSummoning(): Boolean {
        return entityData.get(SUMMONING)
    }

    private fun setSummoning(summon: Boolean) {
        entityData.set(SUMMONING, summon)
    }

    fun startSummoning(type: SummonType) {
        summonType = type

        setSummoning(true)
        playSound(SoundEvents.EVOKER_PREPARE_ATTACK, 1.0f, 1.0f)

        summonTimer = 80

        navigation.stop()
    }

    fun stopSummoning() {
        setSummoning(false)

        when (summonType) {
            SummonType.BEAKLINGS -> {
                beaklingCooldown = 300
            }

            SummonType.HYPNAUTILUS -> {
                hypnautilusCooldown = 800
            }

            else -> {}
        }

        summonType = SummonType.NONE
        summonTimer = 0
    }

    fun addMinion(minion: HypnautilusEntity) {
        minions.add(WeakReference(minion))
        minion.setOwner(this)
        minion.beastPosition = minions.size - 1
    }

    fun hasMinions(): Boolean {
        return (minions.isNotEmpty())
    }

    fun cleanMinions() {
        minions.removeAll { ref -> ref.get() == null }
    }

    // private fun summonHypnautilus() {
    //    val count = 6

    //     for (i in 0 until count) {
    //      val hypnautilus = HAEntityTypes.HYPNAUTILUS.get().create(level(), EntitySpawnReason.MOB_SUMMONED)
    //       if (hypnautilus != null) {
    //          addMinion(hypnautilus)
    //         val spawnPos = hypnautilus.calcBeastRelativePos()
    //         hypnautilus.snapTo(
    //           spawnPos.x,
    //            spawnPos.y,
    //           spawnPos.z,
    //          0f,
    //         0f
    //     )
    //      level().addFreshEntity(hypnautilus)
    //     }
    //  }
    // }

    private fun summonBeaklings() {
        val random = this.random
        val count = 3

        for (i in 0 until count) {
            val offsetX = (random.nextDouble() - 0.5) * 1.0
            val offsetZ = (random.nextDouble() - 0.5) * 1.0
            val spawnPos = blockPosition().offset(offsetX.toInt(), 0, offsetZ.toInt())

            val beakling = HAEntityTypes.BEAKLING.get().create(level(), EntitySpawnReason.MOB_SUMMONED)
            if (beakling != null) {
                beakling.snapTo(
                    spawnPos.x.toDouble(),
                    spawnPos.y.toDouble(),
                    spawnPos.z.toDouble(),
                    random.nextFloat() * 360f,
                    0f
                )
                beakling.setOwner(this)
                level().addFreshEntity(beakling)
            }
        }
    }
}