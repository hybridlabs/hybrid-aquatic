package dev.hybridlabs.aquatic.entity.ai.goal

import dev.hybridlabs.aquatic.entity.miniboss.ManglerfishEntity
import net.minecraft.world.InteractionHand
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.Items
import java.util.*
import kotlin.math.max

open class ManglerfishMeleeAttackGoal(
    protected val manglerfish: ManglerfishEntity,
    private val speedModifier: Double,
    private val followingTargetEvenIfNotSeen: Boolean,
) :
    MeleeAttackGoal(manglerfish, speedModifier, true) {
    private var ticksUntilNextPathRecalculation = 0
    private var ticksUntilNextAttack: Int = 0
    private var attackDelayTicks = 0
    private var pendingTarget: LivingEntity? = null

    init {
        this.flags = EnumSet.of(Flag.MOVE, Flag.LOOK)
    }

    override fun start() {
        manglerfish.isSprinting = true
        return super.start()
    }

    override fun stop() {
        manglerfish.isSprinting = false
        return super.stop()
    }


    override fun requiresUpdateEveryTick(): Boolean {
        return true
    }

    override fun tick() {
        if (attackDelayTicks > 0) {
            attackDelayTicks--
            if (attackDelayTicks == 0 && pendingTarget != null && !manglerfish.level().isClientSide) {
                manglerfish.doHurtTarget(pendingTarget!!)

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

        val livingEntity = manglerfish.target

        if (!manglerfish.navigation.isInProgress) {
            if (livingEntity != null) {
                manglerfish.navigation.moveTo(livingEntity, speedModifier)
            }
        }

        if (livingEntity != null) {
            if (manglerfish.distanceToSqr(livingEntity) < 16.0) {
                manglerfish.lookControl.setLookAt(livingEntity, 10.0f, 10.0f)
            }
            val d0 = manglerfish.getPerceivedTargetDistanceSquareForMeleeAttack(livingEntity)
            this.ticksUntilNextPathRecalculation =
                max((this.ticksUntilNextPathRecalculation - 1).toDouble(), 0.0).toInt()
            if ((followingTargetEvenIfNotSeen || manglerfish.sensing.hasLineOfSight(livingEntity)) && ticksUntilNextPathRecalculation <= 0) {
                manglerfish.navigation.moveTo(livingEntity, speedModifier)
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
            manglerfish.swing(InteractionHand.MAIN_HAND)
            attackDelayTicks = 10
            pendingTarget = enemy
            ticksUntilNextAttack = attackDelayTicks
        }
    }

    override fun resetAttackCooldown() {
        this.ticksUntilNextAttack = this.adjustedTickDelay(20)
    }

    override fun getAttackReachSqr(attackTarget: LivingEntity): Double {
        return (manglerfish.bbWidth * 1.75f * manglerfish.bbWidth * 1.75f + attackTarget.bbWidth).toDouble()
    }
}
