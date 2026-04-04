package dev.hybridlabs.aquatic.world.inventory

import net.minecraft.world.Container
import net.minecraft.world.SimpleContainer
import net.minecraft.world.entity.player.Inventory
import net.minecraft.world.entity.player.Player
import net.minecraft.world.inventory.*
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity

class ArgonautMenu(type: MenuType<*>, containerId: Int, playerInventory: Inventory, container: Container, rows: Int, val containerData: ContainerData) :
    AbstractContainerMenu(type, containerId) {
    val container: Container
    val rowCount: Int

    init {
        checkContainerSize(container, rows * SLOTS_PER_ROW + 1)
        checkContainerDataCount(containerData, 2)
        this.container = container
        this.rowCount = rows
        container.startOpen(playerInventory.player)
        val playerInventoryOffset = (this.rowCount - 4) * 18

        this.addSlot(ArgonautFuelSlot(
            this,
            container,
            0,
            LEFT_PIXEL_TO_SLOT + 72,
            TOP_OFFSET + (-36)
        ))

        for (argonautRow in 0..<this.rowCount) {
            for (argonautColumn in 0..<SLOTS_PER_ROW) {
                this.addSlot(Slot(container,
                    (argonautColumn + argonautRow * SLOTS_PER_ROW) + 1, // fuel slot is index 0 now
                    LEFT_PIXEL_TO_SLOT + argonautColumn * 18,
                    TOP_OFFSET + (18 + argonautRow * 18)
                ))
            }
        }

        for (playerRow in 0..2) {
            for (playerColumn in 0..8) {
                this.addSlot(Slot(playerInventory,
                    playerColumn + playerRow * 9 + 9,
                    LEFT_PIXEL_TO_SLOT + playerColumn * 18,
                    TOP_OFFSET + (103 + playerRow * 18 + playerInventoryOffset)
                ))
            }
        }

        for (playerHotbar in 0..8) {
            this.addSlot(Slot(
                playerInventory,
                playerHotbar,
                LEFT_PIXEL_TO_SLOT + playerHotbar * 18,
                TOP_OFFSET + (161 + playerInventoryOffset)))
        }

        addDataSlots(containerData)
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

            if (index <= this.rowCount * SLOTS_PER_ROW) {
                if (!this.moveItemStackTo(itemstack1, this.rowCount * SLOTS_PER_ROW, this.slots.size, true)) {
                    return ItemStack.EMPTY
                }
            } else if (!this.moveItemStackTo(itemstack1, 0, this.rowCount * SLOTS_PER_ROW + 1, false)) {
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

    fun isFuel(stack: ItemStack): Boolean {
        return AbstractFurnaceBlockEntity.isFuel(stack)
    }

    fun getBurnProgress(): Int {
        val litTime: Int = containerData.get(0)
        val litDuration: Int = containerData.get(1)
        return if (litDuration != 0 && litTime != 0) litTime * 24 / litDuration else 0
    }

    companion object {
        private const val SLOTS_PER_ROW = 9
        private const val LEFT_PIXEL_TO_SLOT = 11
        private const val TOP_OFFSET = 54

        fun twoRows(containerId: Int, playerInventory: Inventory, container: Container, containerData: ContainerData): ArgonautMenu {
            return ArgonautMenu(
                HAMenuTypes.ARGONAUT_MENU_2ROW.get(),
                containerId,
                playerInventory,
                container,
                2,
                containerData
            )
        }

        fun twoRows(containerId: Int, playerInventory: Inventory): ArgonautMenu {
            return ArgonautMenu(
                HAMenuTypes.ARGONAUT_MENU_2ROW.get(),
                containerId,
                playerInventory,
                SimpleContainer(SLOTS_PER_ROW * 2 + 1),
                2,
                SimpleContainerData(2)
            )
        }

        fun threeRows(containerId: Int, playerInventory: Inventory): ArgonautMenu {
            return ArgonautMenu(
                HAMenuTypes.ARGONAUT_MENU_3ROW.get(),
                containerId,
                playerInventory,
                SimpleContainer(SLOTS_PER_ROW * 3 + 1),
                3,
                SimpleContainerData(2)
            )
        }

        fun threeRows(containerId: Int, playerInventory: Inventory, container: Container, containerData: ContainerData): ArgonautMenu {
            return ArgonautMenu(
                HAMenuTypes.ARGONAUT_MENU_3ROW.get(),
                containerId,
                playerInventory,
                container,
                3,
                containerData
            )
        }
    }
}