package dev.hybridlabs.aquatic.block.property

import dev.hybridlabs.aquatic.block.HAPlatformBlocks
import net.fabricmc.fabric.api.item.v1.BlockTransformerHelper
import net.minecraft.world.level.block.Block

object StrippableProperty {
    init {
        register(HAPlatformBlocks.DRIFTWOOD_LOG.get(), HAPlatformBlocks.STRIPPED_DRIFTWOOD_LOG.get())
        register(HAPlatformBlocks.DRIFTWOOD_WOOD.get(), HAPlatformBlocks.STRIPPED_DRIFTWOOD_WOOD.get())
    }

    fun register(input: Block, output: Block) {
        BlockTransformerHelper.registerStripping(input, output)
    }
}