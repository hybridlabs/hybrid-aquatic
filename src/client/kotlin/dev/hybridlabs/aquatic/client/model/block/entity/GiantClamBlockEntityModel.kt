package dev.hybridlabs.aquatic.client.model.block.entity

import dev.hybridlabs.aquatic.HybridAquatic
import dev.hybridlabs.aquatic.block.entity.GiantClamBlockEntity
import net.minecraft.util.Identifier
import software.bernie.geckolib.model.GeoModel

class GiantClamBlockEntityModel: GeoModel<GiantClamBlockEntity>() {
    override fun getAnimationResource(entity: GiantClamBlockEntity): Identifier {
        return ANIMATION_LOCATION
    }

    override fun getModelResource(animatable: GiantClamBlockEntity): Identifier {
        return MODEL_LOCATION
    }

    override fun getTextureResource(entity: GiantClamBlockEntity): Identifier {
        return TEXTURE_LOCATION
    }

    companion object {
        val ANIMATION_LOCATION = Identifier(HybridAquatic.MOD_ID, "animations/giant_clam.animation.json")
        val MODEL_LOCATION = Identifier(HybridAquatic.MOD_ID, "geo/giant_clam.geo.json")
        val TEXTURE_LOCATION = Identifier(HybridAquatic.MOD_ID, "textures/block/giant_clam.png")
    }
}