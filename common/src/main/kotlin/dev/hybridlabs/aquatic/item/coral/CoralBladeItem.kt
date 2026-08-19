package dev.hybridlabs.aquatic.item.coral

import dev.hybridlabs.hapi.item.HAPIToolMaterials
import net.minecraft.ChatFormatting
import net.minecraft.network.chat.Component
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.SwordItem
import net.minecraft.world.item.TooltipFlag
import net.minecraft.world.level.Level

class CoralBladeItem(settings: Properties) : SwordItem(
    HAPIToolMaterials.CORAL,
    3,
    -2.4f,
    settings) {

    override fun appendHoverText(
        stack: ItemStack,
        level: Level?,
        tooltip: MutableList<Component>,
        options: TooltipFlag,
    ) {
        val text = Component.translatable(this.descriptionId.plus(".description")).withStyle(ChatFormatting.GRAY)

        tooltip.add(text)
        super.appendHoverText(stack, level, tooltip, options)
    }
}