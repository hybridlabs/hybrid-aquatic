package dev.hybridlabs.aquatic.entity.ai.goal.boids

import net.minecraft.commands.arguments.EntityAnchorArgument
import net.minecraft.world.entity.Mob
import net.minecraft.world.entity.ai.goal.Goal


class LookInVelocityDirectionGoal(
    private val mob: Mob
) :
    Goal() {
    override fun canUse(): Boolean {
        return true
    }

    override fun tick() {
        val velocity = mob.deltaMovement
        mob.lookAt(
            EntityAnchorArgument.Anchor.EYES,
            mob.position().add(velocity.scale(3.0))
        ) // Scale by 3 just to be sure it is roughly the right direction
    }
}