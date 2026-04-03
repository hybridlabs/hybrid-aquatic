package dev.hybridlabs.aquatic.client.model.entity.jellyfish

import dev.hybridlabs.aquatic.entity.jellyfish.BigRedJellyfishEntity
import net.minecraft.client.renderer.RenderType
import net.minecraft.resources.ResourceLocation

class BigRedJellyfishEntityModel : HAJellyfishEntityModel<BigRedJellyfishEntity>("big_red_jellyfish") {
    override fun getRenderType(animatable: BigRedJellyfishEntity, texture: ResourceLocation): RenderType {
        return RenderType.entityTranslucent(texture)
    }
}