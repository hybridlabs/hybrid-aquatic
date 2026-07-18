package dev.hybridlabs.aquatic.painting

import dev.hybridlabs.aquatic.CommonClass
import net.minecraft.core.registries.Registries
import net.minecraft.resources.ResourceKey
import net.minecraft.world.entity.decoration.PaintingVariant

object HAPaintings {
    val MELON = registerPainting("melon", 1, 1)

    val CONCH_STREET = registerPainting("conch_street", 2, 1)
    val BOLD_AND_BRASH = registerPainting("bold_and_brash", 1, 2)
    val BIG_LURE = registerPainting("big_lure", 3, 2)
    val PRESERVER = registerPainting("preserver", 1, 1)

    val CREATURES_OF_THE_DEEP = registerPainting("creatures_of_the_deep", 1, 2)
    val PRESSED_KELP = registerPainting("pressed_kelp", 2, 1)
    val PRESSED_BULL_KELP = registerPainting("pressed_bull_kelp", 1, 3)
    val BIGEYE = registerPainting("bigeye", 3, 2)
    val THE_WATCHFUL = registerPainting("the_watchful", 3, 3)
    val LIGHTHOUSE = registerPainting("lighthouse", 1, 2)

    val JAWS = registerPainting("jaws", 1, 2)
    val GREAT_LAKE = registerPainting("great_lake", 2, 2)
    val SUNSET = registerPainting("sunset", 2, 1)
    val RIVERFISH = registerPainting("riverfish", 2, 2)
    val JAM = registerPainting("jam", 2, 3)
    val SWIRLING = registerPainting("swirling", 3, 3)
    val POULPE_COLOSSAL = registerPainting("poulpe_colossal", 2, 3)
    val BELOW = registerPainting("below", 2, 3)
    val SUNTIDE = registerPainting("suntide", 4, 2)
    val MOONCATCHER = registerPainting("mooncatcher", 4, 4)
    val NEVEN_NAIVEN = registerPainting("neven_naiven", 3, 3)
    val ORGANON = registerPainting("organon", 3, 3)

    val PRAYA_DUBIA = registerPainting("praya_dubia", 2, 3)
    val KING_OF_HERRING = registerPainting("king_of_herring", 2, 2)

    val PALESTINE_FLAG = registerPainting("palestine_flag", 2, 1)
    val JOLLY_ROGER = registerPainting("jolly_roger", 2, 1)
    val PRIDE_FLAG = registerPainting("pride_flag", 2, 1)
    val GAY_PRIDE_FLAG = registerPainting("gay_pride_flag", 2, 1)
    val LESBIAN_PRIDE_FLAG = registerPainting("lesbian_pride_flag", 2, 1)
    val ASEXUAL_PRIDE_FLAG = registerPainting("asexual_pride_flag", 2, 1)
    val BISEXUAL_PRIDE_FLAG = registerPainting("bisexual_pride_flag", 2, 1)
    val TRANS_PRIDE_FLAG = registerPainting("trans_pride_flag", 2, 1)
    val NONBINARY_PRIDE_FLAG = registerPainting("nonbinary_pride_flag", 2, 1)

    fun registerPainting(id: String, widthBlocks: Int, heightBlocks: Int): ResourceKey<PaintingVariant> {
        return ResourceKey.create(Registries.PAINTING_VARIANT, CommonClass.locate(id))
    }
}