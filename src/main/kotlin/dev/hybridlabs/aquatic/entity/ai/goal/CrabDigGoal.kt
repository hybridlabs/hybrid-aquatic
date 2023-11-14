package dev.hybridlabs.aquatic.entity.ai.goal

import dev.hybridlabs.aquatic.entity.critter.HybridAquaticCrabEntity
import dev.hybridlabs.aquatic.loot.HybridAquaticLootTables
import dev.hybridlabs.aquatic.tag.HybridAquaticBlockTags
import net.minecraft.block.BlockState
import net.minecraft.entity.ItemEntity
import net.minecraft.entity.ai.goal.Goal
import net.minecraft.loot.context.LootContextParameterSet
import net.minecraft.loot.context.LootContextParameters
import net.minecraft.loot.context.LootContextTypes
import net.minecraft.particle.BlockStateParticleEffect
import net.minecraft.particle.ParticleTypes
import net.minecraft.server.world.ServerWorld
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
        if (crab.diggingCooldown == 0) {
            if (random.nextDouble() <= chance && diggableBlock.isIn(HybridAquaticBlockTags.CRAB_DIGGABLE_BLOCKS)) {
                return true
            }

            crab.diggingCooldown = 300
        }
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

        world.addParticle(BlockStateParticleEffect(ParticleTypes.BLOCK, diggableBlock), crab.x, crab.y, crab.z, random.nextDouble() * 0.2 - 0.1, 0.1, random.nextDouble() * 0.2 - 0.1)

        if(diggingTimer > 40) {
            if(diggableBlock.isIn(HybridAquaticBlockTags.CRAB_DIGGABLE_TREASURE_BLOCKS)) {
                val lootParameters = LootContextParameterSet.Builder(world as ServerWorld)
                    .add(LootContextParameters.ORIGIN, crab.pos)
                    .add(LootContextParameters.THIS_ENTITY, crab)
                    .build(LootContextTypes.GENERIC)
                val lootTable = world.server.lootManager?.getLootTable(HybridAquaticLootTables.CRAB_DIGGING_TREASURE_ID)
                if (lootTable != null) {
                    val genLoot = lootTable.generateLoot(lootParameters)
                    genLoot.forEach { itemInList ->
                        val item = ItemEntity(
                            world, crab.x, crab.y, crab.z, itemInList,
                            random.nextDouble() * 0.2 - 0.1,
                            0.1,
                            random.nextDouble() * 0.2 - 0.1
                        )
                        world.spawnEntity(item)
                    }
                }
            }

            diggingTimer = 100
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