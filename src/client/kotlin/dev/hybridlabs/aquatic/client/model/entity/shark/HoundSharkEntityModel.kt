package dev.hybridlabs.aquatic.client.model.entity.shark

import dev.hybridlabs.aquatic.entity.shark.HoundSharkEntity
import net.minecraft.util.Identifier

class HoundSharkEntityModel : HybridAquaticSharkEntityModel<HoundSharkEntity>("hound_shark") {

    private val LEOPARD_TEXTURE = Identifier.of("hybrid-aquatic", "textures/entity/shark/hound_shark/leopard_shark.png")

    private val LEOPARD_MODEL = Identifier.of("hybrid-aquatic", "geo/shark/hound_shark/leopard_shark.geo.json")

    private val LEOPARD_ANIMATION = Identifier.of("hybrid-aquatic", "animations/leopard_shark.animation.json")

    override fun getTextureResource(animatable: HoundSharkEntity): Identifier {
        return when (animatable.variant) {
            HoundSharkEntity.Companion.Type.LEOPARD -> LEOPARD_TEXTURE
        }
    }
    override fun getModelResource(animatable: HoundSharkEntity): Identifier {
        return when (animatable.variant) {
            HoundSharkEntity.Companion.Type.LEOPARD -> LEOPARD_MODEL
        }
    }
    override fun getAnimationResource(animatable: HoundSharkEntity): Identifier {
        return when (animatable.variant) {
            HoundSharkEntity.Companion.Type.LEOPARD -> LEOPARD_ANIMATION
        }
    }
}