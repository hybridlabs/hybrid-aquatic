package dev.hybridlabs.aquatic.block

import net.minecraft.data.BlockFamily

@Suppress("unused")
object HybridAquaticBlockFamilies {

    val WHITE_SANDSTONE: BlockFamily by lazy {
        BlockFamily.Builder(HybridAquaticBlocks.WHITE_SANDSTONE.get())
            .stairs(HybridAquaticBlocks.WHITE_SANDSTONE_STAIRS.get())
            .slab(HybridAquaticBlocks.WHITE_SANDSTONE_SLAB.get())
            .wall(HybridAquaticBlocks.WHITE_SANDSTONE_WALL.get())
            .chiseled(HybridAquaticBlocks.CHISELED_WHITE_SANDSTONE.get())
            .family
    }

    val SMOOTH_WHITE_SANDSTONE: BlockFamily by lazy {
        BlockFamily.Builder(HybridAquaticBlocks.SMOOTH_WHITE_SANDSTONE.get())
            .stairs(HybridAquaticBlocks.SMOOTH_WHITE_SANDSTONE_STAIRS.get())
            .slab(HybridAquaticBlocks.SMOOTH_WHITE_SANDSTONE_SLAB.get())
            .family
    }

    val CUT_WHITE_SANDSTONE: BlockFamily by lazy {
        BlockFamily.Builder(HybridAquaticBlocks.CUT_WHITE_SANDSTONE.get())
            .slab(HybridAquaticBlocks.CUT_WHITE_SANDSTONE_SLAB.get())
            .family
    }
}