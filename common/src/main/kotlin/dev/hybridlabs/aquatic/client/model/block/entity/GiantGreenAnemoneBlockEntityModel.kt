package dev.hybridlabs.aquatic.client.model.block.entity

import dev.hybridlabs.aquatic.Constants
import dev.hybridlabs.aquatic.block.entity.GiantGreenAnemoneBlockEntity
import net.minecraft.resources.ResourceLocation
import software.bernie.geckolib.model.GeoModel

class GiantGreenAnemoneBlockEntityModel : GeoModel<GiantGreenAnemoneBlockEntity>() {
    override fun getAnimationResource(entity: GiantGreenAnemoneBlockEntity): ResourceLocation {
        return ANIMATION_LOCATION
    }

    override fun getModelResource(animatable: GiantGreenAnemoneBlockEntity): ResourceLocation {
        return MODEL_LOCATION
    }

    override fun getTextureResource(entity: GiantGreenAnemoneBlockEntity): ResourceLocation {
        return TEXTURE_LOCATION
    }

    companion object {
        val ANIMATION_LOCATION = ResourceLocation(Constants.MOD_ID, "animations/anemone.animation.json")
        val MODEL_LOCATION = ResourceLocation(Constants.MOD_ID, "geo/giant_green_anemone.geo.json")
        val TEXTURE_LOCATION = ResourceLocation(Constants.MOD_ID, "textures/block/giant_green_anemone.png")
    }
}
