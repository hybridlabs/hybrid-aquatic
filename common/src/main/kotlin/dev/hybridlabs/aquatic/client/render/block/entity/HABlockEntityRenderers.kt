package dev.hybridlabs.aquatic.client.render.block.entity


import dev.hybridlabs.aquatic.block.HABlocks
import dev.hybridlabs.aquatic.block.entity.HABlockEntityTypes
import net.minecraft.client.renderer.rendertype.RenderType
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers

object HABlockEntityRenderers {

    fun registerBlockEntityRenderers() {
        BlockEntityRenderers.register(
            HABlockEntityTypes.ANEMONE.get(),
            ::AnemoneBlockEntityRenderer
        )
        BlockEntityRenderers.register(
            HABlockEntityTypes.GIANT_GREEN_ANEMONE.get(),
            ::GiantGreenAnemoneBlockEntityRenderer
        )
        BlockEntityRenderers.register(
            HABlockEntityTypes.STRAWBERRY_ANEMONE.get(),
            ::StrawberryAnemoneBlockEntityRenderer
        )
        BlockEntityRenderers.register(
            HABlockEntityTypes.MESSAGE_IN_A_BOTTLE.get(),
            ::MessageInABottleBlockEntityRenderer
        )
        BlockEntityRenderers.register(
            HABlockEntityTypes.BUOY.get(),
            ::BuoyBlockEntityRenderer
        )
        BlockEntityRenderers.register(
            HABlockEntityTypes.BELL_BUOY.get(),
            ::BellBuoyBlockEntityRenderer
        )

    }
}
