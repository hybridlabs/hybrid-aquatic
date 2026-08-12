package dev.hybridlabs.aquatic.client.model.entity.fish

import dev.hybridlabs.aquatic.entity.fish.TrevallyEntity
import dev.hybridlabs.hapi.client.model.entity.BaseFishEntityModel
import net.minecraft.resources.ResourceLocation
import kotlin.random.Random

class TrevallyEntityModel : BaseFishEntityModel<TrevallyEntity>("hybrid_aquatic", "trevally") {

    override fun getTextureResource(animatable: TrevallyEntity): ResourceLocation {
        val seed = animatable.uuid.leastSignificantBits
        val random = Random(seed)
        return pilotfishTextures[random.nextInt(pilotfishTextures.size)]
    }

    companion object {
        private val pilotfishTextures = listOf(
            ResourceLocation.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/fish/trevally/pilotfish_yellow.png"),
            ResourceLocation.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/fish/trevally/pilotfish_white.png"),
        )
    }
}
