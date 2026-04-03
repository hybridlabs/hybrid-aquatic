package dev.hybridlabs.aquatic.client.render.entity.miniboss

import dev.hybridlabs.aquatic.client.model.entity.miniboss.KarcinomaEntityModel
import dev.hybridlabs.aquatic.entity.miniboss.KarcinomaEntity
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class KarcinomaEntityRenderer(context: Context) :
    HAMinionEntityRenderer<KarcinomaEntity>(context, KarcinomaEntityModel()) {
    override fun getMotionAnimThreshold(animatable: KarcinomaEntity): Float {
        return 0.0025f
    }

    override fun getDeathMaxRotation(animatable: KarcinomaEntity): Float {
        return 180f
    }

    init {
        this.shadowRadius = 0.4f
    }
}