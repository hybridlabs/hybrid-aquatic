package dev.hybridlabs.aquatic.client.model.entity.jellyfish

import dev.hybridlabs.aquatic.entity.jellyfish.HybridAquaticJellyfishEntity
import net.minecraft.client.renderer.RenderType
import net.minecraft.resources.ResourceLocation

class CosmicJellyfishEntityModel : HybridAquaticJellyfishEntityModel<HybridAquaticJellyfishEntity>("cosmic_jellyfish") {
    override fun getRenderType(animatable: HybridAquaticJellyfishEntity, texture: ResourceLocation): RenderType {
        return RenderType.entityTranslucent(texture)
    }
}
