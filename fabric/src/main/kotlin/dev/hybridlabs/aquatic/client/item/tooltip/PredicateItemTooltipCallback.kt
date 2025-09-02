package dev.hybridlabs.aquatic.client.item.tooltip

import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback
import net.minecraft.advancements.critereon.ItemPredicate
import net.minecraft.network.chat.Component
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.TooltipFlag

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
    constructor(vararg items: Item) : this(ItemPredicate.Builder.item().of(*items))

    override fun getTooltip(stack: ItemStack, context: TooltipFlag, lines: MutableList<Component>) {
        if (itemPredicate.matches(stack)) {
            appendTooltip(stack, context, lines)
        }
    }

    abstract fun appendTooltip(stack: ItemStack, context: TooltipFlag, lines: MutableList<Component>)
}
