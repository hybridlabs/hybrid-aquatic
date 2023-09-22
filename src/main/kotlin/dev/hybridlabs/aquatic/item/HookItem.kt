package dev.hybridlabs.aquatic.item

import net.minecraft.client.item.TooltipContext
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.text.Text
import net.minecraft.util.Formatting
import net.minecraft.world.World

open class HookItem(settings: Settings) : Item(settings) {
    override fun appendTooltip(stack: ItemStack, world: World?, tooltip: MutableList<Text>, context: TooltipContext) {
        val text = Text.translatable(this.translationKey.plus(".description")).formatted(Formatting.GRAY)
        val hookText = Text.translatable("item.hybrid-aquatic.hook.description").formatted(Formatting.GRAY)

        tooltip.add(text)
        tooltip.add(hookText)
        super.appendTooltip(stack, world, tooltip, context)
    }

    override fun isEnchantable(stack: ItemStack?): Boolean {
        return false
    }

    override fun getEnchantability(): Int {
        return 0
    }
}