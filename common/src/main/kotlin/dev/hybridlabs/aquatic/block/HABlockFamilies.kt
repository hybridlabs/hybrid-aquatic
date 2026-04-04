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