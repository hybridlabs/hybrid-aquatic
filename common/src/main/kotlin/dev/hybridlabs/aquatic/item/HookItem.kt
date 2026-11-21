package dev.hybridlabs.aquatic.item

import dev.hybridlabs.aquatic.platform.Services
import net.minecraft.ChatFormatting
import net.minecraft.network.chat.Component
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.component.TooltipDisplay
import java.util.function.Consumer
import net.minecraft.world.item.TooltipFlag

open class HookItem(settings: Properties) : Item(settings) {
    override fun appendHoverText(
        stack: ItemStack,
        context: TooltipContext,
        tooltipDisplay: TooltipDisplay,
        tooltip: Consumer<Component>,
        tooltipFlag: TooltipFlag
    ) {
        val isTideLoaded = Services.PLATFORM.isModLoaded("tide")

        val hookDescription = Component.translatable(this.descriptionId.plus(".description")).withStyle(ChatFormatting.GRAY)
        val globalHookDescription = Component.translatable("item.hybrid_aquatic.hook.description${ if (isTideLoaded) "_tide" else "" }").withStyle(ChatFormatting.GRAY)

        tooltip.accept(hookDescription)
        tooltip.accept(globalHookDescription)

        super.appendHoverText(stack, context, tooltipDisplay, tooltip, tooltipFlag)
    }


}
