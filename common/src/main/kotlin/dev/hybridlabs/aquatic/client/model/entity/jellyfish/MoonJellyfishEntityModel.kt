package dev.hybridlabs.aquatic.client.model.entity.jellyfish

import dev.hybridlabs.aquatic.entity.jellyfish.MoonJellyfishEntity
import net.minecraft.client.render.RenderLayer
import net.minecraft.util.Identifier

class MoonJellyfishEntityModel : HybridAquaticJellyfishEntityModel<MoonJellyfishEntity>("moon_jellyfish") {
    override fun getRenderType(animatable: MoonJellyfishEntity, texture: Identifier): RenderLayer {
        return RenderLayer.getEntityTranslucent(texture)
    }
}
