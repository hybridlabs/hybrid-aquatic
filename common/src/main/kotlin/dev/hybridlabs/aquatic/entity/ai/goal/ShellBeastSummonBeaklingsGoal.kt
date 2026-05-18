package dev.hybridlabs.aquatic.entity.ai.goal

import dev.hybridlabs.aquatic.entity.miniboss.ShellBeastEntity
import net.minecraft.world.entity.ai.goal.Goal

class ShellBeastSummonBeaklingsGoal(
    private val shellBeast: ShellBeastEntity,
) : Goal() {

    override fun canUse(): Boolean {
        val health = shellBeast.health / shellBeast.maxHealth

        val canSummonBeaklings =
            health >= 0.75f && shellBeast.isUnderWater

        return canSummonBeaklings &&
                !shellBeast.isSummoning() &&
                shellBeast.summonCooldown <= 0 &&
                shellBeast.target != null
    }

    override fun start() {
        shellBeast.navigation.stop()
        shellBeast.startSummoning()
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