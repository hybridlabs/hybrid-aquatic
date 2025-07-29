package dev.hybridlabs.aquatic.client.model.entity.cephalopod

import dev.hybridlabs.aquatic.entity.cephalopod.CuttlefishEntity
import net.minecraft.util.Identifier

class CuttlefishEntityModel : HybridAquaticCephalopodEntityModel<CuttlefishEntity>("cuttlefish") {

    private val RED_CUTTLEFISH_TEXTURE = Identifier("hybrid-aquatic", "textures/entity/cephalopod/cuttlefish/cuttlefish_red.png")
    private val RED_CUTTLEFISH_1_TEXTURE = Identifier("hybrid-aquatic", "textures/entity/cephalopod/cuttlefish/cuttlefish_red_1.png")
    private val RED_CUTTLEFISH_2_TEXTURE = Identifier("hybrid-aquatic", "textures/entity/cephalopod/cuttlefish/cuttlefish_red_2.png")
    private val BLACK_CUTTLEFISH_TEXTURE = Identifier("hybrid-aquatic", "textures/entity/cephalopod/cuttlefish/cuttlefish_black.png")
    private val BLACK_CUTTLEFISH_1_TEXTURE = Identifier("hybrid-aquatic", "textures/entity/cephalopod/cuttlefish/cuttlefish_black_1.png")
    private val BLACK_CUTTLEFISH_2_TEXTURE = Identifier("hybrid-aquatic", "textures/entity/cephalopod/cuttlefish/cuttlefish_black_2.png")

    override fun getTextureResource(animatable: CuttlefishEntity): Identifier {
        return when (animatable.variant) {
            CuttlefishEntity.Type.RED -> RED_CUTTLEFISH_TEXTURE
            CuttlefishEntity.Type.RED_1 -> RED_CUTTLEFISH_1_TEXTURE
            CuttlefishEntity.Type.RED_2 -> RED_CUTTLEFISH_2_TEXTURE
            CuttlefishEntity.Type.BLACK -> BLACK_CUTTLEFISH_TEXTURE
            CuttlefishEntity.Type.BLACK_1 -> BLACK_CUTTLEFISH_1_TEXTURE
            CuttlefishEntity.Type.BLACK_2 -> BLACK_CUTTLEFISH_2_TEXTURE
        }
    }
}