package dev.hybridlabs.aquatic.item.seashell

import dev.hybridlabs.aquatic.item.HAToolMaterials
import net.minecraft.ChatFormatting
import net.minecraft.network.chat.Component
import net.minecraft.world.item.AxeItem
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.TooltipFlag
import net.minecraft.world.level.Level

class SeashellAxeItem(settings: Properties) : AxeItem(
    HAToolMaterials.SEASHELL,
    7F,
    -3.2f,
    settings) {

    override fun appendHoverText(
        stack: ItemStack,
        level: Level?,
        tooltip: MutableList<Component>,
        options: TooltipFlag
    ) {
        val text = Component.translatable(this.descriptionId.plus(".description")).withStyle(ChatFormatting.GRAY)

        tooltip.add(text)
        super.appendHoverText(stack, level, tooltip, options)
    }
}