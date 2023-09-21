package dev.hybridlabs.aquatic.client.model.entity

import dev.hybridlabs.aquatic.entity.fish.HybridAquaticFishEntity
import net.minecraft.util.Identifier

class DiscusEntityModel : HybridAquaticFishEntityModel<HybridAquaticFishEntity>("discus") {
    override fun getTextureResource(animatable: HybridAquaticFishEntity?): Identifier {
        if (animatable != null) return getVariantTexture(allVariants[animatable.variant])
        return super.getTextureResource(animatable)
    }

    companion object {
        val allVariants: Array<String> = arrayOf("blue", "green", "orange", "purple")
    }
}
