package dev.hybridlabs.aquatic.client.model.entity.fish

import dev.hybridlabs.aquatic.entity.fish.HybridAquaticFishEntity
import net.minecraft.client.render.RenderLayer
import net.minecraft.util.Identifier

class SnailfishEntityModel : HybridAquaticFishEntityModel<HybridAquaticFishEntity>("snailfish") {
    override fun getRenderType(animatable: HybridAquaticFishEntity, texture: Identifier): RenderLayer {
        return RenderLayer.getEntityTranslucent(texture)
    }
}
