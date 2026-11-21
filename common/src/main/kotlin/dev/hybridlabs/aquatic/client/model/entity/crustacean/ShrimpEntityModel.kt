package dev.hybridlabs.aquatic.client.model.entity.crustacean

import dev.hybridlabs.hapi.client.model.HAPIDataTickets
import software.bernie.geckolib.renderer.base.GeoRenderState
import dev.hybridlabs.aquatic.entity.crustacean.ShrimpEntity
import dev.hybridlabs.hapi.client.model.entity.aquatic.BaseCrustaceanEntityModel
import net.minecraft.resources.Identifier
import kotlin.random.Random

class ShrimpEntityModel : BaseCrustaceanEntityModel<ShrimpEntity>("hybrid_aquatic", "shrimp") {

    companion object {
        private val commonTextures = listOf(
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/crustacean/shrimp/shrimp_black.png"),
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/crustacean/shrimp/shrimp_blue.png"),
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/crustacean/shrimp/shrimp_brown.png"),
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/crustacean/shrimp/shrimp_green.png"),
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/crustacean/shrimp/shrimp_lime.png"),
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/crustacean/shrimp/shrimp_orange.png"),
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/crustacean/shrimp/shrimp_pink.png"),
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/crustacean/shrimp/shrimp_red.png"),
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/crustacean/shrimp/shrimp_white.png"),
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/crustacean/shrimp/shrimp_yellow.png"),
        )
    }

    override fun getTextureResource(renderState: GeoRenderState): Identifier {
        val seed = (renderState.getGeckolibData(HAPIDataTickets.ENTITY_UUID)!!).leastSignificantBits
        val random = Random(seed)
        return commonTextures[random.nextInt(commonTextures.size)]
    }
}
