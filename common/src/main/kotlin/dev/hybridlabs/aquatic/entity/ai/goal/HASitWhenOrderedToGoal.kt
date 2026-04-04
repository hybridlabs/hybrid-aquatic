package dev.hybridlabs.aquatic.entity.ai.goal

import dev.hybridlabs.aquatic.entity.base.HATameableWaterAnimal
import net.minecraft.world.entity.ai.goal.Goal
import java.util.*

class HASitWhenOrderedToGoal(private val mob: HATameableWaterAnimal) : Goal() {
    init {
        this.flags = EnumSet.of<Flag?>(Flag.JUMP, Flag.MOVE)
    }

    override fun canContinueToUse(): Boolean {
        return this.mob.isOrderedToSit()
    }

    override fun canUse(): Boolean {
        return if (!this.mob.isTame()) {
            false
        } else if (this.mob.isInWaterOrBubble) {
            false
        } else if (!this.mob.onGround()) {
            false
        } else {
            val livingentity = this.mob.owner
            if (livingentity == null) {
                true
            } else {
                if (this.mob.distanceToSqr(livingentity) < 144.0 && livingentity.lastHurtByMob != null) false else this.mob.isOrderedToSit()
            }
        }
    }

    override fun start() {
        this.mob.getNavigation().stop()
        this.mob.isInSittingPose()
    }

    override fun stop() {
        !this.mob.isInSittingPose()
    }
}