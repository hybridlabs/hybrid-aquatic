package dev.hybridlabs.aquatic.item

import dev.hybridlabs.aquatic.block.HABlocks
import dev.hybridlabs.aquatic.block.MessageInABottleBlock
import dev.hybridlabs.aquatic.block.MessageInABottleBlock.Variant
import dev.hybridlabs.aquatic.block.entity.MessageInABottleBlockEntity
import dev.hybridlabs.hapi.item.PlaceableInWaterItem
import net.minecraft.ChatFormatting
import net.minecraft.network.chat.Component
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.TooltipFlag
import net.minecraft.world.level.Level

/**
 * Represents the Message in a Bottle block item.
 * @see MessageInABottleBlock
 */
class MessageInABottleItem(settings: Properties) : PlaceableInWaterItem(HABlocks.MESSAGE_IN_A_BOTTLE.get(), settings) {
    override fun appendHoverText(stack: ItemStack, level: Level?, tooltip: MutableList<Component>, flag: TooltipFlag) {
        super.appendHoverText(stack, level, tooltip, flag)

        // custom variant translation keys
        val id = stack.getTagElement(BLOCK_ENTITY_TAG)?.getString(MessageInABottleBlockEntity.VARIANT_KEY) ?: ""
        val variant = Variant.byId(id)
        tooltip.add(Component.translatable("$descriptionId.variant.${variant.id}").withStyle(ChatFormatting.GRAY))
        tooltip.add(Component.translatable("$descriptionId.change_hint"))
    }
}
