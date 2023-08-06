package dev.hybridlabs.aquatic.block

import dev.hybridlabs.aquatic.block.HybridAquaticBlocks.addBlocks
import net.minecraft.block.Block
import net.minecraft.block.SkullBlock
import net.minecraft.block.entity.BlockEntityType
import net.minecraft.util.StringIdentifiable

/**
 * Represents any Blahaj Plushie block.
 */
class BlahajPlushieBlock(variant: Variant, val particleBlock: Block, settings: Settings) : SkullBlock(variant, settings) {
    init {
        BlockEntityType.SKULL.addBlocks(this)
    }

    enum class Variant(val id: String) : SkullType, StringIdentifiable {
        BASKING_SHARK("basking_shark"),
        BULL_SHARK("bull_shark"),
        FRILLED_SHARK("frilled_shark"),
        GREAT_WHITE_SHARK("great_white_shark"),
        HAMMERHEAD_SHARK("hammerhead_shark"),
        THRESHER_SHARK("thresher_shark"),
        TIGER_SHARK("tiger_shark"),
        WHALE_SHARK("whale_shark");

        override fun asString(): String {
            return id
        }
    }
}
