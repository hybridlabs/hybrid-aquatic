package dev.hybridlabs.aquatic.client.model.entity.critter

import dev.hybridlabs.aquatic.entity.critter.SeaCucumberEntity
import net.minecraft.client.render.RenderLayer
import net.minecraft.util.Identifier
import kotlin.random.Random

class SeaCucumberEntityModel : HybridAquaticCritterEntityModel<SeaCucumberEntity>("sea_cucumber") {

    override fun getRenderType(animatable: SeaCucumberEntity, texture: Identifier): RenderLayer {
        return RenderLayer.getEntityTranslucent(texture)
    }

    private val commonTextures = listOf(
    Identifier("hybrid-aquatic", "textures/entity/critter/sea_cucumber/sea_cucumber_black_teatfish.png"),
    Identifier("hybrid-aquatic", "textures/entity/critter/sea_cucumber/sea_cucumber_white_teatfish.png"),
    Identifier("hybrid-aquatic", "textures/entity/critter/sea_cucumber/sea_cucumber_greenfish.png"),
    Identifier("hybrid-aquatic", "textures/entity/critter/sea_cucumber/sea_cucumber_prickly_redfish.png"),
    Identifier("hybrid-aquatic", "textures/entity/critter/sea_cucumber/sea_cucumber_curryfish.png"),
    Identifier("hybrid-aquatic", "textures/entity/critter/sea_cucumber/sea_cucumber_sandfish.png")
    )

    private val SEA_PIG_TEXTURE = Identifier("hybrid-aquatic", "textures/entity/critter/sea_cucumber/sea_pig.png")

    private val SEA_PIG_MODEL = Identifier("hybrid-aquatic", "geo/critter/sea_cucumber/sea_pig.geo.json")
    private val SEA_CUCUMBER_MODEL = Identifier("hybrid-aquatic", "geo/critter/sea_cucumber/sea_cucumber.geo.json")

    private val SEA_PIG_ANIMATION = Identifier("hybrid-aquatic", "animations/sea_pig.animation.json")
    private val SEA_CUCUMBER_ANIMATION = Identifier("hybrid-aquatic", "animations/sea_cucumber.animation.json")

    override fun getTextureResource(animatable: SeaCucumberEntity): Identifier {
        val seed = animatable.uuid.leastSignificantBits
        val random = Random(seed)
        return when (animatable.variant) {
            SeaCucumberEntity.Companion.Type.COMMON -> commonTextures[random.nextInt(commonTextures.size)]
            SeaCucumberEntity.Companion.Type.SEA_PIG -> SEA_PIG_TEXTURE
        }
    }

    override fun getModelResource(animatable: SeaCucumberEntity): Identifier {
        return when (animatable.variant) {
            SeaCucumberEntity.Companion.Type.SEA_PIG -> SEA_PIG_MODEL
            SeaCucumberEntity.Companion.Type.COMMON -> SEA_CUCUMBER_MODEL
        }
    }

    override fun getAnimationResource(animatable: SeaCucumberEntity): Identifier {
        return when (animatable.variant) {
            SeaCucumberEntity.Companion.Type.SEA_PIG -> SEA_PIG_ANIMATION
            SeaCucumberEntity.Companion.Type.COMMON -> SEA_CUCUMBER_ANIMATION
        }
    }
}

