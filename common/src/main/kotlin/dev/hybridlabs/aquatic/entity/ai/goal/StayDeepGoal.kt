package dev.hybridlabs.aquatic.entity.ai.goal

import net.minecraft.core.BlockPos
import net.minecraft.tags.FluidTags
import net.minecraft.world.entity.PathfinderMob
import net.minecraft.world.entity.ai.goal.RandomStrollGoal
import net.minecraft.world.level.pathfinder.PathComputationType
import net.minecraft.world.phys.Vec3
import java.util.*

class StayDeepGoal(
    mob: PathfinderMob,
    speed: Double,
    chance: Int,
    private val maxHeight: Int
) : RandomStrollGoal(mob, speed, chance) {

    private val random: Random = Random()

    override fun getPosition(): Vec3? {
        val vec = getRandomWaterPos() ?: return null

        val distanceOffSeabed = 1 + random.nextInt(maxHeight.coerceAtLeast(1))
        var pos = BlockPos.containing(vec)

        while (mob.level().getFluidState(pos).`is`(FluidTags.WATER) &&
            mob.level().getBlockState(pos).isPathfindable(mob.level(), pos, PathComputationType.WATER) &&
            pos.y > 1
        ) {
            pos = pos.below()
        }

        pos = pos.above()
        var height = 0

        while (mob.level().getFluidState(pos).`is`(FluidTags.WATER) &&
            mob.level().getBlockState(pos).isPathfindable(mob.level(), pos, PathComputationType.WATER) &&
            height < distanceOffSeabed
        ) {
            pos = pos.above()
            height++
        }

        return Vec3.atCenterOf(pos)
    }

    private fun getRandomWaterPos(): Vec3? {
        var attempts = 0
        while (attempts < 10) {
            val x = mob.x + (random.nextDouble() * 20 - 10)
            val y = mob.y + (random.nextDouble() * 14 - 7)
            val z = mob.z + (random.nextDouble() * 20 - 10)
            val pos = BlockPos.containing(x, y, z)

            if (mob.level().getFluidState(pos).`is`(FluidTags.WATER) &&
                mob.level().getBlockState(pos).isPathfindable(mob.level(), pos, PathComputationType.WATER)
            ) {
                return Vec3(x, y, z)
            }
            attempts++
        }
        return null
    }
}