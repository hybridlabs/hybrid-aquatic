package dev.hybridlabs.aquatic.client.model.entity.crustacean

import dev.hybridlabs.aquatic.HybridAquatic
import dev.hybridlabs.aquatic.entity.crustacean.HybridAquaticCrustaceanEntity
import net.minecraft.client.render.entity.model.EntityModelPartNames
import net.minecraft.util.Identifier
import software.bernie.geckolib.animation.AnimationState
import software.bernie.geckolib.model.GeoModel
import kotlin.math.round

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

            //
            val snappedAngle = snapAngle(entityYaw, 4);

//            animatable.isCustomNameVisible = true;
//            animatable.customName = Text.literal("Snapped angle: $snappedAngle");
            body.rotY = snappedAngle;
        }
    }

    private fun snapAngle(angle : Float, slices : Int) : Float {
        val normalized = angle % 360;
        val sliceSize = 360 / slices;

        val sliceCenter = round(normalized / sliceSize) * sliceSize;
        return sliceCenter;
    }
}
