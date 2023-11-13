package dev.hybridlabs.aquatic.client.model.entity

import dev.hybridlabs.aquatic.entity.critter.HybridAquaticCritterEntity
import net.minecraft.util.Identifier

class LobsterEntityModel : HybridAquaticCritterEntityModel<HybridAquaticCritterEntity>("lobster") {
    override fun getTextureResource(animatable: HybridAquaticCritterEntity?): Identifier {
        if (animatable != null) return getVariantTexture(allVariants[animatable.variant])
        return super.getTextureResource(animatable)
    }

    companion object {
        val allVariants: Array<String> = arrayOf("blue", "black", "red", "yellow", "green", "white")
    }
}
