package dev.hybridlabs.aquatic.client.model.entity.fish

import dev.hybridlabs.aquatic.CommonClass
import dev.hybridlabs.aquatic.entity.fish.DamselfishEntity
import net.minecraft.client.Minecraft
import net.minecraft.client.model.geom.PartNames
import net.minecraft.resources.ResourceLocation
import net.minecraft.util.Mth
import software.bernie.geckolib.animation.AnimationState

class DamselfishEntityModel : HybridAquaticFishEntityModel<DamselfishEntity>("damselfish") {

    override fun getModelResource(animatable: DamselfishEntity): ResourceLocation {
        val fish = animatable.getFishCount()

        val modelPath = when (fish) {
            1 -> "geo/fish/damselfish/damselfish.geo.json"
            2 -> "geo/fish/damselfish/damselfish_two.geo.json"
            3 -> "geo/fish/damselfish/damselfish_three.geo.json"
            else -> "geo/fish/damselfish/damselfish.geo.json"
        }
        return CommonClass.locate(modelPath)
    }

    override fun getAnimationResource(animatable: DamselfishEntity): ResourceLocation {
        val fish = animatable.getFishCount()

        val animationPath = when (fish) {
            1 -> "animations/damselfish.animation.json"
            2 -> "animations/damselfish_two.animation.json"
            3 -> "animations/damselfish_three.animation.json"
            else -> "animations/damselfish.animation.json"
        }
        return CommonClass.locate(animationPath)
    }

    override fun setCustomAnimations(
        animatable: DamselfishEntity,
        instanceId: Long,
        animationState: AnimationState<DamselfishEntity>
    ) {
        super.setCustomAnimations(animatable,instanceId,animationState)
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

