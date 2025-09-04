package dev.hybridlabs.aquatic.client.renderer.entity.critter

import dev.hybridlabs.aquatic.client.model.entity.critter.StarfishEntityModel
import dev.hybridlabs.aquatic.client.renderer.entity.critter.layer.HybridAquaticCritterEntityLayer
import dev.hybridlabs.aquatic.entity.critter.StarfishEntity
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class StarfishEntityRenderer(context: Context) :
    HybridAquaticCritterEntityRenderer<StarfishEntity>(context, StarfishEntityModel(), true) {

    init {
        addRenderType(HybridAquaticCritterEntityLayer(this))
    }
}