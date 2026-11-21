package dev.hybridlabs.aquatic.client.model.entity.shark

import dev.hybridlabs.aquatic.entity.shark.GoblinSharkEntity
import com.geckolib.renderer.base.GeoRenderState
import dev.hybridlabs.hapi.client.model.HAPIDataTickets
import dev.hybridlabs.hapi.client.model.entity.aquatic.BaseSharkEntityModel
import net.minecraft.resources.Identifier

class GoblinSharkEntityModel : BaseSharkEntityModel<GoblinSharkEntity>("hybrid_aquatic", "goblin_shark") {

    override fun getTextureResource(renderState: GeoRenderState): Identifier =
        if ((renderState.getGeckolibData(HAPIDataTickets.MOISTNESS)!!) < 590) DEPRESSURIZED_TEXTURE else GOBLIN_SHARK_TEXTURE

    override fun getModelResource(renderState: GeoRenderState): Identifier =
        if ((renderState.getGeckolibData(HAPIDataTickets.MOISTNESS)!!) < 590) DEPRESSURIZED_MODEL else GOBLIN_SHARK_MODEL

    companion object {
        private val GOBLIN_SHARK_TEXTURE =
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/shark/goblin_shark/goblin_shark.png")
        private val DEPRESSURIZED_TEXTURE =
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/shark/goblin_shark/goblin_shark_depressurized.png")

        private val GOBLIN_SHARK_MODEL =
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "shark/goblin_shark/goblin_shark")
        private val DEPRESSURIZED_MODEL =
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "shark/goblin_shark/goblin_shark_depressurized")
    }
}