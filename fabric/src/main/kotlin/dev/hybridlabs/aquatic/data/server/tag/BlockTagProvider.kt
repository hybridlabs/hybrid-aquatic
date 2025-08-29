package dev.hybridlabs.aquatic.data.server.tag

import dev.hybridlabs.aquatic.block.HybridAquaticBlocks
import dev.hybridlabs.aquatic.block.PlushieBlock
import dev.hybridlabs.aquatic.data.HybridAquaticDataGenerator.filterHybridAquatic
import dev.hybridlabs.aquatic.tag.HybridAquaticBlockTags
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider
import net.minecraft.core.HolderLookup
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.tags.BlockTags
import net.minecraft.world.level.block.Blocks
import java.util.concurrent.CompletableFuture

class BlockTagProvider(output: FabricDataOutput, registriesFuture: CompletableFuture<HolderLookup.Provider>) :
    FabricTagProvider.BlockTagProvider(output, registriesFuture) {
    override fun addTags(arg: HolderLookup.Provider) {
        // misc
        getOrCreateTagBuilder(HybridAquaticBlockTags.ANEMONES_GENERATE_IN)
            .add(Blocks.WATER)

        getOrCreateTagBuilder(HybridAquaticBlockTags.GIANT_CLAM_GENERATE_IN)
            .add(Blocks.WATER)

        getOrCreateTagBuilder(HybridAquaticBlockTags.TUBE_SPONGE_GENERATE_IN)
            .add(Blocks.WATER)

        getOrCreateTagBuilder(HybridAquaticBlockTags.MESSAGE_IN_A_BOTTLE_SPAWNS_IN)
            .add(Blocks.WATER)

        getOrCreateTagBuilder(BlockTags.WOODEN_FENCES)
            .add(HybridAquaticBlocks.DRIFTWOOD_FENCE.get())

        getOrCreateTagBuilder(BlockTags.FENCE_GATES)
            .add(HybridAquaticBlocks.DRIFTWOOD_FENCE_GATE.get())

        getOrCreateTagBuilder(BlockTags.LOGS_THAT_BURN)
            .add(HybridAquaticBlocks.DRIFTWOOD_LOG.get())
            .add(HybridAquaticBlocks.DRIFTWOOD_WOOD.get())
            .add(HybridAquaticBlocks.STRIPPED_DRIFTWOOD_LOG.get())
            .add(HybridAquaticBlocks.STRIPPED_DRIFTWOOD_WOOD.get())

        getOrCreateTagBuilder(BlockTags.UNDERWATER_BONEMEALS)
            .add(HybridAquaticBlocks.RED_ALGAE.get())
            .add(HybridAquaticBlocks.SEA_LETTUCE.get())

        getOrCreateTagBuilder(BlockTags.CORAL_BLOCKS)
            .add(HybridAquaticBlocks.BUTTON_CORAL_BLOCK.get())
            .add(HybridAquaticBlocks.SUN_CORAL_BLOCK.get())
            .add(HybridAquaticBlocks.LOPHELIA_CORAL_BLOCK.get())
            .add(HybridAquaticBlocks.THORN_CORAL_BLOCK.get())

        getOrCreateTagBuilder(BlockTags.CORALS)
            .add(HybridAquaticBlocks.BUTTON_CORAL.get())
            .add(HybridAquaticBlocks.SUN_CORAL.get())
            .add(HybridAquaticBlocks.LOPHELIA_CORAL.get())
            .add(HybridAquaticBlocks.THORN_CORAL.get())

        getOrCreateTagBuilder(BlockTags.CORAL_PLANTS)
            .add(HybridAquaticBlocks.BUTTON_CORAL.get())
            .add(HybridAquaticBlocks.SUN_CORAL.get())
            .add(HybridAquaticBlocks.THORN_CORAL.get())
            .add(HybridAquaticBlocks.LOPHELIA_CORAL.get())
            .add(HybridAquaticBlocks.RED_ALGAE.get())
            .add(HybridAquaticBlocks.SEA_LETTUCE.get())

        getOrCreateTagBuilder(BlockTags.WALL_CORALS)
            .add(HybridAquaticBlocks.BUTTON_CORAL_WALL_FAN.get())
            .add(HybridAquaticBlocks.SUN_CORAL_WALL_FAN.get())
            .add(HybridAquaticBlocks.LOPHELIA_CORAL_WALL_FAN.get())
            .add(HybridAquaticBlocks.THORN_CORAL_WALL_FAN.get())

        getOrCreateTagBuilder(BlockTags.MINEABLE_WITH_PICKAXE)
            .add(HybridAquaticBlocks.BUTTON_CORAL_BLOCK.get())
            .add(HybridAquaticBlocks.DEAD_BUTTON_CORAL_BLOCK.get())
            .add(HybridAquaticBlocks.SUN_CORAL_BLOCK.get())
            .add(HybridAquaticBlocks.DEAD_SUN_CORAL_BLOCK.get())
            .add(HybridAquaticBlocks.LOPHELIA_CORAL_BLOCK.get())
            .add(HybridAquaticBlocks.DEAD_LOPHELIA_CORAL_BLOCK.get())
            .add(HybridAquaticBlocks.THORN_CORAL_BLOCK.get())
            .add(HybridAquaticBlocks.DEAD_THORN_CORAL_BLOCK.get())
            .add(HybridAquaticBlocks.THERMAL_VENT.get())
            .add(HybridAquaticBlocks.GIANT_CLAM.get())

        getOrCreateTagBuilder(BlockTags.LOGS)
            .add(HybridAquaticBlocks.DRIFTWOOD_LOG.get())
            .add(HybridAquaticBlocks.STRIPPED_DRIFTWOOD_LOG.get())

        getOrCreateTagBuilder(BlockTags.UNDERWATER_BONEMEALS)
            .add(HybridAquaticBlocks.RED_ALGAE.get())
            .add(HybridAquaticBlocks.SEA_LETTUCE.get())

        // plushies
        BuiltInRegistries.BLOCK
            .filter(filterHybridAquatic(BuiltInRegistries.BLOCK))
            .forEach { block ->
                // plushies
                if (block is PlushieBlock) {
                    getOrCreateTagBuilder(HybridAquaticBlockTags.PLUSHIES).add(block)
                }
            }
    }
}
