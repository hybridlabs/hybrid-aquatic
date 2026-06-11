package dev.hybridlabs.aquatic.entity.ai.goal

import dev.hybridlabs.aquatic.entity.base.HAWaterAnimal
import net.minecraft.world.entity.ai.goal.Goal

class WaterAnimalPerformTrickGoal(
    private val waterAnimal: HAWaterAnimal
) : Goal() {
    private var trickTime = 0
    private var trickCooldown: Int

    init {
        trickCooldown = waterAnimal.tickCount + (10 * 40 + waterAnimal.getRandom().nextInt(10) * 80)
    }

    override fun canUse(): Boolean {
        if (trickCooldown > this.waterAnimal.tickCount ||
            waterAnimal.level().getNearestPlayer(waterAnimal, 32.0) == null) {
            return false
        }
        return this.waterAnimal.getRandom().nextInt(40) == 0 && waterAnimal.isInWater
    }

    override fun start() {
        trickTime = 40
        waterAnimal.startPerfomingTrick()
        trickCooldown = this.waterAnimal.tickCount + (10 * 20 + this.waterAnimal.getRandom().nextInt(10) * 20)
        waterAnimal.triggerAnim("trick_controller", "trick")
    }

    override fun stop() {
        waterAnimal.stopPerformingTrick()
    }

    override fun canContinueToUse(): Boolean {
        return trickTime >= 0
    }

    override fun tick() {
        trickTime--
    }
}