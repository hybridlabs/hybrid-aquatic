package dev.hybridlabs.aquatic.client.model.entity.fish

import dev.hybridlabs.aquatic.entity.fish.OceanSunfishEntity
import net.minecraft.resources.ResourceLocation

class OceanSunfishEntityModel : HybridAquaticFishEntityModel<OceanSunfishEntity>("ocean_sunfish") {

    private val OCEAN_TEXTURE = ResourceLocation("hybrid-aquatic", "textures/entity/fish/ocean_sunfish/ocean_sunfish_ocean.png")
    private val HOODWINKER_TEXTURE = ResourceLocation("hybrid-aquatic", "textures/entity/fish/ocean_sunfish/hoodwinker_ocean_sunfish.png")
    private val SHARPTAIL_TEXTURE = ResourceLocation("hybrid-aquatic", "textures/entity/fish/ocean_sunfish/sharptail_ocean_sunfish.png")
    private val GIANT_TEXTURE = ResourceLocation("hybrid-aquatic", "textures/entity/fish/ocean_sunfish/giant_ocean_sunfish.png")

    private val SUNFISH_MODEL = ResourceLocation("hybrid-aquatic", "geo/fish/ocean_sunfish/ocean_sunfish.geo.json")
    private val HOODWINKER_MODEL = ResourceLocation("hybrid-aquatic", "geo/fish/ocean_sunfish/ocean_sunfish_hoodwinker.geo.json")
    private val GIANT_MODEL = ResourceLocation("hybrid-aquatic", "geo/fish/ocean_sunfish/ocean_sunfish_giant.geo.json")

    private val SUNFISH_ANIMATION = ResourceLocation("hybrid-aquatic", "animations/ocean_sunfish.animation.json")
    private val HOODWINKER_ANIMATION = ResourceLocation("hybrid-aquatic", "animations/ocean_sunfish_hoodwinker.animation.json")
    private val GIANT_ANIMATION = ResourceLocation("hybrid-aquatic", "animations/ocean_sunfish_giant.animation.json")

    override fun getTextureResource(animatable: OceanSunfishEntity): ResourceLocation {
        return when (animatable.variant) {
            OceanSunfishEntity.Companion.Type.OCEAN -> OCEAN_TEXTURE
            OceanSunfishEntity.Companion.Type.HOODWINKER -> HOODWINKER_TEXTURE
            OceanSunfishEntity.Companion.Type.SHARPTAIL -> SHARPTAIL_TEXTURE
        }
    }

    override fun getModelResource(animatable: OceanSunfishEntity): ResourceLocation {
        return when (animatable.variant) {
            OceanSunfishEntity.Companion.Type.OCEAN -> SUNFISH_MODEL
            OceanSunfishEntity.Companion.Type.SHARPTAIL -> SUNFISH_MODEL
            OceanSunfishEntity.Companion.Type.HOODWINKER -> HOODWINKER_MODEL
        }
    }

    override fun getAnimationResource(animatable: OceanSunfishEntity): ResourceLocation {
        return when (animatable.variant) {
            OceanSunfishEntity.Companion.Type.OCEAN -> SUNFISH_ANIMATION
            OceanSunfishEntity.Companion.Type.SHARPTAIL -> SUNFISH_ANIMATION
            OceanSunfishEntity.Companion.Type.HOODWINKER -> HOODWINKER_ANIMATION
        }
    }
}