package dev.hybridlabs.aquatic.entity.ai.goal

import net.minecraft.world.entity.PathfinderMob
import net.minecraft.world.entity.ai.behavior.BehaviorUtils
import net.minecraft.world.entity.ai.goal.RandomStrollGoal
import net.minecraft.world.phys.Vec3
import kotlin.math.sqrt

open class HybridAquaticRandomSwimmingGoal(
    mob: PathfinderMob,
    speed: Double,
    interval: Int,
    private val minDistance: Double
) : RandomStrollGoal(mob, speed, interval) {

    override fun getPosition(): Vec3? {
        val origin = mob.position()
        val minDistanceSqr = minDistance * minDistance

        repeat(4) {
            val pos = BehaviorUtils.getRandomSwimmablePos(this.mob, 32, 16)
            if (pos != null) {
                if (origin.distanceToSqr(pos) >= minDistanceSqr) {
                    return pos
                }
            }
        }

        return null
    }
}
