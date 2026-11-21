package dev.hybridlabs.aquatic.client.model.entity.fish

import dev.hybridlabs.hapi.client.model.HAPIDataTickets
import software.bernie.geckolib.renderer.base.GeoRenderState
import dev.hybridlabs.aquatic.entity.fish.TrevallyEntity
import dev.hybridlabs.hapi.client.model.entity.aquatic.BaseFishEntityModel
import net.minecraft.resources.Identifier
import kotlin.random.Random

class TrevallyEntityModel : BaseFishEntityModel<TrevallyEntity>("hybrid_aquatic", "trevally") {

    override fun getTextureResource(renderState: GeoRenderState): Identifier {
        val seed = (renderState.getGeckolibData(HAPIDataTickets.ENTITY_UUID)!!).leastSignificantBits
        val random = Random(seed)
        return pilotfishTextures[random.nextInt(pilotfishTextures.size)]
    }

    companion object {
        private val pilotfishTextures = listOf(
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/fish/trevally/pilotfish_yellow.png"),
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/fish/trevally/pilotfish_white.png"),
        )
    }
}
