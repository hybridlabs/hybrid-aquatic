package dev.hybridlabs.aquatic.client.model.entity.fish

import dev.hybridlabs.aquatic.entity.fish.SunfishEntity
import net.minecraft.resources.ResourceLocation

class SunfishEntityModel : HybridAquaticFishEntityModel<SunfishEntity>("sunfish") {

    private val OCEAN_TEXTURE = ResourceLocation.fromNamespaceAndPath("hybrid-aquatic", "textures/entity/fish/sunfish/sunfish_ocean.png")
    private val HOODWINKER_TEXTURE = ResourceLocation.fromNamespaceAndPath("hybrid-aquatic", "textures/entity/fish/sunfish/sunfish_hoodwinker.png")
    private val SHARPTAIL_TEXTURE = ResourceLocation.fromNamespaceAndPath("hybrid-aquatic", "textures/entity/fish/sunfish/sunfish_sharptail.png")
    private val GIANT_TEXTURE = ResourceLocation.fromNamespaceAndPath("hybrid-aquatic", "textures/entity/fish/sunfish/sunfish_giant.png")

    private val SUNFISH_MODEL = ResourceLocation.fromNamespaceAndPath("hybrid-aquatic", "geo/fish/sunfish/sunfish.geo.json")
    private val HOODWINKER_MODEL = ResourceLocation.fromNamespaceAndPath("hybrid-aquatic", "geo/fish/sunfish/sunfish_hoodwinker.geo.json")
    private val GIANT_MODEL = ResourceLocation.fromNamespaceAndPath("hybrid-aquatic", "geo/fish/sunfish/sunfish_giant.geo.json")

    private val SUNFISH_ANIMATION = ResourceLocation.fromNamespaceAndPath("hybrid-aquatic", "animations/sunfish.animation.json")
    private val HOODWINKER_ANIMATION = ResourceLocation.fromNamespaceAndPath("hybrid-aquatic", "animations/sunfish_hoodwinker.animation.json")
    private val GIANT_ANIMATION = ResourceLocation.fromNamespaceAndPath("hybrid-aquatic", "animations/sunfish_giant.animation.json")

    override fun getTextureResource(animatable: SunfishEntity): ResourceLocation {
        return when (animatable.variant) {
            SunfishEntity.Companion.Type.OCEAN -> OCEAN_TEXTURE
            SunfishEntity.Companion.Type.HOODWINKER -> HOODWINKER_TEXTURE
            SunfishEntity.Companion.Type.SHARPTAIL -> SHARPTAIL_TEXTURE
        }
    }

    override fun getModelResource(animatable: SunfishEntity): ResourceLocation {
        return when (animatable.variant) {
            SunfishEntity.Companion.Type.OCEAN -> SUNFISH_MODEL
            SunfishEntity.Companion.Type.SHARPTAIL -> SUNFISH_MODEL
            SunfishEntity.Companion.Type.HOODWINKER -> HOODWINKER_MODEL
        }
    }

    override fun getAnimationResource(animatable: SunfishEntity): ResourceLocation {
        return when (animatable.variant) {
            SunfishEntity.Companion.Type.OCEAN -> SUNFISH_ANIMATION
            SunfishEntity.Companion.Type.SHARPTAIL -> SUNFISH_ANIMATION
            SunfishEntity.Companion.Type.HOODWINKER -> HOODWINKER_ANIMATION
        }
    }
}