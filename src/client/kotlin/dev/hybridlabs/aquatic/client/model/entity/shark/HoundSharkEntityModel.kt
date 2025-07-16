package dev.hybridlabs.aquatic.client.model.entity.shark

import dev.hybridlabs.aquatic.entity.shark.HoundSharkEntity
import net.minecraft.util.Identifier

class HoundSharkEntityModel : HybridAquaticSharkEntityModel<HoundSharkEntity>("hound_shark") {

    private val LEOPARD_TEXTURE = Identifier("hybrid-aquatic", "textures/entity/shark/leopard_shark.png")

    private val LEOPARD_MODEL = Identifier("hybrid-aquatic", "geo/shark/leopard_shark.geo.json")

    private val LEOPARD_ANIMATION = Identifier("hybrid-aquatic", "animations/leopard_shark.animation.json")

    override fun getTextureResource(animatable: HoundSharkEntity): Identifier {
        return when (animatable.variant) {
            HoundSharkEntity.Type.LEOPARD -> LEOPARD_TEXTURE
        }
    }
    override fun getModelResource(animatable: HoundSharkEntity): Identifier {
        return when (animatable.variant) {
            HoundSharkEntity.Type.LEOPARD -> LEOPARD_MODEL
        }
    }
    override fun getAnimationResource(animatable: HoundSharkEntity): Identifier {
        return when (animatable.variant) {
            HoundSharkEntity.Type.LEOPARD -> LEOPARD_ANIMATION
        }
    }
}