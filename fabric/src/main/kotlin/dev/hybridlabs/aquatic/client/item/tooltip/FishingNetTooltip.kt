package dev.hybridlabs.aquatic.client.item.tooltip

import dev.hybridlabs.aquatic.item.FishingNetItem
import dev.hybridlabs.aquatic.item.HybridAquaticItems
import net.minecraft.core.component.DataComponents
import net.minecraft.network.chat.Component
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.TooltipFlag

class FishingNetTooltip :

    PredicateItemTooltipCallback(HybridAquaticItems.FISHING_NET.get()) {
    override fun appendTooltip(stack: ItemStack, context: TooltipFlag, lines: MutableList<Component>) {
        val nbtCopy = stack.components[DataComponents.CUSTOM_DATA]
        if (nbtCopy != null) {
            val optionalEntity = FishingNetItem.getEntityFromNBT(nbtCopy)
            if (optionalEntity.isPresent) {
                val entityName = optionalEntity.get().description
                lines.add(Component.translatable("item.hybrid-aquatic.fishing_net.description", entityName))
            }
        }
    }
}