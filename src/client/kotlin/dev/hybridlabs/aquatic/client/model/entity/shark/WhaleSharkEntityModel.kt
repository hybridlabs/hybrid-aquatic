package dev.hybridlabs.aquatic.client.model.entity.shark

import dev.hybridlabs.aquatic.entity.shark.WhaleSharkEntity
import net.minecraft.util.Identifier

class WhaleSharkEntityModel : HybridAquaticSharkEntityModel<WhaleSharkEntity>("whale_shark") {

    private val BLUE_TEXTURE = Identifier("hybrid-aquatic", "textures/entity/shark/whale_shark.png")
    private val BROWN_TEXTURE = Identifier("hybrid-aquatic", "textures/entity/shark/whale_shark_brown.png")
    private val GRAY_TEXTURE = Identifier("hybrid-aquatic", "textures/entity/shark/whale_shark_gray.png")

    override fun getTextureResource(animatable: WhaleSharkEntity): Identifier {
        return when (animatable.variant) {
            WhaleSharkEntity.Type.BLUE -> BLUE_TEXTURE
            WhaleSharkEntity.Type.BROWN -> BROWN_TEXTURE
            WhaleSharkEntity.Type.GRAY -> GRAY_TEXTURE
        }
    }
}