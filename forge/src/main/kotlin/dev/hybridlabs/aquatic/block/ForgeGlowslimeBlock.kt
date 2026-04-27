package dev.hybridlabs.aquatic.block

import net.minecraft.world.level.block.state.BlockState

class ForgeGlowslimeBlock(settings: Properties) : GlowslimeBlock(settings) {

    override fun isSlimeBlock(state: BlockState): Boolean {
        return isSticky()
    }

    override fun canStickTo(state: BlockState, other: BlockState): Boolean {
        return isStickyToNeighbor(other)
    }
}