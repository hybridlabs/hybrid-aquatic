package dev.hybridlabs.aquatic.entity.ai.goal

import dev.hybridlabs.aquatic.entity.miniboss.ShellBeastEntity
import net.minecraft.world.entity.ai.goal.Goal
import java.util.*

class ShellBeastSummonGoal(
    private val shellBeast: ShellBeastEntity,
) : Goal() {

    init {
        this.flags = EnumSet.of(Flag.MOVE, Flag.LOOK)
    }

    override fun canUse(): Boolean {
        val canSummonHypnautilus =
            shellBeast.health <= shellBeast.maxHealth / 2f ||
                    shellBeast.isUnderWater

        return canSummonHypnautilus &&
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
