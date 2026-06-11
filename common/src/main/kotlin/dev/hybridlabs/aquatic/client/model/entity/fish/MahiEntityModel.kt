package dev.hybridlabs.aquatic.client.model.entity.fish

import dev.hybridlabs.aquatic.entity.fish.MahiEntity
import net.minecraft.resources.ResourceLocation
import kotlin.random.Random

class MahiEntityModel : HAFishEntityModel<MahiEntity>("mahi") {

    override fun getTextureResource(animatable: MahiEntity): ResourceLocation {
        val seed = animatable.uuid.leastSignificantBits
        val random = Random(seed)

        return when (animatable.variant) {
            MahiEntity.Companion.Type.MAHI -> mahiTextures[random.nextInt(mahiTextures.size)]
            MahiEntity.Companion.Type.POMPANO -> POMPANO_TEXTURE
        }
    }

    override fun getModelResource(animatable: MahiEntity): ResourceLocation {
        return when (animatable.variant) {
            MahiEntity.Companion.Type.MAHI -> MAHI_MODEL
            MahiEntity.Companion.Type.POMPANO -> POMPANO_MODEL
        }
    }

    companion object {
        private val POMPANO_TEXTURE =
            ResourceLocation.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/fish/mahi/mahi_pompano.png")

        private val mahiTextures = listOf(
            ResourceLocation.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/fish/mahi/mahi_mahi.png"),
            ResourceLocation.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/fish/mahi/mahi_mahi_2.png"),
            ResourceLocation.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/fish/mahi/mahi_mahi_3.png"),
        )

        private val MAHI_MODEL =
            ResourceLocation.fromNamespaceAndPath("hybrid_aquatic", "geo/fish/mahi/mahi_mahi.geo.json")
        private val POMPANO_MODEL =
            ResourceLocation.fromNamespaceAndPath("hybrid_aquatic", "geo/fish/mahi/mahi_pompano.geo.json")
    }
}
