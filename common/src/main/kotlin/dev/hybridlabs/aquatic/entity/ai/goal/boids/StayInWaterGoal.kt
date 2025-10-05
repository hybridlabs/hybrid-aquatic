package dev.hybridlabs.aquatic.entity.ai.goal.boids

import net.minecraft.util.Mth
import net.minecraft.world.entity.Mob
import net.minecraft.world.entity.ai.goal.Goal
import net.minecraft.world.phys.Vec3


class StayInWaterGoal(private val mob: Mob) : Goal() {
    override fun canUse(): Boolean {
        return true
    }

    override fun tick() {
        val blockPos = mob.blockPosition()
        val blockAbove = mob.level().getBlockState(blockPos.above(1))
        val amount = amount()

        if (blockAbove.fluidState.isEmpty) {
            mob.addDeltaMovement(Vec3(0.0, -amount.toDouble(), 0.0))
        }
    }

    fun amount(): Float {
        var amount = 0.1f
        val dY = Mth.abs(mob.deltaMovement.y.toFloat())

        if (dY > amount) {
            amount = dY
        }

        return amount
    }
}