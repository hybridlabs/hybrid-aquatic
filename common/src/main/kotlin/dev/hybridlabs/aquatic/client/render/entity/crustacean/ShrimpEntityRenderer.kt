package dev.hybridlabs.aquatic.client.render.entity.crustacean

import dev.hybridlabs.aquatic.client.model.entity.crustacean.ShrimpEntityModel
import dev.hybridlabs.aquatic.client.render.entity.crustacean.layer.HACrustaceanEntityLayer
import dev.hybridlabs.aquatic.entity.crustacean.ShrimpEntity
import dev.hybridlabs.hapi.client.render.entity.aquatic.BaseCrustaceanEntityRenderer
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class ShrimpEntityRenderer(context: Context) :
    BaseCrustaceanEntityRenderer<ShrimpEntity>(context, ShrimpEntityModel(), true, false) {

    init {
        addRenderLayer(HACrustaceanEntityLayer(this))
    }
}