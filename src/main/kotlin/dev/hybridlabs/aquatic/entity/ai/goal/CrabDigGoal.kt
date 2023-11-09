package dev.hybridlabs.aquatic.entity.ai.goal

import dev.hybridlabs.aquatic.entity.critter.HybridAquaticCrabEntity
import dev.hybridlabs.aquatic.tag.HybridAquaticBlockTags
import net.minecraft.block.BlockState
import net.minecraft.entity.ItemEntity
import net.minecraft.entity.ai.goal.Goal
import net.minecraft.item.Items
import net.minecraft.util.math.random.Random
import net.minecraft.world.World

class CrabDigGoal(
    private val crab: HybridAquaticCrabEntity,
    private val chance: Double,
) : Goal() {
    val random: Random = crab.random
    val world: World = crab.world

    private lateinit var diggableBlock: BlockState
    private var diggingTimer: Int = 0

    override fun canStart(): Boolean {
        diggableBlock = world.getBlockState(crab.blockPos.down())
        if(crab.diggingCooldown == 0 && random.nextDouble() <= chance && diggableBlock.isIn(HybridAquaticBlockTags.CRAB_DIGGABLE_BLOCKS)) {
            return true
        }

        crab.diggingCooldown = 300
        return false
    }

    override fun start() {
        diggingTimer = 0
        crab.isDigging = true
        crab.getNavigation().stop()
        super.start()
    }

    override fun tick() {
        diggingTimer++

        if(diggingTimer > 20) {
            if(diggableBlock.isIn(HybridAquaticBlockTags.CRAB_DIGGABLE_TREASURE_BLOCKS)) {
                val item = ItemEntity(world, crab.x, crab.y, crab.z, Items.DIAMOND.defaultStack, random.nextDouble() * 0.2 - 0.1, 0.2, random.nextDouble() * 0.2 - 0.1)
                world.spawnEntity(item)
                diggingTimer = 100
            }
        }

        super.tick()
    }

    override fun shouldContinue(): Boolean {
        val blockUnderCrab = world.getBlockState(crab.blockPos.down())
        return blockUnderCrab.isIn(HybridAquaticBlockTags.CRAB_DIGGABLE_BLOCKS) && crab.diggingCooldown == 0 && diggingTimer < 100
    }

    override fun stop() {
        diggingTimer = 0
        crab.isDigging = false
        crab.diggingCooldown = random.nextBetween(600, 3000)
        super.stop()
    }
}