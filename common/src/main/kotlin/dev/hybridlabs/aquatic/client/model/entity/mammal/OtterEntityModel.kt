package dev.hybridlabs.aquatic.client.model.entity.mammal

import dev.hybridlabs.aquatic.entity.mammal.OtterEntity
import net.minecraft.resources.ResourceLocation

class OtterEntityModel : HybridAquaticMammalEntityModel<OtterEntity>("otter") {

    private val RIVER_OTTER_TEXTURE = ResourceLocation("hybrid-aquatic", "textures/entity/mammal/otter/river_otter.png")
    private val SEA_OTTER_TEXTURE = ResourceLocation("hybrid-aquatic", "textures/entity/mammal/otter/sea_otter.png")

    private val RIVER_OTTER_MODEL = ResourceLocation("hybrid-aquatic", "geo/mammal/otter/river_otter.geo.json")
    private val SEA_OTTER_MODEL = ResourceLocation("hybrid-aquatic", "geo/mammal/otter/sea_otter.geo.json")

    private val RIVER_OTTER_ANIMATION = ResourceLocation("hybrid-aquatic", "animations/river_otter.animation.json")
    private val SEA_OTTER_ANIMATION = ResourceLocation("hybrid-aquatic", "animations/sea_otter.animation.json")

    override fun getTextureResource(animatable: OtterEntity): ResourceLocation {
        return when (animatable.variant) {
            OtterEntity.Companion.Type.RIVER -> RIVER_OTTER_TEXTURE
            OtterEntity.Companion.Type.SEA -> SEA_OTTER_TEXTURE
        }
    }

    override fun getModelResource(animatable: OtterEntity): ResourceLocation {
        return when (animatable.variant) {
            OtterEntity.Companion.Type.RIVER -> RIVER_OTTER_MODEL
            OtterEntity.Companion.Type.SEA -> SEA_OTTER_MODEL
        }
    }

    override fun getAnimationResource(animatable: OtterEntity): ResourceLocation {
        return when (animatable.variant) {
            OtterEntity.Companion.Type.RIVER -> RIVER_OTTER_ANIMATION
            OtterEntity.Companion.Type.SEA -> SEA_OTTER_ANIMATION
        }
    }
}