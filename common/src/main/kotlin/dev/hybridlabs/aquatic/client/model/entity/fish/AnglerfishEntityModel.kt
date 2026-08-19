package dev.hybridlabs.aquatic.client.model.entity.fish

import dev.hybridlabs.aquatic.entity.fish.AnglerfishEntity
import dev.hybridlabs.hapi.client.model.entity.aquatic.BaseFishEntityModel
import net.minecraft.client.renderer.RenderType
import net.minecraft.resources.ResourceLocation

class AnglerfishEntityModel : BaseFishEntityModel<AnglerfishEntity>("hybrid_aquatic", "anglerfish") {
    override fun getRenderType(animatable: AnglerfishEntity, texture: ResourceLocation): RenderType {
        return RenderType.entityTranslucent(texture)
    }
}