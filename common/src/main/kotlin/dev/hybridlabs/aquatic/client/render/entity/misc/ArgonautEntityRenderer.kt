package dev.hybridlabs.aquatic.client.render.entity.misc

import dev.hybridlabs.aquatic.client.model.entity.misc.ArgonautEntityModel
import dev.hybridlabs.aquatic.client.render.entity.misc.layer.ArgonautSailEntityLayer
import dev.hybridlabs.aquatic.client.render.entity.misc.layer.ArgonautShellEntityLayer
import dev.hybridlabs.aquatic.client.render.entity.misc.layer.ArgonautVisorEntityLayer
import dev.hybridlabs.aquatic.entity.misc.ArgonautEntity
import net.minecraft.client.renderer.entity.EntityRendererProvider
import software.bernie.geckolib.renderer.GeoEntityRenderer
import software.bernie.geckolib.renderer.layer.AutoGlowingGeoLayer

class ArgonautEntityRenderer<T : ArgonautEntity>(
    context: EntityRendererProvider.Context
) : GeoEntityRenderer<T>(context, ArgonautEntityModel()) {

    init {
        addRenderLayer(ArgonautVisorEntityLayer(this))
        addRenderLayer(ArgonautSailEntityLayer(this))
        addRenderLayer(ArgonautShellEntityLayer(this))
        addRenderLayer(AutoGlowingGeoLayer(this))
        this.shadowRadius = 0.3f
    }

    override fun getMotionAnimThreshold(animatable: T): Float {
        return 0.0025f
    }
}