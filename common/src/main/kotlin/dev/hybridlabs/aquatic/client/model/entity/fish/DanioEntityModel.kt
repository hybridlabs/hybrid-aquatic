package dev.hybridlabs.aquatic.client.model.entity.fish

import dev.hybridlabs.aquatic.entity.fish.DanioEntity
import dev.hybridlabs.hapi.client.model.entity.aquatic.BaseFishEntityModel
import net.minecraft.client.renderer.RenderType
import net.minecraft.resources.ResourceLocation

class DanioEntityModel : BaseFishEntityModel<DanioEntity>("hybrid_aquatic", "danio") {
    override fun getRenderType(animatable: DanioEntity, texture: ResourceLocation): RenderType {
        return RenderType.entityTranslucent(texture)
    }
}