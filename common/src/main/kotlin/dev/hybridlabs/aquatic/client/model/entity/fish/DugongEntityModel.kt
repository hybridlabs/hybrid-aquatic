package dev.hybridlabs.aquatic.client.model.entity.fish

import dev.hybridlabs.aquatic.client.model.entity.mammal.HybridAquaticMammalEntityModel
import dev.hybridlabs.aquatic.entity.mammal.DugongEntity
import net.minecraft.client.Minecraft
import net.minecraft.client.model.geom.PartNames
import net.minecraft.util.Mth
import software.bernie.geckolib.core.animation.AnimationState
import kotlin.math.abs

class DugongEntityModel : HybridAquaticMammalEntityModel<DugongEntity>("dugong") {

    override fun setCustomAnimations(
        animatable: DugongEntity,
        instanceId: Long,
        animationState: AnimationState<DugongEntity>
    ) {
        val deltaTime: Float = Minecraft.getInstance().deltaFrameTime

        val head = animationProcessor.getBone(PartNames.HEAD)
        val body = animationProcessor.getBone(PartNames.BODY)
        val tail = animationProcessor.getBone(PartNames.TAIL)

        val tilt = Mth.clamp(Mth.lerp(deltaTime, animatable.xRotO, animatable.xRot), -45f, 45f)

        val yawDiff = animatable.yRot - animatable.yRotO
        val targetRoll = Mth.clamp(yawDiff * 3f, -30f, 30f)

        val turnSpeed = abs(yawDiff)
        val smoothing = Mth.clamp(0.05f + turnSpeed * 0.02f, 0.05f, 0.25f)
        animatable.currentRoll = Mth.lerp(smoothing, animatable.currentRoll, targetRoll)

        val roll = Mth.lerp(deltaTime, animatable.prevRoll, animatable.currentRoll)

        head.rotY += roll * -Mth.DEG_TO_RAD
        body.rotX += tilt * -Mth.DEG_TO_RAD
        body.rotZ += roll * -Mth.DEG_TO_RAD
        tail.rotY += roll * Mth.DEG_TO_RAD
    }
}
