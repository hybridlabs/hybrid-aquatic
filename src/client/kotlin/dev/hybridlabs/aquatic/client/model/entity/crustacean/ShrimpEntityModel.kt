package dev.hybridlabs.aquatic.client.model.entity.crustacean

import dev.hybridlabs.aquatic.entity.crustacean.HybridAquaticCrustaceanEntity
import net.minecraft.util.Identifier

class ShrimpEntityModel : HybridAquaticCrustaceanEntityModel<HybridAquaticCrustaceanEntity>("shrimp") {
    override fun getTextureResource(animatable: HybridAquaticCrustaceanEntity?): Identifier {
        if (animatable != null) return getVariantTexture(allVariants[animatable.variant])
        return super.getTextureResource(animatable)
    }

    companion object {
        val allVariants: Array<String> = arrayOf("blue", "blue_white", "black", "black_white", "red", "red_white", "green", "green_white", "brown", "brown_white", "lime", "lime_white", "yellow", "yellow_white", "pink", "pink_white", "white", "orange", "orange_white", "palestine")
    }
}
