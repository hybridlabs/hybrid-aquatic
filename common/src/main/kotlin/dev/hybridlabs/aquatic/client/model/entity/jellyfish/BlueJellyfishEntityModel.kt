package dev.hybridlabs.aquatic.client.model.entity.jellyfish

import dev.hybridlabs.aquatic.entity.jellyfish.BlueJellyfishEntity
import net.minecraft.client.renderer.RenderType
import net.minecraft.resources.ResourceLocation

class BlueJellyfishEntityModel : HAJellyfishEntityModel<BlueJellyfishEntity>("blue_jellyfish") {
    override fun getRenderType(animatable: BlueJellyfishEntity, texture: ResourceLocation): RenderType {
        return RenderType.entityTranslucent(texture)
    }
}
