package dev.hybridlabs.aquatic.client.model.entity.jellyfish

import dev.hybridlabs.aquatic.entity.jellyfish.CompassJellyfishEntity
import net.minecraft.client.render.RenderLayer
import net.minecraft.util.Identifier

class CompassJellyfishEntityModel : HybridAquaticJellyfishEntityModel<CompassJellyfishEntity>("compass_jellyfish") {
    override fun getRenderType(animatable: CompassJellyfishEntity, texture: Identifier): RenderLayer {
        return RenderLayer.getEntityTranslucent(texture)
    }
}
