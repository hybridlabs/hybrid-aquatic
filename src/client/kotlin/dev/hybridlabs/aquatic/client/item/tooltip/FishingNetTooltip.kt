package dev.hybridlabs.aquatic.client.item.tooltip

import dev.hybridlabs.aquatic.item.HybridAquaticItems
import net.minecraft.client.item.TooltipContext
import net.minecraft.item.ItemStack
import net.minecraft.text.Text
import net.minecraft.util.Formatting

class FishingNetTooltip : PredicateItemTooltipCallback(HybridAquaticItems.FISHING_NET) {
    override fun appendTooltip(stack: ItemStack, context: TooltipContext, lines: MutableList<Text>) {
        lines.add(Text.literal("Test tooltip!").formatted(Formatting.entries.random()))
    }
}
