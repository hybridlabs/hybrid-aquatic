package dev.hybridlabs.aquatic.client.render.entity

import dev.hybridlabs.aquatic.client.model.entity.BullSharkEntityModel
import dev.hybridlabs.aquatic.entity.shark.BullSharkEntity
import net.minecraft.client.render.entity.EntityRendererFactory.Context
import software.bernie.geckolib.renderer.GeoEntityRenderer

class BullSharkEntityRenderer(context: Context) : GeoEntityRenderer<BullSharkEntity>(context, BullSharkEntityModel())
