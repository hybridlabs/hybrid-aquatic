package dev.hybridlabs.aquatic.client.model.entity.jellyfish

import dev.hybridlabs.aquatic.entity.jellyfish.FriedEggJellyfishEntity
import net.minecraft.client.render.RenderLayer
import net.minecraft.util.Identifier

class FriedEggJellyfishEntityModel : HybridAquaticJellyfishEntityModel<FriedEggJellyfishEntity>("fried_egg_jellyfish") {
    override fun getRenderType(animatable: FriedEggJellyfishEntity, texture: Identifier): RenderLayer {
        return RenderLayer.getEntityTranslucent(texture)
    }
}
