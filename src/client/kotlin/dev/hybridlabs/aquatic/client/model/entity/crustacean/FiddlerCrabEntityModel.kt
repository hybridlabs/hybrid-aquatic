package dev.hybridlabs.aquatic.client.model.entity.crustacean

import dev.hybridlabs.aquatic.entity.crustacean.HybridAquaticCrustaceanEntity
import net.minecraft.util.Identifier

class FiddlerCrabEntityModel : HybridAquaticCrustaceanEntityModel<HybridAquaticCrustaceanEntity>("fiddler_crab") {
    override fun getTextureResource(animatable: HybridAquaticCrustaceanEntity?): Identifier {
        if (animatable != null) return getVariantTexture(allVariants[animatable.variant])
        return super.getTextureResource(animatable)
    }

    companion object {
        val allVariants: Array<String> = arrayOf("blue", "red", "purple", "palestine")
    }
}
