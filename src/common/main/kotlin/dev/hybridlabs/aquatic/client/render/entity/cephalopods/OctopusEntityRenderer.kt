package dev.hybridlabs.aquatic.client.render.entity.cephalopods

import dev.hybridlabs.aquatic.client.model.entity.cephalopod.OctopusEntityModel
import dev.hybridlabs.aquatic.client.render.entity.cephalopods.layer.HybridAquaticOctopusEntityLayer
import dev.hybridlabs.aquatic.entity.cephalopod.OctopusEntity
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class OctopusEntityRenderer(context: Context) :
    HybridAquaticOctopusEntityRenderer<OctopusEntity>(
        context,
        OctopusEntityModel(),
        true,
        true
    ) {

    init {
        addRenderLayer(HybridAquaticOctopusEntityLayer(this))
    }
}