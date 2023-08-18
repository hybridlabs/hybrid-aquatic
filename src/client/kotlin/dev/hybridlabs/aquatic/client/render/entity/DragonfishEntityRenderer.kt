package dev.hybridlabs.aquatic.client.render.entity

import dev.hybridlabs.aquatic.client.model.entity.DragonfishEntityModel
import dev.hybridlabs.aquatic.entity.fish.HybridAquaticFishEntity
import net.minecraft.client.render.entity.EntityRendererFactory.Context
import software.bernie.geckolib.renderer.GeoEntityRenderer
import software.bernie.geckolib.renderer.layer.AutoGlowingGeoLayer

class DragonfishEntityRenderer(context: Context) : GeoEntityRenderer<HybridAquaticFishEntity>(context, DragonfishEntityModel()) {
    init {
        addRenderLayer(AutoGlowingGeoLayer(this))
    }
}
