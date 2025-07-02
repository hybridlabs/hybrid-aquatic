package dev.hybridlabs.aquatic.client.render.entity.fish

import dev.hybridlabs.aquatic.client.model.entity.fish.StingrayEntityModel
import dev.hybridlabs.aquatic.entity.fish.StingrayEntity
import net.minecraft.client.render.entity.EntityRendererFactory.Context

class StingrayEntityRenderer(context: Context) :
    HybridAquaticFishEntityRenderer<StingrayEntity>(context, StingrayEntityModel(), true, false)