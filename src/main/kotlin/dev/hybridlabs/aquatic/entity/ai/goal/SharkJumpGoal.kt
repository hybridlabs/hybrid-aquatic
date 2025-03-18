package dev.hybridlabs.aquatic.entity.ai.goal

import dev.hybridlabs.aquatic.entity.shark.HybridAquaticSharkEntity
import net.minecraft.entity.ai.goal.DiveJumpingGoal
import net.minecraft.registry.tag.FluidTags
import net.minecraft.sound.SoundEvents
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.MathHelper
import kotlin.math.abs
import kotlin.math.atan2

@Suppress("DEPRECATION")
class SharkJumpGoal(private val shark: HybridAquaticSharkEntity, chance: Int) : DiveJumpingGoal() {
    private val chance: Int = toGoalTicks(chance)
    private var inWater = false

    override fun canStart(): Boolean {
        return if (shark.random.nextInt(chance) != 0) {
            false
        } else {
            val direction = shark.movementDirection
            val i = direction.offsetX
            val j = direction.offsetZ
            val blockPos = shark.blockPos
            val var5 = OFFSET_MULTIPLIERS
            val var6 = var5.size
            for (var7 in 0 until var6) {
                val k = var5[var7]
                if (!isWater(blockPos, i, j, k) || !isAirAbove(blockPos, i, j, k)) {
                    return false
                }
            }
            true
        }
    }

    private fun isWater(pos: BlockPos, offsetX: Int, offsetZ: Int, multiplier: Int): Boolean {
        val blockPos = pos.add(offsetX * multiplier, 0, offsetZ * multiplier)
        return shark.world.getFluidState(blockPos).isIn(FluidTags.WATER) && !shark.world.getBlockState(blockPos)
            .blocksMovement()
    }

    private fun isAirAbove(pos: BlockPos, offsetX: Int, offsetZ: Int, multiplier: Int): Boolean {
        return shark.world.getBlockState(
            pos.add(
                offsetX * multiplier,
                1,
                offsetZ * multiplier
            )
        ).isAir && shark.world.getBlockState(pos.add(offsetX * multiplier, 2, offsetZ * multiplier)).isAir
    }

    override fun shouldContinue(): Boolean {
        val d = shark.velocity.y
        return (!(d * d < 0.029999999329447746) || shark.pitch == 0.0f || !(abs(shark.pitch) < 10.0f) || !shark.isTouchingWater) && !shark.isOnGround
    }

    override fun canStop(): Boolean {
        return false
    }

    override fun start() {
        val direction = shark.movementDirection
        shark.velocity =
            shark.velocity.add(direction.offsetX.toDouble() * 0.6, 1.0, direction.offsetZ.toDouble() * 0.6)
        shark.navigation.stop()
    }

    override fun stop() {
        shark.pitch = 0.0f
    }

    override fun tick() {
        val bl = inWater
        if (!bl) {
            val fluidState = shark.world.getFluidState(shark.blockPos)
            inWater = fluidState.isIn(FluidTags.WATER)
        }
        if (inWater && !bl) {
            shark.playSound(SoundEvents.ENTITY_DOLPHIN_JUMP, 1.0f, 1.0f)
        }
        val vec3d = shark.velocity
        if (vec3d.y * vec3d.y < 0.029999999329447746 && shark.pitch != 0.0f) {
            shark.pitch = MathHelper.lerpAngleDegrees(0.2f, shark.pitch, 0.0f)
        } else if (vec3d.length() > 9.999999747378752E-6) {
            val d = vec3d.horizontalLength()
            val e = atan2(-vec3d.y, d) * 57.2957763671875
            shark.pitch = e.toFloat()
        }
    }

    companion object {
        private val OFFSET_MULTIPLIERS = intArrayOf(0, 1, 4, 5, 6, 7)
    }
}

