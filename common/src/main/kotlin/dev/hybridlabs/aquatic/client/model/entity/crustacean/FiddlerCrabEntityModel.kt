package dev.hybridlabs.aquatic.client.model.entity.crustacean

import dev.hybridlabs.aquatic.entity.crustacean.FiddlerCrabEntity
import net.minecraft.resources.ResourceLocation
import kotlin.random.Random

class FiddlerCrabEntityModel : HACrustaceanEntityModel<FiddlerCrabEntity>("fiddler_crab") {

    companion object {
        private val commonTextures = listOf(
            ResourceLocation("hybrid-aquatic", "textures/entity/crustacean/fiddler_crab/fiddler_crab_blue.png"),
            ResourceLocation("hybrid-aquatic", "textures/entity/crustacean/fiddler_crab/fiddler_crab_purple.png"),
            ResourceLocation("hybrid-aquatic", "textures/entity/crustacean/fiddler_crab/fiddler_crab_red.png")
        )
    }

    override fun getTextureResource(animatable: FiddlerCrabEntity): ResourceLocation {
        val seed = animatable.uuid.leastSignificantBits
        val random = Random(seed)
        return commonTextures[random.nextInt(commonTextures.size)]
    }
}