package dev.hybridlabs.aquatic.item.coral

import dev.hybridlabs.aquatic.item.HAToolMaterials
import net.minecraft.ChatFormatting
import net.minecraft.network.chat.Component
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.ShovelItem
import net.minecraft.world.item.TooltipFlag
import net.minecraft.world.level.Level

class CoralShovelItem(settings: Properties) : ShovelItem(
    HAToolMaterials.CORAL,
    1.5F,
    -3.0f,
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