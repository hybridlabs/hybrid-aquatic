package dev.hybridlabs.aquatic.client.model.entity.shark

import dev.hybridlabs.hapi.client.model.HAPIDataTickets
import software.bernie.geckolib.renderer.base.GeoRenderState
import dev.hybridlabs.aquatic.entity.shark.WhaleSharkEntity
import dev.hybridlabs.hapi.client.model.entity.aquatic.BaseSharkEntityModel
import net.minecraft.resources.Identifier
import kotlin.random.Random

class WhaleSharkEntityModel : BaseSharkEntityModel<WhaleSharkEntity>("hybrid_aquatic", "whale_shark") {

    companion object {
        private val commonTextures = listOf(
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/shark/whale_shark/whale_shark.png"),
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/shark/whale_shark/whale_shark_brown.png"),
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/shark/whale_shark/whale_shark_gray.png")
        )
    }

    override fun getTextureResource(renderState: GeoRenderState): Identifier {
        val seed = (renderState.getGeckolibData(HAPIDataTickets.ENTITY_UUID)!!).leastSignificantBits
        val random = Random(seed)
        return commonTextures[random.nextInt(commonTextures.size)]
    }
}
