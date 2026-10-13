package dev.hybridlabs.aquatic.client.render.entity.mammal

import dev.hybridlabs.aquatic.client.model.entity.mammal.HippoEntityModel
import dev.hybridlabs.aquatic.entity.mammal.HippoEntity
import dev.hybridlabs.hapi.client.render.entity.aquatic.BaseMammalEntityRenderer
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class HippoEntityRenderer(context: Context) : BaseMammalEntityRenderer<HippoEntity>(context, HippoEntityModel()) {

    init {
        this.shadowRadius = 0.5f
    }
}
