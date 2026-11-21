package dev.hybridlabs.aquatic.client.model.entity.fish

import dev.hybridlabs.hapi.client.model.HAPIDataTickets
import dev.hybridlabs.hapi.client.model.getVariant
import software.bernie.geckolib.renderer.base.GeoRenderState
import dev.hybridlabs.aquatic.entity.fish.CatfishEntity
import dev.hybridlabs.hapi.client.model.entity.aquatic.BaseFishEntityModel
import net.minecraft.resources.Identifier

class CatfishEntityModel : BaseFishEntityModel<CatfishEntity>("hybrid_aquatic", "catfish") {

    override fun getTextureResource(renderState: GeoRenderState): Identifier {
        return when (renderState.getVariant(CatfishEntity.Companion.Type.entries)!!) {
            CatfishEntity.Companion.Type.CHANNEL_CATFISH -> CHANNEL_CATFISH_TEXTURE
        }
    }

    override fun getModelResource(renderState: GeoRenderState): Identifier {
        return when (renderState.getVariant(CatfishEntity.Companion.Type.entries)!!) {
            CatfishEntity.Companion.Type.CHANNEL_CATFISH -> CHANNEL_CATFISH_MODEL
        }
    }

    companion object {
        private val CHANNEL_CATFISH_TEXTURE =
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/fish/catfish/channel_catfish.png")

        private val CHANNEL_CATFISH_MODEL =
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "fish/catfish/channel_catfish")
    }
}
