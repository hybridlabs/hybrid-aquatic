package dev.hybridlabs.aquatic.item.coral

import dev.hybridlabs.hapi.item.HAPIToolMaterials
import net.minecraft.ChatFormatting
import net.minecraft.network.chat.Component
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.component.TooltipDisplay
import java.util.function.Consumer
import net.minecraft.world.item.TooltipFlag
import net.minecraft.world.level.Level

class CoralHoeItem(settings: Properties) : Item(settings) {

    override fun appendHoverText(
        stack: ItemStack,
        context: TooltipContext,
        tooltipDisplay: TooltipDisplay,
        tooltip: Consumer<Component>,
        options: TooltipFlag
    ) {
        val text = Component.translatable(this.descriptionId.plus(".description")).withStyle(ChatFormatting.GRAY)

        tooltip.accept(text)
        super.appendHoverText(stack, context, tooltipDisplay, tooltip, options)
    }
}