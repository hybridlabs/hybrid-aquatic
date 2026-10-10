package dev.hybridlabs.aquatic.client.render.entity.mammal

import dev.hybridlabs.aquatic.client.model.entity.mammal.BeaverEntityModel
import dev.hybridlabs.aquatic.entity.mammal.BeaverEntity
import dev.hybridlabs.hapi.client.render.entity.aquatic.BaseMammalEntityRenderer
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class BeaverEntityRenderer(context: Context) : BaseMammalEntityRenderer<BeaverEntity>(context, BeaverEntityModel()) {

    init {
        this.shadowRadius = 0.5f
    }
}
