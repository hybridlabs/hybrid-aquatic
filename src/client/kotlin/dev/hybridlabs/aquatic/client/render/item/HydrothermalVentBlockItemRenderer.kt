package dev.hybridlabs.aquatic.client.render.item

import dev.hybridlabs.aquatic.HybridAquaticClient
import dev.hybridlabs.aquatic.block.HybridAquaticBlocks
import dev.hybridlabs.aquatic.block.entity.BuoyBlockEntity
import dev.hybridlabs.aquatic.block.entity.CrabPotBlockEntity
import dev.hybridlabs.aquatic.block.entity.HydrothermalVentBlockEntity
import dev.hybridlabs.aquatic.client.render.block.entity.BuoyBlockEntityRenderer
import dev.hybridlabs.aquatic.client.render.block.entity.HydrothermalVentBlockEntityRenderer
import net.fabricmc.fabric.api.client.rendering.v1.BuiltinItemRendererRegistry.DynamicItemRenderer
import net.minecraft.client.render.VertexConsumerProvider
import net.minecraft.client.render.model.json.ModelTransformationMode
import net.minecraft.client.util.math.MatrixStack
import net.minecraft.item.ItemStack
import net.minecraft.util.math.BlockPos

class HydrothermalVentBlockItemRenderer: DynamicItemRenderer {
    private val hydrothermalVentBlockEntity = HydrothermalVentBlockEntity(BlockPos.ORIGIN, HybridAquaticBlocks.HYDROTHERMAL_VENT.defaultState)
    private val renderer = HydrothermalVentBlockEntityRenderer(HybridAquaticClient.createBlockEntityRendererFactoryContext())

    override fun render(
        stack: ItemStack,
        mode: ModelTransformationMode,
        matrices: MatrixStack,
        vertexConsumers: VertexConsumerProvider,
        light: Int,
        overlay: Int
    ) {
        renderer.render(hydrothermalVentBlockEntity, 1.0f, matrices, vertexConsumers, light, overlay)
    }
}