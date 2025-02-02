package dev.hybridlabs.aquatic.client.item.tooltip

import dev.hybridlabs.aquatic.item.HybridAquaticItems
import net.minecraft.item.Item.TooltipContext
import net.minecraft.item.ItemStack
import net.minecraft.text.Text

class FishingNetTooltip : PredicateItemTooltipCallback(HybridAquaticItems.FISHING_NET) {
    override fun appendTooltip(stack: ItemStack, context: TooltipContext, lines: MutableList<Text>) {
        /*val nbtCopy = stack.nbt?.copy()
        if (nbtCopy != null) {
            val optionalEntity = FishingNetItem.getEntityFromNbt(nbtCopy)
            if (optionalEntity.isPresent) {
                val entityName = optionalEntity.get().name
                lines.add(Text.translatable("item.hybrid-aquatic.fishing_net.description", entityName))
            }
        } TODO */
    }
}
