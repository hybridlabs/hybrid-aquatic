package dev.hybridlabs.aquatic.client.model.entity.fish

import dev.hybridlabs.aquatic.entity.fish.CatfishEntity
import dev.hybridlabs.hapi.client.model.entity.aquatic.BaseFishEntityModel
import net.minecraft.resources.ResourceLocation

class CatfishEntityModel : BaseFishEntityModel<CatfishEntity>("hybrid_aquatic", "catfish") {

    override fun getTextureResource(animatable: CatfishEntity): ResourceLocation {
        return when (animatable.variant) {
            CatfishEntity.Companion.Type.CHANNEL_CATFISH -> CHANNEL_CATFISH_TEXTURE
        }
    }

    override fun getModelResource(animatable: CatfishEntity): ResourceLocation {
        return when (animatable.variant) {
            CatfishEntity.Companion.Type.CHANNEL_CATFISH -> CHANNEL_CATFISH_MODEL
        }
    }

    companion object {
        private val CHANNEL_CATFISH_TEXTURE =
            ResourceLocation.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/fish/catfish/channel_catfish.png")

        private val CHANNEL_CATFISH_MODEL =
            ResourceLocation.fromNamespaceAndPath("hybrid_aquatic", "geo/fish/catfish/channel_catfish.geo.json")
    }
}
