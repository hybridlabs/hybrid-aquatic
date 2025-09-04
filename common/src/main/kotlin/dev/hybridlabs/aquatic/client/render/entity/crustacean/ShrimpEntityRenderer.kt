package dev.hybridlabs.aquatic.client.render.entity.crustacean

import dev.hybridlabs.aquatic.client.model.entity.crustacean.ShrimpEntityModel
import dev.hybridlabs.aquatic.client.render.entity.crustacean.layer.HybridAquaticCrustaceanEntityLayer
import dev.hybridlabs.aquatic.entity.crustacean.ShrimpEntity
import net.minecraft.client.render.entity.EntityRendererFactory.Context

class ShrimpEntityRenderer(context: Context) :
    HybridAquaticCrustaceanEntityRenderer<ShrimpEntity>(context, ShrimpEntityModel(), true, false) {

    init {
        addRenderLayer(HybridAquaticCrustaceanEntityLayer(this))
    }
}