package dev.hybridlabs.aquatic.item

import net.minecraft.ChatFormatting
import net.minecraft.network.chat.Component
import net.minecraft.world.item.BlockItem
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.TooltipFlag
import net.minecraft.world.item.component.TooltipDisplay
import net.minecraft.world.level.block.Block
import java.util.function.Consumer

/**
 * Block item that optionally appends the block's `<description id>.description` line, since blocks
 * no longer receive tooltip callbacks themselves.
 */
open class HABlockItem(
    block: Block,
    properties: Properties,
    private val withDescription: Boolean = false,
) : BlockItem(block, properties) {

    override fun appendHoverText(
        stack: ItemStack,
        context: TooltipContext,
        tooltipDisplay: TooltipDisplay,
        tooltipAdder: Consumer<Component>,
        flag: TooltipFlag
    ) {
        if (withDescription) {
            tooltipAdder.accept(
                Component.translatable(this.block.descriptionId.plus(".description")).withStyle(ChatFormatting.GRAY)
            )
        }

        super.appendHoverText(stack, context, tooltipDisplay, tooltipAdder, flag)
    }
}
