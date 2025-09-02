package dev.hybridlabs.aquatic.client.model.entity.fish

import dev.hybridlabs.aquatic.entity.fish.HybridAquaticFishEntity
import net.minecraft.client.renderer.RenderType
import net.minecraft.resources.ResourceLocation

class JohnDoryEntityModel : HybridAquaticFishEntityModel<HybridAquaticFishEntity>("john_dory") {
    override fun getRenderType(
        animatable: HybridAquaticFishEntity, texture:
        ResourceLocation
    ): RenderType {
        return RenderType.entityTranslucent(texture)
    }
}

