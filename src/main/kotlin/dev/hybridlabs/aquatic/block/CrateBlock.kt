package dev.hybridlabs.aquatic.block

import net.minecraft.block.Block
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.item.tooltip.TooltipType
import net.minecraft.text.Text
import net.minecraft.util.Formatting

class CrateBlock(settings: Settings): Block(settings) {
    override fun appendTooltip(
        stack: ItemStack,
        context: Item.TooltipContext,
        tooltip: MutableList<Text>,
        options: TooltipType
    ) {
        val text = Text.translatable(this.translationKey.plus(".description")).formatted(Formatting.GRAY)
        tooltip.add(text)

        super.appendTooltip(stack, context, tooltip, options)
    }
}
