package dev.hybridlabs.aquatic.entity.ai.goal

import dev.hybridlabs.aquatic.entity.mammal.HybridAquaticDolphinEntity
import net.minecraft.core.BlockPos
import net.minecraft.sounds.SoundEvents
import net.minecraft.tags.FluidTags
import net.minecraft.util.Mth
import net.minecraft.world.entity.ai.goal.JumpGoal
import kotlin.math.abs
import kotlin.math.atan2

@Suppress("DEPRECATION")
class HADolphinJumpGoal(private val dolphin: HybridAquaticDolphinEntity, chance: Int) : JumpGoal() {
    private val chance: Int = reducedTickDelay(chance)
    private var inWater = false

    override fun canUse(): Boolean {
        return if (dolphin.random.nextInt(chance) != 0) {
            false
        } else {
            val direction = dolphin.motionDirection
            val i = direction.stepX
            val j = direction.stepZ
            val blockPos = dolphin.blockPosition()
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
        return dolphin.level().getFluidState(blockPos).`is`(FluidTags.WATER) && !dolphin.level().getBlockState(blockPos)
            .blocksMotion()
    }

    private fun isAirAbove(pos: BlockPos, offsetX: Int, offsetZ: Int, multiplier: Int): Boolean {
        return dolphin.level().getBlockState(
            pos.offset(
                offsetX * multiplier,
                1,
                offsetZ * multiplier
            )
        ).isAir && dolphin.level().getBlockState(pos.offset(offsetX * multiplier, 2, offsetZ * multiplier)).isAir
    }

    override fun canContinueToUse(): Boolean {
        val d = dolphin.deltaMovement.y
        return (!(d * d < 0.029999999329447746) || dolphin.xRot == 0.0f || !(abs(dolphin.xRot) < 10.0f) || !dolphin.isInWater) && !dolphin.onGround()
    }

    override fun isInterruptable(): Boolean {
        return false
    }

    override fun start() {
        val direction = dolphin.motionDirection
        dolphin.deltaMovement =
            dolphin.deltaMovement.add(direction.stepX.toDouble() * 0.6, 0.7, direction.stepZ.toDouble() * 0.6)
        dolphin.navigation.stop()
    }

    override fun stop() {
        dolphin.xRot = 0.0f
    }

    override fun tick() {
        val bl = inWater
        if (!bl) {
            val fluidState = dolphin.level().getFluidState(dolphin.blockPosition())
            inWater = fluidState.`is`(FluidTags.WATER)
        }
        if (inWater && !bl) {
            dolphin.playSound(SoundEvents.DOLPHIN_JUMP, 1.0f, 1.0f)
        }
        val vec3d = dolphin.deltaMovement
        if (vec3d.y * vec3d.y < 0.029999999329447746 && dolphin.xRot != 0.0f) {
            dolphin.xRot = Mth.rotLerp(0.2f, dolphin.xRot, 0.0f)
        } else if (vec3d.length() > 9.999999747378752E-6) {
            val d = vec3d.length()
            val e = atan2(-vec3d.y, d) * 57.2957763671875
            dolphin.xRot = e.toFloat()
        }
    }

    companion object {
        private val OFFSET_MULTIPLIERS = intArrayOf(0, 1, 4, 5, 6, 7)
    }
}