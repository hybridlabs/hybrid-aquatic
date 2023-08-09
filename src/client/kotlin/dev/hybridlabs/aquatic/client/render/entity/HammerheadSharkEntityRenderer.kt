package dev.hybridlabs.aquatic.client.render.entity

import dev.hybridlabs.aquatic.client.model.entity.HammerheadSharkEntityModel
import dev.hybridlabs.aquatic.entity.HammerheadSharkEntity
import net.minecraft.client.render.entity.EntityRendererFactory.Context
import software.bernie.geckolib.renderer.GeoEntityRenderer

class HammerheadSharkEntityRenderer(context: Context) : GeoEntityRenderer<HammerheadSharkEntity>(context, HammerheadSharkEntityModel()) {
}
