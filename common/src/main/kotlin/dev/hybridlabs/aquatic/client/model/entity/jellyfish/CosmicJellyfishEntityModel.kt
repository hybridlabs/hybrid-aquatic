package dev.hybridlabs.aquatic.client.model.entity.jellyfish

import dev.hybridlabs.aquatic.entity.jellyfish.CosmicJellyfishEntity
import dev.hybridlabs.hapi.client.model.entity.aquatic.BaseJellyfishEntityModel
import net.minecraft.client.renderer.RenderType
import net.minecraft.resources.ResourceLocation

class CosmicJellyfishEntityModel : BaseJellyfishEntityModel<CosmicJellyfishEntity>("hybrid_aquatic", "cosmic_jellyfish") {
    override fun getRenderType(animatable: CosmicJellyfishEntity, texture: ResourceLocation): RenderType {
        return RenderType.entityTranslucent(texture)
    }
}
