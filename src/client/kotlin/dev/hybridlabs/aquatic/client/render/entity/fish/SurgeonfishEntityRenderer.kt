package dev.hybridlabs.aquatic.client.render.entity.fish

import dev.hybridlabs.aquatic.client.model.entity.fish.SurgeonfishEntityModel
import dev.hybridlabs.aquatic.entity.fish.SurgeonfishEntity
import net.minecraft.client.render.entity.EntityRendererFactory.Context

class SurgeonfishEntityRenderer(context: Context) :
    HybridAquaticFishEntityRenderer<SurgeonfishEntity>(context, SurgeonfishEntityModel(), true, false)