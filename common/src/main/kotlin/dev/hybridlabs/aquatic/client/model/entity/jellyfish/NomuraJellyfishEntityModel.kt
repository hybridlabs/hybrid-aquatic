package dev.hybridlabs.aquatic.client.model.entity.jellyfish

import dev.hybridlabs.aquatic.entity.jellyfish.NomuraJellyfishEntity
import dev.hybridlabs.hapi.client.model.entity.aquatic.BaseJellyfishEntityModel
import net.minecraft.client.renderer.RenderType
import net.minecraft.resources.ResourceLocation

class NomuraJellyfishEntityModel : BaseJellyfishEntityModel<NomuraJellyfishEntity>("hybrid_aquatic", "nomura_jellyfish") {
    override fun getRenderType(animatable: NomuraJellyfishEntity, texture: ResourceLocation): RenderType {
        return RenderType.entityTranslucent(texture)
    }
}
