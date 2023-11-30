package dev.hybridlabs.aquatic.client.render.item

import dev.hybridlabs.aquatic.HybridAquaticClient
import dev.hybridlabs.aquatic.block.HybridAquaticBlocks
import dev.hybridlabs.aquatic.block.entity.GiantClamBlockEntity
import dev.hybridlabs.aquatic.client.render.block.entity.GiantClamBlockEntityRenderer
import net.fabricmc.fabric.api.client.rendering.v1.BuiltinItemRendererRegistry.DynamicItemRenderer
import net.minecraft.client.render.VertexConsumerProvider
import net.minecraft.client.render.model.json.ModelTransformationMode
import net.minecraft.client.util.math.MatrixStack
import net.minecraft.item.ItemStack
import net.minecraft.util.math.BlockPos

class GiantClamBlockItemRenderer: DynamicItemRenderer {
    private val giantClamBlockEntity = GiantClamBlockEntity(BlockPos.ORIGIN, HybridAquaticBlocks.GIANT_CLAM.defaultState)
    private val renderer = GiantClamBlockEntityRenderer(HybridAquaticClient.createBlockEntityRendererFactoryContext())

    override fun render(
        stack: ItemStack,
        mode: ModelTransformationMode,
        matrices: MatrixStack,
        vertexConsumers: VertexConsumerProvider,
        light: Int,
        overlay: Int
    ) {
        renderer.render(giantClamBlockEntity, 1.0f, matrices, vertexConsumers, light, overlay)
    }
}