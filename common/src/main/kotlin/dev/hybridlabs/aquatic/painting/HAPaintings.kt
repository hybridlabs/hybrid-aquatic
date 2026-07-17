package dev.hybridlabs.aquatic.painting

import dev.hybridlabs.aquatic.CommonClass
import dev.hybridlabs.aquatic.Constants.MOD_ID
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.entity.decoration.PaintingVariant
import java.util.function.Supplier

object HAPaintings {
    val MELON = registerPainting("melon", 1, 1)

    val CONCH_STREET = registerPainting("conch_street", 2, 1)
    val BOLD_AND_BRASH = registerPainting("bold_and_brash", 1, 2)
    val BIG_LURE = registerPainting("big_lure", 3, 2)
    val PRESERVER = registerPainting("preserver", 1, 1)

    val CREATURES_OF_THE_DEEP = registerPainting("creatures_of_the_deep", 1, 2)
    val BIGEYE = registerPainting("bigeye", 3, 2)

    val JAWS = registerPainting("jaws", 1, 2)
    val FAYETTE = registerPainting("fayette", 2, 2)
    val SUNSET = registerPainting("sunset", 2, 1)
    val RIVERFISH = registerPainting("riverfish", 2, 3)
    val JAM = registerPainting("jam", 2, 3)
    val POULPE_COLOSSAL = registerPainting("poulpe_colossal", 2, 3)

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

    fun register(id: String, painting: Supplier<PaintingVariant>): Supplier<PaintingVariant> {
        return CommonClass.PAINTINGS.register(id, painting)
    }

    fun registerPainting(id: String, widthBlocks: Int, heightBlocks: Int): ResourceLocation {
        register(id) { PaintingVariant(widthBlocks * 16, heightBlocks * 16) }
        return ResourceLocation(MOD_ID, id)
    }
}