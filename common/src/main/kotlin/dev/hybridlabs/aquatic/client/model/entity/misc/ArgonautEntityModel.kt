package dev.hybridlabs.aquatic.client.model.entity.misc

import dev.hybridlabs.aquatic.CommonClass
import dev.hybridlabs.aquatic.entity.misc.ArgonautEntity
import net.minecraft.client.Minecraft
import net.minecraft.client.model.geom.PartNames
import net.minecraft.client.renderer.RenderType
import net.minecraft.resources.ResourceLocation
import net.minecraft.util.Mth
import software.bernie.geckolib.core.animation.AnimationState
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

    fun getVisorTextureResource(): ResourceLocation {
        return CommonClass.locate("textures/entity/misc/argonaut/argonaut_visor.png")
    }

    override fun getAnimationResource(animatable: T): ResourceLocation {
        return CommonClass.locate("animations/entity/misc/argonaut.animation.json")
    }

    override fun getRenderType(animatable: T, texture: ResourceLocation): RenderType {
        return RenderType.entityCutoutNoCull(texture)
    }

    override fun setCustomAnimations(
        animatable: T,
        instanceId: Long,
        animationState: AnimationState<T>,
    ) {
        val deltaTime = Minecraft.getInstance().deltaFrameTime
        val body = animationProcessor.getBone(PartNames.BODY)

        val yaw = Mth.lerp(deltaTime, animatable.yRotO, animatable.yRot)
        body.rotY = -yaw * Mth.DEG_TO_RAD

        val tilt = Mth.lerp(deltaTime, animatable.xRotO, animatable.xRot)

        body.rotX = tilt * -Mth.DEG_TO_RAD
    }
}