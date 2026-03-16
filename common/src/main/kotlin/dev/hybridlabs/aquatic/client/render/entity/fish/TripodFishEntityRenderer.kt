package dev.hybridlabs.aquatic.client.render.entity.fish

import dev.hybridlabs.aquatic.client.model.entity.fish.TripodFishEntityModel
import dev.hybridlabs.aquatic.entity.fish.TripodFishEntity
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class TripodFishEntityRenderer(context: Context) :
    HybridAquaticFishEntityRenderer<TripodFishEntity>(context, TripodFishEntityModel(), true, false)
