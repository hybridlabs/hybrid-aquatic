package dev.hybridlabs.aquatic.client.model.entity.fish

import dev.hybridlabs.aquatic.entity.fish.SquirrelfishEntity
import net.minecraft.client.renderer.RenderType
import net.minecraft.resources.ResourceLocation

class SquirrelfishEntityModel : HybridAquaticFishEntityModel<SquirrelfishEntity>("squirrelfish") {
    override fun getRenderType(animatable: SquirrelfishEntity, texture: ResourceLocation): RenderType {
        return RenderType.entityTranslucent(texture)
    }
}