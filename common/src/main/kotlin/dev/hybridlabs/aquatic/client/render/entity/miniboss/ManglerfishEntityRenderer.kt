package dev.hybridlabs.aquatic.client.render.entity.miniboss

import dev.hybridlabs.aquatic.client.model.entity.miniboss.ManglerfishEntityModel
import dev.hybridlabs.aquatic.entity.miniboss.ManglerfishEntity
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class ManglerfishEntityRenderer(context: Context) :
    HybridAquaticMinibossEntityRenderer<ManglerfishEntity>(context, ManglerfishEntityModel(), false, true) {
    override fun getMotionAnimThreshold(animatable: ManglerfishEntity): Float {
        return 0.0025f
    }

    override fun getDeathMaxRotation(animatable: ManglerfishEntity): Float {
        return 180f
    }

    init {
        this.shadowRadius = 0.4f
    }
}