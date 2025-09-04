package dev.hybridlabs.aquatic.client.render.entity.miniboss

import dev.hybridlabs.aquatic.client.model.entity.miniboss.KarkinosEntityModel
import dev.hybridlabs.aquatic.entity.miniboss.KarkinosEntity
import net.minecraft.client.render.entity.EntityRendererFactory.Context

class KarkinosEntityRenderer(context: Context) :
    HybridAquaticMinibossEntityRenderer<KarkinosEntity>(context, KarkinosEntityModel()) {
    override fun getMotionAnimThreshold(animatable: KarkinosEntity): Float {
        return 0.0025f
    }

    override fun getDeathMaxRotation(animatable: KarkinosEntity): Float {
        return 0f
    }
}