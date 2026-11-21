package dev.hybridlabs.aquatic.client.render.entity.mammal.layer

import dev.hybridlabs.aquatic.client.model.HADataTickets
import dev.hybridlabs.aquatic.client.model.entity.mammal.OrcaEntityModel
import dev.hybridlabs.aquatic.client.render.entity.layer.HATextureLayer
import dev.hybridlabs.aquatic.entity.mammal.OrcaEntity
import dev.hybridlabs.hapi.client.render.entity.aquatic.BaseDolphinEntityRenderer
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState
import net.minecraft.resources.Identifier

class OrcaEyeSpotEntityLayer(
    renderer: BaseDolphinEntityRenderer<OrcaEntity, LivingEntityRenderState>
) : HATextureLayer<OrcaEntity>(renderer) {

    override fun addRenderData(
        animatable: OrcaEntity,
        relatedObject: Void?,
        renderState: LivingEntityRenderState,
        partialTick: Float
    ) {
        super.addRenderData(animatable, relatedObject, renderState, partialTick)
        renderState.addGeckolibData(HADataTickets.EYE_SPOT_NAME, animatable.getEyeSpotTextureName())
    }

    override fun layerTexture(renderState: LivingEntityRenderState): Identifier? {
        val eyeSpotName = renderState.getGeckolibData(HADataTickets.EYE_SPOT_NAME) ?: return null

        if (eyeSpotName.isEmpty()) return null

        return (getGeoModel() as OrcaEntityModel).getEyeSpotTextureResource(renderState, eyeSpotName)
    }
}
