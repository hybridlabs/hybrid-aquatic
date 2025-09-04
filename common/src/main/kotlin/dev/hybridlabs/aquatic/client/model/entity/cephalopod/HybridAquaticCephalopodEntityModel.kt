package dev.hybridlabs.aquatic.client.model.entity.cephalopod

import dev.hybridlabs.aquatic.Constants
import dev.hybridlabs.aquatic.HybridAquatic
import dev.hybridlabs.aquatic.entity.cephalopod.HybridAquaticCephalopodEntity
import net.minecraft.client.MinecraftClient
import net.minecraft.client.renderer.entity.model.EntityModelPartNames
import net.minecraft.resources.ResourceLocation
import net.minecraft.util.math.Mth
import software.bernie.geckolib.core.animation.AnimationState
import software.bernie.geckolib.model.GeoModel

abstract class HybridAquaticCephalopodEntityModel<T : HybridAquaticCephalopodEntity>(private val id: String) : GeoModel<T>() {

    override fun getModelResource(animatable: T): ResourceLocation {
        return ResourceLocation(Constants.MOD_ID, "geo/cephalopod/$id/$id.geo.json")
    }

    override fun getTextureResource(animatable: T): ResourceLocation {
        return ResourceLocation(Constants.MOD_ID, "textures/entity/cephalopod/$id/$id.png")
    }

    override fun getAnimationResource(animatable: T): ResourceLocation {
        return ResourceLocation(Constants.MOD_ID, "animations/$id.animation.json")
    }

    override fun setCustomAnimations(
        animatable: T,
        instanceId: Long,
        animationState: AnimationState<T>
    ) {
        super.setCustomAnimations(animatable, instanceId, animationState)
        val deltaTime: Float = MinecraftClient.getInstance().tickDelta

        val body = animationProcessor.getBone(EntityModelPartNames.BODY)
        body.rotX = Mth.lerp(deltaTime, animatable.prevPitch, animatable.pitch) * -Mth.RADIANS_PER_DEGREE
    }
}