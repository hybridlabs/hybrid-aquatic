package dev.hybridlabs.aquatic.client.model.entity.fish

import dev.hybridlabs.aquatic.entity.fish.AfricanButterflyEntity
import net.minecraft.client.render.RenderLayer
import net.minecraft.util.Identifier

class AfricanButterflyEntityModel : HybridAquaticFishEntityModel<AfricanButterflyEntity>("african_butterfly") {
    override fun getRenderType(animatable: AfricanButterflyEntity, texture: Identifier): RenderLayer {
        return RenderLayer.getEntityTranslucent(texture)
    }
}