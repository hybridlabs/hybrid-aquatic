package dev.hybridlabs.aquatic.client.render.entity.critter

import dev.hybridlabs.aquatic.entity.critter.HybridAquaticCritterEntity
import net.minecraft.client.renderer.entity.EntityRendererProvider
import software.bernie.geckolib.model.GeoModel
import software.bernie.geckolib.renderer.GeoEntityRenderer

open class HybridAquaticCritterEntityRenderer<T : HybridAquaticCritterEntity>(
    context: EntityRendererProvider.Context,
    model: GeoModel<T>
) : GeoEntityRenderer<T>(context, model) {
    override fun getMotionAnimThreshold(animatable: T): Float {
        return 0.0025f
    }

    override fun getDeathMaxRotation(animatable: T): Float {
        return 0f
    }
}