package dev.hybridlabs.aquatic.entity.ai.goal

import dev.hybridlabs.aquatic.effect.HAMobEffects
import dev.hybridlabs.aquatic.item.HAItems
import dev.hybridlabs.hapi.entity.water.base.BaseSharkEntity
import dev.hybridlabs.hapi.entity.water.base.BaseWaterAnimal
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
    protected val shark: BaseSharkEntity,
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

        val i: Long = shark.level().gameTime
        if (i - this.lastCanUseCheck < 20L) {
            return false
        } else {
            this.lastCanUseCheck = i
            val livingentity: LivingEntity? = shark.target
            if (livingentity == null) {
                return false
            } else if (!livingentity.isAlive) {
                return false
            } else {
                this.path = shark.getNavigation().createPath(livingentity, 0)
                return if (this.path != null) true else shark.isWithinMeleeAttackRange(livingentity)
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
                val d0: Double = shark.distanceToSqr(livingEntity)
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

            this.ticksUntilNextAttack = max((this.ticksUntilNextAttack - 1).toDouble(), 0.0).toInt()
            this.checkAndPerformAttack(livingEntity)
        }
    }

    protected open fun checkAndPerformAttack(enemy: LivingEntity) {
        if (this.canPerformAttack(enemy)) {
            this.resetAttackCooldown()
            shark.swing(InteractionHand.MAIN_HAND)
            shark.doHurtTarget(enemy)
            if (!enemy.isBlocking) {
                enemy.addEffect(MobEffectInstance(HAMobEffects.BLEEDING.asHolder(), 200, 0), shark)
            }

            if (enemy.health <= 0) shark.hunger = BaseWaterAnimal.MAX_HUNGER

            val hasShield = enemy.mainHandItem.`is`(Items.SHIELD) || enemy.offhandItem.`is`(Items.SHIELD)
            if (hasShield && enemy.isBlocking) {
                shark.spawnAtLocation(HAItems.SHARK_TOOTH.get())
            }
        }
    }

    private fun resetAttackCooldown() {
        this.ticksUntilNextAttack = this.adjustedTickDelay(20)
    }

    protected fun isTimeToAttack(): Boolean {
        return this.ticksUntilNextAttack <= 0
    }

    protected fun canPerformAttack(entity: LivingEntity): Boolean {
        return this.isTimeToAttack() && shark.isWithinMeleeAttackRange(entity) && shark.sensing
            .hasLineOfSight(entity)
    }
}
