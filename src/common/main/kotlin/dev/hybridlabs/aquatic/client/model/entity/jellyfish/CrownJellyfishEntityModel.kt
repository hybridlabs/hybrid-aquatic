package dev.hybridlabs.aquatic.client.model.entity.jellyfish

import dev.hybridlabs.aquatic.entity.jellyfish.CrownJellyfishEntity
import net.minecraft.client.renderer.RenderType
import net.minecraft.resources.ResourceLocation

class CrownJellyfishEntityModel : HybridAquaticJellyfishEntityModel<CrownJellyfishEntity>("crown_jellyfish") {
    override fun getRenderType(animatable: CrownJellyfishEntity, texture: ResourceLocation): RenderType {
        return RenderType.entityTranslucent(texture)
    }
}
