package dev.hybridlabs.aquatic.client.model.entity.fish

import dev.hybridlabs.aquatic.entity.fish.HybridAquaticFishEntity
import net.minecraft.client.renderer.RenderType
import net.minecraft.resources.ResourceLocation

class PearlfishEntityModel : HybridAquaticFishEntityModel<HybridAquaticFishEntity>("pearlfish") {
    override fun getRenderType(animatable: HybridAquaticFishEntity, texture: ResourceLocation): RenderType {
        return RenderType.entityTranslucent(texture)
    }
}