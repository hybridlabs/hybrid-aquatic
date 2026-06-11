package dev.hybridlabs.aquatic.client.model.entity.jellyfish

import dev.hybridlabs.aquatic.entity.jellyfish.FireworkJellyfishEntity
import net.minecraft.client.renderer.RenderType
import net.minecraft.resources.ResourceLocation

class FireworkJellyfishEntityModel : HAJellyfishEntityModel<FireworkJellyfishEntity>("firework_jellyfish") {
    override fun getRenderType(animatable: FireworkJellyfishEntity, texture: ResourceLocation): RenderType {
        return RenderType.entityTranslucent(texture)
    }
}
