package dev.hybridlabs.aquatic.data.server.seamessage

import dev.hybridlabs.aquatic.HybridAquatic
import dev.hybridlabs.aquatic.block.SeaMessage
import dev.hybridlabs.aquatic.registry.HybridAquaticRegistryKeys
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider
import net.minecraft.core.HolderLookup
import net.minecraft.resources.ResourceKey
import net.minecraft.resources.ResourceLocation
import java.util.*
import java.util.concurrent.CompletableFuture

class SeaMessageProvider(output: FabricDataOutput, registriesFuture: CompletableFuture<HolderLookup.Provider>) :
    FabricDynamicRegistryProvider(output, registriesFuture) {
    override fun configure(registries: HolderLookup.Provider, entries: Entries) {
        BUILT_IN.forEach { message ->
            val key = ResourceKey<SeaMessage>.create<SeaMessage>(
                HybridAquaticRegistryKeys.SEA_MESSAGE,
                ResourceLocation(HybridAquatic.MOD_ID, message.id)
            )
            entries.add(
                key,
                SeaMessage(
                    message.translationKey,
                    message.englishTitle != null,
                    message.infinite,
                    Optional.ofNullable(message.author)
                )
            )
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
            GeneratedSeaMessageData(
                "the_creepers_code",
                "The creepers have a code...",
                englishTitle = "The Creeper's Code"
            ),
            GeneratedSeaMessageData("poyo", "I hate litterbugs.", "Poyo"),
            GeneratedSeaMessageData(
                "rick_roll",
                "Never gonna give you up!\nNever gonna let you down!\nNever gonna run around and desert you!"
            ),
            GeneratedSeaMessageData("bold_muddy", "AW MAN I DROWNED!", "Bold Muddy"),
            GeneratedSeaMessageData(
                "catpenjoe",
                "If you wanna get a catgirl, you first have to become a catboy.",
                "Kaupenjoe"
            ),
            GeneratedSeaMessageData("willowshine", "Beware the fish girl", "Willowshine"),
            GeneratedSeaMessageData("loss", "| ||\n|| |_"),
            GeneratedSeaMessageData(
                "threats",
                "Try to counter unanticipated threats by anticipating them",
                "DaDolphin",
                englishTitle = "Seamoth"
            ),
            GeneratedSeaMessageData(
                "warranty",
                "We've been trying to reach you about your car's extended warranty.",
                englishTitle = "IMPORTANT: PLEASE READ"
            ),
            GeneratedSeaMessageData("poke", "I like cheese", "Poke", englishTitle = "Cheese"),
            GeneratedSeaMessageData("one_piece", "THE ONE PIECE IS REAL", "Poke", englishTitle = "One Piece"),
            GeneratedSeaMessageData(
                "mylo",
                "Mylo, you are the best friend I could have ever hoped to have. \nRest in peace, my sweet prince. I hope you have sweet dreams and find the peace you deserve.",
                "MysticKoko",
                englishTitle = "The Best Boy"
            ),
            GeneratedSeaMessageData("womp_womp", "womp womp", "MrPress", englishTitle = "Catchphrase"),
            GeneratedSeaMessageData(
                "crocodile",
                "It's always the crocodile you don't see you have to worry about.",
                "Jeremy Wade",
                englishTitle = "The Unseen Crocodile"
            ),
            GeneratedSeaMessageData(
                "bad_luck",
                "According to the Luck and Probability department it’s statistically bad luck to wish people good luck during a crisis.",
                "Agent Estevez",
                englishTitle = "Important Notice: FBC"
            ),
            GeneratedSeaMessageData(
                "cryptic_gun_message",
                "< You/We wield the Gun/You >",
                "The Board",
                englishTitle = "Hotline"
            ),
            GeneratedSeaMessageData("boo", "Boo"),
            GeneratedSeaMessageData(
                "yashaa",
                "Why are you crying on a nice day like today? I mean, it's even snowing...",
                "Yashaa",
                englishTitle = "Cepriestess"
            ),
            GeneratedSeaMessageData(
                "river_to_sea",
                "From the river to the sea, Palestine will be free!",
                englishTitle = "River To Sea"
            ),
            GeneratedSeaMessageData("free_palestine", "#FreePalestine", englishTitle = "Freedom"),
            GeneratedSeaMessageData("free_gaza", "#FreeGaza", englishTitle = "Freedom"),
            GeneratedSeaMessageData(
                "control_oop",
                "Objects of Power shape reality around us. Handle with care.",
                author = "FBC",
                englishTitle = "Object of Power: Sea Message"
            ),
            GeneratedSeaMessageData(
                "dylan", """
                You are a worm through time.
                The thunder song distorts you.
                Happiness comes.
                White pearls, but yellow and red in the eye.
                Through a mirror, inverted is made right.
                Leave your insides by the door.
                Push the fingers through the surface into the wet.
                You’ve always been the new you.
                You want this to be true.
                We stand around you while you dream.
                You can almost hear our words but you forget.
                This happens more and more now.
                You gave us the permission in your regulations.
                We wait in the stains.
                The word that describes this is redacted.
                Repeat the word.
                The name of the sound.
                It resonates in your house.
                After the song, time for applause.
                We build you till nothing remains.
                The egg cracks and the truth will emerge out of you.
                You are home.
                You remind us of home.
                You’ve taken your boss with your boss with you.
                All hair must be eaten.
                Under the conceptual reality behind this reality you must want these waves to drag you away.
                After the song, time for applause.
                This cliché is death out of time, breaking the first the second the third the fourth wall, the fifth wall, floor; no floor: you fall!
                How do you say “insane”?
                Hurts to be happy.
                An earworm is a tune you can’t stop humming in a dream: "Baby baby baby, yeah."
                Just plastic.
                So safe and nothing to worry about.
                Ha ha, funny.
                The last egg breaks now.
                The hole in your room is a hole in you.
                You came and we let you in through the hole in you.
                You have always been here, the only child.
                A copy of a copy of a copy.
                Orange peel.
                The picture is you holding the picture.
                When you hear this you will know you’re in new you.
                You want to listen.
                You want to dream.
                You want to smile.
                You want to hurt.
                You don’t want to be.
            """.trimIndent(), "Prime Candidate 6", infinite = true
            ),
        )
    }
}
