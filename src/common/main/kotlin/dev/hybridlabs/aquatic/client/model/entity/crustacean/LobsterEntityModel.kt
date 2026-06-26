package dev.hybridlabs.aquatic.client.model.entity.crustacean

import dev.hybridlabs.aquatic.entity.crustacean.LobsterEntity
import net.minecraft.resources.ResourceLocation
import kotlin.random.Random

class LobsterEntityModel : HybridAquaticCrustaceanEntityModel<LobsterEntity>("lobster") {

    private val clawlessTextures = listOf(
        ResourceLocation("hybrid-aquatic", "textures/entity/crustacean/lobster/lobster_ornate_spiny.png"),
        ResourceLocation("hybrid-aquatic", "textures/entity/crustacean/lobster/lobster_california_spiny.png")
    )

    private val clawedTextures = listOf(
        ResourceLocation("hybrid-aquatic", "textures/entity/crustacean/lobster/lobster_american.png")
    )

    private val REGAL_SLIPPER_TEXTURE = ResourceLocation("hybrid-aquatic", "textures/entity/crustacean/lobster/lobster_regal_slipper.png")

    private val LOBSTER_MODEL = ResourceLocation("hybrid-aquatic", "geo/crustacean/lobster/lobster.geo.json")
    private val SLIPPER_LOBSTER_MODEL = ResourceLocation("hybrid-aquatic", "geo/crustacean/lobster/lobster_slipper.geo.json")

    override fun getTextureResource(animatable: LobsterEntity): ResourceLocation {
        val seed = animatable.uuid.leastSignificantBits
        val random = Random(seed)
        return when (animatable.variant) {
            LobsterEntity.Companion.Type.CLAWED -> clawedTextures[random.nextInt(clawedTextures.size)]
            LobsterEntity.Companion.Type.CLAWLESS -> clawlessTextures[random.nextInt(clawlessTextures.size)]
            LobsterEntity.Companion.Type.REGAL_SLIPPER -> REGAL_SLIPPER_TEXTURE
        }
    }

    override fun getModelResource(animatable: LobsterEntity): ResourceLocation {
        return when (animatable.variant) {
            LobsterEntity.Companion.Type.REGAL_SLIPPER -> SLIPPER_LOBSTER_MODEL
            else -> LOBSTER_MODEL
        }
    }
}