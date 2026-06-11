package dev.hybridlabs.aquatic.item

import dev.hybridlabs.aquatic.platform.Services
import net.minecraft.ChatFormatting
import net.minecraft.network.chat.Component
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.TooltipFlag

open class HookItem(settings: Properties) : Item(settings) {
    override fun appendHoverText(
        stack: ItemStack,
        context: TooltipContext,
        tooltip: MutableList<Component>,
        tooltipFlag: TooltipFlag
    ) {
        val isTideLoaded = Services.PLATFORM.isModLoaded("tide")

        val hookDescription = Component.translatable(this.descriptionId.plus(".description")).withStyle(ChatFormatting.GRAY)
        val globalHookDescription = Component.translatable("item.hybrid_aquatic.hook.description${ if (isTideLoaded) "_tide" else "" }").withStyle(ChatFormatting.GRAY)

        tooltip.add(hookDescription)
        tooltip.add(globalHookDescription)

        super.appendHoverText(stack, context, tooltip, tooltipFlag)
    }

    override fun isEnchantable(stack: ItemStack): Boolean {
        return false
    }

    override fun getEnchantmentValue(): Int {
        return 0
    }
}
