package dev.hybridlabs.aquatic.client.render.entity.crustacean.layer

import dev.hybridlabs.aquatic.client.model.HADataTickets
import dev.hybridlabs.aquatic.client.render.entity.layer.HATextureLayer
import dev.hybridlabs.aquatic.entity.feature.OverlayTextureFeature
import dev.hybridlabs.hapi.client.model.entity.aquatic.BaseCrustaceanEntityModel
import dev.hybridlabs.hapi.client.render.entity.aquatic.BaseCrustaceanEntityRenderer
import dev.hybridlabs.hapi.entity.base.aquatic.BaseCrustaceanEntity
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState
import net.minecraft.resources.Identifier

class HACrustaceanEntityLayer<T : BaseCrustaceanEntity>(
    renderer: BaseCrustaceanEntityRenderer<T, LivingEntityRenderState>
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

        return (getGeoModel() as BaseCrustaceanEntityModel).getLayerTextureResource(overlayName.lowercase())
    }
}
