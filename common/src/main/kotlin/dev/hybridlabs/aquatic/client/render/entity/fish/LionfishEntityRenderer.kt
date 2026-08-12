package dev.hybridlabs.aquatic.client.render.entity.fish

import dev.hybridlabs.aquatic.client.model.entity.fish.LionfishEntityModel
import dev.hybridlabs.aquatic.entity.fish.LionfishEntity
import dev.hybridlabs.hapi.client.render.entity.BaseFishEntityRenderer
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class LionfishEntityRenderer(context: Context) :
    BaseFishEntityRenderer<LionfishEntity>(context, LionfishEntityModel(), true, false)