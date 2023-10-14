package dev.hybridlabs.aquatic.client.model.entity

import dev.hybridlabs.aquatic.entity.critter.HybridAquaticCritterEntity
import net.minecraft.util.Identifier

class ShrimpEntityModel : HybridAquaticCritterEntityModel<HybridAquaticCritterEntity>("shrimp") {
    override fun getTextureResource(animatable: HybridAquaticCritterEntity?): Identifier {
        if (animatable != null) return getVariantTexture(allVariants[animatable.variant])
        return super.getTextureResource(animatable)
    }

    companion object {
        val allVariants: Array<String> = arrayOf("blue", "blue_white", "black", "black_white", "red", "red_white", "green", "green_white", "brown", "brown_white", "lime", "lime_white", "yellow", "yellow_white", "pink", "pink_white", "white", "orange", "orange_white")
    }
}
