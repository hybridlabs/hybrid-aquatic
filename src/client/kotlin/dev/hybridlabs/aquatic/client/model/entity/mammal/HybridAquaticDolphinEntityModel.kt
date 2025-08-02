package dev.hybridlabs.aquatic.client.model.entity.mammal

import dev.hybridlabs.aquatic.HybridAquatic
import dev.hybridlabs.aquatic.entity.mammal.HybridAquaticDolphinEntity
import net.minecraft.util.Identifier
import software.bernie.geckolib.model.GeoModel

abstract class HybridAquaticDolphinEntityModel<T : HybridAquaticDolphinEntity>(private val id: String) :
    GeoModel<T>() {
    override fun getModelResource(animatable: T): Identifier {
        return Identifier(HybridAquatic.MOD_ID, "geo/mammal/$id/$id.geo.json")
    }

    override fun getTextureResource(animatable: T): Identifier {
        return Identifier(HybridAquatic.MOD_ID, "textures/entity/mammal/$id/$id.png")
    }

    override fun getAnimationResource(animatable: T): Identifier {
        return Identifier(HybridAquatic.MOD_ID, "animations/$id.animation.json")
    }

    fun getLayerTextureResource(layer: String): Identifier {
        return Identifier(HybridAquatic.MOD_ID, "textures/entity/mammal/$id/layers/${id}_$layer.png")
    }
}