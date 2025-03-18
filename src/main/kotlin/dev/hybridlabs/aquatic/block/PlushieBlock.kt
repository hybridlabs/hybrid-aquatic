package dev.hybridlabs.aquatic.block

import dev.hybridlabs.aquatic.HybridAquatic
import dev.hybridlabs.aquatic.block.HybridAquaticBlocks.addBlocks
import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.block.SkullBlock
import net.minecraft.block.entity.BlockEntityType
import net.minecraft.state.StateManager
import net.minecraft.util.Identifier
import net.minecraft.util.StringIdentifiable

/**
 * Represents any Plushie block.
 */
class PlushieBlock(variant: Variant, val particleBlock: Block, settings: Settings) : SkullBlock(variant, settings) {
    init {
        BlockEntityType.SKULL.addBlocks(this)
    }

    override fun appendProperties(builder: StateManager.Builder<Block, BlockState>) {
        super.appendProperties(builder)
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

        val textureLocation: Identifier by lazy { Identifier(HybridAquatic.MOD_ID, "textures/entity/block/plushie/${id}_plushie.png") }

        override fun asString(): String = id
    }
}
