package dev.hybridlabs.aquatic.client.model.entity.fish

import dev.hybridlabs.aquatic.entity.fish.OceanSunfishEntity
import dev.hybridlabs.hapi.client.model.entity.aquatic.BaseFishEntityModel
import net.minecraft.resources.ResourceLocation

class OceanSunfishEntityModel : BaseFishEntityModel<OceanSunfishEntity>("hybrid_aquatic","ocean_sunfish") {

    override fun getTextureResource(animatable: OceanSunfishEntity): ResourceLocation {
        return when (animatable.variant) {
            OceanSunfishEntity.Companion.Type.OCEAN -> OCEAN_TEXTURE
            OceanSunfishEntity.Companion.Type.HOODWINKER -> HOODWINKER_TEXTURE
            OceanSunfishEntity.Companion.Type.SHARPTAIL -> SHARPTAIL_TEXTURE
            OceanSunfishEntity.Companion.Type.GIANT -> GIANT_TEXTURE
        }
    }

    override fun getModelResource(animatable: OceanSunfishEntity): ResourceLocation {
        return when (animatable.variant) {
            OceanSunfishEntity.Companion.Type.OCEAN -> SUNFISH_MODEL
            OceanSunfishEntity.Companion.Type.SHARPTAIL -> SUNFISH_MODEL
            OceanSunfishEntity.Companion.Type.HOODWINKER -> HOODWINKER_MODEL
            OceanSunfishEntity.Companion.Type.GIANT -> GIANT_MODEL
        }
    }

    override fun getAnimationResource(animatable: OceanSunfishEntity): ResourceLocation {
        return when (animatable.variant) {
            OceanSunfishEntity.Companion.Type.OCEAN -> SUNFISH_ANIMATION
            OceanSunfishEntity.Companion.Type.SHARPTAIL -> SUNFISH_ANIMATION
            OceanSunfishEntity.Companion.Type.HOODWINKER -> HOODWINKER_ANIMATION
            OceanSunfishEntity.Companion.Type.GIANT -> GIANT_ANIMATION
        }
    }

    companion object {
        private val OCEAN_TEXTURE =
            ResourceLocation.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/fish/ocean_sunfish/ocean_sunfish.png")
        private val HOODWINKER_TEXTURE =
            ResourceLocation.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/fish/ocean_sunfish/hoodwinker_sunfish.png")
        private val SHARPTAIL_TEXTURE =
            ResourceLocation.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/fish/ocean_sunfish/sharptail_sunfish.png")
        private val GIANT_TEXTURE =
            ResourceLocation.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/fish/ocean_sunfish/giant_sunfish.png")

        private val SUNFISH_MODEL =
            ResourceLocation.fromNamespaceAndPath("hybrid_aquatic", "geo/fish/ocean_sunfish/ocean_sunfish.geo.json")
        private val HOODWINKER_MODEL =
            ResourceLocation.fromNamespaceAndPath("hybrid_aquatic", "geo/fish/ocean_sunfish/hoodwinker_sunfish.geo.json")
        private val GIANT_MODEL =
            ResourceLocation.fromNamespaceAndPath("hybrid_aquatic", "geo/fish/ocean_sunfish/giant_sunfish.geo.json")

        private val SUNFISH_ANIMATION =
            ResourceLocation.fromNamespaceAndPath("hybrid_aquatic", "animations/entity/fish/ocean_sunfish/ocean_sunfish.animation.json")
        private val HOODWINKER_ANIMATION =
            ResourceLocation.fromNamespaceAndPath("hybrid_aquatic", "animations/entity/fish/ocean_sunfish/hoodwinker_sunfish.animation.json")
        private val GIANT_ANIMATION =
            ResourceLocation.fromNamespaceAndPath("hybrid_aquatic", "animations/entity/fish/ocean_sunfish/giant_sunfish.animation.json")
    }
}
