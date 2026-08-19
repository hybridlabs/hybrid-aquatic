package dev.hybridlabs.aquatic.client.render.entity.cephalopods

import dev.hybridlabs.aquatic.client.model.entity.cephalopod.OctopusEntityModel
import dev.hybridlabs.aquatic.client.render.entity.cephalopods.layer.HAOctopusEntityLayer
import dev.hybridlabs.aquatic.entity.cephalopod.OctopusEntity
import dev.hybridlabs.hapi.client.render.entity.aquatic.BaseOctopusEntityRenderer
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class OctopusEntityRenderer(context: Context) :
    BaseOctopusEntityRenderer<OctopusEntity>(
        context,
        OctopusEntityModel(),
        true,
        true
    ) {

    init {
        addRenderLayer(HAOctopusEntityLayer(this))
    }
}