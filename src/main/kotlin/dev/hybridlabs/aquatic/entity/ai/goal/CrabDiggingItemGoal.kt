package dev.hybridlabs.aquatic.entity.ai.goal

import dev.hybridlabs.aquatic.entity.critter.HybridAquaticCrabEntity
import net.minecraft.entity.ai.goal.Goal
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.random.Random

class CrabDiggingItemGoal(
    private val crab: HybridAquaticCrabEntity,
    private val chance: Double,
    private val radius: Int,
    private val speed: Double
) : Goal() {
    val random: Random = crab.random

    private var diggableBlock: BlockPos = BlockPos.ORIGIN
    private var diggingTimer: Int = 0

    override fun canStart(): Boolean {
        return crab.diggingCooldown == 0 && random.nextDouble() <= chance && findNearestDiggableBlock()
    }

    override fun start() {
        diggingTimer = 0
        crab.navigation.startMovingTo(diggableBlock.x + 0.5, diggableBlock.y + 0.5, diggableBlock.z + 0.5, speed)

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

    private fun findNearestDiggableBlock(): Boolean {

        return false
    }
}