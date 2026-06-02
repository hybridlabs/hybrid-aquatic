package dev.hybridlabs.aquatic.block

import net.minecraft.data.BlockFamily
import net.minecraft.world.level.block.Blocks

@Suppress("unused")
object HABlockFamilies {

    val WHITE_SANDSTONE: BlockFamily by lazy {
        BlockFamily.Builder(HABlocks.WHITE_SANDSTONE.get())
            .stairs(HABlocks.WHITE_SANDSTONE_STAIRS.get())
            .slab(HABlocks.WHITE_SANDSTONE_SLAB.get())
            .wall(HABlocks.WHITE_SANDSTONE_WALL.get())
            .chiseled(HABlocks.CHISELED_WHITE_SANDSTONE.get())
            .family
    }

    val SMOOTH_WHITE_SANDSTONE: BlockFamily by lazy {
        BlockFamily.Builder(HABlocks.SMOOTH_WHITE_SANDSTONE.get())
            .stairs(HABlocks.SMOOTH_WHITE_SANDSTONE_STAIRS.get())
            .slab(HABlocks.SMOOTH_WHITE_SANDSTONE_SLAB.get())
            .family
    }

    val YELLOW_BRINESTONE: BlockFamily by lazy {
        BlockFamily.Builder(HABlocks.YELLOW_BRINESTONE.get())
            .stairs(HABlocks.YELLOW_BRINESTONE_STAIRS.get())
            .slab(HABlocks.YELLOW_BRINESTONE_SLAB.get())
            .polished(HABlocks.POLISHED_YELLOW_BRINESTONE.get())
            .wall(HABlocks.YELLOW_BRINESTONE_WALL.get())
            .family
    }

    val ORANGE_BRINESTONE: BlockFamily by lazy {
        BlockFamily.Builder(HABlocks.ORANGE_BRINESTONE.get())
            .stairs(HABlocks.ORANGE_BRINESTONE_STAIRS.get())
            .slab(HABlocks.ORANGE_BRINESTONE_SLAB.get())
            .polished(HABlocks.POLISHED_ORANGE_BRINESTONE.get())
            .wall(HABlocks.ORANGE_BRINESTONE_WALL.get())
            .family
    }

    val RED_BRINESTONE: BlockFamily by lazy {
        BlockFamily.Builder(HABlocks.RED_BRINESTONE.get())
            .stairs(HABlocks.RED_BRINESTONE_STAIRS.get())
            .slab(HABlocks.RED_BRINESTONE_SLAB.get())
            .polished(HABlocks.POLISHED_RED_BRINESTONE.get())
            .wall(HABlocks.RED_BRINESTONE_WALL.get())
            .family
    }

    val POLISHED_YELLOW_BRINESTONE: BlockFamily by lazy {
        BlockFamily.Builder(HABlocks.POLISHED_YELLOW_BRINESTONE.get())
            .stairs(HABlocks.POLISHED_YELLOW_BRINESTONE_STAIRS.get())
            .slab(HABlocks.POLISHED_YELLOW_BRINESTONE_SLAB.get())
            .family
    }

    val POLISHED_ORANGE_BRINESTONE: BlockFamily by lazy {
        BlockFamily.Builder(HABlocks.POLISHED_ORANGE_BRINESTONE.get())
            .stairs(HABlocks.POLISHED_ORANGE_BRINESTONE_STAIRS.get())
            .slab(HABlocks.POLISHED_ORANGE_BRINESTONE_SLAB.get())
            .family
    }

    val POLISHED_RED_BRINESTONE: BlockFamily by lazy {
        BlockFamily.Builder(HABlocks.POLISHED_RED_BRINESTONE.get())
            .stairs(HABlocks.POLISHED_RED_BRINESTONE_STAIRS.get())
            .slab(HABlocks.POLISHED_RED_BRINESTONE_SLAB.get())
            .family
    }

    val CUT_WHITE_SANDSTONE: BlockFamily by lazy {
        BlockFamily.Builder(HABlocks.CUT_WHITE_SANDSTONE.get())
            .slab(HABlocks.CUT_WHITE_SANDSTONE_SLAB.get())
            .family
    }

    val BONE_BLOCK: BlockFamily by lazy {
        BlockFamily.Builder(Blocks.BONE_BLOCK).dontGenerateModel().dontGenerateRecipe()
            .slab(HABlocks.BONE_SLAB.get())
            .stairs(HABlocks.BONE_STAIRS.get())
            .fence(HABlocks.BONE_FENCE.get())
            .wall(HABlocks.BONE_WALL.get())
            .family
    }
}