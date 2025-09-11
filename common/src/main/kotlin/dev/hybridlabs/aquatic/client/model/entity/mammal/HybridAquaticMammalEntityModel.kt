package dev.hybridlabs.aquatic.client.model.entity.mammal

import dev.hybridlabs.aquatic.Constants
import dev.hybridlabs.aquatic.entity.mammal.HybridAquaticMammalEntity
import net.minecraft.client.Minecraft
import net.minecraft.client.model.geom.PartNames
import net.minecraft.resources.ResourceLocation
import net.minecraft.util.Mth
import software.bernie.geckolib.core.animation.AnimationState
import software.bernie.geckolib.model.GeoModel

abstract class HybridAquaticMammalEntityModel<T : HybridAquaticMammalEntity>(private val id: String) :
    GeoModel<T>() {
    override fun getModelResource(animatable: T): ResourceLocation {
        return ResourceLocation(Constants.MOD_ID, "geo/mammal/$id/$id.geo.json")
    }

    override fun getTextureResource(animatable: T): ResourceLocation {
        return ResourceLocation(Constants.MOD_ID, "textures/entity/mammal/$id/$id.png")
    }

    override fun getAnimationResource(animatable: T): ResourceLocation {
        return ResourceLocation(Constants.MOD_ID, "animations/$id.animation.json")
    }

    fun getLayerTextureResource(layer: String): ResourceLocation {
        return ResourceLocation(Constants.MOD_ID, "textures/entity/mammal/$id/layers/${id}_$layer.png")
    }

    override fun setCustomAnimations(
        animatable: T,
        instanceId: Long,
        animationState: AnimationState<T>
    ) {
        super.setCustomAnimations(animatable, instanceId, animationState)
        val deltaTime: Float = Minecraft.getInstance().deltaFrameTime

        val body = animationProcessor.getBone(PartNames.BODY)

        val xRot = Mth.clamp(Mth.lerp(deltaTime, animatable.xRotO, animatable.xRot), -45f, 45f)
        body.rotX = xRot * -Mth.DEG_TO_RAD
    }
}