package dev.hybridlabs.aquatic.client.gui.screen

import dev.hybridlabs.aquatic.CommonClass
import dev.hybridlabs.aquatic.world.inventory.ArgonautMenu
import net.minecraft.client.gui.GuiGraphicsExtractor
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen
import net.minecraft.client.renderer.RenderPipelines
import net.minecraft.network.chat.Component
import net.minecraft.resources.Identifier
import net.minecraft.world.entity.player.Inventory

class ArgonautScreen(menu: ArgonautMenu, playerInventory: Inventory, title: Component) :
    AbstractContainerScreen<ArgonautMenu>(
        menu,
        playerInventory,
        title,
        182,
        HEADER_HEIGHT + menu.rowCount * 18 + PLAYER_INVENTORY_HEIGHT
    ) {
    private val argonautRows: Int = menu.rowCount

    init {
        // The background is drawn as a header (fuel slot and title), one strip per cargo row, and
        // then the player inventory section, so the screen has to be exactly as tall as those add
        // up to. Getting this wrong offsets the whole window and leaves a gap at the bottom.
        inventoryLabelY = imageHeight - 94
    }

    override fun extractBackground(
        extractor: GuiGraphicsExtractor,
        mouseX: Int,
        mouseY: Int,
        partialTick: Float
    ) {
        super.extractBackground(extractor, mouseX, mouseY, partialTick)

        val leftDrawPos = (this.width - this.imageWidth) / 2
        val topDrawPos = (this.height - this.imageHeight) / 2
        extractor.blit(
            RenderPipelines.GUI_TEXTURED,
            ARGONAUT_BACKGROUND,
            leftDrawPos,
            topDrawPos,
            16.0f,
            0.0f,
            this.imageWidth,
            HEADER_HEIGHT + argonautRows * 18,
            256,
            256
        )
        extractor.blit(
            RenderPipelines.GUI_TEXTURED,
            ARGONAUT_BACKGROUND,
            leftDrawPos,
            topDrawPos + HEADER_HEIGHT + this.argonautRows * 18,
            16.0f,
            126.0f,
            this.imageWidth,
            PLAYER_INVENTORY_HEIGHT,
            256,
            256
        )

        if (this.menu.isLit()) {
            val litProgress = this.menu.getLitProgress()
            extractor.blit(
                RenderPipelines.GUI_TEXTURED,
                ARGONAUT_BACKGROUND,
                leftDrawPos + 84,
                topDrawPos + 38 + 12 - litProgress,
                198.0f,
                (12 - litProgress).toFloat(),
                14,
                litProgress + 1,
                256,
                256
            )
        }
    }

    companion object {
        /** Title bar plus the fuel slot row, above the cargo rows. */
        private const val HEADER_HEIGHT = 71

        /** Inventory label, the three inventory rows and the hotbar, below the cargo rows. */
        private const val PLAYER_INVENTORY_HEIGHT = 96

        val ARGONAUT_BACKGROUND: Identifier = CommonClass.locate("textures/gui/container/argonaut_3row.png")
    }
}
