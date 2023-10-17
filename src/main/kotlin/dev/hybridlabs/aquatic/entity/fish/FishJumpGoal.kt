package dev.hybridlabs.aquatic.entity.fish

import net.minecraft.entity.ai.goal.DiveJumpingGoal
import net.minecraft.registry.tag.FluidTags
import net.minecraft.sound.SoundEvents
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.MathHelper
import kotlin.math.abs
import kotlin.math.atan2

class FishJumpGoal(private val fish: HybridAquaticFishEntity, chance: Int) : DiveJumpingGoal() {
    private val chance: Int
    private var inWater = false

    init {
        this.chance = toGoalTicks(chance)
    }

    override fun canStart(): Boolean {
        return if (fish.random.nextInt(chance) != 0) {
            false
        } else {
            val direction = fish.movementDirection
            val i = direction.offsetX
            val j = direction.offsetZ
            val blockPos = fish.blockPos
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
        return fish.world.getFluidState(blockPos).isIn(FluidTags.WATER) && !fish.world.getBlockState(blockPos)
            .material.blocksMovement()
    }

    private fun isAirAbove(pos: BlockPos, offsetX: Int, offsetZ: Int, multiplier: Int): Boolean {
        return fish.world.getBlockState(
            pos.add(
                offsetX * multiplier,
                1,
                offsetZ * multiplier
            )
        ).isAir && fish.world.getBlockState(pos.add(offsetX * multiplier, 2, offsetZ * multiplier)).isAir
    }

    override fun shouldContinue(): Boolean {
        val d = fish.velocity.y
        return (!(d * d < 0.029999999329447746) || fish.pitch == 0.0f || !(abs(fish.pitch) < 10.0f) || !fish.isTouchingWater) && !fish.isOnGround
    }

    override fun canStop(): Boolean {
        return false
    }

    override fun start() {
        val direction = fish.movementDirection
        fish.velocity =
            fish.velocity.add(direction.offsetX.toDouble() * 0.6, 0.7, direction.offsetZ.toDouble() * 0.6)
        fish.navigation.stop()
    }

    override fun stop() {
        fish.pitch = 0.0f
    }

    override fun tick() {
        val bl = inWater
        if (!bl) {
            val fluidState = fish.world.getFluidState(fish.blockPos)
            inWater = fluidState.isIn(FluidTags.WATER)
        }
        if (inWater && !bl) {
            fish.playSound(SoundEvents.ENTITY_DOLPHIN_JUMP, 1.0f, 1.0f)
        }
        val vec3d = fish.velocity
        if (vec3d.y * vec3d.y < 0.029999999329447746 && fish.pitch != 0.0f) {
            fish.pitch = MathHelper.lerpAngleDegrees(0.2f, fish.pitch, 0.0f)
        } else if (vec3d.length() > 9.999999747378752E-6) {
            val d = vec3d.horizontalLength()
            val e = atan2(-vec3d.y, d) * 57.2957763671875
            fish.pitch = e.toFloat()
        }
    }

    companion object {
        private val OFFSET_MULTIPLIERS = intArrayOf(0, 1, 4, 5, 6, 7)
    }
}
