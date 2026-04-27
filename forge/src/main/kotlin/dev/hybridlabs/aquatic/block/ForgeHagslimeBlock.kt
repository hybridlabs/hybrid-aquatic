package dev.hybridlabs.aquatic.block

import net.minecraft.world.level.block.state.BlockState

class ForgeHagslimeBlock(settings: Properties) : HagslimeBlock(settings) {

    override fun isStickyBlock(state: BlockState): Boolean {
        return isSticky()
    }

    override fun canStickTo(state: BlockState, other: BlockState): Boolean {
        return isStickyToNeighbor(other)
    }
}