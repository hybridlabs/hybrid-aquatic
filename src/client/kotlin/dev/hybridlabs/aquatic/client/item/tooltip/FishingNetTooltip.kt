package dev.hybridlabs.aquatic.client.item.tooltip

import dev.hybridlabs.aquatic.item.FishingNetItem
import dev.hybridlabs.aquatic.item.HybridAquaticItems
import net.minecraft.client.item.TooltipContext
import net.minecraft.item.ItemStack
import net.minecraft.text.Text
import net.minecraft.util.Formatting

class FishingNetTooltip : PredicateItemTooltipCallback(HybridAquaticItems.FISHING_NET) {
    override fun appendTooltip(stack: ItemStack, context: TooltipContext, lines: MutableList<Text>) {
        val optionalEntity = FishingNetItem.getEntityFromNet(stack)
        if (optionalEntity.isPresent) {
            val entityName = optionalEntity.get().name
            lines.add(Text.literal("Stored Entity: ").append(entityName))
        }
    }
}
