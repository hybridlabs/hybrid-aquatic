package dev.hybridlabs.aquatic.client.model.entity.jellyfish

import dev.hybridlabs.aquatic.HybridAquatic
import dev.hybridlabs.aquatic.entity.jellyfish.HybridAquaticJellyfishEntity
import net.minecraft.resources.ResourceLocation
import software.bernie.geckolib.model.GeoModel

abstract class HybridAquaticJellyfishEntityModel<T : HybridAquaticJellyfishEntity>(private val id: String) :
    GeoModel<T>() {
    override fun getModelResource(animatable: T): ResourceLocation {
        return ResourceLocation(HybridAquatic.MOD_ID, "geo/jellyfish/$id.geo.json")
    }

    override fun getTextureResource(animatable: T): ResourceLocation {
        return ResourceLocation(HybridAquatic.MOD_ID, "textures/entity/jellyfish/$id.png")
    }

    fun getVariantTexture(variant: String): ResourceLocation {
        return ResourceLocation(HybridAquatic.MOD_ID, "textures/entity/jellyfish/${id}_$variant.png")
    }

    override fun getAnimationResource(animatable: T): ResourceLocation {
        return ResourceLocation(HybridAquatic.MOD_ID, "animations/$id.animation.json")
    }
}