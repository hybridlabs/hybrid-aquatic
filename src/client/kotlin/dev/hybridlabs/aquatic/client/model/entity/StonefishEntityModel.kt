package dev.hybridlabs.aquatic.client.model.entity

import dev.hybridlabs.aquatic.entity.fish.HybridAquaticFishEntity
import net.minecraft.util.Identifier

class StonefishEntityModel : HybridAquaticFishEntityModel<HybridAquaticFishEntity>("stonefish") {
    override fun getTextureResource(animatable: HybridAquaticFishEntity?): Identifier {
        if (animatable != null) return getVariantTexture(allVariants[animatable.variant % allVariants.size])
        return super.getTextureResource(animatable)
    }

    companion object {
        val allVariants: Array<String> = arrayOf("brown", "gray", "green")
    }
}

