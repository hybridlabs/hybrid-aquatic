package dev.hybridlabs.aquatic.item

import dev.hybridlabs.aquatic.block.HABlocks
import dev.hybridlabs.aquatic.block.MessageInABottleBlock
import dev.hybridlabs.aquatic.block.MessageInABottleBlock.Variant
import dev.hybridlabs.aquatic.block.entity.MessageInABottleBlockEntity
import net.minecraft.ChatFormatting
import net.minecraft.core.component.DataComponents
import net.minecraft.network.chat.Component
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.TooltipFlag


/**
 * Represents the Message in a Bottle block item.
 * @see MessageInABottleBlock
 */
class MessageInABottleItem(settings: Properties) : PlaceableInWaterItem(HABlocks.MESSAGE_IN_A_BOTTLE.get(), settings) {

    override fun appendHoverText(
        stack: ItemStack,
        context: TooltipContext,
        tooltipComponents: List<Component?>,
        tooltipFlag: TooltipFlag
    ) {
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag)
        // custom variant translation keys
        val id = stack.components[DataComponents.BLOCK_ENTITY_DATA]?.copyTag()?.getString(MessageInABottleBlockEntity.VARIANT_KEY) ?: ""
        val variant = Variant.byId(id)
        tooltipComponents + Component.translatable("$descriptionId.variant.${variant.id}").withStyle(ChatFormatting.GRAY)
        tooltipComponents + Component.translatable("$descriptionId.change_hint")
    }
}
