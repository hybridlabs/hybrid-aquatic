package dev.hybridlabs.aquatic.client.model.entity.jellyfish

import dev.hybridlabs.aquatic.entity.jellyfish.HybridAquaticJellyfishEntity
import net.minecraft.client.renderer.RenderType
import net.minecraft.resources.ResourceLocation

class FriedEggJellyfishEntityModel :
    HybridAquaticJellyfishEntityModel<HybridAquaticJellyfishEntity>("fried_egg_jellyfish") {
    override fun getRenderType(animatable: HybridAquaticJellyfishEntity, texture: ResourceLocation): RenderType {
        return RenderType.entityTranslucent(texture)
    }
}
