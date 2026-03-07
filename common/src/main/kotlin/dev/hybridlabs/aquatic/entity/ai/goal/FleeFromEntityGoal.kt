package dev.hybridlabs.aquatic.entity.ai.goal

import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.LivingEntity
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
    private var level = pathfinderMob.level()
    private var pathNav = pathfinderMob.navigation

    init {
        this.setFlags(EnumSet.of<Flag>(Flag.MOVE))
    }

    override fun canUse(): Boolean {
        val nearbyEntitiesOfClass = level.getEntitiesOfClass(
            entityClassToAvoid,
            pathfinderMob.boundingBox.inflate(maxDistance, 3.0, maxDistance)
        )

        toAvoid = getNearestEntity(nearbyEntitiesOfClass, pathfinderMob, pathfinderMob.position()) ?: return false

        val blockPosAway = DefaultRandomPos.getPosAway(this.pathfinderMob, 16, 7, this.toAvoid!!.position()) ?: return false
        if (this.toAvoid!!.distanceToSqr(blockPosAway.x, blockPosAway.y, blockPosAway.z) < this.toAvoid!!.distanceToSqr(this.pathfinderMob)) return false

        this.path = this.pathNav.createPath(blockPosAway.x, blockPosAway.y, blockPosAway.z, 0)
        return this.path != null
    }

    override fun canContinueToUse(): Boolean {
        return !this.pathNav.isDone
    }

    override fun start() {
        this.pathNav.moveTo(this.path, this.walkSpeedModifier)
    }

    override fun stop() {
        this.toAvoid = null
    }

    override fun tick() {
        if (this.pathfinderMob.distanceToSqr(this.toAvoid!!) < 49.0) {
            pathNav.setSpeedModifier(this.sprintSpeedModifier)
        } else {
            pathNav.setSpeedModifier(this.walkSpeedModifier)
        }
    }

    companion object {
        fun <T: Entity> getNearestEntity(entities: List<T>, target: LivingEntity?, pos: Vec3): T? {
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