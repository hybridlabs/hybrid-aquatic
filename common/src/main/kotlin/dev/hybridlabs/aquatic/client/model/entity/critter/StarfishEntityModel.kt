package dev.hybridlabs.aquatic.client.model.entity.critter

import dev.hybridlabs.hapi.client.model.HAPIDataTickets
import dev.hybridlabs.hapi.client.model.getVariant
import com.geckolib.renderer.base.GeoRenderState
import dev.hybridlabs.aquatic.entity.critter.StarfishEntity
import dev.hybridlabs.hapi.client.model.entity.aquatic.BaseCritterEntityModel
import net.minecraft.resources.Identifier
import kotlin.random.Random

class StarfishEntityModel : BaseCritterEntityModel<StarfishEntity>("hybrid_aquatic", "starfish") {

    companion object {
        private val brittlestarTextures = listOf(
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/critter/starfish/starfish_brittlestar_black.png"),
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/critter/starfish/starfish_brittlestar_white.png"),
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/critter/starfish/starfish_brittlestar_yellow.png"),
        )

        private val mediumTextures = listOf(
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/critter/starfish/starfish_medium.png"),
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/critter/starfish/starfish_medium_knobbed.png")
        )

        private val SMALL_TEXTURE =
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/critter/starfish/starfish_small.png")

        private val CROWN_OF_THORNS_TEXTURE =
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/critter/starfish/starfish_crown_of_thorns.png")

        private val STARFISH_MODEL =
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "critter/starfish/starfish")
        private val BRITTLESTAR_MODEL =
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "critter/starfish/starfish_brittlestar")
        private val CROWN_OF_THORNS_MODEL =
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "critter/starfish/starfish_crown_of_thorns")

        private val STARFISH_ANIMATION =
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "entity/critter/starfish/starfish")
        private val BRITTLESTAR_ANIMATION =
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "entity/critter/starfish/starfish_brittlestar")
        private val CROWN_OF_THORNS_ANIMATION =
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "entity/critter/starfish/starfish_crown_of_thorns")
    }

    override fun getTextureResource(renderState: GeoRenderState): Identifier {
        val seed = (renderState.getGeckolibData(HAPIDataTickets.ENTITY_UUID)!!).leastSignificantBits
        val random = Random(seed)
        return when (renderState.getVariant(StarfishEntity.Companion.Type.entries)!!) {
            StarfishEntity.Companion.Type.CROWN_OF_THORNS -> CROWN_OF_THORNS_TEXTURE
            StarfishEntity.Companion.Type.BRITTLESTAR -> brittlestarTextures[random.nextInt(brittlestarTextures.size)]
            StarfishEntity.Companion.Type.SMALL -> SMALL_TEXTURE
            StarfishEntity.Companion.Type.MEDIUM -> mediumTextures[random.nextInt(mediumTextures.size)]
        }
    }

    override fun getModelResource(renderState: GeoRenderState): Identifier {
        return when (renderState.getVariant(StarfishEntity.Companion.Type.entries)!!) {
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
