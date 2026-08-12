package dev.hybridlabs.aquatic.client.model.entity.fish

import dev.hybridlabs.aquatic.entity.fish.ShinerEntity
import dev.hybridlabs.hapi.client.model.entity.BaseFishEntityModel
import net.minecraft.client.renderer.RenderType
import net.minecraft.resources.ResourceLocation

class ShinerEntityModel : BaseFishEntityModel<ShinerEntity>("hybrid_aquatic", "shiner") {
    override fun getRenderType(animatable: ShinerEntity, texture: ResourceLocation): RenderType {
        return RenderType.entityTranslucent(texture)
    }
}