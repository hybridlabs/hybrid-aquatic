package dev.hybridlabs.aquatic.client.model.entity.jellyfish

import dev.hybridlabs.aquatic.entity.jellyfish.MoonJellyfishEntity
import net.minecraft.client.renderer.RenderType
import net.minecraft.resources.ResourceLocation

class MoonJellyfishEntityModel : HybridAquaticJellyfishEntityModel<MoonJellyfishEntity>("moon_jellyfish") {
    override fun getRenderType(animatable: MoonJellyfishEntity, texture: ResourceLocation): RenderType {
        return RenderType.entityTranslucent(texture)
    }
}
