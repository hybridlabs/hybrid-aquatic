package dev.hybridlabs.aquatic.client.model.entity.fish

import dev.hybridlabs.aquatic.client.model.HADataTickets
import dev.hybridlabs.hapi.client.model.HAPIDataTickets
import com.geckolib.renderer.base.GeoRenderState
import dev.hybridlabs.aquatic.CommonClass
import dev.hybridlabs.aquatic.entity.fish.BlowfishEntity
import dev.hybridlabs.hapi.client.model.entity.aquatic.BaseFishEntityModel
import net.minecraft.client.renderer.rendertype.RenderType
import net.minecraft.client.renderer.rendertype.RenderTypes
import net.minecraft.resources.Identifier

class BlowfishEntityModel : BaseFishEntityModel<BlowfishEntity>("hybrid_aquatic", "blowfish") {

    override fun getTextureResource(renderState: GeoRenderState): Identifier {
        val puffState = (renderState.getGeckolibData(HADataTickets.PUFF_STATE) ?: 0)

        val texturePath = when (puffState) {
            0 -> "textures/entity/fish/blowfish/blowfish_small.png"
            1 -> "textures/entity/fish/blowfish/blowfish_medium.png"
            else -> "textures/entity/fish/blowfish/blowfish_large.png"
        }

        return CommonClass.locate(texturePath)
    }

    override fun addAdditionalStateData(
        animatable: BlowfishEntity,
        relatedObject: Any?,
        renderState: GeoRenderState
    ) {
        renderState.addGeckolibData(HADataTickets.PUFF_STATE, animatable.getPuffState())
    }

    override fun getModelResource(renderState: GeoRenderState): Identifier {
        val puffState = (renderState.getGeckolibData(HADataTickets.PUFF_STATE) ?: 0)

        val texturePath = when (puffState) {
            0 -> "fish/blowfish/blowfish_small"
            1 -> "fish/blowfish/blowfish_medium"
            else -> "fish/blowfish/blowfish_large"
        }

        return CommonClass.locate(texturePath)
    }
}
