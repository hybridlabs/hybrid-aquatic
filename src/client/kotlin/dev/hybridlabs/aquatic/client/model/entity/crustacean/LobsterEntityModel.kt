package dev.hybridlabs.aquatic.client.model.entity.crustacean

import dev.hybridlabs.aquatic.entity.crustacean.LobsterEntity
import net.minecraft.util.Identifier

class LobsterEntityModel : HybridAquaticCrustaceanEntityModel<LobsterEntity>("lobster") {

    private val AMERICAN_TEXTURE = Identifier("hybrid-aquatic", "textures/entity/crustacean/lobster/lobster_american.png")
    private val CALIFORNIA_SPINY_TEXTURE = Identifier("hybrid-aquatic", "textures/entity/crustacean/lobster/lobster_california_spiny.png")
    private val ORNATE_SPINY_TEXTURE = Identifier("hybrid-aquatic", "textures/entity/crustacean/lobster/lobster_ornate_spiny.png")
    private val REGAL_SLIPPER_TEXTURE = Identifier("hybrid-aquatic", "textures/entity/crustacean/lobster/lobster_regal_slipper.png")

    private val LOBSTER_MODEL = Identifier("hybrid-aquatic", "geo/crustacean/lobster/lobster.geo.json")
    private val SLIPPER_LOBSTER_MODEL = Identifier("hybrid-aquatic", "geo/crustacean/lobster/lobster_slipper.geo.json")

    override fun getTextureResource(animatable: LobsterEntity): Identifier {
        return when (animatable.variant) {
            LobsterEntity.Type.AMERICAN -> AMERICAN_TEXTURE
            LobsterEntity.Type.CALIFORNIA_SPINY -> CALIFORNIA_SPINY_TEXTURE
            LobsterEntity.Type.ORNATE_SPINY -> ORNATE_SPINY_TEXTURE
            LobsterEntity.Type.REGAL_SLIPPER -> REGAL_SLIPPER_TEXTURE
        }
    }

    override fun getModelResource(animatable: LobsterEntity): Identifier {
        return when (animatable.variant) {
            LobsterEntity.Type.REGAL_SLIPPER -> SLIPPER_LOBSTER_MODEL
            else -> LOBSTER_MODEL
        }
    }
}