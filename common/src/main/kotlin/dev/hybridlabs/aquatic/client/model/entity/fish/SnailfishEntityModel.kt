package dev.hybridlabs.aquatic.client.model.entity.fish

import dev.hybridlabs.aquatic.entity.fish.SnailfishEntity
import net.minecraft.client.render.RenderLayer
import net.minecraft.util.Identifier

class SnailfishEntityModel : HybridAquaticFishEntityModel<SnailfishEntity>("snailfish") {
    override fun getRenderType(animatable: SnailfishEntity, texture: Identifier): RenderLayer {
        return RenderLayer.getEntityTranslucent(texture)
    }
}
