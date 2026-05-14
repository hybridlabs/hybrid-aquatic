package dev.hybridlabs.aquatic.client.render.entity.miniboss

import dev.hybridlabs.aquatic.client.model.entity.miniboss.BeaklingEntityModel
import dev.hybridlabs.aquatic.entity.miniboss.BeaklingEntity
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class BeaklingEntityRenderer(context: Context) :
    HAMinionEntityRenderer<BeaklingEntity>(context, BeaklingEntityModel()) {
    override fun getMotionAnimThreshold(animatable: BeaklingEntity): Float {
        return 0.0025f
    }

    override fun getDeathMaxRotation(animatable: BeaklingEntity): Float {
        return 0f
    }

    init {
        this.shadowRadius = 0.4f
    }
}