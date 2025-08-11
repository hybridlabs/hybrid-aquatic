package dev.hybridlabs.aquatic.client.model.entity.critter

import dev.hybridlabs.aquatic.entity.critter.StarfishEntity
import net.minecraft.util.Identifier
import kotlin.random.Random

class StarfishEntityModel : HybridAquaticCritterEntityModel<StarfishEntity>("starfish") {

    private val brittlestarTextures = listOf(
        Identifier.of("hybrid-aquatic", "textures/entity/critter/starfish/starfish_brittlestar_black.png"),
        Identifier.of("hybrid-aquatic", "textures/entity/critter/starfish/starfish_brittlestar_white.png"),
        Identifier.of("hybrid-aquatic", "textures/entity/critter/starfish/starfish_brittlestar_yellow.png"),
    )

    private val smallTextures = listOf(
        Identifier.of("hybrid-aquatic", "textures/entity/critter/starfish/starfish_blue.png"),
        Identifier.of("hybrid-aquatic", "textures/entity/critter/starfish/starfish_green.png"),
        Identifier.of("hybrid-aquatic", "textures/entity/critter/starfish/starfish_orange.png"),
        Identifier.of("hybrid-aquatic", "textures/entity/critter/starfish/starfish_purple.png"),
        Identifier.of("hybrid-aquatic", "textures/entity/critter/starfish/starfish_red.png"),
        Identifier.of("hybrid-aquatic", "textures/entity/critter/starfish/starfish_yellow.png"),
    )

    private val mediumTextures = listOf(
        Identifier.of("hybrid-aquatic", "textures/entity/critter/starfish/starfish_medium_blue.png"),
        Identifier.of("hybrid-aquatic", "textures/entity/critter/starfish/starfish_medium_green.png"),
        Identifier.of("hybrid-aquatic", "textures/entity/critter/starfish/starfish_medium_orange.png"),
        Identifier.of("hybrid-aquatic", "textures/entity/critter/starfish/starfish_medium_purple.png"),
        Identifier.of("hybrid-aquatic", "textures/entity/critter/starfish/starfish_medium_red.png"),
        Identifier.of("hybrid-aquatic", "textures/entity/critter/starfish/starfish_medium_yellow.png"),
        Identifier.of("hybrid-aquatic", "textures/entity/critter/starfish/starfish_medium_knobbed_blue.png"),
        Identifier.of("hybrid-aquatic", "textures/entity/critter/starfish/starfish_medium_knobbed_green.png"),
        Identifier.of("hybrid-aquatic", "textures/entity/critter/starfish/starfish_medium_knobbed_orange.png"),
        Identifier.of("hybrid-aquatic", "textures/entity/critter/starfish/starfish_medium_knobbed_purple.png"),
        Identifier.of("hybrid-aquatic", "textures/entity/critter/starfish/starfish_medium_knobbed_red.png"),
        Identifier.of("hybrid-aquatic", "textures/entity/critter/starfish/starfish_medium_knobbed_yellow.png"),
    )

    private val CROWN_OF_THORNS_TEXTURE = Identifier.of("hybrid-aquatic", "textures/entity/critter/starfish/starfish_crown_of_thorns.png")

    private val STARFISH_MODEL = Identifier.of("hybrid-aquatic", "geo/critter/starfish/starfish.geo.json")
    private val BRITTLESTAR_MODEL = Identifier.of("hybrid-aquatic", "geo/critter/starfish/starfish_brittlestar.geo.json")
    private val CROWN_OF_THORNS_MODEL = Identifier.of("hybrid-aquatic", "geo/critter/starfish/starfish_crown_of_thorns.geo.json")

    private val STARFISH_ANIMATION = Identifier.of("hybrid-aquatic", "animations/starfish.animation.json")
    private val BRITTLESTAR_ANIMATION = Identifier.of("hybrid-aquatic", "animations/starfish_brittlestar.animation.json")
    private val CROWN_OF_THORNS_ANIMATION = Identifier.of("hybrid-aquatic", "animations/starfish_crown_of_thorns.animation.json")

    override fun getTextureResource(animatable: StarfishEntity): Identifier {
        val seed = animatable.uuid.leastSignificantBits
        val random = Random(seed)
        return when (animatable.variant) {
            StarfishEntity.Companion.Type.CROWN_OF_THORNS -> CROWN_OF_THORNS_TEXTURE
            StarfishEntity.Companion.Type.BRITTLESTAR -> brittlestarTextures[random.nextInt(brittlestarTextures.size)]
            StarfishEntity.Companion.Type.SMALL -> smallTextures[random.nextInt(smallTextures.size)]
            StarfishEntity.Companion.Type.MEDIUM -> mediumTextures[random.nextInt(mediumTextures.size)]
        }
    }

    override fun getModelResource(animatable: StarfishEntity): Identifier {
        return when (animatable.variant) {
            StarfishEntity.Companion.Type.BRITTLESTAR -> BRITTLESTAR_MODEL
            StarfishEntity.Companion.Type.CROWN_OF_THORNS -> CROWN_OF_THORNS_MODEL
            else -> STARFISH_MODEL
        }
    }

    override fun getAnimationResource(animatable: StarfishEntity): Identifier {
        return when (animatable.variant) {
            StarfishEntity.Companion.Type.BRITTLESTAR -> BRITTLESTAR_ANIMATION
            StarfishEntity.Companion.Type.CROWN_OF_THORNS -> CROWN_OF_THORNS_ANIMATION
            else -> STARFISH_ANIMATION
        }
    }
}