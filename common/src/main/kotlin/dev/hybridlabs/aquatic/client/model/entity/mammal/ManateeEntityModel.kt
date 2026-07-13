package dev.hybridlabs.aquatic.client.model.entity.mammal

import dev.hybridlabs.aquatic.entity.mammal.ManateeEntity
import net.minecraft.resources.ResourceLocation
import kotlin.random.Random

class ManateeEntityModel : HASirenianEntityModel<ManateeEntity>("manatee") {

    override fun getTextureResource(animatable: ManateeEntity): ResourceLocation {
        val seed = animatable.uuid.leastSignificantBits
        val random = Random(seed)

        return if (animatable.isBaby) {
            BABY_TEXTURE
        } else {
            manateeTextures[random.nextInt(manateeTextures.size)]
        }
    }

    companion object {
        private val BABY_TEXTURE =
            ResourceLocation("hybrid_aquatic", "textures/entity/mammal/manatee/baby_manatee.png")

        private val manateeTextures = listOf(
            ResourceLocation("hybrid_aquatic", "textures/entity/mammal/manatee/manatee.png"),
            ResourceLocation("hybrid_aquatic", "textures/entity/mammal/manatee/mossy_manatee.png")
        )
    }
}