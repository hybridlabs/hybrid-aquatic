package dev.hybridlabs.aquatic.client.model.entity

import dev.hybridlabs.aquatic.entity.jellyfish.HybridAquaticJellyfishEntity
import net.minecraft.client.render.RenderLayer
import net.minecraft.util.Identifier

class BlueJellyfishEntityModel : HybridAquaticJellyfishEntityModel<HybridAquaticJellyfishEntity>("blue_jellyfish") {
    override fun getRenderType(animatable: HybridAquaticJellyfishEntity?, texture: Identifier?): RenderLayer {
        return RenderLayer.getEntityTranslucent(texture)
    }
}
