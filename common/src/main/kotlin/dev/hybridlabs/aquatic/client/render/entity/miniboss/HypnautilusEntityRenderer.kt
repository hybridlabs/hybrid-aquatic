package dev.hybridlabs.aquatic.client.render.entity.miniboss

import dev.hybridlabs.aquatic.client.model.entity.miniboss.HypnautilusEntityModel
import dev.hybridlabs.aquatic.entity.miniboss.HypnautilusEntity
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context
import software.bernie.geckolib.renderer.layer.AutoGlowingGeoLayer

class HypnautilusEntityRenderer(context: Context) :
    HAMinionEntityRenderer<HypnautilusEntity>(context, HypnautilusEntityModel()) {
    override fun getMotionAnimThreshold(animatable: HypnautilusEntity): Float {
        return 0.0025f
    }

    override fun getDeathMaxRotation(animatable: HypnautilusEntity): Float {
        return 180f
    }

    override fun doPostRenderCleanup() {
        this.animatable.registerHypnotizing()
        super.doPostRenderCleanup()
    }

    init {
        this.addRenderLayer(AutoGlowingGeoLayer(this))
        this.shadowRadius = 0.4f
    }
}