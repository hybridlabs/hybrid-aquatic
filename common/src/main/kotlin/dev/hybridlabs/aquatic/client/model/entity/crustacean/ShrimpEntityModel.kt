package dev.hybridlabs.aquatic.client.model.entity.crustacean

import dev.hybridlabs.aquatic.entity.crustacean.ShrimpEntity
import dev.hybridlabs.hapi.client.model.entity.aquatic.BaseCrustaceanEntityModel
import net.minecraft.resources.ResourceLocation
import kotlin.random.Random

class ShrimpEntityModel : BaseCrustaceanEntityModel<ShrimpEntity>("hybrid_aquatic", "shrimp") {

    companion object {
        private val commonTextures = listOf(
            ResourceLocation.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/crustacean/shrimp/shrimp_black.png"),
            ResourceLocation.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/crustacean/shrimp/shrimp_blue.png"),
            ResourceLocation.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/crustacean/shrimp/shrimp_brown.png"),
            ResourceLocation.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/crustacean/shrimp/shrimp_green.png"),
            ResourceLocation.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/crustacean/shrimp/shrimp_lime.png"),
            ResourceLocation.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/crustacean/shrimp/shrimp_orange.png"),
            ResourceLocation.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/crustacean/shrimp/shrimp_pink.png"),
            ResourceLocation.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/crustacean/shrimp/shrimp_red.png"),
            ResourceLocation.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/crustacean/shrimp/shrimp_white.png"),
            ResourceLocation.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/crustacean/shrimp/shrimp_yellow.png"),
        )
    }

    override fun getTextureResource(animatable: ShrimpEntity): ResourceLocation {
        val seed = animatable.uuid.leastSignificantBits
        val random = Random(seed)
        return commonTextures[random.nextInt(commonTextures.size)]
    }
}
