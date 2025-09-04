package dev.hybridlabs.aquatic.client.model.entity.cephalopod

import dev.hybridlabs.aquatic.entity.cephalopod.CuttlefishEntity
import net.minecraft.util.Identifier
import kotlin.random.Random

class CuttlefishEntityModel : HybridAquaticCephalopodEntityModel<CuttlefishEntity>("cuttlefish") {

    private val commonTextures = listOf(
        Identifier("hybrid-aquatic", "textures/entity/cephalopod/cuttlefish/common_cuttlefish_4.png"),
        Identifier("hybrid-aquatic", "textures/entity/cephalopod/cuttlefish/common_cuttlefish_6.png"),
        Identifier("hybrid-aquatic", "textures/entity/cephalopod/cuttlefish/common_cuttlefish_5.png"),
        Identifier("hybrid-aquatic", "textures/entity/cephalopod/cuttlefish/common_cuttlefish_1.png"),
        Identifier("hybrid-aquatic", "textures/entity/cephalopod/cuttlefish/common_cuttlefish_2.png"),
        Identifier("hybrid-aquatic", "textures/entity/cephalopod/cuttlefish/common_cuttlefish_3.png")
    )

    override fun getTextureResource(animatable: CuttlefishEntity): Identifier {
        val seed = animatable.uuid.leastSignificantBits
        val random = Random(seed)
        return commonTextures[random.nextInt(commonTextures.size)]
    }
}