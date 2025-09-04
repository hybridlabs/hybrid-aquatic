package dev.hybridlabs.aquatic.client.render.entity.fish

import dev.hybridlabs.aquatic.client.model.entity.fish.AfricanButterflyfishEntityModel
import dev.hybridlabs.aquatic.entity.fish.AfricanButterflyfishEntity
import net.minecraft.client.render.entity.EntityRendererFactory.Context

class AfricanButterflyfishEntityRenderer(context: Context) :
    HybridAquaticFishEntityRenderer<AfricanButterflyfishEntity>(context, AfricanButterflyfishEntityModel(), false, false)
