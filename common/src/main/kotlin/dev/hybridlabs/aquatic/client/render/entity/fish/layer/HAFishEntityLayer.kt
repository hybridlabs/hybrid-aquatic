package dev.hybridlabs.aquatic.client.render.entity.fish.layer

import dev.hybridlabs.aquatic.client.model.HADataTickets
import dev.hybridlabs.aquatic.client.render.entity.layer.HATextureLayer
import dev.hybridlabs.aquatic.entity.feature.OverlayTextureFeature
import dev.hybridlabs.hapi.client.model.entity.aquatic.BaseFishEntityModel
import dev.hybridlabs.hapi.client.render.entity.aquatic.BaseFishEntityRenderer
import dev.hybridlabs.hapi.entity.base.aquatic.BaseFishEntity
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState
import net.minecraft.resources.Identifier

class HAFishEntityLayer<T : BaseFishEntity>(
    renderer: BaseFishEntityRenderer<T, LivingEntityRenderState>
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

        return (getGeoModel() as BaseFishEntityModel).getLayerTextureResource(overlayName.lowercase())
    }
}
