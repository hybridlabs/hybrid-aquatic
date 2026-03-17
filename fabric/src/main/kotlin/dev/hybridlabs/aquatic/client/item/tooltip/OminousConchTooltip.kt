package dev.hybridlabs.aquatic.client.item.tooltip

import dev.hybridlabs.aquatic.item.HybridAquaticItems
import net.minecraft.ChatFormatting
import net.minecraft.network.chat.Component
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.TooltipFlag

class OminousConchTooltip :
    PredicateItemTooltipCallback(HybridAquaticItems.OMINOUS_CONCH.get()) {

    override fun appendTooltip(stack: ItemStack, context: TooltipFlag, lines: MutableList<Component>) {
        val tag = stack.tag

        lines.add(
            Component.translatable("item.hybrid-aquatic.ominous_conch.function")
                .withStyle(ChatFormatting.GRAY)
        )

        val hasSummoned = tag?.getBoolean("hasSummoned") == true

        if (!hasSummoned) {
            lines.add(
                Component.translatable("tooltip.hybrid-aquatic.ominous_conch.unused")
                    .withStyle(ChatFormatting.DARK_PURPLE)
            )
        } else {
            lines.add(
                Component.translatable("tooltip.hybrid-aquatic.ominous_conch.used")
                    .withStyle(ChatFormatting.GRAY)
            )
        }
    }
}