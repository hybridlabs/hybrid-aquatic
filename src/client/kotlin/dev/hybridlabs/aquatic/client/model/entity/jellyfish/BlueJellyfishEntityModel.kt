package dev.hybridlabs.aquatic.client.model.entity.jellyfish

import dev.hybridlabs.aquatic.entity.jellyfish.BlueJellyfishEntity
import net.minecraft.client.render.RenderLayer
import net.minecraft.util.Identifier

class BlueJellyfishEntityModel : HybridAquaticJellyfishEntityModel<BlueJellyfishEntity>("blue_jellyfish") {
    override fun getRenderType(animatable: BlueJellyfishEntity, texture: Identifier): RenderLayer {
        return RenderLayer.getEntityTranslucent(texture)
    }
}
