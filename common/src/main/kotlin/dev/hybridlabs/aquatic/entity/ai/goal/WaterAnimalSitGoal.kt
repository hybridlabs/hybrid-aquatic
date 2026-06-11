package dev.hybridlabs.aquatic.entity.ai.goal

import dev.hybridlabs.aquatic.entity.base.HAWaterAnimal
import net.minecraft.world.entity.ai.goal.Goal
import java.util.*

class WaterAnimalSitGoal(
    private val waterAnimal: HAWaterAnimal,
) : Goal() {

    private var sitTime = 0
    private var sitCooldown: Int

    init {
        this.flags = EnumSet.of(Flag.MOVE, Flag.LOOK)

        sitCooldown = waterAnimal.tickCount + (30 * 20 + waterAnimal.random.nextInt(15) * 20)
    }

    override fun canUse(): Boolean {
        if (sitCooldown > this.waterAnimal.tickCount ||
            waterAnimal.level().getNearestPlayer(waterAnimal, 32.0) == null
        ) {
            return false
        }
        return this.waterAnimal.getRandom().nextInt(40) == 0
    }

    override fun start() {
        sitTime = (5 * 20 + waterAnimal.random.nextInt(10) * 30)
        sitCooldown = waterAnimal.tickCount + (30 * 20 + waterAnimal.random.nextInt(15) * 20)
        waterAnimal.startSitting()
    }

    override fun stop() {
        waterAnimal.stopSitting()
    }

    override fun canContinueToUse(): Boolean {
        return sitTime > 0 && waterAnimal.isInWaterOrBubble
    }

    override fun tick() {
        sitTime--
        waterAnimal.deltaMovement = waterAnimal.deltaMovement.subtract(0.0, 0.01, 0.0)
        waterAnimal.navigation.stop()
    }
}