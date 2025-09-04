package dev.hybridlabs.aquatic.client.model.entity.jellyfish

import dev.hybridlabs.aquatic.entity.jellyfish.BigRedJellyfishEntity
import net.minecraft.client.render.RenderLayer
import net.minecraft.util.Identifier

class BigRedJellyfishEntityModel : HybridAquaticJellyfishEntityModel<BigRedJellyfishEntity>("big_red_jellyfish") {
    override fun getRenderType(animatable: BigRedJellyfishEntity, texture: Identifier): RenderLayer {
        return RenderLayer.getEntityTranslucent(texture)
    }
}