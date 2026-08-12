package dev.hybridlabs.aquatic.client.model.entity.fish

import dev.hybridlabs.aquatic.entity.fish.RockfishEntity
import dev.hybridlabs.hapi.client.model.entity.BaseFishEntityModel
import net.minecraft.resources.ResourceLocation

class RockfishEntityModel : BaseFishEntityModel<RockfishEntity>("hybrid_aquatic", "rockfish") {

    override fun getTextureResource(animatable: RockfishEntity): ResourceLocation {
        return when (animatable.variant) {
            RockfishEntity.Companion.Type.VERMILION -> VERMILION_TEXTURE
            RockfishEntity.Companion.Type.COPPER -> COPPER_TEXTURE
            RockfishEntity.Companion.Type.YELLOWEYE -> YELLOWEYE_TEXTURE
        }
    }

    companion object {
        private val VERMILION_TEXTURE =
            ResourceLocation.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/fish/rockfish/vermilion_rockfish.png")
        private val COPPER_TEXTURE =
            ResourceLocation.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/fish/rockfish/copper_rockfish.png")
        private val YELLOWEYE_TEXTURE =
            ResourceLocation.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/fish/rockfish/yelloweye_rockfish.png")
    }
}
