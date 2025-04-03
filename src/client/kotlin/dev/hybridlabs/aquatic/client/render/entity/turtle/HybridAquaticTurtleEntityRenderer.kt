package dev.hybridlabs.aquatic.client.render.entity.turtle

import dev.hybridlabs.aquatic.entity.turtle.HybridAquaticTurtleEntity
import net.minecraft.client.render.entity.EntityRendererFactory
import software.bernie.geckolib.model.GeoModel
import software.bernie.geckolib.renderer.GeoEntityRenderer

open class HybridAquaticTurtleEntityRenderer<T : HybridAquaticTurtleEntity>(
    context: EntityRendererFactory.Context,
    model: GeoModel<T>
) : GeoEntityRenderer<T>(context, model) {
    override fun getMotionAnimThreshold(animatable: T): Float {
        return 0.0025f
    }

    override fun getDeathMaxRotation(animatable: T): Float {
        return 0f
    }
}