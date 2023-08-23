package dev.hybridlabs.aquatic.client.render.entity

import dev.hybridlabs.aquatic.client.model.entity.GiantClamEntityModel
import dev.hybridlabs.aquatic.entity.critter.GiantClamEntity
import net.minecraft.client.render.entity.EntityRendererFactory.Context
import software.bernie.geckolib.renderer.GeoEntityRenderer

class GiantClamEntityRenderer(context: Context) : GeoEntityRenderer<GiantClamEntity>(context, GiantClamEntityModel())
