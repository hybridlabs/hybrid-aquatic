package dev.hybridlabs.aquatic.client.model.block.entity

import dev.hybridlabs.aquatic.HybridAquatic
import dev.hybridlabs.aquatic.block.entity.AnemoneBlockEntity
import net.minecraft.util.Identifier
import software.bernie.geckolib.model.GeoModel

class AnemoneBlockEntityModel : GeoModel<AnemoneBlockEntity>() {
    override fun getAnimationResource(entity: AnemoneBlockEntity): Identifier {
        return ANIMATION_LOCATION
    }

    override fun getModelResource(animatable: AnemoneBlockEntity): Identifier {
        return MODEL_LOCATION
    }

    override fun getTextureResource(entity: AnemoneBlockEntity): Identifier {
        return TEXTURE_LOCATION
    }

    companion object {
        val ANIMATION_LOCATION = Identifier(HybridAquatic.MOD_ID, "animations/anemone.animation.json")
        val MODEL_LOCATION = Identifier(HybridAquatic.MOD_ID, "geo/anemone.geo.json")
        val TEXTURE_LOCATION = Identifier(HybridAquatic.MOD_ID, "textures/block/anemone.png")
    }
}
