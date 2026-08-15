package dev.hybridlabs.aquatic.client.model.entity.shark

import dev.hybridlabs.aquatic.entity.shark.WhaleSharkEntity
import dev.hybridlabs.hapi.client.model.entity.aquatic.BaseSharkEntityModel
import net.minecraft.resources.ResourceLocation
import kotlin.random.Random

class WhaleSharkEntityModel : BaseSharkEntityModel<WhaleSharkEntity>("hybrid_aquatic", "whale_shark") {

    companion object {
        private val commonTextures = listOf(
            ResourceLocation.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/shark/whale_shark/whale_shark.png"),
            ResourceLocation.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/shark/whale_shark/whale_shark_brown.png"),
            ResourceLocation.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/shark/whale_shark/whale_shark_gray.png")
        )
    }

    override fun getTextureResource(animatable: WhaleSharkEntity): ResourceLocation {
        val seed = animatable.uuid.leastSignificantBits
        val random = Random(seed)
        return commonTextures[random.nextInt(commonTextures.size)]
    }
}
