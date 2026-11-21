package dev.hybridlabs.aquatic.client.model.entity.fish

import dev.hybridlabs.hapi.client.model.HAPIDataTickets
import dev.hybridlabs.hapi.client.model.getVariant
import software.bernie.geckolib.renderer.base.GeoRenderState
import dev.hybridlabs.aquatic.entity.fish.PlecoEntity
import dev.hybridlabs.hapi.client.model.entity.aquatic.BaseFishEntityModel
import net.minecraft.client.renderer.rendertype.RenderType
import net.minecraft.client.renderer.rendertype.RenderTypes
import net.minecraft.resources.Identifier

class PlecoEntityModel : BaseFishEntityModel<PlecoEntity>("hybrid_aquatic", "pleco") {

    override fun getTextureResource(renderState: GeoRenderState): Identifier {
        return when (renderState.getVariant(PlecoEntity.Companion.Type.entries)!!) {
            PlecoEntity.Companion.Type.COMMON -> COMMON_PLECO_TEXTURE
            PlecoEntity.Companion.Type.BRISTLENOSE -> BRISTLENOSE_PLECO_TEXTURE
        }
    }

    override fun getModelResource(renderState: GeoRenderState): Identifier {
        return when (renderState.getVariant(PlecoEntity.Companion.Type.entries)!!) {
            PlecoEntity.Companion.Type.COMMON -> COMMON_PLECO_MODEL
            PlecoEntity.Companion.Type.BRISTLENOSE -> BRISTLENOSE_PLECO_MODEL
        }
    }

    companion object {
        private val COMMON_PLECO_TEXTURE =
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/fish/pleco/common_pleco.png")
        private val BRISTLENOSE_PLECO_TEXTURE =
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/fish/pleco/bristlenose_pleco.png")

        private val COMMON_PLECO_MODEL =
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "fish/pleco/common_pleco")
        private val BRISTLENOSE_PLECO_MODEL =
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "fish/pleco/bristlenose_pleco")
    }
}
