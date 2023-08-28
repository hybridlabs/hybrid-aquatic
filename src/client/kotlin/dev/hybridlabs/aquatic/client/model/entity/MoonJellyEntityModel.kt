package dev.hybridlabs.aquatic.client.model.entity

import dev.hybridlabs.aquatic.entity.jellyfish.HybridAquaticJellyfishEntity
import net.minecraft.client.render.RenderLayer
import net.minecraft.util.Identifier

class MoonJellyEntityModel : HybridAquaticJellyfishEntityModel<HybridAquaticJellyfishEntity>("moon_jelly") {
    override fun getRenderType(animatable: HybridAquaticJellyfishEntity?, texture: Identifier?): RenderLayer {
        return RenderLayer.getEntityTranslucent(texture)
    }
}
