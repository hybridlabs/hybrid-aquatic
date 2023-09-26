package dev.hybridlabs.aquatic.block

import net.minecraft.block.Block
import net.minecraft.client.item.TooltipContext
import net.minecraft.item.ItemStack
import net.minecraft.text.Text
import net.minecraft.util.Formatting
import net.minecraft.world.BlockView

class CrateBlock(settings: Settings): Block(settings) {
    override fun appendTooltip(
        stack: ItemStack,
        world: BlockView?,
        tooltip: MutableList<Text>,
        options: TooltipContext
    ) {
        val text = Text.translatable(this.translationKey.plus(".description")).formatted(Formatting.GRAY)

        tooltip.add(text)
        super.appendTooltip(stack, world, tooltip, options)
    }
}