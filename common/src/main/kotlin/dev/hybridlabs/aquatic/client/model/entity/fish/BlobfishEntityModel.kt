package dev.hybridlabs.aquatic.client.model.entity.fish

import dev.hybridlabs.aquatic.entity.fish.BlobfishEntity
import net.minecraft.client.renderer.RenderType
import net.minecraft.resources.ResourceLocation

class BlobfishEntityModel : HAFishEntityModel<BlobfishEntity>("blobfish") {
    override fun getRenderType(animatable: BlobfishEntity, texture: ResourceLocation): RenderType {
        return RenderType.entityTranslucent(texture)
    }

    override fun getTextureResource(animatable: BlobfishEntity): ResourceLocation =
        if (animatable.moistness < 590) BLOBBY_TEXTURE else BLOBFISH_TEXTURE

    override fun getModelResource(animatable: BlobfishEntity): ResourceLocation =
        if (animatable.moistness < 590) BLOBBY_MODEL else BLOBFISH_MODEL

    companion object {
        private val BLOBFISH_TEXTURE =
            ResourceLocation.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/fish/blobfish/blobfish.png")
        private val BLOBBY_TEXTURE =
            ResourceLocation.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/fish/blobfish/blobfish_blobby.png")

        private val BLOBFISH_MODEL =
            ResourceLocation.fromNamespaceAndPath("hybrid_aquatic", "geo/fish/blobfish/blobfish.geo.json")
        private val BLOBBY_MODEL =
            ResourceLocation.fromNamespaceAndPath("hybrid_aquatic", "geo/fish/blobfish/blobfish_blobby.geo.json")
    }
}
