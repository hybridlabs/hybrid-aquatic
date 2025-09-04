package dev.hybridlabs.aquatic.client.render.entity.fish

import dev.hybridlabs.aquatic.client.model.entity.fish.PearlfishEntityModel
import dev.hybridlabs.aquatic.entity.fish.PearlfishEntity
import net.minecraft.client.render.entity.EntityRendererFactory.Context

class PearlfishEntityRenderer(context: Context) :
    HybridAquaticFishEntityRenderer<PearlfishEntity>(context, PearlfishEntityModel(), true, false)