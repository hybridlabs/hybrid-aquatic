package dev.hybridlabs.aquatic.entity.ai.goal.boids

import net.minecraft.commands.arguments.EntityAnchorArgument
import net.minecraft.world.entity.Mob
import net.minecraft.world.entity.ai.goal.Goal


class LimitSpeedAndLookInVelocityDirectionGoal(
    private val mob: Mob,
    private val minSpeed: Float,
    private val maxSpeed: Float,
) :
    Goal() {
    override fun canUse(): Boolean {
        return true
    }

    override fun tick() {
        var velocity = mob.deltaMovement
        val speed = velocity.length()

        if (speed < minSpeed) velocity = velocity.normalize().scale(minSpeed.toDouble())
        if (speed > maxSpeed) velocity = velocity.normalize().scale(maxSpeed.toDouble())

        mob.deltaMovement = velocity
        mob.lookAt(
            EntityAnchorArgument.Anchor.EYES,
            mob.position().add(velocity.scale(3.0))
        ) // Scale by 3 just to be sure it is roughly the right direction
    }
}