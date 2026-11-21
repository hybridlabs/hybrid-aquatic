package dev.hybridlabs.aquatic.client.model.entity.fish

import dev.hybridlabs.hapi.client.model.HAPIDataTickets
import dev.hybridlabs.hapi.client.model.getVariant
import com.geckolib.renderer.base.GeoRenderState
import dev.hybridlabs.hapi.client.model.entity.aquatic.BaseFishEntityModel
import dev.hybridlabs.aquatic.CommonClass
import dev.hybridlabs.aquatic.entity.fish.CarpEntity
import net.minecraft.resources.Identifier
import kotlin.random.Random

class CarpEntityModel : BaseFishEntityModel<CarpEntity>("hybrid_aquatic", "carp") {

    override fun getTextureResource(renderState: GeoRenderState): Identifier {
        if ((renderState.getGeckolibData(HAPIDataTickets.IS_BABY) == true)) {
            return BABY_CARP_TEXTURE
        }

        val seed = (renderState.getGeckolibData(HAPIDataTickets.ENTITY_UUID)!!).leastSignificantBits
        val random = Random(seed)

        return when (renderState.getVariant(CarpEntity.Companion.Type.entries)!!) {
            CarpEntity.Companion.Type.COMMON -> COMMON_TEXTURE
            CarpEntity.Companion.Type.PRUSSIAN -> PRUSSIAN_TEXTURE
            CarpEntity.Companion.Type.KOI -> koiTextures[random.nextInt(koiTextures.size)]
        }
    }

    override fun getModelResource(renderState: GeoRenderState): Identifier {
        if ((renderState.getGeckolibData(HAPIDataTickets.IS_BABY) == true)) {
            return BABY_CARP_MODEL
        }

        return when (renderState.getVariant(CarpEntity.Companion.Type.entries)!!) {
            CarpEntity.Companion.Type.PRUSSIAN -> PRUSSIAN_CARP_MODEL
            CarpEntity.Companion.Type.KOI -> COMMON_CARP_MODEL
            CarpEntity.Companion.Type.COMMON -> COMMON_CARP_MODEL
        }
    }

    override fun getAnimationResource(animatable: CarpEntity): Identifier {
        return when (animatable.variant) {
            CarpEntity.Companion.Type.PRUSSIAN -> CARP_ANIMATION
            CarpEntity.Companion.Type.KOI -> CARP_ANIMATION
            CarpEntity.Companion.Type.COMMON -> CARP_ANIMATION
        }
    }

    fun getPatternTextureResource(layer: String): Identifier {
        return CommonClass.locate("textures/entity/fish/carp/layer/$layer.png")
    }

    companion object {
        private val BABY_CARP_TEXTURE =
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/fish/carp/baby_carp.png")
        private val COMMON_TEXTURE =
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/fish/carp/carp.png")
        private val PRUSSIAN_TEXTURE =
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/fish/carp/prussian_carp.png")

        private val BABY_CARP_MODEL =
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "fish/carp/baby_carp")
        private val COMMON_CARP_MODEL =
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "fish/carp/carp")
        private val PRUSSIAN_CARP_MODEL =
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "fish/carp/prussian_carp")

        private val CARP_ANIMATION =
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "entity/fish/carp/carp")

        private val koiTextures = listOf(
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/fish/carp/koi_silver.png"),
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/fish/carp/koi_gold.png"),
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/fish/carp/koi_orange.png"),
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/fish/carp/koi_red.png"),
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/fish/carp/koi_yellow.png"),
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/fish/carp/koi_black.png"),
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/fish/carp/koi_white.png")
        )
    }
}