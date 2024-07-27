package dev.hybridlabs.aquatic.client.model.entity.fish

import dev.hybridlabs.aquatic.HybridAquatic
import dev.hybridlabs.aquatic.entity.fish.ray.HybridAquaticRayEntity
import dev.hybridlabs.aquatic.entity.fish.ray.HybridAquaticRayEntity.RayVariant.Ignore.*
import net.minecraft.client.MinecraftClient
import net.minecraft.client.render.entity.model.EntityModelPartNames
import net.minecraft.util.Identifier
import net.minecraft.util.math.MathHelper
import software.bernie.geckolib.core.animation.AnimationState
import software.bernie.geckolib.model.GeoModel

abstract class HybridAquaticRayEntityModel<T: HybridAquaticRayEntity> (private val id: String) : GeoModel<T>() {
    override fun getModelResource(animatable: T?): Identifier {
        val variant = animatable?.variant
        if (variant != null && !variant.ignore.contains(MODEL))
            return Identifier.of(HybridAquatic.MOD_ID, "geo/fish/${id}/${id}_${variant.getProvidedVariant(animatable)}.geo.json")
        return Identifier.of(HybridAquatic.MOD_ID, "geo/fish/${id}/$id.geo.json")
    }

    override fun getTextureResource(animatable: T?): Identifier {
        val variant = animatable?.variant
        if (variant != null && !variant.ignore.contains(TEXTURE))
            return Identifier.of(HybridAquatic.MOD_ID, "textures/entity/fish/$id/${id}_${variant.getProvidedVariant(animatable)}.png")
        return Identifier.of(HybridAquatic.MOD_ID, "textures/entity/fish/$id/$id.png")
    }

    override fun getAnimationResource(animatable: T?): Identifier {
        val variant = animatable?.variant
        if (variant != null && !variant.ignore.contains(ANIMATION))
            return Identifier.of(HybridAquatic.MOD_ID, "animations/${id}_${variant.getProvidedVariant(animatable)}.animation.json")
        return Identifier.of(HybridAquatic.MOD_ID, "animations/$id.animation.json")
    }

    override fun setCustomAnimations(
        animatable: T,
        instanceId: Long,
        animationState: AnimationState<T>
    ) {
        super.setCustomAnimations(animatable, instanceId, animationState)
        val deltaTime: Float = MinecraftClient.getInstance().tickDelta

        val body = animationProcessor.getBone(EntityModelPartNames.BODY)
        body.rotX = MathHelper.lerp(deltaTime, animatable.prevPitch, animatable.pitch) * -MathHelper.RADIANS_PER_DEGREE
    }
}
