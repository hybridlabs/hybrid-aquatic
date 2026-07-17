package dev.hybridlabs.aquatic.painting

import dev.hybridlabs.aquatic.CommonClass
import net.minecraft.core.registries.Registries
import net.minecraft.resources.ResourceKey
import net.minecraft.world.entity.decoration.PaintingVariant

object HAPaintings {
    val MELON = key("melon")

    val CONCH_STREET = key("conch_street")
    val BOLD_AND_BRASH = key("bold_and_brash")
    val BIG_LURE = key("big_lure")
    val PRESERVER = key("preserver")

    val CREATURES_OF_THE_DEEP = key("creatures_of_the_deep")
    val BIGEYE = key("bigeye")

    val JAWS = key("jaws")
    val FAYETTE = key("fayette")

    val PRAYA_DUBIA = key("praya_dubia")
    val KING_OF_HERRING = key("king_of_herring")

    val PALESTINE_FLAG = key("palestine_flag")
    val JOLLY_ROGER = key("jolly_roger")
    val PRIDE_FLAG = key("pride_flag")
    val GAY_PRIDE_FLAG = key("gay_pride_flag")
    val LESBIAN_PRIDE_FLAG = key("lesbian_pride_flag")
    val ASEXUAL_PRIDE_FLAG = key("asexual_pride_flag")
    val BISEXUAL_PRIDE_FLAG = key("bisexual_pride_flag")
    val TRANS_PRIDE_FLAG = key("trans_pride_flag")

    fun key(id: String): ResourceKey<PaintingVariant> {
        return ResourceKey.create(Registries.PAINTING_VARIANT, CommonClass.locate(id))
    }
}