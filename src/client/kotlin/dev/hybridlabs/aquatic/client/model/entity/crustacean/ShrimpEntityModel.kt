package dev.hybridlabs.aquatic.client.model.entity.crustacean

import dev.hybridlabs.aquatic.entity.crustacean.ShrimpEntity
import net.minecraft.util.Identifier
import kotlin.random.Random

class ShrimpEntityModel : HybridAquaticCrustaceanEntityModel<ShrimpEntity>("shrimp") {

    private val commonTextures = listOf(
        Identifier("hybrid-aquatic", "textures/entity/crustacean/shrimp/shrimp_black.png"),
        Identifier("hybrid-aquatic", "textures/entity/crustacean/shrimp/shrimp_blue.png"),
        Identifier("hybrid-aquatic", "textures/entity/crustacean/shrimp/shrimp_brown.png"),
        Identifier("hybrid-aquatic", "textures/entity/crustacean/shrimp/shrimp_green.png"),
        Identifier("hybrid-aquatic", "textures/entity/crustacean/shrimp/shrimp_lime.png"),
        Identifier("hybrid-aquatic", "textures/entity/crustacean/shrimp/shrimp_orange.png"),
        Identifier("hybrid-aquatic", "textures/entity/crustacean/shrimp/shrimp_pink.png"),
        Identifier("hybrid-aquatic", "textures/entity/crustacean/shrimp/shrimp_red.png"),
        Identifier("hybrid-aquatic", "textures/entity/crustacean/shrimp/shrimp_white.png"),
        Identifier("hybrid-aquatic", "textures/entity/crustacean/shrimp/shrimp_yellow.png"),
    )

    override fun getTextureResource(animatable: ShrimpEntity): Identifier {
        val seed = animatable.uuid.leastSignificantBits
        val random = Random(seed)
        return commonTextures[random.nextInt(commonTextures.size)]
    }
}