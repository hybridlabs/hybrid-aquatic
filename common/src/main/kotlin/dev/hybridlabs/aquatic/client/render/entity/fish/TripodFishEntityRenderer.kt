package dev.hybridlabs.aquatic.client.render.entity.fish

import dev.hybridlabs.aquatic.client.model.entity.fish.TripodFishEntityModel
import dev.hybridlabs.aquatic.entity.fish.TripodFishEntity
import dev.hybridlabs.hapi.client.render.entity.aquatic.BaseFishEntityRenderer
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class TripodFishEntityRenderer(context: Context) :
    BaseFishEntityRenderer<TripodFishEntity>(context, TripodFishEntityModel(), true, false)
