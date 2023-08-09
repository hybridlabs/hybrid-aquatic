package dev.hybridlabs.aquatic.client.model.entity

import dev.hybridlabs.aquatic.HybridAquatic
import dev.hybridlabs.aquatic.entity.HybridAquaticFishEntity
import net.minecraft.util.Identifier
import software.bernie.geckolib.model.GeoModel

class AnglerfishEntityModel : GeoModel<HybridAquaticFishEntity>() {
    override fun getModelResource(animatable: HybridAquaticFishEntity): Identifier {
        return Identifier(HybridAquatic.MOD_ID, "geo/anglerfish.geo.json")
    }

    override fun getTextureResource(animatable: HybridAquaticFishEntity): Identifier {
        return Identifier(HybridAquatic.MOD_ID, "textures/entity/anglerfish.png")
    }

    override fun getAnimationResource(animatable: HybridAquaticFishEntity): Identifier {
        return Identifier(HybridAquatic.MOD_ID, "animations/anglerfish.animation.json")
    }
}
