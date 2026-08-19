package dev.hybridlabs.aquatic.client.render.entity.mammal

import dev.hybridlabs.aquatic.client.model.entity.mammal.OtterEntityModel
import dev.hybridlabs.aquatic.entity.mammal.OtterEntity
import dev.hybridlabs.hapi.client.render.entity.aquatic.BaseMammalEntityRenderer
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class OtterEntityRenderer(context: Context) :
    BaseMammalEntityRenderer<OtterEntity>(context, OtterEntityModel()) {

    init {
        this.shadowRadius = 0.5f
    }
}