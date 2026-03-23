package dev.hybridlabs.aquatic.client.render.entity.mammal

import dev.hybridlabs.aquatic.client.model.entity.mammal.OrcaEntityModel
import dev.hybridlabs.aquatic.client.render.entity.mammal.layer.HybridAquaticDolphinEntityLayer
import dev.hybridlabs.aquatic.client.render.entity.mammal.layer.OrcaSaddleEntityLayer
import dev.hybridlabs.aquatic.entity.mammal.OrcaEntity
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class OrcaEntityRenderer(context: Context) :
    HybridAquaticDolphinEntityRenderer<OrcaEntity>(context, OrcaEntityModel(), true) {

    init {
        addRenderLayer(HybridAquaticDolphinEntityLayer(this))
        addRenderLayer(OrcaSaddleEntityLayer(this))
    }
}
