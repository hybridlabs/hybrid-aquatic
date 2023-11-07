package dev.hybridlabs.aquatic.entity.ai.goal

import dev.hybridlabs.aquatic.entity.critter.HybridAquaticCrabEntity
import net.minecraft.entity.ai.goal.Goal
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.random.Random

class CrabDiggingItemGoal(
    private val crab: HybridAquaticCrabEntity,
    private val chance: Double,
    private val radius: Int
) : Goal() {
    val random: Random = crab.random

    private var diggableBlock: BlockPos? = null
    private var diggingTimer: Int = 0

    override fun canStart(): Boolean {
        if (crab.diggingCooldown == 0 && random.nextDouble() <= chance) {

            diggableBlock
        }

        return diggableBlock != null
    }

    override fun start() {
        diggingTimer = 0

        super.start()
    }

    override fun tick() {
        super.tick()
    }

    override fun shouldContinue(): Boolean {
        return super.shouldContinue()
    }

    override fun stop() {
        super.stop()
    }
}