package dev.hybridlabs.aquatic.data.server.tag

import dev.hybridlabs.aquatic.block.HABlocks
import dev.hybridlabs.aquatic.block.HAPlatformBlocks
import dev.hybridlabs.aquatic.block.PlushieBlock
import dev.hybridlabs.aquatic.data.HybridAquaticDataGenerator.filterHybridAquatic
import dev.hybridlabs.aquatic.tag.HABlockTags
import java.util.concurrent.CompletableFuture
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider
import net.minecraft.core.HolderLookup
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.tags.BlockTags
import net.minecraft.world.level.block.Blocks

class BlockTagProvider(output: FabricDataOutput, registriesFuture: CompletableFuture<HolderLookup.Provider>) :
    FabricTagProvider.BlockTagProvider(output, registriesFuture) {
    override fun addTags(arg: HolderLookup.Provider) {
        // misc
        valueLookupBuilder(HABlockTags.ANEMONES)
            .add(HABlocks.ANEMONE.get())
            .add(HABlocks.GIANT_GREEN_ANEMONE.get())
            .add(HABlocks.STRAWBERRY_ANEMONE.get())

        valueLookupBuilder(HABlockTags.DETRITIVORE_EDIBLE)
            .add(HABlocks.MARINE_SNOW.get())
            .add(HABlocks.BONE_STAIRS.get())
            .add(HABlocks.BONE_SLAB.get())
            .add(HABlocks.BONE_FENCE.get())
            .add(HABlocks.BONE_WALL.get())
            .add(Blocks.BONE_BLOCK)

        valueLookupBuilder(HABlockTags.CORALLIVORE_EDIBLE)
            .forceAddTag(BlockTags.CORAL_BLOCKS)
            .forceAddTag(HABlockTags.DEEP_CORAL_BLOCKS)

        valueLookupBuilder(HABlockTags.ALGIVORE_EDIBLE)
            .add(HABlocks.GRASSY_SAND.get())
            .add(HABlocks.SEA_LETTUCE.get())
            .add(HABlocks.TALL_SEA_LETTUCE.get())
            .add(HABlocks.SHORT_RED_ALGAE.get())
            .add(HABlocks.RED_ALGAE.get())
            .add(HABlocks.TALL_RED_ALGAE.get())
            .add(Blocks.MOSS_BLOCK)
            .add(Blocks.MOSSY_COBBLESTONE)
            .add(Blocks.MOSSY_COBBLESTONE_STAIRS)
            .add(Blocks.MOSSY_COBBLESTONE_SLAB)
            .add(Blocks.MOSSY_COBBLESTONE_WALL)
            .add(Blocks.MOSSY_STONE_BRICKS)
            .add(Blocks.MOSSY_STONE_BRICK_STAIRS)
            .add(Blocks.MOSSY_STONE_BRICK_SLAB)
            .add(Blocks.MOSSY_STONE_BRICK_WALL)
            .add(Blocks.MOSS_CARPET)

        valueLookupBuilder(HABlockTags.HERBIVORE_EDIBLE)
            .add(HABlocks.GRASSY_SAND.get())
            .add(HABlocks.SEA_LETTUCE.get())
            .add(HABlocks.TALL_SEA_LETTUCE.get())
            .add(Blocks.SEAGRASS)
            .add(Blocks.TALL_SEAGRASS)

        //#region Deep Corals
        valueLookupBuilder(HABlockTags.DEEP_CORAL_BLOCKS)
            .add(HABlocks.LOPHELIA_CORAL_BLOCK.get())
            .add(HABlocks.THORN_CORAL_BLOCK.get())
            .add(HABlocks.ZIGZAG_CORAL_BLOCK.get())
            .add(HABlocks.BAMBOO_CORAL_BLOCK.get())

        valueLookupBuilder(HABlockTags.DEEP_CORAL_PLANTS)
            .add(HABlocks.LOPHELIA_CORAL.get())
            .add(HABlocks.THORN_CORAL.get())
            .add(HABlocks.ZIGZAG_CORAL.get())
            .add(HABlocks.BAMBOO_CORAL.get())

        valueLookupBuilder(HABlockTags.DEEP_CORALS)
            .forceAddTag(HABlockTags.DEEP_CORAL_PLANTS)
            .add(HABlocks.LOPHELIA_CORAL_FAN.get())
            .add(HABlocks.THORN_CORAL_FAN.get())
            .add(HABlocks.ZIGZAG_CORAL_FAN.get())
            .add(HABlocks.BAMBOO_CORAL_FAN.get())

        valueLookupBuilder(HABlockTags.DEEP_WALL_CORALS)
            .add(HABlocks.LOPHELIA_CORAL_WALL_FAN.get())
            .add(HABlocks.THORN_CORAL_WALL_FAN.get())
            .add(HABlocks.ZIGZAG_CORAL_WALL_FAN.get())
            .add(HABlocks.BAMBOO_CORAL_WALL_FAN.get())
        //#endregion

        //#region Bleached Corals
        valueLookupBuilder(HABlockTags.BLEACHED_CORAL_BLOCKS)
            .add(HABlocks.BLEACHED_LOPHELIA_CORAL_BLOCK.get())
            .add(HABlocks.BLEACHED_BAMBOO_CORAL_BLOCK.get())
            .add(HABlocks.BLEACHED_ZIGZAG_CORAL_BLOCK.get())
            .add(HABlocks.BLEACHED_ROSE_CORAL_BLOCK.get())
            .add(HABlocks.BLEACHED_BUTTON_CORAL_BLOCK.get())
            .add(HABlocks.BLEACHED_SUN_CORAL_BLOCK.get())
            .add(HABlocks.BLEACHED_LEAF_CORAL_BLOCK.get())
            .add(HABlocks.BLEACHED_THORN_CORAL_BLOCK.get())
            .add(HABlocks.BLEACHED_FIRE_CORAL_BLOCK.get())
            .add(HABlocks.BLEACHED_TUBE_CORAL_BLOCK.get())
            .add(HABlocks.BLEACHED_HORN_CORAL_BLOCK.get())
            .add(HABlocks.BLEACHED_BUBBLE_CORAL_BLOCK.get())
            .add(HABlocks.BLEACHED_BRAIN_CORAL_BLOCK.get())

        valueLookupBuilder(HABlockTags.BLEACHED_CORAL_PLANTS)
            .add(HABlocks.BLEACHED_LOPHELIA_CORAL.get())
            .add(HABlocks.BLEACHED_BAMBOO_CORAL.get())
            .add(HABlocks.BLEACHED_ZIGZAG_CORAL.get())
            .add(HABlocks.BLEACHED_ROSE_CORAL.get())
            .add(HABlocks.BLEACHED_BUTTON_CORAL.get())
            .add(HABlocks.BLEACHED_SUN_CORAL.get())
            .add(HABlocks.BLEACHED_LEAF_CORAL.get())
            .add(HABlocks.BLEACHED_THORN_CORAL.get())
            .add(HABlocks.BLEACHED_FIRE_CORAL.get())
            .add(HABlocks.BLEACHED_TUBE_CORAL.get())
            .add(HABlocks.BLEACHED_HORN_CORAL.get())
            .add(HABlocks.BLEACHED_BUBBLE_CORAL.get())
            .add(HABlocks.BLEACHED_BRAIN_CORAL.get())

        valueLookupBuilder(HABlockTags.BLEACHED_CORALS)
            .forceAddTag(HABlockTags.BLEACHED_CORAL_PLANTS)
            .add(HABlocks.BLEACHED_LOPHELIA_CORAL_FAN.get())
            .add(HABlocks.BLEACHED_BAMBOO_CORAL_FAN.get())
            .add(HABlocks.BLEACHED_ZIGZAG_CORAL_FAN.get())
            .add(HABlocks.BLEACHED_ROSE_CORAL_FAN.get())
            .add(HABlocks.BLEACHED_BUTTON_CORAL_FAN.get())
            .add(HABlocks.BLEACHED_SUN_CORAL_FAN.get())
            .add(HABlocks.BLEACHED_LEAF_CORAL_FAN.get())
            .add(HABlocks.BLEACHED_THORN_CORAL_FAN.get())
            .add(HABlocks.BLEACHED_FIRE_CORAL_FAN.get())
            .add(HABlocks.BLEACHED_TUBE_CORAL_FAN.get())
            .add(HABlocks.BLEACHED_HORN_CORAL_FAN.get())
            .add(HABlocks.BLEACHED_BUBBLE_CORAL_FAN.get())
            .add(HABlocks.BLEACHED_BRAIN_CORAL_FAN.get())

        valueLookupBuilder(HABlockTags.BLEACHED_WALL_CORALS)
            .add(HABlocks.BLEACHED_LOPHELIA_CORAL_WALL_FAN.get())
            .add(HABlocks.BLEACHED_BAMBOO_CORAL_WALL_FAN.get())
            .add(HABlocks.BLEACHED_ZIGZAG_CORAL_WALL_FAN.get())
            .add(HABlocks.BLEACHED_ROSE_CORAL_WALL_FAN.get())
            .add(HABlocks.BLEACHED_BUTTON_CORAL_WALL_FAN.get())
            .add(HABlocks.BLEACHED_SUN_CORAL_WALL_FAN.get())
            .add(HABlocks.BLEACHED_LEAF_CORAL_WALL_FAN.get())
            .add(HABlocks.BLEACHED_THORN_CORAL_WALL_FAN.get())
            .add(HABlocks.BLEACHED_FIRE_CORAL_WALL_FAN.get())
            .add(HABlocks.BLEACHED_TUBE_CORAL_WALL_FAN.get())
            .add(HABlocks.BLEACHED_HORN_CORAL_WALL_FAN.get())
            .add(HABlocks.BLEACHED_BUBBLE_CORAL_WALL_FAN.get())
            .add(HABlocks.BLEACHED_BRAIN_CORAL_WALL_FAN.get())
//#endregion

        valueLookupBuilder(HABlockTags.KELP)
            .add(HABlocks.BULL_KELP.get())
            .add(HABlocks.BULL_KELP_PLANT.get())
            .add(HABlocks.DELESSERIA.get())
            .add(HABlocks.DELESSERIA_PLANT.get())
            .add(HABlocks.SARGASSUM.get())
            .add(HABlocks.SARGASSUM_PLANT.get())
            .add(Blocks.KELP)
            .add(Blocks.KELP_PLANT)

        valueLookupBuilder(BlockTags.DEEPSLATE_ORE_REPLACEABLES)
            .add(HABlocks.SCHIST.get())
            .add(HABlocks.CHIMNEYSTONE.get())

        valueLookupBuilder(BlockTags.WOODEN_FENCES)
            .add(HAPlatformBlocks.DRIFTWOOD_FENCE.get())

        valueLookupBuilder(BlockTags.FENCES)
            .add(HABlocks.BONE_FENCE.get())

        valueLookupBuilder(BlockTags.WALLS)
            .add(HABlocks.BONE_WALL.get())
            .add(HABlocks.WHITE_SANDSTONE_WALL.get())
            .add(HABlocks.RED_BRINESTONE_WALL.get())
            .add(HABlocks.ORANGE_BRINESTONE_WALL.get())
            .add(HABlocks.YELLOW_BRINESTONE_WALL.get())
            .add(HABlocks.RED_BRINESTONE_BRICK_WALL.get())
            .add(HABlocks.ORANGE_BRINESTONE_BRICK_WALL.get())
            .add(HABlocks.YELLOW_BRINESTONE_BRICK_WALL.get())
            .add(HABlocks.SCHIST_WALL.get())
            .add(HABlocks.CHIMNEYSTONE_WALL.get())

        valueLookupBuilder(BlockTags.SLABS)
            .add(HABlocks.BONE_SLAB.get())
            .add(HABlocks.WHITE_SANDSTONE_SLAB.get())
            .add(HABlocks.CUT_WHITE_SANDSTONE_SLAB.get())
            .add(HABlocks.SMOOTH_WHITE_SANDSTONE_SLAB.get())

            .add(HABlocks.RED_BRINESTONE_SLAB.get())
            .add(HABlocks.POLISHED_RED_BRINESTONE_SLAB.get())
            .add(HABlocks.RED_BRINESTONE_BRICK_SLAB.get())

            .add(HABlocks.ORANGE_BRINESTONE_SLAB.get())
            .add(HABlocks.POLISHED_ORANGE_BRINESTONE_SLAB.get())
            .add(HABlocks.ORANGE_BRINESTONE_BRICK_SLAB.get())

            .add(HABlocks.YELLOW_BRINESTONE_SLAB.get())
            .add(HABlocks.POLISHED_YELLOW_BRINESTONE_SLAB.get())
            .add(HABlocks.YELLOW_BRINESTONE_BRICK_SLAB.get())

            .add(HABlocks.SCHIST_SLAB.get())
            .add(HABlocks.POLISHED_SCHIST_SLAB.get())
            .add(HABlocks.SCHIST_BRICK_SLAB.get())

            .add(HABlocks.CHIMNEYSTONE_SLAB.get())
            .add(HABlocks.POLISHED_CHIMNEYSTONE_SLAB.get())
            .add(HABlocks.CHIMNEYSTONE_BRICK_SLAB.get())

        valueLookupBuilder(BlockTags.STAIRS)
            .add(HABlocks.BONE_STAIRS.get())
            .add(HABlocks.WHITE_SANDSTONE_STAIRS.get())
            .add(HABlocks.SMOOTH_WHITE_SANDSTONE_STAIRS.get())

            .add(HABlocks.RED_BRINESTONE_STAIRS.get())
            .add(HABlocks.POLISHED_RED_BRINESTONE_STAIRS.get())
            .add(HABlocks.RED_BRINESTONE_BRICK_STAIRS.get())

            .add(HABlocks.ORANGE_BRINESTONE_STAIRS.get())
            .add(HABlocks.POLISHED_ORANGE_BRINESTONE_STAIRS.get())
            .add(HABlocks.ORANGE_BRINESTONE_BRICK_STAIRS.get())

            .add(HABlocks.YELLOW_BRINESTONE_STAIRS.get())
            .add(HABlocks.POLISHED_YELLOW_BRINESTONE_STAIRS.get())
            .add(HABlocks.YELLOW_BRINESTONE_BRICK_STAIRS.get())

            .add(HABlocks.SCHIST_STAIRS.get())
            .add(HABlocks.POLISHED_SCHIST_STAIRS.get())
            .add(HABlocks.SCHIST_BRICK_STAIRS.get())

            .add(HABlocks.CHIMNEYSTONE_STAIRS.get())
            .add(HABlocks.POLISHED_CHIMNEYSTONE_STAIRS.get())
            .add(HABlocks.CHIMNEYSTONE_BRICK_STAIRS.get())

        valueLookupBuilder(BlockTags.FENCE_GATES)
            .add(HAPlatformBlocks.DRIFTWOOD_FENCE_GATE.get())

        valueLookupBuilder(BlockTags.LOGS_THAT_BURN)
            .add(HAPlatformBlocks.DRIFTWOOD_LOG.get())
            .add(HAPlatformBlocks.DRIFTWOOD_WOOD.get())
            .add(HAPlatformBlocks.STRIPPED_DRIFTWOOD_LOG.get())
            .add(HAPlatformBlocks.STRIPPED_DRIFTWOOD_WOOD.get())

        valueLookupBuilder(BlockTags.UNDERWATER_BONEMEALS)
            .add(HABlocks.SHORT_RED_ALGAE.get())
            .add(HABlocks.RED_ALGAE.get())
            .add(HABlocks.SEA_LETTUCE.get())

        valueLookupBuilder(BlockTags.CORAL_BLOCKS)
            .add(HABlocks.BUTTON_CORAL_BLOCK.get())
            .add(HABlocks.ROSE_CORAL_BLOCK.get())
            .add(HABlocks.LEAF_CORAL_BLOCK.get())
            .add(HABlocks.SUN_CORAL_BLOCK.get())

        valueLookupBuilder(BlockTags.CORALS)
            .add(HABlocks.ROSE_CORAL.get())
            .add(HABlocks.LEAF_CORAL.get())
            .add(HABlocks.BUTTON_CORAL.get())
            .add(HABlocks.SUN_CORAL.get())

        valueLookupBuilder(BlockTags.CORAL_PLANTS)
            .add(HABlocks.ROSE_CORAL.get())
            .add(HABlocks.LEAF_CORAL.get())
            .add(HABlocks.BUTTON_CORAL.get())
            .add(HABlocks.SUN_CORAL.get())

        valueLookupBuilder(BlockTags.WALL_CORALS)
            .add(HABlocks.ROSE_CORAL_WALL_FAN.get())
            .add(HABlocks.LEAF_CORAL_WALL_FAN.get())
            .add(HABlocks.BUTTON_CORAL_WALL_FAN.get())
            .add(HABlocks.SUN_CORAL_WALL_FAN.get())

        valueLookupBuilder(BlockTags.SAND)
            .add(HABlocks.WHITE_SAND.get())

        valueLookupBuilder(BlockTags.MINEABLE_WITH_PICKAXE)
            .add(HABlocks.BUTTON_CORAL_BLOCK.get())
            .add(HABlocks.DEAD_BUTTON_CORAL_BLOCK.get())
            .add(HABlocks.BLEACHED_BUTTON_CORAL_BLOCK.get())
            .add(HABlocks.DEAD_BUTTON_CORAL.get())
            .add(HABlocks.BLEACHED_BUTTON_CORAL.get())
            .add(HABlocks.DEAD_BUTTON_CORAL_FAN.get())
            .add(HABlocks.BLEACHED_BUTTON_CORAL_FAN.get())
            .add(HABlocks.DEAD_BUTTON_CORAL_WALL_FAN.get())
            .add(HABlocks.BLEACHED_BUTTON_CORAL_WALL_FAN.get())

            .add(HABlocks.ROSE_CORAL_BLOCK.get())
            .add(HABlocks.DEAD_ROSE_CORAL_BLOCK.get())
            .add(HABlocks.BLEACHED_ROSE_CORAL_BLOCK.get())
            .add(HABlocks.DEAD_ROSE_CORAL.get())
            .add(HABlocks.BLEACHED_ROSE_CORAL.get())
            .add(HABlocks.DEAD_ROSE_CORAL_FAN.get())
            .add(HABlocks.BLEACHED_ROSE_CORAL_FAN.get())
            .add(HABlocks.DEAD_ROSE_CORAL_WALL_FAN.get())
            .add(HABlocks.BLEACHED_ROSE_CORAL_WALL_FAN.get())

            .add(HABlocks.LEAF_CORAL_BLOCK.get())
            .add(HABlocks.DEAD_LEAF_CORAL_BLOCK.get())
            .add(HABlocks.BLEACHED_LEAF_CORAL_BLOCK.get())
            .add(HABlocks.DEAD_LEAF_CORAL.get())
            .add(HABlocks.BLEACHED_LEAF_CORAL.get())
            .add(HABlocks.DEAD_LEAF_CORAL_FAN.get())
            .add(HABlocks.BLEACHED_LEAF_CORAL_FAN.get())
            .add(HABlocks.DEAD_LEAF_CORAL_WALL_FAN.get())
            .add(HABlocks.BLEACHED_LEAF_CORAL_WALL_FAN.get())

            .add(HABlocks.SUN_CORAL_BLOCK.get())
            .add(HABlocks.DEAD_SUN_CORAL_BLOCK.get())
            .add(HABlocks.BLEACHED_SUN_CORAL_BLOCK.get())
            .add(HABlocks.DEAD_SUN_CORAL.get())
            .add(HABlocks.BLEACHED_SUN_CORAL.get())
            .add(HABlocks.DEAD_SUN_CORAL_FAN.get())
            .add(HABlocks.BLEACHED_SUN_CORAL_FAN.get())
            .add(HABlocks.DEAD_SUN_CORAL_WALL_FAN.get())
            .add(HABlocks.BLEACHED_SUN_CORAL_WALL_FAN.get())

            .add(HABlocks.LOPHELIA_CORAL_BLOCK.get())
            .add(HABlocks.DEAD_LOPHELIA_CORAL_BLOCK.get())
            .add(HABlocks.BLEACHED_LOPHELIA_CORAL_BLOCK.get())
            .add(HABlocks.DEAD_LOPHELIA_CORAL.get())
            .add(HABlocks.BLEACHED_LOPHELIA_CORAL.get())
            .add(HABlocks.DEAD_LOPHELIA_CORAL_FAN.get())
            .add(HABlocks.BLEACHED_LOPHELIA_CORAL_FAN.get())
            .add(HABlocks.DEAD_LOPHELIA_CORAL_WALL_FAN.get())
            .add(HABlocks.BLEACHED_LOPHELIA_CORAL_WALL_FAN.get())

            .add(HABlocks.THORN_CORAL_BLOCK.get())
            .add(HABlocks.DEAD_THORN_CORAL_BLOCK.get())
            .add(HABlocks.BLEACHED_THORN_CORAL_BLOCK.get())
            .add(HABlocks.DEAD_THORN_CORAL.get())
            .add(HABlocks.BLEACHED_THORN_CORAL.get())
            .add(HABlocks.DEAD_THORN_CORAL_FAN.get())
            .add(HABlocks.BLEACHED_THORN_CORAL_FAN.get())
            .add(HABlocks.DEAD_THORN_CORAL_WALL_FAN.get())
            .add(HABlocks.BLEACHED_THORN_CORAL_WALL_FAN.get())

            .add(HABlocks.BAMBOO_CORAL_BLOCK.get())
            .add(HABlocks.DEAD_BAMBOO_CORAL_BLOCK.get())
            .add(HABlocks.BLEACHED_BAMBOO_CORAL_BLOCK.get())
            .add(HABlocks.DEAD_BAMBOO_CORAL.get())
            .add(HABlocks.BLEACHED_BAMBOO_CORAL.get())
            .add(HABlocks.DEAD_BAMBOO_CORAL_FAN.get())
            .add(HABlocks.BLEACHED_BAMBOO_CORAL_FAN.get())
            .add(HABlocks.DEAD_BAMBOO_CORAL_WALL_FAN.get())
            .add(HABlocks.BLEACHED_BAMBOO_CORAL_WALL_FAN.get())

            .add(HABlocks.ZIGZAG_CORAL_BLOCK.get())
            .add(HABlocks.DEAD_ZIGZAG_CORAL_BLOCK.get())
            .add(HABlocks.BLEACHED_ZIGZAG_CORAL_BLOCK.get())
            .add(HABlocks.DEAD_ZIGZAG_CORAL.get())
            .add(HABlocks.BLEACHED_ZIGZAG_CORAL.get())
            .add(HABlocks.DEAD_ZIGZAG_CORAL_FAN.get())
            .add(HABlocks.BLEACHED_ZIGZAG_CORAL_FAN.get())
            .add(HABlocks.DEAD_ZIGZAG_CORAL_WALL_FAN.get())
            .add(HABlocks.BLEACHED_ZIGZAG_CORAL_WALL_FAN.get())

            .add(HABlocks.BLEACHED_FIRE_CORAL_BLOCK.get())
            .add(HABlocks.BLEACHED_FIRE_CORAL.get())
            .add(HABlocks.BLEACHED_FIRE_CORAL_FAN.get())
            .add(HABlocks.BLEACHED_FIRE_CORAL_WALL_FAN.get())

            .add(HABlocks.BLEACHED_TUBE_CORAL_BLOCK.get())
            .add(HABlocks.BLEACHED_TUBE_CORAL.get())
            .add(HABlocks.BLEACHED_TUBE_CORAL_FAN.get())
            .add(HABlocks.BLEACHED_TUBE_CORAL_WALL_FAN.get())

            .add(HABlocks.BLEACHED_HORN_CORAL_BLOCK.get())
            .add(HABlocks.BLEACHED_HORN_CORAL.get())
            .add(HABlocks.BLEACHED_HORN_CORAL_FAN.get())
            .add(HABlocks.BLEACHED_HORN_CORAL_WALL_FAN.get())

            .add(HABlocks.BLEACHED_BRAIN_CORAL_BLOCK.get())
            .add(HABlocks.BLEACHED_BRAIN_CORAL.get())
            .add(HABlocks.BLEACHED_BRAIN_CORAL_FAN.get())
            .add(HABlocks.BLEACHED_BRAIN_CORAL_WALL_FAN.get())

            .add(HABlocks.BLEACHED_BUBBLE_CORAL_BLOCK.get())
            .add(HABlocks.BLEACHED_BUBBLE_CORAL.get())
            .add(HABlocks.BLEACHED_BUBBLE_CORAL_FAN.get())
            .add(HABlocks.BLEACHED_BUBBLE_CORAL_WALL_FAN.get())

            .add(HABlocks.PEARL_BLOCK.get())
            .add(HABlocks.BLACK_PEARL_BLOCK.get())

            .add(HABlocks.THERMAL_VENT.get())
            .add(HABlocks.GIANT_THERMAL_VENT.get())

            .add(HABlocks.CRYSTALLINE_SULFUR.get())

            .add(HABlocks.GIANT_CLAM.get())
            .add(HABlocks.OYSTER.get())

            .add(HABlocks.CORALSTONE.get())
            .add(HABlocks.SHORESTONE.get())
            .add(HABlocks.BARNACLE_SHORESTONE.get())

            .add(HABlocks.RED_BRINESTONE.get())
            .add(HABlocks.POLISHED_RED_BRINESTONE.get())
            .add(HABlocks.CHISELED_RED_BRINESTONE.get())
            .add(HABlocks.RED_BRINESTONE_BRICKS.get())

            .add(HABlocks.RED_BRINESTONE_SLAB.get())
            .add(HABlocks.POLISHED_RED_BRINESTONE_SLAB.get())
            .add(HABlocks.RED_BRINESTONE_BRICK_SLAB.get())

            .add(HABlocks.RED_BRINESTONE_STAIRS.get())
            .add(HABlocks.POLISHED_RED_BRINESTONE_STAIRS.get())
            .add(HABlocks.RED_BRINESTONE_BRICK_STAIRS.get())

            .add(HABlocks.RED_BRINESTONE_WALL.get())
            .add(HABlocks.RED_BRINESTONE_BRICK_WALL.get())

            .add(HABlocks.ORANGE_BRINESTONE.get())
            .add(HABlocks.POLISHED_ORANGE_BRINESTONE.get())
            .add(HABlocks.CHISELED_ORANGE_BRINESTONE.get())
            .add(HABlocks.ORANGE_BRINESTONE_BRICKS.get())

            .add(HABlocks.ORANGE_BRINESTONE_SLAB.get())
            .add(HABlocks.POLISHED_ORANGE_BRINESTONE_SLAB.get())
            .add(HABlocks.ORANGE_BRINESTONE_BRICK_SLAB.get())

            .add(HABlocks.ORANGE_BRINESTONE_STAIRS.get())
            .add(HABlocks.POLISHED_ORANGE_BRINESTONE_STAIRS.get())
            .add(HABlocks.ORANGE_BRINESTONE_BRICK_STAIRS.get())

            .add(HABlocks.ORANGE_BRINESTONE_WALL.get())
            .add(HABlocks.ORANGE_BRINESTONE_BRICK_WALL.get())

            .add(HABlocks.YELLOW_BRINESTONE.get())
            .add(HABlocks.POLISHED_YELLOW_BRINESTONE.get())
            .add(HABlocks.CHISELED_YELLOW_BRINESTONE.get())
            .add(HABlocks.YELLOW_BRINESTONE_BRICKS.get())

            .add(HABlocks.YELLOW_BRINESTONE_SLAB.get())
            .add(HABlocks.POLISHED_YELLOW_BRINESTONE_SLAB.get())
            .add(HABlocks.YELLOW_BRINESTONE_BRICK_SLAB.get())

            .add(HABlocks.YELLOW_BRINESTONE_STAIRS.get())
            .add(HABlocks.POLISHED_YELLOW_BRINESTONE_STAIRS.get())
            .add(HABlocks.YELLOW_BRINESTONE_BRICK_STAIRS.get())

            .add(HABlocks.YELLOW_BRINESTONE_WALL.get())
            .add(HABlocks.YELLOW_BRINESTONE_BRICK_WALL.get())

            .add(HABlocks.SCHIST.get())
            .add(HABlocks.POLISHED_SCHIST.get())
            .add(HABlocks.CHISELED_SCHIST.get())
            .add(HABlocks.SCHIST_BRICKS.get())

            .add(HABlocks.SCHIST_SLAB.get())
            .add(HABlocks.POLISHED_SCHIST_SLAB.get())
            .add(HABlocks.SCHIST_BRICK_SLAB.get())

            .add(HABlocks.SCHIST_STAIRS.get())
            .add(HABlocks.POLISHED_SCHIST_STAIRS.get())
            .add(HABlocks.SCHIST_BRICK_STAIRS.get())

            .add(HABlocks.SCHIST_WALL.get())
            .add(HABlocks.SCHIST_BRICK_WALL.get())

            .add(HABlocks.CHIMNEYSTONE.get())
            .add(HABlocks.POLISHED_CHIMNEYSTONE.get())
            .add(HABlocks.CHISELED_CHIMNEYSTONE.get())
            .add(HABlocks.CHIMNEYSTONE_BRICKS.get())

            .add(HABlocks.CHIMNEYSTONE_SLAB.get())
            .add(HABlocks.POLISHED_CHIMNEYSTONE_SLAB.get())
            .add(HABlocks.CHIMNEYSTONE_BRICK_SLAB.get())

            .add(HABlocks.CHIMNEYSTONE_STAIRS.get())
            .add(HABlocks.POLISHED_CHIMNEYSTONE_STAIRS.get())
            .add(HABlocks.CHIMNEYSTONE_BRICK_STAIRS.get())

            .add(HABlocks.CHIMNEYSTONE_WALL.get())
            .add(HABlocks.CHIMNEYSTONE_BRICK_WALL.get())

            .add(HABlocks.BONE_WALL.get())
            .add(HABlocks.BONE_SLAB.get())
            .add(HABlocks.BONE_STAIRS.get())

            .add(HABlocks.WHITE_SANDSTONE.get())
            .add(HABlocks.WHITE_SANDSTONE_WALL.get())
            .add(HABlocks.WHITE_SANDSTONE_SLAB.get())
            .add(HABlocks.WHITE_SANDSTONE_STAIRS.get())
            .add(HABlocks.CHISELED_WHITE_SANDSTONE.get())
            .add(HABlocks.CUT_WHITE_SANDSTONE.get())
            .add(HABlocks.CUT_WHITE_SANDSTONE_SLAB.get())
            .add(HABlocks.SMOOTH_WHITE_SANDSTONE.get())
            .add(HABlocks.SMOOTH_WHITE_SANDSTONE_SLAB.get())
            .add(HABlocks.SMOOTH_WHITE_SANDSTONE_STAIRS.get())

        valueLookupBuilder(BlockTags.MINEABLE_WITH_AXE)
            .add(HABlocks.RAFT.get())
            .add(HABlocks.OAK_RAFT.get())
            .add(HABlocks.SPRUCE_RAFT.get())
            .add(HABlocks.BIRCH_RAFT.get())
            .add(HABlocks.DARK_OAK_RAFT.get())
            .add(HABlocks.JUNGLE_RAFT.get())
            .add(HABlocks.ACACIA_RAFT.get())
            .add(HABlocks.MANGROVE_RAFT.get())
            .add(HABlocks.CHERRY_RAFT.get())
            .add(HABlocks.DRIFTWOOD_RAFT.get())
            .add(HABlocks.BUOY.get())
            .add(HABlocks.BELL_BUOY.get())
            .add(HABlocks.CRAB_POT.get())
            .add(HABlocks.HYBRID_CRATE.get())
            .add(HABlocks.OAK_CRATE.get())
            .add(HABlocks.SPRUCE_CRATE.get())
            .add(HABlocks.BIRCH_CRATE.get())
            .add(HABlocks.DARK_OAK_CRATE.get())
            .add(HABlocks.MANGROVE_CRATE.get())
            .add(HABlocks.JUNGLE_CRATE.get())
            .add(HABlocks.ACACIA_CRATE.get())
            .add(HABlocks.CHERRY_CRATE.get())
            .add(HABlocks.BAMBOO_CRATE.get())

        valueLookupBuilder(BlockTags.MINEABLE_WITH_SHOVEL)
            .add(HABlocks.GRASSY_SAND.get())
            .add(HABlocks.BUBBLE_GEYSER.get())
            .add(HABlocks.AERATED_SAND.get())
            .add(HABlocks.WHITE_SAND.get())
            .add(HABlocks.MARINE_SNOW.get())

        valueLookupBuilder(BlockTags.LOGS)
            .add(HAPlatformBlocks.DRIFTWOOD_LOG.get())
            .add(HAPlatformBlocks.STRIPPED_DRIFTWOOD_LOG.get())

        valueLookupBuilder(BlockTags.WOODEN_SLABS)
            .add(HAPlatformBlocks.DRIFTWOOD_SLAB.get())

        valueLookupBuilder(BlockTags.WOODEN_STAIRS)
            .add(HAPlatformBlocks.DRIFTWOOD_STAIRS.get())

        valueLookupBuilder(BlockTags.WOODEN_DOORS)
            .add(HAPlatformBlocks.DRIFTWOOD_DOOR.get())

        valueLookupBuilder(BlockTags.WOODEN_FENCES)
            .add(HAPlatformBlocks.DRIFTWOOD_FENCE.get())

        valueLookupBuilder(BlockTags.WOODEN_TRAPDOORS)
            .add(HAPlatformBlocks.DRIFTWOOD_TRAPDOOR.get())

        valueLookupBuilder(BlockTags.WOODEN_BUTTONS)
            .add(HAPlatformBlocks.DRIFTWOOD_BUTTON.get())

        valueLookupBuilder(BlockTags.WOODEN_PRESSURE_PLATES)
            .add(HAPlatformBlocks.DRIFTWOOD_PRESSURE_PLATE.get())

        valueLookupBuilder(BlockTags.FENCE_GATES)
            .add(HAPlatformBlocks.DRIFTWOOD_FENCE_GATE.get())

        valueLookupBuilder(BlockTags.PLANKS)
            .add(HAPlatformBlocks.DRIFTWOOD_PLANKS.get())

        valueLookupBuilder(BlockTags.FROGS_SPAWNABLE_ON)
            .add(HABlocks.JUNGLE_LILY_PAD.get())

        valueLookupBuilder(BlockTags.FROG_PREFER_JUMP_TO)
            .add(HABlocks.JUNGLE_LILY_PAD.get())

        valueLookupBuilder(HABlockTags.TIDE_POOL_REPLACEABLE)
            .add(Blocks.CLAY)
            .add(Blocks.GRAVEL)
            .add(Blocks.DIRT)
            .add(Blocks.SAND)
            .add(Blocks.GRASS_BLOCK)
            .add(Blocks.STONE)
            .add(Blocks.MOSSY_COBBLESTONE)
            .add(Blocks.TUFF)

        valueLookupBuilder(HABlockTags.CORAL_MOUND_BLOCKS)
            .add(HABlocks.CORALSTONE.get())

        valueLookupBuilder(HABlockTags.CORAL_MOUND_BASE_BLOCKS)
            .addTag(HABlockTags.CORAL_MOUND_BLOCKS)
            .add(HABlocks.SHORESTONE.get())
            .add(Blocks.SAND)

        valueLookupBuilder(HABlockTags.MOUND_BLOCKS)
            .add(Blocks.STONE)
            .add(Blocks.DEAD_BUBBLE_CORAL_BLOCK)
            .add(Blocks.DEAD_FIRE_CORAL_BLOCK)
            .add(Blocks.DEAD_HORN_CORAL_BLOCK)
            .add(Blocks.DEAD_BRAIN_CORAL_BLOCK)
            .add(Blocks.DEAD_TUBE_CORAL_BLOCK)
            .add(HABlocks.DEAD_SUN_CORAL_BLOCK.get())
            .add(HABlocks.DEAD_BUTTON_CORAL_BLOCK.get())
            .add(HABlocks.DEAD_LEAF_CORAL_BLOCK.get())
            .add(HABlocks.DEAD_ROSE_CORAL_BLOCK.get())
            .add(HABlocks.DEAD_LOPHELIA_CORAL_BLOCK.get())
            .add(HABlocks.DEAD_THORN_CORAL_BLOCK.get())
            .add(HABlocks.DEAD_BAMBOO_CORAL_BLOCK.get())
            .add(HABlocks.DEAD_ZIGZAG_CORAL_BLOCK.get())

        valueLookupBuilder(HABlockTags.IS_HONEYLIKE)
            .add(HAPlatformBlocks.HAGSLIME_BLOCK.get())
        valueLookupBuilder(HABlockTags.IS_SLIMELIKE)
            .add(HAPlatformBlocks.GLOWSLIME_BLOCK.get())

        // plushies
        BuiltInRegistries.BLOCK
            .filter(filterHybridAquatic(BuiltInRegistries.BLOCK))
            .forEach { block ->
                // plushies
                if (block is PlushieBlock) {
                    valueLookupBuilder(HABlockTags.PLUSHIES).add(block)
                }
            }
    }
}
