package dev.hybridlabs.aquatic.client.model.entity.miniboss

import dev.hybridlabs.aquatic.entity.miniboss.ManglerfishEntity
import net.minecraft.client.model.geom.PartNames
import net.minecraft.util.Mth
import software.bernie.geckolib.core.animation.AnimationState

class ManglerfishEntityModel : HybridAquaticMinibossEntityModel<ManglerfishEntity>("manglerfish") {

    override fun setCustomAnimations(
        animatable: ManglerfishEntity,
        instanceId: Long,
        animationState: AnimationState<ManglerfishEntity>
    ) {
        super.setCustomAnimations(animatable, instanceId, animationState)
        val deltaTime: Float = animationState.partialTick

        val body = animationProcessor.getBone(PartNames.BODY)

        val xRot = Mth.clamp(Mth.lerp(deltaTime, animatable.xRotO, animatable.xRot), -45f, 45f)
        body.rotX = xRot * -Mth.DEG_TO_RAD
    }
}