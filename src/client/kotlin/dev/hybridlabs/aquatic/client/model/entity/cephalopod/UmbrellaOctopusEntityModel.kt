package dev.hybridlabs.aquatic.client.model.entity.cephalopod

import dev.hybridlabs.aquatic.entity.cephalopod.HybridAquaticCephalopodEntity
import net.minecraft.util.Identifier

class UmbrellaOctopusEntityModel : HybridAquaticCephalopodEntityModel<HybridAquaticCephalopodEntity>("umbrella_octopus") {
    override fun getTextureResource(animatable: HybridAquaticCephalopodEntity?): Identifier {
        if (animatable != null) return getVariantTexture(allVariants[animatable.variant])
        return super.getTextureResource(animatable)
    }

    companion object {
        val allVariants: Array<String> = arrayOf("white", "brown", "orange", "yellow", "pink", "purple")
    }
}

