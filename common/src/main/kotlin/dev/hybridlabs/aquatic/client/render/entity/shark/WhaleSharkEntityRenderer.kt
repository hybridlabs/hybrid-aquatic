package dev.hybridlabs.aquatic.client.render.entity.shark

import dev.hybridlabs.aquatic.client.model.entity.shark.WhaleSharkEntityModel
import dev.hybridlabs.aquatic.client.render.entity.shark.layer.HASharkEntityLayer
import dev.hybridlabs.aquatic.entity.shark.WhaleSharkEntity
import dev.hybridlabs.hapi.client.render.entity.aquatic.BaseSharkEntityRenderer
import net.minecraft.client.renderer.entity.EntityRendererProvider
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState

class WhaleSharkEntityRenderer(context: Context) :
    BaseSharkEntityRenderer<WhaleSharkEntity, LivingEntityRenderState>(context, WhaleSharkEntityModel(), true) {

    init {
        withRenderLayer(HASharkEntityLayer(this))
    }
}