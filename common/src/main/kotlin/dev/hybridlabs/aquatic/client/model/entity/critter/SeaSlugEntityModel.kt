package dev.hybridlabs.aquatic.client.model.entity.critter

import dev.hybridlabs.hapi.client.model.HAPIDataTickets
import dev.hybridlabs.hapi.client.model.getVariant
import software.bernie.geckolib.renderer.base.GeoRenderState
import dev.hybridlabs.aquatic.entity.critter.SeaSlugEntity
import dev.hybridlabs.hapi.client.model.entity.aquatic.BaseCritterEntityModel
import net.minecraft.resources.Identifier
import kotlin.random.Random

class SeaSlugEntityModel : BaseCritterEntityModel<SeaSlugEntity>("hybrid_aquatic", "sea_slug") {

    companion object {
        private val SPOTTED_SEA_HARE_TEXTURE =
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/critter/sea_slug/spotted_sea_hare.png")

        private val nudibranchTextures = listOf(
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/critter/sea_slug/nudibranch_baba.png"),
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/critter/sea_slug/nudibranch_bullock.png"),
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/critter/sea_slug/nudibranch_festiva.png"),
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/critter/sea_slug/nudibranch_kubaryana.png"),
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/critter/sea_slug/nudibranch_kuniei.png"),
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/critter/sea_slug/nudibranch_magnificent.png"),
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/critter/sea_slug/nudibranch_pyjama.png"),
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/critter/sea_slug/nudibranch_sagami.png"),
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/critter/sea_slug/nudibranch_yonowae.png")
        )

        private val SPOTTED_SEA_HARE_MODEL =
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "critter/sea_slug/sea_hare")
        private val NUDIBRANCH_MODEL =
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "critter/sea_slug/nudibranch")

        private val SEA_HARE_ANIMATION =
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "entity/critter/sea_slug/sea_slug")
        private val NUDIBRANCH_ANIMATION =
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "entity/critter/sea_slug/nudibranch")
    }

    override fun getTextureResource(renderState: GeoRenderState): Identifier {
        val seed = (renderState.getGeckolibData(HAPIDataTickets.ENTITY_UUID)!!).leastSignificantBits
        val random = Random(seed)
        return when (renderState.getVariant(SeaSlugEntity.Companion.Type.entries)!!) {
            SeaSlugEntity.Companion.Type.SEA_HARE -> SPOTTED_SEA_HARE_TEXTURE
            SeaSlugEntity.Companion.Type.NUDIBRANCH -> nudibranchTextures[random.nextInt(nudibranchTextures.size)]
        }
    }

    override fun getModelResource(renderState: GeoRenderState): Identifier {
        return when (renderState.getVariant(SeaSlugEntity.Companion.Type.entries)!!) {
            SeaSlugEntity.Companion.Type.SEA_HARE -> SPOTTED_SEA_HARE_MODEL
            SeaSlugEntity.Companion.Type.NUDIBRANCH -> NUDIBRANCH_MODEL
        }
    }

    override fun getAnimationResource(animatable: SeaSlugEntity): Identifier {
        return when (animatable.variant) {
            SeaSlugEntity.Companion.Type.SEA_HARE -> SEA_HARE_ANIMATION
            SeaSlugEntity.Companion.Type.NUDIBRANCH -> NUDIBRANCH_ANIMATION
        }
    }
}
