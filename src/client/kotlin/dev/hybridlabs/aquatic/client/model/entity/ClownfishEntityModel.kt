package dev.hybridlabs.aquatic.client.model.entity

import dev.hybridlabs.aquatic.HybridAquatic
import dev.hybridlabs.aquatic.entity.ClownfishEntity
import net.minecraft.util.Identifier
import software.bernie.geckolib.model.GeoModel

class ClownfishEntityModel : GeoModel<ClownfishEntity>() {
    override fun getModelResource(animatable: ClownfishEntity): Identifier {
        return Identifier(HybridAquatic.MOD_ID, "geo/clownfish.geo.json")
    }

    override fun getTextureResource(animatable: ClownfishEntity): Identifier {
        return Identifier(HybridAquatic.MOD_ID, "textures/entity/clownfish.png")
    }

    override fun getAnimationResource(animatable: ClownfishEntity): Identifier {
        return Identifier(HybridAquatic.MOD_ID, "animations/clownfish.animation.json")
    }
}
