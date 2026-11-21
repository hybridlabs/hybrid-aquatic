package dev.hybridlabs.aquatic.client.render.entity.fish

import dev.hybridlabs.aquatic.client.model.entity.fish.MantaRayEntityModel
import dev.hybridlabs.aquatic.client.render.entity.fish.layer.HAFishEntityLayer
import dev.hybridlabs.aquatic.entity.fish.MantaRayEntity
import dev.hybridlabs.hapi.client.render.entity.aquatic.BaseFishEntityRenderer
import net.minecraft.client.renderer.entity.EntityRendererProvider
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState

class MantaRayEntityRenderer(context: Context) :
    BaseFishEntityRenderer<MantaRayEntity, LivingEntityRenderState>(context, MantaRayEntityModel(), true, false) {

    init {
        withRenderLayer(HAFishEntityLayer(this))
    }
}