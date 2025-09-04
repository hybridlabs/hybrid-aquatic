package dev.hybridlabs.aquatic.client.model.block.entity

import dev.hybridlabs.aquatic.HybridAquatic
import dev.hybridlabs.aquatic.block.entity.GiantGreenAnemoneBlockEntity
import net.minecraft.util.Identifier
import software.bernie.geckolib.model.GeoModel

class GiantGreenAnemoneBlockEntityModel : GeoModel<GiantGreenAnemoneBlockEntity>() {
    override fun getAnimationResource(entity: GiantGreenAnemoneBlockEntity): Identifier {
        return ANIMATION_LOCATION
    }

    override fun getModelResource(animatable: GiantGreenAnemoneBlockEntity): Identifier {
        return MODEL_LOCATION
    }

    override fun getTextureResource(entity: GiantGreenAnemoneBlockEntity): Identifier {
        return TEXTURE_LOCATION
    }

    companion object {
        val ANIMATION_LOCATION = Identifier(HybridAquatic.MOD_ID, "animations/anemone.animation.json")
        val MODEL_LOCATION = Identifier(HybridAquatic.MOD_ID, "geo/giant_green_anemone.geo.json")
        val TEXTURE_LOCATION = Identifier(HybridAquatic.MOD_ID, "textures/block/giant_green_anemone.png")
    }
}
