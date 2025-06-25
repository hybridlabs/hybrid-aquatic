package dev.hybridlabs.aquatic.client.model.entity.critter

import dev.hybridlabs.aquatic.entity.critter.SeaCucumberEntity
import net.minecraft.util.Identifier

class SeaCucumberEntityModel : HybridAquaticCritterEntityModel<SeaCucumberEntity>("sea_cucumber") {

    private val BLACK_TEATFISH_TEXTURE = Identifier("hybrid-aquatic", "textures/entity/critter/sea_cucumber/sea_cucumber_black_teatfish.png")
    private val WHITE_TEATFISH_TEXTURE = Identifier("hybrid-aquatic", "textures/entity/critter/sea_cucumber/sea_cucumber_white_teatfish.png")
    private val GREENFISH_TEXTURE = Identifier("hybrid-aquatic", "textures/entity/critter/sea_cucumber/sea_cucumber_greenfish.png")
    private val PRICKLY_REDFISH_TEXTURE = Identifier("hybrid-aquatic", "textures/entity/critter/sea_cucumber/sea_cucumber_prickly_redfish.png")
    private val CURRYFISH_TEXTURE = Identifier("hybrid-aquatic", "textures/entity/critter/sea_cucumber/sea_cucumber_curryfish.png")
    private val SANDFISH_TEXTURE = Identifier("hybrid-aquatic", "textures/entity/critter/sea_cucumber/sea_cucumber_sandfish.png")
    private val SEA_PIG_TEXTURE = Identifier("hybrid-aquatic", "textures/entity/critter/sea_cucumber/sea_cucumber_sea_pig.png")

    private val SEA_PIG_MODEL = Identifier("hybrid-aquatic", "geo/critter/sea_cucumber/sea_cucumber_sea_pig.geo.json")
    private val SEA_CUCUMBER_MODEL = Identifier("hybrid-aquatic", "geo/critter/sea_cucumber/sea_cucumber.geo.json")

    override fun getTextureResource(animatable: SeaCucumberEntity): Identifier {
        return when (animatable.variant) {
            SeaCucumberEntity.Type.BLACK_TEATFISH -> BLACK_TEATFISH_TEXTURE
            SeaCucumberEntity.Type.WHITE_TEATFISH -> WHITE_TEATFISH_TEXTURE
            SeaCucumberEntity.Type.GREENFISH -> GREENFISH_TEXTURE
            SeaCucumberEntity.Type.PRICKLY_REDFISH -> PRICKLY_REDFISH_TEXTURE
            SeaCucumberEntity.Type.CURRYFISH -> CURRYFISH_TEXTURE
            SeaCucumberEntity.Type.SANDFISH -> SANDFISH_TEXTURE
            SeaCucumberEntity.Type.SEA_PIG -> SEA_PIG_TEXTURE
        }
    }

    override fun getModelResource(animatable: SeaCucumberEntity): Identifier {
        return when (animatable.variant) {
            SeaCucumberEntity.Type.SEA_PIG -> SEA_PIG_MODEL
            else -> SEA_CUCUMBER_MODEL
        }
    }
}

