package dev.hybridlabs.aquatic.client.model.entity.fish

import dev.hybridlabs.aquatic.entity.fish.HybridAquaticFishEntity
import net.minecraft.client.render.RenderLayer
import net.minecraft.util.Identifier

class PearlfishEntityModel : HybridAquaticFishEntityModel<HybridAquaticFishEntity>("pearlfish") {
    override fun getRenderType(animatable: HybridAquaticFishEntity, texture: Identifier): RenderLayer {
        return RenderLayer.getEntityTranslucent(texture)
    }
}