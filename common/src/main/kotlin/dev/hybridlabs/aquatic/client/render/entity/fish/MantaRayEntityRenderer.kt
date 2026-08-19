package dev.hybridlabs.aquatic.client.render.entity.fish

import dev.hybridlabs.aquatic.client.model.entity.fish.MantaRayEntityModel
import dev.hybridlabs.aquatic.client.render.entity.fish.layer.HAFishEntityLayer
import dev.hybridlabs.aquatic.entity.fish.MantaRayEntity
import dev.hybridlabs.hapi.client.render.entity.aquatic.BaseFishEntityRenderer
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class MantaRayEntityRenderer(context: Context) :
    BaseFishEntityRenderer<MantaRayEntity>(context, MantaRayEntityModel(), true, false) {

    init {
        addRenderLayer(HAFishEntityLayer(this))
    }
}