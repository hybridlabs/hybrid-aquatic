package dev.hybridlabs.aquatic.client.model.entity.crustacean

import dev.hybridlabs.aquatic.entity.crustacean.DecoratorCrabEntity
import net.minecraft.resources.ResourceLocation
import kotlin.random.Random

class DecoratorCrabEntityModel : HACrustaceanEntityModel<DecoratorCrabEntity>("decorator_crab") {

    companion object {
        private val coralTextures = listOf(
            ResourceLocation("hybrid-aquatic", "textures/entity/crustacean/decorator_crab/decorator_crab_brain.png"),
            ResourceLocation("hybrid-aquatic", "textures/entity/crustacean/decorator_crab/decorator_crab_bubble.png"),
            ResourceLocation("hybrid-aquatic", "textures/entity/crustacean/decorator_crab/decorator_crab_button.png"),
            ResourceLocation("hybrid-aquatic", "textures/entity/crustacean/decorator_crab/decorator_crab_rose.png"),
            ResourceLocation("hybrid-aquatic", "textures/entity/crustacean/decorator_crab/decorator_crab_leaf.png"),
            ResourceLocation("hybrid-aquatic", "textures/entity/crustacean/decorator_crab/decorator_crab_bamboo.png"),
            ResourceLocation("hybrid-aquatic", "textures/entity/crustacean/decorator_crab/decorator_crab_zigzag.png"),
            ResourceLocation("hybrid-aquatic", "textures/entity/crustacean/decorator_crab/decorator_crab_fire.png"),
            ResourceLocation("hybrid-aquatic", "textures/entity/crustacean/decorator_crab/decorator_crab_horn.png"),
            ResourceLocation("hybrid-aquatic", "textures/entity/crustacean/decorator_crab/decorator_crab_lophelia.png"),
            ResourceLocation("hybrid-aquatic", "textures/entity/crustacean/decorator_crab/decorator_crab_sun.png"),
            ResourceLocation("hybrid-aquatic", "textures/entity/crustacean/decorator_crab/decorator_crab_thorn.png"),
            ResourceLocation("hybrid-aquatic", "textures/entity/crustacean/decorator_crab/decorator_crab_tube.png")
        )

        private val DECORATED_MODEL = ResourceLocation("hybrid-aquatic", "geo/crustacean/decorator_crab/decorator_crab.geo.json")
        private val UNDECORATED_MODEL = ResourceLocation("hybrid-aquatic", "geo/crustacean/decorator_crab/undecorated_decorator_crab.geo.json")

    }

    override fun getTextureResource(animatable: DecoratorCrabEntity): ResourceLocation {
        val seed = animatable.uuid.leastSignificantBits
        val random = Random(seed)
        return when (animatable.variant) {
            DecoratorCrabEntity.Companion.Type.CORAL -> coralTextures[random.nextInt(coralTextures.size)]
        }
    }

    override fun getModelResource(animatable: DecoratorCrabEntity): ResourceLocation {
        return if (animatable.coralTimer > 0) {
            UNDECORATED_MODEL
        } else {
            DECORATED_MODEL
        }
    }
}