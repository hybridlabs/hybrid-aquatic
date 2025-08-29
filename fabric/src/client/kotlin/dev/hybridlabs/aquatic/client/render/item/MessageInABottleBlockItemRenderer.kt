package dev.hybridlabs.aquatic.client.render.item

import dev.hybridlabs.aquatic.HybridAquaticClient
import dev.hybridlabs.aquatic.block.HybridAquaticBlocks
import dev.hybridlabs.aquatic.block.MessageInABottleBlock
import dev.hybridlabs.aquatic.block.entity.MessageInABottleBlockEntity
import dev.hybridlabs.aquatic.client.render.block.entity.MessageInABottleBlockEntityRenderer
import net.fabricmc.fabric.api.client.rendering.v1.BuiltinItemRendererRegistry.DynamicItemRenderer
import net.minecraft.client.render.VertexConsumerProvider
import net.minecraft.client.render.model.json.ModelTransformationMode
import net.minecraft.client.util.math.MatrixStack
import net.minecraft.item.BlockItem
import net.minecraft.item.ItemStack
import net.minecraft.util.math.BlockPos

/**
 * Renders the Message in a Bottle block entity as an item.
 */
class MessageInABottleBlockItemRenderer : DynamicItemRenderer {
    private val messageInABottleBlockEntity = MessageInABottleBlockEntity(BlockPos.ORIGIN, HybridAquaticBlocks.MESSAGE_IN_A_BOTTLE.defaultState)
    private val renderer = MessageInABottleBlockEntityRenderer(HybridAquaticClient.createBlockEntityRendererFactoryContext())

    override fun render(
        stack: ItemStack,
        mode: ModelTransformationMode,
        matrices: MatrixStack,
        vertexConsumers: VertexConsumerProvider,
        light: Int,
        overlay: Int
    ) {
        messageInABottleBlockEntity.variant = MessageInABottleBlock.Variant.byId(stack.getSubNbt(BlockItem.BLOCK_ENTITY_TAG_KEY)?.getString(MessageInABottleBlockEntity.VARIANT_KEY) ?: "")
        renderer.render(messageInABottleBlockEntity, 1.0f, matrices, vertexConsumers, light, overlay)
    }
}
