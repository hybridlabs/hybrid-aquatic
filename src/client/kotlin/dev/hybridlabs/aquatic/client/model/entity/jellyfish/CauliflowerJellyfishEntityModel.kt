package dev.hybridlabs.aquatic.client.model.entity.jellyfish

import dev.hybridlabs.aquatic.entity.jellyfish.CauliflowerJellyfishEntity
import net.minecraft.client.render.RenderLayer
import net.minecraft.util.Identifier

class CauliflowerJellyfishEntityModel : HybridAquaticJellyfishEntityModel<CauliflowerJellyfishEntity>("cauliflower_jellyfish") {
    override fun getRenderType(animatable: CauliflowerJellyfishEntity, texture: Identifier): RenderLayer {
        return RenderLayer.getEntityTranslucent(texture)
    }
}
