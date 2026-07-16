package dev.hybridlabs.aquatic.block

import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder
import dev.hybridlabs.aquatic.registry.HARegistryKeys
import net.minecraft.core.RegistryAccess
import net.minecraft.resources.ResourceLocation
import java.util.Optional

/**
 * Represents a message inside a Message in a Bottle.
 */
data class SeaMessage(
    /**
     * The translation key for the text content of this message.
     */
    val translationKey: String,

    /**
     * Whether this message has a title.
     */
    val hasTitle: Boolean,

    /**
     * Whether this message is infinite.
     */
    val infinite: Boolean,

    /**
     * Melon.
     */
    val melon: Boolean,

    /**
     * The author of this message.
     */
    val author: Optional<String>
) {
    /**
     * Retrieves the id of this sea message.
     */
    fun getId(registryManager: RegistryAccess): ResourceLocation? {
        val registry = registryManager.registryOrThrow(HARegistryKeys.SEA_MESSAGE)
        return registry.getKey(this)
    }

    companion object {
        /**
         * The codec for this class.
         */
        val CODEC: Codec<SeaMessage> = RecordCodecBuilder.create { instance ->
            instance.group(
                Codec.STRING.fieldOf("translation_key").forGetter(SeaMessage::translationKey),
                Codec.BOOL.fieldOf("has_title").orElse(false).forGetter(SeaMessage::hasTitle),
                Codec.BOOL.fieldOf("infinite").orElse(false).forGetter(SeaMessage::infinite),
                Codec.BOOL.fieldOf("melon").orElse(false).forGetter(SeaMessage::melon),
                Codec.STRING.optionalFieldOf("author").orElse(Optional.empty()).forGetter(SeaMessage::author)
            ).apply(instance, ::SeaMessage)
        }
    }
}
