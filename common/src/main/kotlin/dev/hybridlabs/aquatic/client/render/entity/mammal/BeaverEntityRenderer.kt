package dev.hybridlabs.aquatic.client.render.entity.mammal

import dev.hybridlabs.aquatic.client.model.entity.mammal.BeaverEntityModel
import dev.hybridlabs.aquatic.entity.mammal.BeaverEntity
import dev.hybridlabs.hapi.client.render.entity.aquatic.BaseMammalEntityRenderer
import net.minecraft.client.renderer.entity.EntityRendererProvider
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState

class BeaverEntityRenderer(context: Context) : BaseMammalEntityRenderer<BeaverEntity, LivingEntityRenderState>(context, BeaverEntityModel()) {

    init {
        this.shadowRadius = 0.5f
    }
}
