package dev.hybridlabs.aquatic.entity.ai.goal

import dev.hybridlabs.aquatic.entity.miniboss.ShellBeastEntity
import net.minecraft.world.entity.ai.goal.Goal

class ShellBeastSummonHypnautilusGoal(
    private val shellBeast: ShellBeastEntity,
) : Goal() {

    override fun canUse(): Boolean {
        val canSummonHypnautilus =
            shellBeast.health <= shellBeast.maxHealth * 0.5f &&
                    shellBeast.isUnderWater &&
                    !shellBeast.hasMinions() &&
                    shellBeast.tickCount > 120

        return canSummonHypnautilus &&
                !shellBeast.isSummoning() &&
                shellBeast.hypnautilusCooldown <= 0 &&
                shellBeast.target != null
    }

    override fun start() {
        shellBeast.navigation.stop()
        shellBeast.startSummoning(ShellBeastEntity.SummonType.HYPNAUTILUS)
    }

    override fun canContinueToUse(): Boolean {
        return shellBeast.isSummoning()
    }

    override fun stop() {
        shellBeast.stopSummoning()
    }

    override fun tick() {
        shellBeast.lookControl.setLookAt(
            shellBeast.x,
            shellBeast.y + shellBeast.eyeHeight.toDouble(),
            shellBeast.z
        )
    }
}