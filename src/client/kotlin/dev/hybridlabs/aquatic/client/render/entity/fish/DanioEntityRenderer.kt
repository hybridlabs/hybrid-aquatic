package dev.hybridlabs.aquatic.client.render.entity.fish

import dev.hybridlabs.aquatic.client.model.entity.fish.DanioEntityModel
import dev.hybridlabs.aquatic.client.render.entity.fish.layer.HybridAquaticFishEntityLayer
import dev.hybridlabs.aquatic.entity.fish.DanioEntity
import net.minecraft.client.render.entity.EntityRendererFactory.Context

class DanioEntityRenderer(context: Context)
    : HybridAquaticFishEntityRenderer<DanioEntity>(context, DanioEntityModel(), false, false) {

    init {
        addRenderLayer(HybridAquaticFishEntityLayer(this))
    }
}