package dev.hybridlabs.aquatic.client.model.entity.fish

import dev.hybridlabs.aquatic.entity.fish.HatchetfishEntity
import net.minecraft.client.renderer.RenderType
import net.minecraft.resources.ResourceLocation

class HatchetfishEntityModel : HybridAquaticFishEntityModel<HatchetfishEntity>("hatchetfish") {
    override fun getRenderType(animatable: HatchetfishEntity, texture: ResourceLocation): RenderType {
        return RenderType.entityTranslucent(texture)
    }
}