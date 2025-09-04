package dev.hybridlabs.aquatic.client.model.entity.jellyfish

import dev.hybridlabs.aquatic.entity.jellyfish.CosmicJellyfishEntity
import net.minecraft.client.render.RenderLayer
import net.minecraft.util.Identifier

class CosmicJellyfishEntityModel : HybridAquaticJellyfishEntityModel<CosmicJellyfishEntity>("cosmic_jellyfish") {
    override fun getRenderType(animatable: CosmicJellyfishEntity, texture: Identifier): RenderLayer {
        return RenderLayer.getEntityTranslucent(texture)
    }
}
