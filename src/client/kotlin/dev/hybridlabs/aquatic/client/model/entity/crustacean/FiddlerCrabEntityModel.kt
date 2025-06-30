package dev.hybridlabs.aquatic.client.model.entity.crustacean

import dev.hybridlabs.aquatic.entity.crustacean.FiddlerCrabEntity
import net.minecraft.util.Identifier

class FiddlerCrabEntityModel : HybridAquaticCrustaceanEntityModel<FiddlerCrabEntity>("fiddler_crab") {

    private val BLUE_TEXTURE = Identifier("hybrid-aquatic", "textures/entity/crustacean/fiddler_crab/fiddler_crab_blue.png")
    private val PURPLE_TEXTURE = Identifier("hybrid-aquatic", "textures/entity/crustacean/fiddler_crab/fiddler_crab_purple.png")
    private val RED_TEXTURE = Identifier("hybrid-aquatic", "textures/entity/crustacean/fiddler_crab/fiddler_crab_red.png")

    override fun getTextureResource(animatable: FiddlerCrabEntity): Identifier {
        return when (animatable.variant) {
            FiddlerCrabEntity.Type.BLUE -> BLUE_TEXTURE
            FiddlerCrabEntity.Type.PURPLE -> PURPLE_TEXTURE
            FiddlerCrabEntity.Type.RED -> RED_TEXTURE
        }
    }
}