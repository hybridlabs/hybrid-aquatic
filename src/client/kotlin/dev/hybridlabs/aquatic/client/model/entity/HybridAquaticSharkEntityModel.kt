package dev.hybridlabs.aquatic.client.model.entity

import dev.hybridlabs.aquatic.HybridAquatic
import dev.hybridlabs.aquatic.entity.HybridAquaticSharkEntity
import dev.hybridlabs.aquatic.entity.ClownfishEntity
import net.minecraft.client.render.entity.model.EntityModelPartNames
import net.minecraft.util.Identifier
import net.minecraft.util.math.MathHelper
import software.bernie.geckolib.core.animatable.GeoAnimatable
import software.bernie.geckolib.core.animation.AnimationState
import software.bernie.geckolib.model.GeoModel

abstract class HybridAquaticSharkEntityModel<T : HybridAquaticSharkEntity> (
    private val id: String
) : GeoModel<T>() {
    override fun getModelResource(animatable: T?): Identifier {
        return Identifier(HybridAquatic.MOD_ID, "geo/$id.geo.json")
    }

    override fun getTextureResource(animatable: T?): Identifier {
        return Identifier(HybridAquatic.MOD_ID, "textures/entity/$id.png")
    }

    override fun getAnimationResource(animatable: T?): Identifier {
        return Identifier(HybridAquatic.MOD_ID, "animations/$id.animation.json")
    }

    override fun setCustomAnimations(
        animatable: T,
        instanceId: Long,
        animationState: AnimationState<T>
    ) {
        super.setCustomAnimations(animatable, instanceId, animationState)

        val body = animationProcessor.getBone(EntityModelPartNames.BODY)
        body.rotX = animatable.pitch * -MathHelper.RADIANS_PER_DEGREE
    }
}