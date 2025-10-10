package dev.hybridlabs.aquatic.client.model.entity.fish

import dev.hybridlabs.aquatic.CommonClass
import dev.hybridlabs.aquatic.entity.fish.FlashlightFishEntity
import net.minecraft.client.Minecraft
import net.minecraft.client.model.geom.PartNames
import net.minecraft.client.renderer.RenderType
import net.minecraft.resources.ResourceLocation
import net.minecraft.util.Mth
import software.bernie.geckolib.animation.AnimationState

class FlashlightFishEntityModel : HybridAquaticFishEntityModel<FlashlightFishEntity>("flashlight_fish") {
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
            1 -> "animations/flashlight_fish.animation.json"
            2 -> "animations/flashlight_fish_two.animation.json"
            3 -> "animations/flashlight_fish_three.animation.json"
            else -> "animations/flashlight_fish.animation.json"
        }
        return CommonClass.locate(animationPath)
    }

    override fun setCustomAnimations(
        animatable: FlashlightFishEntity,
        instanceId: Long,
        animationState: AnimationState<FlashlightFishEntity>
    ) {
        super.setCustomAnimations(animatable, instanceId, animationState)
        val timer = Minecraft.getInstance().timer

        val body = animationProcessor.getBone(PartNames.BODY)
        val body2 = animationProcessor.getBone("body2")
        val body3 = animationProcessor.getBone("body3")

        val xRot = Mth.clamp(Mth.lerp(timer.gameTimeDeltaTicks, animatable.xRot, animatable.xRotO), -45f, 45f)
        body.rotX = xRot * -Mth.DEG_TO_RAD
        body2?.rotX = xRot * -Mth.DEG_TO_RAD
        body3?.rotX = xRot * -Mth.DEG_TO_RAD
    }
}