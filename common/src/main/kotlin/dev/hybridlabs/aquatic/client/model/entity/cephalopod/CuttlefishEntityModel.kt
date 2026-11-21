package dev.hybridlabs.aquatic.client.model.entity.cephalopod

import dev.hybridlabs.hapi.client.model.HAPIDataTickets
import software.bernie.geckolib.renderer.base.GeoRenderState
import dev.hybridlabs.aquatic.entity.cephalopod.CuttlefishEntity
import dev.hybridlabs.hapi.client.model.entity.aquatic.BaseCephalopodEntityModel
import net.minecraft.resources.Identifier
import kotlin.random.Random

class CuttlefishEntityModel : BaseCephalopodEntityModel<CuttlefishEntity>("hybrid_aquatic", "cuttlefish") {

    companion object {
        private val commonTextures = listOf(
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/cephalopod/cuttlefish/common_cuttlefish_4.png"),
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/cephalopod/cuttlefish/common_cuttlefish_6.png"),
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/cephalopod/cuttlefish/common_cuttlefish_5.png"),
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/cephalopod/cuttlefish/common_cuttlefish_1.png"),
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/cephalopod/cuttlefish/common_cuttlefish_2.png"),
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/cephalopod/cuttlefish/common_cuttlefish_3.png")
        )
    }

    override fun getTextureResource(renderState: GeoRenderState): Identifier {
        val seed = (renderState.getGeckolibData(HAPIDataTickets.ENTITY_UUID)!!).leastSignificantBits
        val random = Random(seed)
        return commonTextures[random.nextInt(commonTextures.size)]
    }
}
