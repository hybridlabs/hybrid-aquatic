package dev.hybridlabs.aquatic.client.model.entity.fish

import dev.hybridlabs.aquatic.entity.fish.SnailfishEntity
import dev.hybridlabs.hapi.client.model.entity.BaseFishEntityModel
import net.minecraft.client.renderer.RenderType
import net.minecraft.resources.ResourceLocation

class SnailfishEntityModel : BaseFishEntityModel<SnailfishEntity>("hybrid_aquatic", "snailfish") {
    override fun getRenderType(animatable: SnailfishEntity, texture: ResourceLocation): RenderType {
        return RenderType.entityTranslucent(texture)
    }
}
