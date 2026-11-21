package dev.hybridlabs.aquatic.client.model.entity.fish

import dev.hybridlabs.hapi.client.model.HAPIDataTickets
import dev.hybridlabs.hapi.client.model.getVariant
import software.bernie.geckolib.renderer.base.GeoRenderState
import dev.hybridlabs.hapi.client.model.entity.aquatic.BaseFishEntityModel
import dev.hybridlabs.aquatic.entity.fish.CichlidEntity
import net.minecraft.resources.Identifier

class CichlidEntityModel : BaseFishEntityModel<CichlidEntity>("hybrid_aquatic", "cichlid") {

    override fun getTextureResource(renderState: GeoRenderState): Identifier {
        return when (renderState.getVariant(CichlidEntity.Companion.Type.entries)!!) {
            CichlidEntity.Companion.Type.TILAPIA -> TILAPIA_TEXTURE
            CichlidEntity.Companion.Type.OSCAR -> OSCAR_TEXTURE
        }
    }

    override fun getModelResource(renderState: GeoRenderState): Identifier {
        return when (renderState.getVariant(CichlidEntity.Companion.Type.entries)!!) {
            CichlidEntity.Companion.Type.TILAPIA -> TILAPIA_MODEL
            CichlidEntity.Companion.Type.OSCAR -> OSCAR_MODEL
        }
    }

    companion object {
        private val TILAPIA_TEXTURE =
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/fish/cichlid/tilapia.png")
        private val OSCAR_TEXTURE =
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/fish/cichlid/oscar.png")

        private val TILAPIA_MODEL =
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "fish/cichlid/tilapia")
        private val OSCAR_MODEL =
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "fish/cichlid/oscar")
    }
}
