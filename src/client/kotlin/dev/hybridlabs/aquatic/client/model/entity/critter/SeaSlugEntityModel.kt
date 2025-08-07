package dev.hybridlabs.aquatic.client.model.entity.critter

import dev.hybridlabs.aquatic.entity.critter.SeaSlugEntity
import net.minecraft.util.Identifier
import kotlin.random.Random

class SeaSlugEntityModel : HybridAquaticCritterEntityModel<SeaSlugEntity>("sea_slug") {

    private val SPOTTED_SEA_HARE_TEXTURE = Identifier("hybrid-aquatic", "textures/entity/critter/sea_slug/spotted_sea_hare.png")

    private val nudibranchTextures = listOf(
        Identifier("hybrid-aquatic", "textures/entity/critter/sea_slug/nudibranch_baba.png"),
        Identifier("hybrid-aquatic", "textures/entity/critter/sea_slug/nudibranch_bullock.png"),
        Identifier("hybrid-aquatic", "textures/entity/critter/sea_slug/nudibranch_festiva.png"),
        Identifier("hybrid-aquatic", "textures/entity/critter/sea_slug/nudibranch_kubaryana.png"),
        Identifier("hybrid-aquatic", "textures/entity/critter/sea_slug/nudibranch_kuniei.png"),
        Identifier("hybrid-aquatic", "textures/entity/critter/sea_slug/nudibranch_magnificent.png"),
        Identifier("hybrid-aquatic", "textures/entity/critter/sea_slug/nudibranch_pyjama.png"),
        Identifier("hybrid-aquatic", "textures/entity/critter/sea_slug/nudibranch_sagami.png"),
        Identifier("hybrid-aquatic", "textures/entity/critter/sea_slug/nudibranch_yonowae.png")
    )

    private val SPOTTED_SEA_HARE_MODEL = Identifier("hybrid-aquatic", "geo/critter/sea_slug/spotted_sea_hare.geo.json")
    private val NUDIBRANCH_MODEL = Identifier("hybrid-aquatic", "geo/critter/sea_slug/nudibranch.geo.json")

    private val SEA_HARE_ANIMATION = Identifier("hybrid-aquatic", "animations/sea_hare.animation.json")
    private val NUDIBRANCH_ANIMATION = Identifier("hybrid-aquatic", "animations/nudibranch.animation.json")

    override fun getTextureResource(animatable: SeaSlugEntity): Identifier {
        val seed = animatable.uuid.leastSignificantBits
        val random = Random(seed)
        return when (animatable.variant) {
            SeaSlugEntity.Companion.Type.SPOTTED_SEA_HARE -> SPOTTED_SEA_HARE_TEXTURE
            SeaSlugEntity.Companion.Type.NUDIBRANCH -> nudibranchTextures[random.nextInt(nudibranchTextures.size)]
        }
    }

    override fun getModelResource(animatable: SeaSlugEntity): Identifier {
        return when (animatable.variant) {
            SeaSlugEntity.Companion.Type.SPOTTED_SEA_HARE -> SPOTTED_SEA_HARE_MODEL
            SeaSlugEntity.Companion.Type.NUDIBRANCH -> NUDIBRANCH_MODEL
        }
    }

    override fun getAnimationResource(animatable: SeaSlugEntity): Identifier {
        return when (animatable.variant) {
            SeaSlugEntity.Companion.Type.SPOTTED_SEA_HARE -> SEA_HARE_ANIMATION
            SeaSlugEntity.Companion.Type.NUDIBRANCH -> NUDIBRANCH_ANIMATION
        }
    }
}