package dev.hybridlabs.aquatic.client.model.entity.fish

import dev.hybridlabs.aquatic.HybridAquatic
import dev.hybridlabs.aquatic.entity.fish.HybridAquaticFishEntity
import dev.hybridlabs.aquatic.entity.fish.HybridAquaticFishEntity.FishVariant.Ignore.*
import net.minecraft.client.Minecraft
import net.minecraft.client.model.geom.PartNames
import net.minecraft.resources.ResourceLocation
import net.minecraft.util.Mth
import software.bernie.geckolib.core.animation.AnimationState
import software.bernie.geckolib.model.GeoModel

abstract class HybridAquaticFishEntityModel<T : HybridAquaticFishEntity>(private val id: String) : GeoModel<T>() {
    override fun getModelResource(animatable: T): ResourceLocation {
        val variant = animatable.variant
        if (variant != null && !variant.ignore.contains(MODEL))
            return ResourceLocation(
                HybridAquatic.MOD_ID,
                "geo/fish/${id}/${id}_${variant.getProvidedVariant(animatable)}.geo.json"
            )
        return ResourceLocation(HybridAquatic.MOD_ID, "geo/fish/${id}/$id.geo.json")
    }

    override fun getTextureResource(animatable: T): ResourceLocation {
        val variant = animatable.variant
        if (variant != null && !variant.ignore.contains(TEXTURE))
            return ResourceLocation(
                HybridAquatic.MOD_ID,
                "textures/entity/fish/${id}/${id}_${variant.getProvidedVariant(animatable)}.png"
            )
        return ResourceLocation(HybridAquatic.MOD_ID, "textures/entity/fish/${id}/$id.png")
    }

    override fun getAnimationResource(animatable: T): ResourceLocation {
        val variant = animatable.variant
        if (variant != null && !variant.ignore.contains(ANIMATION))
            return ResourceLocation(
                HybridAquatic.MOD_ID,
                "animations/${id}_${variant.getProvidedVariant(animatable)}.animation.json"
            )
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