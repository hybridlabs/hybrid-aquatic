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
        level: Level?,
        lines: MutableList<Component>,
        context: TooltipFlag
    ) {
        lines.add(Component.translatable("item.hybrid-aquatic.diving_weight.description").withStyle(ChatFormatting.GRAY)
        )
    }
}