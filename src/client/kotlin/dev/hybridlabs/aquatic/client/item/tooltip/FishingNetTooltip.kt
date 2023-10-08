package dev.hybridlabs.aquatic.client.item.tooltip

import dev.hybridlabs.aquatic.item.FishingNetItem
import dev.hybridlabs.aquatic.item.HybridAquaticItems
import net.minecraft.client.item.TooltipContext
import net.minecraft.item.ItemStack
import net.minecraft.text.Text

class FishingNetTooltip : PredicateItemTooltipCallback(HybridAquaticItems.FISHING_NET) {
    override fun appendTooltip(stack: ItemStack, context: TooltipContext, lines: MutableList<Text>) {
        val nbtCopy = stack.nbt?.copy()
        if (nbtCopy != null) {
            val optionalEntity = FishingNetItem.getEntityFromNBT(nbtCopy)
            if (optionalEntity.isPresent) {
                val entityName = optionalEntity.get().name
                lines.add(Text.literal("Stored Entity: ").append(entityName))
            }
        }
    }
}
