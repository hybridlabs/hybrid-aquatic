package dev.hybridlabs.aquatic.client.render.entity.fish

import dev.hybridlabs.aquatic.client.model.entity.fish.DragonfishEntityModel
import dev.hybridlabs.aquatic.entity.fish.DragonfishEntity
import dev.hybridlabs.hapi.client.render.entity.aquatic.BaseFishEntityRenderer
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class DragonfishEntityRenderer(context: Context) :
    BaseFishEntityRenderer<DragonfishEntity>(context, DragonfishEntityModel(), true, true)