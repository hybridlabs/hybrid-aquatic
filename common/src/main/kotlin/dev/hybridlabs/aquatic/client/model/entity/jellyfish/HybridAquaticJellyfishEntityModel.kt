package dev.hybridlabs.aquatic.client.model.entity.jellyfish

import dev.hybridlabs.aquatic.Constants
import dev.hybridlabs.aquatic.entity.jellyfish.HybridAquaticJellyfishEntity
import net.minecraft.resources.ResourceLocation
import software.bernie.geckolib.model.GeoModel

abstract class HybridAquaticJellyfishEntityModel<T : HybridAquaticJellyfishEntity>(private val id: String) :
    GeoModel<T>() {
    override fun getModelResource(animatable: T): ResourceLocation {
        return ResourceLocation(Constants.MOD_ID, "geo/jellyfish/$id/$id.geo.json")
    }

    override fun getTextureResource(animatable: T): ResourceLocation {
        return ResourceLocation(Constants.MOD_ID, "textures/entity/jellyfish/$id/$id.png")
    }

    fun getVariantTexture(variant: String): ResourceLocation {
        return ResourceLocation(Constants.MOD_ID, "textures/entity/jellyfish/${id}_$variant.png")
    }

    override fun getAnimationResource(animatable: T): ResourceLocation {
        return ResourceLocation(Constants.MOD_ID, "animations/$id.animation.json")
    }
}