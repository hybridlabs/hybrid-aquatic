package dev.hybridlabs.aquatic.entity.ai.goal.boids

import dev.hybridlabs.aquatic.entity.base.HAFishEntity
import net.minecraft.commands.arguments.EntityAnchorArgument
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.Mob
import net.minecraft.world.entity.VariantHolder
import net.minecraft.world.entity.ai.attributes.Attributes.MOVEMENT_SPEED
import net.minecraft.world.entity.ai.goal.Goal
import net.minecraft.world.phys.Vec3
import java.util.function.Predicate

class BoidGoal(
    private val fish: HAFishEntity,
    private val separationInfluence: Float,
    private val separationRange: Float,
    private val alignmentInfluence: Float,
    private val cohesionInfluence: Float,
) :
    Goal() {
    private var timeToFindNearbyEntities = 0
    private var nearbyMobs: MutableList<out Mob> = mutableListOf()
    private val maxSpeed: Float = fish.getAttributeValue(MOVEMENT_SPEED).toFloat()

    override fun canUse(): Boolean {
        if (fish.isGrazing()) {
            return false
        }

        if (!fish.isUnderWater) {
            return false
        }

        if (--this.timeToFindNearbyEntities <= 0) {
            this.timeToFindNearbyEntities = this.adjustedTickDelay(40)
            nearbyMobs = getNearbyEntitiesOfSameClass(fish)
        } else {
            nearbyMobs.removeIf { obj: LivingEntity -> obj.isDeadOrDying }
        }

        if (nearbyMobs.isEmpty()) {
            return false
        }
        return true
    }

    override fun canContinueToUse(): Boolean {
        if (!fish.isUnderWater) {
            return false
        }

        if (fish.isGrazing()) {
            return false
        }

        nearbyMobs.removeIf { it.isDeadOrDying }

        return nearbyMobs.isNotEmpty()
    }

    private fun getMaxDelta(): Double {
        return maxSpeed * 0.075
    }

    override fun tick() {

        val hasTarget = fish.target != null

        val sep = if (hasTarget) separation().scale(0.2) else separation()
        val ali = if (hasTarget) alignment().scale(0.5) else alignment()
        val coh = if (hasTarget) cohesion().scale(0.4) else cohesion()

        var boidVec = coh
            .add(ali)
            .add(sep)
            .add(random())
            .add(targetAttraction())

        if (boidVec.length() > getMaxDelta()) {
            boidVec = boidVec.normalize().scale(getMaxDelta())
        }

        fish.addDeltaMovement(boidVec)

        val targetPos = fish.position().add(fish.deltaMovement)
        fish.lookAt(
            EntityAnchorArgument.Anchor.EYES,
            Vec3(targetPos.x, targetPos.y + fish.eyeHeight, targetPos.z)
        )
    }

    fun random(): Vec3 {
        val velocity = fish.deltaMovement
        if (velocity.length() < maxSpeed * 0.001) {
            val yaw = (fish.random.nextGaussian() * 40) - 20
            val pitch = (fish.random.nextGaussian() * 2) - 1
            return Vec3.directionFromRotation(pitch.toFloat(), yaw.toFloat()).scale(0.1)
        }
        return fish.forward.scale(0.1)
    }

    private fun separation(): Vec3 {
        var c = Vec3.ZERO

        for (nearbyMob in nearbyMobs) {
            if ((nearbyMob.position().subtract(fish.position()).length()) < separationRange) {
                c = c.subtract(nearbyMob.position().subtract(fish.position()))
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
        c = c.subtract(fish.deltaMovement)
        return c.scale(alignmentInfluence.toDouble())
    }

    private fun cohesion(): Vec3 {
        var c = Vec3.ZERO

        for (nearbyMob in nearbyMobs) {
            c = c.add(nearbyMob.position())
        }

        c = c.scale((1f / nearbyMobs.size).toDouble())
        c = c.subtract(fish.position())
        return c.scale(cohesionInfluence.toDouble())
    }

    private fun targetAttraction(): Vec3 {
        val target = fish.target ?: return Vec3.ZERO
        if (!target.isAlive) return Vec3.ZERO

        val targetPos = target.position().subtract(fish.position())
        return targetPos.normalize().scale(maxSpeed * 0.15)
    }

    companion object {
        fun getNearbyEntitiesOfSameClass(mob: Mob): MutableList<out Mob> {
            val predicate = Predicate<Mob> { other ->
                if (other == mob) return@Predicate false

                if (mob is VariantHolder<*> && other is VariantHolder<*>) {
                    return@Predicate mob.variant == other.variant
                }

                other.type == mob.type
            }

            return mob.level().getEntitiesOfClass(
                mob.javaClass,
                mob.boundingBox.inflate(4.0, 4.0, 4.0),
                predicate
            )
        }
    }
}