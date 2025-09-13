package dev.hybridlabs.aquatic.client.render.item

import com.mojang.blaze3d.vertex.PoseStack
import dev.hybridlabs.aquatic.block.BlockEntityHelper
import dev.hybridlabs.aquatic.block.HybridAquaticBlocks
import dev.hybridlabs.aquatic.block.MessageInABottleBlock
import dev.hybridlabs.aquatic.block.entity.MessageInABottleBlockEntity
import dev.hybridlabs.aquatic.client.render.block.entity.MessageInABottleBlockEntityRenderer
import net.fabricmc.fabric.api.client.rendering.v1.BuiltinItemRendererRegistry.DynamicItemRenderer
import net.minecraft.client.renderer.MultiBufferSource
import net.minecraft.core.BlockPos
import net.minecraft.world.item.BlockItem
import net.minecraft.world.item.ItemDisplayContext
import net.minecraft.world.item.ItemStack

class MessageInABottleBlockItemRenderer : DynamicItemRenderer {
    private val messageInABottleBlockEntity =
        MessageInABottleBlockEntity(BlockPos.ZERO, HybridAquaticBlocks.MESSAGE_IN_A_BOTTLE.get().defaultBlockState())
    private val renderer =
        MessageInABottleBlockEntityRenderer(BlockEntityHelper.createBlockEntityRendererProviderContext())

    override fun render(
        stack: ItemStack,
        mode: ItemDisplayContext,
        matrices: PoseStack,
        vertexConsumers: MultiBufferSource,
        light: Int,
        overlay: Int
    ) {
        messageInABottleBlockEntity.variant = MessageInABottleBlock.Variant.byId(
            stack.getTagElement(BlockItem.BLOCK_ENTITY_TAG)?.getString(MessageInABottleBlockEntity.VARIANT_KEY) ?: ""
        )
        renderer.render(messageInABottleBlockEntity, 1.0f, matrices, vertexConsumers, light, overlay)
    }
}