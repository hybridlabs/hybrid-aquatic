package dev.hybridlabs.aquatic.client.model.entity.jellyfish

import dev.hybridlabs.hapi.client.model.HAPIDataTickets
import dev.hybridlabs.hapi.client.model.getVariant
import software.bernie.geckolib.renderer.base.GeoRenderState
import dev.hybridlabs.aquatic.entity.jellyfish.CombJellyEntity
import dev.hybridlabs.hapi.client.model.entity.aquatic.BaseJellyfishEntityModel
import net.minecraft.client.renderer.rendertype.RenderType
import net.minecraft.client.renderer.rendertype.RenderTypes
import net.minecraft.resources.Identifier

class CombJellyEntityModel : BaseJellyfishEntityModel<CombJellyEntity>("hybrid_aquatic", "comb_jelly") {

    companion object {
        private val SEA_WALNUT_TEXTURE =
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/jellyfish/comb_jelly/comb_jelly.png")
        private val BLOODYBELLY_TEXTURE =
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/jellyfish/comb_jelly/bloodybelly_comb_jelly.png")
    }

    override fun getTextureResource(renderState: GeoRenderState): Identifier {
        return when (renderState.getVariant(CombJellyEntity.Companion.Type.entries)!!) {
            CombJellyEntity.Companion.Type.SEA_WALNUT -> SEA_WALNUT_TEXTURE
            CombJellyEntity.Companion.Type.BLOODYBELLY -> BLOODYBELLY_TEXTURE
        }
    }
}
