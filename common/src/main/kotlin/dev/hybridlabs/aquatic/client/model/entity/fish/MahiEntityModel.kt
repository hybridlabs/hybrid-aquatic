package dev.hybridlabs.aquatic.client.model.entity.fish

import dev.hybridlabs.hapi.client.model.HAPIDataTickets
import dev.hybridlabs.hapi.client.model.getVariant
import software.bernie.geckolib.renderer.base.GeoRenderState
import dev.hybridlabs.aquatic.entity.fish.MahiEntity
import dev.hybridlabs.hapi.client.model.entity.aquatic.BaseFishEntityModel
import net.minecraft.resources.Identifier
import kotlin.random.Random

class MahiEntityModel : BaseFishEntityModel<MahiEntity>("hybrid_aquatic", "mahi") {

    override fun getTextureResource(renderState: GeoRenderState): Identifier {
        val seed = (renderState.getGeckolibData(HAPIDataTickets.ENTITY_UUID)!!).leastSignificantBits
        val random = Random(seed)

        return when (renderState.getVariant(MahiEntity.Companion.Type.entries)!!) {
            MahiEntity.Companion.Type.MAHI -> mahiTextures[random.nextInt(mahiTextures.size)]
            MahiEntity.Companion.Type.POMPANO -> POMPANO_TEXTURE
        }
    }

    override fun getModelResource(renderState: GeoRenderState): Identifier {
        return when (renderState.getVariant(MahiEntity.Companion.Type.entries)!!) {
            MahiEntity.Companion.Type.MAHI -> MAHI_MODEL
            MahiEntity.Companion.Type.POMPANO -> POMPANO_MODEL
        }
    }

    companion object {
        private val POMPANO_TEXTURE =
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/fish/mahi/mahi_pompano.png")

        private val mahiTextures = listOf(
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/fish/mahi/mahi_mahi.png"),
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/fish/mahi/mahi_mahi_2.png"),
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/fish/mahi/mahi_mahi_3.png"),
        )

        private val MAHI_MODEL =
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "fish/mahi/mahi_mahi")
        private val POMPANO_MODEL =
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "fish/mahi/mahi_pompano")
    }
}
