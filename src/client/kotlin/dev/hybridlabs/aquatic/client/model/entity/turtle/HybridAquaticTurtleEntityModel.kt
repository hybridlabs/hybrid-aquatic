package dev.hybridlabs.aquatic.client.model.entity.turtle

import dev.hybridlabs.aquatic.HybridAquatic
import dev.hybridlabs.aquatic.entity.turtle.HybridAquaticTurtleEntity
import net.minecraft.util.Identifier
import software.bernie.geckolib.model.GeoModel

abstract class HybridAquaticTurtleEntityModel<T : HybridAquaticTurtleEntity> (
    private val id: String
) : GeoModel<T>() {
    override fun getModelResource(animatable: T): Identifier {
        return Identifier(HybridAquatic.MOD_ID, "geo/turtle/$id.geo.json")
    }

    override fun getTextureResource(animatable: T): Identifier {
        return Identifier(HybridAquatic.MOD_ID, "textures/entity/turtle/$id.png")
    }

    override fun getAnimationResource(animatable: T): Identifier {
        return Identifier(HybridAquatic.MOD_ID, "animations/$id.animation.json")
    }
}