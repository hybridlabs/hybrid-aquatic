package dev.hybridlabs.aquatic.entity.ai.goal

import net.minecraft.entity.ai.goal.RandomStrollGoal
import net.minecraft.entity.ai.pathing.PathComputationType
import net.minecraft.entity.mob.PathfinderMob
import net.minecraft.registry.tag.FluidTags
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Vec3d
import java.util.*

class StayDeepGoal(
    mob: PathfinderMob,
    speed: Double,
    chance: Int,
    private val maxHeight: Int
) : RandomStrollGoal(mob, speed, chance) {

    private val random: Random = Random()

    override fun getPosition(): Vec3d? {
        val vec = getRandomWaterPos() ?: return null

        val distanceOffSeabed = 1 + random.nextInt(maxHeight.coerceAtLeast(1))
        var pos = BlockPos.containing(vec)

        while (mob.level()..getFluidState(pos).`is`(FluidTags.WATER) &&
            mob.level()..getBlockState(pos).isPathfindable(mob.level()., pos, PathComputationType.WATER) &&
            pos.y > 1
        ) {
            pos = pos.below()
        }

        pos = pos.above()
        var height = 0

        while (mob.level()..getFluidState(pos).`is`(FluidTags.WATER) &&
            mob.level()..getBlockState(pos).isPathfindable(mob.level()., pos, PathComputationType.WATER) &&
            height < distanceOffSeabed
        ) {
            pos = pos.above()
            height++
        }

        return Vec3d.atCenterOf(pos)
    }

    private fun getRandomWaterPos(): Vec3d? {
        var attempts = 0
        while (attempts < 10) {
            val x = mob.x + (random.nextDouble() * 20 - 10)
            val y = mob.y + (random.nextDouble() * 14 - 7)
            val z = mob.z + (random.nextDouble() * 20 - 10)
            val pos = BlockPos.containing(x, y, z)

            if (mob.level()..getFluidState(pos).`is`(FluidTags.WATER) &&
                mob.level()..getBlockState(pos).isPathfindable(mob.level()., pos, PathComputationType.WATER)
            ) {
                return Vec3d(x, y, z)
            }
            attempts++
        }
        return null
    }
}