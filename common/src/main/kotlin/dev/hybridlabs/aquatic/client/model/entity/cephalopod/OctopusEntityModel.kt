package dev.hybridlabs.aquatic.client.model.entity.cephalopod

import dev.hybridlabs.hapi.client.model.HAPIDataTickets
import dev.hybridlabs.hapi.client.model.getVariant
import software.bernie.geckolib.renderer.base.GeoRenderState
import dev.hybridlabs.aquatic.entity.cephalopod.OctopusEntity
import dev.hybridlabs.hapi.client.model.entity.aquatic.BaseOctopusEntityModel
import net.minecraft.resources.Identifier

class OctopusEntityModel : BaseOctopusEntityModel<OctopusEntity>("hybrid_aquatic","octopus") {

    companion object {
        private val OCTOPUS_TEXTURE =
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/cephalopod/octopus/octopus.png")
        private val BLUE_RINGED_OCTOPUS_TEXTURE =
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/cephalopod/octopus/blue_ringed_octopus.png")
        private val COCONUT_OCTOPUS_TEXTURE =
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/cephalopod/octopus/coconut_octopus.png")

        private val OCTOPUS_MODEL =
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "cephalopod/octopus/octopus")
        private val BLUE_RINGED_OCTOPUS_MODEL =
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "cephalopod/octopus/blue_ringed_octopus")
        private val COCONUT_OCTOPUS_MODEL =
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "cephalopod/octopus/coconut_octopus")

        private val OCTOPUS_ANIMATION =
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "entity/cephalopod/octopus/octopus")
        private val BLUE_RINGED_OCTOPUS_ANIMATION =
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "entity/cephalopod/octopus/blue_ringed_octopus")
        private val COCONUT_OCTOPUS_ANIMATION =
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "entity/cephalopod/octopus/coconut_octopus")
    }

    override fun getTextureResource(renderState: GeoRenderState): Identifier {
        return when (renderState.getVariant(OctopusEntity.Companion.Type.entries)!!) {
            OctopusEntity.Companion.Type.OCTOPUS -> OCTOPUS_TEXTURE
            OctopusEntity.Companion.Type.BLUE_RINGED -> BLUE_RINGED_OCTOPUS_TEXTURE
            OctopusEntity.Companion.Type.COCONUT -> COCONUT_OCTOPUS_TEXTURE
        }
    }

    override fun getModelResource(renderState: GeoRenderState): Identifier {
        return when (renderState.getVariant(OctopusEntity.Companion.Type.entries)!!) {
            OctopusEntity.Companion.Type.OCTOPUS -> OCTOPUS_MODEL
            OctopusEntity.Companion.Type.BLUE_RINGED -> BLUE_RINGED_OCTOPUS_MODEL
            OctopusEntity.Companion.Type.COCONUT -> COCONUT_OCTOPUS_MODEL
        }
    }

    override fun getAnimationResource(animatable: OctopusEntity): Identifier {
        return when (animatable.variant) {
            OctopusEntity.Companion.Type.OCTOPUS -> OCTOPUS_ANIMATION
            OctopusEntity.Companion.Type.BLUE_RINGED -> BLUE_RINGED_OCTOPUS_ANIMATION
            OctopusEntity.Companion.Type.COCONUT -> COCONUT_OCTOPUS_ANIMATION
        }
    }
}
