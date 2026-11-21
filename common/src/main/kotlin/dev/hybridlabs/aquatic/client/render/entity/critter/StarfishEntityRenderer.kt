package dev.hybridlabs.aquatic.client.render.entity.critter

import dev.hybridlabs.aquatic.client.model.entity.critter.StarfishEntityModel
import dev.hybridlabs.aquatic.client.render.entity.critter.layer.StarfishColorLayer
import dev.hybridlabs.aquatic.client.render.entity.critter.layer.StarfishOverlayLayer
import dev.hybridlabs.aquatic.entity.critter.StarfishEntity
import dev.hybridlabs.hapi.client.render.entity.aquatic.BaseCritterEntityRenderer
import net.minecraft.client.renderer.entity.EntityRendererProvider
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState

class StarfishEntityRenderer(context: Context) :
    BaseCritterEntityRenderer<StarfishEntity, LivingEntityRenderState>(context, StarfishEntityModel(), true) {
    init {
        withRenderLayer(StarfishColorLayer(this))
        withRenderLayer(StarfishOverlayLayer(this))
    }
}