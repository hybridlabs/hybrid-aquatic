package dev.hybridlabs.aquatic.entity.ai.goal

import dev.hybridlabs.aquatic.entity.base.HAWaterAnimal
import net.minecraft.world.entity.ai.goal.Goal
import java.util.*

class WaterAnimalSitGoal(
    private val waterAnimal: HAWaterAnimal
) : Goal() {
    private var sitTime = 0
    private var sitCooldown: Int

    init {
        this.flags = EnumSet.of(Flag.MOVE, Flag.LOOK)
        sitCooldown = waterAnimal.tickCount + (10 * 40 + waterAnimal.getRandom().nextInt(10) * 80)
    }

    override fun canUse(): Boolean {
        if (sitCooldown > this.waterAnimal.tickCount || waterAnimal.level().getNearestPlayer(waterAnimal, 32.0) == null) {
            return false
        }
        return this.waterAnimal.getRandom().nextInt(40) == 0
    }

    override fun start() {
        sitTime = (10 * 20 + this.waterAnimal.getRandom().nextInt(10) * 30)
        waterAnimal.startSitting()
        sitCooldown = this.waterAnimal.tickCount + (10 * 20 + this.waterAnimal.getRandom().nextInt(10) * 20)
    }

    override fun stop() {
        waterAnimal.stopSitting()
    }

    override fun canContinueToUse(): Boolean {
        return sitTime >= 0
    }

    override fun tick() {
        sitTime--
    }
}