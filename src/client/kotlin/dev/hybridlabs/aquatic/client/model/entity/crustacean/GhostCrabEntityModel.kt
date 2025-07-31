package dev.hybridlabs.aquatic.client.model.entity.crustacean

import dev.hybridlabs.aquatic.entity.crustacean.FiddlerCrabEntity
import dev.hybridlabs.aquatic.entity.crustacean.GhostCrabEntity
import net.minecraft.util.Identifier
import kotlin.random.Random

class GhostCrabEntityModel : HybridAquaticCrustaceanEntityModel<GhostCrabEntity>("ghost_crab") {

    private val commonTextures = listOf(
        Identifier("hybrid-aquatic", "textures/entity/crustacean/ghost_crab/ghost_crab_white.png"),
        Identifier("hybrid-aquatic", "textures/entity/crustacean/ghost_crab/ghost_crab_yellow.png"),
        Identifier("hybrid-aquatic", "textures/entity/crustacean/ghost_crab/ghost_crab_purple.png"),
        Identifier("hybrid-aquatic", "textures/entity/crustacean/ghost_crab/ghost_crab_red.png")
    )

    override fun getTextureResource(animatable: GhostCrabEntity): Identifier {
        val seed = animatable.uuid.leastSignificantBits
        val random = Random(seed)
        return commonTextures[random.nextInt(commonTextures.size)]
    }
}