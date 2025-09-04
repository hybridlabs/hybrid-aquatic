package dev.hybridlabs.aquatic.client.renderer.item

import dev.hybridlabs.aquatic.HybridAquaticClient
import dev.hybridlabs.aquatic.block.HybridAquaticBlocks
import dev.hybridlabs.aquatic.block.entity.GiantGreenAnemoneBlockEntity
import dev.hybridlabs.aquatic.client.renderer.block.entity.GiantGreenAnemoneBlockEntityRenderer
import net.fabricmc.fabric.api.client.rendering.v1.BuiltinItemRendererRegistry.DynamicItemRenderer
import net.minecraft.client.renderer.MultiBufferSource
import net.minecraft.client.renderer.model.json.ItemDisplayContext
import net.minecraft.client.util.math.PoseStack
import net.minecraft.item.ItemStack
import net.minecraft.util.math.BlockPos

class GiantGreenAnemoneBlockItemRenderer : DynamicItemRenderer {
    private val giantGreenAnemoneBlockEntity = GiantGreenAnemoneBlockEntity(BlockPos.ORIGIN, HybridAquaticBlocks.GIANT_GREEN_ANEMONE.defaultBlockState())
    private val renderer = GiantGreenAnemoneBlockEntityRenderer(HybridAquaticClient.createBloc.EntityRendererProviderContext())

    override fun render(
        stack: ItemStack,
        mode: ItemDisplayContext,
        matrices: PoseStack,
        vertexConsumers: MultiBufferSource,
        light: Int,
        overlay: Int
    ) {
        renderer.render(giantGreenAnemoneBlockEntity, 1.0f, matrices, vertexConsumers, light, overlay)
    }
}