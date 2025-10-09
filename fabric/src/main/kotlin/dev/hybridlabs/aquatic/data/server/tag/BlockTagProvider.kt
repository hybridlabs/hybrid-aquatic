package dev.hybridlabs.aquatic.data.server.tag

import dev.hybridlabs.aquatic.block.HybridAquaticBlocks
import dev.hybridlabs.aquatic.block.PlushieBlock
import dev.hybridlabs.aquatic.block.wood.HybridAquaticWoodBlocks
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

        getOrCreateTagBuilder(HybridAquaticBlockTags.CLOWNFISH_ANEMONES)
            .add(HybridAquaticBlocks.ANEMONE.get())
            .add(HybridAquaticBlocks.GIANT_GREEN_ANEMONE.get())

        getOrCreateTagBuilder(HybridAquaticBlockTags.KELPS)
            .add(HybridAquaticBlocks.BULL_KELP.get())
            .add(HybridAquaticBlocks.BULL_KELP_PLANT.get())
            .add(HybridAquaticBlocks.SARGASSUM.get())
            .add(HybridAquaticBlocks.SARGASSUM_PLANT.get())
            .add(Blocks.KELP)
            .add(Blocks.KELP_PLANT)

        getOrCreateTagBuilder(BlockTags.WOODEN_FENCES)
            .add(HybridAquaticWoodBlocks.DRIFTWOOD_FENCE.get())

        getOrCreateTagBuilder(BlockTags.FENCE_GATES)
            .add(HybridAquaticWoodBlocks.DRIFTWOOD_FENCE_GATE.get())

        getOrCreateTagBuilder(BlockTags.LOGS_THAT_BURN)
            .add(HybridAquaticWoodBlocks.DRIFTWOOD_LOG.get())
            .add(HybridAquaticWoodBlocks.DRIFTWOOD_WOOD.get())
            .add(HybridAquaticWoodBlocks.STRIPPED_DRIFTWOOD_LOG.get())
            .add(HybridAquaticWoodBlocks.STRIPPED_DRIFTWOOD_WOOD.get())

        getOrCreateTagBuilder(BlockTags.UNDERWATER_BONEMEALS)
            .add(HybridAquaticBlocks.RED_ALGAE.get())
            .add(HybridAquaticBlocks.SEA_LETTUCE.get())

        getOrCreateTagBuilder(BlockTags.CORAL_BLOCKS)
            .add(HybridAquaticBlocks.BUTTON_CORAL_BLOCK.get())
            .add(HybridAquaticBlocks.ROSE_CORAL_BLOCK.get())
            .add(HybridAquaticBlocks.LEAF_CORAL_BLOCK.get())
            .add(HybridAquaticBlocks.SUN_CORAL_BLOCK.get())
            .add(HybridAquaticBlocks.LOPHELIA_CORAL_BLOCK.get())
            .add(HybridAquaticBlocks.THORN_CORAL_BLOCK.get())

        getOrCreateTagBuilder(BlockTags.CORALS)
            .add(HybridAquaticBlocks.ROSE_CORAL.get())
            .add(HybridAquaticBlocks.LEAF_CORAL.get())
            .add(HybridAquaticBlocks.BUTTON_CORAL.get())
            .add(HybridAquaticBlocks.SUN_CORAL.get())
            .add(HybridAquaticBlocks.LOPHELIA_CORAL.get())
            .add(HybridAquaticBlocks.THORN_CORAL.get())

        getOrCreateTagBuilder(BlockTags.CORAL_PLANTS)
            .add(HybridAquaticBlocks.ROSE_CORAL.get())
            .add(HybridAquaticBlocks.LEAF_CORAL.get())
            .add(HybridAquaticBlocks.BUTTON_CORAL.get())
            .add(HybridAquaticBlocks.SUN_CORAL.get())
            .add(HybridAquaticBlocks.THORN_CORAL.get())
            .add(HybridAquaticBlocks.LOPHELIA_CORAL.get())
            .add(HybridAquaticBlocks.RED_ALGAE.get())
            .add(HybridAquaticBlocks.SEA_LETTUCE.get())

        getOrCreateTagBuilder(BlockTags.WALL_CORALS)
            .add(HybridAquaticBlocks.ROSE_CORAL_WALL_FAN.get())
            .add(HybridAquaticBlocks.LEAF_CORAL_WALL_FAN.get())
            .add(HybridAquaticBlocks.BUTTON_CORAL_WALL_FAN.get())
            .add(HybridAquaticBlocks.SUN_CORAL_WALL_FAN.get())
            .add(HybridAquaticBlocks.LOPHELIA_CORAL_WALL_FAN.get())
            .add(HybridAquaticBlocks.THORN_CORAL_WALL_FAN.get())

        getOrCreateTagBuilder(BlockTags.MINEABLE_WITH_PICKAXE)
            .add(HybridAquaticBlocks.BUTTON_CORAL_BLOCK.get())
            .add(HybridAquaticBlocks.DEAD_BUTTON_CORAL_BLOCK.get())
            .add(HybridAquaticBlocks.ROSE_CORAL_BLOCK.get())
            .add(HybridAquaticBlocks.DEAD_ROSE_CORAL_BLOCK.get())
            .add(HybridAquaticBlocks.LEAF_CORAL_BLOCK.get())
            .add(HybridAquaticBlocks.DEAD_LEAF_CORAL_BLOCK.get())
            .add(HybridAquaticBlocks.SUN_CORAL_BLOCK.get())
            .add(HybridAquaticBlocks.DEAD_SUN_CORAL_BLOCK.get())
            .add(HybridAquaticBlocks.LOPHELIA_CORAL_BLOCK.get())
            .add(HybridAquaticBlocks.DEAD_LOPHELIA_CORAL_BLOCK.get())
            .add(HybridAquaticBlocks.THORN_CORAL_BLOCK.get())
            .add(HybridAquaticBlocks.DEAD_THORN_CORAL_BLOCK.get())
            .add(HybridAquaticBlocks.PEARL_BLOCK.get())
            .add(HybridAquaticBlocks.BLACK_PEARL_BLOCK.get())
            .add(HybridAquaticBlocks.THERMAL_VENT.get())
            .add(HybridAquaticBlocks.GIANT_CLAM.get())

        getOrCreateTagBuilder(BlockTags.MINEABLE_WITH_AXE)
            .add(HybridAquaticBlocks.RAFT.get())
            .add(HybridAquaticBlocks.BUOY.get())
            .add(HybridAquaticBlocks.CRAB_POT.get())
            .add(HybridAquaticBlocks.HYBRID_CRATE.get())
            .add(HybridAquaticBlocks.OAK_CRATE.get())
            .add(HybridAquaticBlocks.SPRUCE_CRATE.get())
            .add(HybridAquaticBlocks.BIRCH_CRATE.get())
            .add(HybridAquaticBlocks.DARK_OAK_CRATE.get())
            .add(HybridAquaticBlocks.MANGROVE_CRATE.get())
            .add(HybridAquaticBlocks.JUNGLE_CRATE.get())
            .add(HybridAquaticBlocks.ACACIA_CRATE.get())
            .add(HybridAquaticBlocks.CHERRY_CRATE.get())

        getOrCreateTagBuilder(BlockTags.LOGS)
            .add(HybridAquaticWoodBlocks.DRIFTWOOD_LOG.get())
            .add(HybridAquaticWoodBlocks.STRIPPED_DRIFTWOOD_LOG.get())

        getOrCreateTagBuilder(BlockTags.WOODEN_SLABS)
            .add(HybridAquaticWoodBlocks.DRIFTWOOD_SLAB.get())

        getOrCreateTagBuilder(BlockTags.WOODEN_STAIRS)
            .add(HybridAquaticWoodBlocks.DRIFTWOOD_STAIRS.get())

        getOrCreateTagBuilder(BlockTags.WOODEN_DOORS)
            .add(HybridAquaticWoodBlocks.DRIFTWOOD_DOOR.get())

        getOrCreateTagBuilder(BlockTags.WOODEN_FENCES)
            .add(HybridAquaticWoodBlocks.DRIFTWOOD_FENCE.get())

        getOrCreateTagBuilder(BlockTags.WOODEN_TRAPDOORS)
            .add(HybridAquaticWoodBlocks.DRIFTWOOD_TRAPDOOR.get())

        getOrCreateTagBuilder(BlockTags.WOODEN_BUTTONS)
            .add(HybridAquaticWoodBlocks.DRIFTWOOD_BUTTON.get())

        getOrCreateTagBuilder(BlockTags.WOODEN_PRESSURE_PLATES)
            .add(HybridAquaticWoodBlocks.DRIFTWOOD_PRESSURE_PLATE.get())

        getOrCreateTagBuilder(BlockTags.FENCE_GATES)
            .add(HybridAquaticWoodBlocks.DRIFTWOOD_FENCE_GATE.get())

        getOrCreateTagBuilder(BlockTags.PLANKS)
            .add(HybridAquaticWoodBlocks.DRIFTWOOD_PLANKS.get())

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
