package dev.hybridlabs.aquatic.client.render.entity.critter.layer

import dev.hybridlabs.aquatic.client.model.HADataTickets
import dev.hybridlabs.aquatic.client.render.entity.layer.HATextureLayer
import dev.hybridlabs.aquatic.entity.feature.OverlayTextureFeature
import dev.hybridlabs.hapi.client.model.entity.aquatic.BaseCritterEntityModel
import dev.hybridlabs.hapi.client.render.entity.aquatic.BaseCritterEntityRenderer
import dev.hybridlabs.hapi.entity.base.aquatic.BaseCritterEntity
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState
import net.minecraft.resources.Identifier

class HACritterEntityLayer<T : BaseCritterEntity>(
    renderer: BaseCritterEntityRenderer<T, LivingEntityRenderState>
) : HATextureLayer<T>(renderer) {

    override fun addRenderData(
        animatable: T,
        relatedObject: Void?,
        renderState: LivingEntityRenderState,
        partialTick: Float
    ) {
        super.addRenderData(animatable, relatedObject, renderState, partialTick)
        renderState.addGeckolibData(
            HADataTickets.OVERLAY_NAME,
            (animatable as? OverlayTextureFeature)?.getOverlayTextureName() ?: ""
        )
    }

    override fun layerTexture(renderState: LivingEntityRenderState): Identifier? {
        val overlayName = renderState.getGeckolibData(HADataTickets.OVERLAY_NAME) ?: return null

        if (overlayName.isEmpty()) return null

        return (getGeoModel() as BaseCritterEntityModel).getLayerTextureResource(overlayName.lowercase())
    }
}
