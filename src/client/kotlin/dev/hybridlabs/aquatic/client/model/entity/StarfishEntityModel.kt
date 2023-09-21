package dev.hybridlabs.aquatic.client.model.entity

import dev.hybridlabs.aquatic.entity.critter.HybridAquaticCritterEntity
import net.minecraft.util.Identifier
class StarfishEntityModel : HybridAquaticCritterEntityModel<HybridAquaticCritterEntity>("starfish") {
    override fun getTextureResource(animatable: HybridAquaticCritterEntity?): Identifier {
        if (animatable != null) return getVariantTexture(allVariants[animatable.variant])
        return super.getTextureResource(animatable)
    }
    companion object {
        val allVariants: Array<String> = arrayOf("black", "blue", "green", "red", "white", "yellow", "purple")
    }
}
