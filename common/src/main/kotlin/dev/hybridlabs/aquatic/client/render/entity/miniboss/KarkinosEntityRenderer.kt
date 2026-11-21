package dev.hybridlabs.aquatic.client.render.entity.miniboss

import software.bernie.geckolib.renderer.base.GeoRenderState

import dev.hybridlabs.aquatic.client.model.entity.miniboss.KarkinosEntityModel
import dev.hybridlabs.aquatic.entity.miniboss.KarkinosEntity
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class KarkinosEntityRenderer(context: Context) :
    HAMinibossEntityRenderer<KarkinosEntity>(context, KarkinosEntityModel()) {
    override fun getMotionAnimThreshold(animatable: KarkinosEntity): Float {
        return 0.0025f
    }

    override fun getDeathMaxRotation(renderState: GeoRenderState): Float {
        return 0f
    }

    init {
        this.shadowRadius = 1.5f
    }
}