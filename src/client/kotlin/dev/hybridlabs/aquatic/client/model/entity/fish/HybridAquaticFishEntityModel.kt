package dev.hybridlabs.aquatic.client.model.entity.fish

import dev.hybridlabs.aquatic.HybridAquatic
import dev.hybridlabs.aquatic.entity.fish.HybridAquaticFishEntity
import dev.hybridlabs.aquatic.entity.fish.HybridAquaticFishEntity.FishVariant.Ignore.ANIMATION
import dev.hybridlabs.aquatic.entity.fish.HybridAquaticFishEntity.FishVariant.Ignore.MODEL
import dev.hybridlabs.aquatic.entity.fish.HybridAquaticFishEntity.FishVariant.Ignore.TEXTURE
import net.minecraft.util.Identifier
import software.bernie.geckolib.model.GeoModel

abstract class HybridAquaticFishEntityModel<T: HybridAquaticFishEntity> (private val id: String) : GeoModel<T>() {
    override fun getModelResource(animatable: T?): Identifier {
        val variant = animatable?.variant
        if (variant != null && !variant.ignore.contains(MODEL))
            return Identifier.of(HybridAquatic.MOD_ID, "geo/fish/${id}/${id}_${variant.getProvidedVariant(animatable)}.geo.json")
        return Identifier.of(HybridAquatic.MOD_ID, "geo/fish/${id}/$id.geo.json")
    }

    override fun getTextureResource(animatable: T?): Identifier {
        val variant = animatable?.variant
        if (variant != null && !variant.ignore.contains(TEXTURE))
            return Identifier.of(HybridAquatic.MOD_ID, "textures/entity/fish/${id}/${id}_${variant.getProvidedVariant(animatable)}.png")
        return Identifier.of(HybridAquatic.MOD_ID, "textures/entity/fish/${id}/$id.png")
    }

    override fun getAnimationResource(animatable: T?): Identifier {
        val variant = animatable?.variant
        if (variant != null && !variant.ignore.contains(ANIMATION))
            return Identifier.of(HybridAquatic.MOD_ID, "animations/${id}_${variant.getProvidedVariant(animatable)}.animation.json")
        return Identifier.of(HybridAquatic.MOD_ID, "animations/$id.animation.json")
    }
}
