package dev.hybridlabs.aquatic.block.impl

import net.minecraft.world.level.block.state.BlockState

interface StickyBlock {

    fun isSticky(): Boolean

    fun isStickyToNeighbor(neighbor: BlockState): Boolean
}