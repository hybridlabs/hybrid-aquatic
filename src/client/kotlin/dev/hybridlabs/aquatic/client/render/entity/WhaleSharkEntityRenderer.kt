package dev.hybridlabs.aquatic.client.render.entity

import dev.hybridlabs.aquatic.client.model.entity.*
import dev.hybridlabs.aquatic.entity.*
import net.minecraft.client.render.entity.DolphinEntityRenderer
import net.minecraft.client.render.entity.EntityRendererFactory.Context
import net.minecraft.client.util.math.MatrixStack
import net.minecraft.util.math.RotationAxis
import software.bernie.geckolib.renderer.GeoEntityRenderer

class WhaleSharkEntityRenderer(context: Context) : GeoEntityRenderer<WhaleSharkEntity>(context, WhaleSharkEntityModel()) {
}