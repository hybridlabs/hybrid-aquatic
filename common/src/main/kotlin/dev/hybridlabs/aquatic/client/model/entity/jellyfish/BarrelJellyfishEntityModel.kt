package dev.hybridlabs.aquatic.client.model.entity.jellyfish

import dev.hybridlabs.aquatic.entity.jellyfish.BarrelJellyfishEntity
import net.minecraft.client.renderer.RenderType
import net.minecraft.resources.ResourceLocation

class BarrelJellyfishEntityModel : HybridAquaticJellyfishEntityModel<BarrelJellyfishEntity>("barrel_jellyfish") {
    override fun getRenderType(animatable: BarrelJellyfishEntity, texture: ResourceLocation): RenderType {
        return RenderType.entityTranslucent(texture)
    }
}
