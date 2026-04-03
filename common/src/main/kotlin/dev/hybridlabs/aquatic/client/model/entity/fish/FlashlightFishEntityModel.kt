package dev.hybridlabs.aquatic.client.model.entity.fish

import dev.hybridlabs.aquatic.CommonClass
import dev.hybridlabs.aquatic.entity.fish.FlashlightFishEntity
import net.minecraft.client.model.geom.PartNames
import net.minecraft.client.renderer.RenderType
import net.minecraft.resources.ResourceLocation
import net.minecraft.util.Mth
import software.bernie.geckolib.core.animation.AnimationState
import kotlin.math.abs

class FlashlightFishEntityModel : HAFishEntityModel<FlashlightFishEntity>("flashlight_fish") {
    override fun getRenderType(animatable: FlashlightFishEntity, texture: ResourceLocation): RenderType {
        return RenderType.entityTranslucent(texture)
    }

    override fun getModelResource(animatable: FlashlightFishEntity): ResourceLocation {
        val fish = animatable.getFishCount()

        val modelPath = when (fish) {
            1 -> "geo/fish/flashlight_fish/flashlight_fish.geo.json"
            2 -> "geo/fish/flashlight_fish/flashlight_fish_two.geo.json"
            3 -> "geo/fish/flashlight_fish/flashlight_fish_three.geo.json"
            else -> "geo/fish/flashlight_fish/flashlight_fish.geo.json"
        }
        return CommonClass.locate(modelPath)
    }

    override fun getAnimationResource(animatable: FlashlightFishEntity): ResourceLocation {
        val fish = animatable.getFishCount()

        val animationPath = when (fish) {
            1 -> "animations/entity/fish/flashlight_fish/flashlight_fish.animation.json"
            2 -> "animations/entity/fish/flashlight_fish/flashlight_fish_two.animation.json"
            3 -> "animations/entity/fish/flashlight_fish/flashlight_fish_three.animation.json"
            else -> "animations/entity/fish/flashlight_fish/flashlight_fish.animation.json"
        }
        return CommonClass.locate(animationPath)
    }

    override fun setCustomAnimations(
        animatable: FlashlightFishEntity,
        instanceId: Long,
        animationState: AnimationState<FlashlightFishEntity>
    ) {
        super.setCustomAnimations(animatable, instanceId, animationState)
        val deltaTime: Float = animationState.partialTick

        val body = animationProcessor.getBone(PartNames.BODY)
        val body2 = animationProcessor.getBone("body2")
        val body3 = animationProcessor.getBone("body3")
        val tail2 = animationProcessor.getBone("tail2")
        val tail3 = animationProcessor.getBone("tail3")
        val tailFin2 = animationProcessor.getBone("tail_fin2")
        val tailFin3 = animationProcessor.getBone("tail_fin3")

        val xRot = Mth.clamp(Mth.lerp(deltaTime, animatable.xRot, animatable.xRotO), -45f, 45f)
        body.rotX = xRot * -Mth.DEG_TO_RAD
        body2?.rotX = xRot * -Mth.DEG_TO_RAD
        body3?.rotX = xRot * -Mth.DEG_TO_RAD

        val yawDiff = animatable.yRot - animatable.yRotO
        val targetRoll = Mth.clamp(yawDiff * 3f, -30f, 30f)

        val turnSpeed = abs(yawDiff)
        val smoothing = Mth.clamp(0.05f + turnSpeed * 0.02f, 0.05f, 0.25f)
        animatable.currentRoll = Mth.lerp(smoothing, animatable.currentRoll, targetRoll)

        val roll = Mth.lerp(deltaTime, animatable.prevRoll, animatable.currentRoll)
        body.rotZ = roll * -Mth.DEG_TO_RAD
        body2?.rotZ = roll * -Mth.DEG_TO_RAD
        body3?.rotZ = roll * -Mth.DEG_TO_RAD
        tail2?.rotY = roll * Mth.DEG_TO_RAD
        tail3?.rotY = roll * Mth.DEG_TO_RAD
        tailFin2?.rotY = roll * Mth.DEG_TO_RAD
        tailFin3?.rotY = roll * Mth.DEG_TO_RAD
    }
}