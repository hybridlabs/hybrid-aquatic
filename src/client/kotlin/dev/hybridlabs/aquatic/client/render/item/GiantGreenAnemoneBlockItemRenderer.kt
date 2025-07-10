package dev.hybridlabs.aquatic.client.render.item

import dev.hybridlabs.aquatic.HybridAquaticClient
import dev.hybridlabs.aquatic.block.HybridAquaticBlocks
import dev.hybridlabs.aquatic.block.entity.GiantGreenAnemoneBlockEntity
import dev.hybridlabs.aquatic.client.render.block.entity.GiantGreenAnemoneBlockEntityRenderer
import net.fabricmc.fabric.api.client.rendering.v1.BuiltinItemRendererRegistry.DynamicItemRenderer
import net.minecraft.client.render.VertexConsumerProvider
import net.minecraft.client.render.model.json.ModelTransformationMode
import net.minecraft.client.util.math.MatrixStack
import net.minecraft.item.ItemStack
import net.minecraft.util.math.BlockPos

/**
 * Renders the Anemone block entity as an item.
 */
class GiantGreenAnemoneBlockItemRenderer : DynamicItemRenderer {
    private val giantGreenAnemoneBlockEntity = GiantGreenAnemoneBlockEntity(BlockPos.ORIGIN, HybridAquaticBlocks.GIANT_GREEN_ANEMONE.defaultState)
    private val renderer = GiantGreenAnemoneBlockEntityRenderer(HybridAquaticClient.createBlockEntityRendererFactoryContext())

    override fun render(
        stack: ItemStack,
        mode: ModelTransformationMode,
        matrices: MatrixStack,
        vertexConsumers: VertexConsumerProvider,
        light: Int,
        overlay: Int
    ) {
        renderer.render(giantGreenAnemoneBlockEntity, 1.0f, matrices, vertexConsumers, light, overlay)
    }
}
