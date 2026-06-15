package dev.hybridlabs.aquatic.entity.ai.control

import net.minecraft.util.Mth
import net.minecraft.world.entity.Mob
import net.minecraft.world.entity.ai.attributes.Attributes
import net.minecraft.world.entity.ai.control.MoveControl
import net.minecraft.world.level.pathfinder.PathType
import kotlin.math.abs
import kotlin.math.sqrt

open class SmoothStrafeSwimmingMoveControl(
    mob: Mob,
    private val maxTurnX: Int,
    private val maxTurnY: Int,
    private val inWaterSpeedModifier: Float,
    private val outsideWaterSpeedModifier: Float,
    private val applyGravity: Boolean,
) : MoveControl(mob) {

    override fun tick() {
        if (applyGravity && mob.isInWater) {
            mob.deltaMovement = mob.deltaMovement.add(0.0, 0.005, 0.0)
        }

        if (operation == Operation.STRAFE) {
            val baseSpeed = mob.getAttributeValue(Attributes.MOVEMENT_SPEED).toFloat()
            val speed = speedModifier.toFloat() * baseSpeed

            var forward = strafeForwards
            var right = strafeRight

            var magnitude = Mth.sqrt(forward * forward + right * right)
            if (magnitude < 1.0f) magnitude = 1.0f

            magnitude = speed / magnitude
            forward *= magnitude
            right *= magnitude

            val sin = Mth.sin(mob.yRot * (Math.PI.toFloat() / 180f))
            val cos = Mth.cos(mob.yRot * (Math.PI.toFloat() / 180f))

            val relX = forward * cos - right * sin
            val relZ = right * cos + forward * sin

            if (!isPassable(relX, relZ)) {
                strafeForwards = 1.0f
                strafeRight = 0.0f
            }

            mob.speed = speed
            mob.zza = strafeForwards
            mob.xxa = strafeRight

            operation = Operation.WAIT
        }

        else if (operation == Operation.MOVE_TO && !mob.navigation.isDone) {

            val dx = wantedX - mob.x
            val dy = wantedY - mob.y
            val dz = wantedZ - mob.z

            val distSq = dx * dx + dy * dy + dz * dz
            if (distSq < 2.5000003E-7) {
                mob.zza = 0f
                return
            }

            val targetYaw =
                (Mth.atan2(dz, dx) * (180f / Math.PI.toFloat())).toFloat() - 90f

            mob.yRot = rotlerp(mob.yRot, targetYaw, maxTurnY.toFloat())
            mob.yBodyRot = mob.yRot
            mob.yHeadRot = mob.yRot

            val baseSpeed =
                (speedModifier * mob.getAttributeValue(Attributes.MOVEMENT_SPEED)).toFloat()

            if (mob.isInWater) {
                mob.speed = baseSpeed * inWaterSpeedModifier

                val horizontalDist = sqrt(dx * dx + dz * dz)

                if (abs(dy) > 1.0E-5 || abs(horizontalDist) > 1.0E-5) {
                    var pitch =
                        -((Mth.atan2(dy, horizontalDist) * (180f / Math.PI.toFloat())).toFloat())

                    pitch = Mth.clamp(
                        Mth.wrapDegrees(pitch),
                        -maxTurnX.toFloat(),
                        maxTurnX.toFloat()
                    )

                    mob.xRot = rotlerp(mob.xRot, pitch, 5f)
                }

                val cosPitch = Mth.cos(mob.xRot * (Math.PI.toFloat() / 180f))
                val sinPitch = Mth.sin(mob.xRot * (Math.PI.toFloat() / 180f))

                mob.zza = cosPitch * baseSpeed
                mob.yya = -sinPitch * baseSpeed
            }
            else {
                val yawDiff = abs(Mth.wrapDegrees(mob.yRot - targetYaw))
                val factor = getTurningSpeedFactor(yawDiff)

                mob.speed = baseSpeed * outsideWaterSpeedModifier * factor
            }
        }

        else {
            mob.speed = 0f
            mob.xxa = 0f
            mob.yya = 0f
            mob.zza = 0f
        }
    }

    private fun isPassable(relativeX: Float, relativeZ: Float): Boolean {
        val pathnavigation = this.mob.getNavigation()
        if (pathnavigation != null) {
            val nodeevaluator = pathnavigation.getNodeEvaluator()
            if (nodeevaluator != null && nodeevaluator.getPathTypeOfMob(
                    TODO("NEEDS FIXING"),
                    Mth.floor(this.mob.x + relativeX.toDouble()),
                    this.mob.blockY,
                    Mth.floor(this.mob.z + relativeZ.toDouble()),
                    this.mob
                ) == PathType.BLOCKED
            ) {
                return false
            }
        }

        return true
    }

    override fun strafe(forward: Float, strafe: Float) {
        operation = Operation.STRAFE
        strafeForwards = forward
        strafeRight = strafe
        speedModifier = 0.25
    }

    companion object {

        private fun getTurningSpeedFactor(angle: Float): Float {
            return 1.0f - Mth.clamp((angle - 10.0f) / 50.0f, 0.0f, 1.0f)
        }
    }
}