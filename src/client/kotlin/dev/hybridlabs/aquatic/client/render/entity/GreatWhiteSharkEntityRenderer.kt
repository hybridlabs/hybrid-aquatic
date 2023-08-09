package dev.hybridlabs.aquatic.client.render.entity

import dev.hybridlabs.aquatic.client.model.entity.GreatWhiteSharkEntityModel
import dev.hybridlabs.aquatic.entity.GreatWhiteSharkEntity
import net.minecraft.client.render.entity.EntityRendererFactory.Context
import software.bernie.geckolib.renderer.GeoEntityRenderer

class GreatWhiteSharkEntityRenderer(context: Context) : GeoEntityRenderer<GreatWhiteSharkEntity>(context, GreatWhiteSharkEntityModel()) {
}
