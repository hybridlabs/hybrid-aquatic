package dev.hybridlabs.aquatic.client.render.entity.fish

import dev.hybridlabs.aquatic.client.model.entity.fish.FlyingFishEntityModel
import dev.hybridlabs.aquatic.entity.fish.FlyingFishEntity
import net.minecraft.client.render.entity.EntityRendererFactory.Context

class FlyingFishEntityRenderer(context: Context) :
    HybridAquaticFishEntityRenderer<FlyingFishEntity>(context, FlyingFishEntityModel(), false, false)
