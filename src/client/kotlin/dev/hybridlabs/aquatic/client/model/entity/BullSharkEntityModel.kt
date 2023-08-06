package dev.hybridlabs.aquatic.client.model.entity

import dev.hybridlabs.aquatic.HybridAquatic
import dev.hybridlabs.aquatic.entity.BullSharkEntity
import dev.hybridlabs.aquatic.entity.ClownfishEntity
import net.minecraft.util.Identifier
import software.bernie.geckolib.core.animatable.GeoAnimatable
import software.bernie.geckolib.core.animation.AnimationState
import software.bernie.geckolib.model.GeoModel

class BullSharkEntityModel : GeoModel<BullSharkEntity>() {
    override fun getModelResource(animatable: BullSharkEntity?): Identifier {
        return Identifier(HybridAquatic.MOD_ID, "geo/bull_shark.geo.json")
    }

    override fun getTextureResource(animatable: BullSharkEntity?): Identifier {
        return Identifier(HybridAquatic.MOD_ID, "textures/entity/bull_shark.png")
    }

    override fun getAnimationResource(animatable: BullSharkEntity?): Identifier {
        return Identifier(HybridAquatic.MOD_ID, "animations/bull_shark.animation.json")
    }

    override fun setCustomAnimations(
        animatable: BullSharkEntity?,
        instanceId: Long,
        animationState: AnimationState<BullSharkEntity>?
    ) {
        super.setCustomAnimations(animatable, instanceId, animationState)

        val body = animationProcessor.getBone("body")

        body.rotX = (animatable!!.pitch) * -0.01745329252f;
    }
}