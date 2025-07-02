package dev.hybridlabs.aquatic.client.model.entity.fish

import dev.hybridlabs.aquatic.entity.fish.PearlfishEntity
import net.minecraft.client.render.RenderLayer
import net.minecraft.util.Identifier

class PearlfishEntityModel : HybridAquaticFishEntityModel<PearlfishEntity>("pearlfish") {
    override fun getRenderType(animatable: PearlfishEntity, texture: Identifier): RenderLayer {
        return RenderLayer.getEntityTranslucent(texture)
    }
}