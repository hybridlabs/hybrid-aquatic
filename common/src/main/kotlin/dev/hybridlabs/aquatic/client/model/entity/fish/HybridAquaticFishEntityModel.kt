package dev.hybridlabs.aquatic.client.model.entity.fish

import dev.hybridlabs.aquatic.Constants
import dev.hybridlabs.aquatic.entity.fish.HybridAquaticFishEntity
import net.minecraft.client.Minecraft
import net.minecraft.client.model.geom.PartNames
import net.minecraft.resources.ResourceLocation
import net.minecraft.util.Mth
import software.bernie.geckolib.core.animation.AnimationState
import software.bernie.geckolib.model.GeoModel

abstract class HybridAquaticFishEntityModel<T : HybridAquaticFishEntity>(private val id: String) : GeoModel<T>() {
    override fun getModelResource(animatable: T): ResourceLocation {
        return ResourceLocation(Constants.MOD_ID, "geo/fish/$id/$id.geo.json")
    }

    override fun getTextureResource(animatable: T): ResourceLocation {
        return ResourceLocation(Constants.MOD_ID, "textures/entity/fish/$id/$id.png")
    }

    override fun getAnimationResource(animatable: T): ResourceLocation {
        return ResourceLocation(Constants.MOD_ID, "animations/$id.animation.json")
    }

    fun getLayerTextureResource(layer: String): ResourceLocation {
        return ResourceLocation(Constants.MOD_ID, "textures/entity/fish/$id/layers/${id}_$layer.png")
    }

    override fun setCustomAnimations(
        animatable: T,
        instanceId: Long,
        animationState: AnimationState<T>
    ) {
        super.setCustomAnimations(animatable, instanceId, animationState)
        val deltaTime: Float = Minecraft.getInstance().deltaFrameTime

        val body = animationProcessor.getBone(PartNames.BODY)

        val xRot = Mth.clamp(Mth.lerp(deltaTime, animatable.xRot, animatable.xRotO), -45f, 45f)
        body.rotX = xRot * -Mth.RAD_TO_DEG
    }
}