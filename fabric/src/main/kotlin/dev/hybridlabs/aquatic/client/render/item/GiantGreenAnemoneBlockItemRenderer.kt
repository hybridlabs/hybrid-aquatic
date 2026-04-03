package dev.hybridlabs.aquatic.client.render.item

import com.mojang.blaze3d.vertex.PoseStack
import dev.hybridlabs.aquatic.block.BlockEntityHelper
import dev.hybridlabs.aquatic.block.HABlocks
import dev.hybridlabs.aquatic.block.entity.GiantGreenAnemoneBlockEntity
import dev.hybridlabs.aquatic.client.render.block.entity.GiantGreenAnemoneBlockEntityRenderer
import net.fabricmc.fabric.api.client.rendering.v1.BuiltinItemRendererRegistry.DynamicItemRenderer
import net.minecraft.client.renderer.MultiBufferSource
import net.minecraft.core.BlockPos
import net.minecraft.world.item.ItemDisplayContext
import net.minecraft.world.item.ItemStack

class GiantGreenAnemoneBlockItemRenderer : DynamicItemRenderer {
    private val giantGreenAnemoneBlockEntity =
        GiantGreenAnemoneBlockEntity(BlockPos.ZERO, HABlocks.GIANT_GREEN_ANEMONE.get().defaultBlockState())
    private val renderer =
        GiantGreenAnemoneBlockEntityRenderer(BlockEntityHelper.createBlockEntityRendererProviderContext())

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