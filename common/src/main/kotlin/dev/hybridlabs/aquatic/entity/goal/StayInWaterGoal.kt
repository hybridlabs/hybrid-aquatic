package dev.hybridlabs.aquatic.entity.goal

import net.minecraft.world.entity.Mob
import net.minecraft.world.entity.ai.goal.Goal
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.phys.Vec3

@Suppress("DEPRECATION")
class StayInWaterGoal(private val mob: Mob) : Goal() {

    override fun canUse(): Boolean {
        return true
    }

    override fun tick() {
        val blockPos = mob.blockPosition()
        val blockAbove = mob.level().getBlockState(blockPos.above(1))

        if (!blockAbove.`is`(Blocks.WATER)) {
            setDownwardVelocity()
        }
    }

    private fun setDownwardVelocity() {
        val downwardVelocity = -0.25
        val currentVelocity = mob.deltaMovement
        mob.deltaMovement = Vec3(currentVelocity.x, downwardVelocity, currentVelocity.z)
    }
}