package dev.hybridlabs.aquatic.client.item.tooltip

import dev.hybridlabs.aquatic.entity.misc.ArgonautEntity
import dev.hybridlabs.aquatic.item.HybridAquaticItems
import net.minecraft.network.chat.Component
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.TooltipFlag

class ArgonautTooltip :
    PredicateItemTooltipCallback(HybridAquaticItems.ARGONAUT.get()) {

    override fun appendTooltip(stack: ItemStack, context: TooltipFlag, lines: MutableList<Component>) {
        val tag = stack.tag ?: return

        if (tag.contains("ShellColor")) {
            val color = ArgonautEntity.ShellColor.byId(tag.getInt("ShellColor"))
            lines.add(Component.translatable("tooltip.hybrid-aquatic.argonaut.shell", color.name.uppercase()))
        }

        if (tag.contains("SailColor")) {
            val color = ArgonautEntity.SailColor.byId(tag.getInt("SailColor"))
            lines.add(Component.translatable("tooltip.hybrid-aquatic.argonaut.sail", color.name.uppercase()))
        }

        if (tag.contains("Glowing") && tag.getBoolean("Glowing")) {
            lines.add(Component.translatable("tooltip.hybrid-aquatic.argonaut.glowing"))
        }
    }
}