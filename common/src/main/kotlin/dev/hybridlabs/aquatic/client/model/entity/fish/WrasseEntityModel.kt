package dev.hybridlabs.aquatic.client.model.entity.fish

import dev.hybridlabs.hapi.client.model.HAPIDataTickets
import dev.hybridlabs.hapi.client.model.getVariant
import com.geckolib.renderer.base.GeoRenderState
import dev.hybridlabs.aquatic.entity.fish.WrasseEntity
import dev.hybridlabs.hapi.client.model.entity.aquatic.BaseFishEntityModel
import net.minecraft.resources.Identifier

class WrasseEntityModel : BaseFishEntityModel<WrasseEntity>("hybrid_aquatic", "wrasse") {

    companion object {
        private val CALIFORNIA_SHEEPSHEAD_TEXTURE =
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/fish/wrasse/california_sheepshead.png")

        private val CALIFORNIA_SHEEPSHEAD_MODEL =
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "fish/wrasse/california_sheepshead")
    }

    override fun getTextureResource(renderState: GeoRenderState): Identifier {
        return when (renderState.getVariant(WrasseEntity.Companion.Type.entries)!!) {
            WrasseEntity.Companion.Type.CALIFORNIA_SHEEPSHEAD -> CALIFORNIA_SHEEPSHEAD_TEXTURE
        }
    }

    override fun getModelResource(renderState: GeoRenderState): Identifier {
        return when (renderState.getVariant(WrasseEntity.Companion.Type.entries)!!) {
            WrasseEntity.Companion.Type.CALIFORNIA_SHEEPSHEAD -> CALIFORNIA_SHEEPSHEAD_MODEL
        }
    }
}
