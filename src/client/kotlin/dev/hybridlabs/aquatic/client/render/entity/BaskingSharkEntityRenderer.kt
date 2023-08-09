package dev.hybridlabs.aquatic.client.render.entity

import dev.hybridlabs.aquatic.client.model.entity.BaskingSharkEntityModel
import dev.hybridlabs.aquatic.client.model.entity.BullSharkEntityModel
import dev.hybridlabs.aquatic.client.model.entity.ClownfishEntityModel
import dev.hybridlabs.aquatic.entity.BaskingSharkEntity
import dev.hybridlabs.aquatic.entity.BullSharkEntity
import dev.hybridlabs.aquatic.entity.ClownfishEntity
import net.minecraft.client.render.entity.DolphinEntityRenderer
import net.minecraft.client.render.entity.EntityRendererFactory.Context
import net.minecraft.client.util.math.MatrixStack
import net.minecraft.util.math.RotationAxis
import software.bernie.geckolib.renderer.GeoEntityRenderer

class BaskingSharkEntityRenderer(context: Context) : GeoEntityRenderer<BaskingSharkEntity>(context, BaskingSharkEntityModel()) {
}