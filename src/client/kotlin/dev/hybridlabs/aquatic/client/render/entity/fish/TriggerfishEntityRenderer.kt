package dev.hybridlabs.aquatic.client.render.entity.fish

import dev.hybridlabs.aquatic.client.model.entity.fish.TriggerfishEntityModel
import dev.hybridlabs.aquatic.entity.fish.TriggerfishEntity
import net.minecraft.client.render.entity.EntityRendererFactory.Context

class TriggerfishEntityRenderer(context: Context) :
    HybridAquaticFishEntityRenderer<TriggerfishEntity>(context, TriggerfishEntityModel(), true, false)