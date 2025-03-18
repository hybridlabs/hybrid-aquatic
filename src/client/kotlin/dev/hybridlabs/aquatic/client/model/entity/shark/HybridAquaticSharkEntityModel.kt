package dev.hybridlabs.aquatic.client.model.entity.shark

import dev.hybridlabs.aquatic.HybridAquatic
import dev.hybridlabs.aquatic.entity.shark.HybridAquaticSharkEntity
import net.minecraft.client.MinecraftClient
import net.minecraft.client.render.entity.model.EntityModelPartNames
import net.minecraft.util.Identifier
import net.minecraft.util.math.MathHelper
import software.bernie.geckolib.core.animation.AnimationState
import software.bernie.geckolib.model.GeoModel

abstract class HybridAquaticSharkEntityModel<T : HybridAquaticSharkEntity> (
    private val id: String
) : GeoModel<T>() {
    override fun getModelResource(animatable: T): Identifier {
        return Identifier(HybridAquatic.MOD_ID, "geo/shark/$id.geo.json")
    }

    override fun getTextureResource(animatable: T): Identifier {
        return Identifier(HybridAquatic.MOD_ID, "textures/entity/shark/$id.png")
    }

    override fun getAnimationResource(animatable: T): Identifier {
        return Identifier(HybridAquatic.MOD_ID, "animations/$id.animation.json")
    }

    override fun setCustomAnimations(
        animatable: T,
        instanceId: Long,
        animationState: AnimationState<T>
    ) {
        super.setCustomAnimations(animatable, instanceId, animationState)
        val deltaTime: Float = MinecraftClient.getInstance().tickDelta

        val body = animationProcessor.getBone(EntityModelPartNames.BODY)

        val pitch = MathHelper.clamp(MathHelper.lerp(deltaTime, animatable.prevPitch, animatable.pitch), -45f, 45f)
        body.rotX = pitch * -MathHelper.RADIANS_PER_DEGREE
    }
}