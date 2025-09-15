package dev.hybridlabs.aquatic.client.model.entity.fish

import dev.hybridlabs.aquatic.entity.fish.DanioEntity
import net.minecraft.client.renderer.RenderType
import net.minecraft.resources.ResourceLocation

class DanioEntityModel : HybridAquaticFishEntityModel<DanioEntity>("danio") {
    override fun getRenderType(animatable: DanioEntity, texture: ResourceLocation): RenderType {
        return RenderType.entityTranslucent(texture)
    }
}