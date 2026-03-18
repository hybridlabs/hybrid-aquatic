package dev.hybridlabs.aquatic.client.model.entity.misc

import dev.hybridlabs.aquatic.CommonClass
import dev.hybridlabs.aquatic.entity.misc.CavitationBubbleEntity
import net.minecraft.client.renderer.RenderType
import net.minecraft.resources.ResourceLocation
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
}