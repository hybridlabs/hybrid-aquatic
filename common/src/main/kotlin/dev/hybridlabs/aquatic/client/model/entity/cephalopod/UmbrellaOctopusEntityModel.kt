package dev.hybridlabs.aquatic.client.model.entity.cephalopod

import dev.hybridlabs.hapi.client.model.HAPIDataTickets
import com.geckolib.renderer.base.GeoRenderState
import dev.hybridlabs.aquatic.entity.cephalopod.UmbrellaOctopusEntity
import dev.hybridlabs.hapi.client.model.entity.aquatic.BaseOctopusEntityModel
import net.minecraft.resources.Identifier
import kotlin.random.Random

class UmbrellaOctopusEntityModel : BaseOctopusEntityModel<UmbrellaOctopusEntity>("hybrid_aquatic","umbrella_octopus") {

    companion object {
        private val commonTextures = listOf(
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/cephalopod/umbrella_octopus/umbrella_octopus_yellow.png"),
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/cephalopod/umbrella_octopus/umbrella_octopus_brown.png"),
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/cephalopod/umbrella_octopus/umbrella_octopus_orange.png"),
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/cephalopod/umbrella_octopus/umbrella_octopus_pink.png"),
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/cephalopod/umbrella_octopus/umbrella_octopus_purple.png"),
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/cephalopod/umbrella_octopus/umbrella_octopus_white.png"),
        )
    }

    override fun getTextureResource(renderState: GeoRenderState): Identifier {
        val seed = (renderState.getGeckolibData(HAPIDataTickets.ENTITY_UUID)!!).leastSignificantBits
        val random = Random(seed)
        return commonTextures[random.nextInt(commonTextures.size)]
    }
}
