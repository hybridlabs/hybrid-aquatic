package dev.hybridlabs.aquatic.client.render.entity

import dev.hybridlabs.aquatic.client.model.entity.ThresherSharkEntityModel
import dev.hybridlabs.aquatic.entity.ThresherSharkEntity
import net.minecraft.client.render.entity.EntityRendererFactory.Context
import software.bernie.geckolib.renderer.GeoEntityRenderer

class ThresherSharkEntityRenderer(context: Context) : GeoEntityRenderer<ThresherSharkEntity>(context, ThresherSharkEntityModel()) {
}
