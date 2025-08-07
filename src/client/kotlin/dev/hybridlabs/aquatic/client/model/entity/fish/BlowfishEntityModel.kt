package dev.hybridlabs.aquatic.client.model.entity.fish

import dev.hybridlabs.aquatic.HybridAquatic
import dev.hybridlabs.aquatic.entity.fish.BlowfishEntity
import net.minecraft.client.render.RenderLayer
import net.minecraft.util.Identifier

class BlowfishEntityModel : HybridAquaticFishEntityModel<BlowfishEntity>("blowfish") {
    override fun getRenderType(animatable: BlowfishEntity, texture: Identifier): RenderLayer {
        return RenderLayer.getEntityTranslucent(texture)
    }

    override fun getTextureResource(animatable: BlowfishEntity): Identifier {
        val puffState = animatable.getPuffState()

        val texturePath = when (puffState) {
            0 -> "textures/entity/fish/blowfish/blowfish_small.png"
            1 -> "textures/entity/fish/blowfish/blowfish_medium.png"
            else -> "textures/entity/fish/blowfish/blowfish_large.png"
        }

        return Identifier(HybridAquatic.MOD_ID, texturePath)
    }

    override fun getModelResource(animatable: BlowfishEntity): Identifier {
        val puffState = animatable.getPuffState()

        val texturePath = when (puffState) {
            0 -> "geo/fish/blowfish/blowfish_small.geo.json"
            1 -> "geo/fish/blowfish/blowfish_medium.geo.json"
            else -> "geo/fish/blowfish/blowfish_large.geo.json"
        }

        return Identifier(HybridAquatic.MOD_ID, texturePath)
    }
}
