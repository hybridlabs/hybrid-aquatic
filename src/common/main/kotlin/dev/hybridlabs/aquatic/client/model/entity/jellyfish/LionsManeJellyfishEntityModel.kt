package dev.hybridlabs.aquatic.client.model.entity.jellyfish

import dev.hybridlabs.aquatic.entity.jellyfish.LionsManeJellyfishEntity
import net.minecraft.client.renderer.RenderType
import net.minecraft.resources.ResourceLocation

class LionsManeJellyfishEntityModel : HybridAquaticJellyfishEntityModel<LionsManeJellyfishEntity>("lions_mane_jellyfish") {
    override fun getRenderType(animatable: LionsManeJellyfishEntity, texture: ResourceLocation): RenderType {
        return RenderType.entityTranslucent(texture)
    }
}
