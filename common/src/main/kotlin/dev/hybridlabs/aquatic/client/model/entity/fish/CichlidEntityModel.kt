package dev.hybridlabs.aquatic.client.model.entity.fish

import dev.hybridlabs.hapi.client.model.entity.aquatic.BaseFishEntityModel
import dev.hybridlabs.aquatic.entity.fish.CichlidEntity
import net.minecraft.resources.ResourceLocation

class CichlidEntityModel : BaseFishEntityModel<CichlidEntity>("hybrid_aquatic", "cichlid") {

    override fun getTextureResource(animatable: CichlidEntity): ResourceLocation {
        return when (animatable.variant) {
            CichlidEntity.Companion.Type.TILAPIA -> TILAPIA_TEXTURE
            CichlidEntity.Companion.Type.OSCAR -> OSCAR_TEXTURE
        }
    }

    override fun getModelResource(animatable: CichlidEntity): ResourceLocation {
        return when (animatable.variant) {
            CichlidEntity.Companion.Type.TILAPIA -> TILAPIA_MODEL
            CichlidEntity.Companion.Type.OSCAR -> OSCAR_MODEL
        }
    }

    companion object {
        private val TILAPIA_TEXTURE =
            ResourceLocation.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/fish/cichlid/tilapia.png")
        private val OSCAR_TEXTURE =
            ResourceLocation.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/fish/cichlid/oscar.png")

        private val TILAPIA_MODEL =
            ResourceLocation.fromNamespaceAndPath("hybrid_aquatic", "geo/fish/cichlid/tilapia.geo.json")
        private val OSCAR_MODEL =
            ResourceLocation.fromNamespaceAndPath("hybrid_aquatic", "geo/fish/cichlid/oscar.geo.json")
    }
}
