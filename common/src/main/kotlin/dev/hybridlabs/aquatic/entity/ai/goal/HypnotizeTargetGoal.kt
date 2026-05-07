package dev.hybridlabs.aquatic.entity.ai.goal

import dev.hybridlabs.aquatic.entity.miniboss.HypnautilusEntity
import net.minecraft.commands.arguments.EntityAnchorArgument
import net.minecraft.world.entity.ai.goal.Goal

class HypnotizeTargetGoal(
    private val hypnautilus: HypnautilusEntity,
) : Goal() {
    private var hypnosisTime = 0
    private var hypnosisCooldown: Int

    init {
        hypnosisCooldown = hypnautilus.tickCount + (10 * 40 + hypnautilus.getRandom().nextInt(10) * 80)
    }

    override fun canUse(): Boolean {
        if (hypnosisCooldown > 0) {
            return false
        }
        return this.hypnautilus.getRandom().nextInt(40) == 0
    }

    override fun start() {
        hypnosisCooldown = this.hypnautilus.tickCount + (10 * 20 + this.hypnautilus.getRandom().nextInt(10) * 20)
        hypnautilus.navigation.stop()
        hypnosisTime = 30
        hypnautilus.startHypnotizing()
    }

    override fun canContinueToUse(): Boolean {
        return hypnosisTime >= 0
    }

    override fun stop() {
        hypnautilus.stopHypnotizing()
    }

    override fun tick() {
        val target = hypnautilus.target ?: return

        hypnosisTime--

        hypnautilus.lookControl.setLookAt(
            target.x,
            target.eyeY,
            target.z
        )

        target.lookAt(
            EntityAnchorArgument.Anchor.EYES,
            hypnautilus.eyePosition
        )
    }
}