package dev.hybridlabs.aquatic.client.model.entity.jellyfish

import dev.hybridlabs.aquatic.entity.jellyfish.FireworkJellyfishEntity
import net.minecraft.client.render.RenderLayer
import net.minecraft.util.Identifier

class FireworkJellyfishEntityModel : HybridAquaticJellyfishEntityModel<FireworkJellyfishEntity>("firework_jellyfish") {
    override fun getRenderType(animatable: FireworkJellyfishEntity, texture: Identifier): RenderLayer {
        return RenderLayer.getEntityTranslucent(texture)
    }
}
