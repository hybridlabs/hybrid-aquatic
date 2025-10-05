package dev.hybridlabs.aquatic.entity.ai.goal

import dev.hybridlabs.aquatic.entity.miniboss.KarkinosEntity
import net.minecraft.world.InteractionHand
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.Items
import java.util.*
import kotlin.math.max

open class KarkinosAttackGoal(
    protected val karkinos: KarkinosEntity,
    private val speedModifier: Double,
    private val followingTargetEvenIfNotSeen: Boolean,
) :
    MeleeAttackGoal(karkinos, speedModifier, true) {
    private var ticksUntilNextPathRecalculation = 0
    private var ticksUntilNextAttack: Int = 0
    private var attackDelayTicks = 0
    private var pendingTarget: LivingEntity? = null

    init {
        this.flags = EnumSet.of(Flag.MOVE, Flag.LOOK)
    }

    override fun canUse(): Boolean {
        if (karkinos.isFlipped() || karkinos.isSummoning()) return false

        return super.canUse()
    }

    override fun canContinueToUse(): Boolean {
        if (karkinos.isFlipped() || karkinos.isSummoning() || karkinos.lastAttackBlocked) return false
        return super.canContinueToUse()
    }

    override fun start() {
        karkinos.isSprinting = true
        return super.start()
    }

    override fun stop() {
        karkinos.isSprinting = false
        return super.stop()
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
                val target = pendingTarget!!

                val wasHurt = karkinos.doHurtTarget(target)

                if (target is Player) {
                    val itemInUse = target.useItem
                    if (itemInUse.`is`(Items.SHIELD) && !wasHurt) {
                        karkinos.lastAttackBlocked = true
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

    override fun checkAndPerformAttack(enemy: LivingEntity, distToEnemySqr: Double) {
        val reachSqr = getAttackReachSqr(enemy)
        if (distToEnemySqr <= reachSqr && ticksUntilNextAttack <= 0 && attackDelayTicks == 0) {
            karkinos.swing(InteractionHand.MAIN_HAND)
            attackDelayTicks = 10
            pendingTarget = enemy
            ticksUntilNextAttack = attackDelayTicks
        }
    }

    override fun resetAttackCooldown() {
        this.ticksUntilNextAttack = this.adjustedTickDelay(20)
    }

    override fun getAttackReachSqr(attackTarget: LivingEntity): Double {
        return (karkinos.bbWidth * 1.75f * karkinos.bbWidth * 1.75f + attackTarget.bbWidth).toDouble()
    }
}