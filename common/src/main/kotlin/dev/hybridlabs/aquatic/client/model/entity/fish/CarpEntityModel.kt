package dev.hybridlabs.aquatic.client.model.entity.fish

import dev.hybridlabs.aquatic.entity.fish.CarpEntity
import net.minecraft.resources.ResourceLocation
import kotlin.random.Random

class CarpEntityModel : HybridAquaticFishEntityModel<CarpEntity>("carp") {

    private val COMMON_TEXTURE = ResourceLocation("hybrid-aquatic", "textures/entity/fish/carp/carp.png")
    private val MAGIKARP_TEXTURE = ResourceLocation("hybrid-aquatic", "textures/entity/fish/carp/magikarp.png")

    private val koiTextures = listOf(
        ResourceLocation("hybrid-aquatic", "textures/entity/fish/carp/koi_silver.png"),
        ResourceLocation("hybrid-aquatic", "textures/entity/fish/carp/koi_gold.png"),
        ResourceLocation("hybrid-aquatic", "textures/entity/fish/carp/koi_orange.png"),
        ResourceLocation("hybrid-aquatic", "textures/entity/fish/carp/koi_red.png"),
        ResourceLocation("hybrid-aquatic", "textures/entity/fish/carp/koi_yellow.png"),
        ResourceLocation("hybrid-aquatic", "textures/entity/fish/carp/koi_black.png"),
        ResourceLocation("hybrid-aquatic", "textures/entity/fish/carp/koi_black_red_heart.png"),
        ResourceLocation("hybrid-aquatic", "textures/entity/fish/carp/koi_white.png"),
        ResourceLocation("hybrid-aquatic", "textures/entity/fish/carp/koi_white_black_creeper.png"),
        ResourceLocation("hybrid-aquatic", "textures/entity/fish/carp/koi_white_orange_heart.png"),
        ResourceLocation("hybrid-aquatic", "textures/entity/fish/carp/koi_white_red_heart.png"),
        ResourceLocation("hybrid-aquatic", "textures/entity/fish/carp/koi_white_red_spot.png"),
    )

    override fun getTextureResource(animatable: CarpEntity): ResourceLocation {
        val seed = animatable.uuid.leastSignificantBits
        val random = Random(seed)
        return when (animatable.variant) {
            CarpEntity.Companion.Type.COMMON -> COMMON_TEXTURE
            CarpEntity.Companion.Type.MAGIKARP -> MAGIKARP_TEXTURE
            CarpEntity.Companion.Type.KOI -> koiTextures[random.nextInt(koiTextures.size)]
        }
    }
}

