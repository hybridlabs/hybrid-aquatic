package dev.hybridlabs.aquatic.client.render.entity.miniboss

import software.bernie.geckolib.renderer.base.GeoRenderState

import dev.hybridlabs.aquatic.client.model.entity.miniboss.KarcinogenEntityModel
import dev.hybridlabs.aquatic.entity.miniboss.KarcinogenEntity
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class KarcinogenEntityRenderer(context: Context) :
    HAMinionEntityRenderer<KarcinogenEntity>(context, KarcinogenEntityModel()) {
    override fun getMotionAnimThreshold(animatable: KarcinogenEntity): Float {
        return 0.0025f
    }

    override fun getDeathMaxRotation(renderState: GeoRenderState): Float {
        return 0f
    }

    init {
        this.shadowRadius = 0.4f
    }
}