package dev.hybridlabs.aquatic.client.model.entity.miniboss

import dev.hybridlabs.aquatic.HybridAquatic
import dev.hybridlabs.aquatic.entity.miniboss.HybridAquaticMinibossEntity
import net.minecraft.resources.ResourceLocation
import software.bernie.geckolib.model.GeoModel

abstract class HybridAquaticMinibossEntityModel<T : HybridAquaticMinibossEntity>(private val id: String) :
    GeoModel<T>() {
    override fun getModelResource(animatable: T): ResourceLocation {
        return ResourceLocation(Constants.MOD_ID, "geo/miniboss/${id}/$id.geo.json")
    }

    override fun getTextureResource(animatable: T): ResourceLocation {
        return ResourceLocation(Constants.MOD_ID, "textures/entity/miniboss/${id}/$id.png")
    }

    fun getVariantTexture(variant: String): ResourceLocation {
        return ResourceLocation(Constants.MOD_ID, "textures/entity/miniboss/${id}/${id}_$variant.png")
    }

    override fun getAnimationResource(animatable: T): ResourceLocation {
        return ResourceLocation(Constants.MOD_ID, "animations/$id.animation.json")
    }
}