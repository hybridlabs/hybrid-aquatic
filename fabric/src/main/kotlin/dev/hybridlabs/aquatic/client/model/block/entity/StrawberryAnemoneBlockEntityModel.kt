package dev.hybridlabs.aquatic.client.model.block.entity

import dev.hybridlabs.aquatic.HybridAquatic
import dev.hybridlabs.aquatic.block.entity.StrawberryAnemoneBlockEntity
import net.minecraft.resources.ResourceLocation
import software.bernie.geckolib.model.GeoModel

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
        val ANIMATION_LOCATION = ResourceLocation(HybridAquatic.MOD_ID, "animations/anemone.animation.json")
        val MODEL_LOCATION = ResourceLocation(HybridAquatic.MOD_ID, "geo/strawberry_anemone.geo.json")
        val TEXTURE_LOCATION = ResourceLocation(HybridAquatic.MOD_ID, "textures/block/strawberry_anemone.png")
    }
}
