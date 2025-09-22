package dev.hybridlabs.aquatic.client.model.entity.fish

import dev.hybridlabs.aquatic.entity.fish.OarfishEntity
import net.minecraft.client.renderer.RenderType
import net.minecraft.resources.ResourceLocation

class OarfishEntityModel : HybridAquaticFishEntityModel<OarfishEntity>("oarfish") {
    override fun getRenderType(animatable: OarfishEntity, texture: ResourceLocation): RenderType {
        return RenderType.entityTranslucent(texture)
    }
}
