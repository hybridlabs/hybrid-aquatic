package dev.hybridlabs.aquatic.client.model.entity.mammal

import dev.hybridlabs.hapi.client.model.HAPIDataTickets
import dev.hybridlabs.hapi.client.model.getVariant
import software.bernie.geckolib.renderer.base.GeoRenderState
import dev.hybridlabs.aquatic.entity.mammal.ManateeEntity
import dev.hybridlabs.hapi.client.model.entity.aquatic.BaseSirenianEntityModel
import net.minecraft.resources.Identifier

class ManateeEntityModel : BaseSirenianEntityModel<ManateeEntity>("hybrid_aquatic", "manatee") {

    override fun getTextureResource(renderState: GeoRenderState): Identifier {
        return if ((renderState.getGeckolibData(HAPIDataTickets.IS_BABY) == true)) {
            BABY_TEXTURE
        } else when (renderState.getVariant(ManateeEntity.Companion.Type.entries)!!) {
            ManateeEntity.Companion.Type.PLAIN -> PLAIN_TEXTURE
            ManateeEntity.Companion.Type.MOSSY -> MOSSY_TEXTURE
        }
    }

    companion object {
        private val BABY_TEXTURE =
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/mammal/manatee/baby_manatee.png")

        private val PLAIN_TEXTURE =
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/mammal/manatee/manatee.png")
        private val MOSSY_TEXTURE =
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/mammal/manatee/mossy_manatee.png")
    }
}