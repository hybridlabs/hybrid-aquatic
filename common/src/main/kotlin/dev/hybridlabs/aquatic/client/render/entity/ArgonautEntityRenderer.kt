package dev.hybridlabs.aquatic.client.render.entity

import dev.hybridlabs.aquatic.client.model.entity.misc.ArgonautEntityModel
import dev.hybridlabs.aquatic.entity.misc.ArgonautEntity
import net.minecraft.client.renderer.entity.EntityRendererProvider
import software.bernie.geckolib.renderer.GeoEntityRenderer

class ArgonautEntityRenderer(
    context: EntityRendererProvider.Context
) : GeoEntityRenderer<ArgonautEntity>(
    context,
    ArgonautEntityModel()
) {

    override fun getMotionAnimThreshold(animatable: ArgonautEntity): Float {
        return 0.0025f
    }

    init {
        this.shadowRadius = 0.3f
    }

    override fun getDeathMaxRotation(animatable: ArgonautEntity): Float {
        return 180f
    }
}