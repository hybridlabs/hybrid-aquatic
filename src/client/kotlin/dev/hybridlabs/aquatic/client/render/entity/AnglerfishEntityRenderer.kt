package dev.hybridlabs.aquatic.client.render.entity

import dev.hybridlabs.aquatic.client.model.entity.AnglerfishEntityModel
import dev.hybridlabs.aquatic.entity.fish.HybridAquaticFishEntity
import net.minecraft.client.render.entity.EntityRendererFactory.Context
import software.bernie.geckolib.renderer.GeoEntityRenderer
import software.bernie.geckolib.renderer.layer.AutoGlowingGeoLayer

class AnglerfishEntityRenderer(context: Context) : GeoEntityRenderer<HybridAquaticFishEntity>(context, AnglerfishEntityModel()) {
    init {
        addRenderLayer(AutoGlowingGeoLayer(this))
    }
}
