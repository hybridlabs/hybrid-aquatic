package dev.hybridlabs.aquatic.client.model.entity.fish

import dev.hybridlabs.aquatic.entity.fish.PupfishEntity
import dev.hybridlabs.hapi.client.model.entity.BaseFishEntityModel
import net.minecraft.client.renderer.RenderType
import net.minecraft.resources.ResourceLocation

class PupfishEntityModel : BaseFishEntityModel<PupfishEntity>("hybrid_aquatic", "pupfish") {
    override fun getRenderType(animatable: PupfishEntity, texture: ResourceLocation): RenderType {
        return RenderType.entityTranslucent(texture)
    }
}