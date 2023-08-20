package dev.hybridlabs.aquatic.client.render.entity

import dev.hybridlabs.aquatic.client.model.entity.StarfishEntityModel
import dev.hybridlabs.aquatic.entity.critter.StarfishEntity
import net.minecraft.client.render.entity.EntityRendererFactory.Context
import software.bernie.geckolib.renderer.GeoEntityRenderer
class StarfishEntityRenderer(context: Context) : GeoEntityRenderer<StarfishEntity>(context, StarfishEntityModel())
