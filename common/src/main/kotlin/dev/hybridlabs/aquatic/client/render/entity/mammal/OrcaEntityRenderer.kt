package dev.hybridlabs.aquatic.client.render.entity.mammal

import dev.hybridlabs.aquatic.client.model.entity.mammal.OrcaEntityModel
import dev.hybridlabs.aquatic.client.render.entity.mammal.layer.OrcaEyeSpotEntityLayer
import dev.hybridlabs.aquatic.client.render.entity.mammal.layer.OrcaSaddleEntityLayer
import dev.hybridlabs.aquatic.entity.mammal.OrcaEntity
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class OrcaEntityRenderer(context: Context) :
    HADolphinEntityRenderer<OrcaEntity>(context, OrcaEntityModel(), true) {

    init {
        addRenderLayer(OrcaEyeSpotEntityLayer(this))
        addRenderLayer(OrcaSaddleEntityLayer(this))
    }
}