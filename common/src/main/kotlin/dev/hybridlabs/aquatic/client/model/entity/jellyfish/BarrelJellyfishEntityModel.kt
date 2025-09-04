package dev.hybridlabs.aquatic.client.model.entity.jellyfish

import dev.hybridlabs.aquatic.entity.jellyfish.BarrelJellyfishEntity
import net.minecraft.client.render.RenderLayer
import net.minecraft.util.Identifier

class BarrelJellyfishEntityModel : HybridAquaticJellyfishEntityModel<BarrelJellyfishEntity>("barrel_jellyfish") {
    override fun getRenderType(animatable: BarrelJellyfishEntity, texture: Identifier): RenderLayer {
        return RenderLayer.getEntityTranslucent(texture)
    }
}
