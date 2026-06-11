package dev.hybridlabs.aquatic.client.model.entity.fish

import dev.hybridlabs.aquatic.entity.fish.GoldfishEntity
import net.minecraft.resources.ResourceLocation

class GoldfishEntityModel : HAFishEntityModel<GoldfishEntity>("goldfish") {

    override fun getTextureResource(animatable: GoldfishEntity): ResourceLocation {
        if (animatable.isBaby) {
            return BABY_GOLDFISH_TEXTURE
        }

        return when (animatable.variant) {
            GoldfishEntity.Companion.Type.COMMON_GOLDFISH -> COMMON_GOLDFISH_TEXTURE
            GoldfishEntity.Companion.Type.TELESCOPE -> TELESCOPE_GOLDFISH_TEXTURE
            GoldfishEntity.Companion.Type.BUBBLE_EYE -> BUBBLE_EYE_GOLDFISH_TEXTURE
            GoldfishEntity.Companion.Type.RYUKIN -> RYUKIN_GOLDFISH_TEXTURE
            GoldfishEntity.Companion.Type.FANTAIL -> FANTAIL_GOLDFISH_TEXTURE
            GoldfishEntity.Companion.Type.SHUBUNKIN -> SHUBUNKIN_GOLDFISH_TEXTURE
        }
    }

    override fun getModelResource(animatable: GoldfishEntity): ResourceLocation {
        if (animatable.isBaby) {
            return BABY_CARP_MODEL
        }

        return when (animatable.variant) {
            GoldfishEntity.Companion.Type.COMMON_GOLDFISH -> COMMON_GOLDFISH_MODEL
            GoldfishEntity.Companion.Type.TELESCOPE -> TELESCOPE_GOLDFISH_MODEL
            GoldfishEntity.Companion.Type.BUBBLE_EYE -> BUBBLE_EYE_GOLDFISH_MODEL
            GoldfishEntity.Companion.Type.RYUKIN -> RYUKIN_GOLDFISH_MODEL
            GoldfishEntity.Companion.Type.FANTAIL -> FANTAIL_GOLDFISH_MODEL
            GoldfishEntity.Companion.Type.SHUBUNKIN -> SHUBUNKIN_GOLDFISH_MODEL
        }
    }

    override fun getAnimationResource(animatable: GoldfishEntity): ResourceLocation {
        return when (animatable.variant) {
            GoldfishEntity.Companion.Type.COMMON_GOLDFISH -> COMMON_GOLDFISH_ANIMATION
            else -> GOLDFISH_ANIMATION
        }
    }

    companion object {
        private val BABY_GOLDFISH_TEXTURE =
            ResourceLocation.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/fish/goldfish/baby_goldfish.png")
        private val COMMON_GOLDFISH_TEXTURE =
            ResourceLocation.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/fish/goldfish/common_goldfish.png")
        private val TELESCOPE_GOLDFISH_TEXTURE =
            ResourceLocation.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/fish/goldfish/telescope_goldfish.png")
        private val BUBBLE_EYE_GOLDFISH_TEXTURE =
            ResourceLocation.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/fish/goldfish/bubble_eye_goldfish.png")
        private val RYUKIN_GOLDFISH_TEXTURE =
            ResourceLocation.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/fish/goldfish/ryukin_goldfish.png")
        private val FANTAIL_GOLDFISH_TEXTURE =
            ResourceLocation.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/fish/goldfish/fantail_goldfish.png")
        private val SHUBUNKIN_GOLDFISH_TEXTURE =
            ResourceLocation.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/fish/goldfish/shubunkin_goldfish.png")

        private val BABY_CARP_MODEL =
            ResourceLocation.fromNamespaceAndPath("hybrid_aquatic", "geo/fish/goldfish/baby_goldfish.geo.json")
        private val COMMON_GOLDFISH_MODEL =
            ResourceLocation.fromNamespaceAndPath("hybrid_aquatic", "geo/fish/goldfish/common_goldfish.geo.json")
        private val TELESCOPE_GOLDFISH_MODEL =
            ResourceLocation.fromNamespaceAndPath("hybrid_aquatic", "geo/fish/goldfish/telescope_goldfish.geo.json")
        private val BUBBLE_EYE_GOLDFISH_MODEL =
            ResourceLocation.fromNamespaceAndPath("hybrid_aquatic", "geo/fish/goldfish/bubble_eye_goldfish.geo.json")
        private val RYUKIN_GOLDFISH_MODEL =
            ResourceLocation.fromNamespaceAndPath("hybrid_aquatic", "geo/fish/goldfish/ryukin_goldfish.geo.json")
        private val FANTAIL_GOLDFISH_MODEL =
            ResourceLocation.fromNamespaceAndPath("hybrid_aquatic", "geo/fish/goldfish/fantail_goldfish.geo.json")
        private val SHUBUNKIN_GOLDFISH_MODEL =
            ResourceLocation.fromNamespaceAndPath("hybrid_aquatic", "geo/fish/goldfish/shubunkin_goldfish.geo.json")

        private val COMMON_GOLDFISH_ANIMATION =
            ResourceLocation.fromNamespaceAndPath("hybrid_aquatic", "animations/entity/fish/goldfish/common_goldfish.animation.json")
        private val GOLDFISH_ANIMATION =
            ResourceLocation.fromNamespaceAndPath("hybrid_aquatic", "animations/entity/fish/goldfish/goldfish.animation.json")
    }
}
