package dev.hybridlabs.aquatic.client.model.entity.fish

import dev.hybridlabs.aquatic.entity.fish.BlobfishEntity
import software.bernie.geckolib.renderer.base.GeoRenderState
import dev.hybridlabs.hapi.client.model.HAPIDataTickets
import dev.hybridlabs.hapi.client.model.entity.aquatic.BaseFishEntityModel
import net.minecraft.client.renderer.rendertype.RenderType
import net.minecraft.client.renderer.rendertype.RenderTypes
import net.minecraft.resources.Identifier

class BlobfishEntityModel : BaseFishEntityModel<BlobfishEntity>("hybrid_aquatic", "blobfish") {

    override fun getTextureResource(renderState: GeoRenderState): Identifier =
        if ((renderState.getGeckolibData(HAPIDataTickets.MOISTNESS)!!) < 590) BLOBBY_TEXTURE else BLOBFISH_TEXTURE

    override fun getModelResource(renderState: GeoRenderState): Identifier =
        if ((renderState.getGeckolibData(HAPIDataTickets.MOISTNESS)!!) < 590) BLOBBY_MODEL else BLOBFISH_MODEL

    companion object {
        private val BLOBFISH_TEXTURE =
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/fish/blobfish/blobfish.png")
        private val BLOBBY_TEXTURE =
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/fish/blobfish/blobfish_blobby.png")

        private val BLOBFISH_MODEL =
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "fish/blobfish/blobfish")
        private val BLOBBY_MODEL =
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "fish/blobfish/blobfish_blobby")
    }
}
