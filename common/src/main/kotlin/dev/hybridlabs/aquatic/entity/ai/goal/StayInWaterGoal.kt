package dev.hybridlabs.aquatic.entity.ai.goal

import net.minecraft.block.Blocks
import net.minecraft.entity.ai.goal.Goal
import net.minecraft.entity.mob.MobEntity
import net.minecraft.util.math.Vec3d

class StayInWaterGoal(private val mob: MobEntity) : Goal() {

    override fun canUse(): Boolean {
        return true
    }

    override fun tick() {
        val blockPos = mob.blockPos
        val blockAbove = mob.entityWorld.getBlockState(blockPos.up(1))

        if (!blockAbove.`is`(Blocks.WATER)) {
            setDownwardVelocity()
        }
    }

    private fun setDownwardVelocity() {
        val downwardVelocity = -0.25
        val currentVelocity = mob.velocity
        mob.velocity = Vec3d(currentVelocity.x, downwardVelocity, currentVelocity.z)
    }
}