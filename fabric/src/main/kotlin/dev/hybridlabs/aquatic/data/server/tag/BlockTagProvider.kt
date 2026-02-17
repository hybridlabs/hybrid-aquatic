package dev.hybridlabs.aquatic.data.server.tag

import dev.hybridlabs.aquatic.block.HybridAquaticBlocks
import dev.hybridlabs.aquatic.block.PlushieBlock
import dev.hybridlabs.aquatic.block.wood.HybridAquaticPlatformBlocks
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
        getOrCreateTagBuilder(HybridAquaticBlockTags.ANEMONES)
            .add(HybridAquaticBlocks.ANEMONE.get())
            .add(HybridAquaticBlocks.GIANT_GREEN_ANEMONE.get())
            .add(HybridAquaticBlocks.STRAWBERRY_ANEMONE.get())

        //#region Deep Corals
        getOrCreateTagBuilder(HybridAquaticBlockTags.DEEP_CORAL_BLOCKS)
            .add(HybridAquaticBlocks.LOPHELIA_CORAL_BLOCK.get())
            .add(HybridAquaticBlocks.THORN_CORAL_BLOCK.get())

        getOrCreateTagBuilder(HybridAquaticBlockTags.DEEP_CORAL_PLANTS)
            .add(HybridAquaticBlocks.LOPHELIA_CORAL.get())
            .add(HybridAquaticBlocks.THORN_CORAL.get())

        getOrCreateTagBuilder(HybridAquaticBlockTags.DEEP_CORALS)
            .forceAddTag(HybridAquaticBlockTags.DEEP_CORAL_PLANTS)
            .add(HybridAquaticBlocks.LOPHELIA_CORAL_FAN.get())
            .add(HybridAquaticBlocks.THORN_CORAL_FAN.get())

        getOrCreateTagBuilder(HybridAquaticBlockTags.DEEP_WALL_CORALS)
            .add(HybridAquaticBlocks.LOPHELIA_CORAL_WALL_FAN.get())
            .add(HybridAquaticBlocks.THORN_CORAL_WALL_FAN.get())
        //#endregion

        //#region Bleached Corals
        getOrCreateTagBuilder(HybridAquaticBlockTags.BLEACHED_CORAL_BLOCKS)
            .add(HybridAquaticBlocks.BLEACHED_LOPHELIA_CORAL_BLOCK.get())
            .add(HybridAquaticBlocks.BLEACHED_ROSE_CORAL_BLOCK.get())
            .add(HybridAquaticBlocks.BLEACHED_BUTTON_CORAL_BLOCK.get())
            .add(HybridAquaticBlocks.BLEACHED_SUN_CORAL_BLOCK.get())
            .add(HybridAquaticBlocks.BLEACHED_LEAF_CORAL_BLOCK.get())
            .add(HybridAquaticBlocks.BLEACHED_THORN_CORAL_BLOCK.get())
            .add(HybridAquaticBlocks.BLEACHED_FIRE_CORAL_BLOCK.get())
            .add(HybridAquaticBlocks.BLEACHED_TUBE_CORAL_BLOCK.get())
            .add(HybridAquaticBlocks.BLEACHED_HORN_CORAL_BLOCK.get())
            .add(HybridAquaticBlocks.BLEACHED_BUBBLE_CORAL_BLOCK.get())
            .add(HybridAquaticBlocks.BLEACHED_BRAIN_CORAL_BLOCK.get())

        getOrCreateTagBuilder(HybridAquaticBlockTags.BLEACHED_CORAL_PLANTS)
            .add(HybridAquaticBlocks.BLEACHED_LOPHELIA_CORAL.get())
            .add(HybridAquaticBlocks.BLEACHED_ROSE_CORAL.get())
            .add(HybridAquaticBlocks.BLEACHED_BUTTON_CORAL.get())
            .add(HybridAquaticBlocks.BLEACHED_SUN_CORAL.get())
            .add(HybridAquaticBlocks.BLEACHED_LEAF_CORAL.get())
            .add(HybridAquaticBlocks.BLEACHED_THORN_CORAL.get())
            .add(HybridAquaticBlocks.BLEACHED_FIRE_CORAL.get())
            .add(HybridAquaticBlocks.BLEACHED_TUBE_CORAL.get())
            .add(HybridAquaticBlocks.BLEACHED_HORN_CORAL.get())
            .add(HybridAquaticBlocks.BLEACHED_BUBBLE_CORAL.get())
            .add(HybridAquaticBlocks.BLEACHED_BRAIN_CORAL.get())

        getOrCreateTagBuilder(HybridAquaticBlockTags.BLEACHED_CORALS)
            .forceAddTag(HybridAquaticBlockTags.BLEACHED_CORAL_PLANTS)
            .add(HybridAquaticBlocks.BLEACHED_LOPHELIA_CORAL_FAN.get())
            .add(HybridAquaticBlocks.BLEACHED_ROSE_CORAL_FAN.get())
            .add(HybridAquaticBlocks.BLEACHED_BUTTON_CORAL_FAN.get())
            .add(HybridAquaticBlocks.BLEACHED_SUN_CORAL_FAN.get())
            .add(HybridAquaticBlocks.BLEACHED_LEAF_CORAL_FAN.get())
            .add(HybridAquaticBlocks.BLEACHED_THORN_CORAL_FAN.get())
            .add(HybridAquaticBlocks.BLEACHED_FIRE_CORAL_FAN.get())
            .add(HybridAquaticBlocks.BLEACHED_TUBE_CORAL_FAN.get())
            .add(HybridAquaticBlocks.BLEACHED_HORN_CORAL_FAN.get())
            .add(HybridAquaticBlocks.BLEACHED_BUBBLE_CORAL_FAN.get())
            .add(HybridAquaticBlocks.BLEACHED_BRAIN_CORAL_FAN.get())

        getOrCreateTagBuilder(HybridAquaticBlockTags.BLEACHED_WALL_CORALS)
            .add(HybridAquaticBlocks.BLEACHED_LOPHELIA_CORAL_WALL_FAN.get())
            .add(HybridAquaticBlocks.BLEACHED_ROSE_CORAL_WALL_FAN.get())
            .add(HybridAquaticBlocks.BLEACHED_BUTTON_CORAL_WALL_FAN.get())
            .add(HybridAquaticBlocks.BLEACHED_SUN_CORAL_WALL_FAN.get())
            .add(HybridAquaticBlocks.BLEACHED_LEAF_CORAL_WALL_FAN.get())
            .add(HybridAquaticBlocks.BLEACHED_THORN_CORAL_WALL_FAN.get())
            .add(HybridAquaticBlocks.BLEACHED_FIRE_CORAL_WALL_FAN.get())
            .add(HybridAquaticBlocks.BLEACHED_TUBE_CORAL_WALL_FAN.get())
            .add(HybridAquaticBlocks.BLEACHED_HORN_CORAL_WALL_FAN.get())
            .add(HybridAquaticBlocks.BLEACHED_BUBBLE_CORAL_WALL_FAN.get())
            .add(HybridAquaticBlocks.BLEACHED_BRAIN_CORAL_WALL_FAN.get())
//#endregion

        getOrCreateTagBuilder(HybridAquaticBlockTags.KELP)
            .add(HybridAquaticBlocks.BULL_KELP.get())
            .add(HybridAquaticBlocks.BULL_KELP_PLANT.get())
            .add(HybridAquaticBlocks.SARGASSUM.get())
            .add(HybridAquaticBlocks.SARGASSUM_PLANT.get())
            .add(Blocks.KELP)
            .add(Blocks.KELP_PLANT)

        getOrCreateTagBuilder(BlockTags.WOODEN_FENCES)
            .add(HybridAquaticPlatformBlocks.DRIFTWOOD_FENCE.get())

        getOrCreateTagBuilder(BlockTags.FENCE_GATES)
            .add(HybridAquaticPlatformBlocks.DRIFTWOOD_FENCE_GATE.get())

        getOrCreateTagBuilder(BlockTags.LOGS_THAT_BURN)
            .add(HybridAquaticPlatformBlocks.DRIFTWOOD_LOG.get())
            .add(HybridAquaticPlatformBlocks.DRIFTWOOD_WOOD.get())
            .add(HybridAquaticPlatformBlocks.STRIPPED_DRIFTWOOD_LOG.get())
            .add(HybridAquaticPlatformBlocks.STRIPPED_DRIFTWOOD_WOOD.get())

        getOrCreateTagBuilder(BlockTags.UNDERWATER_BONEMEALS)
            .add(HybridAquaticBlocks.SHORT_RED_ALGAE.get())
            .add(HybridAquaticBlocks.RED_ALGAE.get())
            .add(HybridAquaticBlocks.SEA_LETTUCE.get())

        getOrCreateTagBuilder(BlockTags.CORAL_BLOCKS)
            .add(HybridAquaticBlocks.BUTTON_CORAL_BLOCK.get())
            .add(HybridAquaticBlocks.ROSE_CORAL_BLOCK.get())
            .add(HybridAquaticBlocks.LEAF_CORAL_BLOCK.get())
            .add(HybridAquaticBlocks.SUN_CORAL_BLOCK.get())

        getOrCreateTagBuilder(BlockTags.CORALS)
            .add(HybridAquaticBlocks.ROSE_CORAL.get())
            .add(HybridAquaticBlocks.LEAF_CORAL.get())
            .add(HybridAquaticBlocks.BUTTON_CORAL.get())
            .add(HybridAquaticBlocks.SUN_CORAL.get())

        getOrCreateTagBuilder(BlockTags.CORAL_PLANTS)
            .add(HybridAquaticBlocks.ROSE_CORAL.get())
            .add(HybridAquaticBlocks.LEAF_CORAL.get())
            .add(HybridAquaticBlocks.BUTTON_CORAL.get())
            .add(HybridAquaticBlocks.SUN_CORAL.get())

        getOrCreateTagBuilder(BlockTags.WALL_CORALS)
            .add(HybridAquaticBlocks.ROSE_CORAL_WALL_FAN.get())
            .add(HybridAquaticBlocks.LEAF_CORAL_WALL_FAN.get())
            .add(HybridAquaticBlocks.BUTTON_CORAL_WALL_FAN.get())
            .add(HybridAquaticBlocks.SUN_CORAL_WALL_FAN.get())

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
            .add(HybridAquaticBlocks.CRYSTALLINE_SULFUR.get())
            .add(HybridAquaticBlocks.GIANT_CLAM.get())
            .add(HybridAquaticBlocks.OYSTER.get())
            .add(HybridAquaticBlocks.CORALSTONE.get())
            .add(HybridAquaticBlocks.SHORESTONE.get())
            .add(HybridAquaticBlocks.BARNACLE_SHORESTONE.get())

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
            .add(HybridAquaticBlocks.BAMBOO_CRATE.get())

        getOrCreateTagBuilder(BlockTags.MINEABLE_WITH_SHOVEL)
            .add(HybridAquaticBlocks.GRASSY_SAND.get())

        getOrCreateTagBuilder(BlockTags.LOGS)
            .add(HybridAquaticPlatformBlocks.DRIFTWOOD_LOG.get())
            .add(HybridAquaticPlatformBlocks.STRIPPED_DRIFTWOOD_LOG.get())

        getOrCreateTagBuilder(BlockTags.WOODEN_SLABS)
            .add(HybridAquaticPlatformBlocks.DRIFTWOOD_SLAB.get())

        getOrCreateTagBuilder(BlockTags.WOODEN_STAIRS)
            .add(HybridAquaticPlatformBlocks.DRIFTWOOD_STAIRS.get())

        getOrCreateTagBuilder(BlockTags.WOODEN_DOORS)
            .add(HybridAquaticPlatformBlocks.DRIFTWOOD_DOOR.get())

        getOrCreateTagBuilder(BlockTags.WOODEN_FENCES)
            .add(HybridAquaticPlatformBlocks.DRIFTWOOD_FENCE.get())

        getOrCreateTagBuilder(BlockTags.WOODEN_TRAPDOORS)
            .add(HybridAquaticPlatformBlocks.DRIFTWOOD_TRAPDOOR.get())

        getOrCreateTagBuilder(BlockTags.WOODEN_BUTTONS)
            .add(HybridAquaticPlatformBlocks.DRIFTWOOD_BUTTON.get())

        getOrCreateTagBuilder(BlockTags.WOODEN_PRESSURE_PLATES)
            .add(HybridAquaticPlatformBlocks.DRIFTWOOD_PRESSURE_PLATE.get())

        getOrCreateTagBuilder(BlockTags.FENCE_GATES)
            .add(HybridAquaticPlatformBlocks.DRIFTWOOD_FENCE_GATE.get())

        getOrCreateTagBuilder(BlockTags.PLANKS)
            .add(HybridAquaticPlatformBlocks.DRIFTWOOD_PLANKS.get())

        getOrCreateTagBuilder(HybridAquaticBlockTags.TIDE_POOL_REPLACEABLE)
            .add(Blocks.CLAY)
            .add(Blocks.GRAVEL)
            .add(Blocks.DIRT)
            .add(Blocks.SAND)
            .add(Blocks.GRASS_BLOCK)
            .add(Blocks.STONE)
            .add(Blocks.MOSSY_COBBLESTONE)
            .add(Blocks.TUFF)

        getOrCreateTagBuilder(HybridAquaticBlockTags.CORAL_MOUND_BLOCKS)
            .add(HybridAquaticBlocks.CORALSTONE.get())

        getOrCreateTagBuilder(HybridAquaticBlockTags.CORAL_MOUND_BASE_BLOCKS)
            .addTag(HybridAquaticBlockTags.CORAL_MOUND_BLOCKS)
            .add(HybridAquaticBlocks.SHORESTONE.get())
            .add(Blocks.SAND)

        getOrCreateTagBuilder(HybridAquaticBlockTags.MOUND_BLOCKS)
            .add(Blocks.STONE)
            .add(Blocks.DEAD_BUBBLE_CORAL_BLOCK)
            .add(Blocks.DEAD_FIRE_CORAL_BLOCK)
            .add(Blocks.DEAD_HORN_CORAL_BLOCK)
            .add(Blocks.DEAD_BRAIN_CORAL_BLOCK)
            .add(Blocks.DEAD_TUBE_CORAL_BLOCK)
            .add(HybridAquaticBlocks.DEAD_SUN_CORAL_BLOCK.get())
            .add(HybridAquaticBlocks.DEAD_BUTTON_CORAL_BLOCK.get())
            .add(HybridAquaticBlocks.DEAD_LEAF_CORAL_BLOCK.get())
            .add(HybridAquaticBlocks.DEAD_ROSE_CORAL_BLOCK.get())
            .add(HybridAquaticBlocks.DEAD_LOPHELIA_CORAL_BLOCK.get())
            .add(HybridAquaticBlocks.DEAD_THORN_CORAL_BLOCK.get())

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
