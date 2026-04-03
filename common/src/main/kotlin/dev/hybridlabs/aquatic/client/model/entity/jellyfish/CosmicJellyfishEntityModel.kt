package dev.hybridlabs.aquatic.client.model.entity.jellyfish

import dev.hybridlabs.aquatic.entity.jellyfish.CosmicJellyfishEntity
import net.minecraft.client.renderer.RenderType
import net.minecraft.resources.ResourceLocation

class CosmicJellyfishEntityModel : HAJellyfishEntityModel<CosmicJellyfishEntity>("cosmic_jellyfish") {
    override fun getRenderType(animatable: CosmicJellyfishEntity, texture: ResourceLocation): RenderType {
        return RenderType.entityTranslucent(texture)
    }
}
