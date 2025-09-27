package dev.hybridlabs.aquatic.entity.ai.goal

import net.minecraft.world.entity.Mob
import net.minecraft.world.entity.ai.goal.Goal
import net.minecraft.world.level.material.Fluids
import net.minecraft.world.phys.Vec3

class StayInWaterGoal(private val mob: Mob) : Goal() {

    override fun canUse(): Boolean {
        return true
    }

    override fun tick() {
        val blockPos = mob.blockPosition()
        val fluidAbove = mob.level().getFluidState(blockPos.above(1))

        if (!fluidAbove.`is`(Fluids.WATER)) {
            setDownwardVelocity()
        }
    }

    private fun setDownwardVelocity() {
        val downwardVelocity = -0.1
        val currentVelocity = mob.deltaMovement
        mob.deltaMovement = Vec3(currentVelocity.x, downwardVelocity, currentVelocity.z)
    }
}