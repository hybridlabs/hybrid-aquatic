package dev.hybridlabs.aquatic.entity.ai.goal.boids

import net.minecraft.commands.arguments.EntityAnchorArgument
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.Mob
import net.minecraft.world.entity.VariantHolder
import net.minecraft.world.entity.ai.attributes.Attributes.MOVEMENT_SPEED
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
    private var nearbyMobs: MutableList<out Mob> = mutableListOf()
    private val maxSpeed: Float = mob.getAttributeValue(MOVEMENT_SPEED).toFloat()

    override fun canUse(): Boolean {
        if (--this.timeToFindNearbyEntities <= 0) {
            this.timeToFindNearbyEntities = this.adjustedTickDelay(40)
            nearbyMobs = getNearbyEntitiesOfSameClass(mob)
        } else {
            nearbyMobs.removeIf { obj: LivingEntity -> obj.isDeadOrDying }
        }

        if (nearbyMobs.isEmpty()) {
            return false
        }
        return true
    }

    private fun getMaxDelta(): Double {
        return maxSpeed * 0.075
    }

    override fun tick() {

        var boidVec = cohesion().add(alignment().add(separation().add(random())))
        if (boidVec.length() > getMaxDelta()) {
            boidVec = boidVec.normalize().scale(getMaxDelta())
        }

        mob.addDeltaMovement(boidVec)

        val target = mob.position().add(mob.deltaMovement)
        mob.lookAt(
            EntityAnchorArgument.Anchor.EYES,
            Vec3(target.x, target.y + mob.eyeHeight, target.z)
        )
    }

    fun random(): Vec3 {
        val velocity = mob.deltaMovement
        if (velocity.length() < maxSpeed * 0.001) {
            val yaw = (mob.random.nextGaussian() * 40) - 20
            val pitch = (mob.random.nextGaussian() * 2) - 1
            return Vec3.directionFromRotation(pitch.toFloat(), yaw.toFloat()).scale(0.1)
        }
        return mob.forward.scale(0.1)
    }

    private fun separation(): Vec3 {
        var c = Vec3.ZERO

        for (nearbyMob in nearbyMobs) {
            if ((nearbyMob.position().subtract(mob.position()).length()) < separationRange) {
                c = c.subtract(nearbyMob.position().subtract(mob.position()))
            }
        }

        return c.scale(separationInfluence.toDouble())
    }

    private fun alignment(): Vec3 {
        var c = Vec3.ZERO

        for (nearbyMob in nearbyMobs) {
            c = c.add(nearbyMob.deltaMovement)
        }

        c = c.scale((1f / nearbyMobs.size).toDouble())
        c = c.subtract(mob.deltaMovement)
        return c.scale(alignmentInfluence.toDouble())
    }

    private fun cohesion(): Vec3 {
        var c = Vec3.ZERO

        for (nearbyMob in nearbyMobs) {
            c = c.add(nearbyMob.position())
        }

        c = c.scale((1f / nearbyMobs.size).toDouble())
        c = c.subtract(mob.position())
        return c.scale(cohesionInfluence.toDouble())
    }

    companion object {

        fun getNearbyEntitiesOfSameClass(mob: Mob): MutableList<out Mob> {
            val predicate: Predicate<Mob> =
                Predicate { other -> other != mob
                        && (mob is VariantHolder<*>) && (other is VariantHolder<*>)
                        && (mob as VariantHolder<*>).variant == (other as VariantHolder<*>).variant
                }

            return mob.level().getEntitiesOfClass(mob.javaClass, mob.boundingBox.inflate(4.0, 4.0, 4.0), predicate)
        }
    }
}