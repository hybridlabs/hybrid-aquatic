package dev.hybridlabs.aquatic.item

import dev.hybridlabs.aquatic.platform.Services
import net.minecraft.ChatFormatting
import net.minecraft.network.chat.Component
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.TooltipFlag
import net.minecraft.world.level.Level

open class HookItem(settings: Properties) : Item(settings) {
    override fun appendHoverText(stack: ItemStack, world: Level?, tooltip: MutableList<Component>, context: TooltipFlag) {
        val isTideLoaded = Services.PLATFORM.isModLoaded("tide")

        val hookDescription = Component.translatable(this.descriptionId.plus(".description")).withStyle(ChatFormatting.GRAY)
        val globalHookDescription = Component.translatable("item.hybrid-aquatic.hook.description${ if (isTideLoaded) "_tide" else "" }").withStyle(ChatFormatting.GRAY)

        tooltip.add(hookDescription)
        tooltip.add(globalHookDescription)

        super.appendHoverText(stack, world, tooltip, context)
    }

    override fun isEnchantable(stack: ItemStack): Boolean {
        return false
    }

    override fun getEnchantmentValue(): Int {
        return 0
    }
}