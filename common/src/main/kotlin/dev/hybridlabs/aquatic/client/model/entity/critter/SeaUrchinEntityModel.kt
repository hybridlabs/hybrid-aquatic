package dev.hybridlabs.aquatic.client.model.entity.critter

import dev.hybridlabs.hapi.client.model.HAPIDataTickets
import dev.hybridlabs.hapi.client.model.getVariant
import com.geckolib.renderer.base.GeoRenderState
import dev.hybridlabs.aquatic.entity.critter.SeaUrchinEntity
import dev.hybridlabs.hapi.client.model.entity.aquatic.BaseCritterEntityModel
import net.minecraft.resources.Identifier
import kotlin.random.Random

class SeaUrchinEntityModel : BaseCritterEntityModel<SeaUrchinEntity>("hybrid_aquatic", "sea_urchin") {

    companion object {
        private val largeTextures = listOf(
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/critter/sea_urchin/sea_urchin_long_black.png"),
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/critter/sea_urchin/sea_urchin_long_blue.png"),
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/critter/sea_urchin/sea_urchin_long_purple.png"),
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/critter/sea_urchin/sea_urchin_long_red.png"),
        )

        private val smallTextures = listOf(
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/critter/sea_urchin/sea_urchin_black.png"),
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/critter/sea_urchin/sea_urchin_blue.png"),
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/critter/sea_urchin/sea_urchin_purple.png"),
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/critter/sea_urchin/sea_urchin_red.png"),
        )
    }

    override fun getTextureResource(renderState: GeoRenderState): Identifier {
        val seed = (renderState.getGeckolibData(HAPIDataTickets.ENTITY_UUID)!!).leastSignificantBits
        val random = Random(seed)
        return when (renderState.getVariant(SeaUrchinEntity.Companion.Type.entries)!!) {
            SeaUrchinEntity.Companion.Type.SMALL -> smallTextures[random.nextInt(smallTextures.size)]
            SeaUrchinEntity.Companion.Type.LARGE -> largeTextures[random.nextInt(largeTextures.size)]
        }
    }
}
