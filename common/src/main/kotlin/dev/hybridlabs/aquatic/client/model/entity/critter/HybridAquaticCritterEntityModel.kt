package dev.hybridlabs.aquatic.client.model.entity.critter

import dev.hybridlabs.aquatic.HybridAquatic
import dev.hybridlabs.aquatic.entity.critter.HybridAquaticCritterEntity
import net.minecraft.resources.ResourceLocation
import software.bernie.geckolib.model.GeoModel

abstract class HybridAquaticCritterEntityModel<T : HybridAquaticCritterEntity>(private val id: String) : GeoModel<T>() {
    override fun getModelResource(animatable: T): ResourceLocation {
        val variant = animatable.variant
        if (variant != null && !variant.ignore.contains(HybridAquaticCritterEntity.CritterVariant.Ignore.MODEL))
            return ResourceLocation(
                HybridAquatic.MOD_ID,
                "geo/critter/${id}/${id}_${variant.getProvidedVariant(animatable)}.geo.json"
            )
        return ResourceLocation(HybridAquatic.MOD_ID, "geo/critter/${id}/$id.geo.json")
    }

    override fun getTextureResource(animatable: T): ResourceLocation {
        val variant = animatable.variant
        if (variant != null && !variant.ignore.contains(HybridAquaticCritterEntity.CritterVariant.Ignore.TEXTURE))
            return ResourceLocation(
                HybridAquatic.MOD_ID,
                "textures/entity/critter/${id}/${id}_${variant.getProvidedVariant(animatable)}.png"
            )
        return ResourceLocation(HybridAquatic.MOD_ID, "textures/entity/critter/$id/${id}.png")
    }

    override fun getAnimationResource(animatable: T): ResourceLocation {
        val variant = animatable.variant
        if (variant != null && !variant.ignore.contains(HybridAquaticCritterEntity.CritterVariant.Ignore.ANIMATION))
            return ResourceLocation(
                HybridAquatic.MOD_ID,
                "animations/${id}_${variant.getProvidedVariant(animatable)}.animation.json"
            )
        return ResourceLocation(HybridAquatic.MOD_ID, "animations/$id.animation.json")
    }
}