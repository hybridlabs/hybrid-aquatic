package dev.hybridlabs.aquatic.client.model.entity.jellyfish

import dev.hybridlabs.aquatic.entity.jellyfish.MauveStingerEntity
import net.minecraft.client.renderer.RenderType
import net.minecraft.resources.ResourceLocation

class MauveStingerEntityModel : HAJellyfishEntityModel<MauveStingerEntity>("mauve_stinger") {
    override fun getRenderType(animatable: MauveStingerEntity, texture: ResourceLocation): RenderType {
        return RenderType.entityTranslucent(texture)
    }
}
