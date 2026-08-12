package dev.hybridlabs.aquatic.item.coral

import dev.hybridlabs.hapi.item.HAPIToolMaterials
import net.minecraft.ChatFormatting
import net.minecraft.network.chat.Component
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.ShovelItem
import net.minecraft.world.item.TooltipFlag

class CoralShovelItem(settings: Properties) : ShovelItem(
    HAPIToolMaterials.CORAL,
    settings) {

    override fun appendHoverText(
        stack: ItemStack,
        context: TooltipContext,
        tooltip: MutableList<Component>,
        options: TooltipFlag
    ) {
        val text = Component.translatable(this.descriptionId.plus(".description")).withStyle(ChatFormatting.GRAY)

        tooltip.add(text)
        super.appendHoverText(stack, context, tooltip, options)
    }
}