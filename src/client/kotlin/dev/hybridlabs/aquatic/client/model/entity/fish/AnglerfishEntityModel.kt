package dev.hybridlabs.aquatic.client.model.entity.fish

import dev.hybridlabs.aquatic.entity.fish.AnglerfishEntity
import net.minecraft.client.render.RenderLayer
import net.minecraft.util.Identifier

class AnglerfishEntityModel : HybridAquaticFishEntityModel<AnglerfishEntity>("anglerfish") {
    override fun getRenderType(animatable: AnglerfishEntity, texture: Identifier): RenderLayer {
        return RenderLayer.getEntityTranslucent(texture)
    }
}