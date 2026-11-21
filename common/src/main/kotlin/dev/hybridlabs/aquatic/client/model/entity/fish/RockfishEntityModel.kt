package dev.hybridlabs.aquatic.client.model.entity.fish

import dev.hybridlabs.hapi.client.model.HAPIDataTickets
import dev.hybridlabs.hapi.client.model.getVariant
import software.bernie.geckolib.renderer.base.GeoRenderState
import dev.hybridlabs.aquatic.entity.fish.RockfishEntity
import dev.hybridlabs.hapi.client.model.entity.aquatic.BaseFishEntityModel
import net.minecraft.resources.Identifier

class RockfishEntityModel : BaseFishEntityModel<RockfishEntity>("hybrid_aquatic", "rockfish") {

    override fun getTextureResource(renderState: GeoRenderState): Identifier {
        return when (renderState.getVariant(RockfishEntity.Companion.Type.entries)!!) {
            RockfishEntity.Companion.Type.VERMILION -> VERMILION_TEXTURE
            RockfishEntity.Companion.Type.COPPER -> COPPER_TEXTURE
            RockfishEntity.Companion.Type.YELLOWEYE -> YELLOWEYE_TEXTURE
        }
    }

    companion object {
        private val VERMILION_TEXTURE =
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/fish/rockfish/vermilion_rockfish.png")
        private val COPPER_TEXTURE =
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/fish/rockfish/copper_rockfish.png")
        private val YELLOWEYE_TEXTURE =
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/fish/rockfish/yelloweye_rockfish.png")
    }
}
