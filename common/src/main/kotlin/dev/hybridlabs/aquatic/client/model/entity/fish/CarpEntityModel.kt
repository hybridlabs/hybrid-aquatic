package dev.hybridlabs.aquatic.client.model.entity.fish

import dev.hybridlabs.aquatic.entity.fish.CarpEntity
import net.minecraft.resources.ResourceLocation
import kotlin.random.Random

class CarpEntityModel : HybridAquaticFishEntityModel<CarpEntity>("carp") {

    private val COMMON_TEXTURE = ResourceLocation.fromNamespaceAndPath("hybrid-aquatic", "textures/entity/fish/carp/carp.png")

    private val koiTextures = listOf(
        ResourceLocation.fromNamespaceAndPath("hybrid-aquatic", "textures/entity/fish/carp/carp_ai_goromo.png"),
        ResourceLocation.fromNamespaceAndPath("hybrid-aquatic", "textures/entity/fish/carp/carp_hajiro.png"),
        ResourceLocation.fromNamespaceAndPath("hybrid-aquatic", "textures/entity/fish/carp/carp_platinum.png"),
        ResourceLocation.fromNamespaceAndPath("hybrid-aquatic", "textures/entity/fish/carp/carp_tancho.png")
    )

    override fun getTextureResource(animatable: CarpEntity): ResourceLocation {
        val seed = animatable.uuid.leastSignificantBits
        val random = Random(seed)
        return when (animatable.variant) {
            CarpEntity.Companion.Type.COMMON -> COMMON_TEXTURE
            CarpEntity.Companion.Type.KOI -> koiTextures[random.nextInt(koiTextures.size)]
        }
    }
}

