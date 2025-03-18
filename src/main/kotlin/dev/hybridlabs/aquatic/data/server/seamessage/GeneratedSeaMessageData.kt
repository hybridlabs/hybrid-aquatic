package dev.hybridlabs.aquatic.data.server.seamessage

import dev.hybridlabs.aquatic.HybridAquatic

data class GeneratedSeaMessageData(
    /**
     * The registry id.
     */
    val id: String,

    /**
     * The english translation of the message text content.
     */
    val englishText: String,

    /**
     * The author's name.
     */
    val author: String? = null,

    /**
     * The english translation of the message title.
     */
    val englishTitle: String? = null,

    /**
     * Whether this sea message is infinite.
     */
    val infinite: Boolean = false,
) {
    /**
     * The automated translation key for this message.
     */
    val translationKey: String = "${HybridAquatic.MOD_ID}.sea_message.$id"

    /**
     * The automated title translation key for this message.
     */
    val titleTranslationKey: String = "$translationKey.title"
}
