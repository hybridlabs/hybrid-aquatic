package dev.hybridlabs.aquatic.client.model.entity.fish

import dev.hybridlabs.aquatic.entity.fish.DiscusEntity
import net.minecraft.client.renderer.RenderType
import net.minecraft.resources.ResourceLocation

class DiscusEntityModel : HAFishEntityModel<DiscusEntity>("discus") {
    override fun getRenderType(animatable: DiscusEntity, texture: ResourceLocation): RenderType {
        return RenderType.entityTranslucent(texture)
    }
}
