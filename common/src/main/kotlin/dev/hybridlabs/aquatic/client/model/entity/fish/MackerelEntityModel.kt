package dev.hybridlabs.aquatic.client.model.entity.fish

import dev.hybridlabs.aquatic.CommonClass
import dev.hybridlabs.aquatic.entity.fish.MackerelEntity
import net.minecraft.client.Minecraft
import net.minecraft.client.model.geom.PartNames
import net.minecraft.resources.ResourceLocation
import net.minecraft.util.Mth
import software.bernie.geckolib.core.animation.AnimationState

class MackerelEntityModel : HybridAquaticFishEntityModel<MackerelEntity>("mackerel") {

    override fun getModelResource(animatable: MackerelEntity): ResourceLocation {
        val mackerels = animatable.getFishCount()

        val texturePath = when (mackerels) {
            1 -> "geo/fish/mackerel/mackerel.geo.json"
            2 -> "geo/fish/mackerel/mackerel_two.geo.json"
            3 -> "geo/fish/mackerel/mackerel_three.geo.json"
            else -> "geo/fish/mackerel/mackerel.geo.json"
        }
        return CommonClass.locate(texturePath)
    }

    override fun getAnimationResource(animatable: MackerelEntity): ResourceLocation {
        val mackerels = animatable.getFishCount()

        val texturePath = when (mackerels) {
            1 -> "animations/mackerel.animation.json"
            2 -> "animations/mackerel_two.animation.json"
            3 -> "animations/mackerel_three.animation.json"
            else -> "animations/mackerel.animation.json"
        }
        return CommonClass.locate(texturePath)
    }

    override fun setCustomAnimations(
        animatable: MackerelEntity,
        instanceId: Long,
        animationState: AnimationState<MackerelEntity>
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