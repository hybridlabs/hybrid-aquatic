package dev.hybridlabs.aquatic.entity.ai.goal

import dev.hybridlabs.aquatic.entity.miniboss.HypnautilusEntity
import net.minecraft.world.entity.ai.goal.Goal
import java.util.*

class HypnotizeTargetGoal(
    private val hypnautilus: HypnautilusEntity,
) : Goal() {

    init {
        this.flags = EnumSet.of(Flag.MOVE, Flag.LOOK)
    }

    override fun canUse(): Boolean {
        return hypnautilus.hypnosisCooldown <= 0 &&
                hypnautilus.target != null
    }

    override fun start() {
        hypnautilus.navigation.stop()
        hypnautilus.startHypnotizing()
    }

    override fun canContinueToUse(): Boolean {
        return hypnautilus.isHypnotizing()
    }

    override fun stop() {
        hypnautilus.stopHypnotizing()
    }

    override fun tick() {
        hypnautilus.lookControl.setLookAt(
            hypnautilus.x,
            hypnautilus.y + hypnautilus.eyeHeight.toDouble(),
            hypnautilus.z
        )
    }
}