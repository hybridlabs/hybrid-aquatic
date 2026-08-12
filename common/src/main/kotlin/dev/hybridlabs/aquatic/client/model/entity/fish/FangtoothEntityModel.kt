package dev.hybridlabs.aquatic.client.model.entity.fish

import dev.hybridlabs.aquatic.entity.fish.FangtoothEntity
import dev.hybridlabs.hapi.client.model.entity.BaseFishEntityModel
import net.minecraft.client.renderer.RenderType
import net.minecraft.resources.ResourceLocation

class FangtoothEntityModel : BaseFishEntityModel<FangtoothEntity>("hybrid_aquatic", "fangtooth") {
    override fun getRenderType(animatable: FangtoothEntity, texture: ResourceLocation): RenderType {
        return RenderType.entityTranslucent(texture)
    }
}