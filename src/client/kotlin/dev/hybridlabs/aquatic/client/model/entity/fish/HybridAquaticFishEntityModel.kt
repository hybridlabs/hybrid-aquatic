package dev.hybridlabs.aquatic.client.model.entity.fish

import dev.hybridlabs.aquatic.HybridAquatic
import dev.hybridlabs.aquatic.entity.fish.HybridAquaticFishEntity
import net.minecraft.client.render.entity.model.EntityModelPartNames
import net.minecraft.util.Identifier
import net.minecraft.util.math.MathHelper
import software.bernie.geckolib.animation.AnimationState
import software.bernie.geckolib.model.GeoModel

abstract class HybridAquaticFishEntityModel<T: HybridAquaticFishEntity> (private val id: String) : GeoModel<T>() {
    override fun getModelResource(animatable: T): Identifier {
        return Identifier.of(HybridAquatic.MOD_ID, "geo/fish/$id/$id.geo.json")
    }

    override fun getTextureResource(animatable: T): Identifier {
        return Identifier.of(HybridAquatic.MOD_ID, "textures/entity/fish/$id/$id.png")
    }

    override fun getAnimationResource(animatable: T): Identifier {
        return Identifier.of(HybridAquatic.MOD_ID, "animations/$id.animation.json")
    }

    fun getLayerTextureResource(layer: String): Identifier {
        return Identifier.of(HybridAquatic.MOD_ID, "textures/entity/fish/$id/layers/${id}_$layer.png")
    }

    override fun setCustomAnimations(
        animatable: T,
        instanceId: Long,
        animationState: AnimationState<T>
    ) {
        super.setCustomAnimations(animatable, instanceId, animationState)
        val deltaTime: Float = animationState.partialTick

        val body = animationProcessor.getBone(EntityModelPartNames.BODY)

        val pitch = MathHelper.clamp(MathHelper.lerp(deltaTime, animatable.prevPitch, animatable.pitch), -45f, 45f)
        body.rotX = pitch * -MathHelper.RADIANS_PER_DEGREE
    }
}