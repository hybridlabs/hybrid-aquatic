package dev.hybridlabs.aquatic.client.render.entity.shark

import dev.hybridlabs.aquatic.client.model.entity.shark.WhaleSharkEntityModel
import dev.hybridlabs.aquatic.client.render.entity.shark.layer.HASharkEntityLayer
import dev.hybridlabs.aquatic.entity.shark.WhaleSharkEntity
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class WhaleSharkEntityRenderer(context: Context) :
    HASharkEntityRenderer<WhaleSharkEntity>(context, WhaleSharkEntityModel(), true) {

    init {
        addRenderLayer(HASharkEntityLayer(this))
    }
}