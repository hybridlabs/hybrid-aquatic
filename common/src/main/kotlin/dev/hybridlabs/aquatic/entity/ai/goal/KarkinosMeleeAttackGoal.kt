package dev.hybridlabs.aquatic.entity.ai.goal

import dev.hybridlabs.aquatic.entity.miniboss.KarkinosEntity
import net.minecraft.world.InteractionHand
import net.minecraft.world.entity.EntitySelector
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.ai.goal.Goal
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.Items
import net.minecraft.world.level.pathfinder.Path
import java.util.*
import kotlin.math.max

open class KarkinosMeleeAttackGoal(
    protected val karkinos: KarkinosEntity,
    private val speedModifier: Double,
    private val followingTargetEvenIfNotSeen: Boolean,
) :
    Goal() {
    private var path: Path? = null
    private var ticksUntilNextPathRecalculation = 0
    private var ticksUntilNextAttack: Int = 0
    private var lastCanUseCheck: Long = 0
    private var attackDelayTicks = 0
    private var pendingTarget: LivingEntity? = null

    init {
        this.flags = EnumSet.of(Flag.MOVE, Flag.LOOK)
    }

    override fun canUse(): Boolean {
        val i = karkinos.level().gameTime
        if (i - this.lastCanUseCheck < 20L) {
            return false
        } else {
            this.lastCanUseCheck = i

            if (karkinos.isFlipped() || karkinos.isSummoning()) return false

            val livingEntity = karkinos.target
            if (livingEntity == null) {
                return false
            } else if (!livingEntity.isAlive) {
                return false
            } else {
                this.path = karkinos.navigation.createPath(livingEntity, 3)
                return if (this.path != null) {
                    true
                } else {
                    getAttackReachSqr(livingEntity) >= karkinos.distanceToSqr(
                        livingEntity.x,
                        livingEntity.y,
                        livingEntity.z
                    )
                }
            }
        }
    }

    override fun canContinueToUse(): Boolean {
        if (karkinos.isFlipped() || karkinos.isSummoning()) return false

        val livingEntity = karkinos.target ?: return false

        return if (!livingEntity.isAlive) {
            false
        } else if (!this.followingTargetEvenIfNotSeen) {
            !karkinos.navigation.isDone
        } else if (!karkinos.isWithinRestriction(livingEntity.blockPosition())) {
            false
        } else {
            livingEntity !is Player || (!livingEntity.isSpectator && !livingEntity.isCreative)
        }
    }

    override fun start() {
        karkinos.navigation.moveTo(this.path, this.speedModifier)
        karkinos.isAggressive = true
        karkinos.isSprinting = true
        karkinos.swinging = false
        karkinos.swingTime = 0
        this.ticksUntilNextPathRecalculation = 0
        this.ticksUntilNextAttack = 0
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

    override fun tick() {
        if (karkinos.isFlipped()) {
            stop()
            return
        }

        if (attackDelayTicks > 0) {
            attackDelayTicks--
            if (attackDelayTicks == 0 && pendingTarget != null && !karkinos.level().isClientSide) {
                karkinos.doHurtTarget(pendingTarget!!)

                val target = pendingTarget
                if (target is Player) {
                    val itemInUse = target.useItem
                    if (itemInUse.`is`(Items.SHIELD)) {
                        target.disableShield(true)

                        target.level().broadcastEntityEvent(target, 30.toByte())
                    }
                }
                resetAttackCooldown()
                pendingTarget = null
            }
        }

        val livingEntity = karkinos.target

        if (!karkinos.navigation.isInProgress) {
            if (livingEntity != null) {
                karkinos.navigation.moveTo(livingEntity, speedModifier)
            }
        }

        if (livingEntity != null) {
            if (karkinos.distanceToSqr(livingEntity) < 16.0) {
                karkinos.lookControl.setLookAt(livingEntity, 10.0f, 10.0f)
            }
            val d0 = karkinos.getPerceivedTargetDistanceSquareForMeleeAttack(livingEntity)
            this.ticksUntilNextPathRecalculation =
                max((this.ticksUntilNextPathRecalculation - 1).toDouble(), 0.0).toInt()
            if ((followingTargetEvenIfNotSeen || karkinos.sensing.hasLineOfSight(livingEntity)) && ticksUntilNextPathRecalculation <= 0) {
                karkinos.navigation.moveTo(livingEntity, speedModifier)
                ticksUntilNextPathRecalculation = 20
            }

            this.ticksUntilNextAttack =
                max((this.ticksUntilNextAttack - 1).toDouble(), 0.0).toInt()
            this.checkAndPerformAttack(livingEntity, d0)
        }
    }

    protected open fun checkAndPerformAttack(enemy: LivingEntity, distToEnemySqr: Double) {
        val reachSqr = getAttackReachSqr(enemy)
        if (distToEnemySqr <= reachSqr && ticksUntilNextAttack <= 0 && attackDelayTicks == 0) {
            karkinos.swing(InteractionHand.MAIN_HAND)
            attackDelayTicks = 10
            pendingTarget = enemy
            ticksUntilNextAttack = attackDelayTicks
        }
    }

    private fun resetAttackCooldown() {
        this.ticksUntilNextAttack = this.adjustedTickDelay(20)
    }

    protected open fun getAttackReachSqr(attackTarget: LivingEntity): Double {
        return (karkinos.bbWidth * 1.75f * karkinos.bbWidth * 1.75f + attackTarget.bbWidth).toDouble()
    }
}