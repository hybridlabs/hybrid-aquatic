package dev.hybridlabs.aquatic.block

import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder
import dev.hybridlabs.aquatic.registry.HybridAquaticRegistryKeys
import net.minecraft.registry.DynamicRegistryManager
import net.minecraft.util.Identifier
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
     * The author of this message.
     */
    val author: Optional<String>
) {
    /**
     * Retrieves the id of this sea message.
     */
    fun getId(registryManager: DynamicRegistryManager): Identifier? {
        val registry = registryManager.get(HybridAquaticRegistryKeys.SEA_MESSAGE)
        return registry.getId(this)
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
                Codec.STRING.optionalFieldOf("author").orElse(Optional.empty()).forGetter(SeaMessage::author)
            ).apply(instance, ::SeaMessage)
        }
    }
}
