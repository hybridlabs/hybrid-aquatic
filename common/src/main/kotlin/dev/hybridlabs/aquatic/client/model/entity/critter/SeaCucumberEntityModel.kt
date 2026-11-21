package dev.hybridlabs.aquatic.client.model.entity.critter

import dev.hybridlabs.hapi.client.model.HAPIDataTickets
import dev.hybridlabs.hapi.client.model.getVariant
import software.bernie.geckolib.renderer.base.GeoRenderState
import dev.hybridlabs.aquatic.entity.critter.SeaCucumberEntity
import dev.hybridlabs.hapi.client.model.entity.aquatic.BaseCritterEntityModel
import net.minecraft.client.renderer.rendertype.RenderType
import net.minecraft.client.renderer.rendertype.RenderTypes
import net.minecraft.resources.Identifier
import kotlin.random.Random

class SeaCucumberEntityModel : BaseCritterEntityModel<SeaCucumberEntity>("hybrid_aquatic", "sea_cucumber") {

    companion object {
        private val commonTextures = listOf(
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/critter/sea_cucumber/sea_cucumber_black_teatfish.png"),
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/critter/sea_cucumber/sea_cucumber_white_teatfish.png"),
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/critter/sea_cucumber/sea_cucumber_greenfish.png"),
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/critter/sea_cucumber/sea_cucumber_prickly_redfish.png"),
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/critter/sea_cucumber/sea_cucumber_curryfish.png"),
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/critter/sea_cucumber/sea_cucumber_sandfish.png")
        )

        private val SEA_PIG_TEXTURE =
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/critter/sea_cucumber/sea_pig.png")

        private val SEA_PIG_MODEL =
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "critter/sea_cucumber/sea_pig")
        private val SEA_CUCUMBER_MODEL =
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "critter/sea_cucumber/sea_cucumber")

        private val SEA_PIG_ANIMATION =
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "entity/critter/sea_cucumber/sea_pig")
        private val SEA_CUCUMBER_ANIMATION =
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "entity/critter/sea_cucumber/sea_cucumber")
    }

    override fun getTextureResource(renderState: GeoRenderState): Identifier {
        val seed = (renderState.getGeckolibData(HAPIDataTickets.ENTITY_UUID)!!).leastSignificantBits
        val random = Random(seed)
        return when (renderState.getVariant(SeaCucumberEntity.Companion.Type.entries)!!) {
            SeaCucumberEntity.Companion.Type.COMMON -> commonTextures[random.nextInt(commonTextures.size)]
            SeaCucumberEntity.Companion.Type.SEA_PIG -> SEA_PIG_TEXTURE
        }
    }

    override fun getModelResource(renderState: GeoRenderState): Identifier {
        return when (renderState.getVariant(SeaCucumberEntity.Companion.Type.entries)!!) {
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
