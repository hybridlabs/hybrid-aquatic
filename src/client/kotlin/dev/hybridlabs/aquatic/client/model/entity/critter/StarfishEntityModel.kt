package dev.hybridlabs.aquatic.client.model.entity.critter

import dev.hybridlabs.aquatic.entity.critter.StarfishEntity
import net.minecraft.util.Identifier

class StarfishEntityModel : HybridAquaticCritterEntityModel<StarfishEntity>("starfish") {

    private val BRITTLESTAR_BLACK_TEXTURE = Identifier("hybrid-aquatic", "textures/entity/critter/starfish/starfish_brittlestar_black.png")
    private val BRITTLESTAR_WHITE_TEXTURE = Identifier("hybrid-aquatic", "textures/entity/critter/starfish/starfish_brittlestar_white.png")
    private val BRITTLESTAR_YELLOW_TEXTURE = Identifier("hybrid-aquatic", "textures/entity/critter/starfish/starfish_brittlestar_yellow.png")
    private val CROWN_OF_THORNS_TEXTURE = Identifier("hybrid-aquatic", "textures/entity/critter/starfish/starfish_crown_of_thorns.png")
    private val RED_TEXTURE = Identifier("hybrid-aquatic", "textures/entity/critter/starfish/starfish_red.png")
    private val MEDIUM_RED_TEXTURE = Identifier("hybrid-aquatic", "textures/entity/critter/starfish/starfish_medium_red.png")
    private val KNOBBED_RED_TEXTURE = Identifier("hybrid-aquatic", "textures/entity/critter/starfish/starfish_medium_knobbed_red.png")
    private val ORANGE_TEXTURE = Identifier("hybrid-aquatic", "textures/entity/critter/starfish/starfish_orange.png")
    private val MEDIUM_ORANGE_TEXTURE = Identifier("hybrid-aquatic", "textures/entity/critter/starfish/starfish_medium_orange.png")
    private val KNOBBED_ORANGE_TEXTURE = Identifier("hybrid-aquatic", "textures/entity/critter/starfish/starfish_medium_knobbed_orange.png")
    private val YELLOW_TEXTURE = Identifier("hybrid-aquatic", "textures/entity/critter/starfish/starfish_yellow.png")
    private val MEDIUM_YELLOW_TEXTURE = Identifier("hybrid-aquatic", "textures/entity/critter/starfish/starfish_medium_yellow.png")
    private val KNOBBED_YELLOW_TEXTURE = Identifier("hybrid-aquatic", "textures/entity/critter/starfish/starfish_medium_knobbed_yellow.png")
    private val GREEN_TEXTURE = Identifier("hybrid-aquatic", "textures/entity/critter/starfish/starfish_green.png")
    private val MEDIUM_GREEN_TEXTURE = Identifier("hybrid-aquatic", "textures/entity/critter/starfish/starfish_medium_green.png")
    private val KNOBBED_GREEN_TEXTURE = Identifier("hybrid-aquatic", "textures/entity/critter/starfish/starfish_medium_knobbed_green.png")
    private val BLUE_TEXTURE = Identifier("hybrid-aquatic", "textures/entity/critter/starfish/starfish_blue.png")
    private val MEDIUM_BLUE_TEXTURE = Identifier("hybrid-aquatic", "textures/entity/critter/starfish/starfish_medium_blue.png")
    private val KNOBBED_BLUE_TEXTURE = Identifier("hybrid-aquatic", "textures/entity/critter/starfish/starfish_medium_knobbed_blue.png")
    private val PURPLE_TEXTURE = Identifier("hybrid-aquatic", "textures/entity/critter/starfish/starfish_purple.png")
    private val MEDIUM_PURPLE_TEXTURE = Identifier("hybrid-aquatic", "textures/entity/critter/starfish/starfish_medium_purple.png")
    private val KNOBBED_PURPLE_TEXTURE = Identifier("hybrid-aquatic", "textures/entity/critter/starfish/starfish_medium_knobbed_purple.png")

    private val STARFISH_MODEL = Identifier("hybrid-aquatic", "geo/critter/starfish/starfish.geo.json")
    private val BRITTLESTAR_MODEL = Identifier("hybrid-aquatic", "geo/critter/starfish/starfish_brittlestar.geo.json")
    private val CROWN_OF_THORNS_MODEL = Identifier("hybrid-aquatic", "geo/critter/starfish/starfish_crown_of_thorns.geo.json")

    private val STARFISH_ANIMATION = Identifier("hybrid-aquatic", "animations/starfish.animation.json")
    private val BRITTLESTAR_ANIMATION = Identifier("hybrid-aquatic", "animations/starfish_brittlestar.animation.json")
    private val CROWN_OF_THORNS_ANIMATION = Identifier("hybrid-aquatic", "animations/starfish_crown_of_thorns.animation.json")

    override fun getTextureResource(animatable: StarfishEntity): Identifier {
        return when (animatable.variant) {
            StarfishEntity.Type.BRITTLESTAR_BLACK -> BRITTLESTAR_BLACK_TEXTURE
            StarfishEntity.Type.BRITTLESTAR_WHITE -> BRITTLESTAR_WHITE_TEXTURE
            StarfishEntity.Type.BRITTLESTAR_YELLOW -> BRITTLESTAR_YELLOW_TEXTURE
            StarfishEntity.Type.CROWN_OF_THORNS -> CROWN_OF_THORNS_TEXTURE
            StarfishEntity.Type.RED -> RED_TEXTURE
            StarfishEntity.Type.MEDIUM_RED -> MEDIUM_RED_TEXTURE
            StarfishEntity.Type.MEDIUM_KNOBBED_RED -> KNOBBED_RED_TEXTURE
            StarfishEntity.Type.ORANGE -> ORANGE_TEXTURE
            StarfishEntity.Type.MEDIUM_ORANGE -> MEDIUM_ORANGE_TEXTURE
            StarfishEntity.Type.MEDIUM_KNOBBED_ORANGE -> KNOBBED_ORANGE_TEXTURE
            StarfishEntity.Type.YELLOW -> YELLOW_TEXTURE
            StarfishEntity.Type.MEDIUM_YELLOW -> MEDIUM_YELLOW_TEXTURE
            StarfishEntity.Type.MEDIUM_KNOBBED_YELLOW -> KNOBBED_YELLOW_TEXTURE
            StarfishEntity.Type.GREEN -> GREEN_TEXTURE
            StarfishEntity.Type.MEDIUM_GREEN -> MEDIUM_GREEN_TEXTURE
            StarfishEntity.Type.MEDIUM_KNOBBED_GREEN -> KNOBBED_GREEN_TEXTURE
            StarfishEntity.Type.BLUE -> BLUE_TEXTURE
            StarfishEntity.Type.MEDIUM_BLUE -> MEDIUM_BLUE_TEXTURE
            StarfishEntity.Type.MEDIUM_KNOBBED_BLUE -> KNOBBED_BLUE_TEXTURE
            StarfishEntity.Type.PURPLE -> PURPLE_TEXTURE
            StarfishEntity.Type.MEDIUM_PURPLE -> MEDIUM_PURPLE_TEXTURE
            StarfishEntity.Type.MEDIUM_KNOBBED_PURPLE -> KNOBBED_PURPLE_TEXTURE
        }
    }

    override fun getModelResource(animatable: StarfishEntity): Identifier {
        return when (animatable.variant) {
            StarfishEntity.Type.BRITTLESTAR_BLACK -> BRITTLESTAR_MODEL
            StarfishEntity.Type.BRITTLESTAR_WHITE -> BRITTLESTAR_MODEL
            StarfishEntity.Type.BRITTLESTAR_YELLOW -> BRITTLESTAR_MODEL
            StarfishEntity.Type.CROWN_OF_THORNS -> CROWN_OF_THORNS_MODEL
            else -> STARFISH_MODEL
        }
    }

    override fun getAnimationResource(animatable: StarfishEntity): Identifier {
        return when (animatable.variant) {
            StarfishEntity.Type.BRITTLESTAR_BLACK -> BRITTLESTAR_ANIMATION
            StarfishEntity.Type.BRITTLESTAR_WHITE -> BRITTLESTAR_ANIMATION
            StarfishEntity.Type.BRITTLESTAR_YELLOW -> BRITTLESTAR_ANIMATION
            StarfishEntity.Type.CROWN_OF_THORNS -> CROWN_OF_THORNS_ANIMATION
            else -> STARFISH_ANIMATION
        }
    }
}