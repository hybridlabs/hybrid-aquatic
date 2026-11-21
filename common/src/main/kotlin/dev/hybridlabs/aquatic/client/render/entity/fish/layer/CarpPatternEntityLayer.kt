package dev.hybridlabs.aquatic.client.render.entity.fish.layer

import dev.hybridlabs.aquatic.client.model.HADataTickets
import dev.hybridlabs.aquatic.client.model.entity.fish.CarpEntityModel
import dev.hybridlabs.aquatic.client.render.entity.layer.HATextureLayer
import dev.hybridlabs.aquatic.entity.fish.CarpEntity
import dev.hybridlabs.hapi.client.render.entity.aquatic.BaseFishEntityRenderer
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState
import net.minecraft.resources.Identifier

class CarpPatternEntityLayer(
    renderer: BaseFishEntityRenderer<CarpEntity, LivingEntityRenderState>
) : HATextureLayer<CarpEntity>(renderer) {

    override fun addRenderData(
        animatable: CarpEntity,
        relatedObject: Void?,
        renderState: LivingEntityRenderState,
        partialTick: Float
    ) {
        super.addRenderData(animatable, relatedObject, renderState, partialTick)
        renderState.addGeckolibData(
            HADataTickets.PATTERN_NAME,
            if (animatable.isBaby) "" else animatable.getPatternTextureName()
        )
    }

    override fun layerTexture(renderState: LivingEntityRenderState): Identifier? {
        val patternName = renderState.getGeckolibData(HADataTickets.PATTERN_NAME) ?: return null

        if (patternName.isEmpty()) return null

        return (getGeoModel() as CarpEntityModel).getPatternTextureResource(patternName)
    }
}
