package dev.hybridlabs.aquatic.client.model.entity.fish

import dev.hybridlabs.aquatic.CommonClass
import dev.hybridlabs.aquatic.entity.fish.HerringEntity
import net.minecraft.client.Minecraft
import net.minecraft.client.model.geom.PartNames
import net.minecraft.resources.ResourceLocation
import net.minecraft.util.Mth
import software.bernie.geckolib.core.animation.AnimationState

class HerringEntityModel : HybridAquaticFishEntityModel<HerringEntity>("herring") {

    override fun getModelResource(animatable: HerringEntity): ResourceLocation {
        val herrings = animatable.getFishCount()

        val texturePath = when (herrings) {
            1 -> "geo/fish/herring/herring.geo.json"
            2 -> "geo/fish/herring/herring_two.geo.json"
            3 -> "geo/fish/herring/herring_three.geo.json"
            else -> "geo/fish/herring/herring.geo.json"
        }
        return CommonClass.locate(texturePath)
    }

    override fun getAnimationResource(animatable: HerringEntity): ResourceLocation {
        val herrings = animatable.getFishCount()

        val texturePath = when (herrings) {
            1 -> "animations/herring.animation.json"
            2 -> "animations/herring_two.animation.json"
            3 -> "animations/herring_three.animation.json"
            else -> "animations/herring.animation.json"
        }
        return CommonClass.locate(texturePath)
    }

    override fun setCustomAnimations(
        animatable: HerringEntity,
        instanceId: Long,
        animationState: AnimationState<HerringEntity>
    ) {
        super.setCustomAnimations(animatable, instanceId, animationState)
        val deltaTime: Float = Minecraft.getInstance().deltaFrameTime

        val body = animationProcessor.getBone(PartNames.BODY)
        val body2 = animationProcessor.getBone("body2")
        val body3 = animationProcessor.getBone("body3")

        val xRot = Mth.clamp(Mth.lerp(deltaTime, animatable.xRot, animatable.xRotO), -45f, 45f)
        body.rotX = xRot * -Mth.DEG_TO_RAD
        body2?.rotX = xRot * -Mth.DEG_TO_RAD
        body3?.rotX = xRot * -Mth.DEG_TO_RAD
    }
}
