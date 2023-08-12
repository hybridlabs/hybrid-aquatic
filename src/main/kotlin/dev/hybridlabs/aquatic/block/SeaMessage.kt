package dev.hybridlabs.aquatic.block

import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder
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
     * The translation key for this sea message.
     */
    val translationKey: String
) {
    /**
     * The text component for this message.
     */
    private val text: MutableText get() = Text.translatable(translationKey)

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
                Codec.STRING.fieldOf("translation_key")
                    .forGetter(SeaMessage::translationKey)
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
