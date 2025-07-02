package dev.hybridlabs.aquatic.client.model.entity.jellyfish

import dev.hybridlabs.aquatic.entity.jellyfish.AtollaJellyfishEntity
import net.minecraft.client.render.RenderLayer
import net.minecraft.util.Identifier

class AtollaJellyfishEntityModel : HybridAquaticJellyfishEntityModel<AtollaJellyfishEntity>("atolla_jellyfish") {
    override fun getRenderType(animatable: AtollaJellyfishEntity, texture: Identifier): RenderLayer {
        return RenderLayer.getEntityTranslucent(texture)
    }
}
