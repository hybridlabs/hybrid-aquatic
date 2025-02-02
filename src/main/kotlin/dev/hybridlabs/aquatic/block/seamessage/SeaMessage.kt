package dev.hybridlabs.aquatic.block.seamessage

import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder
import java.util.Optional

/**
 * Represents a message inside a Message in a Bottle.
 */
data class SeaMessage(
    /**
     * Whether this message is infinite.
     */
    val infinite: Boolean,

    /**
     * The author of this message.
     */
    val author: Optional<String>
) {
    companion object {
        /**
         * The codec for this class.
         */
        val CODEC: Codec<SeaMessage> = RecordCodecBuilder.create { instance ->
            instance.group(
                Codec.BOOL.fieldOf("infinite").orElse(false).forGetter(SeaMessage::infinite),
                Codec.STRING.optionalFieldOf("author").orElse(Optional.empty()).forGetter(SeaMessage::author),
            ).apply(instance, ::SeaMessage)
        }
    }
}
