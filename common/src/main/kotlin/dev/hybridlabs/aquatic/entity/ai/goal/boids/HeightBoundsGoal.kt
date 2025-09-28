package dev.hybridlabs.aquatic.entity.ai.goal.boids

import net.minecraft.util.Mth
import net.minecraft.world.entity.Mob
import net.minecraft.world.entity.ai.goal.Goal
import net.minecraft.world.phys.Vec3


class HeightBoundsGoal(private val mob: Mob, private val minHeight: Float, private val maxHeight: Float) : Goal() {
    override fun canUse(): Boolean {
        return mob.y > maxHeight || mob.y < maxHeight
    }

    override fun tick() {
        mob.addDeltaMovement(bounds())
    }

    private fun bounds(): Vec3 {
        var amount = 0.1
        val dY = Mth.abs(mob.deltaMovement.y.toFloat())

        if (dY > amount) {
            amount = dY.toDouble()
        }

        if (mob.y > maxHeight) {
            return Vec3(0.0, -amount, 0.0)
        }
        if (mob.y < minHeight) return Vec3(0.0, amount, 0.0)

        return Vec3.ZERO
    }
}