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

    init {
        checkContainerSize(container, rows * SLOTS_PER_ROW + 1)
        this.container = container
        this.rowCount = rows
        container.startOpen(playerInventory.player)
        val inventoryOffset = (this.rowCount - 4) * 18

        for (argonautRow in 0..<this.rowCount) {
            for (argonautColumn in 0..<SLOTS_PER_ROW) {
                this.addSlot(Slot(container, argonautColumn + argonautRow * SLOTS_PER_ROW, LEFT_PIXEL_TO_SLOT + argonautColumn * 18, 18 + argonautRow * 18))
            }
        }
        this.addSlot(Slot(container, rows * SLOTS_PER_ROW /* count from 0*/, LEFT_PIXEL_TO_SLOT + 72, -36)) // 28th fuel slot

        for (playerRow in 0..2) {
            for (playerColumn in 0..8) {
                this.addSlot(Slot(playerInventory, playerColumn + playerRow * 9 + 9, LEFT_PIXEL_TO_SLOT + playerColumn * 18, 103 + playerRow * 18 + inventoryOffset))
            }
        }

        for (playerHotbar in 0..8) {
            this.addSlot(Slot(playerInventory, playerHotbar, LEFT_PIXEL_TO_SLOT + playerHotbar * 18, 161 + inventoryOffset))
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
            if (index < this.rowCount * SLOTS_PER_ROW) {
                if (!this.moveItemStackTo(itemstack1, this.rowCount * SLOTS_PER_ROW, this.slots.size, true)) {
                    return ItemStack.EMPTY
                }
            } else if (!this.moveItemStackTo(itemstack1, 0, this.rowCount * SLOTS_PER_ROW, false)) {
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
        private const val LEFT_PIXEL_TO_SLOT = 11

        fun twoRows(containerId: Int, playerInventory: Inventory, container: Container): ArgonautMenu {
            return ArgonautMenu(
                HAMenuTypes.ARGONAUT_MENU_2ROW.get(),
                containerId,
                playerInventory,
                container,
                2
            )
        }

        fun twoRows(containerId: Int, playerInventory: Inventory): ArgonautMenu {
            return ArgonautMenu(
                HAMenuTypes.ARGONAUT_MENU_2ROW.get(),
                containerId,
                playerInventory,
                SimpleContainer(SLOTS_PER_ROW * 2 + 1),
                2
            )
        }

        fun threeRows(containerId: Int, playerInventory: Inventory): ArgonautMenu {
            return ArgonautMenu(
                HAMenuTypes.ARGONAUT_MENU_3ROW.get(),
                containerId,
                playerInventory,
                SimpleContainer(SLOTS_PER_ROW * 3 + 1),
                3
            )
        }

        fun threeRows(containerId: Int, playerInventory: Inventory, container: Container): ArgonautMenu {
            return ArgonautMenu(
                HAMenuTypes.ARGONAUT_MENU_3ROW.get(),
                containerId,
                playerInventory,
                container,
                3
            )
        }
    }
}