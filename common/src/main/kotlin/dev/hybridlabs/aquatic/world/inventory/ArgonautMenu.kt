package dev.hybridlabs.aquatic.world.inventory

import net.minecraft.world.Container
import net.minecraft.world.SimpleContainer
import net.minecraft.world.entity.player.Inventory
import net.minecraft.world.entity.player.Player
import net.minecraft.world.inventory.AbstractContainerMenu
import net.minecraft.world.inventory.MenuType
import net.minecraft.world.inventory.Slot
import net.minecraft.world.item.ItemStack

class ArgonautMenu(type: MenuType<*>, containerId: Int, playerInventory: Inventory, container: Container, rows: Int) :
    AbstractContainerMenu(type, containerId) {
    val container: Container
    val rowCount: Int

    constructor(containerId: Int, playerInventory: Inventory) : this(
        HybridAquaticMenuTypes.ARGONAUT_MENU.get(),
        containerId,
        playerInventory,
        SimpleContainer(9 * 3 + 1),
        3
    )

    init {
        checkContainerSize(container, rows * 9)
        this.container = container
        this.rowCount = rows
        container.startOpen(playerInventory.player)
        val i = (this.rowCount - 4) * 18

        for (j in 0..<this.rowCount) {
            for (k in 0..8) {
                this.addSlot(Slot(container, k + j * 9, 8 + k * 18, 18 + j * 18))
            }
        }

        for (l in 0..2) {
            for (j1 in 0..8) {
                this.addSlot(Slot(playerInventory, j1 + l * 9 + 9, 8 + j1 * 18, 103 + l * 18 + i))
            }
        }

        for (i1 in 0..8) {
            this.addSlot(Slot(playerInventory, i1, 8 + i1 * 18, 161 + i))
        }
    }

    override fun stillValid(player: Player): Boolean {
        return this.container.stillValid(player)
    }

    override fun quickMoveStack(player: Player, index: Int): ItemStack {
        var itemstack = ItemStack.EMPTY
        val slot = this.slots[index]
        if (slot.hasItem()) {
            val itemstack1 = slot.item
            itemstack = itemstack1.copy()
            if (index < this.rowCount * 9) {
                if (!this.moveItemStackTo(itemstack1, this.rowCount * 9, this.slots.size, true)) {
                    return ItemStack.EMPTY
                }
            } else if (!this.moveItemStackTo(itemstack1, 0, this.rowCount * 9, false)) {
                return ItemStack.EMPTY
            }

            if (itemstack1.isEmpty) {
                slot.setByPlayer(ItemStack.EMPTY)
            } else {
                slot.setChanged()
            }
        }

        return itemstack
    }

    override fun removed(player: Player) {
        super.removed(player)
        this.container.stopOpen(player)
    }

    companion object {
        private const val SLOTS_PER_ROW = 9

        fun twoRows(containerId: Int, playerInventory: Inventory, container: Container): ArgonautMenu {
            return ArgonautMenu(MenuType.GENERIC_9x2, containerId, playerInventory, container, 2)
        }
    }
}