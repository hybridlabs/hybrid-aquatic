package dev.hybridlabs.aquatic.client.model.entity.fish

import dev.hybridlabs.aquatic.entity.fish.SeaAngelEntity
import net.minecraft.client.render.RenderLayer
import net.minecraft.util.Identifier

class SeaAngelEntityModel : HybridAquaticFishEntityModel<SeaAngelEntity>("sea_angel") {
    override fun getRenderType(animatable: SeaAngelEntity, texture: Identifier): RenderLayer {
        return RenderLayer.getEntityTranslucent(texture)
    }
}
