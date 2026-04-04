package dev.hybridlabs.aquatic.client.render.entity.miniboss

import dev.hybridlabs.aquatic.client.model.entity.miniboss.HypnautilusEntityModel
import dev.hybridlabs.aquatic.entity.miniboss.HypnautilusEntity
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class HypnautilusEntityRenderer(context: Context) :
    HAMinionEntityRenderer<HypnautilusEntity>(context, HypnautilusEntityModel()) {
    override fun getMotionAnimThreshold(animatable: HypnautilusEntity): Float {
        return 0.0025f
    }

    override fun getDeathMaxRotation(animatable: HypnautilusEntity): Float {
        return 180f
    }

    init {
        this.shadowRadius = 0.4f
    }
}