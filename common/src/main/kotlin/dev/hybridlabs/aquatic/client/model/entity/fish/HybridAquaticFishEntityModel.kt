package dev.hybridlabs.aquatic.client.model.entity.fish

import dev.hybridlabs.aquatic.CommonClass
import dev.hybridlabs.aquatic.entity.fish.HybridAquaticFishEntity
import net.minecraft.client.Minecraft
import net.minecraft.client.model.geom.PartNames
import net.minecraft.resources.ResourceLocation
import net.minecraft.util.Mth
import software.bernie.geckolib.core.animation.AnimationState
import software.bernie.geckolib.model.GeoModel
import kotlin.math.abs

@Suppress("OVERRIDE_DEPRECATION")
abstract class HybridAquaticFishEntityModel<T : HybridAquaticFishEntity>(private val id: String) : GeoModel<T>() {
    override fun getModelResource(animatable: T): ResourceLocation {
        return CommonClass.locate("geo/fish/$id/$id.geo.json")
    }

    override fun getTextureResource(animatable: T): ResourceLocation {
        return CommonClass.locate("textures/entity/fish/$id/$id.png")
    }

    override fun getAnimationResource(animatable: T): ResourceLocation {
        return CommonClass.locate("animations/$id.animation.json")
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

        if (animatable.onGround() && !animatable.isInWater) {
            return
        }

        val deltaTime = Minecraft.getInstance().deltaFrameTime
        val body = animationProcessor.getBone(PartNames.BODY)
        val body2 = animationProcessor.getBone("body_2")
        val tail = animationProcessor.getBone(PartNames.TAIL)
        val tailFin = animationProcessor.getBone(PartNames.TAIL_FIN)

        val tilt = Mth.clamp(
            Mth.lerp(deltaTime, animatable.xRotO, animatable.xRot),
            -45f, 45f
        )

        val yawDiff = animatable.yRot - animatable.yRotO
        val targetRoll = Mth.clamp(yawDiff * 3f, -30f, 30f)

        val turnSpeed = abs(yawDiff)
        val smoothing = Mth.clamp(0.05f + turnSpeed * 0.02f, 0.05f, 0.25f)
        animatable.currentRoll = Mth.lerp(smoothing, animatable.currentRoll, targetRoll)

        val roll = Mth.lerp(deltaTime, animatable.prevRoll, animatable.currentRoll)

        body.rotX = tilt * -Mth.DEG_TO_RAD
        body.rotZ = roll * -Mth.DEG_TO_RAD
        body2?.rotY += roll * Mth.DEG_TO_RAD
        tail?.rotY += roll * Mth.DEG_TO_RAD
        tailFin?.rotY += roll * Mth.DEG_TO_RAD
    }
}