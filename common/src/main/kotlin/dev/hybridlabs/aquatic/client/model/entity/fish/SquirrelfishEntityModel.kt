package dev.hybridlabs.aquatic.client.model.entity.fish

import dev.hybridlabs.aquatic.entity.fish.SquirrelfishEntity
import net.minecraft.client.render.RenderLayer
import net.minecraft.util.Identifier

class SquirrelfishEntityModel : HybridAquaticFishEntityModel<SquirrelfishEntity>("squirrelfish") {
    override fun getRenderType(animatable: SquirrelfishEntity, texture: Identifier): RenderLayer {
        return RenderLayer.getEntityTranslucent(texture)
    }
}