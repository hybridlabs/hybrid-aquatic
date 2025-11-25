package dev.hybridlabs.aquatic.client.model.entity.fish

import dev.hybridlabs.aquatic.entity.fish.AnglerfishEntity
import dev.hybridlabs.aquatic.entity.fish.FangtoothEntity
import net.minecraft.client.renderer.RenderType
import net.minecraft.resources.ResourceLocation

class FangtoothEntityModel : HybridAquaticFishEntityModel<FangtoothEntity>("fangtooth") {
    override fun getRenderType(animatable: FangtoothEntity, texture: ResourceLocation): RenderType {
        return RenderType.entityTranslucent(texture)
    }
}