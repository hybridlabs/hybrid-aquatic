package dev.hybridlabs.aquatic.client.model.entity.cephalopod

import dev.hybridlabs.aquatic.entity.cephalopod.CuttlefishEntity
import net.minecraft.resources.ResourceLocation
import kotlin.random.Random

class CuttlefishEntityModel : HACephalopodEntityModel<CuttlefishEntity>("cuttlefish") {

    companion object {
        private val commonTextures = listOf(
            ResourceLocation("hybrid-aquatic", "textures/entity/cephalopod/cuttlefish/common_cuttlefish_4.png"),
            ResourceLocation("hybrid-aquatic", "textures/entity/cephalopod/cuttlefish/common_cuttlefish_6.png"),
            ResourceLocation("hybrid-aquatic", "textures/entity/cephalopod/cuttlefish/common_cuttlefish_5.png"),
            ResourceLocation("hybrid-aquatic", "textures/entity/cephalopod/cuttlefish/common_cuttlefish_1.png"),
            ResourceLocation("hybrid-aquatic", "textures/entity/cephalopod/cuttlefish/common_cuttlefish_2.png"),
            ResourceLocation("hybrid-aquatic", "textures/entity/cephalopod/cuttlefish/common_cuttlefish_3.png")
        )
    }

    override fun getTextureResource(animatable: CuttlefishEntity): ResourceLocation {
        val seed = animatable.uuid.leastSignificantBits
        val random = Random(seed)
        return commonTextures[random.nextInt(commonTextures.size)]
    }
}