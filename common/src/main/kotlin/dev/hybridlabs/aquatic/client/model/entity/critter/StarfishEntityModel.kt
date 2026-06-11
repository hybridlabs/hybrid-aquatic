package dev.hybridlabs.aquatic.client.model.entity.critter

import dev.hybridlabs.aquatic.entity.critter.StarfishEntity
import net.minecraft.resources.ResourceLocation
import kotlin.random.Random

class StarfishEntityModel : HACritterEntityModel<StarfishEntity>("starfish") {

    companion object {
        private val brittlestarTextures = listOf(
            ResourceLocation.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/critter/starfish/starfish_brittlestar_black.png"),
            ResourceLocation.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/critter/starfish/starfish_brittlestar_white.png"),
            ResourceLocation.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/critter/starfish/starfish_brittlestar_yellow.png"),
        )

        private val mediumTextures = listOf(
            ResourceLocation.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/critter/starfish/starfish_medium.png"),
            ResourceLocation.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/critter/starfish/starfish_medium_knobbed.png")
        )

        private val SMALL_TEXTURE =
            ResourceLocation.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/critter/starfish/starfish_small.png")

        private val CROWN_OF_THORNS_TEXTURE =
            ResourceLocation.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/critter/starfish/starfish_crown_of_thorns.png")

        private val STARFISH_MODEL =
            ResourceLocation.fromNamespaceAndPath("hybrid_aquatic", "geo/critter/starfish/starfish.geo.json")
        private val BRITTLESTAR_MODEL =
            ResourceLocation.fromNamespaceAndPath("hybrid_aquatic", "geo/critter/starfish/starfish_brittlestar.geo.json")
        private val CROWN_OF_THORNS_MODEL =
            ResourceLocation.fromNamespaceAndPath("hybrid_aquatic", "geo/critter/starfish/starfish_crown_of_thorns.geo.json")

        private val STARFISH_ANIMATION =
            ResourceLocation.fromNamespaceAndPath("hybrid_aquatic", "animations/entity/critter/starfish/starfish.animation.json")
        private val BRITTLESTAR_ANIMATION =
            ResourceLocation.fromNamespaceAndPath("hybrid_aquatic", "animations/entity/critter/starfish/starfish_brittlestar.animation.json")
        private val CROWN_OF_THORNS_ANIMATION =
            ResourceLocation.fromNamespaceAndPath("hybrid_aquatic", "animations/entity/critter/starfish/starfish_crown_of_thorns.animation.json")
    }

    override fun getTextureResource(animatable: StarfishEntity): ResourceLocation {
        val seed = animatable.uuid.leastSignificantBits
        val random = Random(seed)
        return when (animatable.variant) {
            StarfishEntity.Companion.Type.CROWN_OF_THORNS -> CROWN_OF_THORNS_TEXTURE
            StarfishEntity.Companion.Type.BRITTLESTAR -> brittlestarTextures[random.nextInt(brittlestarTextures.size)]
            StarfishEntity.Companion.Type.SMALL -> SMALL_TEXTURE
            StarfishEntity.Companion.Type.MEDIUM -> mediumTextures[random.nextInt(mediumTextures.size)]
        }
    }

    override fun getModelResource(animatable: StarfishEntity): ResourceLocation {
        return when (animatable.variant) {
            StarfishEntity.Companion.Type.BRITTLESTAR -> BRITTLESTAR_MODEL
            StarfishEntity.Companion.Type.CROWN_OF_THORNS -> CROWN_OF_THORNS_MODEL
            else -> STARFISH_MODEL
        }
    }

    override fun getAnimationResource(animatable: StarfishEntity): ResourceLocation {
        return when (animatable.variant) {
            StarfishEntity.Companion.Type.BRITTLESTAR -> BRITTLESTAR_ANIMATION
            StarfishEntity.Companion.Type.CROWN_OF_THORNS -> CROWN_OF_THORNS_ANIMATION
            else -> STARFISH_ANIMATION
        }
    }
}
