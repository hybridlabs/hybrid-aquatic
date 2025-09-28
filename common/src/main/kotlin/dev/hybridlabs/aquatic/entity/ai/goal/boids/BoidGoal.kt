package dev.hybridlabs.aquatic.entity.ai.goal.boids

import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.Mob
import net.minecraft.world.entity.ai.goal.Goal
import net.minecraft.world.phys.Vec3
import java.util.function.Predicate


class BoidGoal(
    private val mob: Mob,
    private val separationInfluence: Float,
    private val separationRange: Float,
    private val alignmentInfluence: Float,
    private val cohesionInfluence: Float,
) :
    Goal() {
    private var timeToFindNearbyEntities = 0
    private var nearbyMobs: MutableList<out Mob>? = null
    private var enabled = true

    override fun canUse(): Boolean {
        return true
    }

    override fun tick() {
        if (--this.timeToFindNearbyEntities <= 0) {
            this.timeToFindNearbyEntities = this.adjustedTickDelay(40)
            nearbyMobs = getNearbyEntitiesOfSameClass(mob)
        } else {
            nearbyMobs!!.removeIf { obj: LivingEntity -> obj.isDeadOrDying }
        }

        if (nearbyMobs!!.isEmpty()) {
            enabled = false
            return
        }

        if (mob.level().gameTime % 4 == 0L) {
            mob.addDeltaMovement(random())
        } else {
            mob.addDeltaMovement(mob.deltaMovement.normalize().scale(0.07))
        }

        mob.addDeltaMovement(cohesion())
        mob.addDeltaMovement(alignment())
        mob.addDeltaMovement(separation())
    }

    fun random(): Vec3 {
        val velocity = mob.deltaMovement
        if (velocity.length() < 1.2) {
            val pitch = (mob.random.nextGaussian() * 180) - 90
            return Vec3.directionFromRotation(pitch.toFloat(), 0f).scale(0.1)
        }
        return Vec3.ZERO
    }

    private fun randomSign(): Int {
        val isNegative = mob.random.nextBoolean()

        if (isNegative) {
            return -1
        }

        return 1
    }

    private fun separation(): Vec3 {
        var c = Vec3.ZERO

        for (nearbyMob in nearbyMobs!!) {
            if ((nearbyMob.position().subtract(mob.position()).length()) < separationRange) {
                c = c.subtract(nearbyMob.position().subtract(mob.position()))
            }
        }

        return c.scale(separationInfluence.toDouble())
    }

    private fun alignment(): Vec3 {
        var c = Vec3.ZERO

        for (nearbyMob in nearbyMobs!!) {
            c = c.add(nearbyMob.deltaMovement)
        }

        c = c.scale((1f / nearbyMobs!!.size).toDouble())
        c = c.subtract(mob.deltaMovement)
        return c.scale(alignmentInfluence.toDouble())
    }

    private fun cohesion(): Vec3 {
        var c = Vec3.ZERO

        for (nearbyMob in nearbyMobs!!) {
            c = c.add(nearbyMob.position())
        }

        c = c.scale((1f / nearbyMobs!!.size).toDouble())
        c = c.subtract(mob.position())
        return c.scale(cohesionInfluence.toDouble())
    }

    companion object {

        fun getNearbyEntitiesOfSameClass(mob: Mob): MutableList<out Mob> {
            val predicate: Predicate<Mob> = Predicate { other -> other != mob }

            return mob.level().getEntitiesOfClass(mob.javaClass, mob.boundingBox.inflate(4.0, 4.0, 4.0), predicate)
        }
    }
}