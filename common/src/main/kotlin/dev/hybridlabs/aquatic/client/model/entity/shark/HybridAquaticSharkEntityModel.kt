package dev.hybridlabs.aquatic.client.model.entity.shark

import dev.hybridlabs.aquatic.HybridAquatic
import dev.hybridlabs.aquatic.entity.shark.HybridAquaticSharkEntity
import net.minecraft.client.Minecraft
import net.minecraft.client.model.geom.PartNames
import net.minecraft.resources.ResourceLocation
import net.minecraft.util.Mth
import software.bernie.geckolib.core.animation.AnimationState
import software.bernie.geckolib.model.GeoModel

abstract class HybridAquaticSharkEntityModel<T : HybridAquaticSharkEntity>(
    private val id: String
) : GeoModel<T>() {
    override fun getModelResource(animatable: T): ResourceLocation {
        return ResourceLocation(HybridAquatic.MOD_ID, "geo/shark/$id.geo.json")
    }

    override fun getTextureResource(animatable: T): ResourceLocation {
        return ResourceLocation(HybridAquatic.MOD_ID, "textures/entity/shark/$id.png")
    }

    override fun getAnimationResource(animatable: T): ResourceLocation {
        return ResourceLocation(HybridAquatic.MOD_ID, "animations/$id.animation.json")
    }

    override fun setCustomAnimations(
        animatable: T,
        instanceId: Long,
        animationState: AnimationState<T>
    ) {
        super.setCustomAnimations(animatable, instanceId, animationState)
        val deltaTime: Float = Minecraft.getInstance().deltaFrameTime

        val body = animationProcessor.getBone(PartNames.BODY)

        val pitch = Mth.clamp(Mth.lerp(deltaTime, animatable.xRotO, animatable.xRot), -45f, 45f)
        body.rotX = pitch * -Mth.DEG_TO_RAD
    }
}