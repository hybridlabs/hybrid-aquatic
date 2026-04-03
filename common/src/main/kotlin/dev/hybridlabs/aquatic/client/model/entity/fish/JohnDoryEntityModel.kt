package dev.hybridlabs.aquatic.client.model.entity.fish

import dev.hybridlabs.aquatic.entity.fish.JohnDoryEntity
import net.minecraft.client.renderer.RenderType
import net.minecraft.resources.ResourceLocation

class JohnDoryEntityModel : HAFishEntityModel<JohnDoryEntity>("john_dory") {
    override fun getRenderType(animatable: JohnDoryEntity, texture: ResourceLocation): RenderType {
        return RenderType.entityTranslucent(texture)
    }
}

