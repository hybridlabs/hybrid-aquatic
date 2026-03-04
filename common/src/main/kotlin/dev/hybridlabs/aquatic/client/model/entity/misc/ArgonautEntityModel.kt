package dev.hybridlabs.aquatic.client.model.entity.misc

import dev.hybridlabs.aquatic.CommonClass
import dev.hybridlabs.aquatic.entity.misc.ArgonautEntity
import net.minecraft.client.renderer.RenderType
import net.minecraft.resources.ResourceLocation
import software.bernie.geckolib.model.GeoModel

@Suppress("OVERRIDE_DEPRECATION")
class ArgonautEntityModel<T : ArgonautEntity>() :
    GeoModel<T>() {
    override fun getModelResource(animatable: T): ResourceLocation {
        return CommonClass.locate("geo/misc/argonaut.geo.json")
    }

    override fun getTextureResource(animatable: T): ResourceLocation {
        return CommonClass.locate("textures/entity/misc/argonaut/argonaut.png")
    }

    override fun getAnimationResource(animatable: T): ResourceLocation {
        return CommonClass.locate("animations/entity/misc/argonaut.animation.json")
    }

    override fun getRenderType(animatable: T, texture: ResourceLocation): RenderType {
        return RenderType.entityTranslucent(texture)
    }
}