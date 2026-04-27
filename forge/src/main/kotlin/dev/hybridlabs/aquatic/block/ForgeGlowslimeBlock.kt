package dev.hybridlabs.aquatic.block

import dev.hybridlabs.aquatic.Constants
import dev.hybridlabs.aquatic.block.impl.StickyBlock
import net.minecraft.world.level.block.state.BlockState

class ForgeGlowslimeBlock(settings: Properties) : GlowslimeBlock(settings) {

    override fun isStickyBlock(state: BlockState): Boolean {
        Constants.LOG.info("test1: {}, {}", this, state)
        return (state.block as StickyBlock).isSticky()
    }

    override fun canStickTo(state: BlockState, other: BlockState): Boolean {
        Constants.LOG.info("test2: {}, {}, {}", this, state.block, other.block)
        return (state.block as StickyBlock).isStickyToNeighbor(other)
    }
}