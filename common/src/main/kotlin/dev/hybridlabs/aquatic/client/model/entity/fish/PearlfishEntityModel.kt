package dev.hybridlabs.aquatic.client.model.entity.fish

import dev.hybridlabs.aquatic.entity.fish.PearlfishEntity
import dev.hybridlabs.hapi.client.model.entity.aquatic.BaseFishEntityModel
import net.minecraft.client.renderer.RenderType
import net.minecraft.resources.ResourceLocation

class PearlfishEntityModel : BaseFishEntityModel<PearlfishEntity>("hybrid_aquatic", "pearlfish") {
    override fun getRenderType(animatable: PearlfishEntity, texture: ResourceLocation): RenderType {
        return RenderType.entityTranslucent(texture)
    }
}