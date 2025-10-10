package dev.hybridlabs.aquatic.client.model.entity.cephalopod

import dev.hybridlabs.aquatic.entity.cephalopod.OctopusEntity
import net.minecraft.resources.ResourceLocation

class OctopusEntityModel : HybridAquaticOctopusEntityModel<OctopusEntity>("octopus") {

    private val OCTOPUS_TEXTURE = ResourceLocation.fromNamespaceAndPath("hybrid-aquatic", "textures/entity/cephalopod/octopus/octopus.png")
    private val BLUE_RINGED_OCTOPUS_TEXTURE = ResourceLocation.fromNamespaceAndPath("hybrid-aquatic", "textures/entity/cephalopod/octopus/blue_ringed_octopus.png")
    private val COCONUT_OCTOPUS_TEXTURE = ResourceLocation.fromNamespaceAndPath("hybrid-aquatic", "textures/entity/cephalopod/octopus/coconut_octopus.png")

    private val OCTOPUS_MODEL = ResourceLocation.fromNamespaceAndPath("hybrid-aquatic", "geo/cephalopod/octopus/octopus.geo.json")
    private val BLUE_RINGED_OCTOPUS_MODEL = ResourceLocation.fromNamespaceAndPath("hybrid-aquatic", "geo/cephalopod/octopus/blue_ringed_octopus.geo.json")
    private val COCONUT_OCTOPUS_MODEL = ResourceLocation.fromNamespaceAndPath("hybrid-aquatic", "geo/cephalopod/octopus/coconut_octopus.geo.json")

    private val OCTOPUS_ANIMATION = ResourceLocation.fromNamespaceAndPath("hybrid-aquatic", "animations/octopus.animation.json")
    private val BLUE_RINGED_OCTOPUS_ANIMATION = ResourceLocation.fromNamespaceAndPath("hybrid-aquatic", "animations/blue_ringed_octopus.animation.json")
    private val COCONUT_OCTOPUS_ANIMATION = ResourceLocation.fromNamespaceAndPath("hybrid-aquatic", "animations/coconut_octopus.animation.json")

    override fun getTextureResource(animatable: OctopusEntity): ResourceLocation {
        return when (animatable.variant) {
            OctopusEntity.Companion.Type.OCTOPUS -> OCTOPUS_TEXTURE
            OctopusEntity.Companion.Type.BLUE_RINGED -> BLUE_RINGED_OCTOPUS_TEXTURE
            OctopusEntity.Companion.Type.COCONUT -> COCONUT_OCTOPUS_TEXTURE
        }
    }

    override fun getModelResource(animatable: OctopusEntity): ResourceLocation {
        return when (animatable.variant) {
            OctopusEntity.Companion.Type.OCTOPUS -> OCTOPUS_MODEL
            OctopusEntity.Companion.Type.BLUE_RINGED -> BLUE_RINGED_OCTOPUS_MODEL
            OctopusEntity.Companion.Type.COCONUT -> COCONUT_OCTOPUS_MODEL
        }
    }

    override fun getAnimationResource(animatable: OctopusEntity): ResourceLocation {
        return when (animatable.variant) {
            OctopusEntity.Companion.Type.OCTOPUS -> OCTOPUS_ANIMATION
            OctopusEntity.Companion.Type.BLUE_RINGED -> BLUE_RINGED_OCTOPUS_ANIMATION
            OctopusEntity.Companion.Type.COCONUT -> COCONUT_OCTOPUS_ANIMATION
        }
    }
}