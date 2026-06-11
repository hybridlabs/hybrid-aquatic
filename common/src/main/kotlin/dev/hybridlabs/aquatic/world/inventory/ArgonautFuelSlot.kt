package dev.hybridlabs.aquatic.world.inventory

import net.minecraft.world.Container
import net.minecraft.world.inventory.Slot
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.Items

data class ArgonautFuelSlot(
    val argonautMenu: ArgonautMenu,
    val argonautContainer: Container,
    val slot: Int,
    val xPos: Int,
    val yPos: Int
): Slot(argonautContainer, slot, xPos, yPos) {

    override fun mayPlace(stack: ItemStack): Boolean {
        return this.argonautMenu.isFuel(stack) || isBucket(stack)
    }

    override fun getMaxStackSize(stack: ItemStack): Int {
        return if (isBucket(stack)) 1 else super.getMaxStackSize(stack)
    }

    fun isBucket(stack: ItemStack): Boolean {
        return stack.`is`(Items.BUCKET)
    }
}
