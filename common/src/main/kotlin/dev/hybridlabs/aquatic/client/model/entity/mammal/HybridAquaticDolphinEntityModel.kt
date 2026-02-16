package dev.hybridlabs.aquatic.client.model.entity.mammal

import dev.hybridlabs.aquatic.CommonClass
import dev.hybridlabs.aquatic.entity.mammal.HybridAquaticDolphinEntity
import net.minecraft.client.Minecraft
import net.minecraft.client.model.geom.PartNames
import net.minecraft.resources.ResourceLocation
import net.minecraft.util.Mth
import software.bernie.geckolib.core.animation.AnimationState
import software.bernie.geckolib.model.GeoModel
import kotlin.math.abs

@Suppress("OVERRIDE_DEPRECATION")
abstract class HybridAquaticDolphinEntityModel<T : HybridAquaticDolphinEntity>(private val id: String) :
    GeoModel<T>() {
    override fun getModelResource(animatable: T): ResourceLocation {
        return if (animatable.isBaby) {
            CommonClass.locate("geo/mammal/$id/baby_$id.geo.json")
        } else {
            CommonClass.locate("geo/mammal/$id/$id.geo.json")
        }
    }

    override fun getTextureResource(animatable: T): ResourceLocation {
        return if (animatable.isBaby) {
            CommonClass.locate("textures/entity/mammal/$id/baby_$id.png")
        } else {
            CommonClass.locate("textures/entity/mammal/$id/$id.png")
        }
    }

    override fun getAnimationResource(animatable: T): ResourceLocation {
        return CommonClass.locate("animations/$id.animation.json")
    }

    fun getLayerTextureResource(layer: String): ResourceLocation {
        return CommonClass.locate("textures/entity/mammal/$id/layers/${id}_$layer.png")
    }

    override fun setCustomAnimations(
        animatable: T,
        instanceId: Long,
        animationState: AnimationState<T>
    ) {
        super.setCustomAnimations(animatable, instanceId, animationState)

        val deltaTime = Minecraft.getInstance().deltaFrameTime
        val head = animationProcessor.getBone(PartNames.HEAD)
        val body = animationProcessor.getBone(PartNames.BODY)
        val body2 = animationProcessor.getBone("body_2")
        val tail = animationProcessor.getBone(PartNames.TAIL)

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

        head.rotY += roll * -Mth.DEG_TO_RAD
        body.rotX = tilt * -Mth.DEG_TO_RAD
        body.rotZ = roll * -Mth.DEG_TO_RAD
        body2?.rotY += roll * Mth.DEG_TO_RAD
        tail.rotY += roll * Mth.DEG_TO_RAD
    }
}