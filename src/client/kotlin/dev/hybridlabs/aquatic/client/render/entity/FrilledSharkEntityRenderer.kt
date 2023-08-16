package dev.hybridlabs.aquatic.client.render.entity

import dev.hybridlabs.aquatic.client.model.entity.FrilledSharkEntityModel
import dev.hybridlabs.aquatic.entity.shark.FrilledSharkEntity
import net.minecraft.client.render.entity.EntityRendererFactory.Context
import software.bernie.geckolib.renderer.GeoEntityRenderer

class FrilledSharkEntityRenderer(context: Context) : GeoEntityRenderer<FrilledSharkEntity>(context, FrilledSharkEntityModel()) {
}
