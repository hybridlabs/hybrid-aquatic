package dev.hybridlabs.aquatic.client.render.entity.cephalopods

import dev.hybridlabs.aquatic.client.model.entity.cephalopod.OctopusEntityModel
import dev.hybridlabs.aquatic.client.render.entity.cephalopods.layer.HAOctopusEntityLayer
import dev.hybridlabs.aquatic.entity.cephalopod.OctopusEntity
import dev.hybridlabs.hapi.client.render.entity.aquatic.BaseOctopusEntityRenderer
import net.minecraft.client.renderer.entity.EntityRendererProvider
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState

class OctopusEntityRenderer(context: Context) :
    BaseOctopusEntityRenderer<OctopusEntity, LivingEntityRenderState>(
        context,
        OctopusEntityModel(),
        true
    ) {

    init {
        withRenderLayer(HAOctopusEntityLayer(this))
    }
}