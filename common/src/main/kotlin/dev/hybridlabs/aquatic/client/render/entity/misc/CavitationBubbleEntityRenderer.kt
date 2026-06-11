package dev.hybridlabs.aquatic.client.render.entity.misc

import dev.hybridlabs.aquatic.client.model.entity.misc.CavitationBubbleEntityModel
import dev.hybridlabs.aquatic.entity.projectile.CavitationBubbleEntity
import net.minecraft.client.renderer.entity.EntityRendererProvider
import software.bernie.geckolib.renderer.GeoEntityRenderer
import software.bernie.geckolib.renderer.layer.AutoGlowingGeoLayer

class CavitationBubbleEntityRenderer(
    context: EntityRendererProvider.Context
) : GeoEntityRenderer<CavitationBubbleEntity>(context, CavitationBubbleEntityModel()) {

    init {
        addRenderLayer(AutoGlowingGeoLayer(this))
        this.shadowRadius = 0.5f
    }

    override fun getMotionAnimThreshold(animatable: CavitationBubbleEntity): Float {
        return 0.0025f
    }
}