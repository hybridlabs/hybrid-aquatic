package dev.hybridlabs.aquatic.client.render.entity.critter

import dev.hybridlabs.aquatic.client.model.entity.critter.StarfishEntityModel
import dev.hybridlabs.aquatic.client.render.entity.critter.layer.StarfishColorLayer
import dev.hybridlabs.aquatic.client.render.entity.critter.layer.StarfishOverlayLayer
import dev.hybridlabs.aquatic.entity.critter.StarfishEntity
import dev.hybridlabs.hapi.client.render.entity.aquatic.BaseCritterEntityRenderer
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class StarfishEntityRenderer(context: Context) :
    BaseCritterEntityRenderer<StarfishEntity>(context, StarfishEntityModel(), true) {
    init {
        addRenderLayer(StarfishColorLayer(this))
        addRenderLayer(StarfishOverlayLayer(this))
    }
}