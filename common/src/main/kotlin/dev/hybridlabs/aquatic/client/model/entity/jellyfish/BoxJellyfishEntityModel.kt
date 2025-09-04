package dev.hybridlabs.aquatic.client.model.entity.jellyfish

import dev.hybridlabs.aquatic.entity.jellyfish.BoxJellyfishEntity
import net.minecraft.client.render.RenderLayer
import net.minecraft.util.Identifier

class BoxJellyfishEntityModel : HybridAquaticJellyfishEntityModel<BoxJellyfishEntity>("box_jellyfish") {
    override fun getRenderType(animatable: BoxJellyfishEntity, texture: Identifier): RenderLayer {
        return RenderLayer.getEntityTranslucent(texture)
    }
}
