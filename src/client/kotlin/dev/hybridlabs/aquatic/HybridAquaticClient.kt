package dev.hybridlabs.aquatic

import dev.hybridlabs.aquatic.block.HybridAquaticBlocks
import dev.hybridlabs.aquatic.block.entity.AnemoneBlockEntity
import dev.hybridlabs.aquatic.block.entity.HybridAquaticBlockEntityTypes
import dev.hybridlabs.aquatic.client.render.block.entity.AnemoneBlockEntityRenderer
import dev.hybridlabs.aquatic.item.HybridAquaticItems
import net.fabricmc.api.ClientModInitializer
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap
import net.fabricmc.fabric.api.client.rendering.v1.BuiltinItemRendererRegistry
import net.minecraft.client.MinecraftClient
import net.minecraft.client.render.RenderLayer
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory
import net.minecraft.client.render.model.json.ModelTransformationMode
import net.minecraft.util.math.BlockPos
import org.joml.Matrix4f

object HybridAquaticClient : ClientModInitializer {
    override fun onInitializeClient() {
        registerBlockRenderLayers()
        registerBlockEntityRenderers()
        registerBuiltinItemRenderers()
    }

    private fun registerBlockRenderLayers(registry: BlockRenderLayerMap = BlockRenderLayerMap.INSTANCE) {
        registry.putBlocks(RenderLayer.getTranslucent(),
            HybridAquaticBlocks.ANEMONE
        )
    }

    private fun registerBlockEntityRenderers() {
        BlockEntityRendererFactories.register(HybridAquaticBlockEntityTypes.ANEMONE, ::AnemoneBlockEntityRenderer)
    }

    private fun registerBuiltinItemRenderers(registry: BuiltinItemRendererRegistry = BuiltinItemRendererRegistry.INSTANCE) {
        val anemoneBlockEntity = AnemoneBlockEntity(BlockPos.ORIGIN, HybridAquaticBlocks.ANEMONE.defaultState)
        val client = MinecraftClient.getInstance()
        val renderer = AnemoneBlockEntityRenderer(BlockEntityRendererFactory.Context(client.blockEntityRenderDispatcher, client.blockRenderManager, client.itemRenderer, client.entityRenderDispatcher, client.entityModelLoader, client.textRenderer))
        registry.register(HybridAquaticItems.ANEMONE) { stack, mode, matrices, vertices, light, overlay ->
            matrices.push()

            if (mode == ModelTransformationMode.GUI) {
                matrices.translate(8.0f, 8.0f, 150.0f)
                matrices.multiplyPositionMatrix(Matrix4f().scaling(1.0f, 1.0f, 1.0f))
                matrices.scale(16.0f, 16.0f, 16.0f)
                renderer.render(anemoneBlockEntity, 1.0f, matrices, vertices, light, overlay)
            }

            matrices.pop()
        }
    }
}
