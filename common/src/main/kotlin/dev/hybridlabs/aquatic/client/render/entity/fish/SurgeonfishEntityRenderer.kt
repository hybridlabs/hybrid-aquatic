package dev.hybridlabs.aquatic.client.render.entity.fish

import dev.hybridlabs.aquatic.client.model.entity.fish.SurgeonfishEntityModel
import dev.hybridlabs.aquatic.entity.fish.SurgeonfishEntity
import dev.hybridlabs.hapi.client.render.entity.BaseFishEntityRenderer
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class SurgeonfishEntityRenderer(context: Context) :
    BaseFishEntityRenderer<SurgeonfishEntity>(context, SurgeonfishEntityModel(), true, false)