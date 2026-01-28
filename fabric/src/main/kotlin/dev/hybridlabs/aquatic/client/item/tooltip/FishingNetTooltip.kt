package dev.hybridlabs.aquatic.client.item.tooltip

import dev.hybridlabs.aquatic.item.FishingNetItem
import dev.hybridlabs.aquatic.item.HybridAquaticItems
import net.minecraft.network.chat.Component
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.TooltipFlag

class FishingNetTooltip :

    PredicateItemTooltipCallback(HybridAquaticItems.FISHING_NET.get()) {
    override fun appendTooltip(stack: ItemStack, context: TooltipFlag, lines: MutableList<Component>) {
        val nbtCopy = stack.tag?.copy()
        if (nbtCopy != null) {
            FishingNetItem.getEntityFromNBT(nbtCopy)?.let { entityType ->
                val entityName = entityType.description
                lines.add(Component.translatable("item.hybrid-aquatic.fishing_net.description", entityName))
            }
        }
    }
}
