package dev.hybridlabs.aquatic.client.render.entity

import dev.hybridlabs.aquatic.client.model.entity.BarreleyeEntityModel
import dev.hybridlabs.aquatic.entity.fish.HybridAquaticFishEntity
import net.minecraft.client.render.RenderLayer
import net.minecraft.client.render.entity.EntityRendererFactory.Context
import net.minecraft.util.Identifier
import software.bernie.geckolib.renderer.GeoEntityRenderer
import software.bernie.geckolib.renderer.layer.AutoGlowingGeoLayer

class BarreleyeEntityRenderer(context: Context) : GeoEntityRenderer<HybridAquaticFishEntity>(context, BarreleyeEntityModel()) {
    fun getRenderType(animatable: HybridAquaticFishEntity?, texture: Identifier?): RenderLayer {
       return RenderLayer.getEntityTranslucentEmissive(texture)
   }
    init {
        addRenderLayer(AutoGlowingGeoLayer(this))
    }
}