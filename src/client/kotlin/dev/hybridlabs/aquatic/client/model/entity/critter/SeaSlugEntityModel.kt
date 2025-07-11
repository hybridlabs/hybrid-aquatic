package dev.hybridlabs.aquatic.client.model.entity.critter

import dev.hybridlabs.aquatic.entity.critter.SeaSlugEntity
import net.minecraft.util.Identifier

class SeaSlugEntityModel : HybridAquaticCritterEntityModel<SeaSlugEntity>("sea_slug") {

    private val SPOTTED_SEA_HARE_TEXTURE = Identifier("hybrid-aquatic", "textures/entity/critter/sea_slug/spotted_sea_hare.png")

    private val SPOTTED_SEA_HARE_MODEL = Identifier("hybrid-aquatic", "geo/critter/sea_slug/spotted_sea_hare.geo.json")

    override fun getTextureResource(animatable: SeaSlugEntity): Identifier {
        return when (animatable.variant) {
            SeaSlugEntity.Type.SPOTTED_SEA_HARE -> SPOTTED_SEA_HARE_TEXTURE
        }
    }

    override fun getModelResource(animatable: SeaSlugEntity): Identifier {
        return when (animatable.variant) {
            SeaSlugEntity.Type.SPOTTED_SEA_HARE -> SPOTTED_SEA_HARE_MODEL
            else -> SPOTTED_SEA_HARE_MODEL
        }
    }
}