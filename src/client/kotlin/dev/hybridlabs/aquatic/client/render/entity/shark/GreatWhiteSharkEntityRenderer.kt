package dev.hybridlabs.aquatic.client.render.entity.shark

import dev.hybridlabs.aquatic.client.model.entity.shark.GreatWhiteSharkEntityModel
import dev.hybridlabs.aquatic.client.render.entity.shark.layer.HybridAquaticSharkBodyEntityLayer
import dev.hybridlabs.aquatic.client.render.entity.shark.layer.HybridAquaticSharkFaceEntityLayer
import dev.hybridlabs.aquatic.entity.shark.GreatWhiteSharkEntity
import net.minecraft.client.render.entity.EntityRendererFactory.Context

class GreatWhiteSharkEntityRenderer(context: Context) :
    HybridAquaticSharkEntityRenderer<GreatWhiteSharkEntity>(context, GreatWhiteSharkEntityModel(), true, false) {

    init {
        addRenderLayer(HybridAquaticSharkFaceEntityLayer(this))
        addRenderLayer(HybridAquaticSharkBodyEntityLayer(this))
    }
}