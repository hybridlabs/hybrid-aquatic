package dev.hybridlabs.aquatic.item

import net.minecraft.client.item.TooltipType
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.text.Text
import net.minecraft.util.Formatting

open class HookItem(settings: Settings) : Item(settings) {
    override fun appendTooltip(stack: ItemStack, context: TooltipContext, tooltip: MutableList<Text>, type: TooltipType) {
        val text = Text.translatable(this.translationKey.plus(".description")).formatted(Formatting.GRAY)
        val hookText = Text.translatable("item.hybrid-aquatic.hook.description").formatted(Formatting.GRAY)

        tooltip.add(text)
        tooltip.add(hookText)
        super.appendTooltip(stack, context, tooltip, type)
    }

    override fun isEnchantable(stack: ItemStack?): Boolean {
        return false
    }

    override fun getEnchantability(): Int {
        return 0
    }
}
