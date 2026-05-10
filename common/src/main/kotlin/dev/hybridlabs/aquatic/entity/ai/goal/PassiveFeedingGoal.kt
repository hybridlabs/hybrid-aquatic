package dev.hybridlabs.aquatic.entity.ai.goal

import dev.hybridlabs.aquatic.entity.base.HAWaterAnimal
import dev.hybridlabs.aquatic.entity.base.HAWaterAnimal.Companion.MAX_HUNGER
import net.minecraft.world.entity.ai.goal.Goal

class PassiveFeedingGoal(
    private val waterAnimal: HAWaterAnimal
) : Goal() {

    override fun canUse(): Boolean {
        if (waterAnimal.hunger > 0 ||
            !waterAnimal.isInWaterOrBubble ||
            waterAnimal.level().getNearestPlayer(waterAnimal, 32.0) == null) {
            return false
        }

        return this.waterAnimal.hunger <= 0
    }

    override fun start() {
        waterAnimal.startFeeding()
    }

    override fun stop() {
        waterAnimal.stopFeeding()
    }

    override fun canContinueToUse(): Boolean {
        return waterAnimal.hunger <= MAX_HUNGER
    }

    override fun tick() {
        waterAnimal.hunger += 1
    }
}