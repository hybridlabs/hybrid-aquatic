package dev.hybridlabs.aquatic.client.item.tooltip

import dev.hybridlabs.aquatic.item.HybridAquaticItems
import net.minecraft.ChatFormatting
import net.minecraft.network.chat.Component
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.TooltipFlag

class DivingWeightTooltip :
    PredicateItemTooltipCallback(HybridAquaticItems.DIVING_WEIGHT.get()) {

    override fun appendTooltip(stack: ItemStack, context: TooltipFlag, lines: MutableList<Component>) {
        lines.add(Component.translatable("item.hybrid-aquatic.diving_weight.description").withStyle(ChatFormatting.GRAY)
        )
    }
}