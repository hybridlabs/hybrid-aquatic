package dev.hybridlabs.aquatic.data.server.seamessage

import dev.hybridlabs.aquatic.HybridAquatic
import dev.hybridlabs.aquatic.block.SeaMessage
import dev.hybridlabs.aquatic.registry.HybridAquaticRegistryKeys
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider
import net.minecraft.registry.RegistryKey
import net.minecraft.registry.RegistryWrapper
import net.minecraft.util.Identifier
import java.util.Optional
import java.util.concurrent.CompletableFuture

class SeaMessageProvider(output: FabricDataOutput, registriesFuture: CompletableFuture<RegistryWrapper.WrapperLookup>) : FabricDynamicRegistryProvider(output, registriesFuture) {
    override fun configure(registries: RegistryWrapper.WrapperLookup, entries: Entries) {
        BUILT_IN.forEach { message ->
            val key = RegistryKey.of(HybridAquaticRegistryKeys.SEA_MESSAGE, Identifier.of(HybridAquatic.MOD_ID, message.id))
            entries.add(key, SeaMessage(message.translationKey, message.englishTitle != null, Optional.ofNullable(message.author)))
        }
    }

    override fun getName(): String {
        return "Sea Messages"
    }

    companion object {
        /**
         * The built-in Hybrid Aquatic sea messages.
         */
        val BUILT_IN: Set<GeneratedSeaMessageData> = setOf(
            GeneratedSeaMessageData("the_creepers_code", "The creepers have a code...", englishTitle = "The Creeper's Code"),
            GeneratedSeaMessageData("poyo", "I hate litterbugs.", "Poyo"),
            GeneratedSeaMessageData("rick_roll", "Never gonna give you up!\nNever gonna let you down!\nNever gonna run around and desert you!"),
            GeneratedSeaMessageData("bold_muddy", "AW MAN I DROWNED!", "Bold Muddy"),
            GeneratedSeaMessageData("kaupenjoe", "It is better to sail the seven sea, than to get STDs.", "Kaupenjoe"),
            GeneratedSeaMessageData("catpenjoe", "If you wanna get a catgirl, you first have to become a catboy.", "Catpenjoe"),
            GeneratedSeaMessageData("fishenjoe", "Give a man a fish and you'll feed him for a day,\nkill a man and you'll have one less homeless person begging you for fish.", "Fishenjoe"),
            GeneratedSeaMessageData("willowshine", "Beware the fish girl", "Willowshine"),
            GeneratedSeaMessageData("loss", "| ||\n|| |_"),
            GeneratedSeaMessageData("duunch", "Do I know you?", "Duunc_h"),
            GeneratedSeaMessageData("warranty", "We've been trying to reach you about your car's extended warranty.", englishTitle = "IMPORTANT: PLEASE READ"),
            GeneratedSeaMessageData("poke", "I like cheese", "Poke", englishTitle = "Cheese"),
            GeneratedSeaMessageData("one_piece", "THE ONE PIECE IS REAL", "Poke", englishTitle = "One Piece"),
            GeneratedSeaMessageData("mylo", "Mylo, you are the best friend I could have ever hoped to have. \nRest in peace, my sweet prince. I hope you have sweet dreams and find the peace you deserve.", "MysticKoko", englishTitle = "The Best Boy"),
            GeneratedSeaMessageData("womp_womp", "womp womp", "MrPress", englishTitle = "Catchphrase"),
        )
    }
}
