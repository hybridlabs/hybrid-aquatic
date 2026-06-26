package dev.hybridlabs.aquatic.client.model.entity.fish

import dev.hybridlabs.aquatic.entity.fish.PlecoEntity
import net.minecraft.client.renderer.RenderType
import net.minecraft.resources.ResourceLocation

class PlecoEntityModel : HybridAquaticFishEntityModel<PlecoEntity>("pleco") {
    override fun getRenderType(animatable: PlecoEntity, texture: ResourceLocation): RenderType {
        return RenderType.entityTranslucent(texture)
    }
}