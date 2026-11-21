package dev.hybridlabs.aquatic.client.render.entity.miniboss

import net.minecraft.client.renderer.entity.state.LivingEntityRenderState
import software.bernie.geckolib.renderer.base.GeoRenderState

import dev.hybridlabs.aquatic.client.model.entity.miniboss.HypnautilusEntityModel
import dev.hybridlabs.aquatic.entity.miniboss.HypnautilusEntity
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context
import software.bernie.geckolib.renderer.layer.builtin.AutoGlowingGeoLayer

class HypnautilusEntityRenderer(context: Context) :
    HAMinionEntityRenderer<HypnautilusEntity>(context, HypnautilusEntityModel()) {
    override fun getMotionAnimThreshold(animatable: HypnautilusEntity): Float {
        return 0.0025f
    }

    override fun getDeathMaxRotation(renderState: GeoRenderState): Float {
        return 180f
    }

    override fun addRenderData(
        animatable: HypnautilusEntity,
        relatedObject: Void?,
        renderState: LivingEntityRenderState,
        partialTick: Float
    ) {
        super.addRenderData(animatable, relatedObject, renderState, partialTick)
        animatable.registerHypnotizing()
    }

    init {
        this.withRenderLayer(AutoGlowingGeoLayer(this))
        this.shadowRadius = 0.4f
    }
}