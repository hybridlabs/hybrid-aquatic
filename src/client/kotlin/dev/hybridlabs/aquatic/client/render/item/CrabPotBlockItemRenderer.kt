package dev.hybridlabs.aquatic.client.render.item

import dev.hybridlabs.aquatic.HybridAquaticClient
import dev.hybridlabs.aquatic.block.HybridAquaticBlocks
import dev.hybridlabs.aquatic.block.entity.BuoyBlockEntity
import dev.hybridlabs.aquatic.block.entity.CrabPotBlockEntity
import dev.hybridlabs.aquatic.client.render.block.entity.BuoyBlockEntityRenderer
import dev.hybridlabs.aquatic.client.render.block.entity.CrabPotBlockEntityRenderer
import net.fabricmc.fabric.api.client.rendering.v1.BuiltinItemRendererRegistry.DynamicItemRenderer
import net.minecraft.client.render.VertexConsumerProvider
import net.minecraft.client.render.model.json.ModelTransformationMode
import net.minecraft.client.util.math.MatrixStack
import net.minecraft.item.ItemStack
import net.minecraft.util.math.BlockPos

class CrabPotBlockItemRenderer: DynamicItemRenderer {
    private val crabPotBlockEntity = CrabPotBlockEntity(BlockPos.ORIGIN, HybridAquaticBlocks.CRAB_POT.defaultState)
    private val renderer = CrabPotBlockEntityRenderer(HybridAquaticClient.createBlockEntityRendererFactoryContext())

    override fun render(
        stack: ItemStack,
        mode: ModelTransformationMode,
        matrices: MatrixStack,
        vertexConsumers: VertexConsumerProvider,
        light: Int,
        overlay: Int
    ) {
        renderer.render(crabPotBlockEntity, 1.0f, matrices, vertexConsumers, light, overlay)
    }
}