package dev.hybridlabs.aquatic.entity.ai.goal

import dev.hybridlabs.aquatic.effect.HAMobEffects
import dev.hybridlabs.aquatic.entity.base.HAWaterAnimal
import dev.hybridlabs.aquatic.entity.shark.HASharkEntity
import dev.hybridlabs.aquatic.item.HAItems
import net.minecraft.world.InteractionHand
import net.minecraft.world.effect.MobEffectInstance
import net.minecraft.world.entity.EntitySelector
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.ai.attributes.Attributes
import net.minecraft.world.entity.ai.goal.Goal
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.Items
import net.minecraft.world.level.pathfinder.Path
import java.util.*
import kotlin.math.max

open class SharkAttackGoal(
    protected val shark: HASharkEntity,
    private val speedMultiplier: Double = 1.0,
    private val followingTargetEvenIfNotSeen: Boolean,
) :
    Goal() {
    private var path: Path? = null
    private var pathedTargetX = 0.0
    private var pathedTargetY = 0.0
    private var pathedTargetZ = 0.0
    private var ticksUntilNextPathRecalculation = 0
    private var ticksUntilNextAttack: Int = 0
    private var lastCanUseCheck: Long = 0
    private val speedModifier: Double
        get() = shark.getAttributeValue(Attributes.MOVEMENT_SPEED) * speedMultiplier

    init {
        this.flags = EnumSet.of(Flag.MOVE, Flag.LOOK)
    }

    override fun canUse(): Boolean {
        if (shark.fromFishingNet) {
            return false
        }

        val i = shark.level().gameTime
        if (i - this.lastCanUseCheck < 20L) {
            return false
        } else {
            this.lastCanUseCheck = i
            val livingEntity = shark.target
            if (livingEntity == null) {
                return false
            } else if (!livingEntity.isAlive) {
                return false
            } else {
                this.path = shark.navigation.createPath(livingEntity, 3)
                return if (this.path != null) {
                    true
                } else {
                    getAttackReachSqr(livingEntity) >= shark.distanceToSqr(
                        livingEntity.x,
                        livingEntity.y,
                        livingEntity.z
                    )
                }
            }
        }
    }

    override fun canContinueToUse(): Boolean {
        val livingEntity = shark.target
        return if (livingEntity == null) {
            false
        } else if (!livingEntity.isAlive) {
            false
        } else if (!this.followingTargetEvenIfNotSeen) {
            !shark.navigation.isDone
        } else if (!shark.isWithinRestriction(livingEntity.blockPosition())) {
            false
        } else {
            livingEntity !is Player || !livingEntity.isSpectator && !livingEntity.isCreative
        }
    }

    override fun start() {
        shark.navigation.moveTo(this.path, this.speedModifier)
        shark.isAggressive = true
        shark.isSprinting = true
        shark.swinging = false
        shark.swingTime = 0
        this.ticksUntilNextPathRecalculation = 0
        this.ticksUntilNextAttack = 0
    }

    override fun stop() {
        val livingEntity = shark.target
        if (!EntitySelector.NO_CREATIVE_OR_SPECTATOR.test(livingEntity)) {
            shark.target = null
        }

        shark.isSprinting = false
        shark.isAggressive = false
        shark.navigation.stop()
    }

    override fun requiresUpdateEveryTick(): Boolean {
        return true
    }

    override fun tick() {
        val livingEntity = shark.target
        if (livingEntity != null) {
            shark.lookControl.setLookAt(livingEntity, 30.0f, 30.0f)
            val d0 = shark.getPerceivedTargetDistanceSquareForMeleeAttack(livingEntity)
            this.ticksUntilNextPathRecalculation =
                max((this.ticksUntilNextPathRecalculation - 1).toDouble(), 0.0).toInt()
            if ((this.followingTargetEvenIfNotSeen || shark.sensing.hasLineOfSight(livingEntity)) &&
                (this.ticksUntilNextPathRecalculation <= 0) &&
                (this.pathedTargetX == 0.0 &&
                        (this.pathedTargetY == 0.0) &&
                        (this.pathedTargetZ == 0.0) || (livingEntity.distanceToSqr(
                    this.pathedTargetX,
                    this.pathedTargetY,
                    this.pathedTargetZ
                ) >= 1.0) || (shark.random.nextFloat() < 0.05f))
            ) {
                this.pathedTargetX = livingEntity.x
                this.pathedTargetY = livingEntity.y
                this.pathedTargetZ = livingEntity.z
                this.ticksUntilNextPathRecalculation = 4 + shark.random.nextInt(7)
                if (d0 > 1024.0) {
                    this.ticksUntilNextPathRecalculation += 10
                } else if (d0 > 256.0) {
                    this.ticksUntilNextPathRecalculation += 5
                }

                if (!shark.navigation.moveTo(livingEntity, this.speedModifier)) {
                    this.ticksUntilNextPathRecalculation += 15
                }

                this.ticksUntilNextPathRecalculation = this.adjustedTickDelay(this.ticksUntilNextPathRecalculation)
            }

            this.ticksUntilNextAttack =
                max((this.ticksUntilNextAttack - 1).toDouble(), 0.0).toInt()
            this.checkAndPerformAttack(livingEntity, d0)
        }
    }

    protected open fun checkAndPerformAttack(enemy: LivingEntity, distToEnemySqr: Double) {
        val d0 = this.getAttackReachSqr(enemy)
        if (distToEnemySqr <= d0 && this.ticksUntilNextAttack <= 0) {
            this.resetAttackCooldown()
            shark.swing(InteractionHand.MAIN_HAND)
            shark.doHurtTarget(enemy)
            if (!enemy.isBlocking) { enemy.addEffect(MobEffectInstance(HAMobEffects.BLEEDING.get(), 200, 0), shark) }

            if (enemy.health <= 0) shark.hunger = HAWaterAnimal.MAX_HUNGER

            val hasShield = enemy.mainHandItem.`is`(Items.SHIELD) || enemy.offhandItem.`is`(Items.SHIELD)
            if (hasShield && enemy.isBlocking) {
                shark.spawnAtLocation(HAItems.SHARK_TOOTH.get())
            }
        }
    }

    private fun resetAttackCooldown() {
        this.ticksUntilNextAttack = this.adjustedTickDelay(20)
    }

    protected open fun getAttackReachSqr(attackTarget: LivingEntity): Double {
        return (shark.bbWidth * 1.75f * shark.bbWidth * 1.75f + attackTarget.bbWidth).toDouble()
    }
}
