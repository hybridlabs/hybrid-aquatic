package dev.hybridlabs.aquatic.client.render.entity.critter.layer

import dev.hybridlabs.hapi.client.model.getVariant

import dev.hybridlabs.aquatic.client.model.HADataTickets
import dev.hybridlabs.aquatic.client.model.entity.critter.StarfishEntityModel
import dev.hybridlabs.aquatic.client.render.entity.critter.StarfishEntityRenderer
import dev.hybridlabs.aquatic.client.render.entity.layer.HATextureLayer
import dev.hybridlabs.aquatic.entity.critter.StarfishEntity
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState
import net.minecraft.resources.Identifier

class StarfishColorLayer(
    renderer: StarfishEntityRenderer,
) : HATextureLayer<StarfishEntity>(renderer, tinted = true) {

    override fun addRenderData(
        animatable: StarfishEntity,
        relatedObject: Void?,
        renderState: LivingEntityRenderState,
        partialTick: Float
    ) {
        super.addRenderData(animatable, relatedObject, renderState, partialTick)
        renderState.addGeckolibData(HADataTickets.STARFISH_COLOR, animatable.starfishColor)
    }

    override fun layerTexture(renderState: LivingEntityRenderState): Identifier? {
        val variant = renderState.getVariant(StarfishEntity.Companion.Type.entries)

        if (variant != StarfishEntity.Companion.Type.SMALL && variant != StarfishEntity.Companion.Type.MEDIUM) return null

        return (getGeoModel() as StarfishEntityModel).getTextureResource(renderState)
    }

    override fun layerTint(renderState: LivingEntityRenderState): Int =
        renderState.getGeckolibData(HADataTickets.STARFISH_COLOR) ?: WHITE
}
