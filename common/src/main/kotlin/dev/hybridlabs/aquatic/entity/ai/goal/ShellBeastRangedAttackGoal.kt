package dev.hybridlabs.aquatic.entity.ai.goal

import dev.hybridlabs.aquatic.entity.miniboss.ShellBeastEntity
import dev.hybridlabs.aquatic.entity.projectile.CavitationBubbleEntity
import dev.hybridlabs.aquatic.sound.HASoundEvents
import net.minecraft.world.entity.ai.goal.Goal
import java.util.*
import kotlin.math.abs

class ShellBeastRangedAttackGoal(private val shellBeast: ShellBeastEntity) : Goal() {
    var chargeTime: Int = 0
    private var seeTime = 0
    private var strafingClockwise = false
    private var strafingBackwards = false
    private var strafingTime = -1
    private val strafeAmount = 0.20f

    init {
        this.flags = EnumSet.of(Flag.MOVE, Flag.LOOK)
    }

    override fun canUse(): Boolean = shellBeast.target != null &&
            !shellBeast.isSummoning() &&
            shellBeast.tickCount > 120

    override fun start() {
        chargeTime = 0
    }

    override fun stop() {
        shellBeast.setCharging(false)
    }

    override fun requiresUpdateEveryTick(): Boolean = true

    override fun tick() {
        val target = shellBeast.target ?: return

        val distance = shellBeast.distanceToSqr(target.position())
        val canSee = shellBeast.sensing.hasLineOfSight(target)
        val seenBefore = seeTime > 0

        if (canSee != seenBefore) {
            seeTime = 0
        }

        if (canSee) seeTime++ else seeTime--

        val yDelta = abs(shellBeast.y - target.y)

        if (distance <= 4096.0 && yDelta <= 8.0 && chargeTime < 20 && seeTime >= 20) {
            shellBeast.navigation.stop()
            strafingTime++
        } else {
            if (distance >= 64)
                shellBeast.navigation.moveTo(target, 1.0)
            strafingTime = -1
        }

        if (strafingTime >= 20) {
            if (shellBeast.random.nextFloat() < 0.3f) {
                strafingClockwise = !strafingClockwise
            }

            if (distance <= 64) {
                strafingBackwards = true
            } else if (shellBeast.random.nextFloat() < 0.3f) {
                strafingBackwards = !strafingBackwards
            }
            strafingTime = 0
        }


        if (strafingTime > -1) {
            strafingBackwards = distance < 256 // 16**2
            shellBeast.moveControl.strafe(
                if (strafingBackwards) -strafeAmount else strafeAmount,
                if (strafingClockwise) strafeAmount else -strafeAmount
            )

        }

        shellBeast.lookAt(target, 5f, 5f)

        val health = shellBeast.health / shellBeast.maxHealth

        val bubbleCount = when {
            health >= 0.75f -> 1
            health >= 0.5f -> 2
            else -> 3
        }

        if (distance < 4096.0 && canSee) {
            val level = shellBeast.level()
            chargeTime++

            val canShootBubble =
                (chargeTime == 20) ||
                        (chargeTime == 40 && bubbleCount >= 2) ||
                        (chargeTime == 60 && bubbleCount >= 3)

            if (canShootBubble) {
                val view = shellBeast.getViewVector(1.0f)

                val dxFire = target.x - (shellBeast.x + view.x * 4.0)
                val dyFire = target.getY(0.5) - (0.5 + shellBeast.getY(0.5))
                val dzFire = target.z - (shellBeast.z + view.z * 4.0)

                shellBeast.triggerAnim("shoot_controller", "shoot")

                shellBeast.playSound(
                    HASoundEvents.SHELL_BEAST_SHOOT.get(),
                    1.0f,
                    1.0f
                )

                val cavitationBubble = CavitationBubbleEntity(
                    level,
                    shellBeast,
                    dxFire,
                    dyFire,
                    dzFire,
                    1
                )

                cavitationBubble.setPos(
                    shellBeast.x + view.x * 3.5,
                    shellBeast.getY(0.5),
                    shellBeast.z + view.z * 3.5
                )

                level.addFreshEntity(cavitationBubble)
            }

            if (chargeTime == 60) {
                chargeTime = -60
            }
        } else if (chargeTime > 0) {
            chargeTime--
        }

        shellBeast.setCharging(chargeTime > 10)
    }
}