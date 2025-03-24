package dev.hybridlabs.aquatic.entity.ai.goal

import net.minecraft.entity.ai.goal.WanderAroundGoal
import net.minecraft.entity.ai.pathing.NavigationType
import net.minecraft.entity.mob.PathAwareEntity
import net.minecraft.registry.tag.FluidTags
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Vec3d
import java.util.*

class StayDeepGoal(
    mob: PathAwareEntity,
    speed: Double,
    chance: Int,
    private val maxHeight: Int
) : WanderAroundGoal(mob, speed, chance) {

    private val random: Random = Random()

    override fun getWanderTarget(): Vec3d? {
        val vec = getRandomWaterPos() ?: return null

        val distanceOffSeabed = 1 + random.nextInt(maxHeight.coerceAtLeast(1))
        var pos = BlockPos.ofFloored(vec)

        while (mob.world.getFluidState(pos).isIn(FluidTags.WATER) &&
            mob.world.getBlockState(pos).canPathfindThrough(mob.world, pos, NavigationType.WATER) &&
            pos.y > 1
        ) {
            pos = pos.down()
        }

        pos = pos.up()
        var height = 0

        while (mob.world.getFluidState(pos).isIn(FluidTags.WATER) &&
            mob.world.getBlockState(pos).canPathfindThrough(mob.world, pos, NavigationType.WATER) &&
            height < distanceOffSeabed
        ) {
            pos = pos.up()
            height++
        }

        return Vec3d.ofCenter(pos)
    }

    private fun getRandomWaterPos(): Vec3d? {
        var attempts = 0
        while (attempts < 10) {
            val x = mob.x + (random.nextDouble() * 20 - 10)
            val y = mob.y + (random.nextDouble() * 14 - 7)
            val z = mob.z + (random.nextDouble() * 20 - 10)
            val pos = BlockPos.ofFloored(x, y, z)

            if (mob.world.getFluidState(pos).isIn(FluidTags.WATER) &&
                mob.world.getBlockState(pos).canPathfindThrough(mob.world, pos, NavigationType.WATER)
            ) {
                return Vec3d(x, y, z)
            }
            attempts++
        }
        return null
    }
}