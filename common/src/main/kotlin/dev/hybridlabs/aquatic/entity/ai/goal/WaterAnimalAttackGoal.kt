package dev.hybridlabs.aquatic.entity.ai.goal

import dev.hybridlabs.aquatic.entity.base.HAWaterAnimal
import net.minecraft.world.InteractionHand
import net.minecraft.world.entity.EntitySelector
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.ai.attributes.Attributes
import net.minecraft.world.entity.ai.goal.Goal
import net.minecraft.world.entity.player.Player
import net.minecraft.world.level.pathfinder.Path
import java.util.*
import kotlin.math.max

open class WaterAnimalAttackGoal(
    protected val waterAnimal: HAWaterAnimal,
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
        get() = waterAnimal.getAttributeValue(Attributes.MOVEMENT_SPEED) * speedMultiplier

    init {
        this.flags = EnumSet.of(Flag.MOVE, Flag.LOOK)
    }

    override fun canUse(): Boolean {
        if (waterAnimal.fromFishingNet) {
            return false
        }

        val i: Long = waterAnimal.level().gameTime
        if (i - this.lastCanUseCheck < 20L) {
            return false
        } else {
            this.lastCanUseCheck = i
            val livingentity: LivingEntity? = waterAnimal.target
            if (livingentity == null) {
                return false
            } else if (!livingentity.isAlive) {
                return false
            } else {
                this.path = waterAnimal.getNavigation().createPath(livingentity, 0)
                return if (this.path != null) true else waterAnimal.isWithinMeleeAttackRange(livingentity)
            }
        }
    }

    override fun canContinueToUse(): Boolean {
        val livingEntity = waterAnimal.target
        return if (livingEntity == null) {
            false
        } else if (!livingEntity.isAlive) {
            false
        } else if (!this.followingTargetEvenIfNotSeen) {
            !waterAnimal.navigation.isDone
        } else if (!waterAnimal.isWithinRestriction(livingEntity.blockPosition())) {
            false
        } else {
            livingEntity !is Player || !livingEntity.isSpectator && !livingEntity.isCreative
        }
    }

    override fun start() {
        waterAnimal.navigation.moveTo(this.path, this.speedModifier)
        waterAnimal.isAggressive = true
        waterAnimal.isSprinting = true
        waterAnimal.swinging = false
        waterAnimal.swingTime = 0
        this.ticksUntilNextPathRecalculation = 0
        this.ticksUntilNextAttack = 0
    }

    override fun stop() {
        val livingEntity = waterAnimal.target
        if (!EntitySelector.NO_CREATIVE_OR_SPECTATOR.test(livingEntity)) {
            waterAnimal.target = null
        }

        waterAnimal.isSprinting = false
        waterAnimal.isAggressive = false
        waterAnimal.navigation.stop()
    }

    override fun requiresUpdateEveryTick(): Boolean {
        return true
    }

    override fun tick() {
        val livingEntity = waterAnimal.target
        if (livingEntity != null) {
            waterAnimal.lookControl.setLookAt(livingEntity, 30.0f, 30.0f)
            this.ticksUntilNextPathRecalculation =
                max((this.ticksUntilNextPathRecalculation - 1).toDouble(), 0.0).toInt()
            if ((this.followingTargetEvenIfNotSeen || waterAnimal.sensing.hasLineOfSight(livingEntity)) &&
                (this.ticksUntilNextPathRecalculation <= 0) &&
                (this.pathedTargetX == 0.0 &&
                        (this.pathedTargetY == 0.0) &&
                        (this.pathedTargetZ == 0.0) || (livingEntity.distanceToSqr(
                    this.pathedTargetX,
                    this.pathedTargetY,
                    this.pathedTargetZ
                ) >= 1.0) || (waterAnimal.random.nextFloat() < 0.05f))
            ) {
                this.pathedTargetX = livingEntity.x
                this.pathedTargetY = livingEntity.y
                this.pathedTargetZ = livingEntity.z
                this.ticksUntilNextPathRecalculation = 4 + waterAnimal.random.nextInt(7)
                val d0: Double = waterAnimal.distanceToSqr(livingEntity)
                if (d0 > 1024.0) {
                    this.ticksUntilNextPathRecalculation += 10
                } else if (d0 > 256.0) {
                    this.ticksUntilNextPathRecalculation += 5
                }

                if (!waterAnimal.navigation.moveTo(livingEntity, this.speedModifier)) {
                    this.ticksUntilNextPathRecalculation += 15
                }

                this.ticksUntilNextPathRecalculation = this.adjustedTickDelay(this.ticksUntilNextPathRecalculation)
            }

            this.ticksUntilNextAttack = max((this.ticksUntilNextAttack - 1).toDouble(), 0.0).toInt()
            this.checkAndPerformAttack(livingEntity)
        }
    }

    protected open fun checkAndPerformAttack(target: LivingEntity) {
        if (this.canPerformAttack(target)) {
            this.resetAttackCooldown()
            waterAnimal.swing(InteractionHand.MAIN_HAND)
            waterAnimal.doHurtTarget(target)
        }
    }

    private fun resetAttackCooldown() {
        this.ticksUntilNextAttack = this.adjustedTickDelay(20)
    }

    protected fun isTimeToAttack(): Boolean {
        return this.ticksUntilNextAttack <= 0
    }

    protected fun canPerformAttack(entity: LivingEntity): Boolean {
        return this.isTimeToAttack() && waterAnimal.isWithinMeleeAttackRange(entity) && waterAnimal.sensing
            .hasLineOfSight(entity)
    }
}