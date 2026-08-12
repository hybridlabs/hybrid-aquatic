package dev.hybridlabs.aquatic.client.model.entity.fish

import dev.hybridlabs.aquatic.entity.fish.HatchetfishEntity
import dev.hybridlabs.hapi.client.model.entity.BaseFishEntityModel
import net.minecraft.client.renderer.RenderType
import net.minecraft.resources.ResourceLocation

class HatchetfishEntityModel : BaseFishEntityModel<HatchetfishEntity>("hybrid_aquatic", "hatchetfish") {
    override fun getRenderType(animatable: HatchetfishEntity, texture: ResourceLocation): RenderType {
        return RenderType.entityTranslucent(texture)
    }
}