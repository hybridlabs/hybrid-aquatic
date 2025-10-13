package dev.hybridlabs.aquatic.entity.ai.goal

import dev.hybridlabs.aquatic.effect.HybridAquaticMobEffects
import dev.hybridlabs.aquatic.entity.shark.HybridAquaticSharkEntity
import dev.hybridlabs.aquatic.item.HybridAquaticItems
import net.minecraft.world.InteractionHand
import net.minecraft.world.effect.MobEffectInstance
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.Items
import java.util.*
import kotlin.math.max

open class SharkAttackGoal(
    protected val shark: HybridAquaticSharkEntity,
    private val speedMultiplier: Double = 1.0,
    private val followingTargetEvenIfNotSeen: Boolean,
) :
    MeleeAttackGoal(shark, speedMultiplier, true) {
    private var ticksUntilNextPathRecalculation = 0
    private var ticksUntilNextAttack: Int = 0
    private var attackDelayTicks = 0
    private var pendingTarget: LivingEntity? = null

    init {
        this.flags = EnumSet.of(Flag.MOVE, Flag.LOOK)
    }

    override fun canUse(): Boolean {
        if (shark.fromFishingNet) {
            return false
        }
        return super.canUse()
    }

    override fun start() {
        shark.isSprinting = true
        return super.start()
    }

    override fun stop() {
        shark.isSprinting = false
        return super.stop()
    }

    override fun requiresUpdateEveryTick(): Boolean {
        return true
    }

    override fun tick() {
        if (attackDelayTicks > 0) {
            attackDelayTicks--
            if (attackDelayTicks == 0 && pendingTarget != null && !shark.level().isClientSide) {
                shark.doHurtTarget(pendingTarget!!)

                val target = pendingTarget
                if (target is Player) {
                    val itemInUse = target.useItem
                    if (itemInUse.`is`(Items.SHIELD)) {
                        target.disableShield()
                        target.level().broadcastEntityEvent(target, 30.toByte())
                    }
                }
                resetAttackCooldown()
                pendingTarget = null
            }
        }

        val livingEntity = shark.target

        if (!shark.navigation.isInProgress) {
            if (livingEntity != null) {
                shark.navigation.moveTo(livingEntity, speedMultiplier)
            }
        }

        if (livingEntity != null) {
            if (shark.distanceToSqr(livingEntity) < 16.0) {
                shark.lookControl.setLookAt(livingEntity, 10.0f, 10.0f)
            }
            this.ticksUntilNextPathRecalculation =
                max((this.ticksUntilNextPathRecalculation - 1).toDouble(), 0.0).toInt()
            if ((followingTargetEvenIfNotSeen || shark.sensing.hasLineOfSight(livingEntity)) && ticksUntilNextPathRecalculation <= 0) {
                shark.navigation.moveTo(livingEntity, speedMultiplier)
                ticksUntilNextPathRecalculation = 20
            }

            this.ticksUntilNextAttack =
                max((this.ticksUntilNextAttack - 1).toDouble(), 0.0).toInt()
            this.checkAndPerformAttack(livingEntity)
        }
    }

    override fun checkAndPerformAttack(enemy: LivingEntity) {
        if (canPerformAttack(enemy) && ticksUntilNextAttack <= 0 && attackDelayTicks == 0) {
            shark.swing(InteractionHand.MAIN_HAND)
            shark.doHurtTarget(enemy)
            enemy.addEffect(MobEffectInstance(HybridAquaticMobEffects.BLEEDING.asHolder(), 200, 0), shark)

            if (enemy.health <= 0) {
                shark.hunger = HybridAquaticSharkEntity.MAX_HUNGER
            }
            shark.health = shark.maxHealth

            val hasShield = enemy.mainHandItem.`is`(Items.SHIELD) || enemy.offhandItem.`is`(Items.SHIELD)
            if (hasShield && enemy.isBlocking) {
                shark.spawnAtLocation(HybridAquaticItems.SHARK_TOOTH.get())
            }

            resetAttackCooldown()
        }
    }

    override fun resetAttackCooldown() {
        this.ticksUntilNextAttack = this.adjustedTickDelay(20)
    }

    override fun canPerformAttack(attackTarget: LivingEntity): Boolean {
        return shark.distanceToSqr(attackTarget) <= (shark.bbWidth * 1.75f * shark.bbWidth * 1.75f + attackTarget.bbWidth).toDouble()
    }
}