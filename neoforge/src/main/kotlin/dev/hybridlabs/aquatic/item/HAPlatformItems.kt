package dev.hybridlabs.aquatic.item

import dev.hybridlabs.aquatic.block.HAPlatformBlocks
import dev.hybridlabs.aquatic.fluid.HAPlatformFluids
import dev.hybridlabs.aquatic.item.HAItems.register
import dev.hybridlabs.aquatic.item.HAItems.registerBlockItem
import net.minecraft.world.item.BucketItem
import net.minecraft.world.item.Item
import net.minecraft.world.item.Items

object HAPlatformItems {

    //#region Wood Blocks
    val DRIFTWOOD_PLANKS =
        registerBlockItem("driftwood_planks") { HAPlatformBlocks.DRIFTWOOD_PLANKS.get() }
    val DRIFTWOOD_LOG =
        registerBlockItem("driftwood_log") { HAPlatformBlocks.DRIFTWOOD_LOG.get() }
    val DRIFTWOOD_WOOD =
        registerBlockItem("driftwood_wood") { HAPlatformBlocks.DRIFTWOOD_WOOD.get() }
    val STRIPPED_DRIFTWOOD_LOG =
        registerBlockItem("stripped_driftwood_log") { HAPlatformBlocks.STRIPPED_DRIFTWOOD_LOG.get() }
    val STRIPPED_DRIFTWOOD_WOOD =
        registerBlockItem("stripped_driftwood_wood") { HAPlatformBlocks.STRIPPED_DRIFTWOOD_WOOD.get() }
    val DRIFTWOOD_DOOR =
        registerBlockItem("driftwood_door") { HAPlatformBlocks.DRIFTWOOD_DOOR.get() }
    val DRIFTWOOD_TRAPDOOR =
        registerBlockItem("driftwood_trapdoor") { HAPlatformBlocks.DRIFTWOOD_TRAPDOOR.get() }
    val DRIFTWOOD_SLAB =
        registerBlockItem("driftwood_slab") { HAPlatformBlocks.DRIFTWOOD_SLAB.get() }
    val DRIFTWOOD_STAIRS =
        registerBlockItem("driftwood_stairs") { HAPlatformBlocks.DRIFTWOOD_STAIRS.get() }
    val DRIFTWOOD_FENCE =
        registerBlockItem("driftwood_fence") { HAPlatformBlocks.DRIFTWOOD_FENCE.get() }
    val DRIFTWOOD_FENCE_GATE =
        registerBlockItem("driftwood_fence_gate") { HAPlatformBlocks.DRIFTWOOD_FENCE_GATE.get() }
    val DRIFTWOOD_PRESSURE_PLATE =
        registerBlockItem("driftwood_pressure_plate") { HAPlatformBlocks.DRIFTWOOD_PRESSURE_PLATE.get() }
    val DRIFTWOOD_BUTTON =
        registerBlockItem("driftwood_button") { HAPlatformBlocks.DRIFTWOOD_BUTTON.get() }
    val DUNEGRASS = registerBlockItem("dunegrass") { HAPlatformBlocks.DUNEGRASS.get() }
    val TALL_DUNEGRASS = registerBlockItem("tall_dunegrass") { HAPlatformBlocks.TALL_DUNEGRASS.get() }
    val CATTAIL = registerBlockItem("cattail") { HAPlatformBlocks.CATTAIL.get() }

    //#endregion

    val GLOWSLIME_BLOCK = registerBlockItem("glowslime_block") { HAPlatformBlocks.GLOWSLIME_BLOCK.get() }
    val HAGSLIME_BLOCK = registerBlockItem("hagslime_block") { HAPlatformBlocks.HAGSLIME_BLOCK.get() }

    val BRINE_BUCKET = register("brine_bucket") {
        BucketItem(HAPlatformFluids.BRINE_STILL.get(),Item.Properties()
            .stacksTo(1)
            .craftRemainder(Items.BUCKET)
        ) }
}