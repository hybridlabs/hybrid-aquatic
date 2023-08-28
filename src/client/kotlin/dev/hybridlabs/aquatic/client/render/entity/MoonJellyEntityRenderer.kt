package dev.hybridlabs.aquatic.client.render.entity

import dev.hybridlabs.aquatic.client.model.entity.MoonJellyEntityModel
import dev.hybridlabs.aquatic.entity.jellyfish.HybridAquaticJellyfishEntity
import net.minecraft.client.render.entity.EntityRendererFactory.Context
import software.bernie.geckolib.renderer.GeoEntityRenderer
import software.bernie.geckolib.renderer.layer.AutoGlowingGeoLayer

class MoonJellyEntityRenderer(context: Context) : GeoEntityRenderer<HybridAquaticJellyfishEntity>(context, MoonJellyEntityModel()) {
// TODO somehow fix translucent emissive renderer so it isn't fucked up
//
//    init {
//        addRenderLayer(AutoGlowingGeoLayer(this))
//    }
}
