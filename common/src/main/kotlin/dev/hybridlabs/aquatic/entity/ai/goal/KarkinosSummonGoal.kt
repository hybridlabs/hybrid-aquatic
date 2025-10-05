package dev.hybridlabs.aquatic.entity.ai.goal

import dev.hybridlabs.aquatic.entity.miniboss.KarkinosEntity
import net.minecraft.world.entity.ai.goal.Goal
import java.util.*

class KarkinosSummonGoal(
    private val karkinos: KarkinosEntity,
) : Goal() {

    init {
        this.flags = EnumSet.of(Flag.MOVE, Flag.LOOK)
    }

    override fun canUse(): Boolean {
        return karkinos.health <= karkinos.maxHealth / 2f &&
                !karkinos.isFlipped() &&
                !karkinos.isSummoning() &&
                karkinos.summonCooldown <= 0 &&
                karkinos.target != null
    }

    override fun start() {
        karkinos.navigation.stop()
        karkinos.isAggressive = false
        karkinos.isSprinting = false

        karkinos.startSummoning()
    }

    override fun canContinueToUse(): Boolean {
        return karkinos.isSummoning()
    }

    override fun stop() {
        karkinos.stopSummoning()
    }

    override fun tick() {
        karkinos.lookControl.setLookAt(
            karkinos.x,
            karkinos.y + karkinos.eyeHeight.toDouble(),
            karkinos.z
        )
    }
}
