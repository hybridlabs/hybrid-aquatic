package dev.hybridlabs.aquatic.client.model.entity.fish

import dev.hybridlabs.aquatic.CommonClass
import dev.hybridlabs.aquatic.entity.fish.CarpEntity
import net.minecraft.resources.ResourceLocation
import kotlin.random.Random

class CarpEntityModel : HAFishEntityModel<CarpEntity>("carp") {

    private val BABY_CARP_TEXTURE = ResourceLocation("hybrid-aquatic", "textures/entity/fish/carp/baby_carp.png")
    private val COMMON_TEXTURE = ResourceLocation("hybrid-aquatic", "textures/entity/fish/carp/carp.png")
    private val PRUSSIAN_TEXTURE = ResourceLocation("hybrid-aquatic", "textures/entity/fish/carp/prussian_carp.png")
    private val GOLDFISH_TEXTURE = ResourceLocation("hybrid-aquatic", "textures/entity/fish/carp/goldfish.png")
    private val COMMON_GOLDFISH_TEXTURE = ResourceLocation("hybrid-aquatic", "textures/entity/fish/carp/common_goldfish.png")

    private val BABY_CARP_MODEL = ResourceLocation("hybrid-aquatic", "geo/fish/carp/baby_carp.geo.json")
    private val COMMON_CARP_MODEL = ResourceLocation("hybrid-aquatic", "geo/fish/carp/carp.geo.json")
    private val PRUSSIAN_CARP_MODEL = ResourceLocation("hybrid-aquatic", "geo/fish/carp/prussian_carp.geo.json")
    private val GOLDFISH_MODEL = ResourceLocation("hybrid-aquatic", "geo/fish/carp/goldfish.geo.json")
    private val COMMON_GOLDFISH_MODEL = ResourceLocation("hybrid-aquatic", "geo/fish/carp/common_goldfish.geo.json")

    private val CARP_ANIMATION = ResourceLocation("hybrid-aquatic", "animations/entity/fish/carp/carp.animation.json")
    private val GOLDFISH_ANIMATION = ResourceLocation("hybrid-aquatic", "animations/entity/fish/carp/goldfish.animation.json")

    private val koiTextures = listOf(
        ResourceLocation("hybrid-aquatic", "textures/entity/fish/carp/koi_silver.png"),
        ResourceLocation("hybrid-aquatic", "textures/entity/fish/carp/koi_gold.png"),
        ResourceLocation("hybrid-aquatic", "textures/entity/fish/carp/koi_orange.png"),
        ResourceLocation("hybrid-aquatic", "textures/entity/fish/carp/koi_red.png"),
        ResourceLocation("hybrid-aquatic", "textures/entity/fish/carp/koi_yellow.png"),
        ResourceLocation("hybrid-aquatic", "textures/entity/fish/carp/koi_black.png"),
        ResourceLocation("hybrid-aquatic", "textures/entity/fish/carp/koi_white.png")
    )

    private val smallKoiTextures = listOf(
        ResourceLocation("hybrid-aquatic", "textures/entity/fish/carp/small_koi_silver.png"),
        ResourceLocation("hybrid-aquatic", "textures/entity/fish/carp/small_koi_gold.png"),
        ResourceLocation("hybrid-aquatic", "textures/entity/fish/carp/small_koi_orange.png"),
        ResourceLocation("hybrid-aquatic", "textures/entity/fish/carp/small_koi_red.png"),
        ResourceLocation("hybrid-aquatic", "textures/entity/fish/carp/small_koi_yellow.png"),
        ResourceLocation("hybrid-aquatic", "textures/entity/fish/carp/small_koi_black.png"),
        ResourceLocation("hybrid-aquatic", "textures/entity/fish/carp/small_koi_white.png")
    )

    override fun getTextureResource(animatable: CarpEntity): ResourceLocation {
        if (animatable.isBaby) {
            return BABY_CARP_TEXTURE
        }

        val seed = animatable.uuid.leastSignificantBits
        val random = Random(seed)

        return when (animatable.variant) {
            CarpEntity.Companion.Type.COMMON -> COMMON_TEXTURE
            CarpEntity.Companion.Type.PRUSSIAN -> PRUSSIAN_TEXTURE
            CarpEntity.Companion.Type.COMMON_GOLDFISH -> COMMON_GOLDFISH_TEXTURE
            CarpEntity.Companion.Type.KOI -> koiTextures[random.nextInt(koiTextures.size)]
            CarpEntity.Companion.Type.SMALL_KOI -> smallKoiTextures[random.nextInt(smallKoiTextures.size)]
            else -> GOLDFISH_TEXTURE
        }
    }

    override fun getModelResource(animatable: CarpEntity): ResourceLocation {
        if (animatable.isBaby) {
            return BABY_CARP_MODEL
        }

        return when (animatable.variant) {
            CarpEntity.Companion.Type.PRUSSIAN -> PRUSSIAN_CARP_MODEL
            CarpEntity.Companion.Type.SMALL_KOI -> PRUSSIAN_CARP_MODEL
            CarpEntity.Companion.Type.KOI -> COMMON_CARP_MODEL
            CarpEntity.Companion.Type.COMMON -> COMMON_CARP_MODEL
            CarpEntity.Companion.Type.COMMON_GOLDFISH -> COMMON_GOLDFISH_MODEL
            else -> GOLDFISH_MODEL
        }
    }

    override fun getAnimationResource(animatable: CarpEntity): ResourceLocation {return when (animatable.variant) {
            CarpEntity.Companion.Type.PRUSSIAN -> CARP_ANIMATION
            CarpEntity.Companion.Type.SMALL_KOI -> CARP_ANIMATION
            CarpEntity.Companion.Type.KOI -> CARP_ANIMATION
            CarpEntity.Companion.Type.COMMON -> CARP_ANIMATION
            CarpEntity.Companion.Type.COMMON_GOLDFISH -> CARP_ANIMATION
            else -> GOLDFISH_ANIMATION
        }
    }

    fun getPatternTextureResource(animatable: CarpEntity, layer: String): ResourceLocation {
        return when (animatable.variant) {
            CarpEntity.Companion.Type.SMALL_KOI -> CommonClass.locate("textures/entity/fish/carp/layer/small_$layer.png")
            else -> CommonClass.locate("textures/entity/fish/carp/layer/$layer.png")
        }
    }
}