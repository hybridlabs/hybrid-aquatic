package dev.hybridlabs.aquatic.client.model.entity.misc

import dev.hybridlabs.aquatic.CommonClass
import dev.hybridlabs.aquatic.entity.projectile.CavitationBubbleEntity
import net.minecraft.client.model.geom.PartNames
import net.minecraft.client.renderer.RenderType
import net.minecraft.resources.ResourceLocation
import net.minecraft.util.Mth
import software.bernie.geckolib.animation.AnimationState
import software.bernie.geckolib.model.GeoModel

class CavitationBubbleEntityModel() :
    GeoModel<CavitationBubbleEntity>() {
    override fun getModelResource(animatable: CavitationBubbleEntity): ResourceLocation {
        return CommonClass.locate("geo/misc/cavitation_bubble.geo.json")
    }

    override fun getTextureResource(animatable: CavitationBubbleEntity): ResourceLocation {
        return CommonClass.locate("textures/entity/misc/cavitation_bubble/cavitation_bubble.png")
    }

    override fun getAnimationResource(p0: CavitationBubbleEntity?): ResourceLocation? {
        return CommonClass.locate("animations/entity/misc/cavitation_bubble.animation.json")
    }

    override fun getRenderType(animatable: CavitationBubbleEntity, texture: ResourceLocation): RenderType {
        return RenderType.entityTranslucent(texture)
    }

    override fun setCustomAnimations(
        animatable: CavitationBubbleEntity,
        instanceId: Long,
        animationState: AnimationState<CavitationBubbleEntity>
    ) {
        super.setCustomAnimations(animatable, instanceId, animationState)

        val deltaTime = animationState.partialTick
        val body = animationProcessor.getBone(PartNames.BODY)

        val tilt = Mth.clamp(
            Mth.lerp(deltaTime, animatable.xRotO, animatable.xRot),
            -45f, 45f
        )

        body?.rotX = tilt * -Mth.DEG_TO_RAD
    }
}