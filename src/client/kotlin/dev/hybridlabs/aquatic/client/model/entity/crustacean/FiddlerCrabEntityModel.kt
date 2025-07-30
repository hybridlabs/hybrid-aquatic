package dev.hybridlabs.aquatic.client.model.entity.crustacean

import dev.hybridlabs.aquatic.entity.crustacean.FiddlerCrabEntity
import net.minecraft.util.Identifier
import kotlin.random.Random

class FiddlerCrabEntityModel : HybridAquaticCrustaceanEntityModel<FiddlerCrabEntity>("fiddler_crab") {

    private val commonTextures = listOf(
        Identifier("hybrid-aquatic", "textures/entity/crustacean/fiddler_crab/fiddler_crab_blue.png"),
        Identifier("hybrid-aquatic", "textures/entity/crustacean/fiddler_crab/fiddler_crab_purple.png"),
        Identifier("hybrid-aquatic", "textures/entity/crustacean/fiddler_crab/fiddler_crab_red.png")
    )

    override fun getTextureResource(animatable: FiddlerCrabEntity): Identifier {
        val seed = animatable.uuid.leastSignificantBits
        val random = Random(seed)
        return when (animatable.variant) {
            FiddlerCrabEntity.Type.COMMON -> commonTextures[random.nextInt(commonTextures.size)]
        }
    }
}