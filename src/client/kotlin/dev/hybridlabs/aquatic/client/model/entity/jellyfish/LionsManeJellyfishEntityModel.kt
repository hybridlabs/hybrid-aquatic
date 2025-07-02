package dev.hybridlabs.aquatic.client.model.entity.jellyfish

import dev.hybridlabs.aquatic.entity.jellyfish.LionsManeJellyfishEntity
import net.minecraft.client.render.RenderLayer
import net.minecraft.util.Identifier

class LionsManeJellyfishEntityModel : HybridAquaticJellyfishEntityModel<LionsManeJellyfishEntity>("lions_mane_jellyfish") {
    override fun getRenderType(animatable: LionsManeJellyfishEntity, texture: Identifier): RenderLayer {
        return RenderLayer.getEntityTranslucent(texture)
    }
}
