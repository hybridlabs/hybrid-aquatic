package dev.hybridlabs.aquatic.client.render.item

import dev.hybridlabs.aquatic.HybridAquaticClient
import dev.hybridlabs.aquatic.block.HybridAquaticBlocks
import dev.hybridlabs.aquatic.block.entity.StrawberryAnemoneBlockEntity
import dev.hybridlabs.aquatic.client.render.block.entity.StrawberryAnemoneBlockEntityRenderer
import net.fabricmc.fabric.api.client.rendering.v1.BuiltinItemRendererRegistry.DynamicItemRenderer
import net.minecraft.client.render.VertexConsumerProvider
import net.minecraft.client.render.model.json.ModelTransformationMode
import net.minecraft.client.util.math.MatrixStack
import net.minecraft.item.ItemStack
import net.minecraft.util.math.BlockPos

/**
 * Renders the Anemone block entity as an item.
 */
class StrawberryAnemoneBlockItemRenderer : DynamicItemRenderer {
    private val strawberryAnemoneBlockEntity = StrawberryAnemoneBlockEntity(BlockPos.ORIGIN, HybridAquaticBlocks.STRAWBERRY_ANEMONE.defaultState)
    private val renderer = StrawberryAnemoneBlockEntityRenderer(HybridAquaticClient.createBlockEntityRendererFactoryContext())

    override fun render(
        stack: ItemStack,
        mode: ModelTransformationMode,
        matrices: MatrixStack,
        vertexConsumers: VertexConsumerProvider,
        light: Int,
        overlay: Int
    ) {
        renderer.render(strawberryAnemoneBlockEntity, 1.0f, matrices, vertexConsumers, light, overlay)
    }
}
