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
        // The background is drawn as a header (fuel slot and title), one strip per cargo row, and
        // then the player inventory section, so the screen has to be exactly as tall as those add
        // up to. Getting this wrong offsets the whole window and leaves a gap at the bottom.
        imageHeight = HEADER_HEIGHT + this.argonautRows * 18 + PLAYER_INVENTORY_HEIGHT
        imageWidth = 182

        inventoryLabelY = imageHeight - 94
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
            topDrawPos, 16, 0, this.imageWidth, HEADER_HEIGHT + argonautRows * 18)
        guiGraphics.blit(ARGONAUT_BACKGROUND,
            leftDrawPos, topDrawPos + HEADER_HEIGHT + this.argonautRows * 18,
            16, 126, this.imageWidth, PLAYER_INVENTORY_HEIGHT)

        if (this.menu.isLit()) {
            val litProgress = this.menu.getLitProgress()
            guiGraphics.blit(
                ARGONAUT_BACKGROUND,
                leftDrawPos + 84,
                topDrawPos + 38 + 12 - litProgress,
                198,
                12 - litProgress,
                14,
                litProgress + 1)
        }
    }

    override fun render(guiGraphics: GuiGraphics, mouseX: Int, mouseY: Int, partialTick: Float) {
        renderBackground(guiGraphics)
        super.render(guiGraphics, mouseX, mouseY, partialTick)
        renderTooltip(guiGraphics, mouseX, mouseY)
    }

    companion object {
        /** Title bar plus the fuel slot row, above the cargo rows. */
        private const val HEADER_HEIGHT = 71

        /** Inventory label, the three inventory rows and the hotbar, below the cargo rows. */
        private const val PLAYER_INVENTORY_HEIGHT = 96

        val ARGONAUT_BACKGROUND: ResourceLocation = CommonClass.locate("textures/gui/container/argonaut_3row.png")
    }
}
