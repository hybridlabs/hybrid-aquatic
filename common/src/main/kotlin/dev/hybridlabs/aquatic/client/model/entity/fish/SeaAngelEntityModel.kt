package dev.hybridlabs.aquatic.client.model.entity.fish

import dev.hybridlabs.aquatic.entity.fish.SeaAngelEntity
import dev.hybridlabs.hapi.client.model.entity.BaseFishEntityModel
import net.minecraft.client.renderer.RenderType
import net.minecraft.resources.ResourceLocation

class SeaAngelEntityModel : BaseFishEntityModel<SeaAngelEntity>("hybrid_aquatic", "sea_angel") {
    override fun getRenderType(animatable: SeaAngelEntity, texture: ResourceLocation): RenderType {
        return RenderType.entityTranslucent(texture)
    }
}
