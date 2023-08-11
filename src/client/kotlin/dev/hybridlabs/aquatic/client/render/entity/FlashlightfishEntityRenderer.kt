package dev.hybridlabs.aquatic.client.render.entity

import dev.hybridlabs.aquatic.client.model.entity.AnglerfishEntityModel
import dev.hybridlabs.aquatic.client.model.entity.FlashlighfishEntityModel
import dev.hybridlabs.aquatic.entity.HybridAquaticFishEntity
import net.minecraft.client.render.entity.EntityRendererFactory.Context
import software.bernie.geckolib.renderer.GeoEntityRenderer
import software.bernie.geckolib.renderer.layer.AutoGlowingGeoLayer

class FlashlightfishEntityRenderer(context: Context) : GeoEntityRenderer<HybridAquaticFishEntity>(context, FlashlighfishEntityModel()) {
    init {
        addRenderLayer(AutoGlowingGeoLayer(this))
    }
}
