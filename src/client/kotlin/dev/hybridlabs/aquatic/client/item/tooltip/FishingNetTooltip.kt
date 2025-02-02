package dev.hybridlabs.aquatic.client.item.tooltip

import dev.hybridlabs.aquatic.item.HybridAquaticItems
import net.minecraft.component.DataComponentTypes
import net.minecraft.entity.EntityType
import net.minecraft.item.Item.TooltipContext
import net.minecraft.item.ItemStack
import net.minecraft.text.Text

class FishingNetTooltip : PredicateItemTooltipCallback(HybridAquaticItems.FISHING_NET) {
    override fun appendTooltip(stack: ItemStack, context: TooltipContext, lines: MutableList<Text>) {
        stack.get(DataComponentTypes.ENTITY_DATA)?.copyNbt()?.also { entityNbt ->
            EntityType.fromNbt(entityNbt)?.orElse(null)?.also { entity ->
                val entityName = entity.name
                lines.add(Text.translatable("item.hybrid-aquatic.fishing_net.description", entityName))
            }
        }
    }
}
