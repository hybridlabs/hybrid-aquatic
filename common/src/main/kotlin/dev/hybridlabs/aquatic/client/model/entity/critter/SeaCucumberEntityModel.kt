package dev.hybridlabs.aquatic.client.model.entity.critter

import dev.hybridlabs.aquatic.entity.critter.SeaCucumberEntity
import dev.hybridlabs.hapi.client.model.entity.BaseCritterEntityModel
import net.minecraft.client.renderer.RenderType
import net.minecraft.resources.ResourceLocation
import kotlin.random.Random

class SeaCucumberEntityModel : BaseCritterEntityModel<SeaCucumberEntity>("hybrid_aquatic", "sea_cucumber") {

    override fun getRenderType(animatable: SeaCucumberEntity, texture: ResourceLocation): RenderType {
        return RenderType.entityTranslucent(texture)
    }

    companion object {
        private val commonTextures = listOf(
            ResourceLocation.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/critter/sea_cucumber/sea_cucumber_black_teatfish.png"),
            ResourceLocation.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/critter/sea_cucumber/sea_cucumber_white_teatfish.png"),
            ResourceLocation.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/critter/sea_cucumber/sea_cucumber_greenfish.png"),
            ResourceLocation.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/critter/sea_cucumber/sea_cucumber_prickly_redfish.png"),
            ResourceLocation.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/critter/sea_cucumber/sea_cucumber_curryfish.png"),
            ResourceLocation.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/critter/sea_cucumber/sea_cucumber_sandfish.png")
        )

        private val SEA_PIG_TEXTURE =
            ResourceLocation.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/critter/sea_cucumber/sea_pig.png")

        private val SEA_PIG_MODEL =
            ResourceLocation.fromNamespaceAndPath("hybrid_aquatic", "geo/critter/sea_cucumber/sea_pig.geo.json")
        private val SEA_CUCUMBER_MODEL =
            ResourceLocation.fromNamespaceAndPath("hybrid_aquatic", "geo/critter/sea_cucumber/sea_cucumber.geo.json")

        private val SEA_PIG_ANIMATION =
            ResourceLocation.fromNamespaceAndPath("hybrid_aquatic", "animations/entity/critter/sea_cucumber/sea_pig.animation.json")
        private val SEA_CUCUMBER_ANIMATION =
            ResourceLocation.fromNamespaceAndPath("hybrid_aquatic", "animations/entity/critter/sea_cucumber/sea_cucumber.animation.json")
    }

    override fun getTextureResource(animatable: SeaCucumberEntity): ResourceLocation {
        val seed = animatable.uuid.leastSignificantBits
        val random = Random(seed)
        return when (animatable.variant) {
            SeaCucumberEntity.Companion.Type.COMMON -> commonTextures[random.nextInt(commonTextures.size)]
            SeaCucumberEntity.Companion.Type.SEA_PIG -> SEA_PIG_TEXTURE
        }
    }

    override fun getModelResource(animatable: SeaCucumberEntity): ResourceLocation {
        return when (animatable.variant) {
            SeaCucumberEntity.Companion.Type.SEA_PIG -> SEA_PIG_MODEL
            SeaCucumberEntity.Companion.Type.COMMON -> SEA_CUCUMBER_MODEL
        }
    }

    override fun getAnimationResource(animatable: SeaCucumberEntity): ResourceLocation {
        return when (animatable.variant) {
            SeaCucumberEntity.Companion.Type.SEA_PIG -> SEA_PIG_ANIMATION
            SeaCucumberEntity.Companion.Type.COMMON -> SEA_CUCUMBER_ANIMATION
        }
    }
}
