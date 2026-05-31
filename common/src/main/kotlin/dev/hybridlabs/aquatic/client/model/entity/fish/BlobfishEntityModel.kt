package dev.hybridlabs.aquatic.client.model.entity.fish

import dev.hybridlabs.aquatic.entity.fish.BlobfishEntity
import net.minecraft.client.renderer.RenderType
import net.minecraft.resources.ResourceLocation

class BlobfishEntityModel : HAFishEntityModel<BlobfishEntity>("blobfish") {
    override fun getRenderType(animatable: BlobfishEntity, texture: ResourceLocation): RenderType {
        return RenderType.entityTranslucent(texture)
    }
}