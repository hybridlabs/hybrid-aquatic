package dev.hybridlabs.aquatic.client.model.entity.shark

import dev.hybridlabs.aquatic.entity.shark.HoundSharkEntity
import dev.hybridlabs.hapi.client.model.entity.aquatic.BaseSharkEntityModel
import net.minecraft.resources.ResourceLocation

class HoundSharkEntityModel : BaseSharkEntityModel<HoundSharkEntity>("hybrid_aquatic", "hound_shark") {

    private val LEOPARD_TEXTURE = ResourceLocation.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/shark/hound_shark/leopard_shark.png")

    private val LEOPARD_MODEL = ResourceLocation.fromNamespaceAndPath("hybrid_aquatic", "geo/shark/hound_shark/leopard_shark.geo.json")

    private val LEOPARD_ANIMATION = ResourceLocation.fromNamespaceAndPath("hybrid_aquatic", "animations/entity/shark/hound_shark/leopard_shark.animation.json")

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
