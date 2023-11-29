package dev.hybridlabs.aquatic.client.render.item

import dev.hybridlabs.aquatic.HybridAquaticClient
import dev.hybridlabs.aquatic.block.HybridAquaticBlocks
import dev.hybridlabs.aquatic.block.entity.TubeSpongeBlockEntity
import dev.hybridlabs.aquatic.client.render.block.entity.TubeSpongeBlockEntityRenderer
import net.fabricmc.fabric.api.client.rendering.v1.BuiltinItemRendererRegistry.DynamicItemRenderer
import net.minecraft.client.render.VertexConsumerProvider
import net.minecraft.client.render.model.json.ModelTransformationMode
import net.minecraft.client.util.math.MatrixStack
import net.minecraft.item.ItemStack
import net.minecraft.util.math.BlockPos

class TubeSpongeBlockItemRenderer: DynamicItemRenderer {
    private val TubeSpongeBlockEntity = TubeSpongeBlockEntity(BlockPos.ORIGIN, HybridAquaticBlocks.TUBE_SPONGE.defaultState)
    private val renderer = TubeSpongeBlockEntityRenderer(HybridAquaticClient.createBlockEntityRendererFactoryContext())

    override fun render(
        stack: ItemStack,
        mode: ModelTransformationMode,
        matrices: MatrixStack,
        vertexConsumers: VertexConsumerProvider,
        light: Int,
        overlay: Int
    ) {
        renderer.render(TubeSpongeBlockEntity, 1.0f, matrices, vertexConsumers, light, overlay)
    }
}