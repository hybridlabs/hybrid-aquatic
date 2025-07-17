package dev.hybridlabs.aquatic.client.model.entity.crustacean

import dev.hybridlabs.aquatic.entity.crustacean.DecoratorCrabEntity
import net.minecraft.util.Identifier

class DecoratorCrabEntityModel : HybridAquaticCrustaceanEntityModel<DecoratorCrabEntity>("decorator_crab") {

    private val BRAIN_TEXTURE = Identifier("hybrid-aquatic", "textures/entity/crustacean/decorator_crab/decorator_crab_brain.png")
    private val BUBBLE_TEXTURE = Identifier("hybrid-aquatic", "textures/entity/crustacean/decorator_crab/decorator_crab_bubble.png")
    private val BUTTON_TEXTURE = Identifier("hybrid-aquatic", "textures/entity/crustacean/decorator_crab/decorator_crab_button.png")
    private val FIRE_TEXTURE = Identifier("hybrid-aquatic", "textures/entity/crustacean/decorator_crab/decorator_crab_fire.png")
    private val HORN_TEXTURE = Identifier("hybrid-aquatic", "textures/entity/crustacean/decorator_crab/decorator_crab_horn.png")
    private val LOPHELIA_TEXTURE = Identifier("hybrid-aquatic", "textures/entity/crustacean/decorator_crab/decorator_crab_lophelia.png")
    private val SUN_TEXTURE = Identifier("hybrid-aquatic", "textures/entity/crustacean/decorator_crab/decorator_crab_sun.png")
    private val THORN_TEXTURE = Identifier("hybrid-aquatic", "textures/entity/crustacean/decorator_crab/decorator_crab_thorn.png")
    private val TUBE_TEXTURE = Identifier("hybrid-aquatic", "textures/entity/crustacean/decorator_crab/decorator_crab_tube.png")

    private val DECORATED_MODEL = Identifier("hybrid-aquatic", "geo/crustacean/decorator_crab/decorator_crab.geo.json")
    private val UNDECORATED_MODEL = Identifier("hybrid-aquatic", "geo/crustacean/decorator_crab/undecorated_decorator_crab.geo.json")

    override fun getTextureResource(animatable: DecoratorCrabEntity): Identifier {
        return when (animatable.variant) {
            DecoratorCrabEntity.Type.BRAIN -> BRAIN_TEXTURE
            DecoratorCrabEntity.Type.BUBBLE -> BUBBLE_TEXTURE
            DecoratorCrabEntity.Type.BUTTON -> BUTTON_TEXTURE
            DecoratorCrabEntity.Type.FIRE -> FIRE_TEXTURE
            DecoratorCrabEntity.Type.HORN -> HORN_TEXTURE
            DecoratorCrabEntity.Type.LOPHELIA -> LOPHELIA_TEXTURE
            DecoratorCrabEntity.Type.SUN -> SUN_TEXTURE
            DecoratorCrabEntity.Type.THORN -> THORN_TEXTURE
            DecoratorCrabEntity.Type.TUBE -> TUBE_TEXTURE
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