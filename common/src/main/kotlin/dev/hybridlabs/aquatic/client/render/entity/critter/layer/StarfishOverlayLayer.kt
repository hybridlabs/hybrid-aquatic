package dev.hybridlabs.aquatic.client.render.entity.critter.layer

import dev.hybridlabs.aquatic.client.model.HADataTickets
import dev.hybridlabs.aquatic.client.render.entity.critter.StarfishEntityRenderer
import dev.hybridlabs.aquatic.client.render.entity.layer.HATextureLayer
import dev.hybridlabs.aquatic.entity.critter.StarfishEntity
import dev.hybridlabs.hapi.client.model.entity.aquatic.BaseCritterEntityModel
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState
import net.minecraft.resources.Identifier

class StarfishOverlayLayer(
    renderer: StarfishEntityRenderer,
) : HATextureLayer<StarfishEntity>(renderer, tinted = true) {

    override fun addRenderData(
        animatable: StarfishEntity,
        relatedObject: Void?,
        renderState: LivingEntityRenderState,
        partialTick: Float
    ) {
        super.addRenderData(animatable, relatedObject, renderState, partialTick)
        renderState.addGeckolibData(HADataTickets.OVERLAY_NAME, animatable.getOverlayTextureName())
        renderState.addGeckolibData(HADataTickets.OVERLAY_COLOR, animatable.overlayColor)
    }

    override fun layerTexture(renderState: LivingEntityRenderState): Identifier? {
        val overlayName = renderState.getGeckolibData(HADataTickets.OVERLAY_NAME) ?: return null

        if (overlayName.isEmpty()) return null

        return (getGeoModel() as BaseCritterEntityModel).getLayerTextureResource(overlayName.lowercase())
    }

    override fun layerTint(renderState: LivingEntityRenderState): Int =
        renderState.getGeckolibData(HADataTickets.OVERLAY_COLOR) ?: WHITE
}
