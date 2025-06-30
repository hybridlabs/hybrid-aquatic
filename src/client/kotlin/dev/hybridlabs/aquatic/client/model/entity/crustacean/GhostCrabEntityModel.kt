package dev.hybridlabs.aquatic.client.model.entity.crustacean

import dev.hybridlabs.aquatic.entity.crustacean.GhostCrabEntity
import net.minecraft.util.Identifier

class GhostCrabEntityModel : HybridAquaticCrustaceanEntityModel<GhostCrabEntity>("ghost_crab") {

    private val PURPLE_TEXTURE = Identifier("hybrid-aquatic", "textures/entity/crustacean/ghost_crab/ghost_crab_purple.png")
    private val RED_TEXTURE = Identifier("hybrid-aquatic", "textures/entity/crustacean/ghost_crab/ghost_crab_red.png")
    private val WHITE_TEXTURE = Identifier("hybrid-aquatic", "textures/entity/crustacean/ghost_crab/ghost_crab_white.png")
    private val YELLOW_TEXTURE = Identifier("hybrid-aquatic", "textures/entity/crustacean/ghost_crab/ghost_crab_yellow.png")

    override fun getTextureResource(animatable: GhostCrabEntity): Identifier {
        return when (animatable.variant) {
            GhostCrabEntity.Type.PURPLE -> PURPLE_TEXTURE
            GhostCrabEntity.Type.RED -> RED_TEXTURE
            GhostCrabEntity.Type.WHITE -> WHITE_TEXTURE
            GhostCrabEntity.Type.YELLOW -> YELLOW_TEXTURE
        }
    }
}