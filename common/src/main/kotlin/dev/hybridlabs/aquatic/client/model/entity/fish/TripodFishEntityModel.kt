package dev.hybridlabs.aquatic.client.model.entity.fish

import dev.hybridlabs.aquatic.entity.fish.TripodFishEntity
import net.minecraft.client.renderer.RenderType
import net.minecraft.resources.ResourceLocation

class TripodFishEntityModel : HybridAquaticFishEntityModel<TripodFishEntity>("tripod_fish") {
    override fun getRenderType(animatable: TripodFishEntity, texture: ResourceLocation): RenderType {
        return RenderType.entityTranslucent(texture)
    }
}