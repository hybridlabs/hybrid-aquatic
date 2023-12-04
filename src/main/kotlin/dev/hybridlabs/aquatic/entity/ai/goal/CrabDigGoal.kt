package dev.hybridlabs.aquatic.entity.ai.goal

import dev.hybridlabs.aquatic.entity.critter.HybridAquaticCrabEntity
import dev.hybridlabs.aquatic.loot.HybridAquaticLootTables
import dev.hybridlabs.aquatic.tag.HybridAquaticBlockTags
import net.minecraft.block.BlockState
import net.minecraft.entity.Entity
import net.minecraft.entity.ItemEntity
import net.minecraft.entity.ai.goal.Goal
import net.minecraft.item.ItemStack
import net.minecraft.loot.context.LootContextParameterSet
import net.minecraft.loot.context.LootContextParameters
import net.minecraft.loot.context.LootContextTypes
import net.minecraft.particle.ParticleTypes
import net.minecraft.server.world.ServerWorld
import net.minecraft.util.math.Vec3d
import net.minecraft.world.World
import java.util.*

class CrabDigGoal(
    private val crab: HybridAquaticCrabEntity,
    private val chance: Double,
) : Goal() {
    private val random: Random = Random()

    private lateinit var diggableBlock: BlockState
    private var diggingTimer: Int = 0

    override fun canStart(): Boolean {
        diggableBlock = crab.world.getBlockState(crab.blockPos.down())
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
            val x = crab.x
            val y = crab.y
            val z = crab.z

            if (diggingTimer <= 20) {
                (crab.world as ServerWorld).spawnParticles(ParticleTypes.POOF, x, y, z, 1, 0.0, 0.0, 0.0, 0.0)
            } else {
                diggingTimer = 100
                val lootContextParameterSet = LootContextParameterSet.Builder(crab.world as ServerWorld)
                    .add(LootContextParameters.BLOCK_STATE, diggableBlock)
                    .add(LootContextParameters.ORIGIN, crab.pos)
                    .add(LootContextParameters.TOOL, ItemStack.EMPTY)
                    .add(LootContextParameters.THIS_ENTITY, crab)
                    .build(LootContextTypes.BLOCK)
                val lootTable = crab.world.server?.lootManager?.getLootTable(HybridAquaticLootTables.CRAB_DIGGING_TREASURE_ID)
                val loot = lootTable?.generateLoot(lootContextParameterSet)

                if (loot != null) {
                    for (lootItem in loot) {
                        val itemEntity = ItemEntity(crab.world, x, y, z, lootItem)
                        crab.world.spawnEntity(itemEntity)
                    }
                }
            }
        }

        super.tick()
    }

    override fun shouldContinue(): Boolean {
        val blockUnderCrab = crab.world.getBlockState(crab.blockPos.down())
        return !crab.isTouchingWater && blockUnderCrab.isIn(HybridAquaticBlockTags.CRAB_DIGGABLE_BLOCKS) &&
                crab.diggingCooldown == 0 && diggingTimer < 100
    }

    override fun stop() {
        diggingTimer = 0
        crab.isDigging = false
        crab.diggingCooldown = random.nextInt(2400) + 1200 // 1-3 minutes in ticks
        super.stop()
    }
}
