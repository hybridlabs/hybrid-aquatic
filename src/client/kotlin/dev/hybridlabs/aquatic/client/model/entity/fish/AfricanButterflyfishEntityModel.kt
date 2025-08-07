package dev.hybridlabs.aquatic.client.model.entity.fish

import dev.hybridlabs.aquatic.entity.fish.AfricanButterflyfishEntity
import net.minecraft.client.render.RenderLayer
import net.minecraft.util.Identifier

class AfricanButterflyfishEntityModel : HybridAquaticFishEntityModel<AfricanButterflyfishEntity>("african_butterflyfish") {
    override fun getRenderType(animatable: AfricanButterflyfishEntity, texture: Identifier): RenderLayer {
        return RenderLayer.getEntityTranslucent(texture)
    }
}