package dev.hybridlabs.aquatic.client.model.entity.fish

import dev.hybridlabs.aquatic.entity.fish.StingrayEntity
import net.minecraft.util.Identifier

class StingrayEntityModel : HybridAquaticFishEntityModel<StingrayEntity>("stingray") {

    private val BLUE_SPOTTED_TEXTURE = Identifier("hybrid-aquatic", "textures/entity/fish/stingray/stingray_blue_spotted.png")
    private val SPOTTED_EAGLE_TEXTURE = Identifier("hybrid-aquatic", "textures/entity/fish/stingray/stingray_spotted_eagle.png")

    private val BLUE_SPOTTED_MODEL = Identifier("hybrid-aquatic", "geo/fish/stingray/stingray_blue_spotted.geo.json")
    private val SPOTTED_EAGLE_MODEL = Identifier("hybrid-aquatic", "geo/fish/stingray/stingray_spotted_eagle.geo.json")

    private val BLUE_SPOTTED_ANIMATION = Identifier("hybrid-aquatic", "animations/stingray_blue_spotted.geo.json")
    private val SPOTTED_EAGLE_ANIMATION = Identifier("hybrid-aquatic", "animations/stingray_spotted_eagle.geo.json")

    override fun getTextureResource(animatable: StingrayEntity): Identifier {
        return when (animatable.variant) {
            StingrayEntity.Companion.Type.BLUE_SPOTTED -> BLUE_SPOTTED_TEXTURE
            StingrayEntity.Companion.Type.SPOTTED_EAGLE -> SPOTTED_EAGLE_TEXTURE
        }
    }

    override fun getModelResource(animatable: StingrayEntity): Identifier {
        return when (animatable.variant) {
            StingrayEntity.Companion.Type.BLUE_SPOTTED -> BLUE_SPOTTED_MODEL
            StingrayEntity.Companion.Type.SPOTTED_EAGLE -> SPOTTED_EAGLE_MODEL
        }
    }

    override fun getAnimationResource(animatable: StingrayEntity): Identifier {
        return when (animatable.variant) {
            StingrayEntity.Companion.Type.BLUE_SPOTTED -> BLUE_SPOTTED_ANIMATION
            StingrayEntity.Companion.Type.SPOTTED_EAGLE -> SPOTTED_EAGLE_ANIMATION
        }
    }
}