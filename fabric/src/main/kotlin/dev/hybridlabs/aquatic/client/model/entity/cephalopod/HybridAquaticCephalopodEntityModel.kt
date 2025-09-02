package dev.hybridlabs.aquatic.client.model.entity.cephalopod

import dev.hybridlabs.aquatic.HybridAquatic
import dev.hybridlabs.aquatic.entity.cephalopod.HybridAquaticCephalopodEntity
import net.minecraft.client.Minecraft
import net.minecraft.client.model.geom.PartNames
import net.minecraft.resources.ResourceLocation
import net.minecraft.util.Mth
import software.bernie.geckolib.core.animation.AnimationState
import software.bernie.geckolib.model.GeoModel

abstract class HybridAquaticCephalopodEntityModel<T : HybridAquaticCephalopodEntity>(private val id: String) :
    GeoModel<T>() {
    override fun getModelResource(animatable: T): ResourceLocation {
        val variant = animatable.variant
        if (variant != null && !variant.ignore.contains(HybridAquaticCephalopodEntity.CephalopodVariant.Ignore.MODEL))
            return ResourceLocation(HybridAquatic.MOD_ID, "geo/cephalopod/${id}/${id}_${variant.variantName}.geo.json")
        return ResourceLocation(HybridAquatic.MOD_ID, "geo/cephalopod/${id}/$id.geo.json")
    }

    override fun getTextureResource(animatable: T): ResourceLocation {
        val variant = animatable.variant
        if (variant != null && !variant.ignore.contains(HybridAquaticCephalopodEntity.CephalopodVariant.Ignore.TEXTURE))
            return ResourceLocation(
                HybridAquatic.MOD_ID,
                "textures/entity/cephalopod/$id/${id}_${variant.variantName}.png"
            )
        return ResourceLocation(HybridAquatic.MOD_ID, "textures/entity/cephalopod/$id/$id.png")
    }

    override fun getAnimationResource(animatable: T): ResourceLocation {
        val variant = animatable.variant
        if (variant != null && !variant.ignore.contains(HybridAquaticCephalopodEntity.CephalopodVariant.Ignore.ANIMATION))
            return ResourceLocation(HybridAquatic.MOD_ID, "animations/${id}_${variant.variantName}.animation.json")
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
        body.rotX = Mth.lerp(deltaTime, animatable.xRotO, animatable.xRot) * -Mth.DEG_TO_RAD
    }
}