package dev.hybridlabs.aquatic.client.model.entity.fish

import dev.hybridlabs.aquatic.entity.fish.CarpEntity
import net.minecraft.resources.ResourceLocation
import kotlin.random.Random

class CarpEntityModel : HybridAquaticFishEntityModel<CarpEntity>("carp") {

    private val PRUSSIAN_MODEL = ResourceLocation("hybrid-aquatic", "geo/fish/carp/carp.geo.json")
    private val CARP_MODEL = ResourceLocation("hybrid-aquatic", "geo/fish/carp/prussian_carp.geo.json")

    private val COMMON_TEXTURE = ResourceLocation("hybrid-aquatic", "textures/entity/fish/carp/carp.png")
    private val PRUSSIAN_TEXTURE = ResourceLocation("hybrid-aquatic", "textures/entity/fish/carp/prussian_carp.png")
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
            CarpEntity.Companion.Type.PRUSSIAN -> PRUSSIAN_TEXTURE
            CarpEntity.Companion.Type.MAGIKARP -> MAGIKARP_TEXTURE
            CarpEntity.Companion.Type.KOI -> koiTextures[random.nextInt(koiTextures.size)]
        }
    }

    override fun getModelResource(animatable: CarpEntity): ResourceLocation {
        return when (animatable.variant) {
            CarpEntity.Companion.Type.PRUSSIAN -> PRUSSIAN_MODEL
            CarpEntity.Companion.Type.COMMON -> CARP_MODEL
            CarpEntity.Companion.Type.KOI -> CARP_MODEL
            CarpEntity.Companion.Type.MAGIKARP -> CARP_MODEL
        }
    }
}

