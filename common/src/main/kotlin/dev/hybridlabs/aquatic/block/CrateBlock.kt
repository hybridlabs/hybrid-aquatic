package dev.hybridlabs.aquatic.block

import net.minecraft.ChatFormatting
import net.minecraft.network.chat.Component
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.TooltipFlag
import net.minecraft.world.level.block.Block


class CrateBlock(settings: Properties): Block(settings) {
    override fun appendHoverText(
        stack: ItemStack,
        context: Item.TooltipContext,
        tooltip: MutableList<Component>,
        options: TooltipFlag
    ) {
        val text = Component.translatable(this.descriptionId.plus(".description")).withStyle(ChatFormatting.GRAY)

        tooltip.add(text)
        super.appendHoverText(stack, context, tooltip, options)
    }
}