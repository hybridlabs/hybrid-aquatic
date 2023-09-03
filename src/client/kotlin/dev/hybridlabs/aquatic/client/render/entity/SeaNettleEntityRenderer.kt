package dev.hybridlabs.aquatic.client.render.entity

import dev.hybridlabs.aquatic.client.model.entity.SeaNettleEntityModel
import dev.hybridlabs.aquatic.entity.jellyfish.HybridAquaticJellyfishEntity
import net.minecraft.client.render.entity.EntityRendererFactory.Context
import software.bernie.geckolib.renderer.GeoEntityRenderer
import software.bernie.geckolib.renderer.layer.AutoGlowingGeoLayer

class SeaNettleEntityRenderer(context: Context) : GeoEntityRenderer<HybridAquaticJellyfishEntity>(context, SeaNettleEntityModel()) {
    init {
        addRenderLayer(AutoGlowingGeoLayer(this))
    }
}