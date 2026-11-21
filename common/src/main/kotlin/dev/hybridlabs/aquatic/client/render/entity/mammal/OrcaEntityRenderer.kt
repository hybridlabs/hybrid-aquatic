package dev.hybridlabs.aquatic.client.render.entity.mammal

import dev.hybridlabs.aquatic.client.model.entity.mammal.OrcaEntityModel
import dev.hybridlabs.aquatic.client.render.entity.mammal.layer.OrcaEyeSpotEntityLayer
import dev.hybridlabs.aquatic.client.render.entity.mammal.layer.OrcaSaddleEntityLayer
import dev.hybridlabs.aquatic.entity.mammal.OrcaEntity
import dev.hybridlabs.hapi.client.render.entity.aquatic.BaseDolphinEntityRenderer
import net.minecraft.client.renderer.entity.EntityRendererProvider
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState

class OrcaEntityRenderer(context: Context) :
    BaseDolphinEntityRenderer<OrcaEntity, LivingEntityRenderState>(context, OrcaEntityModel(), true) {

    init {
        withRenderLayer(OrcaEyeSpotEntityLayer(this))
        withRenderLayer(OrcaSaddleEntityLayer(this))
    }
}