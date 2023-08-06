package dev.hybridlabs.aquatic.client.model.entity

import dev.hybridlabs.aquatic.HybridAquatic
import dev.hybridlabs.aquatic.entity.ClownfishEntity
import dev.hybridlabs.aquatic.entity.HybridAquaticFishEntity
import net.minecraft.entity.mob.WaterCreatureEntity
import net.minecraft.util.Identifier
import software.bernie.geckolib.model.GeoModel

class BarreleyeEntityModel : GeoModel<HybridAquaticFishEntity>() {
    override fun getModelResource(animatable: HybridAquaticFishEntity): Identifier {
        return Identifier(HybridAquatic.MOD_ID, "geo/barreleye.geo.json")
    }

    override fun getTextureResource(animatable: HybridAquaticFishEntity): Identifier {
        return Identifier(HybridAquatic.MOD_ID, "textures/entity/barreleye.png")
    }

    override fun getAnimationResource(animatable: HybridAquaticFishEntity): Identifier {
        return Identifier(HybridAquatic.MOD_ID, "animations/barreleye.animation.json")
    }
}
