package dev.hybridlabs.aquatic.client.gui.screen

import dev.hybridlabs.aquatic.world.inventory.ArgonautMenu
import net.minecraft.client.gui.GuiGraphics
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen
import net.minecraft.network.chat.Component
import net.minecraft.world.entity.player.Inventory

class ArgonautScreen(menu: ArgonautMenu, playerInventory: Inventory, title: Component) :
    AbstractContainerScreen<ArgonautMenu>(menu, playerInventory, title) {

    override fun init() {
        super.init()
    }

    override fun renderBg(
        guiGraphics: GuiGraphics,
        partialTick: Float,
        mouseX: Int,
        mouseY: Int
    ) {
        TODO("Not yet implemented")
    }
}