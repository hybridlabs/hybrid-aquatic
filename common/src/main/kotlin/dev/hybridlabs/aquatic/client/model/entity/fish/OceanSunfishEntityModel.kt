package dev.hybridlabs.aquatic.client.model.entity.fish

import dev.hybridlabs.hapi.client.model.HAPIDataTickets
import dev.hybridlabs.hapi.client.model.getVariant
import software.bernie.geckolib.renderer.base.GeoRenderState
import dev.hybridlabs.aquatic.entity.fish.OceanSunfishEntity
import dev.hybridlabs.hapi.client.model.entity.aquatic.BaseFishEntityModel
import net.minecraft.resources.Identifier

class OceanSunfishEntityModel : BaseFishEntityModel<OceanSunfishEntity>("hybrid_aquatic","ocean_sunfish") {

    override fun getTextureResource(renderState: GeoRenderState): Identifier {
        return when (renderState.getVariant(OceanSunfishEntity.Companion.Type.entries)!!) {
            OceanSunfishEntity.Companion.Type.OCEAN -> OCEAN_TEXTURE
            OceanSunfishEntity.Companion.Type.HOODWINKER -> HOODWINKER_TEXTURE
            OceanSunfishEntity.Companion.Type.SHARPTAIL -> SHARPTAIL_TEXTURE
            OceanSunfishEntity.Companion.Type.GIANT -> GIANT_TEXTURE
        }
    }

    override fun getModelResource(renderState: GeoRenderState): Identifier {
        return when (renderState.getVariant(OceanSunfishEntity.Companion.Type.entries)!!) {
            OceanSunfishEntity.Companion.Type.OCEAN -> SUNFISH_MODEL
            OceanSunfishEntity.Companion.Type.SHARPTAIL -> SUNFISH_MODEL
            OceanSunfishEntity.Companion.Type.HOODWINKER -> HOODWINKER_MODEL
            OceanSunfishEntity.Companion.Type.GIANT -> GIANT_MODEL
        }
    }

    override fun getAnimationResource(animatable: OceanSunfishEntity): Identifier {
        return when (animatable.variant) {
            OceanSunfishEntity.Companion.Type.OCEAN -> SUNFISH_ANIMATION
            OceanSunfishEntity.Companion.Type.SHARPTAIL -> SUNFISH_ANIMATION
            OceanSunfishEntity.Companion.Type.HOODWINKER -> HOODWINKER_ANIMATION
            OceanSunfishEntity.Companion.Type.GIANT -> GIANT_ANIMATION
        }
    }

    companion object {
        private val OCEAN_TEXTURE =
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/fish/ocean_sunfish/ocean_sunfish.png")
        private val HOODWINKER_TEXTURE =
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/fish/ocean_sunfish/hoodwinker_sunfish.png")
        private val SHARPTAIL_TEXTURE =
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/fish/ocean_sunfish/sharptail_sunfish.png")
        private val GIANT_TEXTURE =
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/fish/ocean_sunfish/giant_sunfish.png")

        private val SUNFISH_MODEL =
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "fish/ocean_sunfish/ocean_sunfish")
        private val HOODWINKER_MODEL =
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "fish/ocean_sunfish/hoodwinker_sunfish")
        private val GIANT_MODEL =
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "fish/ocean_sunfish/giant_sunfish")

        private val SUNFISH_ANIMATION =
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "entity/fish/ocean_sunfish/ocean_sunfish")
        private val HOODWINKER_ANIMATION =
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "entity/fish/ocean_sunfish/hoodwinker_sunfish")
        private val GIANT_ANIMATION =
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "entity/fish/ocean_sunfish/giant_sunfish")
    }
}
