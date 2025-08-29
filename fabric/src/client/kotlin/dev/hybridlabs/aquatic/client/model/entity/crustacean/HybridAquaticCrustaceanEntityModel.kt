package dev.hybridlabs.aquatic.client.model.entity.crustacean

import dev.hybridlabs.aquatic.HybridAquatic
import dev.hybridlabs.aquatic.entity.crustacean.HybridAquaticCrustaceanEntity
import net.minecraft.util.Identifier
import software.bernie.geckolib.model.GeoModel

abstract class HybridAquaticCrustaceanEntityModel<T : HybridAquaticCrustaceanEntity>(private val id: String) :
    GeoModel<T>() {
    override fun getModelResource(animatable: T): Identifier {
        val variant = animatable.variant
        if (variant != null && !variant.ignore.contains(HybridAquaticCrustaceanEntity.CrustaceanVariant.Ignore.MODEL))
            return Identifier(
                HybridAquatic.MOD_ID,
                "geo/crustacean/${id}/${id}_${variant.getProvidedVariant(animatable)}.geo.json"
            )
        return Identifier(HybridAquatic.MOD_ID, "geo/crustacean/${id}/$id.geo.json")
    }

    override fun getTextureResource(animatable: T): Identifier {
        val variant = animatable.variant
        if (variant != null && !variant.ignore.contains(HybridAquaticCrustaceanEntity.CrustaceanVariant.Ignore.TEXTURE))
            return Identifier(
                HybridAquatic.MOD_ID,
                "textures/entity/crustacean/${id}/${id}_${variant.getProvidedVariant(animatable)}.png"
            )
        return Identifier(HybridAquatic.MOD_ID, "textures/entity/crustacean/${id}/$id.png")
    }

    override fun getAnimationResource(animatable: T): Identifier {
        val variant = animatable.variant
        if (variant != null && !variant.ignore.contains(HybridAquaticCrustaceanEntity.CrustaceanVariant.Ignore.ANIMATION))
            return Identifier(
                HybridAquatic.MOD_ID,
                "animations/${id}_${variant.getProvidedVariant(animatable)}.animation.json"
            )
        return Identifier(HybridAquatic.MOD_ID, "animations/$id.animation.json")
    }
}