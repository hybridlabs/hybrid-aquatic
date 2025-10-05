package dev.hybridlabs.aquatic.entity.ai.goal

import dev.hybridlabs.aquatic.entity.miniboss.KarkinosEntity
import net.minecraft.world.InteractionHand
import net.minecraft.world.entity.EntitySelector
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.ai.attributes.Attributes
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.Items
import net.minecraft.world.level.pathfinder.Path
import java.util.*
import kotlin.math.max
import kotlin.math.sqrt

open class KarkinosHeavyAttackGoal(
    protected val karkinos: KarkinosEntity,
    private val speedModifier: Double,
    private val followingTargetEvenIfNotSeen: Boolean,
) :
    MeleeAttackGoal(karkinos, speedModifier, true) {
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

            if (!karkinos.lastAttackBlocked) return false
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
        if (karkinos.lastAttackBlocked && karkinos.isSlamming()) return true
        return super.canContinueToUse()
    }

    override fun start() {
        karkinos.startSlamming()
        karkinos.isSprinting = true
        return super.start()
    }

    override fun stop() {
        val livingEntity = karkinos.target
        if (!EntitySelector.NO_CREATIVE_OR_SPECTATOR.test(livingEntity)) {
            karkinos.target = null
        }

        karkinos.stopSlamming()
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
                val target = pendingTarget!!
                karkinos.lastAttackBlocked = false

                val baseDamage = karkinos.getAttributeValue(Attributes.ATTACK_DAMAGE).toFloat()
                val heavyDamage = if (baseDamage > 0f)
                    baseDamage / 2.0f + karkinos.random.nextInt(baseDamage.toInt()).toFloat()
                else baseDamage

                val damageSource = karkinos.damageSources().mobAttack(karkinos)
                val wasHurt = target.hurt(damageSource, heavyDamage * 1.5f)

                if (wasHurt) {
                    val knockbackResistance = target.getAttributeValue(Attributes.KNOCKBACK_RESISTANCE)

                    val resistanceFactor = max(0.0, 1.0 - knockbackResistance)

                    target.deltaMovement = target.deltaMovement.add(0.0, 0.4 * resistanceFactor, 0.0)

                    val dx = target.x - karkinos.x
                    val dz = target.z - karkinos.z
                    val dist = max(0.001, sqrt(dx * dx + dz * dz))
                    val strength = 0.8 * resistanceFactor
                    target.deltaMovement = target.deltaMovement.add(
                        (dx / dist) * strength,
                        0.1,
                        (dz / dist) * strength
                    )

                    karkinos.doEnchantDamageEffects(karkinos, target)
                    if (target is Player) {
                        val itemInUse = target.useItem
                        if (itemInUse.`is`(Items.SHIELD)) {
                            target.disableShield(true)
                            target.level().broadcastEntityEvent(target, 30.toByte())
                        }
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
            karkinos.triggerHeavyAttackAnimation()
            attackDelayTicks = 15
            pendingTarget = enemy
            ticksUntilNextAttack = attackDelayTicks
        }
    }

    override fun resetAttackCooldown() {
        this.ticksUntilNextAttack = this.adjustedTickDelay(30)
    }

    override fun getAttackReachSqr(attackTarget: LivingEntity): Double {
        return (karkinos.bbWidth * 2.0f * karkinos.bbWidth * 2.0f + attackTarget.bbWidth).toDouble()
    }
}