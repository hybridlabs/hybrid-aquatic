package dev.hybridlabs.aquatic.entity.ai.goal

import dev.hybridlabs.aquatic.entity.mammal.OtterEntity
import dev.hybridlabs.aquatic.entity.mammal.OtterEntity.Companion.OtterAction
import net.minecraft.world.entity.ai.goal.Goal
import java.util.*

class OtterSitGoal(private val otter: OtterEntity) : Goal() {

    init {
        this.flags = EnumSet.of(Flag.MOVE, Flag.JUMP)
    }

    override fun canUse(): Boolean {
        return otter.isTame() && otter.isSitting()
    }

    override fun canContinueToUse(): Boolean {
        return otter.isSitting()
    }

    override fun start() {
        otter.navigation.stop()
    }

    override fun tick() {
        otter.navigation.stop()
        otter.deltaMovement = otter.deltaMovement.multiply(0.0, 1.0, 0.0)
        otter.setAction(
            if (otter.isInWater && !otter.onGround()) OtterAction.FLOATING else OtterAction.IDLE
        )
    }

    override fun stop() {
        otter.setAction(OtterAction.IDLE)
    }
}
