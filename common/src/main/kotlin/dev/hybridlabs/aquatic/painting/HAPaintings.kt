package dev.hybridlabs.aquatic.painting

import dev.hybridlabs.aquatic.CommonClass
import net.minecraft.core.registries.Registries
import net.minecraft.resources.ResourceKey
import net.minecraft.world.entity.decoration.PaintingVariant

object HAPaintings {
    val TEST_PAINTING1 = key("test_painting1")
    val TEST_PAINTING2 = key("test_painting2")
    val MELON = key("melon")
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