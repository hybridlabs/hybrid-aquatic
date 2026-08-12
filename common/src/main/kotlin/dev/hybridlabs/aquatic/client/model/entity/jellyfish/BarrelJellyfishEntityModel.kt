package dev.hybridlabs.aquatic.client.model.entity.jellyfish

import dev.hybridlabs.aquatic.entity.jellyfish.BarrelJellyfishEntity
import dev.hybridlabs.hapi.client.model.entity.BaseJellyfishEntityModel
import net.minecraft.client.renderer.RenderType
import net.minecraft.resources.ResourceLocation

class BarrelJellyfishEntityModel : BaseJellyfishEntityModel<BarrelJellyfishEntity>("hybrid_aquatic", "barrel_jellyfish") {
    override fun getRenderType(animatable: BarrelJellyfishEntity, texture: ResourceLocation): RenderType {
        return RenderType.entityTranslucent(texture)
    }
}
