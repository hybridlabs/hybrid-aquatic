package dev.hybridlabs.aquatic.client.render.entity.fish

import dev.hybridlabs.aquatic.client.model.entity.fish.SnailfishEntityModel
import dev.hybridlabs.aquatic.entity.fish.SnailfishEntity
import net.minecraft.client.render.entity.EntityRendererFactory.Context

class SnailfishEntityRenderer(context: Context) :
    HybridAquaticFishEntityRenderer<SnailfishEntity>(context, SnailfishEntityModel(), true, false)
