package dev.hybridlabs.aquatic.client.render.entity

import dev.hybridlabs.aquatic.client.model.entity.ClownfishEntityModel
import dev.hybridlabs.aquatic.entity.fish.ClownfishEntity
import net.minecraft.client.render.entity.EntityRendererFactory.Context
import software.bernie.geckolib.renderer.GeoEntityRenderer

class ClownfishEntityRenderer(context: Context) : GeoEntityRenderer<ClownfishEntity>(context, ClownfishEntityModel())
