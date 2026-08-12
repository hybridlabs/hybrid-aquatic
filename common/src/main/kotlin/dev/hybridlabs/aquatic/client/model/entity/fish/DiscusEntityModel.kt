package dev.hybridlabs.aquatic.client.model.entity.fish

import dev.hybridlabs.hapi.client.model.entity.BaseFishEntityModel
import dev.hybridlabs.aquatic.entity.fish.DiscusEntity
import net.minecraft.client.renderer.RenderType
import net.minecraft.resources.ResourceLocation

class DiscusEntityModel : BaseFishEntityModel<DiscusEntity>("hybrid_aquatic", "discus") {
    override fun getRenderType(animatable: DiscusEntity, texture: ResourceLocation): RenderType {
        return RenderType.entityTranslucent(texture)
    }
}
