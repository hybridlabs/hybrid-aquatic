package dev.hybridlabs.aquatic.client.model.entity.fish

import dev.hybridlabs.aquatic.entity.fish.WrasseEntity
import dev.hybridlabs.hapi.client.model.entity.BaseFishEntityModel
import net.minecraft.resources.ResourceLocation

class WrasseEntityModel : BaseFishEntityModel<WrasseEntity>("hybrid_aquatic", "wrasse") {

    companion object {
        private val CALIFORNIA_SHEEPSHEAD_TEXTURE =
            ResourceLocation.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/fish/wrasse/california_sheepshead.png")

        private val CALIFORNIA_SHEEPSHEAD_MODEL =
            ResourceLocation.fromNamespaceAndPath("hybrid_aquatic", "geo/fish/wrasse/california_sheepshead.geo.json")
    }

    override fun getTextureResource(animatable: WrasseEntity): ResourceLocation {
        return when (animatable.variant) {
            WrasseEntity.Companion.Type.CALIFORNIA_SHEEPSHEAD -> CALIFORNIA_SHEEPSHEAD_TEXTURE
        }
    }

    override fun getModelResource(animatable: WrasseEntity): ResourceLocation {
        return when (animatable.variant) {
            WrasseEntity.Companion.Type.CALIFORNIA_SHEEPSHEAD -> CALIFORNIA_SHEEPSHEAD_MODEL
        }
    }
}
