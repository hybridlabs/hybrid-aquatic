package dev.hybridlabs.aquatic.client.model.entity.fish

import dev.hybridlabs.aquatic.entity.fish.ParrotfishEntity
import dev.hybridlabs.aquatic.entity.fish.SeadragonEntity
import dev.hybridlabs.aquatic.entity.fish.SheepsheadWrasseEntity
import net.minecraft.util.Identifier

class SheepsheadWrasseEntityModel : HybridAquaticFishEntityModel<SheepsheadWrasseEntity>("sheepshead_wrasse") {

    private val CALIFORNIA_SHEEPSHEAD_TEXTURE = Identifier("hybrid-aquatic", "textures/entity/fish/wrasse/california_sheepshead.png")

    private val CALIFORNIA_SHEEPSHEAD_MODEL = Identifier("hybrid-aquatic", "geo/fish/wrasse/california_sheepshead.geo.json")

    override fun getTextureResource(animatable: SheepsheadWrasseEntity): Identifier {
        return when (animatable.variant) {
            SheepsheadWrasseEntity.Type.CALIFORNIA_SHEEPSHEAD -> CALIFORNIA_SHEEPSHEAD_TEXTURE
        }
    }

    override fun getModelResource(animatable: SheepsheadWrasseEntity): Identifier {
        return when (animatable.variant) {
            SheepsheadWrasseEntity.Type.CALIFORNIA_SHEEPSHEAD -> CALIFORNIA_SHEEPSHEAD_MODEL
        }
    }
}