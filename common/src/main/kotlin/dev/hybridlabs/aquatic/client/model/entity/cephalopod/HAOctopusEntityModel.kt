package dev.hybridlabs.aquatic.client.model.entity.cephalopod

import dev.hybridlabs.aquatic.CommonClass
import dev.hybridlabs.aquatic.entity.base.HAOctopusEntity
import net.minecraft.resources.ResourceLocation
import net.minecraft.util.Mth
import software.bernie.geckolib.core.animation.AnimationState
import software.bernie.geckolib.model.GeoModel

@Suppress("OVERRIDE_DEPRECATION")
abstract class HAOctopusEntityModel<T : HAOctopusEntity>(private val id: String) :
    GeoModel<T>() {

    override fun getModelResource(animatable: T): ResourceLocation {
        return CommonClass.locate("geo/cephalopod/$id/$id.geo.json")
    }

    override fun getTextureResource(animatable: T): ResourceLocation {
        return CommonClass.locate("textures/entity/cephalopod/$id/$id.png")
    }

    override fun getAnimationResource(animatable: T): ResourceLocation {
        return CommonClass.locate("animations/entity/cephalopod/$id/$id.animation.json")
    }

    fun getLayerTextureResource(): ResourceLocation {
        return CommonClass.locate("textures/entity/cephalopod/$id/${id}_tint.png")
    }

    override fun setCustomAnimations(
        animatable: T,
        instanceId: Long,
        animationState: AnimationState<T>
    ) {
        super.setCustomAnimations(animatable, instanceId, animationState)
        val deltaTime: Float = animationState.partialTick

        val body = animationProcessor.getBone("octopus")

        val xRot = Mth.clamp(Mth.lerp(deltaTime, animatable.xRotO, animatable.xRot), -45f, 45f)
        body.rotX = xRot * -Mth.DEG_TO_RAD
    }
}