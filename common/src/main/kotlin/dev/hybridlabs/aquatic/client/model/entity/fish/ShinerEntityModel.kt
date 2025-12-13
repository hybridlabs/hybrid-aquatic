package dev.hybridlabs.aquatic.client.model.entity.fish

import dev.hybridlabs.aquatic.entity.fish.ShinerEntity
import net.minecraft.client.renderer.RenderType
import net.minecraft.resources.ResourceLocation

class ShinerEntityModel : HybridAquaticFishEntityModel<ShinerEntity>("shiner") {
    override fun getRenderType(animatable: ShinerEntity, texture: ResourceLocation): RenderType {
        return RenderType.entityTranslucent(texture)
    }
}