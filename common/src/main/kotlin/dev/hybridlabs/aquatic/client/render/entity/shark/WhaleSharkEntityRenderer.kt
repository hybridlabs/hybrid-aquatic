package dev.hybridlabs.aquatic.client.render.entity.shark

import dev.hybridlabs.aquatic.client.model.entity.shark.WhaleSharkEntityModel
import dev.hybridlabs.aquatic.client.render.entity.shark.layer.HybridAquaticSharkEntityLayer
import dev.hybridlabs.aquatic.entity.shark.WhaleSharkEntity
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class WhaleSharkEntityRenderer(context: Context) :
    HybridAquaticSharkEntityRenderer<WhaleSharkEntity>(context, WhaleSharkEntityModel(), true) {

    init {
        addRenderLayer(HybridAquaticSharkEntityLayer(this))
    }
}