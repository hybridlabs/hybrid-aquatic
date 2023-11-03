package dev.hybridlabs.aquatic.client.model.block.entity

import dev.hybridlabs.aquatic.HybridAquatic
import dev.hybridlabs.aquatic.block.entity.BuoyBlockEntity
import net.minecraft.util.Identifier
import software.bernie.geckolib.model.GeoModel

class BuoyBlockEntityModel: GeoModel<BuoyBlockEntity>() {
    override fun getAnimationResource(entity: BuoyBlockEntity): Identifier {
        return ANIMATION_LOCATION
    }

    override fun getModelResource(animatable: BuoyBlockEntity): Identifier {
        return MODEL_LOCATION
    }

    override fun getTextureResource(entity: BuoyBlockEntity): Identifier {
        return TEXTURE_LOCATION
    }

    companion object {
        val ANIMATION_LOCATION = Identifier(HybridAquatic.MOD_ID, "animations/buoy.animation.json")
        val MODEL_LOCATION = Identifier(HybridAquatic.MOD_ID, "geo/entity/block/buoy.geo.json")
        val TEXTURE_LOCATION = Identifier(HybridAquatic.MOD_ID, "textures/block/buoy.png")
    }
}