package dev.hybridlabs.aquatic.client.model.entity.fish

import dev.hybridlabs.aquatic.entity.fish.HerringEntity
import net.minecraft.client.model.geom.PartNames
import net.minecraft.resources.ResourceLocation
import net.minecraft.util.Mth
import software.bernie.geckolib.core.animation.AnimationState
import kotlin.math.abs

class HerringEntityModel : HAFishEntityModel<HerringEntity>("herring") {

    companion object {
        private val ONE_FISH_MODEL =
            ResourceLocation("hybrid-aquatic", "geo/fish/herring/herring.geo.json")
        private val TWO_FISH_MODEL =
            ResourceLocation("hybrid-aquatic", "geo/fish/herring/herring_two.geo.json")
        private val THREE_FISH_MODEL =
            ResourceLocation("hybrid-aquatic", "geo/fish/herring/herring_three.geo.json")

        private val ONE_FISH_ANIMATION =
            ResourceLocation("hybrid-aquatic", "animations/entity/fish/herring/herring.animation.json")
        private val TWO_FISH_ANIMATION =
            ResourceLocation("hybrid-aquatic", "animations/entity/fish/herring/herring_two.animation.json")
        private val THREE_FISH_ANIMATION =
            ResourceLocation("hybrid-aquatic", "animations/entity/fish/herring/herring_three.animation.json")
    }

    override fun getModelResource(animatable: HerringEntity): ResourceLocation {
        val fish = animatable.getFishCount()

        return when (fish) {
            1 -> ONE_FISH_MODEL
            2 -> TWO_FISH_MODEL
            3 -> THREE_FISH_MODEL
            else -> ONE_FISH_MODEL
        }
    }

    override fun getAnimationResource(animatable: HerringEntity): ResourceLocation {
        val fish = animatable.getFishCount()

        return when (fish) {
            1 -> ONE_FISH_ANIMATION
            2 -> TWO_FISH_ANIMATION
            3 -> THREE_FISH_ANIMATION
            else -> ONE_FISH_ANIMATION
        }
    }

    override fun setCustomAnimations(
        animatable: HerringEntity,
        instanceId: Long,
        animationState: AnimationState<HerringEntity>
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