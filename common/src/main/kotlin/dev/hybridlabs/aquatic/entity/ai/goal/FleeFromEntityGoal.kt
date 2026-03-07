package dev.hybridlabs.aquatic.entity.ai.goal

import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.PathfinderMob
import net.minecraft.world.entity.ai.goal.Goal
import net.minecraft.world.entity.ai.util.DefaultRandomPos
import net.minecraft.world.level.pathfinder.Path
import net.minecraft.world.phys.Vec3
import java.util.*

class FleeFromEntityGoal<E: Entity> (
    val pathfinderMob: PathfinderMob,
    val entityClassToAvoid: Class<E>,
    val maxDistance: Double,
    val walkSpeedModifier: Double,
    val sprintSpeedModifier: Double
): Goal() {
    private var toAvoid: E? = null
    private var path: Path? = null
    private val level = pathfinderMob.level()
    private val pathNav = pathfinderMob.navigation

    init {
        setFlags(EnumSet.of<Flag>(Flag.MOVE))
    }

    override fun canUse(): Boolean {
        val nearbyEntitiesOfClass = level.getEntitiesOfClass(
            entityClassToAvoid,
            pathfinderMob.boundingBox.inflate(maxDistance, 3.0, maxDistance)
        )

        toAvoid = getNearestEntity(nearbyEntitiesOfClass, pathfinderMob.position()) ?: return false

        val blockPosAway = DefaultRandomPos.getPosAway(pathfinderMob, 24, 7, toAvoid!!.position()) ?: return false
        if (toAvoid!!.distanceToSqr(blockPosAway.x, blockPosAway.y, blockPosAway.z) < toAvoid!!.distanceToSqr(pathfinderMob)) return false

        path = pathNav.createPath(blockPosAway.x, blockPosAway.y, blockPosAway.z, 25)
        return path != null
    }

    override fun canContinueToUse(): Boolean {
        return !pathNav.isDone
    }

    override fun start() {
        pathNav.moveTo(path, walkSpeedModifier)
    }

    override fun stop() {
        toAvoid = null
    }

    override fun tick() {
        if (pathfinderMob.distanceToSqr(toAvoid!!) < 64.0) {
            pathNav.setSpeedModifier(sprintSpeedModifier)
        } else {
            pathNav.setSpeedModifier(walkSpeedModifier)
        }
    }

    companion object {
        fun <T: Entity> getNearestEntity(entities: List<T>, pos: Vec3): T? {
            var firstDistance = -1.0
            var pickedEntity: T? = null

            for (entityFromList in entities) {
                val newDistance: Double = entityFromList.distanceToSqr(pos.x, pos.y, pos.z)
                if (firstDistance == -1.0 || newDistance < firstDistance) {
                    firstDistance = newDistance
                    pickedEntity = entityFromList
                }
            }

            return pickedEntity
        }
    }
}