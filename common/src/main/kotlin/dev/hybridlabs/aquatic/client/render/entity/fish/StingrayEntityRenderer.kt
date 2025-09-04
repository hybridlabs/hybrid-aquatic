package dev.hybridlabs.aquatic.client.renderer.entity.fish

import dev.hybridlabs.aquatic.client.model.entity.fish.StingrayEntityModel
import dev.hybridlabs.aquatic.entity.fish.StingrayEntity
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class StingrayEntityRenderer(context: Context) :
    HybridAquaticFishEntityRenderer<StingrayEntity>(context, StingrayEntityModel(), true, false)