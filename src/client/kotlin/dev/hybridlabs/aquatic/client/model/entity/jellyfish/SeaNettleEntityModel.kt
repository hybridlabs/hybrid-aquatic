package dev.hybridlabs.aquatic.client.model.entity.jellyfish

import dev.hybridlabs.aquatic.entity.jellyfish.SeaNettleEntity
import net.minecraft.client.render.RenderLayer
import net.minecraft.util.Identifier

class SeaNettleEntityModel : HybridAquaticJellyfishEntityModel<SeaNettleEntity>("sea_nettle") {
    override fun getRenderType(animatable: SeaNettleEntity, texture: Identifier): RenderLayer {
        return RenderLayer.getEntityTranslucent(texture)
    }
}
