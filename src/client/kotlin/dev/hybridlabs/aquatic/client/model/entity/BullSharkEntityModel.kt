package dev.hybridlabs.aquatic.client.model.entity

import dev.hybridlabs.aquatic.HybridAquatic
import dev.hybridlabs.aquatic.entity.BullSharkEntity
import net.minecraft.client.render.entity.model.EntityModelPartNames
import net.minecraft.util.Identifier
import net.minecraft.util.math.MathHelper
import software.bernie.geckolib.core.animation.AnimationState
import software.bernie.geckolib.model.GeoModel

class BullSharkEntityModel : GeoModel<BullSharkEntity>() {
    override fun getModelResource(animatable: BullSharkEntity): Identifier {
        return Identifier(HybridAquatic.MOD_ID, "geo/bull_shark.geo.json")
    }

    override fun getTextureResource(animatable: BullSharkEntity): Identifier {
        return Identifier(HybridAquatic.MOD_ID, "textures/entity/bull_shark.png")
    }

    override fun getAnimationResource(animatable: BullSharkEntity): Identifier {
        return Identifier(HybridAquatic.MOD_ID, "animations/bull_shark.animation.json")
    }

    override fun setCustomAnimations(
        animatable: BullSharkEntity,
        instanceId: Long,
        animationState: AnimationState<BullSharkEntity>
    ) {
        super.setCustomAnimations(animatable, instanceId, animationState)

        val body = animationProcessor.getBone(EntityModelPartNames.BODY)
        body.rotX = animatable.pitch * -MathHelper.RADIANS_PER_DEGREE
    }
}
