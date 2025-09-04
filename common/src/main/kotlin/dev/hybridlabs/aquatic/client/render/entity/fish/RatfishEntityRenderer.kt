package dev.hybridlabs.aquatic.client.render.entity.fish

import dev.hybridlabs.aquatic.client.model.entity.fish.RatfishEntityModel
import dev.hybridlabs.aquatic.entity.fish.RatfishEntity
import net.minecraft.client.render.entity.EntityRendererFactory.Context

class RatfishEntityRenderer(context: Context) :
    HybridAquaticFishEntityRenderer<RatfishEntity>(context, RatfishEntityModel(), true, false)