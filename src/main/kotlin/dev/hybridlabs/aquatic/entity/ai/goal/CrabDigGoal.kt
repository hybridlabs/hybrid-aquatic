package dev.hybridlabs.aquatic.entity.ai.goal

import dev.hybridlabs.aquatic.entity.critter.HybridAquaticCrabEntity
import dev.hybridlabs.aquatic.tag.HybridAquaticBlockTags
import net.minecraft.block.BlockState
import net.minecraft.entity.ai.goal.Goal
import net.minecraft.particle.ParticleTypes
import net.minecraft.server.world.ServerWorld
import net.minecraft.world.World
import java.util.*

class CrabDigGoal(
    private val crab: HybridAquaticCrabEntity,
    private val chance: Double,
) : Goal() {
    private val random: Random = Random()
    private val world: World = crab.world

    private lateinit var diggableBlock: BlockState
    private var diggingTimer: Int = 0

    override fun canStart(): Boolean {
        diggableBlock = world.getBlockState(crab.blockPos.down())
        return !crab.isTouchingWater && crab.diggingCooldown == 0 && random.nextDouble() <= chance &&
                diggableBlock.isIn(HybridAquaticBlockTags.CRAB_DIGGABLE_BLOCKS)
    }

    override fun start() {
        diggingTimer = 0
        crab.isDigging = true
        crab.navigation.stop()
        super.start()
    }

    override fun tick() {
        if (!crab.isTouchingWater) {
            diggingTimer++

            if (diggingTimer <= 20) {
                val x = crab.x
                val y = crab.y
                val z = crab.z
                val particleX = x + random.nextDouble() * 0.5 - 0.25
                val particleY = y + 0.5
                val particleZ = z + random.nextDouble() * 0.5 - 0.25

                (world as ServerWorld).spawnParticles(ParticleTypes.POOF, particleX, particleY, particleZ, 1, 0.0, 0.0, 0.0, 0.0)
            }
        }

        super.tick()
    }

    override fun shouldContinue(): Boolean {
        val blockUnderCrab = world.getBlockState(crab.blockPos.down())
        return !crab.isTouchingWater && blockUnderCrab.isIn(HybridAquaticBlockTags.CRAB_DIGGABLE_BLOCKS) &&
                crab.diggingCooldown == 0 && diggingTimer < 100
    }

    override fun stop() {
        diggingTimer = 0
        crab.isDigging = false
        crab.diggingCooldown = random.nextInt(2400) + 1200 // 2-5 minutes in ticks
        super.stop()
    }
}
