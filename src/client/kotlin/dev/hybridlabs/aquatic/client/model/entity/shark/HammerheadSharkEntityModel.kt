package dev.hybridlabs.aquatic.client.model.entity.shark

import dev.hybridlabs.aquatic.entity.shark.HammerheadSharkEntity
import net.minecraft.util.Identifier

class HammerheadSharkEntityModel : HybridAquaticSharkEntityModel<HammerheadSharkEntity>("hammerhead_shark") {

    private val BLUE_TEXTURE = Identifier("hybrid-aquatic", "textures/entity/shark/hammerhead_shark.png")
    private val BROWN_TEXTURE = Identifier("hybrid-aquatic", "textures/entity/shark/hammerhead_shark_brown.png")
    private val OLIVE_TEXTURE = Identifier("hybrid-aquatic", "textures/entity/shark/hammerhead_shark_olive.png")

    override fun getTextureResource(animatable: HammerheadSharkEntity): Identifier {
        return when (animatable.variant) {
            HammerheadSharkEntity.Type.BLUE -> BLUE_TEXTURE
            HammerheadSharkEntity.Type.BROWN -> BROWN_TEXTURE
            HammerheadSharkEntity.Type.OLIVE -> OLIVE_TEXTURE
        }
    }
}