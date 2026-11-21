package dev.hybridlabs.aquatic.client.render.entity.crustacean

import dev.hybridlabs.aquatic.client.model.entity.crustacean.ShrimpEntityModel
import dev.hybridlabs.aquatic.client.render.entity.crustacean.layer.HACrustaceanEntityLayer
import dev.hybridlabs.aquatic.entity.crustacean.ShrimpEntity
import dev.hybridlabs.hapi.client.render.entity.aquatic.BaseCrustaceanEntityRenderer
import net.minecraft.client.renderer.entity.EntityRendererProvider
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState

class ShrimpEntityRenderer(context: Context) :
    BaseCrustaceanEntityRenderer<ShrimpEntity, LivingEntityRenderState>(context, ShrimpEntityModel(), true, false) {

    init {
        withRenderLayer(HACrustaceanEntityLayer(this))
    }
}