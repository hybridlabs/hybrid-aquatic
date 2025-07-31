package dev.hybridlabs.aquatic.client.model.entity.shark

import dev.hybridlabs.aquatic.entity.shark.HammerheadSharkEntity
import dev.hybridlabs.aquatic.entity.shark.WhaleSharkEntity
import net.minecraft.util.Identifier
import kotlin.random.Random

class WhaleSharkEntityModel : HybridAquaticSharkEntityModel<WhaleSharkEntity>("whale_shark") {

    private val commonTextures = listOf(
        Identifier("hybrid-aquatic", "textures/entity/shark/whale_shark.png"),
        Identifier("hybrid-aquatic", "textures/entity/shark/whale_shark_brown.png"),
        Identifier("hybrid-aquatic", "textures/entity/shark/whale_shark_gray.png")
    )

    override fun getTextureResource(animatable: WhaleSharkEntity): Identifier {
        val seed = animatable.uuid.leastSignificantBits
        val random = Random(seed)
        return when (animatable.variant) {
            WhaleSharkEntity.Companion.Type.COMMON -> commonTextures[random.nextInt(commonTextures.size)]
        }
    }
}