package dev.hybridlabs.aquatic.client.gui

/**
 * Custom book contents for Sea Message books.
 */
/*class SeaMessageBookContents(val message: RegistryEntry<SeaMessage>) : Contents {
    private val text: String = I18n.translate(message.translationKey)

    private val wrapped: List<StringVisitable> = getBookTextWrapped(text)

    override fun getPageCount(): Int {
        return if (message.value().infinite) Integer.MAX_VALUE else wrapped.size
    }

    override fun getPageUnchecked(index: Int): StringVisitable {
        return wrapped.getOrNull(index % wrapped.size) ?: StringVisitable.EMPTY
    }

    companion object {
        fun getBookTextWrapped(text: String): List<StringVisitable> {
            val client = MinecraftClient.getInstance()
            return client.textRenderer.textHandler.wrapLines(text, 114 * 12, Style.EMPTY)
        }
    }
} TODO*/
