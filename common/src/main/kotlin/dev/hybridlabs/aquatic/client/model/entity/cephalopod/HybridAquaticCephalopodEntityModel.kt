package dev.hybridlabs.aquatic.client.model.entity.cephalopod

import dev.hybridlabs.aquatic.Constants
import dev.hybridlabs.aquatic.entity.cephalopod.HybridAquaticCephalopodEntity
import net.minecraft.client.Minecraft
import net.minecraft.client.model.geom.PartNames
import net.minecraft.resources.ResourceLocation
import net.minecraft.util.Mth
import software.bernie.geckolib.core.animation.AnimationState
import software.bernie.geckolib.model.GeoModel

abstract class HybridAquaticCephalopodEntityModel<T : HybridAquaticCephalopodEntity>(private val id: String) :
    GeoModel<T>() {

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
        val deltaTime: Float = Minecraft.getInstance().deltaFrameTime

        val body = animationProcessor.getBone(PartNames.BODY)
        body.rotX = Mth.lerp(deltaTime, animatable.xRot, animatable.xRotO) * -Mth.DEG_TO_RAD
    }
}