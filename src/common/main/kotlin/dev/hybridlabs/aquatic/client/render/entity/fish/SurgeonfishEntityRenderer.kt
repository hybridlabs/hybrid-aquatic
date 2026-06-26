package dev.hybridlabs.aquatic.client.render.entity.fish

import dev.hybridlabs.aquatic.client.model.entity.fish.SurgeonfishEntityModel
import dev.hybridlabs.aquatic.entity.fish.SurgeonfishEntity
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class SurgeonfishEntityRenderer(context: Context) :
    HybridAquaticFishEntityRenderer<SurgeonfishEntity>(context, SurgeonfishEntityModel(), true, false)