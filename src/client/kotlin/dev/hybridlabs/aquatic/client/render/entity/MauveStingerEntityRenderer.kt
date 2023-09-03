package dev.hybridlabs.aquatic.client.render.entity

import dev.hybridlabs.aquatic.client.model.entity.MauveStingerEntityModel
import dev.hybridlabs.aquatic.entity.jellyfish.HybridAquaticJellyfishEntity
import net.minecraft.client.render.entity.EntityRendererFactory.Context
import software.bernie.geckolib.renderer.GeoEntityRenderer
import software.bernie.geckolib.renderer.layer.AutoGlowingGeoLayer

class MauveStingerEntityRenderer(context: Context) : GeoEntityRenderer<HybridAquaticJellyfishEntity>(context, MauveStingerEntityModel()) {
    init {
        addRenderLayer(AutoGlowingGeoLayer(this))
    }
}