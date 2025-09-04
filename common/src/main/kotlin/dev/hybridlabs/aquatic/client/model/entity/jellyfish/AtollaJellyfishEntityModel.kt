package dev.hybridlabs.aquatic.client.model.entity.jellyfish

import dev.hybridlabs.aquatic.entity.jellyfish.AtollaJellyfishEntity
import net.minecraft.client.renderer.RenderType
import net.minecraft.resources.ResourceLocation

class AtollaJellyfishEntityModel : HybridAquaticJellyfishEntityModel<AtollaJellyfishEntity>("atolla_jellyfish") {
    override fun getRenderType(animatable: AtollaJellyfishEntity, texture: ResourceLocation): RenderType {
        return RenderType.entityTranslucent(texture)
    }
}
