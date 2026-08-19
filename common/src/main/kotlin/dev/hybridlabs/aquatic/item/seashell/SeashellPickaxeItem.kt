package dev.hybridlabs.aquatic.item.seashell

import dev.hybridlabs.hapi.item.HAPIToolMaterials
import net.minecraft.ChatFormatting
import net.minecraft.network.chat.Component
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.PickaxeItem
import net.minecraft.world.item.TooltipFlag
import net.minecraft.world.level.Level

class SeashellPickaxeItem(settings: Properties) : PickaxeItem(
    HAPIToolMaterials.SEASHELL,
    1,
    -2.8f,
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