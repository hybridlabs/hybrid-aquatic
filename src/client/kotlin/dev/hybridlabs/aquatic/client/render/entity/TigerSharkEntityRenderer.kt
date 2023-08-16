package dev.hybridlabs.aquatic.client.render.entity

import dev.hybridlabs.aquatic.client.model.entity.TigerSharkEntityModel
import dev.hybridlabs.aquatic.entity.shark.TigerSharkEntity
import net.minecraft.client.render.entity.EntityRendererFactory.Context
import software.bernie.geckolib.renderer.GeoEntityRenderer

class TigerSharkEntityRenderer(context: Context) : GeoEntityRenderer<TigerSharkEntity>(context, TigerSharkEntityModel()) {
}
