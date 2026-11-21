package dev.hybridlabs.aquatic.client.model.entity.crustacean

import dev.hybridlabs.hapi.client.model.HAPIDataTickets
import dev.hybridlabs.hapi.client.model.getVariant
import com.geckolib.renderer.base.GeoRenderState
import dev.hybridlabs.aquatic.entity.crustacean.LobsterEntity
import dev.hybridlabs.hapi.client.model.entity.aquatic.BaseCrustaceanEntityModel
import net.minecraft.resources.Identifier
import kotlin.random.Random

class LobsterEntityModel : BaseCrustaceanEntityModel<LobsterEntity>("hybrid_aquatic", "lobster") {

    companion object {
        private val clawlessTextures = listOf(
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/crustacean/lobster/lobster_ornate_spiny.png"),
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/crustacean/lobster/lobster_california_spiny.png")
        )

        private val clawedTextures = listOf(
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/crustacean/lobster/lobster_american.png")
        )

        private val REGAL_SLIPPER_TEXTURE =
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/crustacean/lobster/lobster_regal_slipper.png")

        private val LOBSTER_MODEL =
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "crustacean/lobster/lobster")
        private val SLIPPER_LOBSTER_MODEL =
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "crustacean/lobster/lobster_slipper")
    }

    override fun getTextureResource(renderState: GeoRenderState): Identifier {
        val seed = (renderState.getGeckolibData(HAPIDataTickets.ENTITY_UUID)!!).leastSignificantBits
        val random = Random(seed)
        return when (renderState.getVariant(LobsterEntity.Companion.Type.entries)!!) {
            LobsterEntity.Companion.Type.CLAWED -> clawedTextures[random.nextInt(clawedTextures.size)]
            LobsterEntity.Companion.Type.CLAWLESS -> clawlessTextures[random.nextInt(clawlessTextures.size)]
            LobsterEntity.Companion.Type.REGAL_SLIPPER -> REGAL_SLIPPER_TEXTURE
        }
    }

    override fun getModelResource(renderState: GeoRenderState): Identifier {
        return when (renderState.getVariant(LobsterEntity.Companion.Type.entries)!!) {
            LobsterEntity.Companion.Type.REGAL_SLIPPER -> SLIPPER_LOBSTER_MODEL
            else -> LOBSTER_MODEL
        }
    }
}
