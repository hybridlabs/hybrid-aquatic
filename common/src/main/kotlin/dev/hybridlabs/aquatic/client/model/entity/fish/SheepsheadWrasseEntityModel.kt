package dev.hybridlabs.aquatic.client.model.entity.fish

import dev.hybridlabs.aquatic.entity.fish.ParrotfishEntity
import dev.hybridlabs.aquatic.entity.fish.SeadragonEntity
import dev.hybridlabs.aquatic.entity.fish.SheepsheadWrasseEntity
import net.minecraft.resources.ResourceLocation

class SheepsheadWrasseEntityModel : HybridAquaticFishEntityModel<SheepsheadWrasseEntity>("sheepshead_wrasse") {

    private val CALIFORNIA_SHEEPSHEAD_TEXTURE = ResourceLocation("hybrid-aquatic", "textures/entity/fish/wrasse/california_sheepshead.png")

    private val CALIFORNIA_SHEEPSHEAD_MODEL = ResourceLocation("hybrid-aquatic", "geo/fish/wrasse/california_sheepshead.geo.json")

    override fun getTextureResource(animatable: SheepsheadWrasseEntity): ResourceLocation {
        return when (animatable.variant) {
            SheepsheadWrasseEntity.Companion.Type.CALIFORNIA_SHEEPSHEAD -> CALIFORNIA_SHEEPSHEAD_TEXTURE
        }
    }

    override fun getModelResource(animatable: SheepsheadWrasseEntity): ResourceLocation {
        return when (animatable.variant) {
            SheepsheadWrasseEntity.Companion.Type.CALIFORNIA_SHEEPSHEAD -> CALIFORNIA_SHEEPSHEAD_MODEL
        }
    }
}