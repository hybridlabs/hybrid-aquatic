package dev.hybridlabs.aquatic.client.model.block.entity

import dev.hybridlabs.aquatic.CommonClass
import dev.hybridlabs.aquatic.block.entity.GiantGreenAnemoneBlockEntity
import net.minecraft.resources.ResourceLocation
import software.bernie.geckolib.model.GeoModel

@Suppress("OVERRIDE_DEPRECATION")
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
        val ANIMATION_LOCATION: ResourceLocation = CommonClass.locate("animations/anemone.animation.json")
        val MODEL_LOCATION: ResourceLocation = CommonClass.locate("geo/giant_green_anemone.geo.json")
        val TEXTURE_LOCATION: ResourceLocation = CommonClass.locate("textures/block/giant_green_anemone.png")
    }
}