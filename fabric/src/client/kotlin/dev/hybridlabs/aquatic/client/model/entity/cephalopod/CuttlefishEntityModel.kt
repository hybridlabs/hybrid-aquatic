package dev.hybridlabs.aquatic.client.model.entity.cephalopod

import dev.hybridlabs.aquatic.HybridAquatic
import dev.hybridlabs.aquatic.entity.cephalopod.CuttlefishEntity
import net.minecraft.util.Identifier

class CuttlefishEntityModel : HybridAquaticCephalopodEntityModel<CuttlefishEntity>("cuttlefish") {
    override fun getTextureResource(animatable: CuttlefishEntity): Identifier {
        val texturePath = when (animatable.randomValue) {
            0 -> "textures/entity/cephalopod/cuttlefish/cuttlefish.png"
            1 -> "textures/entity/cephalopod/cuttlefish/cuttlefish_zebra.png"
            2 -> "textures/entity/cephalopod/cuttlefish/cuttlefish_disruptive.png"
            3 -> "textures/entity/cephalopod/cuttlefish/cuttlefish_white.png"
            4 -> "textures/entity/cephalopod/cuttlefish/cuttlefish_black.png"
            5 -> "textures/entity/cephalopod/cuttlefish/cuttlefish_black_white.png"
            6 -> "textures/entity/cephalopod/cuttlefish/cuttlefish_white_black.png"
            7 -> "textures/entity/cephalopod/cuttlefish/cuttlefish_red_white.png"
            8 -> "textures/entity/cephalopod/cuttlefish/cuttlefish_white_red.png"
            9 -> "textures/entity/cephalopod/cuttlefish/cuttlefish_creeper_black.png"
            10 -> "textures/entity/cephalopod/cuttlefish/cuttlefish_creeper_white.png"
            11 -> "textures/entity/cephalopod/cuttlefish/cuttlefish_creeper_red.png"
            else -> "textures/entity/cephalopod/cuttlefish/cuttlefish.png"
        }

        return Identifier(HybridAquatic.MOD_ID, texturePath)
    }
}