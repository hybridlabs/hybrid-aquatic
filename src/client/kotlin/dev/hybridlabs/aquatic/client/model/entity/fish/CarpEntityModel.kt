package dev.hybridlabs.aquatic.client.model.entity.fish

import dev.hybridlabs.aquatic.entity.fish.CarpEntity
import net.minecraft.util.Identifier

class CarpEntityModel : HybridAquaticFishEntityModel<CarpEntity>("carp") {

    private val COMMON_TEXTURE = Identifier("hybrid-aquatic", "textures/entity/fish/carp/carp.png")
    private val AI_GOROMO_TEXTURE = Identifier("hybrid-aquatic", "textures/entity/fish/carp/carp_ai_goromo.png")
    private val HAJIRO_TEXTURE = Identifier("hybrid-aquatic", "textures/entity/fish/carp/carp_hajiro.png")
    private val PLATINUM_TEXTURE = Identifier("hybrid-aquatic", "textures/entity/fish/carp/carp_platinum.png")
    private val TANCHO_TEXTURE = Identifier("hybrid-aquatic", "textures/entity/fish/carp/carp_tancho.png")

    override fun getTextureResource(animatable: CarpEntity): Identifier {
        return when (animatable.variant) {
            CarpEntity.Type.COMMON -> COMMON_TEXTURE
            CarpEntity.Type.AI_GOROMO -> AI_GOROMO_TEXTURE
            CarpEntity.Type.HAJIRO -> HAJIRO_TEXTURE
            CarpEntity.Type.PLATINUM -> PLATINUM_TEXTURE
            CarpEntity.Type.TANCHO -> TANCHO_TEXTURE
        }
    }
}

