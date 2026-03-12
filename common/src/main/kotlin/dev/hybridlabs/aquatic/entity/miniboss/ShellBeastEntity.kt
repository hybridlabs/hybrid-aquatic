package dev.hybridlabs.aquatic.entity.miniboss

import dev.hybridlabs.aquatic.entity.ai.goal.boids.StayInWaterGoal
import net.minecraft.nbt.CompoundTag
import net.minecraft.network.chat.Component
import net.minecraft.network.syncher.EntityDataAccessor
import net.minecraft.network.syncher.EntityDataSerializers
import net.minecraft.network.syncher.SynchedEntityData
import net.minecraft.server.level.ServerBossEvent
import net.minecraft.server.level.ServerPlayer
import net.minecraft.util.Mth
import net.minecraft.world.BossEvent
import net.minecraft.world.Difficulty
import net.minecraft.world.entity.*
import net.minecraft.world.entity.ai.attributes.AttributeSupplier
import net.minecraft.world.entity.ai.attributes.Attributes
import net.minecraft.world.entity.ai.control.SmoothSwimmingLookControl
import net.minecraft.world.entity.ai.control.SmoothSwimmingMoveControl
import net.minecraft.world.entity.ai.goal.Goal
import net.minecraft.world.entity.ai.goal.RandomSwimmingGoal
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal
import net.minecraft.world.entity.ai.navigation.PathNavigation
import net.minecraft.world.entity.ai.navigation.WaterBoundPathNavigation
import net.minecraft.world.entity.animal.IronGolem
import net.minecraft.world.entity.player.Player
import net.minecraft.world.entity.projectile.LargeFireball
import net.minecraft.world.level.Level
import net.minecraft.world.level.pathfinder.BlockPathTypes
import net.minecraft.world.phys.Vec3
import software.bernie.geckolib.constant.DefaultAnimations
import software.bernie.geckolib.core.animation.AnimatableManager
import software.bernie.geckolib.core.animation.AnimationController
import software.bernie.geckolib.core.`object`.PlayState
import java.util.*

class ShellBeastEntity(type: EntityType<out HybridAquaticMinibossEntity>, world: Level) :
    HybridAquaticMinibossEntity(type, world) {
    private var explosionPower = 0

    fun getExplosionPower(): Int {
        return this.explosionPower
    }

    override fun createNavigation(level: Level): PathNavigation {
        setPathfindingMalus(BlockPathTypes.WATER, 0.0f)
        setPathfindingMalus(BlockPathTypes.DANGER_FIRE, 16.0f)
        setPathfindingMalus(BlockPathTypes.DAMAGE_FIRE, -1.0f)

        moveControl = SmoothSwimmingMoveControl(
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
        goalSelector.addGoal(0, StayInWaterGoal(this))
        goalSelector.addGoal(3, RandomSwimmingGoal(this, 1.0, 2))
        goalSelector.addGoal(1, ShellBeastShootProjectileGoal(this))
        targetSelector.addGoal(1, HurtByTargetGoal(this))
        targetSelector.addGoal(1, NearestAttackableTargetGoal(
            this,
            Player::class.java,
            10,
            true,
            false,
            null)
        )
        targetSelector.addGoal(1, NearestAttackableTargetGoal(
            this, IronGolem::class.java, 10,
            true,
            false,
            null)
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
        this.entityData.define(DATA_IS_CHARGING, false)
    }

    override fun addAdditionalSaveData(compound: CompoundTag) {
        super.addAdditionalSaveData(compound)
        compound.putByte("ExplosionPower", this.explosionPower.toByte())
    }

    override fun readAdditionalSaveData(compound: CompoundTag) {
        if (hasCustomName()) {
            bossBar.name = this.displayName
        }

        if (compound.contains("ExplosionPower", 99)) {
            this.explosionPower = compound.getByte("ExplosionPower").toInt()
        }

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
                        state.setAndContinue(if (isSprinting && state.isMoving) DefaultAnimations.RUN else DefaultAnimations.SWIM)
                    }

                    else -> {
                        state.setAndContinue(DefaultAnimations.SWIM)
                    }
                }
            }
        )

        controllers.add(
            DefaultAnimations.genericAttackAnimation(this, DefaultAnimations.ATTACK_BITE)
        )

        controllers.add(
            AnimationController(this, "Shell Beast Projectile Attack", 4) { state ->

                when {
                    isCharging() -> {
                        state.setAndContinue(DefaultAnimations.ATTACK_SHOOT)
                    }

                    else -> PlayState.STOP
                }
            }
        )
    }
    //#endregion

    companion object {
        fun createMobAttributes(): AttributeSupplier.Builder {
            return createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 350.0)
                .add(Attributes.MOVEMENT_SPEED, 0.75)
                .add(Attributes.ATTACK_DAMAGE, 10.0)
                .add(Attributes.ATTACK_KNOCKBACK, 1.0)
                .add(Attributes.FOLLOW_RANGE, 64.0)
                .add(Attributes.KNOCKBACK_RESISTANCE, 1.0)
        }

        private val DATA_IS_CHARGING: EntityDataAccessor<Boolean> =
            SynchedEntityData.defineId(ShellBeastEntity::class.java, EntityDataSerializers.BOOLEAN)
    }

    class ShellBeastShootProjectileGoal(private val shellBeast: ShellBeastEntity) : Goal() {
        var chargeTime: Int = 0

        init {
            this.flags = EnumSet.of(Flag.MOVE,Flag.LOOK)
        }

        override fun canUse(): Boolean {
            return this.shellBeast.target != null
        }

        override fun start() {
            this.chargeTime = 0
        }

        override fun stop() {
            this.shellBeast.setCharging(false)
        }

        override fun requiresUpdateEveryTick(): Boolean {
            return true
        }

        override fun tick() {
            val target = shellBeast.target ?: return

            if (this.shellBeast.target == null) {
                val vec3 = this.shellBeast.deltaMovement
                this.shellBeast.yRot = -(Mth.atan2(vec3.x, vec3.z).toFloat()) * (180f / Math.PI.toFloat())
                this.shellBeast.yBodyRot = this.shellBeast.yRot
            } else {
                val livingentity = this.shellBeast.target
                if (livingentity!!.distanceToSqr(this.shellBeast) < 4096.0) {
                    val d1 = livingentity.x - this.shellBeast.x
                    val d2 = livingentity.z - this.shellBeast.z
                    this.shellBeast.yRot = -(Mth.atan2(d1, d2).toFloat()) * (180f / Math.PI.toFloat())
                    this.shellBeast.yBodyRot = this.shellBeast.yRot
                }
            }

            if (target.distanceToSqr(shellBeast) < 4096.0 && shellBeast.hasLineOfSight(target)) {

                val level = shellBeast.level()
                ++chargeTime

                if (chargeTime == 20 && !shellBeast.isSilent) {
                    level.levelEvent(null as Player?, 1015, shellBeast.blockPosition(), 0)
                }

                if (chargeTime == 20 || chargeTime == 40 || chargeTime == 60) {

                    val view = shellBeast.getViewVector(1.0f)

                    val dx = target.x - (shellBeast.x + view.x * 4.0)
                    val dy = target.getY(0.5) - (0.5 + shellBeast.getY(0.5))
                    val dz = target.z - (shellBeast.z + view.z * 4.0)

                    if (!shellBeast.isSilent) {
                        level.levelEvent(null as Player?, 1016, shellBeast.blockPosition(), 0)
                    }

                    val fireball = LargeFireball(
                        level,
                        shellBeast,
                        dx,
                        dy,
                        dz,
                        shellBeast.getExplosionPower()
                    )

                    fireball.setPos(
                        shellBeast.x + view.x * 4.0,
                        shellBeast.getY(0.5) - 0.25,
                        shellBeast.z + view.z * 4.0
                    )

                    level.addFreshEntity(fireball)
                }

                if (chargeTime == 60) {
                    chargeTime = -60
                }

            } else if (chargeTime > 0) {
                --chargeTime
            }

            shellBeast.setCharging(chargeTime > 10)
        }
    }
}