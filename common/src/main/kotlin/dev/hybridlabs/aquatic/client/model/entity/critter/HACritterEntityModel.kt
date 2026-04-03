package dev.hybridlabs.aquatic.client.model.entity.critter

import dev.hybridlabs.aquatic.CommonClass
import dev.hybridlabs.aquatic.entity.critter.HACritterEntity
import net.minecraft.resources.ResourceLocation
import software.bernie.geckolib.model.GeoModel

@Suppress("OVERRIDE_DEPRECATION")
abstract class HACritterEntityModel<T : HACritterEntity>(private val id: String) :
    GeoModel<T>() {

    override fun getModelResource(animatable: T): ResourceLocation {
        return CommonClass.locate("geo/critter/$id/$id.geo.json")
    }

    override fun getTextureResource(animatable: T): ResourceLocation {
        return CommonClass.locate("textures/entity/critter/$id/$id.png")
    }

    override fun getAnimationResource(animatable: T): ResourceLocation {
        return CommonClass.locate("animations/entity/critter/$id/$id.animation.json")
    }

    open fun getLayerTextureResource(layer: String): ResourceLocation {
        return CommonClass.locate("textures/entity/critter/$id/layers/${id}_$layer.png")
    }
}