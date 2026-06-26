package dev.hybridlabs.aquatic.client.model.entity.fish

import dev.hybridlabs.aquatic.entity.fish.AnglerfishEntity
import net.minecraft.client.renderer.RenderType
import net.minecraft.resources.ResourceLocation

class AnglerfishEntityModel : HybridAquaticFishEntityModel<AnglerfishEntity>("anglerfish") {
    override fun getRenderType(animatable: AnglerfishEntity, texture: ResourceLocation): RenderType {
        return RenderType.entityTranslucent(texture)
    }
}