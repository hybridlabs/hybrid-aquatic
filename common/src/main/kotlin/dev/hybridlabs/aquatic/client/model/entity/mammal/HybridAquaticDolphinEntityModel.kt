package dev.hybridlabs.aquatic.client.model.entity.mammal

import com.mojang.authlib.minecraft.client.MinecraftClient
import dev.hybridlabs.aquatic.Constants
import dev.hybridlabs.aquatic.entity.mammal.HybridAquaticDolphinEntity
import net.minecraft.resources.ResourceLocation
import net.minecraft.util.Mth
import software.bernie.geckolib.core.animation.AnimationState
import software.bernie.geckolib.model.GeoModel

abstract class HybridAquaticDolphinEntityModel<T : HybridAquaticDolphinEntity>(private val id: String) :
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
        val deltaTime: Float = MinecraftClient.getInstance().tickDelta

        val body = animationProcessor.getBone(EntityModelPartNames.BODY)

        val xRot = Mth.clamp(Mth.lerp(deltaTime, animatable.prevPitch, animatable.pitch), -45f, 45f)
        body.rotX = xRot * -Mth.RAD_TO_DEG
    }
}