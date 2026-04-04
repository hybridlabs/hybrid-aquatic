package dev.hybridlabs.aquatic.client.gui.screen

import dev.hybridlabs.aquatic.CommonClass
import dev.hybridlabs.aquatic.world.inventory.ArgonautMenu
import net.minecraft.client.gui.GuiGraphics
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen
import net.minecraft.network.chat.Component
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.entity.player.Inventory

class ArgonautScreen(menu: ArgonautMenu, playerInventory: Inventory, title: Component) :
    AbstractContainerScreen<ArgonautMenu>(menu, playerInventory, title) {
    private val argonautRows: Int = menu.rowCount

    init {
        val totalGUIHeight = 222
        val firstSlotFromBottomHeight = 115

        // size of screen in pixels
        imageHeight = firstSlotFromBottomHeight + this.argonautRows * 18 + 71
        imageWidth = 182

        inventoryLabelY = imageHeight - 112
    }


    override fun init() {
        super.init()
    }

    override fun renderBg(
        guiGraphics: GuiGraphics,
        partialTick: Float,
        mouseX: Int,
        mouseY: Int
    ) {
        val leftDrawPos = (this.width - this.imageWidth) / 2
        val topDrawPos = (this.height - this.imageHeight) / 2
        guiGraphics.blit(ARGONAUT_BACKGROUND, leftDrawPos,
            topDrawPos, 16, 0, this.imageWidth,  argonautRows * 18 + 71)
        guiGraphics.blit(ARGONAUT_BACKGROUND,
            leftDrawPos, topDrawPos + this.argonautRows * 18 + 71, 16, 126, this.imageWidth, 96)
    }

    override fun render(guiGraphics: GuiGraphics, mouseX: Int, mouseY: Int, partialTick: Float) {
        renderBackground(guiGraphics)
        super.render(guiGraphics, mouseX, mouseY, partialTick)
        renderTooltip(guiGraphics, mouseX, mouseY)
    }

    companion object {
        val ARGONAUT_BACKGROUND: ResourceLocation = CommonClass.locate("textures/gui/container/argonaut_3row.png")
    }
}