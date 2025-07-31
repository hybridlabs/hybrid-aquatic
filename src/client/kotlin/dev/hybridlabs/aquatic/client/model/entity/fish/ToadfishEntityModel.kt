package dev.hybridlabs.aquatic.client.model.entity.fish

import dev.hybridlabs.aquatic.HybridAquatic
import dev.hybridlabs.aquatic.entity.fish.ToadfishEntity
import dev.hybridlabs.aquatic.entity.jellyfish.BigRedJellyfishEntity
import net.minecraft.client.render.RenderLayer
import net.minecraft.util.Identifier

class ToadfishEntityModel : HybridAquaticFishEntityModel<ToadfishEntity>("toadfish") {
    override fun getRenderType(animatable: ToadfishEntity, texture: Identifier): RenderLayer {
        return RenderLayer.getEntityTranslucent(texture)
    }

    override fun getTextureResource(animatable: ToadfishEntity): Identifier {
        val puffState = animatable.getPuffState()

        val texturePath = when (puffState) {
            0 -> "textures/entity/fish/toadfish/toadfish_small.png"
            1 -> "textures/entity/fish/toadfish/toadfish_medium.png"
            else -> "textures/entity/fish/toadfish/toadfish_large.png"
        }

        return Identifier(HybridAquatic.MOD_ID, texturePath)
    }

    override fun getModelResource(animatable: ToadfishEntity): Identifier {
        val puffState = animatable.getPuffState()

        val texturePath = when (puffState) {
            0 -> "geo/fish/toadfish/toadfish_small.geo.json"
            1 -> "geo/fish/toadfish/toadfish_medium.geo.json"
            else -> "geo/fish/toadfish/toadfish_large.geo.json"
        }

        return Identifier(HybridAquatic.MOD_ID, texturePath)
    }
}
