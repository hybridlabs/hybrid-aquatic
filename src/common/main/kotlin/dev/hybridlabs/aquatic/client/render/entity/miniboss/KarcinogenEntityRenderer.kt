package dev.hybridlabs.aquatic.client.render.entity.miniboss

import dev.hybridlabs.aquatic.client.model.entity.miniboss.KarcinogenEntityModel
import dev.hybridlabs.aquatic.entity.miniboss.KarcinogenEntity
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class KarcinogenEntityRenderer(context: Context) :
    HybridAquaticMinionEntityRenderer<KarcinogenEntity>(context, KarcinogenEntityModel()) {
    override fun getMotionAnimThreshold(animatable: KarcinogenEntity): Float {
        return 0.0025f
    }

    override fun getDeathMaxRotation(animatable: KarcinogenEntity): Float {
        return 0f
    }

    init {
        this.shadowRadius = 0.4f
    }
}