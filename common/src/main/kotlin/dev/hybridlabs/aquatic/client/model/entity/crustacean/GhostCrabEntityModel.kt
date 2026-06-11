package dev.hybridlabs.aquatic.client.model.entity.crustacean

import dev.hybridlabs.aquatic.entity.crustacean.GhostCrabEntity
import net.minecraft.resources.ResourceLocation
import kotlin.random.Random

class GhostCrabEntityModel : HACrustaceanEntityModel<GhostCrabEntity>("ghost_crab") {

    companion object {
        private val commonTextures = listOf(
            ResourceLocation.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/crustacean/ghost_crab/ghost_crab_white.png"),
            ResourceLocation.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/crustacean/ghost_crab/ghost_crab_yellow.png"),
            ResourceLocation.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/crustacean/ghost_crab/ghost_crab_purple.png"),
            ResourceLocation.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/crustacean/ghost_crab/ghost_crab_red.png")
        )
    }

    override fun getTextureResource(animatable: GhostCrabEntity): ResourceLocation {
        val seed = animatable.uuid.leastSignificantBits
        val random = Random(seed)
        return commonTextures[random.nextInt(commonTextures.size)]
    }
}
