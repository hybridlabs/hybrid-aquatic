package dev.hybridlabs.aquatic.client.item.tooltip

import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback
import net.minecraft.client.item.TooltipContext
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.predicate.item.ItemPredicate
import net.minecraft.text.Text

/**
 * An item tooltip that is only appended if [itemPredicate] is passed.
 */
abstract class PredicateItemTooltipCallback(
    /**
     * The item predicate.
     */
    private val itemPredicate: ItemPredicate
) : ItemTooltipCallback {
    /**
     * A secondary constructor for an item predicate builder.
     */
    constructor(
        /**
         * The builder of the item predicate.
         */
        itemPredicate: ItemPredicate.Builder
    ) : this(itemPredicate.build())

    /**
     * A secondary constructor for matching a set of items.
     */
    constructor(vararg items: Item) : this(ItemPredicate.Builder.create().items(*items))

    override fun getTooltip(stack: ItemStack, context: TooltipContext, lines: MutableList<Text>) {
        if (itemPredicate.test(stack)) {
            appendTooltip(stack, context, lines)
        }
    }

    abstract fun appendTooltip(stack: ItemStack, context: TooltipContext, lines: MutableList<Text>)
}
