package dev.hybridlabs.aquatic.entity.ai.goal

import net.minecraft.world.effect.MobEffects
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.PathfinderMob
import net.minecraft.world.entity.ai.goal.Goal

class FollowGlowingEntityGoal(
    private val mob: PathfinderMob,
    private val speed: Double,
    private val minDistance: Float,
    private val maxDistance: Float
) : Goal() {

    private var target: LivingEntity? = null

    override fun canUse(): Boolean {
        val closest = mob.level().getEntitiesOfClass(
            LivingEntity::class.java,
            mob.boundingBox.inflate(maxDistance.toDouble())
        ) { entity ->
            entity != mob && entity.hasEffect(MobEffects.GLOWING)
        }.minByOrNull { it.distanceToSqr(mob) }

        if (closest != null && closest.distanceToSqr(mob) > (minDistance * minDistance)) {
            target = closest
            return true
        }

        return false
    }

    override fun canContinueToUse(): Boolean {
        val t = target ?: return false

        val distSqr = mob.distanceToSqr(t)
        val minDistSqr = minDistance * minDistance
        val maxDistSqr = maxDistance * maxDistance

        return t.isAlive &&
                t.hasEffect(MobEffects.GLOWING) &&
                distSqr > minDistSqr &&
                distSqr < maxDistSqr
    }

    override fun start() {
        target?.let { mob.navigation.moveTo(it, speed) }
    }

    override fun stop() {
        mob.navigation.stop()
        target = null
    }

    override fun tick() {
        target?.let {
            mob.navigation.moveTo(it, speed)
        }
    }
}