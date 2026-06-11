package dev.hybridlabs.aquatic.item

import net.minecraft.ChatFormatting
import net.minecraft.network.chat.Component
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.TooltipFlag
import net.minecraft.world.level.Level

class DivingWeightItem(settings: Properties) : Item(settings) {

    override fun appendHoverText(
        stack: ItemStack,
        context: TooltipContext,
        lines: MutableList<Component>,
        tooltipFlag: TooltipFlag
    ) {
        val text = Component.translatable(this.descriptionId.plus(".description")).withStyle(ChatFormatting.GRAY)

        lines.add(text)
        super.appendHoverText(stack, context, lines, context)
    }
}