package dev.hybridlabs.aquatic.client.model.entity.fish

import dev.hybridlabs.aquatic.HybridAquatic
import dev.hybridlabs.aquatic.entity.fish.ToadfishEntity
import net.minecraft.util.Identifier

class ToadfishEntityModel : HybridAquaticFishEntityModel<ToadfishEntity>("toadfish") {

    override fun getTextureResource(animatable: ToadfishEntity): Identifier {
        val puffState = animatable.getPuffState()

        val texturePath = when (puffState) {
            0 -> "textures/entity/fish/toadfish/toadfish_small.png"
            1 -> "textures/entity/fish/toadfish/toadfish_medium.png"
            else -> "textures/entity/fish/toadfish/toadfish_large.png"
        }

        return Identifier(HybridAquatic.MOD_ID, texturePath)
    }
}
