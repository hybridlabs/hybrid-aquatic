package dev.hybridlabs.aquatic.client.model.entity.fish

import dev.hybridlabs.aquatic.CommonClass
import dev.hybridlabs.aquatic.entity.fish.SquirrelfishEntity
import net.minecraft.client.Minecraft
import net.minecraft.client.model.geom.PartNames
import net.minecraft.client.renderer.RenderType
import net.minecraft.resources.ResourceLocation
import net.minecraft.util.Mth
import software.bernie.geckolib.core.animation.AnimationState
import kotlin.math.abs

class SquirrelfishEntityModel : HybridAquaticFishEntityModel<SquirrelfishEntity>("squirrelfish") {
    override fun getRenderType(animatable: SquirrelfishEntity, texture: ResourceLocation): RenderType {
        return RenderType.entityTranslucent(texture)
    }

    override fun getModelResource(animatable: SquirrelfishEntity): ResourceLocation {
        val fish = animatable.getFishCount()

        val modelPath = when (fish) {
            1 -> "geo/fish/squirrelfish/squirrelfish.geo.json"
            2 -> "geo/fish/squirrelfish/squirrelfish_two.geo.json"
            3 -> "geo/fish/squirrelfish/squirrelfish_three.geo.json"
            else -> "geo/fish/squirrelfish/squirrelfish.geo.json"
        }
        return CommonClass.locate(modelPath)
    }

    override fun getAnimationResource(animatable: SquirrelfishEntity): ResourceLocation {
        val fish = animatable.getFishCount()

        val animationPath = when (fish) {
            1 -> "animations/entity/fish/squirrelfish/squirrelfish.animation.json"
            2 -> "animations/entity/fish/squirrelfish/squirrelfish_two.animation.json"
            3 -> "animations/entity/fish/squirrelfish/squirrelfish_three.animation.json"
            else -> "animations/entity/fish/squirrelfish/squirrelfish.animation.json"
        }
        return CommonClass.locate(animationPath)
    }

    override fun setCustomAnimations(
        animatable: SquirrelfishEntity,
        instanceId: Long,
        animationState: AnimationState<SquirrelfishEntity>
    ) {
        super.setCustomAnimations(animatable, instanceId, animationState)
        val deltaTime: Float = Minecraft.getInstance().deltaFrameTime

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