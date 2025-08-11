package dev.hybridlabs.aquatic.item

import net.fabricmc.loader.api.FabricLoader
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.item.tooltip.TooltipType
import net.minecraft.text.Text
import net.minecraft.util.Formatting

open class HookItem(settings: Settings) : Item(settings) {
    override fun appendTooltip(
        stack: ItemStack,
        context: TooltipContext,
        tooltip: MutableList<Text>,
        type: TooltipType
    ) {
        val isTideLoaded = FabricLoader.getInstance().isModLoaded("tide")

        if (isTideLoaded) {
            val tideText = Text.translatable(this.translationKey.plus(".description")).formatted(Formatting.GRAY)
            val hookTideText = Text.translatable("item.hybrid-aquatic.hook.description_tide").formatted(Formatting.GRAY)

            tooltip.add(tideText)
            tooltip.add(hookTideText)
        } else {
            val text = Text.translatable(this.translationKey.plus(".description")).formatted(Formatting.GRAY)
            val hookText = Text.translatable("item.hybrid-aquatic.hook.description").formatted(Formatting.GRAY)

            tooltip.add(text)
            tooltip.add(hookText)
        }

        super.appendTooltip(stack, context, tooltip, type)
    }

    override fun isEnchantable(stack: ItemStack?): Boolean {
        return false
    }

    override fun getEnchantability(): Int {
        return 0
    }
}