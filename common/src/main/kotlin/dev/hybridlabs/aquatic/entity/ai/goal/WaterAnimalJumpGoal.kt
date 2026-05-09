package dev.hybridlabs.aquatic.entity.ai.goal

import dev.hybridlabs.aquatic.entity.base.HAWaterAnimal
import net.minecraft.core.BlockPos
import net.minecraft.sounds.SoundEvents
import net.minecraft.tags.FluidTags
import net.minecraft.util.Mth
import net.minecraft.world.entity.ai.goal.JumpGoal
import kotlin.math.abs
import kotlin.math.atan2

@Suppress("DEPRECATION")
class WaterAnimalJumpGoal(
    private val waterAnimal: HAWaterAnimal,
    chance: Int,
    private val jumpHeight: Double
) : JumpGoal() {

    private val chance: Int = reducedTickDelay(chance)
    private var inWater = false

    private fun jumpVelocity(): Double {
        return kotlin.math.sqrt(0.16 * jumpHeight)
    }

    override fun canUse(): Boolean {
        return if (waterAnimal.random.nextInt(chance) != 0) {
            false
        } else {
            val direction = waterAnimal.motionDirection
            val i = direction.stepX
            val j = direction.stepZ
            val blockPos = waterAnimal.blockPosition()
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
        return waterAnimal.level().getFluidState(blockPos).`is`(FluidTags.WATER) && !waterAnimal.level().getBlockState(blockPos)
            .blocksMotion()
    }

    private fun isAirAbove(pos: BlockPos, offsetX: Int, offsetZ: Int, multiplier: Int): Boolean {
        return waterAnimal.level().getBlockState(
            pos.offset(
                offsetX * multiplier,
                1,
                offsetZ * multiplier
            )
        ).isAir && waterAnimal.level().getBlockState(pos.offset(offsetX * multiplier, 2, offsetZ * multiplier)).isAir
    }

    override fun canContinueToUse(): Boolean {
        val d = waterAnimal.deltaMovement.y
        return (!(d * d < 0.029999999329447746) || waterAnimal.xRot == 0.0f || !(abs(waterAnimal.xRot) < 10.0f) || !waterAnimal.isInWater) && !waterAnimal.onGround()
    }

    override fun isInterruptable(): Boolean {
        return false
    }

    override fun start() {
        val direction = waterAnimal.motionDirection
        val jumpVelocity = jumpVelocity()

        waterAnimal.deltaMovement =
            waterAnimal.deltaMovement.add(
                direction.stepX * 0.6,
                jumpVelocity,
                direction.stepZ * 0.6
            )

        waterAnimal.navigation.stop()
    }

    override fun stop() {
        waterAnimal.xRot = 0.0f
    }

    override fun tick() {
        val bl = inWater
        if (!bl) {
            val fluidState = waterAnimal.level().getFluidState(waterAnimal.blockPosition())
            inWater = fluidState.`is`(FluidTags.WATER)
        }
        if (inWater && !bl) {
            waterAnimal.playSound(SoundEvents.DOLPHIN_JUMP, 1.0f, 1.0f)
        }
        val vec3d = waterAnimal.deltaMovement
        if (vec3d.y * vec3d.y < 0.029999999329447746 && waterAnimal.xRot != 0.0f) {
            waterAnimal.xRot = Mth.rotLerp(0.2f, waterAnimal.xRot, 0.0f)
        } else if (vec3d.length() > 9.999999747378752E-6) {
            val d = vec3d.length()
            val e = atan2(-vec3d.y, d) * 57.2957763671875
            waterAnimal.xRot = e.toFloat()
        }
    }

    companion object {
        private val OFFSET_MULTIPLIERS = intArrayOf(0, 1, 4, 5, 6, 7)
    }
}