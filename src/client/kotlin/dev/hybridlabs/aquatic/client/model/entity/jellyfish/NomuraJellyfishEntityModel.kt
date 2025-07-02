package dev.hybridlabs.aquatic.client.model.entity.jellyfish

import dev.hybridlabs.aquatic.entity.jellyfish.NomuraJellyfishEntity
import net.minecraft.client.render.RenderLayer
import net.minecraft.util.Identifier

class NomuraJellyfishEntityModel : HybridAquaticJellyfishEntityModel<NomuraJellyfishEntity>("nomura_jellyfish") {
    override fun getRenderType(animatable: NomuraJellyfishEntity, texture: Identifier): RenderLayer {
        return RenderLayer.getEntityTranslucent(texture)
    }
}
