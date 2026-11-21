package dev.hybridlabs.aquatic.client.model.entity.shark

import dev.hybridlabs.hapi.client.model.HAPIDataTickets
import dev.hybridlabs.hapi.client.model.getVariant
import software.bernie.geckolib.renderer.base.GeoRenderState
import dev.hybridlabs.aquatic.entity.shark.HoundSharkEntity
import dev.hybridlabs.hapi.client.model.entity.aquatic.BaseSharkEntityModel
import net.minecraft.resources.Identifier

class HoundSharkEntityModel : BaseSharkEntityModel<HoundSharkEntity>("hybrid_aquatic", "hound_shark") {

    private val LEOPARD_TEXTURE = Identifier.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/shark/hound_shark/leopard_shark.png")

    private val LEOPARD_MODEL = Identifier.fromNamespaceAndPath("hybrid_aquatic", "shark/hound_shark/leopard_shark")

    private val LEOPARD_ANIMATION = Identifier.fromNamespaceAndPath("hybrid_aquatic", "entity/shark/hound_shark/leopard_shark")

    override fun getTextureResource(renderState: GeoRenderState): Identifier {
        return when (renderState.getVariant(HoundSharkEntity.Type.entries)!!) {
            HoundSharkEntity.Type.LEOPARD -> LEOPARD_TEXTURE
        }
    }
    override fun getModelResource(renderState: GeoRenderState): Identifier {
        return when (renderState.getVariant(HoundSharkEntity.Type.entries)!!) {
            HoundSharkEntity.Type.LEOPARD -> LEOPARD_MODEL
        }
    }
    override fun getAnimationResource(animatable: HoundSharkEntity): Identifier {
        return when (animatable.variant) {
            HoundSharkEntity.Type.LEOPARD -> LEOPARD_ANIMATION
        }
    }
}
