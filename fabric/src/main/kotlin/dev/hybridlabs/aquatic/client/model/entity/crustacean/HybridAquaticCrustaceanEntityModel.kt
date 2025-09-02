package dev.hybridlabs.aquatic.client.model.entity.crustacean

import dev.hybridlabs.aquatic.HybridAquatic
import dev.hybridlabs.aquatic.entity.crustacean.HybridAquaticCrustaceanEntity
import net.minecraft.resources.ResourceLocation
import software.bernie.geckolib.model.GeoModel

abstract class HybridAquaticCrustaceanEntityModel<T : HybridAquaticCrustaceanEntity>(private val id: String) :
    GeoModel<T>() {
    override fun getModelResource(animatable: T): ResourceLocation {
        val variant = animatable.variant
        if (variant != null && !variant.ignore.contains(HybridAquaticCrustaceanEntity.CrustaceanVariant.Ignore.MODEL))
            return ResourceLocation(
                HybridAquatic.MOD_ID,
                "geo/crustacean/${id}/${id}_${variant.getProvidedVariant(animatable)}.geo.json"
            )
        return ResourceLocation(HybridAquatic.MOD_ID, "geo/crustacean/${id}/$id.geo.json")
    }

    override fun getTextureResource(animatable: T): ResourceLocation {
        val variant = animatable.variant
        if (variant != null && !variant.ignore.contains(HybridAquaticCrustaceanEntity.CrustaceanVariant.Ignore.TEXTURE))
            return ResourceLocation(
                HybridAquatic.MOD_ID,
                "textures/entity/crustacean/${id}/${id}_${variant.getProvidedVariant(animatable)}.png"
            )
        return ResourceLocation(HybridAquatic.MOD_ID, "textures/entity/crustacean/${id}/$id.png")
    }

    override fun getAnimationResource(animatable: T): ResourceLocation {
        val variant = animatable.variant
        if (variant != null && !variant.ignore.contains(HybridAquaticCrustaceanEntity.CrustaceanVariant.Ignore.ANIMATION))
            return ResourceLocation(
                HybridAquatic.MOD_ID,
                "animations/${id}_${variant.getProvidedVariant(animatable)}.animation.json"
            )
        return ResourceLocation(HybridAquatic.MOD_ID, "animations/$id.animation.json")
    }
}