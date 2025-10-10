package dev.hybridlabs.aquatic.client.gui

import dev.hybridlabs.aquatic.block.SeaMessage
import net.minecraft.client.Minecraft
import net.minecraft.client.gui.screens.inventory.BookViewScreen
import net.minecraft.client.gui.screens.inventory.BookViewScreen.*
import net.minecraft.client.resources.language.I18n
import net.minecraft.network.chat.FormattedText
import net.minecraft.network.chat.Style

/**
 * Custom book contents for Sea Message books.
 */
class SeaMessageBookContents(val message: SeaMessage) {
    private val text: String = I18n.get(message.translationKey)

    private val wrapped: List<FormattedText> = getBookTextWrapped(text)

    /*
    override fun getPageCount(): Int {
        return if (message.infinite) Integer.MAX_VALUE else wrapped.size
    }

    override fun getPageRaw(index: Int): FormattedText {
        return wrapped.getOrNull(index % wrapped.size) ?: FormattedText.EMPTY
    }
     */

    override fun equals(obj: Any?): Boolean {
        TODO("Not yet implemented")
    }

    override fun hashCode(): Int {
        TODO("Not yet implemented")
    }

    override fun toString(): String {
        TODO("Not yet implemented")
    }

    companion object {
        fun getBookTextWrapped(text: String): List<FormattedText> {
            val client = Minecraft.getInstance()
            return client.font.splitter.splitLines(text, 114 * 12, Style.EMPTY)
        }
    }
}
