package dev.hybridlabs.aquatic.client.model.entity.fish

import dev.hybridlabs.aquatic.entity.fish.JohnDoryEntity
import net.minecraft.client.render.RenderLayer
import net.minecraft.util.Identifier

class JohnDoryEntityModel : HybridAquaticFishEntityModel<JohnDoryEntity>("john_dory") {
    override fun getRenderType(animatable: JohnDoryEntity, texture: Identifier): RenderLayer {
        return RenderLayer.getEntityTranslucent(texture)
    }
}

