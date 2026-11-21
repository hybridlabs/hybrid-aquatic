package dev.hybridlabs.aquatic.client.model.entity.jellyfish

import dev.hybridlabs.hapi.client.model.HAPIDataTickets
import dev.hybridlabs.hapi.client.model.getVariant
import com.geckolib.renderer.base.GeoRenderState
import dev.hybridlabs.aquatic.entity.jellyfish.CepheidaeJellyfishEntity
import dev.hybridlabs.hapi.client.model.entity.aquatic.BaseJellyfishEntityModel
import net.minecraft.client.renderer.rendertype.RenderType
import net.minecraft.client.renderer.rendertype.RenderTypes
import net.minecraft.resources.Identifier

class CepheidaeJellyfishEntityModel : BaseJellyfishEntityModel<CepheidaeJellyfishEntity>("hybrid_aquatic", "cepheidae_jellyfish") {

    companion object {
        private val CAULIFLOWER_TEXTURE =
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/jellyfish/cepheidae_jellyfish/cauliflower_jellyfish.png")
        private val FRIED_EGG_TEXTURE =
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/jellyfish/cepheidae_jellyfish/fried_egg_jellyfish.png")

        private val CAULIFLOWER_MODEL =
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "jellyfish/cepheidae_jellyfish/cauliflower_jellyfish")
        private val FRIED_EGG_MODEL =
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "jellyfish/cepheidae_jellyfish/fried_egg_jellyfish")
    }

    override fun getTextureResource(renderState: GeoRenderState): Identifier {
        return when (renderState.getVariant(CepheidaeJellyfishEntity.Companion.Type.entries)!!) {
            CepheidaeJellyfishEntity.Companion.Type.CAULIFLOWER -> CAULIFLOWER_TEXTURE
            CepheidaeJellyfishEntity.Companion.Type.FRIED_EGG -> FRIED_EGG_TEXTURE
        }
    }

    override fun getModelResource(renderState: GeoRenderState): Identifier {
        return when (renderState.getVariant(CepheidaeJellyfishEntity.Companion.Type.entries)!!) {
            CepheidaeJellyfishEntity.Companion.Type.CAULIFLOWER -> CAULIFLOWER_MODEL
            CepheidaeJellyfishEntity.Companion.Type.FRIED_EGG -> FRIED_EGG_MODEL
        }
    }
}
