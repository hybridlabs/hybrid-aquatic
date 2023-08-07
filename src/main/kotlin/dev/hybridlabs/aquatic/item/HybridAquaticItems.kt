@file:Suppress("unused")

package dev.hybridlabs.aquatic.item

import dev.hybridlabs.aquatic.HybridAquatic
import dev.hybridlabs.aquatic.block.HybridAquaticBlocks
import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.minecraft.block.Block
import net.minecraft.item.BlockItem
import net.minecraft.item.Item
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.util.Identifier

object HybridAquaticItems {
    val ANEMONE = registerBlockItem("anemone", HybridAquaticBlocks.ANEMONE)
    val MESSAGE_IN_A_BOTTLE = register("message_in_a_bottle", MessageInABottleItem(FabricItemSettings()))

    val BASKING_SHARK_BLAHAJ_PLUSHIE = registerBlockItem("basking_shark_blahaj_plushie", HybridAquaticBlocks.BASKING_SHARK_BLAHAJ_PLUSHIE)
    val BULL_SHARK_BLAHAJ_PLUSHIE = registerBlockItem("bull_shark_blahaj_plushie", HybridAquaticBlocks.BULL_SHARK_BLAHAJ_PLUSHIE)
    val FRILLED_SHARK_BLAHAJ_PLUSHIE = registerBlockItem("frilled_shark_blahaj_plushie", HybridAquaticBlocks.FRILLED_SHARK_BLAHAJ_PLUSHIE)
    val GREAT_WHITE_SHARK_BLAHAJ_PLUSHIE = registerBlockItem("great_white_shark_blahaj_plushie", HybridAquaticBlocks.GREAT_WHITE_SHARK_BLAHAJ_PLUSHIE)
    val HAMMERHEAD_SHARK_BLAHAJ_PLUSHIE = registerBlockItem("hammerhead_shark_blahaj_plushie", HybridAquaticBlocks.HAMMERHEAD_SHARK_BLAHAJ_PLUSHIE)
    val THRESHER_SHARK_BLAHAJ_PLUSHIE = registerBlockItem("thresher_shark_blahaj_plushie", HybridAquaticBlocks.THRESHER_SHARK_BLAHAJ_PLUSHIE)
    val TIGER_SHARK_BLAHAJ_PLUSHIE = registerBlockItem("tiger_shark_blahaj_plushie", HybridAquaticBlocks.TIGER_SHARK_BLAHAJ_PLUSHIE)
    val WHALE_SHARK_BLAHAJ_PLUSHIE = registerBlockItem("whale_shark_blahaj_plushie", HybridAquaticBlocks.WHALE_SHARK_BLAHAJ_PLUSHIE)

    private fun register(id: String, item: Item): Item {
        return Registry.register(Registries.ITEM, Identifier(HybridAquatic.MOD_ID, id), item)
    }

    private fun registerBlockItem(id: String, block: Block): Item {
        return register(id, BlockItem(block, FabricItemSettings()))
    }
}
