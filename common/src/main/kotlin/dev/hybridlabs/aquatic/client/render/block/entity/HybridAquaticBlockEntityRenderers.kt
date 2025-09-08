package dev.hybridlabs.aquatic

import dev.hybridlabs.aquatic.block.HybridAquaticBlocks
import dev.hybridlabs.aquatic.block.entity.HybridAquaticBlockEntityTypes
import dev.hybridlabs.aquatic.client.render.block.entity.*
import dev.hybridlabs.aquatic.platform.ClientServices
import net.minecraft.client.renderer.RenderType
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers

object HybridAquaticBlockEntityRenderers {

    fun registerBlockEntityRenderers() {
        BlockEntityRenderers.register(HybridAquaticBlockEntityTypes.ANEMONE.get(), ::AnemoneBlockEntityRenderer)
        BlockEntityRenderers.register(
            HybridAquaticBlockEntityTypes.GIANT_GREEN_ANEMONE.get(),
            ::GiantGreenAnemoneBlockEntityRenderer
        )
        BlockEntityRenderers.register(
            HybridAquaticBlockEntityTypes.STRAWBERRY_ANEMONE.get(),
            ::StrawberryAnemoneBlockEntityRenderer
        )
        BlockEntityRenderers.register(
            HybridAquaticBlockEntityTypes.MESSAGE_IN_A_BOTTLE.get(),
            ::MessageInABottleBlockEntityRenderer
        )
        BlockEntityRenderers.register(HybridAquaticBlockEntityTypes.BUOY.get(), ::BuoyBlockEntityRenderer)

        ClientServices.PLATFORM.registerBlockRenderers(
            RenderType.translucent(),
            HybridAquaticBlocks.ANEMONE.get(),
            HybridAquaticBlocks.GIANT_GREEN_ANEMONE.get(),
            HybridAquaticBlocks.STRAWBERRY_ANEMONE.get(),
            HybridAquaticBlocks.MESSAGE_IN_A_BOTTLE.get(),
        )
    }
}
