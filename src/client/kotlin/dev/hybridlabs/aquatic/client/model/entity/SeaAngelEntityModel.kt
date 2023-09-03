package dev.hybridlabs.aquatic.client.model.entity

import dev.hybridlabs.aquatic.entity.fish.HybridAquaticFishEntity
import net.minecraft.client.render.RenderLayer
import net.minecraft.util.Identifier

class SeaAngelEntityModel : HybridAquaticFishEntityModel<HybridAquaticFishEntity>("sea_angel") {
    override fun getRenderType(animatable: HybridAquaticFishEntity?, texture: Identifier?): RenderLayer {
        return RenderLayer.getEntityTranslucent(texture)
    }
}
