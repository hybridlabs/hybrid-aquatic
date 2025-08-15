package dev.hybridlabs.aquatic.client.item.tooltip

import dev.hybridlabs.aquatic.item.FishingNetItem
import dev.hybridlabs.aquatic.item.HybridAquaticItems
import net.minecraft.component.DataComponentTypes
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.item.tooltip.TooltipType
import net.minecraft.text.Text

class FishingNetTooltip : PredicateItemTooltipCallback(HybridAquaticItems.FISHING_NET) {

    override fun appendTooltip(
        stack: ItemStack,
        context: Item.TooltipContext,
        lines: MutableList<Text>?,
        type: TooltipType
    ) {
        val customData = stack.get(DataComponentTypes.CUSTOM_DATA)
        if (customData != null) {
            val nbtCopy = customData.copyNbt()
            val optionalEntity = FishingNetItem.getEntityFromNBT(nbtCopy)
            if (optionalEntity.isPresent) {
                val entityName = optionalEntity.get().name
                lines?.add(Text.translatable("item.hybrid-aquatic.fishing_net.description", entityName))
            }
        }
    }
}