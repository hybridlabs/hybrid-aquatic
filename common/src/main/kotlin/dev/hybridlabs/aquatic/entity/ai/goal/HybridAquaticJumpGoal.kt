package dev.hybridlabs.aquatic.entity.ai.goal

import net.minecraft.core.BlockPos
import net.minecraft.sounds.SoundEvents
import net.minecraft.tags.FluidTags
import net.minecraft.util.Mth
import net.minecraft.world.entity.PathfinderMob
import net.minecraft.world.entity.ai.goal.JumpGoal
import kotlin.math.abs
import kotlin.math.atan2

@Suppress("DEPRECATION")
class HybridAquaticJumpGoal(
    private val mob: PathfinderMob,
    chance: Int,
    private val jumpHeight: Double
) : JumpGoal() {

    private val chance: Int = reducedTickDelay(chance)
    private var inWater = false

    private fun jumpVelocity(): Double {
        return kotlin.math.sqrt(0.16 * jumpHeight)
    }

    override fun canUse(): Boolean {
        return if (mob.random.nextInt(chance) != 0) {
            false
        } else {
            val direction = mob.motionDirection
            val i = direction.stepX
            val j = direction.stepZ
            val blockPos = mob.blockPosition()
            val var5 = OFFSET_MULTIPLIERS
            val var6 = var5.size
            for (var7 in 0 until var6) {
                val k = var5[var7]
                if (!isWaterAt(blockPos, i, j, k) || !isAirAbove(blockPos, i, j, k)) {
                    return false
                }
            }
            true
        }
    }

    private fun isWaterAt(pos: BlockPos, offsetX: Int, offsetZ: Int, multiplier: Int): Boolean {
        val blockPos = pos.offset(offsetX * multiplier, 0, offsetZ * multiplier)
        return mob.level().getFluidState(blockPos).`is`(FluidTags.WATER) && !mob.level().getBlockState(blockPos)
            .blocksMotion()
    }

    private fun isAirAbove(pos: BlockPos, offsetX: Int, offsetZ: Int, multiplier: Int): Boolean {
        return mob.level().getBlockState(
            pos.offset(
                offsetX * multiplier,
                1,
                offsetZ * multiplier
            )
        ).isAir && mob.level().getBlockState(pos.offset(offsetX * multiplier, 2, offsetZ * multiplier)).isAir
    }

    override fun canContinueToUse(): Boolean {
        val d = mob.deltaMovement.y
        return (!(d * d < 0.029999999329447746) || mob.xRot == 0.0f || !(abs(mob.xRot) < 10.0f) || !mob.isInWater) && !mob.onGround()
    }

    override fun isInterruptable(): Boolean {
        return false
    }

    override fun start() {
        val direction = mob.motionDirection
        val jumpVelocity = jumpVelocity()

        mob.deltaMovement =
            mob.deltaMovement.add(
                direction.stepX * 0.6,
                jumpVelocity,
                direction.stepZ * 0.6
            )

        mob.navigation.stop()
    }

    override fun stop() {
        mob.xRot = 0.0f
    }

    override fun tick() {
        val bl = inWater
        if (!bl) {
            val fluidState = mob.level().getFluidState(mob.blockPosition())
            inWater = fluidState.`is`(FluidTags.WATER)
        }
        if (inWater && !bl) {
            mob.playSound(SoundEvents.DOLPHIN_JUMP, 1.0f, 1.0f)
        }
        val vec3d = mob.deltaMovement
        if (vec3d.y * vec3d.y < 0.029999999329447746 && mob.xRot != 0.0f) {
            mob.xRot = Mth.rotLerp(0.2f, mob.xRot, 0.0f)
        } else if (vec3d.length() > 9.999999747378752E-6) {
            val d = vec3d.length()
            val e = atan2(-vec3d.y, d) * 57.2957763671875
            mob.xRot = e.toFloat()
        }
    }

    companion object {
        private val OFFSET_MULTIPLIERS = intArrayOf(0, 1, 4, 5, 6, 7)
    }
}