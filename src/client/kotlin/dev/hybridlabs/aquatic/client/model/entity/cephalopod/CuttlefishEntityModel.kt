package dev.hybridlabs.aquatic.client.model.entity.cephalopod

import dev.hybridlabs.aquatic.entity.cephalopod.CuttlefishEntity
import net.minecraft.util.Identifier
import kotlin.random.Random

class CuttlefishEntityModel : HybridAquaticCephalopodEntityModel<CuttlefishEntity>("cuttlefish") {

    private val redTextures = listOf(
        Identifier("hybrid-aquatic", "textures/entity/cephalopod/cuttlefish/cuttlefish_red.png"),
        Identifier("hybrid-aquatic", "textures/entity/cephalopod/cuttlefish/cuttlefish_red_1.png"),
        Identifier("hybrid-aquatic", "textures/entity/cephalopod/cuttlefish/cuttlefish_red_2.png")
    )

    private val blackTextures = listOf(
        Identifier("hybrid-aquatic", "textures/entity/cephalopod/cuttlefish/cuttlefish_black.png"),
        Identifier("hybrid-aquatic", "textures/entity/cephalopod/cuttlefish/cuttlefish_black_1.png"),
        Identifier("hybrid-aquatic", "textures/entity/cephalopod/cuttlefish/cuttlefish_black_2.png")
    )
    override fun getTextureResource(animatable: CuttlefishEntity): Identifier {
        val seed = animatable.uuid.leastSignificantBits
        val random = Random(seed)

        return when (animatable.variant) {
            CuttlefishEntity.Type.RED -> redTextures[random.nextInt(redTextures.size)]
            CuttlefishEntity.Type.BLACK -> blackTextures[random.nextInt(blackTextures.size)]
        }
    }
}