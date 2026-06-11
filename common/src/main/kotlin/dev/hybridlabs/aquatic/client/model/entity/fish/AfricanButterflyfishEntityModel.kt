package dev.hybridlabs.aquatic.client.model.entity.fish

import dev.hybridlabs.aquatic.entity.fish.AfricanButterflyfishEntity
import net.minecraft.client.renderer.RenderType
import net.minecraft.resources.ResourceLocation

class AfricanButterflyfishEntityModel : HAFishEntityModel<AfricanButterflyfishEntity>("african_butterflyfish") {
    override fun getRenderType(animatable: AfricanButterflyfishEntity, texture: ResourceLocation): RenderType {
        return RenderType.entityTranslucent(texture)
    }
}