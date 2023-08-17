package dev.hybridlabs.aquatic.client.render.entity

import dev.hybridlabs.aquatic.client.model.entity.GlowingSuckerOctopusEntityModel
import dev.hybridlabs.aquatic.entity.fish.HybridAquaticFishEntity
import net.minecraft.client.render.entity.EntityRendererFactory.Context
import software.bernie.geckolib.renderer.GeoEntityRenderer
import software.bernie.geckolib.renderer.layer.AutoGlowingGeoLayer

class GlowingSuckerOctopusEntityRenderer(context: Context) : GeoEntityRenderer<HybridAquaticFishEntity>(context, GlowingSuckerOctopusEntityModel()) {
    init {
        addRenderLayer(AutoGlowingGeoLayer(this))
    }
}
