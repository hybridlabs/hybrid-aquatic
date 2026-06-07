package dev.hybridlabs.aquatic.client.model.entity.shark

import dev.hybridlabs.aquatic.entity.shark.HoundSharkEntity
import net.minecraft.resources.ResourceLocation

class HoundSharkEntityModel : HASharkEntityModel<HoundSharkEntity>("hound_shark") {

    private val LEOPARD_TEXTURE = ResourceLocation("hybrid_aquatic", "textures/entity/shark/hound_shark/leopard_shark.png")

    private val LEOPARD_MODEL = ResourceLocation("hybrid_aquatic", "geo/shark/hound_shark/leopard_shark.geo.json")

    private val LEOPARD_ANIMATION = ResourceLocation("hybrid_aquatic", "animations/entity/shark/hound_shark/leopard_shark.animation.json")

    override fun getTextureResource(animatable: HoundSharkEntity): ResourceLocation {
        return when (animatable.variant) {
            HoundSharkEntity.Type.LEOPARD -> LEOPARD_TEXTURE
        }
    }
    override fun getModelResource(animatable: HoundSharkEntity): ResourceLocation {
        return when (animatable.variant) {
            HoundSharkEntity.Type.LEOPARD -> LEOPARD_MODEL
        }
    }
    override fun getAnimationResource(animatable: HoundSharkEntity): ResourceLocation {
        return when (animatable.variant) {
            HoundSharkEntity.Type.LEOPARD -> LEOPARD_ANIMATION
        }
    }
}
