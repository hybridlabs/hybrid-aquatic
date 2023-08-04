package dev.hybridlabs.aquatic.client.render.item

import dev.hybridlabs.aquatic.HybridAquaticClient
import dev.hybridlabs.aquatic.block.HybridAquaticBlocks
import dev.hybridlabs.aquatic.block.entity.AnemoneBlockEntity
import dev.hybridlabs.aquatic.client.render.block.entity.AnemoneBlockEntityRenderer
import net.fabricmc.fabric.api.client.rendering.v1.BuiltinItemRendererRegistry.DynamicItemRenderer
import net.minecraft.client.render.VertexConsumerProvider
import net.minecraft.client.render.model.json.ModelTransformationMode
import net.minecraft.client.util.math.MatrixStack
import net.minecraft.item.ItemStack
import net.minecraft.util.math.BlockPos
import org.joml.Matrix4f

/**
 * Renders the Anemone block entity as an item.
 */
class AnemoneBlockItemRenderer : DynamicItemRenderer {
    private val anemoneBlockEntity = AnemoneBlockEntity(BlockPos.ORIGIN, HybridAquaticBlocks.ANEMONE.defaultState)
    private val renderer = AnemoneBlockEntityRenderer(HybridAquaticClient.createBlockEntityRendererFactoryContext())

    override fun render(
        stack: ItemStack,
        mode: ModelTransformationMode,
        matrices: MatrixStack,
        vertexConsumers: VertexConsumerProvider,
        light: Int,
        overlay: Int
    ) {
        matrices.push()

        if (mode == ModelTransformationMode.GUI) {
            matrices.translate(8.0f, 8.0f, 150.0f)
            matrices.multiplyPositionMatrix(Matrix4f().scaling(1.0f, 1.0f, 1.0f))
            matrices.scale(16.0f, 16.0f, 16.0f)
            renderer.render(anemoneBlockEntity, 1.0f, matrices, vertexConsumers, light, overlay)
        }

        matrices.pop()
    }
}
