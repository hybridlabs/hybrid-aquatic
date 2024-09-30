package dev.hybridlabs.aquatic.block

import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder
import dev.hybridlabs.aquatic.registry.HybridAquaticRegistryKeys
import net.minecraft.network.RegistryByteBuf
import net.minecraft.network.codec.PacketCodec
import net.minecraft.network.codec.PacketCodecs
import net.minecraft.registry.DynamicRegistryManager
import net.minecraft.util.Identifier
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

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
    val hasTitle: Boolean = false,

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
                Codec.BOOL.fieldOf("has_title").forGetter(SeaMessage::hasTitle),
                Codec.STRING.optionalFieldOf("author").forGetter(SeaMessage::author)
            ).apply(instance, ::SeaMessage)
        }

        val PACKET_CODEC: PacketCodec<RegistryByteBuf, SeaMessage> = PacketCodec.tuple(
            PacketCodecs.STRING,
            SeaMessage::translationKey,
            PacketCodecs.BOOL,
            SeaMessage::hasTitle,
            PacketCodecs.STRING.xmap({ Optional.ofNullable(it) }, { it.getOrNull() }),
            SeaMessage::author,
            ::SeaMessage
        )
    }
}
