package dev.hybridlabs.aquatic.entity.ai.goal

import dev.hybridlabs.aquatic.entity.mammal.HybridAquaticDolphinEntity
import net.minecraft.entity.ai.goal.DiveJumpingGoal
import net.minecraft.registry.tag.FluidTags
import net.minecraft.sound.SoundEvents
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Mth
import kotlin.math.abs
import kotlin.math.atan2

@Suppress("DEPRECATION")
class HADolphinJumpGoal(private val dolphin: HybridAquaticDolphinEntity, chance: Int) : DiveJumpingGoal() {
    private val chance: Int = toGoalTicks(chance)
    private var inWater = false

    override fun canUse(): Boolean {
        return if (dolphin.random.nextInt(chance) != 0) {
            false
        } else {
            val direction = dolphin.movementDirection
            val i = direction.offsetX
            val j = direction.offsetZ
            val blockPos = dolphin.blockPos
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
        val blockPos = pos.add(offsetX * multiplier, 0, offsetZ * multiplier)
        return dolphin.world.getFluidState(blockPos).`is`(FluidTags.WATER) && !dolphin.world.getBlockState(blockPos)
            .blocksMovement()
    }

    private fun isAirAbove(pos: BlockPos, offsetX: Int, offsetZ: Int, multiplier: Int): Boolean {
        return dolphin.world.getBlockState(
            pos.add(
                offsetX * multiplier,
                1,
                offsetZ * multiplier
            )
        ).isAir && dolphin.world.getBlockState(pos.add(offsetX * multiplier, 2, offsetZ * multiplier)).isAir
    }

    override fun shouldContinue(): Boolean {
        val d = dolphin.velocity.y
        return (!(d * d < 0.029999999329447746) || dolphin.pitch == 0.0f || !(abs(dolphin.pitch) < 10.0f) || !dolphin.isTouchingWater) && !dolphin.isOnGround
    }

    override fun canStop(): Boolean {
        return false
    }

    override fun start() {
        val direction = dolphin.movementDirection
        dolphin.velocity =
            dolphin.velocity.add(direction.offsetX.toDouble() * 0.6, 1.0, direction.offsetZ.toDouble() * 0.6)
        dolphin.navigation.stop()
    }

    override fun stop() {
        dolphin.pitch = 0.0f
    }

    override fun tick() {
        val bl = inWater
        if (!bl) {
            val fluidState = dolphin.world.getFluidState(dolphin.blockPos)
            inWater = fluidState.`is`(FluidTags.WATER)
        }
        if (inWater && !bl) {
            dolphin.playSound(SoundEvents._DOLPHIN_JUMP, 1.0f, 1.0f)
        }
        val vec3d = dolphin.velocity
        if (vec3d.y * vec3d.y < 0.029999999329447746 && dolphin.pitch != 0.0f) {
            dolphin.pitch = Mth.lerpAngleDegrees(0.2f, dolphin.pitch, 0.0f)
        } else if (vec3d.length() > 9.999999747378752E-6) {
            val d = vec3d.horizontalLength()
            val e = atan2(-vec3d.y, d) * 57.2957763671875
            dolphin.pitch = e.toFloat()
        }
    }

    companion object {
        private val OFFSET_MULTIPLIERS = intArrayOf(0, 1, 4, 5, 6, 7)
    }
}

