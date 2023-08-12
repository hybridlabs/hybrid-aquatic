package dev.hybridlabs.aquatic.data

import dev.hybridlabs.aquatic.HybridAquatic
import net.minecraft.text.MutableText
import net.minecraft.text.Text

/**
 * Represents a message inside a Message in a Bottle.
 */
enum class SeaMessage(
    /**
     * The id of this message.
     */
    val id: String,

    /**
     * The default English translation of this message.
     */
    val englishTranslation: String
) {
    THE_CREEPERS_CODE("the_creepers_code", "\"The creepers have a code...\""),
    PARROT_POISON("parrot_poison", "\"Flint tried feeding parrots cookies earlier! They did not take. He coulda sworn they used to bite!\"\n*Steele"),
    LAVA_BATHING("lava_bathing", "\"Steele went off and bathed in lava with my beautiful diamonds! That fiend! Least I've the time to myself to WRITE THIS COMPLAINT. STEELE.... STEALER! THIEF!\"\n@Flint"),
    FISH_SCHOOL("fish_school", "\"My underwater house flooded! Now it's just a school for fishies!\"\n~Oak"),
    LOSER("loser", "\"You opened the bottle!\nYou lose!\nVictim list - You!\"\n~Oak"),
    FART_BOTTLE("fart_bottle", "\"Fart in a bottle! Gotcha!\nYou lose!\"\n@Flint"),
    TRICKED("tricked", "\"I win! you fell for the classic ol' Message in a Bottle trick! I 'steele' the throne!\"\n*Steele"),
    MAROONED("marooned", "\"marooned! beneath the night of a full moon (too)!\n(through the waves) which sound suggestions of lulling lays a gurgling\nit beckons me to take the plunge and swim (with him)\n(I) lay back down. tomorrow always comes.\n#Fischer #GlubGlub\""),
    PUMPKIN_CARVING("pumpkin_carving", "\"Carve out a pumpkin and rely on your destiny!\"\n~Dean \"Ween\"");

    /**
     * The translation key of this message.
     */
    val translationKey: String = "${HybridAquatic.MOD_ID}.sea_message.$id"

    /**
     * The text component for this message.
     */
    val text: MutableText get() = Text.translatable(translationKey)
}
