package dev.hybridlabs.aquatic.client.model.entity.fish

import dev.hybridlabs.aquatic.entity.fish.RockfishEntity
import net.minecraft.util.Identifier

class RockfishEntityModel : HybridAquaticFishEntityModel<RockfishEntity>("rockfish") {

    private val VERMILION_TEXTURE = Identifier("hybrid-aquatic", "textures/entity/fish/rockfish/vermilion_rockfish.png")
    private val COPPER_TEXTURE = Identifier("hybrid-aquatic", "textures/entity/fish/rockfish/copper_rockfish.png")
    private val YELLOWEYE_TEXTURE = Identifier("hybrid-aquatic", "textures/entity/fish/rockfish/yelloweye_rockfish.png")

    override fun getTextureResource(animatable: RockfishEntity): Identifier {
        return when (animatable.variant) {
            RockfishEntity.Type.VERMILION -> VERMILION_TEXTURE
            RockfishEntity.Type.COPPER -> COPPER_TEXTURE
            RockfishEntity.Type.YELLOWEYE -> YELLOWEYE_TEXTURE
        }
    }
}
