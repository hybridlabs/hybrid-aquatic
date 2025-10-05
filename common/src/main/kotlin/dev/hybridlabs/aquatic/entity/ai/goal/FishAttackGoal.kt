package dev.hybridlabs.aquatic.entity.ai.goal

import dev.hybridlabs.aquatic.entity.fish.HybridAquaticFishEntity
import net.minecraft.world.InteractionHand
import net.minecraft.world.entity.EntitySelector
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.ai.goal.Goal
import net.minecraft.world.entity.player.Player
import net.minecraft.world.level.pathfinder.Path
import java.util.*
import kotlin.math.max

open class FishAttackGoal(
    protected val fish: HybridAquaticFishEntity,
    private val speedModifier: Double,
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

    init {
        this.flags = EnumSet.of(Flag.MOVE, Flag.LOOK)
    }

    override fun canUse(): Boolean {
        if (fish.fromFishingNet) {
            return false
        }

        val i = fish.level().gameTime
        if (i - this.lastCanUseCheck < 20L) {
            return false
        } else {
            this.lastCanUseCheck = i
            val livingEntity = fish.target
            if (livingEntity == null) {
                return false
            } else if (!livingEntity.isAlive) {
                return false
            } else {
                this.path = fish.navigation.createPath(livingEntity, 3)
                return if (this.path != null) {
                    true
                } else {
                    getAttackReachSqr(livingEntity) >= fish.distanceToSqr(
                        livingEntity.x,
                        livingEntity.y,
                        livingEntity.z
                    )
                }
            }
        }
    }

    override fun canContinueToUse(): Boolean {
        val livingEntity = fish.target
        return if (livingEntity == null) {
            false
        } else if (!livingEntity.isAlive) {
            false
        } else if (!this.followingTargetEvenIfNotSeen) {
            !fish.navigation.isDone
        } else if (!fish.isWithinRestriction(livingEntity.blockPosition())) {
            false
        } else {
            livingEntity !is Player || !livingEntity.isSpectator() && !livingEntity.isCreative
        }
    }

    override fun start() {
        fish.navigation.moveTo(this.path, this.speedModifier)
        fish.isAggressive = true
        fish.isSprinting = true
        fish.swinging = false
        fish.swingTime = 0
        this.ticksUntilNextPathRecalculation = 0
        this.ticksUntilNextAttack = 0
    }

    override fun stop() {
        val livingEntity = fish.target
        if (!EntitySelector.NO_CREATIVE_OR_SPECTATOR.test(livingEntity)) {
            fish.target = null
        }

        fish.isSprinting = false
        fish.isAggressive = false
        fish.navigation.stop()
    }

    override fun requiresUpdateEveryTick(): Boolean {
        return true
    }

    override fun tick() {
        val livingEntity = fish.target
        if (livingEntity != null) {
            fish.lookControl.setLookAt(livingEntity, 30.0f, 30.0f)
            val d0 = fish.getPerceivedTargetDistanceSquareForMeleeAttack(livingEntity)
            this.ticksUntilNextPathRecalculation =
                max((this.ticksUntilNextPathRecalculation - 1).toDouble(), 0.0).toInt()
            if ((this.followingTargetEvenIfNotSeen || fish.sensing.hasLineOfSight(livingEntity)) &&
                (this.ticksUntilNextPathRecalculation <= 0) &&
                (this.pathedTargetX == 0.0 &&
                        (this.pathedTargetY == 0.0) &&
                        (this.pathedTargetZ == 0.0) || (livingEntity.distanceToSqr(
                    this.pathedTargetX,
                    this.pathedTargetY,
                    this.pathedTargetZ
                ) >= 1.0) || (fish.random.nextFloat() < 0.05f))
            ) {
                this.pathedTargetX = livingEntity.x
                this.pathedTargetY = livingEntity.y
                this.pathedTargetZ = livingEntity.z
                this.ticksUntilNextPathRecalculation = 4 + fish.random.nextInt(7)
                if (d0 > 1024.0) {
                    this.ticksUntilNextPathRecalculation += 10
                } else if (d0 > 256.0) {
                    this.ticksUntilNextPathRecalculation += 5
                }

                if (!fish.navigation.moveTo(livingEntity, this.speedModifier)) {
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
            fish.swing(InteractionHand.MAIN_HAND)
            fish.doHurtTarget(enemy)

            if (enemy.health <= 0) fish.hunger = HybridAquaticFishEntity.MAX_HUNGER
            fish.health = fish.maxHealth
        }
    }

    private fun resetAttackCooldown() {
        this.ticksUntilNextAttack = this.adjustedTickDelay(20)
    }

    protected open fun getAttackReachSqr(attackTarget: LivingEntity): Double {
        return (fish.bbWidth * 2.0f * fish.bbWidth * 2.0f + attackTarget.bbWidth).toDouble()
    }
}