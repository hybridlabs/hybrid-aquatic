package dev.hybridlabs.aquatic.client.model.entity.jellyfish

import dev.hybridlabs.aquatic.entity.jellyfish.LionsManeJellyfishEntity
import dev.hybridlabs.hapi.client.model.entity.aquatic.BaseJellyfishEntityModel
import net.minecraft.client.renderer.RenderType
import net.minecraft.resources.ResourceLocation

class LionsManeJellyfishEntityModel : BaseJellyfishEntityModel<LionsManeJellyfishEntity>("hybrid_aquatic", "lions_mane_jellyfish") {
    override fun getRenderType(animatable: LionsManeJellyfishEntity, texture: ResourceLocation): RenderType {
        return RenderType.entityTranslucent(texture)
    }
}
