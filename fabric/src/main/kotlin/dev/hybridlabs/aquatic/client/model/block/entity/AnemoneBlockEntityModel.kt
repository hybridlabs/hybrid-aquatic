package dev.hybridlabs.aquatic.client.model.block.entity

import dev.hybridlabs.aquatic.Constants
import dev.hybridlabs.aquatic.block.entity.AnemoneBlockEntity
import net.minecraft.resources.ResourceLocation
import software.bernie.geckolib.model.GeoModel

class AnemoneBlockEntityModel : GeoModel<AnemoneBlockEntity>() {
    override fun getAnimationResource(entity: AnemoneBlockEntity): ResourceLocation {
        return ANIMATION_LOCATION
    }

    override fun getModelResource(animatable: AnemoneBlockEntity): ResourceLocation {
        return MODEL_LOCATION
    }

    override fun getTextureResource(entity: AnemoneBlockEntity): ResourceLocation {
        return TEXTURE_LOCATION
    }

    companion object {
        val ANIMATION_LOCATION = ResourceLocation(Constants.MOD_ID, "animations/anemone.animation.json")
        val MODEL_LOCATION = ResourceLocation(Constants.MOD_ID, "geo/anemone.geo.json")
        val TEXTURE_LOCATION = ResourceLocation(Constants.MOD_ID, "textures/block/anemone.png")
    }
}
