package dev.hybridlabs.aquatic.client.model.entity.fish

import dev.hybridlabs.aquatic.entity.fish.OarfishEntity
import net.minecraft.client.Minecraft
import net.minecraft.client.model.geom.PartNames
import net.minecraft.client.renderer.RenderType
import net.minecraft.resources.ResourceLocation
import net.minecraft.util.Mth
import software.bernie.geckolib.core.animation.AnimationState

class OarfishEntityModel : HybridAquaticFishEntityModel<OarfishEntity>("oarfish") {
    override fun getRenderType(animatable: OarfishEntity, texture: ResourceLocation): RenderType {
        return RenderType.entityTranslucent(texture)
    }

    override fun setCustomAnimations(
        animatable: OarfishEntity,
        instanceId: Long,
        animationState: AnimationState<OarfishEntity>
    ) {
        if (!animatable.isFeeding()) return

        super.setCustomAnimations(animatable, instanceId, animationState)

        val deltaTime: Float = Minecraft.getInstance().deltaFrameTime

        val body = animationProcessor.getBone(PartNames.BODY)

        val xRot = Mth.clamp(Mth.lerp(deltaTime, animatable.xRot, animatable.xRotO), -45f, 45f)
        body.rotX = xRot * -Mth.DEG_TO_RAD
    }
}
