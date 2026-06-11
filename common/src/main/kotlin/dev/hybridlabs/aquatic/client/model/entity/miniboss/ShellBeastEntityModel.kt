package dev.hybridlabs.aquatic.client.model.entity.miniboss

import dev.hybridlabs.aquatic.entity.miniboss.ShellBeastEntity
import net.minecraft.util.Mth
import software.bernie.geckolib.core.animation.AnimationState

class ShellBeastEntityModel : HAMinibossEntityModel<ShellBeastEntity>("shell_beast") {

    override fun setCustomAnimations(
        animatable: ShellBeastEntity,
        instanceId: Long,
        animationState: AnimationState<ShellBeastEntity>
    ) {
        super.setCustomAnimations(animatable, instanceId, animationState)
        val deltaTime: Float = animationState.partialTick

        val shellBeast = animationProcessor.getBone("shell_beast")

        val xRot = Mth.clamp(Mth.lerp(deltaTime, animatable.xRotO, animatable.xRot), -45f, 45f)
        shellBeast.rotX = xRot * -Mth.DEG_TO_RAD
    }
}