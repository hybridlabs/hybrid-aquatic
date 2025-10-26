package dev.hybridlabs.aquatic.client.model.block.entity

import dev.hybridlabs.aquatic.CommonClass
import dev.hybridlabs.aquatic.block.entity.BuoyBlockEntity
import net.minecraft.resources.ResourceLocation
import software.bernie.geckolib.model.GeoModel

@Suppress("OVERRIDE_DEPRECATION")
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
        val ANIMATION_LOCATION = CommonClass.locate("animations/buoy.animation.json")
        val MODEL_LOCATION = CommonClass.locate("geo/buoy.geo.json")
        val TEXTURE_LOCATION = CommonClass.locate("textures/block/buoy.png")
    }
}