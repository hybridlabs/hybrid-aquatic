package dev.hybridlabs.aquatic.client.render.entity

import dev.hybridlabs.aquatic.client.model.entity.BaskingSharkEntityModel
import dev.hybridlabs.aquatic.entity.shark.BaskingSharkEntity
import net.minecraft.client.render.entity.EntityRendererFactory.Context
import software.bernie.geckolib.renderer.GeoEntityRenderer

class BaskingSharkEntityRenderer(context: Context) : GeoEntityRenderer<BaskingSharkEntity>(context, BaskingSharkEntityModel()) {
}
