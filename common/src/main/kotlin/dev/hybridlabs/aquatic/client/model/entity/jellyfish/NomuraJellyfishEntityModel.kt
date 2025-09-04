package dev.hybridlabs.aquatic.client.model.entity.jellyfish

import dev.hybridlabs.aquatic.entity.jellyfish.NomuraJellyfishEntity
import net.minecraft.client.renderer.RenderType
import net.minecraft.resources.ResourceLocation

class NomuraJellyfishEntityModel : HybridAquaticJellyfishEntityModel<NomuraJellyfishEntity>("nomura_jellyfish") {
    override fun getRenderType(animatable: NomuraJellyfishEntity, texture: ResourceLocation): RenderType {
        return RenderType.entityTranslucent(texture)
    }
}
