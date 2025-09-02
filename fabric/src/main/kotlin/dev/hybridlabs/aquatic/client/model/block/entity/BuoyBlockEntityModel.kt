package dev.hybridlabs.aquatic.client.model.block.entity

import dev.hybridlabs.aquatic.Constants
import dev.hybridlabs.aquatic.block.entity.BuoyBlockEntity
import net.minecraft.resources.ResourceLocation
import software.bernie.geckolib.model.GeoModel

class BuoyBlockEntityModel : GeoModel<BuoyBlockEntity>() {
    override fun getAnimationResource(entity: BuoyBlockEntity): ResourceLocation {
        return ANIMATION_LOCATION
    }

    override fun getModelResource(animatable: BuoyBlockEntity): ResourceLocation {
        return MODEL_LOCATION
    }

    override fun getTextureResource(entity: BuoyBlockEntity): ResourceLocation {
        return TEXTURE_LOCATION
    }

    companion object {
        val ANIMATION_LOCATION = ResourceLocation(Constants.MOD_ID, "animations/buoy.animation.json")
        val MODEL_LOCATION = ResourceLocation(Constants.MOD_ID, "geo/buoy.geo")
        val TEXTURE_LOCATION = ResourceLocation(Constants.MOD_ID, "textures/block/buoy.png")
    }
}