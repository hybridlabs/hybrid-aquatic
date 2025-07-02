package dev.hybridlabs.aquatic.client.model.entity.jellyfish

import dev.hybridlabs.aquatic.entity.jellyfish.MauveStingerEntity
import net.minecraft.client.render.RenderLayer
import net.minecraft.util.Identifier

class MauveStingerEntityModel : HybridAquaticJellyfishEntityModel<MauveStingerEntity>("mauve_stinger") {
    override fun getRenderType(animatable: MauveStingerEntity, texture: Identifier): RenderLayer {
        return RenderLayer.getEntityTranslucent(texture)
    }
}
