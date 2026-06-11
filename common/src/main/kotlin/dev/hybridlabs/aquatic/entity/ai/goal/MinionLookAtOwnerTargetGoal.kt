package dev.hybridlabs.aquatic.entity.ai.goal

import dev.hybridlabs.aquatic.entity.base.HAMinionEntity
import net.minecraft.world.entity.ai.goal.Goal
import java.util.*

class MinionLookAtOwnerTargetGoal(
    private val minion: HAMinionEntity,
) : Goal() {

    init {
        this.flags = EnumSet.of(Flag.LOOK)
    }

    override fun canUse(): Boolean {
        val owner = minion.getOwner() ?: return false
        return owner.target != null
    }

    override fun canContinueToUse(): Boolean {
        val owner = minion.getOwner() ?: return false
        return owner.target != null && owner.target!!.isAlive
    }

    override fun tick() {
        val owner = minion.getOwner() ?: return
        val target = owner.target ?: return

        minion.lookAt(target, 30f, 30f)
    }
}