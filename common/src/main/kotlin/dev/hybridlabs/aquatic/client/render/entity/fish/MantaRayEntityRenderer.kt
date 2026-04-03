package dev.hybridlabs.aquatic.client.render.entity.fish

import dev.hybridlabs.aquatic.client.model.entity.fish.MantaRayEntityModel
import dev.hybridlabs.aquatic.client.render.entity.fish.layer.HAFishEntityLayer
import dev.hybridlabs.aquatic.entity.fish.MantaRayEntity
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class MantaRayEntityRenderer(context: Context) :
    HAFishEntityRenderer<MantaRayEntity>(context, MantaRayEntityModel(), true, false) {

    init {
        addRenderLayer(HAFishEntityLayer(this))
    }
}