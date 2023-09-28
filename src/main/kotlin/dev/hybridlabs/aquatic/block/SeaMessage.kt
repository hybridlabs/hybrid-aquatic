package dev.hybridlabs.aquatic.block

import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder
import dev.hybridlabs.aquatic.HybridAquatic
import net.minecraft.item.ItemStack
import net.minecraft.item.Items
import net.minecraft.item.WrittenBookItem
import net.minecraft.nbt.NbtList
import net.minecraft.nbt.NbtString
import net.minecraft.text.MutableText
import net.minecraft.text.Text

/**
 * Represents a message inside a Message in a Bottle.
 */
data class SeaMessage(
    /**
     * The id of this sea message.
     */
    val id: String
) {
    /**
     * The translation key of this message.
     */
    val translationKey: String = "${HybridAquatic.MOD_ID}.sea_message.$id"

    /**
     * The text component for this message.
     */
    val text: MutableText = Text.translatable(translationKey)

    fun createBookItemStack(): ItemStack {
        val stack = ItemStack(Items.WRITTEN_BOOK)

        stack.setSubNbt(WrittenBookItem.PAGES_KEY, NbtList().apply {
            add(NbtString.of(Text.Serializer.toJson(text)))
        })

        stack.setSubNbt(WrittenBookItem.TITLE_KEY, NbtString.of("Sea Message"))
        stack.setSubNbt(WrittenBookItem.AUTHOR_KEY, NbtString.of("?????"))

        return stack
    }

    companion object {
        /**
         * The codec for this class.
         */
        val CODEC: Codec<SeaMessage> = RecordCodecBuilder.create { instance ->
            instance.group(
                Codec.STRING.fieldOf("id")
                    .forGetter(SeaMessage::id)
            ).apply(instance, ::SeaMessage)
        }

        /**
         * The built-in Hybrid Aquatic sea messages.
         */
        val BUILT_IN = listOf(
            "the_creepers_code",
            "parrot_poison",
            "lava_bathing",
            "fish_school",
            "loser",
            "fart_bottle",
            "tricked",
            "marooned",
            "pumpkin_carving",
        )
    }
}
