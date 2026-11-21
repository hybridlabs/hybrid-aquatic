package dev.hybridlabs.aquatic.client.model.entity.fish

import dev.hybridlabs.hapi.client.model.HAPIDataTickets
import dev.hybridlabs.hapi.client.model.getVariant
import com.geckolib.renderer.base.GeoRenderState
import dev.hybridlabs.hapi.client.model.entity.aquatic.BaseFishEntityModel
import dev.hybridlabs.aquatic.entity.fish.GoldfishEntity
import net.minecraft.resources.Identifier

class GoldfishEntityModel : BaseFishEntityModel<GoldfishEntity>("hybrid_aquatic", "goldfish") {

    override fun getTextureResource(renderState: GeoRenderState): Identifier {
        if ((renderState.getGeckolibData(HAPIDataTickets.IS_BABY) == true)) {
            return BABY_GOLDFISH_TEXTURE
        }

        return when (renderState.getVariant(GoldfishEntity.Companion.Type.entries)!!) {
            GoldfishEntity.Companion.Type.COMMON_GOLDFISH -> COMMON_GOLDFISH_TEXTURE
            GoldfishEntity.Companion.Type.TELESCOPE -> TELESCOPE_GOLDFISH_TEXTURE
            GoldfishEntity.Companion.Type.BUBBLE_EYE -> BUBBLE_EYE_GOLDFISH_TEXTURE
            GoldfishEntity.Companion.Type.RYUKIN -> RYUKIN_GOLDFISH_TEXTURE
            GoldfishEntity.Companion.Type.FANTAIL -> FANTAIL_GOLDFISH_TEXTURE
            GoldfishEntity.Companion.Type.SHUBUNKIN -> SHUBUNKIN_GOLDFISH_TEXTURE
        }
    }

    override fun getModelResource(renderState: GeoRenderState): Identifier {
        if ((renderState.getGeckolibData(HAPIDataTickets.IS_BABY) == true)) {
            return BABY_CARP_MODEL
        }

        return when (renderState.getVariant(GoldfishEntity.Companion.Type.entries)!!) {
            GoldfishEntity.Companion.Type.COMMON_GOLDFISH -> COMMON_GOLDFISH_MODEL
            GoldfishEntity.Companion.Type.TELESCOPE -> TELESCOPE_GOLDFISH_MODEL
            GoldfishEntity.Companion.Type.BUBBLE_EYE -> BUBBLE_EYE_GOLDFISH_MODEL
            GoldfishEntity.Companion.Type.RYUKIN -> RYUKIN_GOLDFISH_MODEL
            GoldfishEntity.Companion.Type.FANTAIL -> FANTAIL_GOLDFISH_MODEL
            GoldfishEntity.Companion.Type.SHUBUNKIN -> SHUBUNKIN_GOLDFISH_MODEL
        }
    }

    override fun getAnimationResource(animatable: GoldfishEntity): Identifier {
        return when (animatable.variant) {
            GoldfishEntity.Companion.Type.COMMON_GOLDFISH -> COMMON_GOLDFISH_ANIMATION
            else -> GOLDFISH_ANIMATION
        }
    }

    companion object {
        private val BABY_GOLDFISH_TEXTURE =
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/fish/goldfish/baby_goldfish.png")
        private val COMMON_GOLDFISH_TEXTURE =
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/fish/goldfish/common_goldfish.png")
        private val TELESCOPE_GOLDFISH_TEXTURE =
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/fish/goldfish/telescope_goldfish.png")
        private val BUBBLE_EYE_GOLDFISH_TEXTURE =
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/fish/goldfish/bubble_eye_goldfish.png")
        private val RYUKIN_GOLDFISH_TEXTURE =
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/fish/goldfish/ryukin_goldfish.png")
        private val FANTAIL_GOLDFISH_TEXTURE =
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/fish/goldfish/fantail_goldfish.png")
        private val SHUBUNKIN_GOLDFISH_TEXTURE =
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/fish/goldfish/shubunkin_goldfish.png")

        private val BABY_CARP_MODEL =
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "fish/goldfish/baby_goldfish")
        private val COMMON_GOLDFISH_MODEL =
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "fish/goldfish/common_goldfish")
        private val TELESCOPE_GOLDFISH_MODEL =
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "fish/goldfish/telescope_goldfish")
        private val BUBBLE_EYE_GOLDFISH_MODEL =
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "fish/goldfish/bubble_eye_goldfish")
        private val RYUKIN_GOLDFISH_MODEL =
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "fish/goldfish/ryukin_goldfish")
        private val FANTAIL_GOLDFISH_MODEL =
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "fish/goldfish/fantail_goldfish")
        private val SHUBUNKIN_GOLDFISH_MODEL =
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "fish/goldfish/shubunkin_goldfish")

        private val COMMON_GOLDFISH_ANIMATION =
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "entity/fish/goldfish/common_goldfish")
        private val GOLDFISH_ANIMATION =
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "entity/fish/goldfish/goldfish")
    }
}
