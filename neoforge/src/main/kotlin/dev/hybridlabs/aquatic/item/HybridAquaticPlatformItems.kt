package dev.hybridlabs.aquatic.item

import dev.hybridlabs.aquatic.block.wood.HybridAquaticPlatformBlocks
import dev.hybridlabs.aquatic.item.HybridAquaticItems.registerBlockItem

object HybridAquaticPlatformItems {

    //#region Wood Blocks
    val DRIFTWOOD_PLANKS =
        HybridAquaticItems.registerBlockItem("driftwood_planks") { HybridAquaticPlatformBlocks.DRIFTWOOD_PLANKS.get() }
    val DRIFTWOOD_LOG =
        HybridAquaticItems.registerBlockItem("driftwood_log") { HybridAquaticPlatformBlocks.DRIFTWOOD_LOG.get() }
    val DRIFTWOOD_WOOD =
        HybridAquaticItems.registerBlockItem("driftwood_wood") { HybridAquaticPlatformBlocks.DRIFTWOOD_WOOD.get() }
    val STRIPPED_DRIFTWOOD_LOG =
        HybridAquaticItems.registerBlockItem("stripped_driftwood_log") { HybridAquaticPlatformBlocks.STRIPPED_DRIFTWOOD_LOG.get() }
    val STRIPPED_DRIFTWOOD_WOOD =
        HybridAquaticItems.registerBlockItem("stripped_driftwood_wood") { HybridAquaticPlatformBlocks.STRIPPED_DRIFTWOOD_WOOD.get() }
    val DRIFTWOOD_DOOR =
        HybridAquaticItems.registerBlockItem("driftwood_door") { HybridAquaticPlatformBlocks.DRIFTWOOD_DOOR.get() }
    val DRIFTWOOD_TRAPDOOR =
        HybridAquaticItems.registerBlockItem("driftwood_trapdoor") { HybridAquaticPlatformBlocks.DRIFTWOOD_TRAPDOOR.get() }
    val DRIFTWOOD_SLAB =
        HybridAquaticItems.registerBlockItem("driftwood_slab") { HybridAquaticPlatformBlocks.DRIFTWOOD_SLAB.get() }
    val DRIFTWOOD_STAIRS =
        HybridAquaticItems.registerBlockItem("driftwood_stairs") { HybridAquaticPlatformBlocks.DRIFTWOOD_STAIRS.get() }
    val DRIFTWOOD_FENCE =
        HybridAquaticItems.registerBlockItem("driftwood_fence") { HybridAquaticPlatformBlocks.DRIFTWOOD_FENCE.get() }
    val DRIFTWOOD_FENCE_GATE =
        HybridAquaticItems.registerBlockItem("driftwood_fence_gate") { HybridAquaticPlatformBlocks.DRIFTWOOD_FENCE_GATE.get() }
    val DRIFTWOOD_PRESSURE_PLATE =
        HybridAquaticItems.registerBlockItem("driftwood_pressure_plate") { HybridAquaticPlatformBlocks.DRIFTWOOD_PRESSURE_PLATE.get() }
    val DRIFTWOOD_BUTTON =
        HybridAquaticItems.registerBlockItem("driftwood_button") { HybridAquaticPlatformBlocks.DRIFTWOOD_BUTTON.get() }
    val DUNEGRASS = registerBlockItem("dunegrass") { HybridAquaticPlatformBlocks.DUNEGRASS.get() }
    val CATTAIL = registerBlockItem("cattail") { HybridAquaticPlatformBlocks.CATTAIL.get() }

    //#endregion

}