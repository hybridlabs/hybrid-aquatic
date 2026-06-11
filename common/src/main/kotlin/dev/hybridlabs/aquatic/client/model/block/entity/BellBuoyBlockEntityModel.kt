package dev.hybridlabs.aquatic.client.model.block.entity

import dev.hybridlabs.aquatic.CommonClass
import dev.hybridlabs.aquatic.block.entity.BellBuoyBlockEntity
import net.minecraft.resources.ResourceLocation
import software.bernie.geckolib.model.GeoModel

@Suppress("OVERRIDE_DEPRECATION")
class BellBuoyBlockEntityModel : GeoModel<BellBuoyBlockEntity>() {
    override fun getAnimationResource(entity: BellBuoyBlockEntity): ResourceLocation {
        return ANIMATION_LOCATION
    }

    override fun getModelResource(animatable: BellBuoyBlockEntity): ResourceLocation {
        return MODEL_LOCATION
    }

    override fun getTextureResource(entity: BellBuoyBlockEntity): ResourceLocation {
        return TEXTURE_LOCATION
    }

    companion object {
        val ANIMATION_LOCATION: ResourceLocation = CommonClass.locate("animations/buoy.animation.json")
        val MODEL_LOCATION: ResourceLocation = CommonClass.locate("geo/bell_buoy.geo.json")
        val TEXTURE_LOCATION: ResourceLocation = CommonClass.locate("textures/block/bell_buoy.png")
    }
}