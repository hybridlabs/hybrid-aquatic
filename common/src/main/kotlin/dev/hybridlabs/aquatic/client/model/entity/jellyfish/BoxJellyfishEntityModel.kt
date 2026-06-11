package dev.hybridlabs.aquatic.client.model.entity.jellyfish

import dev.hybridlabs.aquatic.entity.jellyfish.BoxJellyfishEntity
import net.minecraft.client.renderer.RenderType
import net.minecraft.resources.ResourceLocation

class BoxJellyfishEntityModel : HAJellyfishEntityModel<BoxJellyfishEntity>("box_jellyfish") {
    override fun getRenderType(animatable: BoxJellyfishEntity, texture: ResourceLocation): RenderType {
        return RenderType.entityTranslucent(texture)
    }
}
