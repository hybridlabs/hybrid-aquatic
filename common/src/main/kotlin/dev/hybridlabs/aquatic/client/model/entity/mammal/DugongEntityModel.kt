package dev.hybridlabs.aquatic.client.model.entity.mammal

import dev.hybridlabs.hapi.client.model.HAPIDataTickets
import dev.hybridlabs.hapi.client.model.getVariant
import com.geckolib.renderer.base.GeoRenderState
import dev.hybridlabs.aquatic.entity.mammal.DugongEntity
import dev.hybridlabs.hapi.client.model.entity.aquatic.BaseSirenianEntityModel
import net.minecraft.resources.Identifier

class DugongEntityModel : BaseSirenianEntityModel<DugongEntity>("hybrid_aquatic", "dugong") {

    override fun getTextureResource(renderState: GeoRenderState): Identifier {
        return if ((renderState.getGeckolibData(HAPIDataTickets.IS_BABY) == true)) {
            BABY_TEXTURE
        } else when (renderState.getVariant(DugongEntity.Companion.Type.entries)!!) {
            DugongEntity.Companion.Type.PLAIN -> PLAIN_TEXTURE
            DugongEntity.Companion.Type.MOSSY -> MOSSY_TEXTURE
        }
    }

    companion object {
        private val BABY_TEXTURE =
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/mammal/dugong/baby_dugong.png")

        private val PLAIN_TEXTURE =
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/mammal/dugong/dugong.png")
        private val MOSSY_TEXTURE =
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/mammal/dugong/mossy_dugong.png")
    }
}