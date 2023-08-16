package dev.hybridlabs.aquatic.client.render.entity

import dev.hybridlabs.aquatic.client.model.entity.WhaleSharkEntityModel
import dev.hybridlabs.aquatic.entity.shark.WhaleSharkEntity
import net.minecraft.client.render.entity.EntityRendererFactory.Context
import software.bernie.geckolib.renderer.GeoEntityRenderer
import software.bernie.geckolib.renderer.layer.AutoGlowingGeoLayer

class WhaleSharkEntityRenderer(context: Context) : GeoEntityRenderer<WhaleSharkEntity>(context, WhaleSharkEntityModel()) {
    init {
        addRenderLayer(AutoGlowingGeoLayer(this))
    }
}
