package dev.hybridlabs.aquatic.client.render.entity.crustacean

import dev.hybridlabs.aquatic.entity.crustacean.HybridAquaticCrustaceanEntity
import net.minecraft.client.render.entity.EntityRendererFactory
import software.bernie.geckolib.model.GeoModel
import software.bernie.geckolib.renderer.GeoEntityRenderer

open class HybridAquaticCrustaceanEntityRenderer<T: HybridAquaticCrustaceanEntity>(context: EntityRendererFactory.Context, model: GeoModel<T>): GeoEntityRenderer<T>(context, model) {
    override fun getMotionAnimThreshold(animatable: T): Float {
        return 0.0025f
    }
}