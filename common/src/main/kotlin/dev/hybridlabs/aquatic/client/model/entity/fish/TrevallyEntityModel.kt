package dev.hybridlabs.aquatic.client.model.entity.fish

import dev.hybridlabs.aquatic.entity.fish.TrevallyEntity
import net.minecraft.resources.ResourceLocation
import kotlin.random.Random

class TrevallyEntityModel : HAFishEntityModel<TrevallyEntity>("trevally") {

    private val pilotfishTextures = listOf(
        ResourceLocation("hybrid-aquatic", "textures/entity/fish/trevally/pilotfish_yellow.png"),
        ResourceLocation("hybrid-aquatic", "textures/entity/fish/trevally/pilotfish_white.png"),
    )

    override fun getTextureResource(animatable: TrevallyEntity): ResourceLocation {
        val seed = animatable.uuid.leastSignificantBits
        val random = Random(seed)
        return pilotfishTextures[random.nextInt(pilotfishTextures.size)]
    }
}
