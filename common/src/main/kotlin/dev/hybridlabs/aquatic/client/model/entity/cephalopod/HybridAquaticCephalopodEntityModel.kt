package dev.hybridlabs.aquatic.client.model.entity.cephalopod

import dev.hybridlabs.aquatic.CommonClass
import dev.hybridlabs.aquatic.entity.cephalopod.HybridAquaticCephalopodEntity
import net.minecraft.client.DeltaTracker
import net.minecraft.client.Minecraft
import net.minecraft.client.model.geom.PartNames
import net.minecraft.resources.ResourceLocation
import net.minecraft.util.Mth
import software.bernie.geckolib.animation.AnimationState
import software.bernie.geckolib.model.GeoModel

abstract class HybridAquaticCephalopodEntityModel<T : HybridAquaticCephalopodEntity>(private val id: String) :
    GeoModel<T>() {

    override fun getModelResource(animatable: T): ResourceLocation {
        return CommonClass.locate("geo/cephalopod/$id/$id.geo.json")
    }

    override fun getTextureResource(animatable: T): ResourceLocation {
        return CommonClass.locate("textures/entity/cephalopod/$id/$id.png")
    }

    override fun getAnimationResource(animatable: T): ResourceLocation {
        return CommonClass.locate("animations/$id.animation.json")
    }

    override fun setCustomAnimations(
        animatable: T,
        instanceId: Long,
        animationState: AnimationState<T>
    ) {
        super.setCustomAnimations(animatable, instanceId, animationState)
        val deltaTime: DeltaTracker = Minecraft.getInstance().timer

        val body = animationProcessor.getBone(PartNames.BODY)
        body.rotX = Mth.lerp(deltaTime.gameTimeDeltaTicks, animatable.xRot, animatable.xRotO) * -Mth.DEG_TO_RAD
    }
}