package dev.hybridlabs.aquatic.client.model.entity.crustacean

import dev.hybridlabs.aquatic.HybridAquatic
import dev.hybridlabs.aquatic.entity.crustacean.HybridAquaticCrustaceanEntity
import net.minecraft.client.render.entity.model.EntityModelPartNames
import net.minecraft.util.Identifier
import net.minecraft.util.math.MathHelper
import software.bernie.geckolib.core.animation.AnimationState
import software.bernie.geckolib.model.GeoModel
import kotlin.math.sin

abstract class HybridAquaticCrustaceanEntityModel<T : HybridAquaticCrustaceanEntity>(private val id: String) :
    GeoModel<T>() {
    override fun getModelResource(animatable: T): Identifier {
        val variant = animatable.variant
        if (variant != null && !variant.ignore.contains(HybridAquaticCrustaceanEntity.CrustaceanVariant.Ignore.MODEL))
            return Identifier(
                HybridAquatic.MOD_ID,
                "geo/crustacean/${id}/${id}_${variant.getProvidedVariant(animatable)}.geo.json"
            )
        return Identifier(HybridAquatic.MOD_ID, "geo/crustacean/${id}/$id.geo.json")
    }

    override fun getTextureResource(animatable: T): Identifier {
        val variant = animatable.variant
        if (variant != null && !variant.ignore.contains(HybridAquaticCrustaceanEntity.CrustaceanVariant.Ignore.TEXTURE))
            return Identifier(
                HybridAquatic.MOD_ID,
                "textures/entity/crustacean/${id}/${id}_${variant.getProvidedVariant(animatable)}.png"
            )
        return Identifier(HybridAquatic.MOD_ID, "textures/entity/crustacean/${id}/$id.png")
    }

    override fun getAnimationResource(animatable: T): Identifier {
        val variant = animatable.variant
        if (variant != null && !variant.ignore.contains(HybridAquaticCrustaceanEntity.CrustaceanVariant.Ignore.ANIMATION))
            return Identifier(
                HybridAquatic.MOD_ID,
                "animations/${id}_${variant.getProvidedVariant(animatable)}.animation.json"
            )
        return Identifier(HybridAquatic.MOD_ID, "animations/$id.animation.json")
    }

    override fun setCustomAnimations(
        animatable: T,
        instanceId: Long,
        animationState: AnimationState<T>
    ) {
        super.setCustomAnimations(animatable, instanceId, animationState)

        val body = animationProcessor.getBone(EntityModelPartNames.BODY)
        val entityYaw = animatable.yaw;

        if (animatable.isClimbing) {
            body.rotX = Math.toRadians(90.0).toFloat()

            when (entityYaw) {
                in 135.01f..225.0f -> {
                    body.rotY = Math.toRadians(180.0).toFloat()
                }
                in 225.01f..315.0f -> {
                    body.rotY = Math.toRadians(270.0).toFloat()
                }
                in 315.01f..360.0f, in 0.0f..45.0f -> {
                    body.rotY = Math.toRadians(0.0).toFloat()
                }
                in 45.01f..135.0f -> {
                    body.rotY = Math.toRadians(90.0).toFloat()
                }
            }
        }
    }
}