package dev.hybridlabs.aquatic.client.model.entity.mammal

import dev.hybridlabs.aquatic.entity.mammal.DugongEntity
import net.minecraft.resources.ResourceLocation
import kotlin.random.Random

class DugongEntityModel : HASirenianEntityModel<DugongEntity>("dugong") {

    override fun getTextureResource(animatable: DugongEntity): ResourceLocation {
        val seed = animatable.uuid.leastSignificantBits
        val random = Random(seed)

        return if (animatable.isBaby) {
            BABY_TEXTURE
        } else {
            dugongTextures[random.nextInt(dugongTextures.size)]
        }
    }

    companion object {
        private val BABY_TEXTURE =
            ResourceLocation("hybrid_aquatic", "textures/entity/mammal/dugong/baby_dugong.png")

        private val dugongTextures = listOf(
            ResourceLocation("hybrid_aquatic", "textures/entity/mammal/dugong/dugong.png"),
            ResourceLocation("hybrid_aquatic", "textures/entity/mammal/dugong/mossy_dugong.png")
        )
    }
}