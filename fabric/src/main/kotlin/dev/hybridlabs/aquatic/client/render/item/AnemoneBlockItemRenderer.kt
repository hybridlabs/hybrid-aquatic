package dev.hybridlabs.aquatic.client.renderer.item

import com.mojang.blaze3d.vertex.PoseStack
import dev.hybridlabs.aquatic.HybridAquaticClient
import dev.hybridlabs.aquatic.block.HybridAquaticBlocks
import dev.hybridlabs.aquatic.block.entity.AnemoneBlockEntity
import dev.hybridlabs.aquatic.client.renderer.block.entity.AnemoneBlockEntityRenderer
import net.fabricmc.fabric.api.client.rendering.v1.BuiltinItemRendererRegistry.DynamicItemRenderer
import net.minecraft.client.renderer.MultiBufferSource
import net.minecraft.core.BlockPos
import net.minecraft.world.item.ItemDisplayContext
import net.minecraft.world.item.ItemStack

/**
 * Renders the Anemone block entity as an item.
 */
class AnemoneBlockItemRenderer : DynamicItemRenderer {
    private val anemoneBlockEntity =
        AnemoneBlockEntity(BlockPos.ZERO, HybridAquaticBlocks.ANEMONE.get().defaultBlockState())
    private val renderer = AnemoneBlockEntityRenderer(HybridAquaticClient.createBlocEntityRendererProviderContext())

    override fun render(
        stack: ItemStack,
        mode: ItemDisplayContext,
        matrices: PoseStack,
        vertexConsumers: MultiBufferSource,
        light: Int,
        overlay: Int
    ) {
        renderer.render(anemoneBlockEntity, 1.0f, matrices, vertexConsumers, light, overlay)
    }
}