package dev.hybridlabs.aquatic.client.model.entity.critter

import dev.hybridlabs.aquatic.entity.critter.SeaSlugEntity
import net.minecraft.resources.ResourceLocation
import kotlin.random.Random

class SeaSlugEntityModel : HACritterEntityModel<SeaSlugEntity>("sea_slug") {

    companion object {
        private val SPOTTED_SEA_HARE_TEXTURE =
            ResourceLocation("hybrid-aquatic", "textures/entity/critter/sea_slug/spotted_sea_hare.png")

        private val nudibranchTextures = listOf(
            ResourceLocation("hybrid-aquatic", "textures/entity/critter/sea_slug/nudibranch_baba.png"),
            ResourceLocation("hybrid-aquatic", "textures/entity/critter/sea_slug/nudibranch_bullock.png"),
            ResourceLocation("hybrid-aquatic", "textures/entity/critter/sea_slug/nudibranch_festiva.png"),
            ResourceLocation("hybrid-aquatic", "textures/entity/critter/sea_slug/nudibranch_kubaryana.png"),
            ResourceLocation("hybrid-aquatic", "textures/entity/critter/sea_slug/nudibranch_kuniei.png"),
            ResourceLocation("hybrid-aquatic", "textures/entity/critter/sea_slug/nudibranch_magnificent.png"),
            ResourceLocation("hybrid-aquatic", "textures/entity/critter/sea_slug/nudibranch_pyjama.png"),
            ResourceLocation("hybrid-aquatic", "textures/entity/critter/sea_slug/nudibranch_sagami.png"),
            ResourceLocation("hybrid-aquatic", "textures/entity/critter/sea_slug/nudibranch_yonowae.png")
        )

        private val SPOTTED_SEA_HARE_MODEL =
            ResourceLocation("hybrid-aquatic", "geo/critter/sea_slug/sea_hare.geo.json")
        private val NUDIBRANCH_MODEL =
            ResourceLocation("hybrid-aquatic", "geo/critter/sea_slug/nudibranch.geo.json")

        private val SEA_HARE_ANIMATION =
            ResourceLocation("hybrid-aquatic", "animations/entity/critter/sea_slug/sea_slug.animation.json")
        private val NUDIBRANCH_ANIMATION =
            ResourceLocation("hybrid-aquatic", "animations/entity/critter/sea_slug/nudibranch.animation.json")
    }

    override fun getTextureResource(animatable: SeaSlugEntity): ResourceLocation {
        val seed = animatable.uuid.leastSignificantBits
        val random = Random(seed)
        return when (animatable.variant) {
            SeaSlugEntity.Companion.Type.SEA_HARE -> SPOTTED_SEA_HARE_TEXTURE
            SeaSlugEntity.Companion.Type.NUDIBRANCH -> nudibranchTextures[random.nextInt(nudibranchTextures.size)]
        }
    }

    override fun getModelResource(animatable: SeaSlugEntity): ResourceLocation {
        return when (animatable.variant) {
            SeaSlugEntity.Companion.Type.SEA_HARE -> SPOTTED_SEA_HARE_MODEL
            SeaSlugEntity.Companion.Type.NUDIBRANCH -> NUDIBRANCH_MODEL
        }
    }

    override fun getAnimationResource(animatable: SeaSlugEntity): ResourceLocation {
        return when (animatable.variant) {
            SeaSlugEntity.Companion.Type.SEA_HARE -> SEA_HARE_ANIMATION
            SeaSlugEntity.Companion.Type.NUDIBRANCH -> NUDIBRANCH_ANIMATION
        }
    }
}