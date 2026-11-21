package dev.hybridlabs.aquatic.client.model.entity.fish

import dev.hybridlabs.hapi.client.model.HAPIDataTickets
import dev.hybridlabs.hapi.client.model.getVariant
import software.bernie.geckolib.renderer.base.GeoRenderState
import dev.hybridlabs.aquatic.entity.fish.TunaEntity
import dev.hybridlabs.hapi.client.model.entity.aquatic.BaseFishEntityModel
import net.minecraft.resources.Identifier

class TunaEntityModel : BaseFishEntityModel<TunaEntity>("hybrid_aquatic", "tuna") {

    override fun getTextureResource(renderState: GeoRenderState): Identifier {
        return when (renderState.getVariant(TunaEntity.Companion.Type.entries)!!) {
            TunaEntity.Companion.Type.YELLOWFIN -> YELLOWFIN_TEXTURE
            TunaEntity.Companion.Type.BLUEFIN -> BLUEFIN_TEXTURE
        }
    }

    override fun getModelResource(renderState: GeoRenderState): Identifier {
        return when (renderState.getVariant(TunaEntity.Companion.Type.entries)!!) {
            TunaEntity.Companion.Type.YELLOWFIN -> YELLOWFIN_MODEL
            TunaEntity.Companion.Type.BLUEFIN -> BLUEFIN_MODEL
        }
    }

    companion object {
        private val YELLOWFIN_TEXTURE =
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/fish/tuna/tuna_yellowfin.png")
        private val BLUEFIN_TEXTURE =
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/fish/tuna/tuna_bluefin.png")

        private val YELLOWFIN_MODEL =
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "fish/tuna/tuna_yellowfin")
        private val BLUEFIN_MODEL =
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "fish/tuna/tuna_bluefin")
    }
}
