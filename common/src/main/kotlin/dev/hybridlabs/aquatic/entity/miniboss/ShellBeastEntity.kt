package dev.hybridlabs.aquatic.entity.miniboss

import dev.hybridlabs.aquatic.entity.HAEntityTypes
import dev.hybridlabs.aquatic.entity.ai.control.SmoothStrafeSwimmingMoveControl
import dev.hybridlabs.aquatic.entity.ai.goal.ShellBeastSummonGoal
import dev.hybridlabs.aquatic.entity.ai.goal.boids.StayInWaterGoal
import dev.hybridlabs.aquatic.entity.miniboss.KarkinosEntity.Companion.SUMMONING
import dev.hybridlabs.aquatic.entity.misc.CavitationBubbleEntity
import dev.hybridlabs.aquatic.sound.HASoundEvents
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
import net.minecraft.world.damagesource.DamageSource
import net.minecraft.world.entity.*
import net.minecraft.world.entity.ai.attributes.AttributeSupplier
import net.minecraft.world.entity.ai.attributes.Attributes
import net.minecraft.world.entity.ai.control.SmoothSwimmingLookControl
import net.minecraft.world.entity.ai.goal.Goal
import net.minecraft.world.entity.ai.goal.RandomSwimmingGoal
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal
import net.minecraft.world.entity.ai.navigation.PathNavigation
import net.minecraft.world.entity.ai.navigation.WaterBoundPathNavigation
import net.minecraft.world.entity.animal.IronGolem
import net.minecraft.world.entity.player.Player
import net.minecraft.world.level.Level
import net.minecraft.world.level.pathfinder.BlockPathTypes
import net.minecraft.world.phys.AABB
import net.minecraft.world.phys.Vec3
import software.bernie.geckolib.constant.DefaultAnimations
import software.bernie.geckolib.core.animation.AnimatableManager
import software.bernie.geckolib.core.animation.AnimationController
import java.util.*
import java.util.function.Predicate
import kotlin.math.abs

class ShellBeastEntity(type: EntityType<out HAMinibossEntity>, world: Level) :
    HAMinibossEntity(type, world) {
    private var explosionPower = 0
    private var summonTimer: Int = 0
    var summonCooldown: Int = 0

    fun getExplosionPower(): Int {
        return this.explosionPower
    }

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
        setPathfindingMalus(BlockPathTypes.WATER, 0.0f)
        setPathfindingMalus(BlockPathTypes.DANGER_FIRE, 16.0f)
        setPathfindingMalus(BlockPathTypes.DAMAGE_FIRE, -1.0f)

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

    fun isCharging(): Boolean {
        return this.entityData.get(DATA_IS_CHARGING) as Boolean
    }

    fun setCharging(charging: Boolean) {
        this.entityData.set(DATA_IS_CHARGING, charging)
    }

    private var bossBar: ServerBossEvent =
        ServerBossEvent(displayName, BossEvent.BossBarColor.YELLOW, BossEvent.BossBarOverlay.NOTCHED_6)

    override fun registerGoals() {
        goalSelector.addGoal(1, ShellBeastSummonGoal(this))
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
            ) { target -> target is Player && !(target.isCreative || target.isSpectator) }
        )

        targetSelector.addGoal(
            1, ShellBeastNearestTargetGoal(
                this, IronGolem::class.java, true
            ) { true }
        )
    }

    private fun getHandSwingDuration(): Int {
        return 20
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

    override fun getMobType(): MobType {
        return MobType.WATER
    }

    //#region Data
    override fun defineSynchedData() {
        super.defineSynchedData()
        entityData.define(DATA_IS_CHARGING, false)
        entityData.define(SUMMONING, false)
    }

    override fun addAdditionalSaveData(compound: CompoundTag) {
        super.addAdditionalSaveData(compound)
        compound.putByte("ExplosionPower", this.explosionPower.toByte())
        compound.putBoolean("Summoning", isSummoning())
        compound.putInt("SummonTimer", summonTimer)
        compound.putInt("SummonCooldown", summonCooldown)
    }

    override fun readAdditionalSaveData(compound: CompoundTag) {
        if (hasCustomName()) {
            bossBar.name = this.displayName
        }

        if (compound.contains("ExplosionPower", 99)) {
            this.explosionPower = compound.getByte("ExplosionPower").toInt()
        }

        this.setSummoning(compound.getBoolean("Summoning"))
        this.summonTimer = compound.getInt("SummonTimer")
        this.summonCooldown = compound.getInt("SummonCooldown")

        super.readAdditionalSaveData(compound)
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

    override fun getStandingEyeHeight(pose: Pose, dimensions: EntityDimensions): Float {
        return dimensions.height * 0.4f
    }

    override fun aiStep() {

        if (summonCooldown > 0) summonCooldown--

        if (isSummoning()) {
            summonTimer--

            if (summonTimer == 0) {
                if (this.isUnderWater) {
                    summonHypnautilus()
                }
                stopSummoning()
            }
        }
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
            AnimationController(this, "Shell Beast Controller", 8) { state ->
                when {
                    isInWater -> {
                        state.setAndContinue(DefaultAnimations.SWIM)
                    }

                    isCharging() -> {
                        state.setAndContinue(DefaultAnimations.ATTACK_SHOOT)
                    }

                    else -> {
                        state.setAndContinue(DefaultAnimations.IDLE)
                    }
                }
            }
        )
    }
    //#endregion

    companion object {
        fun createMobAttributes(): AttributeSupplier.Builder {
            return createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 350.0)
                .add(Attributes.MOVEMENT_SPEED, 0.7)
                .add(Attributes.ATTACK_DAMAGE, 10.0)
                .add(Attributes.ATTACK_KNOCKBACK, 1.0)
                .add(Attributes.FOLLOW_RANGE, 64.0)
                .add(Attributes.KNOCKBACK_RESISTANCE, 1.0)
        }

        private val DATA_IS_CHARGING: EntityDataAccessor<Boolean> =
            SynchedEntityData.defineId(ShellBeastEntity::class.java, EntityDataSerializers.BOOLEAN)
    }

    // Scan the entire cube of targetDistance, not just a slice like NearestAttackableTargetGoal
    class ShellBeastNearestTargetGoal<T : LivingEntity>(
        shellBeast: Mob,
        target: Class<T>,
        mustSee: Boolean,
        predicate: Predicate<LivingEntity>,
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

    fun startSummoning() {
        setSummoning(true)
        playSound(SoundEvents.EVOKER_PREPARE_ATTACK, 1.0f, 1.0f)
        summonTimer = 30
        summonCooldown = 480
        navigation.stop()
    }

    fun stopSummoning() {
        setSummoning(false)
        summonTimer = 0
        summonCooldown = 600
    }

    private fun summonHypnautilus() {
        val count = 6

        for (i in 0 until count) {


            val hypnautilus = HAEntityTypes.HYPNAUTILUS.get().create(level())
            if (hypnautilus != null) {
                hypnautilus.setOwner(this)
                hypnautilus.beastPosition = i
                val spawnPos = hypnautilus.calcBeastOffset()
                hypnautilus.moveTo(
                    spawnPos.x,
                    spawnPos.y,
                    spawnPos.z,
                    0f,
                    0f
                )
                hypnautilus.setLimitedLife(600)
                level().addFreshEntity(hypnautilus)
            }
        }
    }

    class ShellBeastRangedAttackGoal(private val shellBeast: ShellBeastEntity) : Goal() {
        var chargeTime: Int = 0
        private var seeTime = 0
        private var strafingClockwise = false
        private var strafingBackwards = false
        private var strafingTime = -1
        private val strafeAmount = 0.20f

        init {
            this.flags = EnumSet.of(Flag.MOVE, Flag.LOOK)
        }

        override fun canUse(): Boolean = shellBeast.target != null

        override fun start() {
            chargeTime = 0
        }

        override fun stop() {
            shellBeast.setCharging(false)
        }

        override fun requiresUpdateEveryTick(): Boolean = true

        override fun tick() {
            val target = shellBeast.target ?: return

            val distance = shellBeast.distanceToSqr(target.position())
            val canSee = shellBeast.sensing.hasLineOfSight(target)
            val seenBefore = seeTime > 0

            if (canSee != seenBefore) {
                seeTime = 0
            }

            if (canSee) seeTime++ else seeTime--

            val yDelta = abs(shellBeast.y - target.y)

            if (distance <= 4096.0 && yDelta <= 8.0 && chargeTime < 20 && seeTime >= 20) {
                shellBeast.navigation.stop()
                strafingTime++
            } else {
                if (distance >= 64)
                    shellBeast.navigation.moveTo(target, 1.0)
                strafingTime = -1
            }

            if (strafingTime >= 20) {
                if (shellBeast.random.nextFloat() < 0.3f) {
                    strafingClockwise = !strafingClockwise
                }

                if (distance <= 64) {
                    strafingBackwards = true
                } else if (shellBeast.random.nextFloat() < 0.3f) {
                    strafingBackwards = !strafingBackwards
                }
                strafingTime = 0
            }


            if (strafingTime > -1) {
                strafingBackwards = distance < 256 // 16**2
                shellBeast.moveControl.strafe(
                    if (strafingBackwards) -strafeAmount else strafeAmount,
                    if (strafingClockwise) strafeAmount else -strafeAmount
                )

            }

            shellBeast.lookAt(target, 5f, 5f)

            if (distance < 4096.0 && canSee) {
                val level = shellBeast.level()
                chargeTime++

                if (chargeTime == 20 && !shellBeast.isSilent) {
                    level.levelEvent(null, 1015, shellBeast.blockPosition(), 0)
                }

                if (chargeTime == 20 || chargeTime == 40 || chargeTime == 60) {
                    val view = shellBeast.getViewVector(1.0f)

                    val dxFire = target.x - (shellBeast.x + view.x * 4.0)
                    val dyFire = target.getY(0.5) - (0.5 + shellBeast.getY(0.5))
                    val dzFire = target.z - (shellBeast.z + view.z * 4.0)

                    if (!shellBeast.isSilent) {
                        level.levelEvent(null, 1016, shellBeast.blockPosition(), 0)
                    }

                    val cavitationBubble = CavitationBubbleEntity(
                        level,
                        shellBeast,
                        dxFire,
                        dyFire,
                        dzFire,
                        shellBeast.getExplosionPower()
                    )

                    cavitationBubble.setPos(
                        shellBeast.x + view.x * 4.0,
                        shellBeast.getY(0.5) - 0.3,
                        shellBeast.z + view.z * 4.0
                    )

                    level.addFreshEntity(cavitationBubble)
                }

                if (chargeTime == 60) {
                    chargeTime = -60
                }
            } else if (chargeTime > 0) {
                chargeTime--
            }

            shellBeast.setCharging(chargeTime > 10)
        }
    }
}