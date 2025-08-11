package dev.hybridlabs.aquatic.client.model.entity.crustacean

import dev.hybridlabs.aquatic.entity.crustacean.DecoratorCrabEntity
import net.minecraft.util.Identifier
import kotlin.random.Random

class DecoratorCrabEntityModel : HybridAquaticCrustaceanEntityModel<DecoratorCrabEntity>("decorator_crab") {

    private val coralTextures = listOf(
    Identifier.of("hybrid-aquatic", "textures/entity/crustacean/decorator_crab/decorator_crab_brain.png"),
    Identifier.of("hybrid-aquatic", "textures/entity/crustacean/decorator_crab/decorator_crab_bubble.png"),
    Identifier.of("hybrid-aquatic", "textures/entity/crustacean/decorator_crab/decorator_crab_button.png"),
    Identifier.of("hybrid-aquatic", "textures/entity/crustacean/decorator_crab/decorator_crab_fire.png"),
    Identifier.of("hybrid-aquatic", "textures/entity/crustacean/decorator_crab/decorator_crab_horn.png"),
    Identifier.of("hybrid-aquatic", "textures/entity/crustacean/decorator_crab/decorator_crab_lophelia.png"),
    Identifier.of("hybrid-aquatic", "textures/entity/crustacean/decorator_crab/decorator_crab_sun.png"),
    Identifier.of("hybrid-aquatic", "textures/entity/crustacean/decorator_crab/decorator_crab_thorn.png"),
    Identifier.of("hybrid-aquatic", "textures/entity/crustacean/decorator_crab/decorator_crab_tube.png")
    )

    private val DECORATED_MODEL = Identifier.of("hybrid-aquatic", "geo/crustacean/decorator_crab/decorator_crab.geo.json")
    private val UNDECORATED_MODEL = Identifier.of("hybrid-aquatic", "geo/crustacean/decorator_crab/undecorated_decorator_crab.geo.json")

    override fun getTextureResource(animatable: DecoratorCrabEntity): Identifier {
        val seed = animatable.uuid.leastSignificantBits
        val random = Random(seed)
        return when (animatable.variant) {
            DecoratorCrabEntity.Companion.Type.CORAL -> coralTextures[random.nextInt(coralTextures.size)]
        }
    }

    override fun getModelResource(animatable: DecoratorCrabEntity): Identifier {
        return if (animatable.coralTimer > 0) {
            UNDECORATED_MODEL
        } else {
            DECORATED_MODEL
        }
    }
}