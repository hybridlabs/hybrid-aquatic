package dev.hybridlabs.aquatic.block

import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder
import dev.hybridlabs.aquatic.registry.HybridAquaticRegistryKeys
import net.minecraft.core.RegistryAccess
import net.minecraft.network.chat.Component
import net.minecraft.resources.ResourceLocation
import net.minecraft.server.network.Filterable
import net.minecraft.server.network.FilteredText
import net.minecraft.world.item.component.WrittenBookContent
import java.util.*
import kotlin.jvm.optionals.getOrDefault

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
    fun getId(registryManager: RegistryAccess): ResourceLocation? {
        val registry = registryManager.registryOrThrow(HybridAquaticRegistryKeys.SEA_MESSAGE)
        return registry.getKey(this)
    }

    /**
     * Turn the message content into WrittenBookContent that can be consumed by
     * BookViewScreen.BookAccess
     */
    fun getWrittenBookContent(): WrittenBookContent {
        return WrittenBookContent(
            Filterable.from(
                FilteredText.passThrough(
                    if (hasTitle)
                        Component.translatable("$translationKey.title").getString(32)
                    else ""
                )
            ),
            author.getOrDefault(""),
            0,
            listOf(Filterable.passThrough(Component.translatable(translationKey))),
            true
        )
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
