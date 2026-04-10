package dev.hybridlabs.aquatic.client.model.entity.crustacean

import dev.hybridlabs.aquatic.entity.crustacean.ShrimpEntity
import net.minecraft.resources.ResourceLocation
import kotlin.random.Random

class ShrimpEntityModel : HACrustaceanEntityModel<ShrimpEntity>("shrimp") {

    companion object {
        private val commonTextures = listOf(
            ResourceLocation("hybrid-aquatic", "textures/entity/crustacean/shrimp/shrimp_black.png"),
            ResourceLocation("hybrid-aquatic", "textures/entity/crustacean/shrimp/shrimp_blue.png"),
            ResourceLocation("hybrid-aquatic", "textures/entity/crustacean/shrimp/shrimp_brown.png"),
            ResourceLocation("hybrid-aquatic", "textures/entity/crustacean/shrimp/shrimp_green.png"),
            ResourceLocation("hybrid-aquatic", "textures/entity/crustacean/shrimp/shrimp_lime.png"),
            ResourceLocation("hybrid-aquatic", "textures/entity/crustacean/shrimp/shrimp_orange.png"),
            ResourceLocation("hybrid-aquatic", "textures/entity/crustacean/shrimp/shrimp_pink.png"),
            ResourceLocation("hybrid-aquatic", "textures/entity/crustacean/shrimp/shrimp_red.png"),
            ResourceLocation("hybrid-aquatic", "textures/entity/crustacean/shrimp/shrimp_white.png"),
            ResourceLocation("hybrid-aquatic", "textures/entity/crustacean/shrimp/shrimp_yellow.png"),
        )
    }

    override fun getTextureResource(animatable: ShrimpEntity): ResourceLocation {
        val seed = animatable.uuid.leastSignificantBits
        val random = Random(seed)
        return commonTextures[random.nextInt(commonTextures.size)]
    }
}