package dev.hybridlabs.aquatic.client.renderer.item

import com.mojang.blaze3d.vertex.PoseStack
import dev.hybridlabs.aquatic.block.BlockEntityHelper
import dev.hybridlabs.aquatic.block.HybridAquaticBlocks
import dev.hybridlabs.aquatic.block.entity.StrawberryAnemoneBlockEntity
import dev.hybridlabs.aquatic.client.render.block.entity.StrawberryAnemoneBlockEntityRenderer
import net.fabricmc.fabric.api.client.rendering.v1.BuiltinItemRendererRegistry.DynamicItemRenderer
import net.minecraft.client.renderer.MultiBufferSource
import net.minecraft.core.BlockPos
import net.minecraft.world.item.ItemDisplayContext
import net.minecraft.world.item.ItemStack

class StrawberryAnemoneBlockItemRenderer : DynamicItemRenderer {
    private val strawberryAnemoneBlockEntity =
        StrawberryAnemoneBlockEntity(BlockPos.ZERO, HybridAquaticBlocks.STRAWBERRY_ANEMONE.get().defaultBlockState())
    private val renderer =
        StrawberryAnemoneBlockEntityRenderer(BlockEntityHelper.createBlockEntityRendererProviderContext())

    override fun render(
        stack: ItemStack,
        mode: ItemDisplayContext,
        matrices: PoseStack,
        vertexConsumers: MultiBufferSource,
        light: Int,
        overlay: Int
    ) {
        renderer.render(strawberryAnemoneBlockEntity, 1.0f, matrices, vertexConsumers, light, overlay)
    }
}