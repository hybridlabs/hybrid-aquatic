package dev.hybridlabs.aquatic.client.model.entity.fish

import dev.hybridlabs.aquatic.entity.fish.JohnDoryEntity
import dev.hybridlabs.hapi.client.model.entity.BaseFishEntityModel
import net.minecraft.client.renderer.RenderType
import net.minecraft.resources.ResourceLocation

class JohnDoryEntityModel : BaseFishEntityModel<JohnDoryEntity>("hybrid_aquatic", "john_dory") {
    override fun getRenderType(animatable: JohnDoryEntity, texture: ResourceLocation): RenderType {
        return RenderType.entityTranslucent(texture)
    }
}

