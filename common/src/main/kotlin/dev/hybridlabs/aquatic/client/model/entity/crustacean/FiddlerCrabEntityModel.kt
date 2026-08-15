package dev.hybridlabs.aquatic.client.model.entity.crustacean

import dev.hybridlabs.aquatic.entity.crustacean.FiddlerCrabEntity
import dev.hybridlabs.hapi.client.model.entity.aquatic.BaseCrustaceanEntityModel
import net.minecraft.resources.ResourceLocation
import kotlin.random.Random

class FiddlerCrabEntityModel : BaseCrustaceanEntityModel<FiddlerCrabEntity>("hybrid_aquatic", "fiddler_crab") {

    companion object {
        private val commonTextures = listOf(
            ResourceLocation.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/crustacean/fiddler_crab/fiddler_crab_blue.png"),
            ResourceLocation.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/crustacean/fiddler_crab/fiddler_crab_purple.png"),
            ResourceLocation.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/crustacean/fiddler_crab/fiddler_crab_red.png")
        )
    }

    override fun getTextureResource(animatable: FiddlerCrabEntity): ResourceLocation {
        val seed = animatable.uuid.leastSignificantBits
        val random = Random(seed)
        return commonTextures[random.nextInt(commonTextures.size)]
    }
}
