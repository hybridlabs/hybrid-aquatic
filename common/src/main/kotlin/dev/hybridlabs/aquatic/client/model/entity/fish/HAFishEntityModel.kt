package dev.hybridlabs.aquatic.client.model.entity.fish

import dev.hybridlabs.aquatic.CommonClass
import dev.hybridlabs.aquatic.entity.base.HAFishEntity
import net.minecraft.client.model.geom.PartNames
import net.minecraft.resources.ResourceLocation
import net.minecraft.util.Mth
import software.bernie.geckolib.core.animation.AnimationState
import software.bernie.geckolib.model.GeoModel
import kotlin.math.abs

@Suppress("OVERRIDE_DEPRECATION")
abstract class HAFishEntityModel<T : HAFishEntity>(private val id: String) :
    GeoModel<T>() {
    override fun getModelResource(animatable: T): ResourceLocation {
        return CommonClass.locate("geo/fish/$id/$id.geo.json")
    }

    override fun getTextureResource(animatable: T): ResourceLocation {
        return CommonClass.locate("textures/entity/fish/$id/$id.png")
    }

    override fun getAnimationResource(animatable: T): ResourceLocation {
        return CommonClass.locate("animations/entity/fish/$id/$id.animation.json")
    }

    fun getLayerTextureResource(layer: String): ResourceLocation {
        return CommonClass.locate("textures/entity/fish/$id/layers/${id}_$layer.png")
    }

    override fun setCustomAnimations(
        animatable: T,
        instanceId: Long,
        animationState: AnimationState<T>
    ) {
        super.setCustomAnimations(animatable, instanceId, animationState)

        if (!animatable.isInWater && animatable.moistness < 580) {
            animatable.triggerAnim("Fish Controller", "misc.flop")
            return
        }

        val deltaTime = animationState.partialTick
        val body = animationProcessor.getBone(PartNames.BODY)
        val torso = animationProcessor.getBone("torso")
        val torso2 = animationProcessor.getBone("torso_2")
        val tail = animationProcessor.getBone(PartNames.TAIL)
        val tailFin = animationProcessor.getBone(PartNames.TAIL_FIN)

        val tilt = Mth.clamp(
            Mth.lerp(deltaTime, animatable.xRotO, animatable.xRot),
            -35f, 35f
        )

        val yawDiff = animatable.yRot - animatable.yRotO
        val targetRoll = Mth.clamp(yawDiff * 3f, -30f, 30f)

        val turnSpeed = abs(yawDiff)
        val smoothing = Mth.clamp(0.05f + turnSpeed * 0.02f, 0.05f, 0.25f)
        animatable.currentRoll = Mth.lerp(smoothing, animatable.currentRoll, targetRoll)

        val roll = Mth.lerp(deltaTime, animatable.prevRoll, animatable.currentRoll)

        body?.rotX = tilt * -Mth.DEG_TO_RAD
        torso?.rotZ = roll * -Mth.DEG_TO_RAD
        torso2?.rotY = torso2.rotY + roll * Mth.DEG_TO_RAD
        tail?.rotY = tail.rotY + roll * Mth.DEG_TO_RAD
        tailFin?.rotY = tailFin.rotY + roll * Mth.DEG_TO_RAD
    }
}