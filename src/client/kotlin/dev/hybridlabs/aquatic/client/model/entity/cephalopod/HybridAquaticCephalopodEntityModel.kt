package dev.hybridlabs.aquatic.client.model.entity.cephalopod

import dev.hybridlabs.aquatic.HybridAquatic
import dev.hybridlabs.aquatic.entity.cephalopod.HybridAquaticCephalopodEntity
import net.minecraft.client.MinecraftClient
import net.minecraft.client.render.entity.model.EntityModelPartNames
import net.minecraft.util.Identifier
import net.minecraft.util.math.MathHelper
import software.bernie.geckolib.core.animation.AnimationState
import software.bernie.geckolib.model.GeoModel

abstract class HybridAquaticCephalopodEntityModel<T: HybridAquaticCephalopodEntity> (private val id: String) : GeoModel<T>() {
    override fun getModelResource(animatable: T): Identifier {
        val variant = animatable.variant
        if (variant != null && !variant.ignore.contains(HybridAquaticCephalopodEntity.CephalopodVariant.Ignore.MODEL))
            return Identifier(HybridAquatic.MOD_ID, "geo/cephalopod/${id}/${id}_${variant.variantName}.geo.json")
        return Identifier(HybridAquatic.MOD_ID, "geo/cephalopod/${id}/$id.geo.json")
    }

    override fun getTextureResource(animatable: T): Identifier {
        val variant = animatable.variant
        if (variant != null && !variant.ignore.contains(HybridAquaticCephalopodEntity.CephalopodVariant.Ignore.TEXTURE))
            return Identifier(HybridAquatic.MOD_ID, "textures/entity/cephalopod/$id/${id}_${variant.variantName}.png")
        return Identifier(HybridAquatic.MOD_ID, "textures/entity/cephalopod/$id/$id.png")
    }

    override fun getAnimationResource(animatable: T): Identifier {
        val variant = animatable.variant
        if (variant != null && !variant.ignore.contains(HybridAquaticCephalopodEntity.CephalopodVariant.Ignore.ANIMATION))
            return Identifier(HybridAquatic.MOD_ID, "animations/${id}_${variant.variantName}.animation.json")
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
        body.rotX = MathHelper.lerp(deltaTime, animatable.prevPitch, animatable.pitch) * -MathHelper.RADIANS_PER_DEGREE
    }
}