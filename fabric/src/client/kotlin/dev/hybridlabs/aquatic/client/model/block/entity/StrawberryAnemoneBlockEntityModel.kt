package dev.hybridlabs.aquatic.client.model.block.entity

import dev.hybridlabs.aquatic.HybridAquatic
import dev.hybridlabs.aquatic.block.entity.StrawberryAnemoneBlockEntity
import net.minecraft.util.Identifier
import software.bernie.geckolib.model.GeoModel

class StrawberryAnemoneBlockEntityModel : GeoModel<StrawberryAnemoneBlockEntity>() {
    override fun getAnimationResource(entity: StrawberryAnemoneBlockEntity): Identifier {
        return ANIMATION_LOCATION
    }

    override fun getModelResource(animatable: StrawberryAnemoneBlockEntity): Identifier {
        return MODEL_LOCATION
    }

    override fun getTextureResource(entity: StrawberryAnemoneBlockEntity): Identifier {
        return TEXTURE_LOCATION
    }

    companion object {
        val ANIMATION_LOCATION = Identifier(HybridAquatic.MOD_ID, "animations/anemone.animation.json")
        val MODEL_LOCATION = Identifier(HybridAquatic.MOD_ID, "geo/strawberry_anemone.geo.json")
        val TEXTURE_LOCATION = Identifier(HybridAquatic.MOD_ID, "textures/block/strawberry_anemone.png")
    }
}
