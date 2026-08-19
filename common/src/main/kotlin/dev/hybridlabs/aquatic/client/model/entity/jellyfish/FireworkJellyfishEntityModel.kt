package dev.hybridlabs.aquatic.client.model.entity.jellyfish

import dev.hybridlabs.aquatic.entity.jellyfish.FireworkJellyfishEntity
import dev.hybridlabs.hapi.client.model.entity.aquatic.BaseJellyfishEntityModel
import net.minecraft.client.renderer.RenderType
import net.minecraft.resources.ResourceLocation

class FireworkJellyfishEntityModel : BaseJellyfishEntityModel<FireworkJellyfishEntity>("hybrid_aquatic", "firework_jellyfish") {
    override fun getRenderType(animatable: FireworkJellyfishEntity, texture: ResourceLocation): RenderType {
        return RenderType.entityTranslucent(texture)
    }
}
