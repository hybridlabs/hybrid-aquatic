package dev.hybridlabs.aquatic.entity.ai.goal

import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.PathfinderMob
import net.minecraft.world.entity.ai.goal.Goal
import java.util.*

internal class WaterAnimalFollowCreatureGoal<T : LivingEntity>(
    private val mob: PathfinderMob,
    private val targetClass: Class<T>,
    private val speed: Double,
    private val minDistance: Float,
    private val maxDistance: Float
) : Goal() {

    private var target: T? = null

    init {
        this.flags = EnumSet.of(Flag.MOVE)
    }

    override fun canUse(): Boolean {
        val closest = mob.level().getEntitiesOfClass(
            targetClass,
            mob.boundingBox.inflate(maxDistance.toDouble())
        ) { true }
            .minByOrNull { it.distanceToSqr(mob) }

        if (closest != null && closest.distanceToSqr(mob) > (minDistance * minDistance)) {
            target = closest
            return true
        }

        return false
    }

    override fun canContinueToUse(): Boolean {
        val t = target ?: return false
        return t.isAlive && mob.distanceToSqr(t) > (minDistance * minDistance)
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