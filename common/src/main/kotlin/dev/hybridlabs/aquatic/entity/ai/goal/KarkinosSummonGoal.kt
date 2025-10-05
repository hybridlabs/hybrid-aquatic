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
                karkinos.summonTimer <= 0
    }

    override fun start() {
        karkinos.navigation.stop()
        karkinos.isAggressive = false
        karkinos.isSprinting = false

        karkinos.setSummoning(true)
        karkinos.summonTimer = 20 * 20
        karkinos.startSummoning()
    }

    override fun stop() {
        karkinos.setSummoning(false)
        karkinos.stopSummoning()
    }

    override fun canContinueToUse(): Boolean {
        return karkinos.isSummoning()
    }

    override fun tick() {
        karkinos.lookControl.setLookAt(karkinos.x, karkinos.y + karkinos.eyeHeight.toDouble(), karkinos.z)
        if (karkinos.summonTimer > 0) karkinos.summonTimer--
    }
}
