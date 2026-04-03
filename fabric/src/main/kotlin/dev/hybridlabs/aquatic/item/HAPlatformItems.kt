package dev.hybridlabs.aquatic.item

import dev.hybridlabs.aquatic.block.wood.HAPlatformBlocks
import dev.hybridlabs.aquatic.item.HAAquaticItems.registerBlockItem

object HAPlatformItems {

    //#region Wood Blocks
    val DRIFTWOOD_PLANKS =
        HAAquaticItems.registerBlockItem("driftwood_planks") { HAPlatformBlocks.DRIFTWOOD_PLANKS.get() }
    val DRIFTWOOD_LOG =
        HAAquaticItems.registerBlockItem("driftwood_log") { HAPlatformBlocks.DRIFTWOOD_LOG.get() }
    val DRIFTWOOD_WOOD =
        HAAquaticItems.registerBlockItem("driftwood_wood") { HAPlatformBlocks.DRIFTWOOD_WOOD.get() }
    val STRIPPED_DRIFTWOOD_LOG =
        HAAquaticItems.registerBlockItem("stripped_driftwood_log") { HAPlatformBlocks.STRIPPED_DRIFTWOOD_LOG.get() }
    val STRIPPED_DRIFTWOOD_WOOD =
        HAAquaticItems.registerBlockItem("stripped_driftwood_wood") { HAPlatformBlocks.STRIPPED_DRIFTWOOD_WOOD.get() }
    val DRIFTWOOD_DOOR =
        HAAquaticItems.registerBlockItem("driftwood_door") { HAPlatformBlocks.DRIFTWOOD_DOOR.get() }
    val DRIFTWOOD_TRAPDOOR =
        HAAquaticItems.registerBlockItem("driftwood_trapdoor") { HAPlatformBlocks.DRIFTWOOD_TRAPDOOR.get() }
    val DRIFTWOOD_SLAB =
        HAAquaticItems.registerBlockItem("driftwood_slab") { HAPlatformBlocks.DRIFTWOOD_SLAB.get() }
    val DRIFTWOOD_STAIRS =
        HAAquaticItems.registerBlockItem("driftwood_stairs") { HAPlatformBlocks.DRIFTWOOD_STAIRS.get() }
    val DRIFTWOOD_FENCE =
        HAAquaticItems.registerBlockItem("driftwood_fence") { HAPlatformBlocks.DRIFTWOOD_FENCE.get() }
    val DRIFTWOOD_FENCE_GATE =
        HAAquaticItems.registerBlockItem("driftwood_fence_gate") { HAPlatformBlocks.DRIFTWOOD_FENCE_GATE.get() }
    val DRIFTWOOD_PRESSURE_PLATE =
        HAAquaticItems.registerBlockItem("driftwood_pressure_plate") { HAPlatformBlocks.DRIFTWOOD_PRESSURE_PLATE.get() }
    val DRIFTWOOD_BUTTON =
        HAAquaticItems.registerBlockItem("driftwood_button") { HAPlatformBlocks.DRIFTWOOD_BUTTON.get() }
    val DUNEGRASS = registerBlockItem("dunegrass") { HAPlatformBlocks.DUNEGRASS.get() }
    val TALL_DUNEGRASS = registerBlockItem("tall_dunegrass") { HAPlatformBlocks.TALL_DUNEGRASS.get() }
    val CATTAIL = registerBlockItem("cattail") { HAPlatformBlocks.CATTAIL.get() }
    //#endregion

}