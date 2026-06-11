package dev.hybridlabs.aquatic.client.model.block.entity

import dev.hybridlabs.aquatic.CommonClass
import dev.hybridlabs.aquatic.block.entity.StrawberryAnemoneBlockEntity
import net.minecraft.resources.ResourceLocation
import software.bernie.geckolib.model.GeoModel

@Suppress("OVERRIDE_DEPRECATION")
class StrawberryAnemoneBlockEntityModel : GeoModel<StrawberryAnemoneBlockEntity>() {
    override fun getAnimationResource(entity: StrawberryAnemoneBlockEntity): ResourceLocation {
        return ANIMATION_LOCATION
    }

    override fun getModelResource(animatable: StrawberryAnemoneBlockEntity): ResourceLocation {
        return MODEL_LOCATION
    }

    override fun getTextureResource(entity: StrawberryAnemoneBlockEntity): ResourceLocation {
        return TEXTURE_LOCATION
    }

    companion object {
        val ANIMATION_LOCATION: ResourceLocation = CommonClass.locate("animations/anemone.animation.json")
        val MODEL_LOCATION: ResourceLocation = CommonClass.locate("geo/strawberry_anemone.geo.json")
        val TEXTURE_LOCATION: ResourceLocation = CommonClass.locate("textures/block/strawberry_anemone.png")
    }
}